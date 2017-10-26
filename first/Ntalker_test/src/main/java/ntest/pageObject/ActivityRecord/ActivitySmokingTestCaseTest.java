package ntest.pageObject.ActivityRecord;

import org.junit.Test;

/**
 * Created by Administrator on 2017/5/4.
 */
public class ActivitySmokingTestCaseTest {

    @Test
    public static void ActivitySmoking() throws InterruptedException {
        String url = "view-source:http://tracking.ntalker.com/track.php?sid=A4CBD79E-2CDD-9ACF-4A7B-2F2724B75BBB&siteid=kf_8002&userid=kf_8002_ISME9754_T2D_fengzhicheng&nodeid=03-12&time=1495442664482";
        String siteid = "4604";
        String savepath = "G:\\";
        ActivitySmokingTestCase oage = new ActivitySmokingTestCase(url, siteid, savepath);
        oage.load();
        oage.isLoaded();


    }
}