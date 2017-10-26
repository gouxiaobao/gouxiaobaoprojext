package ntest.t2dortchat;

import java.util.HashMap;
import java.util.UUID;

import ntest.action.result.MessagesResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntest.action.result.TChatConnectionResult;
import ntest.bean.MonitorTask;
import ntest.mqtt.impl.Client;
import ntest.mqtt.impl.TChatMQTTClientManager;

public class TChatConnection {
	
	//private RTMPClient _rtmp = null;
	
	private TChatMQTTClientManager tChatMQTTClientManager;
//	private IConnection _conn = null;
	private int DISCONNECTED = 0;
	private int CONNECTING = 1;
	private int CONNECTED = 2;
	
	private int _status = DISCONNECTED;
	
	public String _sessionId;
	public String _myuserId;
	public String _targetUid;
	public String _pcId;
	public MonitorTask _monitorTask;
	public TChatConnectionResult _result = new TChatConnectionResult();
	public MessagesResult _web_messages = null;
	private boolean _iskf = false;
	protected static Logger log = LoggerFactory.getLogger(TChatConnection.class.getName());

	/**
	 *
	 * @param monitorTask
	 * @param iskf true时客服 false为访客
	 * @param myuserId
	 * @param pcId
	 * @param targetUid
	 * @param sessionId
	 */
	public TChatConnection(MonitorTask monitorTask, boolean iskf, String myuserId, String pcId, String targetUid, String sessionId,MessagesResult web_messages)
	{
		_monitorTask = monitorTask;
		_iskf = iskf;
		_myuserId = myuserId;
		_pcId = pcId;
		_targetUid = targetUid;
		_sessionId = sessionId;
		_web_messages = web_messages;
	}

	//连接服务器
	public void doConnect()
	{
		try{
			if (_status == CONNECTING)
				return;

			_status = CONNECTING;
			log.debug("doConnect start");

			String protocolType = _monitorTask.getProtocalType();
			log.debug("protocolType= " + protocolType);
			if(protocolType!=null && protocolType.equalsIgnoreCase("mqtt"))
			{
				mqttConnect();
			}
		}
		catch(Exception e)
		{
			log.warn("doConnect Exception " + e.toString());
			StackTraceElement[] er = e.getStackTrace();
			for (int i = 0; i < er.length; i++) {
				log.warn(er[i].toString());
			}
			_result.TCHAT_connectStatus = 2;
		}
	}
	
	//mqtt连接的ID，必须唯一
	String tchatclientid = UUID.randomUUID().toString();
	private void mqttConnect() {
		
		try {
			
			String tchatserver = _monitorTask._flashServerResult.tchatmqttserver;
			if(tchatserver==null)
			{
				log.debug("mqttserver is null");
				_result.TCHAT_connectStatus = 2;
				return;
			}

			_result.TCHAT_connectStatus = 0;
			String _pcId = "";
			String mytoken = "";
			
			/**
			./bin/emqttd_ctl useradd ntuser xiaoneng9754
			./bin/emqttd_ctl useradd ntguest xiaoneng123
			./bin/emqttd_ctl useradd ntmonitor isme_ntmonitor
			 */
			String tchatusername = "ntguest";
			String tchatpassword = "xiaoneng123";
			
			if(_iskf)
			{
				mytoken = _myuserId;
				if(_sessionId==null || _sessionId.length()<=2)
					return;
			}
			
			if(_targetUid==null || _targetUid.length()<=2)
				return;
			
			if(_iskf){
				//客服登录tchat
				Client mqttClient = new Client();
				if(tchatclientid == null) {
					tchatclientid = UUID.randomUUID().toString();
				}
				
				tchatclientid = tchatclientid + "_kf";
				
				mqttClient.setHost(tchatserver);
				mqttClient.setClientid(tchatclientid);
				mqttClient.setUsername(tchatusername);
				mqttClient.setPassword(tchatpassword);
				mqttClient.setRouteTopic("S/ROUTE/TCHAT");
				mqttClient.setSelfTopic("C/" + tchatclientid);
				mqttClient.setSelfWillTopic("S/WILL/" + tchatclientid);
				mqttClient.setSiteid(_monitorTask.siteid);
				mqttClient.setUid(_myuserId);
				mqttClient.setAppid("tchat");
				
				// 添加tchat参数
				mqttClient.setUsertoken(mytoken);
				mqttClient.setMyMachineId(_pcId);
				mqttClient.setTargetId(_targetUid);
				mqttClient.setSessionId(_sessionId);
				mqttClient.setDevicetype("0");
				
				tChatMQTTClientManager = new TChatMQTTClientManager(mqttClient, this);
			}else{
				//访客登录tchat
				Client mqttClient = new Client();
				if(tchatclientid == null) {
					tchatclientid = UUID.randomUUID().toString();
				}
				
				mqttClient.setHost(tchatserver);
				mqttClient.setClientid(tchatclientid);
				mqttClient.setUsername(tchatusername);
				mqttClient.setPassword(tchatpassword);
				mqttClient.setRouteTopic("S/ROUTE/TCHAT");
				mqttClient.setSelfTopic("C/" + tchatclientid);
				mqttClient.setSelfWillTopic("S/WILL/" + tchatclientid);
				mqttClient.setSiteid(_monitorTask.siteid);
				mqttClient.setUid(_myuserId);
				mqttClient.setAppid("tchat");
				
				// 添加tchat参数
				mqttClient.setUsertoken(mytoken);
				mqttClient.setMyMachineId(_pcId);
				mqttClient.setTargetId(_targetUid);
				mqttClient.setSessionId(_sessionId);
				mqttClient.setDevicetype("0");
				
				tChatMQTTClientManager = new TChatMQTTClientManager(mqttClient, this);
			}
			
			
		} catch (Exception e) {
			log.warn("doConnect Exception " + e.toString());
			_result.TCHAT_connectStatus = 2;
		}
	}

