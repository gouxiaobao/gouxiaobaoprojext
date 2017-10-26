package ntest.bean;

import ntest.action.*;
import ntest.action.result.*;
import ntest.t2dortchat.T2DConnection;
import ntest.t2dortchat.TChatConnection;


public class Waiter {

	public String _vuserId;
	public String _myuserId;

	public T2DConnection _t2dConnection = null;
	public TChatConnection _tchatConnection = null;
	public MonitorTask _monitorTask= null;
	public MessagesResult kf_messages = null;

	public Waiter() {
		kf_messages = new MessagesResult();
	}

	public void setMonitorTask(MonitorTask monitorTask) {
		_monitorTask = monitorTask;
	}

	/**
	 * ���ƿͷ�ֻ�ܽӴ���ǰ�ÿ�
	 * @param vuserId
	 */
	public void setVUserId(String vuserId) {
		_vuserId = vuserId;
	}

	//����t2d������
	public T2DConnectionResult connectT2D() {
		_t2dConnection = new T2DConnection(this);
		Action_KF_ConnectT2DServer action = new Action_KF_ConnectT2DServer(this);
		action.execute();
		return _t2dConnection._result;
	}
	//��¼t2d������
	public T2DConnectionResult loginT2D() {
		Action_KF_LoginT2DServer action = new Action_KF_LoginT2DServer(_t2dConnection);
		action.execute();
		return _t2dConnection._result;
	}

	//	������Ϣ
	public MessagesResult sendMessage(String msg) {
		_tchatConnection.sendMessage(msg);
		return _tchatConnection._web_messages;
	}

	//��ȡ�ظ���Ϣ
	public MessagesResult recvMessage(){
		return _tchatConnection._web_messages;
	}

	public TChatConnectionResult joinChatSession(String targetUid) {
		_tchatConnection = new TChatConnection(_monitorTask, true, _myuserId, _myuserId, targetUid, _t2dConnection._result.KF_TCHAT_sessionID,kf_messages);
		Action_KF_JoinChatSession action = new Action_KF_JoinChatSession(this);
		action.doAction();
		return _tchatConnection._result;
	}
	//	�ͷ��Ͽ�tchat
	public void doTchatDisconnect(){
		_tchatConnection.doDisconnect();
	}
	//	�ͷ��Ͽ�t2d
	public void doT2dDisconnect(){
		_t2dConnection.doDisconnect();
	}
	//�����ļ���Ϣ
	public MessagesResult sendFileMessage() {
		//���ϴ��ļ�
		Action_WEB_FileTest a1 = new Action_WEB_FileTest(_monitorTask,true);
		FileTestResult fileTestResult=a1.doUploadAction();
		//�ж��Ƿ��ϴ��ɹ����ϴ��ɹ�������Ϣ
		if (fileTestResult.getUrl()!=null && fileTestResult.KF_FILE_UPLOAD == true){
			_tchatConnection.sendMessage(fileTestResult.getUrl());
			_tchatConnection._web_messages.setFileTestResult(fileTestResult);
		}else {
			_tchatConnection.sendMessage("UploadFail");
		}
		return _tchatConnection._web_messages;
	}
//���ͷ����û����Ƿ������Զ�������
	public KFSettingResult kfReceptionSetting(){
		KFSettingResult kfSettingResult = null;
		Action_KF_ReceptionSetting action_kf_receptionTime = new Action_KF_ReceptionSetting(this);
		//���ͷ����Ƿ����Ӵ�ʱ�书��
		kfSettingResult=action_kf_receptionTime.getReceptionTime();
		if (kfSettingResult.receptionTime == 2){
			//�ڿͻ��˴����Ӵ���
			kfSettingResult = action_kf_receptionTime.addReceptionGroup();

		}
		return kfSettingResult;
	}

//��ԭ�ͷ��˵�����
	public KFSettingResult kfReceptionSettingClean() {
		KFSettingResult kfSettingResult = null;
		Action_KF_ReceptionSetting action_kf_receptionTime = new Action_KF_ReceptionSetting(this);
		kfSettingResult = action_kf_receptionTime.deleteReceptionGroup();
		return kfSettingResult;
	}

}

