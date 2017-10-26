package ntest.action.result;


public class TrailMonitorResult {
	

	public String trailServer_Status;//轨迹监控服务返回结果状态，0：返回成功，-1：返回失败
	public String redissize;
	public String trailcenter_read;
	public String trailcenter_write;
	public String mysql_ntrailcenter_read;
	public String mysql_ntrailcenter_write;
	public String mongo_read;
	public String mongo_write;
	public String servlet_read;
	public String servlet_write;
	public String redis_read;
	public String redis_write;
	public String address;
	
	

}
