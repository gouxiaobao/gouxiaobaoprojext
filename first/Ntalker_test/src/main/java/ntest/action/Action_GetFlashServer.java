package ntest.action;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ntest.action.result.FlashServerResult;
import ntest.bean.MonitorTask;
import ntest.util.Statics;

/**
 * ������Ҫ�����ǻ�ȡ��ȡ�ͷ�������Ҫ�ĸ�������ĵ�ַ��Ϣ
 */
public class Action_GetFlashServer {

	private MonitorTask _monitorTask;
	public FlashServerResult _getFlashServerResult = new FlashServerResult();

	protected static Logger log = LoggerFactory.getLogger(Action_GetFlashServer.class.getName());

	public Action_GetFlashServer(MonitorTask monitorTask)
	{
		_monitorTask = monitorTask;
	}

	public FlashServerResult doAction()
	{
		try{
			doGetFlashServer();
			if(_getFlashServerResult.issuccess)
			{
				log.debug("start getflash server success! ");
				return _getFlashServerResult;
			}
			else
			{
				log.debug("start getflash server failed! ");
				Thread.sleep(1000);

				doGetFlashServer();
			}
		}
		catch(Exception e)
		{
			log.warn("doAction " + e);
		}

		return _getFlashServerResult;
	}

	private boolean doGetFlashServer()
	{
		long tes = new java.util.Date().getTime();

		InputStream in = null;

		try {
			if(_monitorTask==null)
			{
				_getFlashServerResult.getServerTimespan = -1;
				return false;
			}

			String geturl = _monitorTask.url + "/t2d//func/getflashserver.php?siteid="
					+ _monitorTask.siteid + "&from=air&time=" + new java.util.Date().getTime()+"&userid="+_monitorTask.waiterName;
			URL url = new URL(geturl);
			HttpURLConnection httpConn = null;
			if(url.getProtocol().equals("http")){
				httpConn = (HttpURLConnection) url.openConnection();

			}else{
				httpConn = (HttpsURLConnection) url.openConnection();
			}
			if (httpConn == null){
				log.error(" GetFlashServer FAILED!!httpConn is null!!! ");
				return false;
			}

			log.debug("doGetFlashServer :" + geturl);


			httpConn.setRequestProperty("User-agent","MSIE8.0");
			httpConn.setRequestMethod("GET");
			httpConn.setReadTimeout(10000);
			httpConn.setConnectTimeout(10000);
			httpConn.setDoOutput(true);

			in = httpConn.getInputStream();
			DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
			Document doc = domfac.newDocumentBuilder().parse(in);

			Element root=doc.getDocumentElement();
			NodeList sessionNodes = root.getElementsByTagName("t2dserver");
			if(sessionNodes != null && sessionNodes.getLength() == 1) {
				Element e = (Element)sessionNodes.item(0);
				String nodeValue = e.getFirstChild().getNodeValue();

				if(nodeValue==null || nodeValue.length()<=5 || nodeValue.toLowerCase().indexOf("rtmp")<0)
				{
					_getFlashServerResult.getServerTimespan = -1;
					return false;
				}

//				_getFlashServerResult.t2dserver = Statics.paraseServerUrlForRtmp(nodeValue);
//				log.debug("t2dserver " + _getFlashServerResult.t2dserver);
			}

			sessionNodes = root.getElementsByTagName("t2dstatus");
			if(sessionNodes != null && sessionNodes.getLength() == 1)
			{
				Element e = (Element)sessionNodes.item(0);
				String nodeValue = e.getFirstChild().getNodeValue();

				if(nodeValue==null || nodeValue.length()<=5)
				{
					_getFlashServerResult.getServerTimespan = -1;
					return false;
				}

				log.debug("t2dstatus " + nodeValue);
				_getFlashServerResult.t2dserverstatus  = nodeValue;
			}

			sessionNodes = root.getElementsByTagName("tchatserver");
			if(sessionNodes != null && sessionNodes.getLength() == 1) {
				Element e = (Element)sessionNodes.item(0);
				String nodeValue = e.getFirstChild().getNodeValue();

				if(nodeValue==null || nodeValue.length()<=5 || nodeValue.toLowerCase().indexOf("rtmp")<0)
				{
					_getFlashServerResult.getServerTimespan = -1;
					return false;
				}

//				_getFlashServerResult.tchatserver  =  Statics.paraseServerUrlForRtmp(nodeValue);;
//				log.debug("tchatserver " + _getFlashServerResult.tchatserver);
			}

			sessionNodes = root.getElementsByTagName("trailserver");
			if(sessionNodes != null && sessionNodes.getLength() == 1) {
				Element e = (Element)sessionNodes.item(0);
				String nodeValue = e.getFirstChild().getNodeValue();

				_getFlashServerResult.trailserver  =  nodeValue;
				log.debug("trailserver " + _getFlashServerResult.trailserver);
			}

			sessionNodes = root.getElementsByTagName("fileserver");
			if(sessionNodes != null && sessionNodes.getLength() == 1) {
				Element e = (Element)sessionNodes.item(0);
				String nodeValue = e.getFirstChild().getNodeValue();

				_getFlashServerResult.fileserver  =  nodeValue;
				log.debug("fileserver " + _getFlashServerResult.fileserver);
			}

			sessionNodes = root.getElementsByTagName("manageserver");
			if(sessionNodes != null && sessionNodes.getLength() == 1) {
				Element e = (Element)sessionNodes.item(0);
				String nodeValue = e.getFirstChild().getNodeValue();

				_getFlashServerResult.manageserver  =  nodeValue;
				log.debug("manageserver " + _getFlashServerResult.manageserver);
			}

			sessionNodes = root.getElementsByTagName("tchatmqttserver");
			if(sessionNodes != null && sessionNodes.getLength() == 1)
			{
				Element e = (Element)sessionNodes.item(0);
				String nodeValue = e.getFirstChild().getNodeValue();

				if(nodeValue==null || nodeValue.length()<=5)
				{
					log.error("tchatmqttserver��    " + nodeValue);
					return false;
				}

				nodeValue = nodeValue.replace("<![CDATA[", "").replace("]]>", "").trim();

				String[] arr = null;
				if(nodeValue.contains(";"))
					arr = nodeValue.split(";");

				for(int i=0;i<arr.length;i++)
				{
					if(arr[i].indexOf("tcp://")==0)
					{
						nodeValue = arr[i];
					}
				}

				_getFlashServerResult.tchatmqttserver = nodeValue;
				log.debug("tchatmqttserver " + _getFlashServerResult.tchatmqttserver);
			}

			sessionNodes = root.getElementsByTagName("t2dmqttserver");
			if(sessionNodes != null && sessionNodes.getLength() == 1)
			{
				Element e = (Element)sessionNodes.item(0);
				String nodeValue = e.getFirstChild().getNodeValue();

				if(nodeValue==null || nodeValue.length()<=5)
				{
					log.error("t2dmqttserver��    " + nodeValue);
					return false;
				}

				nodeValue = nodeValue.replace("<![CDATA[", "").replace("]]>", "").trim();

				String[] arr = null;
				if(nodeValue.contains(";"))
					arr = nodeValue.split(";");

				for(int i=0;i<arr.length;i++)
				{
					if(arr[i].indexOf("tcp://")==0)
					{
						nodeValue = arr[i];
					}
				}

				_getFlashServerResult.t2dmqttserver = nodeValue;
				log.debug("t2dmqttserver " + _getFlashServerResult.t2dmqttserver);
			}

			_getFlashServerResult.issuccess = true;

//			_getFlashServerResult.getServerTimespan = (int)(new java.util.Date().getTime() - tes);
//			log.debug("doGetFlashServer timespan : " + _getFlashServerResult.getServerTimespan + " ms");
			return true;

		}

		catch(Exception e){
			log.error(" Exception " + e.toString());
			_getFlashServerResult.getServerTimespan = -1;
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
}