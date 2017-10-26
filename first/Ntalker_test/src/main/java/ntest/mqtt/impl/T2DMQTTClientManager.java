package ntest.mqtt.impl;

import ntest.mqtt.network.IMQTTCallback;
import ntest.mqtt.network.IMQTTNetwork;
import ntest.mqtt.network.IMQTTNetworkListener;
import ntest.mqtt.network.MQTTNetworkImpl;
import ntest.t2dortchat.T2DConnection;
import ntest.util.MD5Util;
import org.fusesource.mqtt.client.QoS;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class T2DMQTTClientManager implements IMQTTNetworkListener {

	private IMQTTNetwork _network;
	private AtomicBoolean _isConnected = new AtomicBoolean(false);
	private AtomicBoolean _hasResp = new AtomicBoolean(false);
	private Client _client;
	private String _upTopic;
	private String _downTopic;
	private String _willTopic;
	//private String _clientId;
	private T2DConnection t2DConnection; 
	private static final Logger log = LoggerFactory.getLogger(T2DMQTTClientManager.class);

	TimerTask _keepliveTask;
	TimerTask _requestTask;

	public T2DMQTTClientManager(Client client,T2DConnection t2DConnection) {
		this._client = client;
		this.t2DConnection = t2DConnection;
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
				log.error("subscribe self failure! <{}>, uid:<{}>", _client.getSelfTopic(),_client.getUid());
				t2DConnection.netConnectFailed();
			}

		});
		log.debug("connected uid:<{}>", _client.getUid());
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
//		log.debug("topic:<{}>, msg:<{}>",topic,msg);
		if (_client.getSelfTopic().equals(topic)) {
			responseServer(msg);
			return;
		}
		if (null != _willTopic && _willTopic.equals(topic)) {
			_network.unSubscribe(new String[] { _downTopic, _willTopic });
			_upTopic = null;
			_downTopic = null;
			_willTopic = null;
			log.debug("uptopic set null, uid:<{}>", _client.getUid());
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
			log.debug("<{}>,<{}>,<{}>,<{}>",new Object[]{_upTopic,_willTopic,_downTopic,_client.getUid()});
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
					t2DConnection.netConnectFailed();
				}

			});

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void getMessage(String msg) {
		String message = null;
		String method = null;
		Object[] allobj = null;
		JSONObject jsonobj;
		try {
			message = new String(msg.getBytes("gbk"), "utf-8");

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

			//2.�ӿڵ���
			if ("loginResult".equals(method)) {
				t2DConnection.loginResult(allobj);
				remoteKeepAlive();
			}
			if ("connectResult".equals(method)) {
				t2DConnection.connectResult(allobj);
			}else if("remoteNotifyUserChat".equals(method)){
				t2DConnection.remoteNotifyUserChat(allobj[0].toString(), allobj[1].toString(), allobj[2].toString(), allobj[3].toString());
			}else if ("remoteNotifyUserCode".equals(method)){
				t2DConnection.remoteNotifyUserCode(allobj[0].toString(), allobj[1].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Exception : <{}>", e.getMessage());
		}
	}

	private void roomConnect() {
		log.debug("roomConnect");
		String json = generateIOMsg("roomConnect", new Object[] { _client.getSiteid(), "6.3.1", "param2", 1 });
		_network.publish(_upTopic, json, QoS.AT_LEAST_ONCE, new IMQTTCallback() {

			@Override
			public void onSuccess() {
				log.debug("roomConnect success");
				//add stoptime1
				t2DConnection.netConnectSuccess();
			}

			@Override
			public void onFailure(Throwable error) {
				log.debug("roomConnect Failure", error.getMessage());
				t2DConnection.netConnectFailed();
			}

		});
	}

	public void remoteUserLogin() {
		log.debug("remoteUserLogin : {} / {}", _client.getClientid(), _client.getUid());
		String pwd = MD5Util.encode(_client.getUid() + _client.getUidPassword() + _client.getClientid()) + MD5Util.encode(t2DConnection._result.KF_T2D_clientid);

		String json = generateIOMsg("remoteUserLogin",
				new Object[] {t2DConnection._result.KF_T2D_clientid, _client.getUid(), pwd, 1, t2DConnection.KF_VERSION, _client.getClientid()});

		_network.publish(_upTopic, json, QoS.AT_LEAST_ONCE, new IMQTTCallback() {

			@Override
			public void onSuccess() {
				t2DConnection.loginSuccess();
			}

			@Override
			public void onFailure(Throwable error) {
				t2DConnection.loginFailed();
			}

		});
	}

	public void remoteKeepAlive() {
		log.debug("remoteKeepAlive : {} / {}",  _client.getClientid(), _client.getUid());
		if (null == _keepliveTask) {
			final String json = generateIOMsg("remoteKeepAlive", new Object[] {  _client.getClientid(), _client.getUid() });
			_network.publish(_upTopic, json, QoS.AT_LEAST_ONCE, null);
		}
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

	@Override
	public Client getClient() {
		return this._client;
	}

	public void sendMessage(Object[] array) {
		
		log.debug("remoteEndConnection : {} / {}", _client.getClientid(), _client.getUid());
		String json = generateIOMsg("remoteEndConnection", array);
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
}
