package ntest.mqtt.impl;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import org.fusesource.mqtt.client.QoS;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntest.t2dortchat.T2DConnection;
import ntest.t2dortchat.TChatConnection;
import ntest.mqtt.network.IMQTTCallback;
import ntest.mqtt.network.IMQTTNetwork;
import ntest.mqtt.network.IMQTTNetworkListener;
import ntest.mqtt.network.MQTTNetworkImpl;
import ntest.mqtt.impl.Client;

public class TChatMQTTClientManager implements IMQTTNetworkListener {

	private IMQTTNetwork _network;
	private AtomicBoolean _isConnected = new AtomicBoolean(false);
	private AtomicBoolean _hasResp = new AtomicBoolean(false);
	private Client _client;
	private String _upTopic;
	private String _downTopic;
	private String _willTopic;
	private TChatConnection tChatConnection; 
	private static final Logger log = LoggerFactory.getLogger(TChatMQTTClientManager.class);

	TimerTask _keepliveTask;
	TimerTask _requestTask;

	public TChatMQTTClientManager(Client client,TChatConnection tChatConnection) {
		this._client = client;
		this.tChatConnection = tChatConnection;
		init();
	}

	public void init() {
		_network = new MQTTNetworkImpl(this);
		_network.connect();
	}

	@Override
	public void onConnected() {
		_network.subscribe(new String[] { _client.getSelfTopic() }, new IMQTTCallback() {

			@Override
			public void onSuccess() {
				_isConnected.set(true);
				requestServer();
			}

			@Override
			public void onFailure(Throwable error) {
				log.error("subscribe self failure! <{}>", _client.getSelfTopic());
			}
		});
		log.debug("connected");
	}

	@Override
	public void onDisconnected() {
		_hasResp.set(false);
		_isConnected.set(false);
		_keepliveTask = null;
		_requestTask = null;
	}

	@Override
	public void onPublish(String topic, String msg) {
		log.debug("receive topic:<{}>, msg:<{}>", topic, msg);
		if (_client.getSelfTopic().equals(topic)) {
			responseServer(msg);
			return;
		}
		if (null != _willTopic && _willTopic.equals(topic)) {
			_network.unSubscribe(new String[] { _downTopic, _willTopic });
			_upTopic = null;
			_downTopic = null;
			_willTopic = null;
			//_clientId = null;
			requestServer();
		} else if (null != _downTopic) {
			getMessage(msg);
		}
	}

	public void destroy() {
		_network.publish(_client.getSelfWillTopic(), _client.getWillMessage(), QoS.AT_MOST_ONCE, new IMQTTCallback() {

			@Override
			public void onSuccess() {
				log.debug("send will onSuccess");
			}

			@Override
			public void onFailure(Throwable error) {
				log.debug("send will onFailure! <{}>", error.getMessage());
			}
		});
		_network.disconnect();
	}

	private void requestServer() {
		log.debug("requestServer");
		if (null == _requestTask) {
			final String json = generateIOMsg("requestServer",
					new Object[] { _client.getUid(), _client.getClientid(), _client.getAppid()});

			if (_hasResp.get()) {
				return;
			}
			_network.publish(_client.getRouteTopic(), json, QoS.AT_MOST_ONCE, new IMQTTCallback() {

				@Override
				public void onSuccess() {
					log.debug("requestServer onSuccess");
				}

				@Override
				public void onFailure(Throwable error) {
					log.debug("requestServer onFailure! <{}>", error.getMessage());
				}
			});
		}
	}

