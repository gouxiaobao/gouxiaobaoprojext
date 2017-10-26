package ntest.pageObject.ActivityRecord;

import ntest.pageObject.BasePageObject;
import ntest.pageObject.KPIModule.SecondDriver;
import ntest.pageObject.SeleniumUtilInterface;
import ntest.pageObject.XNCallback;
import ntest.util.SeleniumUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
/**
 * Created by Administrator on 2017/4/26 0026.
 */
public class Activity  extends BasePageObject {
    public Activity(ActivityRecordBToCModule module) {
        if (module.url != null && module.savePath != null && module.siteid != null) {
            this.url = module.url;
            this.siteid = module.siteid;
            this.savePath = module.savePath;
            fetchPageObject(this);
        }
    }
    public boolean downloadRecord(String siteid, String savePath, String exeFilePath) throws Exception {
        String trueQueryLink = ".//a[contains(text(),'导出管理')]";
        String exportRows = ".//*[@class='table_s']/tbody/tr";
        String downloadBtn = ".//table[@class='table_s']/tbody/tr[2]/td/div[@text_val='下载']";
        String validDownloadBtn = ".//td[contains(.,'任务成功')]/following-sibling::td/div[@text_val='下载']";
        if (!clickElement(trueQueryLink)) return false;
        Object result = elementWaitAndHandle(exportRows, new XNCallback() {
            @Override
            public Object action(Object obj) {
                List<WebElement> arr = (List<WebElement>)obj;
                if(arr.size()<2) return null;
                return arr;
            }
        });
        if (result==null) return false;
        List<WebElement> arr = driver.findElements(By.xpath(validDownloadBtn));
        if (arr.size() < 1) return false;
        arr.get(1).click();
        String secondUrl = SeleniumUtil.handleAlertText(driver);
        if (secondUrl == null) return false;
        SecondDriver secondDriver = new SecondDriver(secondUrl,savePath,siteid);
        secondDriver.load();
        secondDriver.isLoaded();
        Thread.sleep(3000);
        secondDriver.quit();
        return true;
    }
    public boolean downloadChatLog (String queryTypeStr) throws InterruptedException {
        String month = ".//*[@id='month']";
        String trueQueryLink = ".//a[contains(text(),'"+queryTypeStr+"')]";
        String logRows = ".//table[@id='ngrid']/tbody/tr";
        String exportBtn = ".//*[@id='button3']";
        if (!clickElement(trueQueryLink)||!clickElement(month)) return false;
        List<WebElement> result = (List<WebElement>) elementWaitAndHandle(logRows, new XNCallback() {
            @Override
            public Object action(Object obj) {
                List<WebElement>arr = (List<WebElement>)obj;
                if (arr.size()<1) return null;
                return arr;
            }
        });
        if (result == null || !clickElement(".//table[@id='ngrid']/tbody/tr[1]")|| !clickElement(exportBtn)) return false;
        String secondUrl = SeleniumUtil.handleAlertText(driver);
        if (secondUrl == null) return false;
        SecondDriver secondDriver = new SecondDriver(secondUrl,savePath,siteid);
        secondDriver.load();
        secondDriver.isLoaded();
        Thread.sleep(3000);
        secondDriver.quit();
        return true;
    }

    public boolean sessionInfoHandle(String queryTypeStr) throws InterruptedException {
        String month = ".//*[@id='month']";
        String trueQueryLink = ".//a[contains(text(),'"+queryTypeStr+"')]";
        String logRows = ".//table[@id='ngrid']/tbody/tr";
        String checkUrl = ".//td[contains(.,'发起页url')]/a/span";
        String checkLoadingUrl = ".//*[@id='landingpages']";
        String sessionInfoLink = ".//*[@id='scene_info_li']";
        String userInfoLink = ".//*[@id='custom_info_li']";
        String userTrailLink = ".//*[@id='trail_info_li']";
        String userTrailFrame = ".//*[@id='trail_info']/iframe";
        String checkTrailUrl = ".//li[contains(.,'当前页')]/span[@class='f_blue']/a";
        if (!clickElement(month)||!clickElement(trueQueryLink)) return false;
        List<WebElement> result = (List<WebElement>) elementWaitAndHandle(logRows, new XNCallback() {
            @Override
            public Object action(Object obj) {
                List<WebElement>arr = (List<WebElement>)obj;
                if (arr.size()<1) return null;
                return arr;
            }
        });
        if (result == null || !clickElement(".//table[@id='ngrid']/tbody/tr[1]")|| !clickElement(sessionInfoLink)) return false;
       String urlResult = (String) elementWaitAndHandle(checkUrl, new XNCallback() {
            @Override
            public Object action(Object obj) {
                List<WebElement> arr = (List<WebElement>)obj;
                if (arr.size() <1 ) return null;
                WebElement element = (WebElement)arr.get(0);
                return element.getAttribute("text_val");
            }
        });
        if (urlResult.length()<1) return false;
        if (!clickElement(userInfoLink)) return false;
        String userInfoResult = (String) elementWaitAndHandle(checkLoadingUrl, new XNCallback() {
            @Override
            public Object action(Object obj) {
                List<WebElement> arr = (List<WebElement>)obj;
                if (arr.size() <1 ) return null;
                WebElement element = (WebElement)arr.get(0);
                return element.getAttribute("text_val");
            }
        });
        if (userInfoResult.length()<1) return false;
        if (!clickElement(userTrailLink)) return false;

        driver.switchTo().frame(driver.findElement(By.xpath(userTrailFrame)));
//        clickElement(checkTrailUrl);
        String userTrailResult = (String) elementWaitAndHandle(checkTrailUrl, new XNCallback() {
            @Override
            public Object action(Object obj) {
                List<WebElement> arr = (List<WebElement>)obj;
                if (arr.size() <1 ) return null;
                WebElement element = (WebElement)arr.get(0);
                return element.getText();
            }
        });
        if (userTrailResult.length()<1) return false;
        driver.switchTo().defaultContent();
        return true;
    }
}
