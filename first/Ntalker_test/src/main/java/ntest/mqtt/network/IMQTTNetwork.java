package ntest.mqtt.network;

import org.fusesource.mqtt.client.QoS;

/**
 * 
 * @author lijun
 * @data 2015-8-11 обнГ5:57:45
 */
public interface IMQTTNetwork {

	public void publish(final String topic, final String msg, final QoS qos, final IMQTTCallback mqttcb);

	public void subscribe(String[] topics, final IMQTTCallback mqttcb);

	public void unSubscribe(String[] topics);

 	public void addListener(IMQTTNetworkListener listener);

 	public void connect();

 	public void disconnect();
 }
