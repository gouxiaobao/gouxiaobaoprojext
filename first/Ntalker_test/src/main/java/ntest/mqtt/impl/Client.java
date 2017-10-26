package ntest.mqtt.impl;

public class Client {
	private String host = "";
	private String clientid = "";
	private String username = "";
	private String password = "";
	private String selfTopic = "C/";
	private String selfWillTopic = "S/WILL/";
	private String willMessage = "{}";
	private String uid;
	private String uidPassword;
	private String siteid;
	private long timeoutPublish = 10000;
	private String routeTopic = "S/ROUTE/T2D";
	private String appid = "";
	private Object serviceProvider;
	
	
	//tchat²ÎÊý
	private String usertoken;
	private String sessionId;
	private String targetId;
	private String myMachineId;
	private String devicetype;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSelfTopic() {
		return selfTopic;
	}
	public void setSelfTopic(String selfTopic) {
		this.selfTopic = selfTopic;
	}
	public String getSelfWillTopic() {
		return selfWillTopic;
	}
	public void setSelfWillTopic(String selfWillTopic) {
		this.selfWillTopic = selfWillTopic;
	}
	public String getWillMessage() {
		return willMessage;
	}
	public void setWillMessage(String willMessage) {
		this.willMessage = willMessage;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSiteid() {
		return siteid;
	}
	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}
	public long getTimeoutPublish() {
		return timeoutPublish;
	}
	public void setTimeoutPublish(long timeoutPublish) {
		this.timeoutPublish = timeoutPublish;
	}
	public String getRouteTopic() {
		return routeTopic;
	}
	public void setRouteTopic(String routeTopic) {
		this.routeTopic = routeTopic;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public Object getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(Object serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public String getUidPassword() {
		return uidPassword;
	}
	public void setUidPassword(String uidPassword) {
		this.uidPassword = uidPassword;
	}
	public String getUsertoken() {
		return usertoken;
	}
	public void setUsertoken(String usertoken) {
		this.usertoken = usertoken;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getMyMachineId() {
		return myMachineId;
	}
	public void setMyMachineId(String myMachineId) {
		this.myMachineId = myMachineId;
	}
	public String getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
}
