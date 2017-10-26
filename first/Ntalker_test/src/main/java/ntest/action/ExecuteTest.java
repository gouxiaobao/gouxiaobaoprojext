package ntest.action;

import ntest.action.result.*;
import ntest.bean.MonitorTask;
import ntest.bean.Visitor;
import ntest.bean.Waiter;
import ntest.pageObject.ActivityRecord.ActivityRecordBToCModule;
import ntest.pageObject.ActivityRecord.ActivitySmokingTestCase;
import ntest.pageObject.BasePageObject;
import ntest.pageObject.settingModule.SettingPageBToC;
import ntest.pageObject.userResources.UserResourcesBToC;
import ntest.pageObject.webChatWindow.Guest;
import ntest.util.SeleniumUtil;
import ntest.util.Statics;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import ntest.pageObject.KPIModule.KpiSmokingTestCase;
import java.io.File;
import java.util.Map;
import ntest.pageObject.userResources.UserResource;
import ntest.pageObject.settingModule.SettingSmokingTest;
import ntest.pageObject.ActivityRecord.ActivityRecordAdvancedSearch;
import ntest.pageObject.settingModule.PlatformSettingSmkoing;

/**
 * Created by guo on 2017/3/24.
 */
public class ExecuteTest extends BasePageObject{
    private Map faceManageResultMap;
    private Map evaluationResultMap;

    private MonitorTask _monitorTask = null;
    private ResulInspect _resulInspect = null;

    public ExecuteTest(MonitorTask monitorTask) {
        this._monitorTask = monitorTask;
    }

    public ResulInspect get_resulInspect() {
        return _resulInspect;
    }

    public void set_resulInspect(ResulInspect _resulInspect) {
        this._resulInspect = _resulInspect;
    }

//    public void Test() throws InterruptedException {
//        Action_GetFlashServer getFlashServer = null;
//        getFlashServer = new Action_GetFlashServer(_monitorTask);
//        FlashServerResult _flashServerResult= getFlashServer.doAction();
//        _monitorTask._flashServerResult = _flashServerResult;
//        String site = _monitorTask._flashServerResult.interaction.substring(0,32);
//        String url = SeleniumUtil.fetchActivityRecordURLWithToken(site,_monitorTask.siteid,_monitorTask.waiterName,_monitorTask.token);
//        ActivitySmokingTestCaseTest.ActivitySmoking(_monitorTask.siteid,url);
//    }

