package ntest.action;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntest.action.result.TrailResult;
import ntest.bean.Visitor;
import ntest.util.StringUtil;

/**
 * 发送轨迹数据
 */
public class Action_WEB_ReportTrail {

	public Visitor _visitor;
	public TrailResult _trailResult = new TrailResult();
	protected static Logger log = LoggerFactory.getLogger(Action_WEB_ReportTrail.class.getName());

	public Action_WEB_ReportTrail(Visitor visitor) {
		_visitor = visitor;
	}
	
	public boolean execute() {
		try {
			log.debug("start notifytrail to server ");
			boolean bresult = reportTrail();
			if (bresult) {
				log.debug("start notifytrail success! ");
				return true;
			} else {
				log.debug("start notifytrail failed! ");

				Thread.sleep(1000);

				return reportTrail();
			}
		} catch (Exception e) {
			log.warn("doAction " + e);
		}

		return false;
	}
	
	private boolean reportTrail() {
		
		InputStream in = null;
		
		try {	
			
			if(_visitor==null)
			{
				_trailResult.WEB_gettrailTimespan = -1;
				return false;
			}
			if(StringUtil.isEmpty(_visitor._monitorTask._flashServerResult.trailserver))
			{
				log.warn("trail server is emplay,return. "+_visitor._monitorTask.siteid);
				_trailResult.WEB_gettrailTimespan = -1;
				return false;
			}

			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			 String psiteid  = _visitor._monitorTask.siteid;
			 if(psiteid != null && !psiteid.startsWith("kf_"))
			 {
				 psiteid =  paraseSiteIdHead(_visitor._monitorTask.siteid)+"1000";
			 }

			String geturl = _visitor._monitorTask._flashServerResult.trailserver + "/trail.php?action=save&uid=";
			/*				+ _visitor.myuserId
							+ "&siteid=" +psiteid //平台id
							+ "&url=" +URLEncoder.encode(_visitor._monitorTask.trailCluster.url+"?testtime="+sdf.format(new Date()),"utf-8")
							+ "&cookie=1"
							+ "&flash=1"
							+ "&cid="+_visitor.pcId
							+ "&sid="+System.currentTimeMillis()
							+ "&log=1"
							+ "&lan=zh-CN"
							+ "&scr=1280*1024";
			*/
			log.debug("doGetTryNotifyTrail :" + geturl);
			
			long stime = System.currentTimeMillis();
			URL url = new URL(geturl);
			
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestProperty("User-agent","MSIE8.0");
			httpConn.setRequestMethod("GET");
			httpConn.setReadTimeout(10000);
			httpConn.setConnectTimeout(10000);
			httpConn.setDoOutput(true);	
			
			in = httpConn.getInputStream();
			String content = Stream2String(in, "utf-8");
			long etime = System.currentTimeMillis();
			
			boolean produckpage = parasePageLevel(content);
			log.debug("http_return_content:"+content +" / "+produckpage);

			_trailResult.WEB_gettrailTimespan = (int)(etime - stime);
			
			//解析失败
			if(!produckpage)
			{
				_trailResult.WEB_gettrailTimespan = 99999999;
			}
			log.debug("doGetTryNotifyTrail timespan : " + _trailResult.WEB_gettrailTimespan + " ms");
			return produckpage;
			
		} catch(Exception e){
        	log.error(" Exception " + e.toString());
        	_trailResult.WEB_gettrailTimespan = 88888888;
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

	private boolean parasePageLevel(String content) {
		if (content == null)
			return false;

		if (content.indexOf("\"pagelevel\":\"3\"") != -1) {
			return true;
		}
		return false;
	}

	private String Stream2String(InputStream in, String encoding) {

		if (in == null) {
			log.debug("input stream is null");
			return null;
		}
		log.debug("Stream2String " + encoding);
		StringBuffer out = new StringBuffer();
		try {

			char[] b = new char[1024];
			InputStreamReader inread = new InputStreamReader(in, encoding);

			for (int n; (n = inread.read(b)) != -1;) {
				String line = new String(b, 0, n);
				out.append(line);
			}

		} catch (Exception e) {
			log.debug("Exception " + e.getMessage());
		}

		return out.toString();
	}

	public String paraseSiteIdHead(String siteid) {
		try {
			if (siteid == null || siteid.length() <= 0) {
				return null;
			}
			int s1 = siteid.indexOf("_");
			if (s1 < 0) {
				return null;
			}
			return siteid.substring(0, s1 + 1);

		} catch (Exception e) {
			log.warn("Exception " + e.toString() + " / " + siteid);
		}
		return null;
	}
}
