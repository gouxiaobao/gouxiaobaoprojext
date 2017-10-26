package ntest.mqtt.network;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.fusesource.mqtt.client.Callback;

public class TimeoutCallback<T> implements Callback<T>, Runnable{

    private final AtomicBoolean _redux = new AtomicBoolean(false);
    IMQTTCallback next;
    Throwable error;
    T value;
    
	@Override
	public void onSuccess(T value) {
		IMQTTCallback callback = null;
        synchronized(this)  {
            this.value = value;
            _redux.set(true);
            callback = next;
        }
        if( callback!=null ) {
            callback.onSuccess();
        }
	}

	@Override
	public void onFailure(Throwable value) {
		IMQTTCallback callback = null;
        synchronized(this)  {
            error = value;
            _redux.set(true);
            callback = next;
        }
        if( callback!=null ) {
            callback.onFailure(value);
        }
	}

	public boolean then(IMQTTCallback callback){
		 boolean fire = false;
	        synchronized(this)  {
	            next = callback;
	            if( _redux.get() ) {
	                fire = true;
	            }
	        }
	        if( fire ) {
	            if( error!=null ) {
	                callback.onFailure(error);
	            } else {
	                callback.onSuccess();
	            }
	        }
	        return fire;
	}
	
	void waitTimeout(ScheduledExecutorService executor,long timeout){
		executor.schedule(this, timeout, TimeUnit.MILLISECONDS);
	}

	@Override
	public void run() {
		try{
			
			if( _redux.get() ) {
				return;
			} else {
				throw new TimeoutException();
			}
			
		}catch(Exception e){
			if(null != next){
				next.onFailure(e);
			}
		}
	}
}