    public void doAction() throws Exception {
        Action_GetFlashServer getFlashServer = null;
        Visitor visitor = null;
        Waiter waiter = null;
        T2DConnectionResult kfConnectT2DResult = null;
        GetWaiterResult getWaiterResult = null;
        TChatConnectionResult web_ChatConnectionResult = null;
        TChatConnectionResult kf_TChatConnectionResult = null;
        MessagesResult kf_messages = null;
        MessagesResult web_messages = null;
        KFSettingResult kfSettingResult = null;

        //1 设置服务器地址
        getFlashServer = new Action_GetFlashServer(_monitorTask);
        FlashServerResult _flashServerResult = getFlashServer.doAction();
        //检查是否正确获取服务地址
        if (!_resulInspect.flashServerResultCheck(_flashServerResult)) {
            return;
        }
        _monitorTask._flashServerResult = _flashServerResult;

        //2 创建访客
        visitor = new Visitor();
        visitor.setMonitorTask(_monitorTask);
        visitor.pcId = "guestWEBPCID_TEST_" + (new java.util.Date().getTime() + 1234);
        visitor.myuserId = _monitorTask.siteid + "_ISME9754_" + visitor.pcId;

        //3 创建客服
        waiter = new Waiter();
        waiter.setMonitorTask(_monitorTask);
        waiter.setVUserId(visitor.myuserId);
        waiter._myuserId = Statics.GetKFUserIdByUid(_monitorTask.siteid, _monitorTask.waiterName);

        //4 客服连接t2d
        kfConnectT2DResult = waiter.connectT2D();
        Thread.sleep(2000);
        //检查客服是否正确连接t2d
        if (!_resulInspect.t2DConnectionResultCheck(kfConnectT2DResult)) {
            return;
        }
        //5 客服登录t2d
        kfConnectT2DResult = waiter.loginT2D();
        Thread.sleep(2000);
        //检查客服是否正确登录t2d
        if (!_resulInspect.t2DloginResultCheck(kfConnectT2DResult)) {
            return;
        }
//
//        System.out.println("互动记录" + _monitorTask._flashServerResult.interaction);
//
//
//        System.out.println("我的报表" + _monitorTask._flashServerResult.statistic);
//
//        System.out.println("用户资源" + _monitorTask._flashServerResult.crm);
//
//        System.out.println("设置" + _monitorTask._flashServerResult.setting);


        //6检查客服设置环境是否满足自动化测试
        kfSettingResult = waiter.kfReceptionSetting();
        //检查客服端设置，判断是否能进行自动化测试
        if (!_resulInspect.kfReceptionSettingCheck(kfSettingResult)) {
            return;
        }
//
        if (!UILayerTest()) return;
//
////
//
//        //6 访客访问t2d请求分配客服
//        getWaiterResult = visitor.getWaiterID();
//        Thread.sleep(2000);
//        //检查是否成功为访客分配客服
//        if (!_resulInspect.getWaiterResultCheck(getWaiterResult)) {
//            return;
//        }
//
//        //7 访客连接到tcaht服务器
//        web_ChatConnectionResult = visitor.connectTchatServer(getWaiterResult.WEB_destKFUid);
//        Thread.sleep(2000);
//        //检查访客是否成功连接到tchat服务器
//        if (!_resulInspect.webConnectionResultCheck(web_ChatConnectionResult)) {
//            return;
//        }
//
//        //8 访客发送一条信息，客服弹窗
//        web_messages = visitor.sendFirstMessage("helloKF", kfConnectT2DResult);
//        Thread.sleep(2000);
//
//        //9 客服接受请求后连接tchat
//        kf_TChatConnectionResult = waiter.joinChatSession(visitor.myuserId);
//        Thread.sleep(2000);
//        //检查客服是否成功连接到tchat服务器
//        if (!_resulInspect.kfConnectionResultCheck(kf_TChatConnectionResult)) {
//            return;
//        }
//
//        //10客服收到信息后回复第一条信息
//        kf_messages = waiter.sendMessage("helloWeb");
//        Thread.sleep(2000);
//        if (!_resulInspect.kfMessageResultCheck(kf_messages, visitor.web_messages, true)) {
////            return;
//        }
//
//        //访客发送第二条文字信息
//        web_messages = visitor.sendMessage("hellokf2");
//        Thread.sleep(2000);
//        if (!_resulInspect.webMessageResultCheck(web_messages, waiter.kf_messages, true)) {
////            return;
//        }
//        //客服发送第二条文字信息
//        kf_messages = waiter.sendMessage("helloWeb2");
//        Thread.sleep(2000);
//        if (!_resulInspect.kfMessageResultCheck(kf_messages, visitor.web_messages, true)) {
////            return;
//        }
//
//        //访客发送表情
//        web_messages = visitor.sendMessage("[wx]");
//        ;
//        Thread.sleep(2000);
//        if (!_resulInspect.webMessageResultCheck(web_messages, waiter.kf_messages, false)) {
////            return;
//        }
//        //客服发送表情
//        kf_messages = waiter.sendMessage("[wx]");
//        Thread.sleep(2000);
//        if (!_resulInspect.kfMessageResultCheck(kf_messages, visitor.web_messages, false)) {
////            return;
//        }
//
//        //访客发送文件
//        web_messages = visitor.sendFileMessage();
//        Thread.sleep(2000);
//        if (!_resulInspect.webFileMessageResultCheck(web_messages, waiter.kf_messages)) {
////            return;
//        }
//        //客服发送文件
//        kf_messages = waiter.sendFileMessage();
//        Thread.sleep(2000);
//        if (!_resulInspect.kfFileMessageResultCheck(kf_messages, visitor.web_messages)) {
////            return;
//        }
//
//        //清除自动化设置前的设置项，还原原来的配置
//        kfSettingResult = waiter.kfReceptionSettingClean();
//        //检查客服端设置，判断是否能进行自动化测试
//        if (!_resulInspect.kfReceptionSettingCleanCheck(kfSettingResult)) {
//        }
//
//        //访客退出房间与tchat服务器断开连接
//        visitor.doDisconnect();
//        Thread.sleep(2000);
//
//        //客服退出房间与tchat服务器断开连接
//        waiter.doTchatDisconnect();
//        Thread.sleep(2000);
//
//        //客服断开与t2d服务器的连接
//        waiter.doT2dDisconnect();
//        Thread.sleep(2000);
//
   }

