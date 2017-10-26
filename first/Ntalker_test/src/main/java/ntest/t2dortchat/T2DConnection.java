package ntest.t2dortchat;

import ntest.action.result.T2DConnectionResult;
import ntest.bean.MonitorTask;
import ntest.bean.Waiter;
import ntest.mqtt.impl.Client;
import ntest.mqtt.impl.T2DMQTTClientManager;
import ntest.util.Statics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.UUID;

public class T2DConnection {


	private String _vuserId;
	public final String KF_VERSION = "9.9.9999999999";
	
	private String _siteid = null;
//	private String _uid = null;
	private String _userId = null;
	private String _pwd = null;
	private String _pcId = null;
	private Waiter _waiter =null;
	
	private String t2dclientid = UUID.randomUUID().toString();
	
	private String _t2dmqttserver = null;

	private T2DMQTTClientManager _mqttClientManager = null;

	private int DISCONNECTED = 0;
	private int CONNECTING = 1;
	private int CONNECTED = 2;

	private int _status = DISCONNECTED;

	public MonitorTask _monitorTask = null;
	public T2DConnectionResult _result = new T2DConnectionResult();

	protected static Logger log = LoggerFactory.getLogger(T2DConnection.class.getName());

	public T2DConnection(Waiter waiter) {
		_waiter = waiter;
		_vuserId = waiter._vuserId;
		_monitorTask = waiter._monitorTask;
		_siteid = _monitorTask.siteid;
//		_uid = _monitorTask.waiterName;
		_pwd = _monitorTask.waiterPassword;
		_userId = Statics.GetKFUserIdByUid(_siteid, waiter._monitorTask.waiterName);
		_pcId = _userId;
		_t2dmqttserver = _monitorTask._flashServerResult.t2dmqttserver;
	}

	// ���ӷ�����
	public void doConnect() {
		
		mqttConnect();

	}

	private void mqttConnect() {
		
		try {
			log.debug("doConnect " + _t2dmqttserver + " " + _siteid + " " + _userId + " " + _pwd);
			//����Ƿ��Ѿ�����t2dmqtt
			if (_status == CONNECTING){
				log.debug("waiter already T2DConnection!!");
				return;
			}

			_status = CONNECTING;

			_result.KF_T2D_connectStatus = 0;

			if(_t2dmqttserver == null){
				log.debug("mqttserver is null");
				return;
			}
			if(Statics.isNullOrEmpty(_userId) || Statics.isNullOrEmpty(_pwd)){
				log.debug("username or password is null");
				return;
			}
			
			/**
			./bin/emqttd_ctl useradd ntuser xiaoneng9754   t2d tchat
			./bin/emqttd_ctl useradd ntguest xiaoneng123          �ÿͺͿͷ�
			./bin/emqttd_ctl useradd ntmonitor isme_ntmonitor   ����ϵͳtopic  
			 */
			String tchatusername = "ntguest";
			String tchatpassword = "xiaoneng123";
			
			Client mqttClient = new Client();
			mqttClient.setHost(_t2dmqttserver);  //tcp://hmqttrb.ntalker.com:1883/T2D
			mqttClient.setClientid(t2dclientid);
			mqttClient.setUsername(tchatusername);
			mqttClient.setPassword(tchatpassword);
			mqttClient.setRouteTopic("S/ROUTE/T2D");
			mqttClient.setSelfTopic("C/" + t2dclientid);
			mqttClient.setSelfWillTopic("S/WILL/" + t2dclientid);
			mqttClient.setSiteid(_siteid);
			mqttClient.setUid(_userId);
			mqttClient.setUidPassword(_pwd);
			mqttClient.setAppid("t2d");
			
			log.debug("T2DMQTTClientManager create");
			_mqttClientManager = new T2DMQTTClientManager(mqttClient, this);
			
		} catch (Exception e) {
			log.warn("doConnect Exception " + e.toString());
			_result.KF_T2D_connectStatus = 2;
		}
	}

	//�ͷ���Ӧ���룬����Ự
//	public void joinChatSession(){
//		_result.KF_TCHAT_connectStartTime = new java.util.Date().getTime();
//		_result.KF_TCHAT_connectStopTime1 = 0;
//		_result.KF_TCHAT_connectStopTime2 = 0;
//		_result.KF_TCHAT_connectStatus = 0;
//		
//		String pcid = _result.KF_PCID;
//		String myuid = _monitorItem.kfusername;
//		String mytoken = myuid;
//		String mysessionid = "";
//		String destid = "";
//		if(_isToWeiXin){
//			mysessionid = _result.KF_WeiXin_TCHAT_sessionID;
//			destid = _result.KF_destWeiXinUid;
//			log.debug("WEIXIN_DEBUG : "+destid);
//		}
//		else{
//			mysessionid = _result.KF_TCHAT_sessionID;
//			destid = _result.KF_destWEBUid;
//		}
//		
//		if(mysessionid==null || mysessionid.length()<=2){
//			return;
//		}
//	}
	
