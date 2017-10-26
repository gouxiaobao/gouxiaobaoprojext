package ntest.action.result;

public class T2DConnectionResult {

//	客服连接t2d服务器
	public int KF_T2D_connectStatus = -1;  //T2D连接状态 -1 未开始  0 connecting  1 connected 2 disconnected
//	保存访客id信息
	public String KF_T2D_clientid;

//	客服登录t2d服务器状态
	public int  KF_T2D_loginStatus = -1;  // -1 未开始   0  logining  1 success  2 failed

//	客服向tcaht服务器发送消息状态
	public int KF_T2D_chatRequestStatus = 0;  // -1 未开始   0  doing  1 success  2 failed

	//保存tchat会话连接
	public String KF_TCHAT_sessionID;

	//客服登录后获取token值
	public boolean KF_token = false;


	@Override
	public String toString() {
		return "T2DConnectionResult [KF_T2D_connectStatus="
				+ KF_T2D_connectStatus + ", KF_T2D_clientid=" + KF_T2D_clientid
				+ ", KF_T2D_loginStatus=" + KF_T2D_loginStatus
				+ ", KF_T2D_chatRequestStatus=" + KF_T2D_chatRequestStatus
				+   ", KF_TCHAT_sessionID="	+ KF_TCHAT_sessionID + "]";
	}

}