    public boolean UILayerTest() throws Exception {
        /*设置模块*/
        if (!testPageJump()) return false;

        /*用户资源模块*/
        if (!userPageJump()) return false;
        /*互动记录模块*/
        if (!testActivity()) return false;

        if (!testactivity1()) return false;
        /*KPI模块*/
        if (!testKpicase()) return false;

//        if (!testChatWindow()) return false;

        String path = System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid;
        SeleniumUtil.getFileAtPath(path);
        System.out.println(SeleniumUtil.getFileNumAtPath(path));
        if (SeleniumUtil.getFileNumAtPath(path) != 6) return false;
        return true;
    }

    //        public boolean testKPIBToC() throws InterruptedException {
//        KPIPageObject page = new KPIPageObject(SeleniumUtil.fetchKPIURLWithToken("http://nt-v1-kpi.ntalker.com",_monitorTask.siteid,_monitorTask.waiterName,_monitorTask.token));
//        page.load();
//        page.isLoaded();
//        page.quit();
//
//            String site =  _monitorTask._flashServerResult.statistic.substring(0,28);
//            KpiSmokingTestCase test1 = new KpiSmokingTestCase(SeleniumUtil.fetchKPIURLWithToken(site,_monitorTask.siteid,_monitorTask.waiterName,_monitorTask.token),
//                    System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid,
//                    _monitorTask.siteid);
//            KpiSmokingTestCase gxb = new KpiSmokingTestCase(SeleniumUtil.fetchKPIURLWithToken(site,_monitorTask.siteid,_monitorTask.waiterName,_monitorTask.token),
//                    System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid,
//                    _monitorTask.siteid);
//            gxb.load();
//            gxb.isLoaded();
//            gxb.surveyMethod();
//            gxb.detailMetod();
////            gxb.invitedSessionMethod();
//            gxb.dianji1();
//            gxb.consultantResultMethod();
//            gxb.qualityPerformanceMethod();
//            gxb.orderOfPerformanceMethod();
//            gxb.keyPerformanceRankingMethod();
//            gxb.workingHoursMethod();
//            gxb.dianji2();
//            gxb.operatingReportsMethod();
//            gxb.consultingTypeMethod();
//            gxb.frequentlyAskedQuestionsMethod();
//            gxb.userAnalysisMethod();
//            gxb.contrastivePerformance();
//            gxb.queueingAnalysis();
//            gxb.qualityOfConversationMethod();
//            /*gxb.userResourcesStatistics();
//            Thread.sleep(1000);
//            gxb.workOrderPerformanceMethod();
//            Thread.sleep(1000);
//            gxb.phonePerformanceMethod();
//            Thread.sleep(1000);*/
//            gxb.quit();
//            return true;
//    }
    public boolean testActivity() throws Exception {
        //定义字符串site
        String site = _monitorTask._flashServerResult.interaction.substring(0, 32);
        //创建互动记录
        ActivityRecordBToCModule module = new ActivityRecordBToCModule();
        //设置url
        module.url = SeleniumUtil.fetchActivityRecordURLWithToken(site, _monitorTask.siteid, _monitorTask.waiterName, _monitorTask.token);
        //设置siteid
        module.siteid = _monitorTask.siteid;
        //设置保存路径
        module.savePath = System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid;
        //创建测试对象,将ActivityRecordBToCModule作为参数对象传入构造方法
        ActivitySmokingTestCase gxb = new ActivitySmokingTestCase(module.url, module.siteid, module.savePath);
        //调用浏览器打开网页
        gxb.load();
        //等待网页加载完成
        gxb.isLoaded();
        gxb.ifGoOn();
        gxb.theOverallStatisticalMethod();
        gxb.effectiveConsultationMethod();
        gxb.haveBoughtConsultingMethod();
        gxb.unpurchasedConsultingMethod();
        gxb.hasSummarizedConsultingMethod();
        gxb.notSummarizeConsultingMethod();
        gxb.commodityLinkConsultingMethod();
        gxb.shoppingCartConsultingMethod();
        gxb.consultingTheOrderPageMethod();
        gxb.paymentPageConsultingMethod();
        gxb.otherLinkConsultingMethod();
        gxb.invalidConsultingMethod();
        gxb.visitorsNoAnswerMethod();
        gxb.customerNoAnswerMethod();
        gxb.allMessagesMethod();
        gxb.processedMethod();
        gxb.notProcessedMethod();
        gxb.telephoneRecordMethod();
        gxb.answeringTheRecordOfIncomingCallsMethod();
        gxb.notAnswerIncomingCallsMethod();
        gxb.exhaleOnRecordMethod();
        gxb.exhaleNotRecordMethod();
        gxb.colleaguesSessionRecordMethod();
        gxb.exportManagementMethod();
        gxb.commonlyUsedSearchMethod();
        Thread.sleep(10000);
        gxb.quit();
        return true;
    }


//        /*下载所有记录*/
//        if (!page.downloadRecord(_monitorTask.siteid,module.savePath,null)) return false;
//        Thread.sleep(1000);
//        /*下载聊天记录*/
//       if(!page.downloadChatLog("无效咨询")) return false;
//        Thread.sleep(1000);
//        if(!page.downloadChatLog("有效咨询")) return false;
//        Thread.sleep(1000);
//        /*会话信息 用户信息 用户轨迹*/
//       if(!page.sessionInfoHandle("无效咨询")) return false;
//        Thread.sleep(1000);
//        if(!page.sessionInfoHandle("有效咨询")) return false;
//        Thread.sleep(1000);
//        page.quit();
//        return true;