	private void responseServer(String msg) {
		try {
			JSONObject json = new JSONObject(msg);
			JSONArray params = json.getJSONArray("params");
			if (null == params || params.length() < 4) {
				return;
			}

			_upTopic = (String) params.get(0);
			_willTopic = (String) params.get(1);
			_downTopic = (String) params.get(2);
			String appId = (String) params.get(3);
			_hasResp.set(true);
			_network.subscribe(new String[] { _willTopic, _downTopic }, new IMQTTCallback() {
				@Override
				public void onSuccess() {
					roomConnect();
				}
				@Override
				public void onFailure(Throwable error) {
					log.debug("responseServer onFailure! <{}>", error.getMessage());
					tChatConnection.netConnectFailed();
				}

			});

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	//处理下行消息
	private void getMessage(String msg) {
		String message = null;
		String method = null;
		Object[] allobj = null;
		JSONObject jsonobj;
		try {
			message = new String(msg.getBytes("gbk"), "utf-8");
			//log.debug("receive : <{}>", message);
			jsonobj = new JSONObject(message);
			if (jsonobj.has("method")) {
				method = jsonobj.getString("method");
			}
			if (null == method || method.isEmpty()) {
				return;
			}
			if (jsonobj.has("params")) {
				JSONArray array = jsonobj.getJSONArray("params");
				if (array.length() > 0) {
					allobj = new Object[array.length()];
					for (int i = 0; i < array.length(); i++) {
						Object theobj = array.get(i);
						allobj[i] = theobj;
					}
				}
			}
			if ("LoginResult".equals(method)) {
				tChatConnection.LoginResult(allobj);
			}
			else if("remoteSendMessage".equals(method)){
				tChatConnection.onReceiveMessage(msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception : <{}>", e.getMessage());
		}
	}

	private void roomConnect() {
		
		log.debug("uid:<{}>, token:<{}>, sessionid:<{}>, machineid:<{}>, targetid:<{}>, devicetype:<{}>", new Object[]{_client.getUid(), _client.getUsertoken(), _client.getSessionId(), _client.getMyMachineId(), _client.getTargetId(), _client.getDevicetype()});
		String json = generateIOMsg("roomConnect", new Object[] { _client.getUid(), _client.getUsertoken(), _client.getSessionId(), _client.getTargetId(), _client.getMyMachineId(), _client.getDevicetype()});
		_network.publish(_upTopic, json, QoS.AT_LEAST_ONCE, new IMQTTCallback() {

			@Override
			public void onSuccess() {
				log.debug("roomConnect success");
				tChatConnection.netConnectSuccess();
			}

			@Override
			public void onFailure(Throwable error) {
				log.debug("roomConnect Failure", error.getMessage());
				tChatConnection.netConnectFailed();
			}

		});
	}

	private String generateIOMsg(String method, Object[] params) {
		JSONObject outMsgJSONObj = new JSONObject();
		try {
			if (null != method) {
				outMsgJSONObj.put("method", method);
			}
			if (null != params) {
				outMsgJSONObj.put("params", params);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return outMsgJSONObj.toString();

	}

	public void sendMessage(Object[] array) {
		
		log.debug("remoteSendMessage : {} / {}", _client.getClientid(), _client.getUid());
		String json = generateIOMsg("remoteSendMessage", array);
		log.debug("json:<{}>", json);
		_network.publish(_upTopic, json, QoS.AT_MOST_ONCE, new IMQTTCallback() {

			@Override
			public void onSuccess() {
				
				log.debug("sendMessage onSuccess");
			}

			@Override
			public void onFailure(Throwable error) {
				log.debug("sendMessage onFailure! <{}>", error.getMessage());
			}
		});
	}
	
	public void send(String method, Object[] array) {
		
		log.debug(method + " : {} / {}", _client.getClientid(), _client.getUid());
		String json = generateIOMsg(method, array);
		log.debug("json:<{}>", json);
		_network.publish(_upTopic, json, QoS.AT_MOST_ONCE, new IMQTTCallback() {

			@Override
			public void onSuccess() {
				
				log.debug("sendMessage onSuccess");
			}

			@Override
			public void onFailure(Throwable error) {
				log.debug("sendMessage onFailure! <{}>", error.getMessage());
			}
		});
	}
	
	@Override
	public Client getClient() {
		return this._client;
	}
	
}
