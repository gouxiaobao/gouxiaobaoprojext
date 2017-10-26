package ntest.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntest.bean.Visitor;
import ntest.bean.Waiter;

/**
 * 访客和客服之间互发消息
 */
public class Action_SendMessages {
	
	private Waiter _waiter = null;
	private Visitor _visitor = null;
	protected static Logger log = LoggerFactory.getLogger(Action_SendMessages.class.getName());
	
	public Action_SendMessages(Waiter waiter, Visitor visitor)
	{
		_waiter = waiter;
		_visitor = visitor;
	}
//	客服和访客互发消息
	public boolean doAction()
	{		
		try{
			long start = new java.util.Date().getTime();
			
			Thread.sleep(10000);
			int index = 2;
			_waiter.sendMessage("waiter11!!");
			
			Thread.sleep(10000);
			index++;
			_visitor.sendMessage("visitor22！！");
			
			Thread.sleep(10000);
			index++;	
			_waiter.sendMessage("waiter33!!");
			
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			log.warn("doAction " + e);
		}
		
		return false;		
	}
}