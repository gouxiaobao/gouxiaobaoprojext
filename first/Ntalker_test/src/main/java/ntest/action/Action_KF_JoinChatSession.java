package ntest.action;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntest.bean.Waiter;

/**
 * 通过客服连接tchat服务器
 */
public class Action_KF_JoinChatSession {
	
	private Waiter _waiter;
	protected static Logger log = LoggerFactory.getLogger(Action_KF_JoinChatSession.class.getName());
	
	public Action_KF_JoinChatSession(Waiter waiter) {
		_waiter = waiter;
	}


//	连接tchat服务器
	public boolean doAction()
	{
		try{
			log.debug("TCHAT Connection 1 Start !");
			
			long start = new java.util.Date().getTime();
			long TIMEOUT = 20000;
			
			_waiter._tchatConnection.doConnect();
			
			while(true)
			{
				if(_waiter._tchatConnection._result.TCHAT_connectStatus>0)
				{
					
					if(_waiter._tchatConnection._result.TCHAT_connectStatus==1)
					{
						log.debug("TCHAT Connection 1 RESULT SUCCESS!");
						return true;
					}
					else
					{
						log.debug("TCHAT Connection 1 RESULT FAILED!");
						break;
					}
				}
				else
				{
					long now = new java.util.Date().getTime();
					if(now - start>=TIMEOUT)
					{
						log.debug("TCHAT Connection 1 RESULT TIMEOUT!");
						
						break;
					}
				}
				
				Thread.sleep(100);				
			}
			
			log.debug("TCHAT Connection 1 ERROR OVER and dicoonected connetion !");
			
			_waiter._tchatConnection.doDisconnect();
			
			Thread.sleep(10000);

//			第一次失败后第二次在连接一次tchat
			log.debug("TCHAT Connection 2 Start !");
			
			start = new java.util.Date().getTime();

			_waiter._tchatConnection._result.TCHAT_connectStatus = 0;
			
			_waiter._tchatConnection.doConnect();
			
			while(true)
			{
				if(_waiter._tchatConnection._result.TCHAT_connectStatus>0)
				{
					if(_waiter._tchatConnection._result.TCHAT_connectStatus==1)
					{
						log.debug("TCHAT Connection 2 RESULT SUCCESS !");
						
						return true;
					}
					else
					{
						log.debug("TCHAT Connection 2 RESULT FAILED !");
						return false;
					}
				}
				else
				{
					long now = new java.util.Date().getTime();
					if(now - start>=TIMEOUT)
					{
						log.debug("TCHAT Connection 2 RESULT TIMEOUT !");
						_waiter._tchatConnection._result.TCHAT_connectStatus = 2;
						return false;
					}
				}
				
				Thread.sleep(100);				
			}			
		}
		catch(Exception e)
		{
			log.warn("doAction " + e);
			_waiter._tchatConnection._result.TCHAT_connectStatus = 2;
			
		}
		
		return false;		
	}
}


