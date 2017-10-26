package ntest.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.q2.powerdog.COMMON;
import ntest.bean.Waiter;
import ntest.util.Statics;

/**
 * 此类为业务类，客服先连接T2D服务器进行登录
 */
public class Action_KF_ConnectT2DServer {

	public Waiter _waiter;
	protected static Logger log = LoggerFactory.getLogger(Action_KF_ConnectT2DServer.class.getName());

	public Action_KF_ConnectT2DServer(Waiter waiter) {
		_waiter = waiter;
	}
	
	public boolean execute() {
		
		String protocolType = _waiter._monitorTask.getProtocalType();
		log.debug("protocolType= " + protocolType);
		if(protocolType!=null && protocolType.equalsIgnoreCase("mqtt"))
		{
			return mqttConnect();
		}
		else
		{
			//return rtmpConnect();
		}
		return false;
	}
	//连接mqtt
	private boolean mqttConnect() {
		
		try{
			log.debug("T2D Connection 1 Start !");
			
			if(_waiter._monitorTask == null || Statics.isNullOrEmpty(_waiter._monitorTask._flashServerResult.t2dmqttserver)) {
				log.debug("t2dserver is null");
				return false;
			}
			
			long TIMEOUT = 15000;
			long start = new java.util.Date().getTime();
			
			_waiter._t2dConnection.doConnect();
			log.debug("KF_T2D_connectStatus:" + _waiter._t2dConnection._result.KF_T2D_connectStatus);
			
			while(true)
			{
				if(_waiter._t2dConnection._result.KF_T2D_connectStatus>0)
				{
					if(_waiter._t2dConnection._result.KF_T2D_connectStatus==1){
						log.debug("T2D Connection 1 RESULT SUCCESS!");
						return true;
					}
					else
					{
						log.debug("T2D Connection 1 RESULT FAILED!");
						break;
					}
				}
				else
				{
					long now = new java.util.Date().getTime();
					if(now - start>=TIMEOUT)
					{
						log.debug("T2D Connection 1 RESULT TIMEOUT!");
						
						break;
					}
				}
				
				Thread.sleep(100);				
			}
			
			log.debug("T2D Connection 1 ERROR OVER and dicoonected connetion !");
			
			_waiter._t2dConnection.doDisconnect();
			
			Thread.sleep(10000);
			
			log.debug("T2D Connection 2 Start !");
			
			start = new java.util.Date().getTime();
			
			_waiter._t2dConnection._result.KF_T2D_connectStatus = 0;
			
			_waiter._t2dConnection.doConnect();
			
			while(true)
			{
				if(_waiter._t2dConnection._result.KF_T2D_connectStatus>0)
				{
					if(_waiter._t2dConnection._result.KF_T2D_connectStatus==1)
					{
						log.debug("T2D Connection 2 RESULT SUCCESS !");
						
						return true;
					}
					else
					{
						log.debug("T2D Connection 2 RESULT FAILED !");
						return false;
					}
				}
				else
				{
					long now = new java.util.Date().getTime();
					if(now - start>=TIMEOUT)
					{
						log.debug("T2D Connection 2 RESULT TIMEOUT !");
						_waiter._t2dConnection._result.KF_T2D_connectStatus = 2;
						return false;
					}
				}
				
				Thread.sleep(100);				
			}	
			
		}
		catch(Exception e)
		{
			log.warn("doAction " + e);
			_waiter._t2dConnection._result.KF_T2D_connectStatus = 2;
		}
		
		return false;		
	}
}