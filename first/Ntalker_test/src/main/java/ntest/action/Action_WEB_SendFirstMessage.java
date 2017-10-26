package ntest.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntest.action.result.T2DConnectionResult;
import ntest.bean.Visitor;

/**
 * 访客和客服互发消息
 */

public class Action_WEB_SendFirstMessage {
	
	public Visitor _visitor;
	public String _msg;
	public T2DConnectionResult _result;
	protected static Logger log = LoggerFactory.getLogger(Action_WEB_SendFirstMessage.class.getName());
	
	public Action_WEB_SendFirstMessage(Visitor visitor, String msg, T2DConnectionResult kfConnectT2DResult)
	{
		_visitor = visitor;
		_msg = msg;
		_result = kfConnectT2DResult;
	}
//	访客向客服发送信息
	public boolean doAction()
	{
		
		try{
			long start = new java.util.Date().getTime();
			long TIMEOUT = 10000;

			_visitor._tchatConnection.sendMessage(_msg);
			
			_result.KF_T2D_chatRequestStatus = 0;
			
			while(true)
			{
				if(_result.KF_T2D_chatRequestStatus > 0)
				{
					log.debug("TCHAT KF_T2D_chatRequestStatus RESULT OVER");
					return _result.KF_T2D_chatRequestStatus==1;
				}
				else
				{
					long now = new java.util.Date().getTime();
					if(now - start>=TIMEOUT)
					{
						log.debug("TCHAT KF_T2D_chatRequestStatus TIMEOUT");
						_result.KF_T2D_chatRequestStatus = 2;
						return false;
					}
				}
				Thread.sleep(100);				
			}
		}
		catch(Exception e)
		{
			log.warn("doAction " + e);
			_result.KF_T2D_chatRequestStatus = 2;
		}
		
		return false;		
	}
}