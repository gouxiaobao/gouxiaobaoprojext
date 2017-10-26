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
	//ͨ��http��t2d����������ͷ���Ϣ
	public GetWaiterResult getWaiterID() {
		Action_WEB_GetWaiterID action = new Action_WEB_GetWaiterID(this);
		action.execute();
		return action._result;
	}
	//����tchat������
	public TChatConnectionResult connectTchatServer(String targetUid) {
		
		_tchatConnection = new TChatConnection(_monitorTask,false,myuserId, pcId,targetUid,"",web_messages);
		Action_WEB_ConnectTChatServer action = new Action_WEB_ConnectTChatServer(this);
		action.doAction();
		return _tchatConnection._result;
	}
	//��tchat����������Ϣ
	public MessagesResult sendMessage(String msg) {
		_tchatConnection.sendMessage(msg);
		return _tchatConnection._web_messages;
	}
	//��ȡ�ظ���Ϣ
	public MessagesResult recvMessage(){
		return _tchatConnection._web_messages;
	}

	//��mqtt�Ͽ�����
	public void doDisconnect(){
		_tchatConnection.doDisconnect();
	}

	// ��tchat����������Ϣ
	public MessagesResult sendFirstMessage(String msg, T2DConnectionResult kfConnectT2DResult) {
		Action_WEB_SendFirstMessage action = new Action_WEB_SendFirstMessage(this,msg,kfConnectT2DResult);
		action.doAction();
		return _tchatConnection._web_messages;
	}

	//�����ļ���Ϣ
	public MessagesResult sendFileMessage() {
		//�ϴ��ļ�
		Action_WEB_FileTest a1 = new Action_WEB_FileTest(_monitorTask,false);
		FileTestResult fileTestResult=a1.doUploadAction();

		//�ж��Ƿ��ϴ��ɹ����ϴ��ɹ�������Ϣ
		if (fileTestResult.getUrl()!=null && fileTestResult.WEB_FILE_UPLOAD == true){
			_tchatConnection.sendMessage(fileTestResult.getUrl());
			_tchatConnection._web_messages.setFileTestResult(fileTestResult);
		}else {
			_tchatConnection.sendMessage("UploadFail");
		}
		return _tchatConnection._web_messages;
	}

}
