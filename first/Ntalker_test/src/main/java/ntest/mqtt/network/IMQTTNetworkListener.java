package ntest.mqtt.network;

import ntest.mqtt.impl.Client;

/**
 * MQTT message listener interface
 * 
 * @author lijun
 * @data 2015-8-3 ионГ11:03:31
 */
public interface IMQTTNetworkListener {

	public void onPublish(String topic, String msg);
	
	public void onConnected();
	
	public void onDisconnected();
	
	public Client getClient();
}
