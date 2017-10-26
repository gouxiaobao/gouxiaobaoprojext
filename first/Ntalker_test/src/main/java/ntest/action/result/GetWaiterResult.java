package ntest.action.result;

public class GetWaiterResult {

	//客服id
	public String WEB_destKFUid = null;
//	客服状态
	public int WEB_destKFStatus = 0;
//	客服sessionID
	public String WEB_TCHAT_sessionID = null;

	//获取settingID
	public boolean WEB_settingID = false;

	
	@Override
	public String toString() {
		return "GetWaiterResult [WEB_destKFUid=" + WEB_destKFUid
				+ ", WEB_destKFStatus=" + WEB_destKFStatus
				+ ", WEB_TCHAT_sessionID=" + WEB_TCHAT_sessionID + "]";
	}
}
