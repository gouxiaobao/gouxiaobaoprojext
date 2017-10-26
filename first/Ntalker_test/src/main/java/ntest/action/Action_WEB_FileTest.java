package ntest.action;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.activation.MimetypesFileTypeMap;
import javax.net.ssl.HttpsURLConnection;

import ntest.bean.MonitorTask;
import ntest.bean.Visitor;
import ntest.bean.Waiter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ntest.action.result.FileTestResult;

/**
 * 访客上传文件和下载文件
 */
public class Action_WEB_FileTest {

	private FileTestResult _result =null;
	private MonitorTask _monitorTask = null;
	private Waiter _waiter=null;
	private boolean _iskf = false;

	protected static Logger log = LoggerFactory.getLogger(Action_WEB_FileTest.class.getName());

	public  Action_WEB_FileTest(MonitorTask monitorTask,boolean iskf){
		this._monitorTask = monitorTask;
		this._iskf= iskf;
	}
	public  Action_WEB_FileTest(){}
	//
	public FileTestResult doUploadAction() {
		_result = new FileTestResult();
		upload(_monitorTask._flashServerResult.fileserver, _monitorTask.pictureFilePath);
		return _result;
	}
	//
	public boolean doDownloadAction(FileTestResult result,String url ) {
		_result = result;
		return download(url);
	}

	//文件上传
	private  void upload(String urlStr,String filepath)
	{
		log.debug("START");

		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716";
		try
		{
			long t = System.currentTimeMillis();

			//iskf为true是走客户端上传链接，false走访客端上传链接
			if (_iskf && _waiter != null ){
				urlStr +="/imageupload.php";
			}else{
				urlStr +="/imageupload.php?" +
						"fkey=D98DD2EE96A6417A" +
						"&rnd=1490251974511" +
						"&action=uploadimage" +
						"&roomid=T2D" +
						"&siteid=" +_monitorTask.siteid+
						"&settingid=" +_monitorTask.settingID+
						"&charset=UTF-8";
			}
			log.debug(urlStr);

			URL urlobj = new URL(urlStr);

			if(urlobj.getProtocol().equals("http")){
				conn = (HttpURLConnection) urlobj.openConnection();

			}else{
				conn = (HttpsURLConnection) urlobj.openConnection();
			}

			if (conn == null){
				if (_iskf){
					_result.KF_FILE_UPLOAD = false;
				}else {
					_result.WEB_FILE_UPLOAD = false;
				}
				log.info(" FileTest upload FAILED!!httpConn is null!!! ");
				return ;
			}

			conn.setConnectTimeout(5000);
			conn.setReadTimeout(10000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Accept-Encoding", "Keep-gzip, deflate, sdch");
			conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());

			// file
			if (filepath != null) {

				File file = new File(filepath);
				String filename = file.getName();
				long len = file.length();

				_result.setFileSize(len);

				String contentType = new MimetypesFileTypeMap()
						.getContentType(file);
				if (filename.endsWith(".png")) {
					contentType = "image/png";
				}
				if (contentType == null || contentType.equals("")) {
					contentType = "application/octet-stream";
				}
				//设置post参数
				StringBuffer strBuf = new StringBuffer();
				//_iskf为true时 是从客服端上传文件
				if (_iskf && _waiter != null){

					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"Filename\"" + "\r\n");
					strBuf.append("\r\n").append(filename);

					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"action\"" + "\r\n");
					strBuf.append("\r\n").append("uploadimage");

					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"type\"" + "\r\n");
					strBuf.append("\r\n").append("json");

					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"roomid\"" + "\r\n");
					strBuf.append("\r\n").append("t2d");

					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"srcuid\"" + "\r\n");
					strBuf.append("\r\n").append(_waiter._myuserId);

					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"fileType\"" + "\r\n");
					strBuf.append("\r\n").append(".jpg");

					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"siteid\"" + "\r\n");
					strBuf.append("\r\n").append(_monitorTask.siteid);


					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"Upload\"" + "\r\n");
					strBuf.append("\r\n").append("Submit Query").append("\r\n");
				}
//----------------------------------------------------------------------------------------------------------------
				strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
				strBuf.append("Content-Disposition: form-data; name=\"userfile\"; filename=\"" + filename
						+ "\"\r\n");
				//strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
				strBuf.append("Content-Type:application/octet-stream" + "\r\n\r\n");


				out.write(strBuf.toString().getBytes());

				DataInputStream in = new DataInputStream(
						new FileInputStream(file));
				int bytes = 0;
				byte[] bufferOut = new byte[1024];
				while ((bytes = in.read(bufferOut)) != -1) {
					out.write(bufferOut, 0, bytes);
				}
				in.close();
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			//提交上传内容
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(),"utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			String res = strBuf.toString();
			System.out.print(res);
			reader.close();
			reader = null;
			if(res == null || res.length()<=0)
			{
				if (_iskf){
					_result.KF_FILE_UPLOAD = false;
				}else {
					_result.WEB_FILE_UPLOAD = false;
				}
				return ;
			}
			//检查是否提交失败
			JSONObject obj = new JSONObject(res);
			if(obj.has("type"))
			{
				int type = obj.getInt("type");
				if(type == 9)
				{
					if (_iskf){
						_result.KF_FILE_UPLOAD = false;
					}else {
						_result.WEB_FILE_UPLOAD = false;
					}
					return  ;
				}
			}
			if(obj.has("sourceurl"))
			{
				String returnurl = obj.getString("sourceurl");
				if(returnurl != null)
				{
					_result.setUrl(returnurl);
					if (_iskf){
						_result.KF_FILE_UPLOAD = true;
					}else {
						_result.WEB_FILE_UPLOAD = true;
					}
					return ;
				}
			}

		}catch (Exception e) {
			if (_iskf){
				_result.KF_FILE_UPLOAD = false;
			}else {
				_result.WEB_FILE_UPLOAD = false;
			}
			log.warn("Exception "+e.toString());
		}finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}

		log.debug("END");
	}

	//文件下载
	private boolean download(String _url)
	{
		log.debug("START");
		HttpURLConnection conn = null;
		try
		{
			if(_result== null)
			{
				return false;
			}
//			String url = result.getUrl();
			String url = _url;
			if(url == null || url.length()<=0)
			{
				return false;
			}

			long t = System.currentTimeMillis();

			URL urlobj = new URL(url);
			if(urlobj.getProtocol().equals("http")){
				conn = (HttpURLConnection) urlobj.openConnection();

			}else{
				conn = (HttpsURLConnection) urlobj.openConnection();
			}

			if (conn == null){
				log.info(" FileTest download FAILED!!httpConn is null!!! ");
				return false;
			}
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(10000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");

			String length = conn.getHeaderField("Accept-Length");

			if(length != null)
			{
				try
				{
					long thelen = Long.parseLong(length);
					if(thelen != _result.getFileSize())
					{
						return false;
					}
				} catch (Exception e) {
					log.warn("Exception "+e.toString() +" / "+length);
					return false;
				}
			}else {
				return false;
			}

			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(),"utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			//String res = strBuf.toString();
			reader.close();
			reader = null;
			strBuf = null;

		} catch (Exception e) {
			log.warn("Exception "+e.toString());
			return false;
		}finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		log.debug("END");
		return true;
	}

}
