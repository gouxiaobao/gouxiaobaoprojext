package ntest.test;

import ntest.pageObject.BasePageObject;
import ntest.pageObject.KPIModule.KpiSmokingTestCase;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/19.
 */
public class Ceshi extends BasePageObject {
    WebDriver driver;
    String answer = "sele";
    String button1 = ".//*[@class='s_left_cont']/div[2]/a";
    /* 绩效分析*/
    String consultantResult = ".//*[@class='s_left_cont']/ul[2]/a[1]/li";       //咨询绩效
    String qualityPerformance = ".//*[@class='s_left_cont']/ul[2]/a[2]/li";        //质检绩效
    static String orderOfPerformance = ".//*[@class='s_left_cont']/ul[2]/a[3]/li";        //订单绩效
    String keyPerformanceRanking = ".//*[@class='s_left_cont']/ul[2]/a[4]/li";     //关键绩效排名
    String workingHours = ".//*[@class='s_left_cont']/ul[2]/a[5]/li";              //工作时长

    public Ceshi() {
    }

    @org.junit.Test
    public void Kpi() throws MalformedURLException, InterruptedException {
//        System.setProperty("webdriver.firefox.bin", "Applications/Firefox.app");
        driver = new FirefoxDriver();
        driver.get("http//:www.baidu.com");
//        driver.get("http://tracking.ntalker.com/track.php?sid=A4CBD79E-2CDD-9ACF-4A7B-2F2724B75BBB&siteid=kf_8002&userid=kf_8002_ISME9754_T2D_fengzhicheng&nodeid=03-12&time=1495442664482");
        String team  = driver.getPageSource();
        System.out.println(team);
    }
}