	public void LoginResult(Object [] obj)
	{
		try{
			log.debug("loginResult " + obj);
			
			boolean success = obj[0].toString().toLowerCase().indexOf("true")>=0;
			String cid = obj[1].toString();
			String sessinid = obj[3].toString();
			
			log.debug("loginResult success = " + success + " " + _myuserId);
			
			if(_iskf)
			{
				_result.TCHAT_connectStatus = success ? 1 : 2;
				_result.TCHAT_clientid = cid;
				_result.TCHAT_sessionID = sessinid;
			}
			else
			{
				_result.TCHAT_connectStatus = success ? 1 : 2;
				_result.TCHAT_clientid = cid;
				_result.TCHAT_sessionID = sessinid;
			}
		}
		catch(Exception e)
		{
			log.warn("connectResult Exception:" + e.toString());
		}
	}
//	制定消息格式
	private String makeMessage(String msg,String msgid)
	{
		//<msg type="1" fontsize="17" color="FF0000" italic="false" bold="false" underline="false" msgid="1346917444039">ASDFASDF</msg>
		String result = "<msg type=\"1\" fontsize=\"17\" color=\"FF0000\" italic=\"false\" bold=\"false\" underline=\"false\" msgid=\"" 
					+ msgid + "\">" + msg + "</msg>";
		
		return result;
	}
	
	private String paraseMessage(String msg)
	{
		int i0 = msg.indexOf("msgid");
		i0 = msg.indexOf(">", i0);
		
		int i1 = msg.lastIndexOf("<");
		
		String result = msg.substring(i0+1, i1);
		
		return result;
	}
//	发送消息
	public void sendMessage(String msg)
	{
		try{
			String msgid = "" + new java.util.Date().getTime();
			
			log.debug("sendMessage " + msg);
			String shortmsg = msg;
			
			msg = makeMessage(msg,msgid);
			
			if(_status == CONNECTED)
				return;
			
			String mycid = "";
			String sid = _result.TCHAT_sessionID;	
			HashMap<String,Long> sendtimemaps = null;
			sendtimemaps = _result.TCHAT_sendMessagesTime;
			
			if(_iskf)
			{
				_web_messages.setKF_sendMessages(shortmsg);
			}
			else
			{
				mycid = _result.TCHAT_clientid;
				_web_messages.setWEB_sendMessages(shortmsg);
			}

			sendtimemaps.put(shortmsg,new java.util.Date().getTime());

			String protocolType = _monitorTask.getProtocalType();
			log.debug("protocolType= " + protocolType);
			if(protocolType!=null && protocolType.equalsIgnoreCase("mqtt"))
			{
				if(tChatMQTTClientManager != null){
					tChatMQTTClientManager.sendMessage(new Object [] {_myuserId,mycid,sid,msg,System.currentTimeMillis()});
				}
			}

		}catch(Exception e)
		{
			log.warn("sendMessage Exception :" + e.toString());
		}	
	}
	/*
	public boolean remoteHistroyMessage(String histroymessage) {
		try {
			log.debug("remoteHistroyMessage ");

			if (_iskf) {
				String msg1 = COMMON.msg + 1;
				long now = new java.util.Date().getTime();
				_result.TCHAT_recvMessagesTime.put(msg1, now);
				_result.TCHAT_recvHistoryTime = now;
			}

		} catch (Exception e) {
			log.warn("sendMessage Exception :" + e.toString());
		}

		return true;
	}

	public void onReceiveMsg(String msg)
	{
		try{
			
			String shortmsg = paraseMessage(msg);
			
			log.debug("onReceiveMsg im " + (_iskf ? "kf " : "web ") + fromuid + " msg=" + shortmsg);
			
			//
//			if(shortmsg.indexOf(_wang.MSGTXT)<0)
//				return;
			
			HashMap<String,Long> sendtimemaps = null;
			
			if(_iskf)
			{
				sendtimemaps = _result.TCHAT_recvMessagesTime;
				
			}
			else
			{
				sendtimemaps = _result.TCHAT_recvMessagesTime;
			}
			
			if(sendtimemaps.containsKey(shortmsg))
				return;
			
			sendtimemaps.put(shortmsg, new java.util.Date().getTime());
			
		}catch(Exception e)
		{
			log.warn("receive remoteSendMessage Exception :" + e.toString());
		}		
	}*/
	
