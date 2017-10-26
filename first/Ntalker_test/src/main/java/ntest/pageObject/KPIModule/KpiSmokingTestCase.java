package ntest.pageObject.KPIModule;

import ntest.pageObject.BasePageObject;
import ntest.pageObject.SeleniumUtilInterface;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.InputMismatchException;


/**
 * Created by Administrator on 2017/4/26.
 */
public class KpiSmokingTestCase extends BasePageObject {

    //    public  KpiSmokingTestCase(String url){
//        this.url = url;
//        fetchPageObject(this);
//    }


    public KpiSmokingTestCase(String url, String siteid, String savePath) {
        if (url != null || savePath != null || siteid != null) {
            this.url = url;
            this.siteid = siteid;
            this.savePath = savePath;
            fetchPageObject(this);
        }
    }

    private static Logger testResultLog = Logger.getLogger("TestResult");

    /*  Kpi统计  */
    String survey = ".//*[@class='s_left_cont']/ul[1]/a[1]/li";        //概况
    String detail = ".//*[@class='s_left_cont']/ul[1]/a[2]/li";        //详细数据
    String invitedSession = ".//*[@class='s_left_cont']/ul[1]/a[3]/li"; //邀请会话


    String button1 = ".//*[@class='s_left_cont']/div[2]/a";
    /* 绩效分析*/
    String consultantResult = ".//*[@class='s_left_cont']/ul[2]/a[1]/li";       //咨询绩效
    String qualityPerformance = ".//*[@class='s_left_cont']/ul[2]/a[2]/li";        //质检绩效
    String orderOfPerformance = ".//*[@class='s_left_cont']/ul[2]/a[3]/li";        //订单绩效
    String keyPerformanceRanking = ".//*[@class='s_left_cont']/ul[2]/a[4]/li";     //关键绩效排名
    String workingHours = ".//*[@class='s_left_cont']/ul[2]/a[5]/li";              //工作时长


    String button2 = ".//*[@class='s_left_cont']/div[3]/a";
    /* 数据分析 */
    String operatingReports = ".//*[@class='s_left_cont']/ul[3]/a[1]/li";          //运营报表
    String consultingType = ".//*[@class='s_left_cont']/ul[3]/a[2]/li";            //咨询类型
    String frequentlyAskedQuestions = ".//*[@class='s_left_cont']/ul[3]/a[3]/li";  //常见问题
    String userAnalysis = ".//*[@class='s_left_cont']/ul[3]/a[4]/li";              //用户分析
    String contrastivePerformance = ".//*[@class='s_left_cont']/ul[3]/a[5]/li";    //绩效对比
    String queueingAnalysis = ".//*[@class='s_left_cont']/ul[3]/a[6]/li";          //排队分析
    String qualityOfConversation = ".//*[@class='s_left_cont']/ul[3]/a[7]/li";     //会话质量

    /*  用户资源统计，工单绩效，电话绩效 */

    String userResourcesStatistics = ".//*[@class='s_left_cont']/div[4]/a";      //用户资源统计
    String workOrderPerformance = ".//*[@class='s_left_cont']/div[5]/a";         //工单绩效
    String phonePerformance = ".//*[@class='s_left_cont']/div[6]/a";             //电话绩效


    String answer = "sele";

