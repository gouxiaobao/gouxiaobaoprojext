package ntest.action;


import ntest.action.result.GetWaiterResult;
import ntest.bean.Visitor;
import ntest.util.HttpSender;
import ntest.util.Statics;
import ntest.util.https.HttpsSender;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ï¿½Ã¿ï¿½ï¿½ï¿½ï¿½ï¿½t2dï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½È¡ï¿½Í·ï¿½ï¿½ï¿½id
 */
public class Action_WEB_GetWaiterID {

	public Visitor _visitor;
	public GetWaiterResult _result = new GetWaiterResult();
	protected static Logger log = LoggerFactory.getLogger(Action_WEB_GetWaiterID.class.getName());


	public Action_WEB_GetWaiterID(Visitor visitor) {
		_visitor = visitor;
	}

	public boolean execute()
	{

		try{
			//»ñÈ¡½Ó´ý×éid
			boolean reception=getSettingID();
			if (reception){
				log.debug("getSettingID success! settingID:"+_visitor._monitorTask.settingID);
			}else {
				log.debug("getSettingID failed! settingID is null");
			}
			//»ñÈ¡¿Í·þÐÅÏ¢
			boolean bresult = doGetSettingID();
			if(bresult)
			{
				log.debug("getsettingid success! first ");
				return true;
			}
			else
			{
				log.debug("getsettingid failed! delay 1000s. ");

				Thread.sleep(1000);

				return doGetSettingID();
			}
		}
		catch(Exception e)
		{
			log.warn("doAction " + e);
		}
		return false;
	}
	//	Í¨ï¿½ï¿½httpï¿½ï¿½ï¿½ï¿½t2dï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½È¡ï¿½Í·ï¿½
	private boolean doGetSettingID()
	{
		long tes = new java.util.Date().getTime();

		InputStream in = null;

		try {

			if(_visitor._monitorTask==null)
			{
				return false;
			}

			String kfuserId = Statics.GetKFUserIdByUid(_visitor._monitorTask.siteid, _visitor._monitorTask.waiterName);

//			String geturl = _visitor._monitorTask._flashServerResult.t2dserverstatus+"/t2d/t2dstatus?query=requestchat&issingle=0&type=0&cid="+_visitor.pcId+"&sitid="+_visitor._monitorTask.siteid+"&uids="+_visitor._monitorTask.settingID+"&uid=" + _visitor.myuserId + "&rands=" + new java.util.Date().getTime()+"&settingid="+_visitor._monitorTask.settingID;
			String geturl = _visitor._monitorTask._flashServerResult.t2dserverstatus+"?query=requestchat&issingle=0&type=0&cid="+_visitor.pcId+"&sitid="+_visitor._monitorTask.siteid+"&uids="+_visitor._monitorTask.settingID+"&uid=" + _visitor.myuserId + "&rands=" + new java.util.Date().getTime()+"&settingid="+_visitor._monitorTask.settingID;

			//*************
			log.debug(geturl);

			URL url = new URL(geturl);
			HttpURLConnection httpConn = null;
			HttpsURLConnection httpsConn = null;

			if(url.getProtocol().equals("http")){
				httpConn = (HttpURLConnection) url.openConnection();
				httpConn.setRequestProperty("User-agent","MSIE8.0");
				httpConn.setRequestMethod("GET");
				httpConn.setReadTimeout(10000);
				httpConn.setConnectTimeout(10000);
				httpConn.setDoOutput(true);

				in = httpConn.getInputStream();
				if (httpConn == null ){
					log.error(" GetWaiterID FAILED!!httpConn is null!!! ");
					return false;
				}
			}else{
				/*
				httpsConn = (HttpsURLConnection) url.openConnection();
				httpsConn.setRequestProperty("User-agent","MSIE8.0");
				httpsConn.setRequestMethod("GET");
				httpsConn.setReadTimeout(10000);
				httpsConn.setConnectTimeout(10000);
				httpsConn.setDoOutput(true);
				in = httpsConn.getInputStream();
*/
				HttpsSender httpsSender = new HttpsSender(geturl);
				in = httpsSender.getInfos();

			}


			String content = Stream2String(in, "utf-8");

			boolean bresult = paraseSettingIDFromJson2(content);

			return bresult;

		}
		catch(Exception e){
			log.error(" Exception " + e.toString());
			return false;
		} finally{
			try
			{
				if(in != null)
					in.close();
			}
			catch(Exception ex)
			{
				log.error(ex.toString());
			}
		}
	}
//»ñÈ¡½Ó´ý×éid
	private boolean getSettingID(){
		JSONObject jsonobj = null;
		JSONArray array = null;
		String jsonString = null;

		String userid = Statics.GetKFUserIdByUid(_visitor._monitorTask.siteid,_visitor._monitorTask.waiterName);

		if (_visitor._monitorTask.token == null){
			_result.WEB_settingID = false;
			return false;
		}
		if (_visitor._monitorTask._flashServerResult.setting == null){
			_result.WEB_settingID = false;
			return false;
		}
		String settingurl = _visitor._monitorTask._flashServerResult.setting.replaceAll("personsetting/index","settingelse/getSettingByid");
		String httpurl = settingurl.replaceAll("userid=###USERID###&token=###TOKEN###","")+"userid=" +userid+
				"&token=" +_visitor._monitorTask.token+
				"&classid=0" +
				"&type=0";

		HttpSender httpSender = new HttpSender();
		jsonString=httpSender.getInfos(httpurl);
		if (jsonString == null){
			_result.WEB_settingID = false;
			return false;
		}
		try {
			jsonobj = new JSONObject(jsonString);
			if (jsonobj.has("data")){
				array = jsonobj.getJSONArray("data");
				for (int i = 0; i < array.length(); i++) {
					JSONObject receptionJson = (JSONObject)array.get(i);
					String nameStr = receptionJson.getString("name");
//					if (nameStr.indexOf("XN_TEST") != -1 ){
					if (nameStr.equals(_visitor._monitorTask.kfGroupname)){
						_visitor._monitorTask.settingID = receptionJson.getString("id");
						if (_visitor._monitorTask.settingID == null){
							_result.WEB_settingID = false;
						}
					}

//					if (receptionJson.getString("name").equals("Ð¡ÄÜ¼¼ÊõÖ§³Ö£¨ÎðÉ¾£©")|receptionJson.getString("name").equals("Ð¡ÄÜ¼¼ÊõÖ§³Ö(ÎðÉ¾)")){
					/*Ôö¼Ó´ÓloginResultÖÐ»ñÈ¡½Ó´ý×éÃû³Æ£¬ÔÚÕâÀï½øÐÐÅÐ¶ÏÐ£Ñé£¬µ«ÊÇÓÉÓÚ±àÂëÎÊÌâ¸ÃÎÊÌâÒÔºó½â¾öÏÈ×¢ÊÍµô
					if (_visitor._monitorTask.kfGroupname != null){
					String nameStr = receptionJson.getString("name");
						if (nameStr.equals(_visitor._monitorTask.kfGroupname)){
							_visitor._monitorTask.settingID = receptionJson.getString("id");
							if (_visitor._monitorTask.settingID == null){
								_result.WEB_settingID = false;
							}
						}
					}
*/
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		_result.WEB_settingID = true;
		return true;
	}
	private boolean paraseSettingIDFromJson2(String jsonString)
	{
		log.debug("return " + jsonString);

		if(jsonString == null || jsonString.length()<=0)
		{
			return false;
		}

		jsonString = "[" + jsonString + "]";

		try {
			JSONArray ja = new JSONArray(jsonString);

			String uid = "";
			String sessionid = "";
			int status = 0;

			for (int i = 0; i < ja.length(); i++) {
				JSONObject jobject = ja.getJSONObject(i);
				if(jobject != null){
					if(jobject.has("error")){
						log.warn("json result:"+jobject.getString("error"));
						return false;
					}

					if(jobject.has("userid"))
						uid = jobject.getString("userid");
					if(jobject.has("sessionid"))
						sessionid = jobject.getString("sessionid");
					if(jobject.has("status"))
						status = jobject.getInt("status");
				}
			}
			//ï¿½ï¿½È¡ï¿½Í·ï¿½ï¿½ï¿½useridï¿½ï¿½status×´Ì¬ï¿½ï¿½Ï¢ï¿½ï¿½sessionidï¿½ï¿½Ï¢
			_result.WEB_destKFUid = uid;
			_result.WEB_destKFStatus = status;
			_result.WEB_TCHAT_sessionID = sessionid;

			//×´Ì¬1 ï¿½ï¿½Â¼×´Ì¬
			if(status!=1){
				log.warn("kf status:"+status);
				return false;
			}
			//ï¿½ï¿½ï¿½useridï¿½ï¿½Ê½
			String kfuserId = Statics.GetKFUserIdByUid(_visitor._monitorTask.siteid, _visitor._monitorTask.waiterName);

			if(uid==null || uid.equals(kfuserId)==false){
				log.warn("kf userid is null or format errorï¿½ï¿½ï¿½ï¿½");
				return false;
			}

			return true;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			log.debug("Exception "+e.getMessage());
		}
		return false;
	}

	private String Stream2String(InputStream in,String encoding){

		if(in == null){
			log.debug("input stream is null");
			return null;
		}
		log.debug("Stream2String "+encoding);

		StringBuffer   out   = new StringBuffer();
		try {

			char[]   b   =   new   char[1024];
			InputStreamReader inread = new InputStreamReader(in,encoding);

			for(int n; (n=inread.read(b))!= -1;)
			{
				String line = new  String(b,0,n);
				out.append(line);
			}

		} catch (Exception e) {
			log.debug("Exception "+e.getMessage());
		}

		return out.toString();
	}

}
