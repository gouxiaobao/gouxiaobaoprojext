package ntest.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ntest.t2dortchat.T2DConnection;

/**
 * 客服登录t2d服务器
 */
public class Action_KF_LoginT2DServer {
	
	public T2DConnection _t2dConnection = null;
	protected static Logger log = LoggerFactory.getLogger(Action_KF_LoginT2DServer.class.getName());
	
	public Action_KF_LoginT2DServer(T2DConnection t2dConnection)
	{
		_t2dConnection = t2dConnection;
	}
//	连接t2d服务器
	public boolean execute()
	{
		try{
			long start = new java.util.Date().getTime();
			long TIMEOUT = 20000;
			
			_t2dConnection.doLogin();
			
			while(true)
			{
				if(_t2dConnection._result.KF_T2D_loginStatus>0)
				{
					if(_t2dConnection._result.KF_T2D_loginStatus==1)
					{
						log.info("T2D LOGIN 1 RESULT SUCCESS!");
						return true;
					}
					else 
					{
						log.info("T2D LOGIN 1 RESULT FAILED!");
						return false;
					}
//					log.debug("T2D LOGIN RESULT OVER");
//					return _t2dConnection._result.KF_T2D_loginStatus==1;
				}
				else
				{
					long now = new java.util.Date().getTime();
					if(now - start>=TIMEOUT)
					{
						log.debug("T2D LOGIN TIMEOUT");
						_t2dConnection._result.KF_T2D_loginStatus = 2;
						return false;
					}
				}
				Thread.sleep(100);				
			}
		}
		catch(Exception e)
		{
			log.warn("doAction " + e);
			
			_t2dConnection._result.KF_T2D_connectStatus = 2;
			
		}
		
		return false;		
	}
}