    public boolean testUserResources() throws Exception {
        String site = _monitorTask._flashServerResult.crm.substring(0, 32);
        UserResourcesBToC page = new UserResourcesBToC(SeleniumUtil.fetchUserResourceURLWithToken(site, _monitorTask.siteid, _monitorTask.waiterName, _monitorTask.token),
                System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid,
                _monitorTask.siteid);
        page.load();
        page.isLoaded();
        /* 下载模板文件*/
        if (!page.downloadFileHandle()) return false;
        page.quit();

        return true;
    }

    public boolean testSetting() throws Exception {
        String site = _monitorTask._flashServerResult.setting.substring(0, 32);
        SettingPageBToC page = new SettingPageBToC(
                SeleniumUtil.fetchSettingURLWithToken(site, _monitorTask.siteid, _monitorTask.waiterName, _monitorTask.token),
                System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid,
                _monitorTask.siteid);
        page.load();
        page.isLoaded();
         /* 创建接待组
        page.createGroupHandle();*/
        /*企业管理
        page.settingManage();*/
       /*表情管理*/
        this.faceManageResultMap = page.faceManage();
        System.out.println("+++++++++++++++" + faceManageResultMap);
        if (faceManageResultMap == null) return false;
        /*咨询评价*/
        this.evaluationResultMap = page.evaluation();
        System.out.println("+++++++++++++++" + evaluationResultMap);
        if (evaluationResultMap == null) return false;

        /*企业常用话术*/
        if (!page.enterpriseUsage()) return false;
        /*创建接待组
         page.checkNewGroup();
        page.createNewReceptionGroup();*/
        /*删除接待组
         page.checkNewGroup();
        page.deleteNewReceptionGroup();
        page.checkNewGroup();*/
        /* 创建用户
        SettingTestModel settingTestModel = new SettingTestModel("admin99hh94","kalen29h534","123456","kf_4607");
        page.createNewUser(settingTestModel);*/
        /*咨询接待
        page.queryPageManagement();*/
        Thread.sleep(10000);
        page.quit();
        return true;
    }


