package ntest.mqtt.network;

public interface IMQTTCallback{
	
    public void onSuccess();
    public void onFailure(Throwable error);
}
