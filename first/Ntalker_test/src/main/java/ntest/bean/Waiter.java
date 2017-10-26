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
	 * 限制客服只能接待当前访客
	 * @param vuserId
	 */
	public void setVUserId(String vuserId) {
		_vuserId = vuserId;
	}

	//连接t2d服务器
	public T2DConnectionResult connectT2D() {
		_t2dConnection = new T2DConnection(this);
		Action_KF_ConnectT2DServer action = new Action_KF_ConnectT2DServer(this);
		action.execute();
		return _t2dConnection._result;
	}
	//登录t2d服务器
	public T2DConnectionResult loginT2D() {
		Action_KF_LoginT2DServer action = new Action_KF_LoginT2DServer(_t2dConnection);
		action.execute();
		return _t2dConnection._result;
	}

	//	发送信息
	public MessagesResult sendMessage(String msg) {
		_tchatConnection.sendMessage(msg);
		return _tchatConnection._web_messages;
	}

	//获取回复信息
	public MessagesResult recvMessage(){
		return _tchatConnection._web_messages;
	}

	public TChatConnectionResult joinChatSession(String targetUid) {
		_tchatConnection = new TChatConnection(_monitorTask, true, _myuserId, _myuserId, targetUid, _t2dConnection._result.KF_TCHAT_sessionID,kf_messages);
		Action_KF_JoinChatSession action = new Action_KF_JoinChatSession(this);
		action.doAction();
		return _tchatConnection._result;
	}
	//	客服断开tchat
	public void doTchatDisconnect(){
		_tchatConnection.doDisconnect();
	}
	//	客服断开t2d
	public void doT2dDisconnect(){
		_t2dConnection.doDisconnect();
	}
	//发送文件信息
	public MessagesResult sendFileMessage() {
		//先上传文件
		Action_WEB_FileTest a1 = new Action_WEB_FileTest(_monitorTask,true);
		FileTestResult fileTestResult=a1.doUploadAction();
		//判断是否上传成功，上传成功后发送消息
		if (fileTestResult.getUrl()!=null && fileTestResult.KF_FILE_UPLOAD == true){
			_tchatConnection.sendMessage(fileTestResult.getUrl());
			_tchatConnection._web_messages.setFileTestResult(fileTestResult);
		}else {
			_tchatConnection.sendMessage("UploadFail");
		}
		return _tchatConnection._web_messages;
	}
//检查客服设置环境是否满足自动化测试
	public KFSettingResult kfReceptionSetting(){
		KFSettingResult kfSettingResult = null;
		Action_KF_ReceptionSetting action_kf_receptionTime = new Action_KF_ReceptionSetting(this);
		//检查客服端是否开启接待时间功能
		kfSettingResult=action_kf_receptionTime.getReceptionTime();
		if (kfSettingResult.receptionTime == 2){
			//在客户端创建接待组
			kfSettingResult = action_kf_receptionTime.addReceptionGroup();

		}
		return kfSettingResult;
	}

//还原客服端的设置
	public KFSettingResult kfReceptionSettingClean() {
		KFSettingResult kfSettingResult = null;
		Action_KF_ReceptionSetting action_kf_receptionTime = new Action_KF_ReceptionSetting(this);
		kfSettingResult = action_kf_receptionTime.deleteReceptionGroup();
		return kfSettingResult;
	}

}

