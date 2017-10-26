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
 * @data 2015-8-3 上午11:10:11
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
			// MQTT设置说明
			mqtt.setHost(listener.getClient().getHost());
			mqtt.setClientId(listener.getClient().getClientid()); // 用于设置客户端会话的ID。在setCleanSession(false);被调用时，MQTT服务器利用该ID获得相应的会话。此ID应少于23个字符，默认根据本机地址、端口和时间自动生成
			mqtt.setCleanSession(false); // 若设为false，MQTT服务器将持久化客户端会话的主体订阅和ACK位置，默认为true
			mqtt.setKeepAlive((short) 30);// 定义客户端传来消息的最大时间间隔秒数，服务器可以据此判断与客户端的连接是否已经断开，从而避免TCP/IP超时的长时间等待
			mqtt.setUserName(listener.getClient().getUsername());// 服务器认证用户名
			mqtt.setPassword(listener.getClient().getPassword());// 服务器认证密码

			mqtt.setWillTopic(listener.getClient().getSelfWillTopic());// 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息
			mqtt.setWillMessage(listener.getClient().getWillMessage());// 设置“遗嘱”消息的内容，默认是长度为零的消息
			mqtt.setWillQos(QoS.AT_LEAST_ONCE);// 设置“遗嘱”消息的QoS，默认为QoS.ATMOSTONCE
			mqtt.setWillRetain(false);// 若想要在发布“遗嘱”消息时拥有retain选项，则为true
			mqtt.setVersion("3.1.1");

			// 失败重连接设置说明
			mqtt.setConnectAttemptsMax(10L);// 客户端首次连接到服务器时，连接的最大重试次数，超出该次数客户端将返回错误。-1意为无重试上限，默认为-1
			// mqtt.setReconnectAttemptsMax(10L);//客户端已经连接到服务器，但因某种原因连接断开时的最大重试次数，超出该次数客户端将返回错误。-1意为无重试上限，默认为-1
			mqtt.setReconnectDelay(10L);// 首次重连接间隔毫秒数，默认为10ms
			mqtt.setReconnectDelayMax(1000L);// 重连接间隔毫秒数，默认为30000ms
			mqtt.setReconnectBackOffMultiplier(2);// 设置重连接指数回归。设置为1则停用指数回归，默认为2

			// Socket设置说明
			mqtt.setReceiveBufferSize(256 * 1024);// 设置socket接收缓冲区大小，默认为65536（64k）
			mqtt.setSendBufferSize(256 * 1024);// 设置socket发送缓冲区大小，默认为65536（64k）
			mqtt.setTrafficClass(8);// 设置发送数据包头的流量类型或服务类型字段，默认为8，意为吞吐量最大化传输

			// 带宽限制设置说明
			mqtt.setMaxReadRate(0);// 设置连接的最大接收速率，单位为bytes/s。默认为0，即无限制
			mqtt.setMaxWriteRate(0);// 设置连接的最大发送速率，单位为bytes/s。默认为0，即无限制
			// 选择消息分发队列
			mqtt.setDispatchQueue(Dispatch.createQueue(listener.getClient().getClientid()));// 若没有调用方法setDispatchQueue，客户端将为连接新建一个队列。如果想实现多个连接使用公用的队列，显式地指定队列是一个非常方便的实现方法
			// 设置跟踪器
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

			// 使用回调式API
			callbackConnection = mqtt.callbackConnection();
			// 连接监听
			callbackConnection.listener(this);

		} catch (Exception e) {
			log.error("Exception : {}", e.getMessage());
		}
	}

	@Override
	public void connect() {
		log.debug("============connect============");
		this.callbackConnection.connect(new Callback<Void>() {
			// 连接失败
			public void onFailure(Throwable value) {
				log.debug("tcp connect failure! Exception :{} ", value.getMessage());
			}

			// 连接成功
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

		// 发布消息
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
