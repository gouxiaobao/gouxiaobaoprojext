package ntest.bean;

import ntest.action.result.*;

import ntest.t2dortchat.TChatConnection;
import ntest.action.Action_WEB_ConnectTChatServer;
import ntest.action.Action_WEB_FileTest;
import ntest.action.Action_WEB_GetWaiterID;
import ntest.action.Action_WEB_SendFirstMessage;



public class Visitor {
	
	public String pcId;
	public String myuserId;
	
	public TChatConnection _tchatConnection = null;
	public MonitorTask _monitorTask = null;
	public MessagesResult web_messages = null;

//	protected static Logger log = LoggerFactory.getLogger(Visitor.class.getName());

	public Visitor(){
		web_messages = new MessagesResult();
	}

	public void setMonitorTask(MonitorTask monitorTask) {
		_monitorTask = monitorTask;
	}
	//通过http向t2d服务器请求客服信息
	public GetWaiterResult getWaiterID() {
		Action_WEB_GetWaiterID action = new Action_WEB_GetWaiterID(this);
		action.execute();
		return action._result;
	}
	//连接tchat服务器
	public TChatConnectionResult connectTchatServer(String targetUid) {
		
		_tchatConnection = new TChatConnection(_monitorTask,false,myuserId, pcId,targetUid,"",web_messages);
		Action_WEB_ConnectTChatServer action = new Action_WEB_ConnectTChatServer(this);
		action.doAction();
		return _tchatConnection._result;
	}
	//向tchat发送聊天信息
	public MessagesResult sendMessage(String msg) {
		_tchatConnection.sendMessage(msg);
		return _tchatConnection._web_messages;
	}
	//获取回复信息
	public MessagesResult recvMessage(){
		return _tchatConnection._web_messages;
	}

	//与mqtt断开连接
	public void doDisconnect(){
		_tchatConnection.doDisconnect();
	}

	// 向tchat发送聊天信息
	public MessagesResult sendFirstMessage(String msg, T2DConnectionResult kfConnectT2DResult) {
		Action_WEB_SendFirstMessage action = new Action_WEB_SendFirstMessage(this,msg,kfConnectT2DResult);
		action.doAction();
		return _tchatConnection._web_messages;
	}

	//发送文件信息
	public MessagesResult sendFileMessage() {
		//上传文件
		Action_WEB_FileTest a1 = new Action_WEB_FileTest(_monitorTask,false);
		FileTestResult fileTestResult=a1.doUploadAction();

		//判断是否上传成功，上传成功后发送消息
		if (fileTestResult.getUrl()!=null && fileTestResult.WEB_FILE_UPLOAD == true){
			_tchatConnection.sendMessage(fileTestResult.getUrl());
			_tchatConnection._web_messages.setFileTestResult(fileTestResult);
		}else {
			_tchatConnection.sendMessage("UploadFail");
		}
		return _tchatConnection._web_messages;
	}

}
