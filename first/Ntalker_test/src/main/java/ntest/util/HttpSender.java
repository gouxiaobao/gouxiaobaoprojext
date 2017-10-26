package ntest.util;

import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpSender {

	protected static org.slf4j.Logger log = LoggerFactory.getLogger(HttpSender.class.getName());
	// http post ����
	public  String postHttp(String http, String xml){

		String infoStr = null;
		InputStream fis = null;
		HttpURLConnection httpConn = null;

		long startt = System.currentTimeMillis();

		try {
			URL url = new URL(http);
			if(url.getProtocol().equals("http")){
				httpConn = (HttpURLConnection) url.openConnection();
			}else{
				httpConn = (HttpsURLConnection) url.openConnection();
			}

			if (httpConn == null){
				log.info(" HttpSender FAILED!!httpConn is null!!! ");
				return null;
			}
			log.info("getInfos: " + http);

			httpConn.setRequestProperty("User-agent", "MSIE8.0");
			httpConn.setRequestProperty("Content-Type", "text/xml");
			httpConn.setRequestMethod("POST");
			httpConn.setDoOutput(true);
			httpConn.setReadTimeout(10000);
			httpConn.setConnectTimeout(10000);

			OutputStream op = httpConn.getOutputStream();
			op.write(xml.getBytes("utf-8"));
			op.flush();
			op.close();

			if (httpConn.getResponseCode() == 200)
			{
				fis = httpConn.getInputStream();

				infoStr = Stream2String(fis, "utf-8");//ͨ��http��õ�û�о���У��Ĵ�

//				log.warning("getInfos success." + infoStr);
			}

		} catch (Exception e) {
			log.warn("Exception :" + e.toString());
			StackTraceElement[] er = e.getStackTrace();
			for (int i = 0; i < er.length; i++) {
				log.info(er[i].toString());
			}
		} finally {
			if (httpConn != null)
				httpConn.disconnect();
			long stopt = System.currentTimeMillis();
			long span = stopt - startt;
			if(span > 1000)
				log.warn("HttpSender send http spans " + span + "ms");
		}
		return infoStr;
	}

	//http get����
	public String getInfos(String query){

		String infoStr = null;
		InputStream fis = null;
		HttpURLConnection httpConn = null;

		long startt = System.currentTimeMillis();

		try {
			URL url = new URL(query);

			if(url.getProtocol().equals("http")){
				httpConn = (HttpURLConnection) url.openConnection();

			}else{
				httpConn = (HttpsURLConnection) url.openConnection();
			}

			if (httpConn == null){
				log.info(" HttpSender FAILED!!httpConn is null!!! ");
				return null;
			}
			log.debug("getInfos: " + query);

			httpConn.setRequestProperty("User-agent", "MSIE8.0");
			httpConn.setRequestMethod("GET");
			httpConn.setReadTimeout(10000);
			httpConn.setConnectTimeout(10000);
			httpConn.setDoOutput(true);

			if (httpConn.getResponseCode() == 200)
			{
				fis = httpConn.getInputStream();

				infoStr = Stream2String(fis, "utf-8");//ͨ��http��õ�û�о���У��Ĵ�

//				log.warning("getInfos success." + infoStr);
			}

		} catch (Exception e) {
			log.warn("Exception :" + e.toString());
			StackTraceElement[] er = e.getStackTrace();
			for (int i = 0; i < er.length; i++) {
				log.info(er[i].toString());
			}
		} finally {
			if (httpConn != null)
				httpConn.disconnect();
			long stopt = System.currentTimeMillis();
			long span = stopt - startt;
			if(span > 1000)
				log.warn("HttpSender send http spans " + span + "ms");
		}
		return infoStr;
	}

	private static String Stream2String(InputStream in, String encoding) {

		if (in == null)
			return null;

		StringBuffer out = new StringBuffer();

		try {

			char[] b = new char[1024];
			InputStreamReader inread = new InputStreamReader(in, encoding);

			for (int n; (n = inread.read(b)) != -1;) {
				String line = new String(b, 0, n);
				out.append(line);
			}

		} catch (Exception e) {

			log.warn("Exception: " + e.toString());
			StackTraceElement[] er = e.getStackTrace();
			for (int i = 0; i < er.length; i++) {
				log.info(er[i].toString());
			}
		}

		return out.toString();
	}

}
