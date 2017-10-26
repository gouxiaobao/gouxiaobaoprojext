package ntest.mqtt.network;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.hawtdispatch.Dispatch;
import org.fusesource.mqtt.client.*;
import org.fusesource.mqtt.codec.MQTTFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * MQTT Client
 * <p>
 * manage MQTT connect/reconnect/subscription/unsub/publish...
 * 
 * @author lijun
 * @data 2015-8-3 ����11:10:11
 */
public class MQTTNetworkImpl implements Listener, IMQTTNetwork {

	private AtomicBoolean connSuccess = new AtomicBoolean(false);
	private MQTT mqtt;
	private CallbackConnection callbackConnection;
	private IMQTTNetworkListener listener;
	private ScheduledExecutorService _scheduledExecutor = new ScheduledThreadPoolExecutor(20);
	private static Logger log = LoggerFactory.getLogger(MQTTNetworkImpl.class);
	
	public MQTTNetworkImpl(IMQTTNetworkListener listener) {
		this.listener = listener;
		init();
	}

	private void init() {
		try {
			mqtt = new MQTT();
			// MQTT����˵��
			mqtt.setHost(listener.getClient().getHost());
			mqtt.setClientId(listener.getClient().getClientid()); // �������ÿͻ��˻Ự��ID����setCleanSession(false);������ʱ��MQTT���������ø�ID�����Ӧ�ĻỰ����IDӦ����23���ַ���Ĭ�ϸ��ݱ�����ַ���˿ں�ʱ���Զ�����
			mqtt.setCleanSession(false); // ����Ϊfalse��MQTT���������־û��ͻ��˻Ự�����嶩�ĺ�ACKλ�ã�Ĭ��Ϊtrue
			mqtt.setKeepAlive((short) 30);// ����ͻ��˴�����Ϣ�����ʱ�������������������Ծݴ��ж���ͻ��˵������Ƿ��Ѿ��Ͽ����Ӷ�����TCP/IP��ʱ�ĳ�ʱ��ȴ�
			mqtt.setUserName(listener.getClient().getUsername());// ��������֤�û���
			mqtt.setPassword(listener.getClient().getPassword());// ��������֤����

			mqtt.setWillTopic(listener.getClient().getSelfWillTopic());// ���á���������Ϣ�Ļ��⣬���ͻ����������֮������������жϣ��������������ͻ��˵ġ���������Ϣ
			mqtt.setWillMessage(listener.getClient().getWillMessage());// ���á���������Ϣ�����ݣ�Ĭ���ǳ���Ϊ�����Ϣ
			mqtt.setWillQos(QoS.AT_LEAST_ONCE);// ���á���������Ϣ��QoS��Ĭ��ΪQoS.ATMOSTONCE
			mqtt.setWillRetain(false);// ����Ҫ�ڷ�������������Ϣʱӵ��retainѡ���Ϊtrue
			mqtt.setVersion("3.1.1");

			// ʧ������������˵��
			mqtt.setConnectAttemptsMax(10L);// �ͻ����״����ӵ�������ʱ�����ӵ�������Դ����������ô����ͻ��˽����ش���-1��Ϊ���������ޣ�Ĭ��Ϊ-1
			// mqtt.setReconnectAttemptsMax(10L);//�ͻ����Ѿ����ӵ�������������ĳ��ԭ�����ӶϿ�ʱ��������Դ����������ô����ͻ��˽����ش���-1��Ϊ���������ޣ�Ĭ��Ϊ-1
			mqtt.setReconnectDelay(10L);// �״������Ӽ����������Ĭ��Ϊ10ms
			mqtt.setReconnectDelayMax(1000L);// �����Ӽ����������Ĭ��Ϊ30000ms
			mqtt.setReconnectBackOffMultiplier(2);// ����������ָ���ع顣����Ϊ1��ͣ��ָ���ع飬Ĭ��Ϊ2

			// Socket����˵��
			mqtt.setReceiveBufferSize(256 * 1024);// ����socket���ջ�������С��Ĭ��Ϊ65536��64k��
			mqtt.setSendBufferSize(256 * 1024);// ����socket���ͻ�������С��Ĭ��Ϊ65536��64k��
			mqtt.setTrafficClass(8);// ���÷������ݰ�ͷ���������ͻ���������ֶΣ�Ĭ��Ϊ8����Ϊ��������󻯴���

			// ������������˵��
			mqtt.setMaxReadRate(0);// �������ӵ����������ʣ���λΪbytes/s��Ĭ��Ϊ0����������
			mqtt.setMaxWriteRate(0);// �������ӵ���������ʣ���λΪbytes/s��Ĭ��Ϊ0����������
			// ѡ����Ϣ�ַ�����
			mqtt.setDispatchQueue(Dispatch.createQueue(listener.getClient().getClientid()));// ��û�е��÷���setDispatchQueue���ͻ��˽�Ϊ�����½�һ�����С������ʵ�ֶ������ʹ�ù��õĶ��У���ʽ��ָ��������һ���ǳ������ʵ�ַ���
			// ���ø�����
			mqtt.setTracer(new Tracer() {
				@Override
				public void onReceive(MQTTFrame frame) {
					//log.debug("recv: <{}>", frame);
				}

				@Override
				public void onSend(MQTTFrame frame) {
					//log.debug("send: <{}>", frame);
				}

				@Override
				public void debug(String message, Object... args) {
					log.debug("debug: <{}> , <{}>", message, args);
				}
			});

			// ʹ�ûص�ʽAPI
			callbackConnection = mqtt.callbackConnection();
			// ���Ӽ���
			callbackConnection.listener(this);

		} catch (Exception e) {
			log.error("Exception : {}", e.getMessage());
		}
	}