	public void connectResult(Object[] obj) {
		try {
			log.debug("connectResult " + obj);

			boolean bsuccess = obj[0].toString().toLowerCase().indexOf("true") >= 0;
			String siteid = obj[1].toString();
			String clientID = obj[2].toString();

			log.debug("connectResult " + bsuccess + " clientid:" + clientID);

			if (bsuccess) {

				_result.KF_T2D_connectStatus = 1;
				_result.KF_T2D_clientid = clientID;
			} else {
				_result.KF_T2D_connectStatus = 2;
			}
		} catch (Exception e) {
			log.warn("connectResult Exception:" + e.toString());
		}

	}

	public void doLogin() {
		
		try {
			
			if (_status == CONNECTED)
				return;
			
			log.debug("doLogin " + _userId + " / " + _pwd);
			_result.KF_T2D_loginStatus = 0;

			// MD5.encode(_userId+_password+_machineID)+MD5.encode(clientid);
			log.debug("doLogin_test0 " + _userId);
			log.debug("doLogin_test0 " + _pwd);
			log.debug("doLogin_test0 " + _result.KF_T2D_clientid);


			 String clientid = _result.KF_T2D_clientid;
			if (clientid == null || clientid.length() <= 0) {
				_result.KF_T2D_clientid = "";
			}
			
			String protocal = _monitorTask.getProtocalType();
			if(protocal==null || protocal.equalsIgnoreCase("mqtt"))
			{
				mqttLogin();
			}
			
		} catch (Exception e) {
			log.warn("doLogin Exception :" + e.toString());
			StackTraceElement[] er = e.getStackTrace();
			for (int i = 0; i < er.length; i++) {
				log.warn(er[i].toString());
			}
		}
	}

	private void mqttLogin() {
		_mqttClientManager.remoteUserLogin();
	}

