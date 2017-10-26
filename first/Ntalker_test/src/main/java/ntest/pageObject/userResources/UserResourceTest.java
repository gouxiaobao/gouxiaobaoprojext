//package ntest.pageObject.userResources;
//
//import org.junit.Test;
//
///**
// * Created by Administrator on 2017/5/8.
// */
//public class UserResourceTest {
//
//    @Test
//    public void userResource1() throws InterruptedException{
//        String url = "http://bj-v2-sticket.ntalker.com/sticket/index.php?m=Home&c=CrmUser&a=index&kfuserid=kf_9900_ISME9754_T2D_test002&siteid=kf_9900&token=f5c9f8f2aacb63b1546aaf22da6f03b9&rnd=1494400617891";
//        String Siteid = "kf_4607";
//        UserResource gxb = new UserResource(url,Siteid);
//        gxb.load();
//        gxb.isLoaded();
//        gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[1]/div");
//        gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[2]/div");
//        gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[3]/div");
//        gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[4]/div");
//        gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[5]/div");
//        gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[6]/div");
//        gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[7]/div");
//        gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[8]/div");
//
//
//    }}
