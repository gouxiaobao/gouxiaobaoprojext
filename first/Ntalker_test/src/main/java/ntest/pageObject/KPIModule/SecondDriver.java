package ntest.pageObject.KPIModule;

import ntest.pageObject.ActivityRecord.ActivityRecordBToCModule;
import ntest.pageObject.BasePageObject;
import ntest.util.SeleniumUtil;

/**
 * Created by Administrator on 2017/4/11 0011.
 */
    public class SecondDriver extends BasePageObject {
        public SecondDriver (String url) {
            this.url = url;
            fetchPageObject(this);
        }
        public SecondDriver (ActivityRecordBToCModule module) {
            this.url = module.url;
            this.siteid = module.siteid;
            this.savePath = module.savePath;
            fetchPageObject(this);
        }

    public SecondDriver (String url, String savePath, String siteid) {
        this.url = url;
        this.siteid = siteid;
        this.savePath = savePath;
        fetchPageObject(this);
    }
        public void downloadFile (String exeName) throws Exception {
            Thread.sleep(1000);
            SeleniumUtil.exeHandle(exeName);
            Thread.sleep(4000);
            driver.quit();
        }
    }

