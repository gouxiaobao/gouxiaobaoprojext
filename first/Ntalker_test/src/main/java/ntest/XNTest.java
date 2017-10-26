package ntest;

import ntest.pageObject.BasePageObject;
import ntest.pageObject.SeleniumUtilInterface;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/4/14 0014.
 */
public class XNTest extends BasePageObject {
    protected static org.slf4j.Logger log = LoggerFactory.getLogger(XNTest.class);
    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("./config/log4j.properties");
        Main.dataDriver();
    }
}
