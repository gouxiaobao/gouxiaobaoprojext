package ntest.util.https;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.InputStream;
import java.net.URL;
/**
 * Created by guo on 2017/4/11.
 */
public class HttpsSender {
    private String submitUrl = null;

    public  HttpsSender(String url){
        this.submitUrl = url;
    }
    public InputStream getInfos() throws Exception {
//      String path = "E:/Bill99QuickPay/81231015722198890.jks";
//      File certFile = new File(path);
//      KeyStore ks = KeyStore.getInstance("JKS");
//      String password = "vpos123";
//      ks.load(new FileInputStream(certFile), password.toCharArray());
//      KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//      kmf.init(ks, password.toCharArray());

        TrustManager[] tm = { new MyX509TrustManager() };
//
        SSLContext sslContext = SSLContext.getInstance("TLSv1");
        System.setProperty("https.protocols","TLSv1");
        String protocol = sslContext.getProtocol();
        System.out.println(protocol);
//      sslContext.init(kmf.getKeyManagers(),tm, null);
        sslContext.init(null, tm, new java.security.SecureRandom());

        SSLSocketFactory factory = sslContext.getSocketFactory();
        SSLSocketFactoryWrapper wrapper = new SSLSocketFactoryWrapper(factory, new String[] { "TLSv1" }, null);
        URL url = new URL(submitUrl);
        HttpsURLConnection urlc = (HttpsURLConnection) url.openConnection();
        urlc.setSSLSocketFactory(wrapper);
        urlc.setHostnameVerifier(new TrustAnyHostnameVerifier());
        urlc.setRequestProperty("User-agent","MSIE8.0");
        urlc.setRequestMethod("GET");
        urlc.setDoOutput(true);
        urlc.setDoInput(true);
        urlc.setReadTimeout(10000);
        urlc.setConnectTimeout(10000);

//        OutputStream out = urlc.getOutputStream();
//        out.write(reqData.getBytes(encoding));
//        out.flush();
//        out.close();
        return urlc.getInputStream();
    }
}
