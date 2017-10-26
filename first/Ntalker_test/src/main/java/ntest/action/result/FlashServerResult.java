package ntest.action.result;


public class FlashServerResult {
	
	public boolean issuccess = false;
	
	public String tchatmqttserver;
	public String t2dmqttserver;
	public String t2dserver;
	public String t2dserverstatus;
	public String tchatserver;
	public String trailserver;
	public String manageserver;
	public String fileserver;
	
	public long getServerTimespan = -2;

	//客户信息地址phone_record
	public String phoneRecord ;
	//常用术语地址get_fast_reply
	public String getFastReply ;
	//互动记录地址get_all_historymsg
	public String getAllHistorymsg;
	//轨迹地址get_usertrail
	public String getusertrail;
	//商品信息地址

	//互动记录
	public String interaction;
	//我的报表
	public String statistic;
	//用户资源
	public String crm;
	//设置
	public String setting;

	@Override
	public String toString() {
		return "GetFlashServerResult [issuccess=" + issuccess + ", t2dserver="
				+ t2dserver + ", t2dserverstatus=" + t2dserverstatus
				+ ", tchatserver=" + tchatserver + ", trailserver="
				+ trailserver + ", manageserver=" + manageserver
				+ ", getServerTimespan=" + getServerTimespan + "]";
	}

}
