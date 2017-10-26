package ntest.util.https;

/**
 * Created by guo on 2017/4/11.
 */
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class TrustAnyHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String arg0, SSLSession arg1) {
        return true;
    }

}