	@Override
	public void connect() {
		log.debug("============connect============");
		this.callbackConnection.connect(new Callback<Void>() {
			// ����ʧ��
			public void onFailure(Throwable value) {
				log.debug("tcp connect failure! Exception :{} ", value.getMessage());
			}

			// ���ӳɹ�
			public void onSuccess(Void v) {
				log.debug("tcp connect success");
			}
		});
	}

	@Override
	public void disconnect() {
		log.debug("============disconnect============");
		this.callbackConnection.disconnect(null);
	}

	@Override
	public void onConnected() {
		log.debug("============mqtt connect success============");
		connSuccess.set(true);
		listener.onConnected();
	}

	@Override
	public void onDisconnected() {
		if (connSuccess.getAndSet(false)) {
			log.debug("has disConnected!");
			return;
		}
		log.debug("============mqtt disconnect============");
		connSuccess.set(false);
		listener.onDisconnected();
	}

	@Override
	public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
		String jsonmsg = new String(body.toByteArray());
		listener.onPublish(topic.toString(), jsonmsg);
		//log.debug("receive msg : <{}> , <{}>", topic, jsonmsg);
		ack.run();
	}

	@Override
	public void onFailure(Throwable value) {
		log.debug("mqtt connect failure ! Exception : {}", value.getMessage());
	}

	public void publish(final String topic, final String msg, final QoS qos, final IMQTTCallback mqttcb) {
		if (null == topic || topic.isEmpty()) {
			log.warn("topic is null! <{}>, <{}>", topic, msg);
			return;
		}

		TimeoutCallback<Void> cb = new TimeoutCallback<Void>();

		// ������Ϣ
		callbackConnection.publish(topic, msg.getBytes(), qos, false, cb);
		if (!cb.then(mqttcb)) {
			cb.waitTimeout(_scheduledExecutor, listener.getClient().getTimeoutPublish());
		}

	}

	public void subscribe(final String[] topics, final IMQTTCallback mqttcb) {
		if (null == topics || topics.length <= 0) {
			log.debug("topics is empty !!! <{}>" + topics);
			return;
		}
		ArrayList<Topic> subTopics = new ArrayList<Topic>();
		for (String topic : topics) {
			if (null != topic && !topic.isEmpty()) {
				subTopics.add(new Topic(topic, QoS.AT_LEAST_ONCE));
			}
		}
		if (subTopics.isEmpty()) {
			log.debug("topics is null !!! <{}>" + topics);
			return;
		}

		TimeoutCallback<byte[]> cb = new TimeoutCallback<byte[]>();
		callbackConnection.subscribe(subTopics.toArray(new Topic[subTopics.size()]), cb);
		if (!cb.then(mqttcb)) {
			cb.waitTimeout(_scheduledExecutor, listener.getClient().getTimeoutPublish());
		}
	}

	@Override
	public void unSubscribe(final String[] topics) {
		if (topics == null || topics.length == 0) {
			log.debug("topics is empty !!! <{}>" + topics);
			return;
		}
		ArrayList<UTF8Buffer> utf8Topics = new ArrayList<UTF8Buffer>();
		for (String topic : topics) {
			if (null != topic && !topic.isEmpty()) {
				utf8Topics.add(UTF8Buffer.utf8(topic));
			}
		}
		if (utf8Topics.isEmpty()) {
			log.debug("topics is empty! <{}>", topics);
			return;
		}
		callbackConnection.unsubscribe(utf8Topics.toArray(new UTF8Buffer[utf8Topics.size()]), new Callback<Void>() {

			@Override
			public void onFailure(Throwable value) {
				// TODO review
				log.error("unsubscribe failure! Exception : <{}> , <{}>", topics.toString(), value.getMessage());
			}

			@Override
			public void onSuccess(Void value) {
				log.debug("unsubscribe success! <{}>", topics.toString());
			}

		});
	}

	@Override
	public void addListener(IMQTTNetworkListener listener) {
		this.listener = listener;
	}

}