	//remoteSendMessage(timestamp:Number, srcuid:String, srcInfoStr:String, msg:String, flashUid:Number)
	public boolean remoteSendMessage(long timestamp,String srcuid, String srcInfoStr,String msg,int flashUid)
	{
		onReceiveMessage(msg);
		return true;
	}
//	断开tcaht连接
	public void doDisconnect()
	{
		try{
			log.debug("doDisconnect");
			
			String protocolType = _monitorTask.getProtocalType();
			log.debug("protocolType= " + protocolType);
			if(protocolType!=null && protocolType.equalsIgnoreCase("mqtt"))
			{
				if(tChatMQTTClientManager != null){
					//AgentUser主动断开连接
					tChatMQTTClientManager.send("remoteEndConnection", new Object [] {_result.TCHAT_clientid, _myuserId});
					log.warn("remoteEndConnection uid=" + _myuserId);
					tChatMQTTClientManager.destroy();
				}
			}

		}
		catch(Exception e)
		{
			log.warn("doDisconnect Exception:" + e.toString());
		}
		_status = DISCONNECTED;
	}
	public boolean remoteSearchWaiter(String s1,String s2)
	{
		return true;
	}
	public boolean remoteSearchWaiter(String s1,String s2,String s3)
	{
		return true;
	}
	
	/****************************使用MQTT连接,下行消息处理*******************************/
	/**
	* 以下文件为mqtt的回调信息
	* */
	
	public void netConnectFailed() {
		log.debug("connect failed! " + _myuserId);
		_result.TCHAT_connectStatus = 2;
	}

	public void netConnectSuccess() {
		log.debug("Net connect success! " + _myuserId);
//		_result.TCHAT_connectStopTime1 = new java.util.Date().getTime();
	}

//	发送mqtt后，mqtt广播消息回调消息
	public void onReceiveMessage(String msg) {
		try {
			log.debug("onReceiveMessage:<{}>",msg);
			int i0 = msg.lastIndexOf("<");
			int i1 = msg.lastIndexOf(">", i0);
			String shortmsg = msg.substring(i1+1, i0);
//			if (shortmsg.indexOf(_wang.MSGTXT) < 0)
//				return;
			HashMap<String, Long> sendtimemaps = null;
			if (_iskf) {
				sendtimemaps = _result.TCHAT_recvMessagesTime;
				_web_messages.setKF_recvMessages(shortmsg);
//				_web_messages.setWEB_recvMessages(shortmsg);
			} else {
				sendtimemaps = _result.TCHAT_recvMessagesTime;
				_web_messages.setWEB_recvMessages(shortmsg);
//				_web_messages.setKF_recvMessages(shortmsg);
			}
			if (sendtimemaps.containsKey(shortmsg))
				return;
			
			sendtimemaps.put(shortmsg, new java.util.Date().getTime());
		} catch (Exception e){
			log.warn("onReceiveMessage Exception :" + e.toString());
		}
	}	
}