    public Boolean testChatWindow() throws InterruptedException {
        String currentPath = System.getProperty("user.dir");
        String savePath = currentPath + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid;
        File fileSavePath = new File(savePath);
        if (!fileSavePath.exists()) {
            fileSavePath.mkdirs();
        } else {
            for (File file :
                    fileSavePath.listFiles()) {
                file.delete();
            }
        }
        ProfilesIni pi = new ProfilesIni();
        FirefoxProfile profile = pi.getProfile("default");
        profile.setPreference("browser.download.folderList", 2); //设置成0 代表下载到浏览器默认下载路径；设置成2 则可以保存到指定目录。
        profile.setPreference("browser.download.manager.showWhenStarting", false); //是否显示开始，Ture 为显示，Flase 为不显示。
        profile.setPreference("browser.download.dir", savePath);   //用于指定你所下载文件的目录。
        profile.setPreference(
                "browser.helperApps.neverAsk.saveToDisk",
                "application/zip,text/plain,application/vnd.ms-excel,text/csv,text/comma-separated-values,application/octet-stream,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        WebDriver webDriver = new FirefoxDriver(profile);

        Guest guest = new Guest(webDriver, _monitorTask.testPageUrl, savePath, this.evaluationResultMap, this.faceManageResultMap);
        guest.openChatPage();

        guest.openChatWindow(_monitorTask.settingID, _monitorTask.waiterName);

//        guest.sendTextMessage(message);
        guest.sendTextMessage("__XN__");

        guest.sendAllEmoticon();
        guest.checkoutEmoticon();
//        String defalutExePath = currentPath + "\\src\\main\\java\\ntest\\other\\upload.exe";
//        String defautlTitle = "[CLASS:#32770]";
//        String defaultImagePath = currentPath + "\\src\\main\\java\\ntest\\other\\images\\3.jpg";
//        String defaultText = "Edit1";
//        String defaultButton = "Button1";
//        guest.sendImage(defalutExePath, defautlTitle, defaultImagePath, defaultText, defaultButton);
//        String defaultFilePath = currentPath + "\\src\\main\\java\\ntest\\other\\file\\test.txt";
//        guest.sendFile(defalutExePath, defautlTitle, defaultFilePath, defaultText, defaultButton);
//
//        guest.downloadChatMessage();
//        guest.activeEvaluate();
//        guest.sendEvaluate();
//        String defaultCaptureExePath = currentPath + "\\src\\main\\java\\ntest\\other\\mousedoubleclick.exe";
//        guest.screenCapture(defaultCaptureExePath);

//        guest.endChat();

        return true;

    }

    public boolean testKpicase() throws InterruptedException {
        String site = _monitorTask._flashServerResult.statistic.substring(0, 28);
        ActivityRecordBToCModule module = new ActivityRecordBToCModule();
        module.url = SeleniumUtil.fetchKPIURLWithToken(site, _monitorTask.siteid, _monitorTask.waiterName, _monitorTask.token);
        module.siteid = _monitorTask.siteid;
        module.savePath = System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid;
        KpiSmokingTestCase gxb = new KpiSmokingTestCase(module.url, module.siteid, module.savePath);
        gxb.load();
        gxb.isLoaded();
        gxb.estimate();
        gxb.surveyMethod();
        gxb.detailMetod();
        gxb.dianji1();
        gxb.consultantResultMethod();
        gxb.qualityPerformanceMethod();
        gxb.orderOfPerformanceMethod();
        Thread.sleep(1000);
        gxb.keyPerformanceRankingMethod();
        gxb.workingHoursMethod();
        gxb.dianji2();
        gxb.operatingReportsMethod();
        gxb.consultingTypeMethod();
        gxb.frequentlyAskedQuestionsMethod();
        gxb.userAnalysisMethod();
        gxb.contrastivePerformanceMethod();
        gxb.queueingAnalysis();
        gxb.qualityOfConversationMethod();
        gxb.userResourcesStatisticsMethod();
        gxb.workOrderPerformanceMethod();
        gxb.phonePerformanceMethod();
        Thread.sleep(10000);
        gxb.quit();
        return true;
    }

    public boolean userPageJump() throws InterruptedException {
        String site = _monitorTask._flashServerResult.crm.substring(0, 32);
        UserResource gxb = new UserResource(SeleniumUtil.fetchUserResourceURLWithToken(site, _monitorTask.siteid, _monitorTask.waiterName, _monitorTask.token),
                System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid,
                _monitorTask.siteid);
        gxb.load();
        gxb.isLoaded();
        gxb.ifGoOn();
        try {
            gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[1]/div");
        }catch (Exception e){
            gxb.logger("用户资源 全部  未通过");
        }
     //   ((JavascriptExecutor) driver).executeScript("history.back()");

        try {
            gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[2]/div");
        }catch (Exception e){
            gxb.logger("用户资源 今日新增  未通过");
        }
//        ((JavascriptExecutor) driver).executeScript("history.back()");

        try {
            gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[3]/div");
        }catch (Exception e){
            gxb.logger("用户资源 未分配 未通过");
        }
//        ((JavascriptExecutor) driver).executeScript("history.back()");

        try {
            gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[4]/div");
        }catch (Exception e){
            gxb.logger("用户资源 分配给我的 未通过");
        }
//        ((JavascriptExecutor) driver).executeScript("history.back()");

        try {
            gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[5]/div");
        }catch (Exception e){
            gxb.logger("用户资源 已完成的 未通过");
        }
//        ((JavascriptExecutor) driver).executeScript("history.back()");

        try {
            gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[6]/div");
        }catch (Exception e){
            gxb.logger("用户资源 无效的 未通过");
        }
//        ((JavascriptExecutor) driver).executeScript("history.back()");

        try {
            gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[7]/div");
        }catch (Exception e){
            gxb.logger("用户资源 我创建的 未通过");
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (int i = 0 ; i < stackTrace.length ; i ++) {
                gxb.logger(stackTrace[i]);
            }

        }
//        ((JavascriptExecutor) driver).executeScript("history.back()");

        try {
            gxb.pageJumpsMethod(".//*[@class='lefts']/ul/li[8]/div");
        }catch (Exception e){
            gxb.logger("用户资源 常用搜索 未通过");
        }
//        ((JavascriptExecutor) driver).executeScript("history.back()");
        Thread.sleep(10000);
        gxb.quit();
        return true;
    }


    public boolean testPageJump() throws InterruptedException {
        String site = _monitorTask._flashServerResult.setting.substring(0, 32);
        SettingSmokingTest gxb = new SettingSmokingTest(
                SeleniumUtil.fetchSettingURLWithToken(site, _monitorTask.siteid, _monitorTask.waiterName, _monitorTask.token),
                System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid,
                _monitorTask.siteid);
        gxb.load();
        gxb.isLoaded();
        gxb.ifGoOn();
        gxb.personalJump();
        Thread.sleep(10000);
        gxb.quit();
        return true;
    }
    public boolean platfromtestPageJump() throws InterruptedException {
        String site = _monitorTask._flashServerResult.setting.substring(0, 32);
        PlatformSettingSmkoing gxb = new PlatformSettingSmkoing(
                SeleniumUtil.fetchSettingURLWithToken(site, _monitorTask.siteid, _monitorTask.waiterName, _monitorTask.token),
                System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid,
                _monitorTask.siteid);
        gxb.load();
        gxb.isLoaded();
        gxb.ifGoOn();
        gxb.PlatformSettingPageJump();
        Thread.sleep(10000);
        gxb.quit();
        return true;
    }


    public boolean  testactivity1() throws  InterruptedException{
        String site = _monitorTask._flashServerResult.interaction.substring(0, 32);
        //创建互动记录
        ActivityRecordBToCModule module = new ActivityRecordBToCModule();
        //设置url
        module.url = SeleniumUtil.fetchActivityRecordURLWithToken(site, _monitorTask.siteid, _monitorTask.waiterName, _monitorTask.token);
        //设置siteid
        module.siteid = _monitorTask.siteid;
        //设置保存路径
        module.savePath = System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + _monitorTask.siteid;
        //创建测试对象,将ActivityRecordBToCModule作为参数对象传入构造方法
        ActivityRecordAdvancedSearch gxb = new ActivityRecordAdvancedSearch(module.url, module.siteid, module.savePath);
        gxb.load();
        gxb.isLoaded();
        gxb.advancedSearchMethod();
        gxb.searchOnlineConsultationRecordMethod();
        gxb.advancedSearchMethod();
        gxb.searchOnlineConsultationRecordMethod();
        gxb.advancedSearchMethod();
        gxb.searchTheMessageRecordMethod();
        gxb.advancedSearchMethod();
        gxb.searchThePhoneRecordsMethod();
        gxb.quit();
        return  true;
    }

    public void  platformDoAction() throws  Exception {
        Action_GetFlashServer getFlashServer = null;
        Visitor visitor = null;
        Waiter waiter = null;
        T2DConnectionResult kfConnectT2DResult = null;
        GetWaiterResult getWaiterResult = null;
        TChatConnectionResult web_ChatConnectionResult = null;
        TChatConnectionResult kf_TChatConnectionResult = null;
        MessagesResult kf_messages = null;
        MessagesResult web_messages = null;
        KFSettingResult kfSettingResult = null;

        //1 设置服务器地址
        getFlashServer = new Action_GetFlashServer(_monitorTask);
        FlashServerResult _flashServerResult = getFlashServer.doAction();
        //检查是否正确获取服务地址
        if (!_resulInspect.flashServerResultCheck(_flashServerResult)) {
            return;
        }
        _monitorTask._flashServerResult = _flashServerResult;

        //2 创建访客
        visitor = new Visitor();
        visitor.setMonitorTask(_monitorTask);
        visitor.pcId = "guestWEBPCID_TEST_" + (new java.util.Date().getTime() + 1234);
        visitor.myuserId = _monitorTask.siteid + "_ISME9754_" + visitor.pcId;

        //3 创建客服
        waiter = new Waiter();
        waiter.setMonitorTask(_monitorTask);
        waiter.setVUserId(visitor.myuserId);
        waiter._myuserId = Statics.GetKFUserIdByUid(_monitorTask.siteid, _monitorTask.waiterName);

        //4 客服连接t2d
        kfConnectT2DResult = waiter.connectT2D();
        Thread.sleep(2000);
        //检查客服是否正确连接t2d
        if (!_resulInspect.t2DConnectionResultCheck(kfConnectT2DResult)) {
            return;
        }
        //5 客服登录t2d
        kfConnectT2DResult = waiter.loginT2D();
        Thread.sleep(2000);
        //检查客服是否正确登录t2d
        if (!_resulInspect.t2DloginResultCheck(kfConnectT2DResult)) {
            return;
        }
        //6检查客服设置环境是否满足自动化测试
        kfSettingResult = waiter.kfReceptionSetting();
        //检查客服端设置，判断是否能进行自动化测试
        if (!_resulInspect.kfReceptionSettingCheck(kfSettingResult)) {
            return;
        }
        if (!platfromUILayerTest()) return;
    }


    public boolean platfromUILayerTest() throws Exception {
        /*平台设置*/
        if (!platfromtestPageJump()) return false;
        /*用户资源模块*/
        if (!userPageJump()) return false;
        /*互动记录模块*/
        if (!testActivity()) return false;

        if (!testactivity1()) return false;
        /*KPI模块*/
        if (!testKpicase()) return false;


        return true;
    }


}