    /*   public void dianji (){
           WebElement dianji=driver.findElement(By.xpath(".//*[@class='s_left_cont']/ul[1]/a[2]/li"));
           dianji.click();
       }
   */
    public void estimate() throws InterruptedException {
        try {

            WebElement kpiStatisticsPointer = driver.findElement(By.xpath(".//*[@class='s_left_cont']/div[1]/div[1]"));
            String a = kpiStatisticsPointer.getAttribute("class");
            if (a.equals("icon03 icon_posi")) {
                driver.findElement(By.xpath(".//*[@class='s_left_cont']/div[1]/div[1]")).click();
            } else if (a.equals("icon_posi icon01")) {
                return;
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase estimate() 404or502or null");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }


    public void surveyMethod() throws InterruptedException {
        try {
            accordingToWait(survey);
            driver.findElement(By.xpath(survey)).click();
            Thread.sleep(1000);
            WebElement survey1 = driver.findElement(By.xpath(survey));
            if (answer.equals(survey1.getAttribute("class"))) {
                logger(siteid + ": KPI概况 pass");
            } else {
                logger(siteid + ":surveyMethod（概况） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase surveyMethod(概况) 404or502or null");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public void detailMetod() throws InterruptedException {
        try {
            accordingToWait(detail);
            driver.findElement(By.xpath(detail)).click();
            Thread.sleep(1000);
            WebElement detail1 = driver.findElement(By.xpath(detail));
            if (answer.equals(detail1.getAttribute("class"))) {
                logger(siteid + ": KPI详细数据 pass");
            } else {
                logger(siteid + ":detailMetod（详细数据） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTest Case detail(详细数据) 404or502or null");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public void dianji1() throws InterruptedException {
        accordingToWait(button1);
        WebElement btn1 = driver.findElement(By.xpath(button1));
        btn1.click();


    }

    public void invitedSessionMethod() throws InterruptedException {
        try {
            accordingToWait(invitedSession);
            driver.findElement(By.xpath(invitedSession)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(invitedSession)).getAttribute("class"))) {
                logger(siteid + ": KPI邀请会话 pass");
            } else {
                logger(siteid + ":invitedSessionMethod（邀请会话） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("404") || driver.getTitle().equals(null))
                logger(siteid + "KpiSmokingTestCase consultantResultMethod(咨询绩效) 404or502or null ");
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }



    public void consultantResultMethod() throws InterruptedException {
        try {
            accordingToWait(consultantResult);
            driver.findElement(By.xpath(consultantResult)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(consultantResult)).getAttribute("class"))) {
                logger(siteid + ": KPI咨询绩效 pass");
            } else {
                logger(siteid + ":consultantResultMethod（咨询绩效） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("404") || driver.getTitle().equals(null))
                logger(siteid + "KpiSmokingTestCase consultantResultMethod(咨询绩效) 404or502or null ");
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }


    public void qualityPerformanceMethod() throws InterruptedException {
        try {
            accordingToWait(qualityPerformance);
            driver.findElement(By.xpath(qualityPerformance)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(qualityPerformance)).getAttribute("class"))) {
                logger(siteid + ":KPI 质检绩效 pass");
            } else {
                logger(siteid + ":qualityPerformanceMethod（质检绩效） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase qualityPerformanceMethod(质检绩效) 404or502or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }


    public void orderOfPerformanceMethod() throws InterruptedException {
        try {
            accordingToWait(orderOfPerformance);
            driver.findElement(By.xpath(orderOfPerformance)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(orderOfPerformance)).getAttribute("class"))) {
                logger(siteid + ":KPI 订单绩效 pass");
            } else {
                logger(siteid + ":orderOfPerformanceMethod（订单绩效） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase orderOfPerformanceMethod(订单绩效）404or502or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }

    }

    public void keyPerformanceRankingMethod() throws InterruptedException {
        try {
            accordingToWait(keyPerformanceRanking);
            driver.findElement(By.xpath(keyPerformanceRanking)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(keyPerformanceRanking)).getAttribute("class"))) {
                logger(siteid + ":KPI 关键绩效排名  pass");
            } else {
                logger(siteid + ":keyPerformanceRankingMethod（关键绩效） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("404") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase keyPerformanceRankingMethod(关键绩效排名)  404or502or null ");
                ((JavascriptExecutor) driver).executeScript("history.back()");
            }
        }
    }


    public void workingHoursMethod() throws InterruptedException {
        try {
            accordingToWait(workingHours);
            driver.findElement(By.xpath(workingHours)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(workingHours)).getAttribute("class"))) {
                logger(siteid + ":KPI 工作时长 pass");
            } else {
                logger(siteid + ":workingHoursMethod（工作时长）no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("404") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase workingHoursMethod(工作时长) 404or502or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public void dianji2() throws InterruptedException {
//        waitingForTheElements(button2);
//        Thread.sleep(2000);
        accordingToWait(button2);
        WebElement btn = driver.findElement(By.xpath(button2));
        Thread.sleep(2000);
        btn.click();

    }


    public void operatingReportsMethod() throws InterruptedException {
        try {
            accordingToWait(operatingReports);
            driver.findElement(By.xpath(operatingReports)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(operatingReports)).getAttribute("class"))) {
                logger(siteid + ": KPI咨询发起 pass");
            } else {
                logger(siteid + ":operatingReport（咨询发起）no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase  operatingReportsMethod(运营报表) 404or502or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }//运营报表
    }


    public void consultingTypeMethod() throws InterruptedException {
        try {
            accordingToWait(consultingType);
            driver.findElement(By.xpath(consultingType)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(consultingType)).getAttribute("class"))) {
                logger(siteid + ": KPI 咨询类型 pass");
            } else {
                logger(siteid + ":consultingTypeMethod（咨询类型） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase consultingTypeMethod(咨询类型) 404or502or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }//咨询类型
    }


    public void frequentlyAskedQuestionsMethod() throws InterruptedException {
        try {
            accordingToWait(frequentlyAskedQuestions);
            driver.findElement(By.xpath(frequentlyAskedQuestions)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(frequentlyAskedQuestions)).getAttribute("class"))) {
                logger(siteid + ":KPI 常见问题 pass");
            } else {
                logger(siteid + ":frequentlyAskedQuestionsMethod（常见问题） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase frequentlyAskedQuestionsMethod(常见问题) 404or502or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }//常见问题
    }


    public void userAnalysisMethod() throws InterruptedException {
        try {
            accordingToWait(userAnalysis);
            driver.findElement(By.xpath(userAnalysis)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(userAnalysis)).getAttribute("class"))) {
                logger(siteid + ":KPI 用户分析 pass");
            } else {
                logger(siteid + ":userAnalysisMethod（用户分析） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase userAnalysisMethod(用户分析) 404or502or null");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }//用户分析
    }

    public void contrastivePerformanceMethod() throws InterruptedException {
        try {
            accordingToWait(contrastivePerformance);
            driver.findElement(By.xpath(contrastivePerformance)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(contrastivePerformance)).getAttribute("class"))) {
                logger(siteid + ":KPI 绩效对比 pass");
            } else {
                logger(siteid + ":contrastivePerformance（绩效对比） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase  contrastivePerformance(绩效对比) 404or502or null");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }//绩效对比
    }

    public void queueingAnalysis() throws InterruptedException {
        try {
            accordingToWait(queueingAnalysis);
            driver.findElement(By.xpath(queueingAnalysis)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(queueingAnalysis)).getAttribute("class"))) {
                logger(siteid + ":KPI 排队分析 pass");
            } else {
                logger(siteid + ":queueingAnalysis(排队分析) no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase queueingAnalysis(排队分析) 404or502or null");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }//排队分析
    }

    public void qualityOfConversationMethod() throws InterruptedException {
        try {
            accordingToWait(qualityOfConversation);
            driver.findElement(By.xpath(qualityOfConversation)).click();
            Thread.sleep(1000);
            if (answer.equals(driver.findElement(By.xpath(qualityOfConversation)).getAttribute("class"))) {
                logger(siteid + ":KPI 会话质量 pass");
            } else {
                logger(siteid + ":qualityOfConversationMethod（会话质量） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase qualityOfConversationMethod(会话质量) 404or502or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }//会话质量
    }

    public void userResourcesStatisticsMethod() throws InterruptedException {
        try {
            accordingToWait(userResourcesStatistics);
            driver.findElement(By.xpath(userResourcesStatistics)).click();
            Thread.sleep(1000);
            String title = "用户资源实时统计";
            if (title.equals(driver.getTitle())) {
                logger(siteid + ":  KPI 用户资源统计  pass");
            } else {
                logger(siteid + ":userResourcesStatisticsMethod(用户资源统计)  no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase userResourcesStatisticsMethod(用户资源统计) 404or502or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }//用户资源统计
    }

    public void workOrderPerformanceMethod() throws InterruptedException {
        try {
            accordingToWait(workOrderPerformance);
            driver.findElement(By.xpath(workOrderPerformance)).click();
            Thread.sleep(1000);
            String title = "工单绩效";
            if (title.equals(driver.getTitle())) {
                logger(siteid + ":KPI 工单绩效 pass");
            } else {
                logger(siteid + ":workOrderPerformanceMethod （工单绩效）no pass");
            }

        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase workOrderPerformanceMethod(工单绩效) 404or502or null");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }//工单绩效
    }

    public void phonePerformanceMethod() throws InterruptedException {
        try {
            accordingToWait(phonePerformance);
            driver.findElement(By.xpath(phonePerformance)).click();
            Thread.sleep(1000);
            String title = "电话绩效 -- 营销概况";
            if (title.equals(driver.getTitle())) {
                logger(siteid + ":KPI 电话绩效 pass");
            } else {
                logger(siteid + "：phonePerformanceMethod （电话绩效）no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "KpiSmokingTestCase phonePerformanceMethod(电话绩效) 404or502or null");
            } else {
                logger(e.getStackTrace().toString());
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        } //电话绩效
    }


    public static void main(String[] args) throws InterruptedException {
        String url = "http://nt-v1-kpi.ntalker.com/index.php/summary/index?userid=kf_4607_ISME9754_T2D_test002&siteid=kf_4607&token=30cae2a6b914824bc87f9f78e5910aad#";
        String siteid = "kf_4608";
        String savePath = "";
        KpiSmokingTestCase gxb = new KpiSmokingTestCase(url, siteid,savePath);
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


    }
}