	public void loginResult(Object[] obj) {
		try {
			boolean success = obj[0].toString().toLowerCase().indexOf("true") >= 0;

			log.debug("loginResult success = " + success + " " + _userId);

			_result.KF_T2D_loginStatus = success ? 1 : 2;
			if (success){
				if (obj[1] != null){
					String jsonurl = obj[1].toString();
					getSystemUrl(jsonurl);
				}
			}

		} catch (Exception e) {
			log.warn("connectResult Exception:" + e.toString());
		}
	}
	//�ɹ���¼���ȡ�ͷ��˸���ҳ������ӵ�ַ
	public void getSystemUrl(String msg){
		String message = null;
		JSONObject jsonobj = null;
		JSONObject jsonurl = null;
		JSONArray array = null;
		try {
			jsonobj = new JSONObject(msg);
			if (jsonobj.has("groupname")){
				String groupname = jsonobj.getString("groupname");
//				_monitorTask.kfGroupname =groupname;
			}
			if (jsonobj.has("enterinfo")) {
				String method = jsonobj.getString("enterinfo");
				jsonurl = new JSONObject(method);
				if (jsonurl.has("system_url")){
					 message = jsonurl.getString("system_url");
					JSONObject url = new JSONObject(message);
					_monitorTask._flashServerResult.phoneRecord=url.getString("phone_record");//�ͻ���Ϣ
					_monitorTask._flashServerResult.getFastReply=url.getString("get_fast_reply");//���û���
					_monitorTask._flashServerResult.getAllHistorymsg=url.getString("get_all_historymsg");//������¼
					_monitorTask._flashServerResult.getusertrail=url.getString("get_usertrail");//�û��켣
				}
				if (jsonurl.has("functions")){
					array = jsonurl.getJSONArray("functions");
					for (int i = 0; i < array.length(); i++) {
						JSONObject functions = (JSONObject)array.get(i);
						if (functions.getString("func_id").equals("ntalker_setting")){
							_monitorTask._flashServerResult.setting = functions.getString("func_page_url");
						}else if (functions.getString("func_id").equals("ntalker_crm")){
							_monitorTask._flashServerResult.crm = functions.getString("func_page_url");
						}else if (functions.getString("func_id").equals("ntalker_interaction")){
							_monitorTask._flashServerResult.interaction = functions.getString("func_page_url");
						}else if (functions.getString("func_id").equals("ntalker_statistic")){
							_monitorTask._flashServerResult.statistic = functions.getString("func_page_url");
						}
					}
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
	public void doDisconnect() {
		try {

			log.debug("doDisconnect");

			String protocolType = _monitorTask.getProtocalType();
			log.debug("protocolType= " + protocolType);
			if(protocolType!=null && protocolType.equalsIgnoreCase("mqtt"))
			{
				if(_mqttClientManager != null){
					//AgentUser�����Ͽ�����
					_mqttClientManager.sendMessage(new Object [] {_result.KF_T2D_clientid, _userId});
					log.warn("remoteEndConnection uid=" + _userId);
					_mqttClientManager.destroy();
				}
			}

		} catch (Exception e) {
			log.warn("doDisconnect Exception:" + e.toString());
		}
		_status = DISCONNECTED;
	}
	

	// remoteNotifyUserChat(srcuid:String, userInfoStr:String,
	// chatSessionID:String, msg:String
	public boolean remoteNotifyUserChat(String srcuid, String userInfoStr, String chatSessionID, String msg) {
		try {
			log.debug("remoteNotifyUserChat from uid=" + srcuid + " ,WEB_USERID=" + _vuserId
					+ " ,sessionID=" + chatSessionID + " ,msg=" + msg);


			if (!Statics.isNullOrEmpty(_vuserId) && srcuid.equals(_vuserId) == false) {
				return true;
			}

//			_result.KF_destWEBUid = srcuid;
			_result.KF_T2D_chatRequestStatus = 1;
			_result.KF_TCHAT_sessionID = chatSessionID;
			
//			_waiter.joinChatSession(srcuid, chatSessionID);
			
//			onReceiveMsg(srcuid,msg);

			log.debug("RECEIVE remoteNotifyUserChat ,uid=" + srcuid + " ,WEB_USERID=" + _vuserId
					+ " ,sessionID=" + chatSessionID + " ,msg=" + msg);

		} catch (Exception e) {
			log.warn("connectResult Exception:" + e.toString());
		}

		return true;
	}
	
	private void onReceiveMsg(String fromuid,String msg)
	{
		try{
			
			String shortmsg = paraseMessage(msg);
			
			log.debug("onReceiveMsg im " + ("kf ") + fromuid + " msg=" + shortmsg);
			
			//
//			if(shortmsg.indexOf(_wang.MSGTXT)<0)
//				return;
			
			HashMap<String,Long> sendtimemaps = null;
			
			//sendtimemaps = _waiter._tchatConnection._result.TCHAT_recvMessagesTime;
			sendtimemaps=null;
			
			if(sendtimemaps.containsKey(shortmsg))
				return;
			
			sendtimemaps.put(shortmsg, new java.util.Date().getTime());
			
		}catch(Exception e)
		{
			log.warn("receive remoteSendMessage Exception :" + e.toString());
			StackTraceElement[] er = e.getStackTrace();
			for (int i = 0; i < er.length; i++) {
				log.warn(er[i].toString());
			}
		}		
	}

	private String paraseMessage(String msg)
	{
		int i0 = msg.indexOf("msgid");
		i0 = msg.indexOf(">", i0);
		
		int i1 = msg.lastIndexOf("<");
		
		String result = msg.substring(i0+1, i1);
		
		return result;
	}
	//��ȡtokenֵ
	public void remoteNotifyUserCode(String srcuid, String time)
	{
		if (srcuid != null){
			_monitorTask.token = srcuid;
			_result.KF_token = true;
			log.debug("remoteNotifyUserCode GetToken success!! GetToken:" + srcuid);
		}else {
			_monitorTask.token = null;
			_result.KF_token = false;
			log.debug("remoteNotifyUserCode GetToken failed!!  GetToken:" + srcuid);
		}
	}

	public boolean remoteNotifyUserChanged(String s1, String s2, int t1, int t2) {
		return true;
	}

	public boolean remoteNotifyQueueStatistics(String s1) {
		return true;
	}
	

	/****************************ʹ��MQTT����*******************************/

	public void netConnectFailed() {
		log.debug("connect failed! " + _userId);
		_result.KF_T2D_connectStatus = 2;
	}

	public void netConnectSuccess() {
		log.debug("Net connect success! " + _userId);
		_result.KF_T2D_connectStatus = 1;
	}
	
	public void loginFailed() {
		log.debug("mqtt login failed " + _userId);
//		_result.KF_T2D_loginStatus = 2;
//		_result.KF_T2D_loginStopTime = new java.util.Date().getTime();
	}
	
	public void loginSuccess() {
		log.debug("mqtt login success " + _userId);
//		_result.KF_T2D_loginStatus = 1;
//		_result.KF_T2D_loginStopTime = new java.util.Date().getTime();
	}
	
	public void notifyUserChat(Object[] objs){
		remoteNotifyUserChat(""+objs[0], ""+objs[1], ""+objs[2], ""+objs[3]);
	}
}