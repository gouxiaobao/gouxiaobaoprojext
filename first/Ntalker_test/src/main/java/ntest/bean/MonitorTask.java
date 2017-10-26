package ntest.bean;


import ntest.action.result.FlashServerResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorTask {
	protected static Logger log = LoggerFactory.getLogger(MonitorTask.class.getName());

	public String protocolType = "mqtt";
	public String siteid = null;
	public String settingID = null;
	public String waiterName =null;
	public String waiterPassword = null;
	public String testPageUrl = null;

	//��ҵ�����ַ
//	public String url="http://downt.ntalker.com/";
//	public String url="http://release-ts1.ntalker.com:10/";
	public String url="http://nt-v1-downt.ntalker.com/";
	//��������ĵ�ַ
	public FlashServerResult _flashServerResult;
	//�ͷ���¼��᷵��tokenֵ
	public String token = null;
	//�Ӵ�������
	public String kfGroupname = "__XN__TEST_______";

	//�ļ���ַ
	public String pictureFilePath = "./config/kf_8002.jpg";
	public String videoFilePath = "";

	public String getProtocalType() {
		return protocolType;
	}

}