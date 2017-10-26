package ntest.action.result;

import java.util.HashMap;

public class TChatConnectionResult {

//	访客和客服连接tchat状态
	public int TCHAT_connectStatus = -1;   //tchat的连接状态 -1 未开始  0 connecting  1 connected 2 disconnected
	
	public String TCHAT_clientid = null;
//	public String TCHAT_sessionID = "";
	public String TCHAT_sessionID = null;

	public HashMap<String,Long> TCHAT_sendMessagesTime = new HashMap<String,Long>(); //保存了发送的信息
	public HashMap<String,Long> TCHAT_recvMessagesTime = new HashMap<String,Long>();//保存了从mqtt服务器发送过来的数据



	@Override
	public String toString() {
		return "TChatConnectionResult [ TCHAT_connectStatus="
				+ TCHAT_connectStatus + ", TCHAT_clientid=" + TCHAT_clientid
				+ ", TCHAT_sessionID=" + TCHAT_sessionID
				+ ", TCHAT_sendMessagesTime=" + TCHAT_sendMessagesTime
				+ ", TCHAT_recvMessagesTime=" + TCHAT_recvMessagesTime + "]";
	}

}
