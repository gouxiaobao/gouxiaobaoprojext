package ntest.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntest.bean.Visitor;

/**
 * 访客连接tchat服务器
 */

public class Action_WEB_ConnectTChatServer {
	
	public Visitor _visitor;
	protected static Logger log = LoggerFactory.getLogger(Action_WEB_ConnectTChatServer.class.getName());
	
	public Action_WEB_ConnectTChatServer(Visitor visitor) {
		_visitor = visitor;
	}

//	连接tachat服务器
	public boolean doAction()
	{
		try{
			log.debug("TCHAT Connection 1 Start !");
			
			long start = new java.util.Date().getTime();
			long TIMEOUT = 20000;
			
			_visitor._tchatConnection.doConnect();
			
			while(true)
			{
				if(_visitor._tchatConnection._result.TCHAT_connectStatus>0)  //有结果
				{
					
					if(_visitor._tchatConnection._result.TCHAT_connectStatus==1)  //连接成功，则返回
					{
						log.debug("TCHAT Connection 1 RESULT SUCCESS!");
						return true;
					}
					else  //连接失败，则结束本次连接，并重试。
					{
						log.debug("TCHAT Connection 1 RESULT FAILED!");
						break;
					}
				}
				else  //没有结果，则检查超时
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
			
			_visitor._tchatConnection.doDisconnect();
			
			Thread.sleep(10000);
			
			log.debug("TCHAT Connection 2 Start !");
			start = new java.util.Date().getTime();
			_visitor._tchatConnection._result.TCHAT_connectStatus = 0;
			_visitor._tchatConnection.doConnect();
			
			while(true)
			{
				if(_visitor._tchatConnection._result.TCHAT_connectStatus>0)
				{
					if(_visitor._tchatConnection._result.TCHAT_connectStatus==1)
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
						_visitor._tchatConnection._result.TCHAT_connectStatus = 2;
						return false;
					}
				}
				Thread.sleep(100);				
			}			
		}
		catch(Exception e)
		{
			log.warn("doAction " + e);
			_visitor._tchatConnection._result.TCHAT_connectStatus = 2;
			
		}
		
		return false;		
	}
}


