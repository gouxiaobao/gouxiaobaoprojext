package ntest.pageObject.ActivityRecord;

import ntest.pageObject.BasePageObject;
import ntest.pageObject.SeleniumUtilInterface;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public class ActivityRecordAdvancedSearch extends BasePageObject {
    //    private WebElement maketure;
    private String ture = ".//*[@class='s_righ_top']/table/tbody/tr[4]/td[3]/input[1]";
    String answer1 = "datachecked";

    public ActivityRecordAdvancedSearch(String url, String siteid,String savePath) {
        //  super(BasePageObject obj);
//        ProfilesIni pi = new ProfilesIni();
//        FirefoxProfile profile = pi.getProfile("default");
//        profile.setPreference("browser.download.folderList",2);
//        profile.setPreference("browser.download.manager.showWhenStarting",false);
//        profile.setPreference("browser.download.dir", savePath);
//        profile.setPreference(
//                "browser.helperApps.neverAsk.saveToDisk",
//                "application/zip,text/plain,application/vnd.ms-excel,text/csv,text/comma-separated-values,application/octet-stream,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//        driver = new FirefoxDriver(profile);
//        String str = addr;
//
//        maketure = driver.findElement(By.xpath(str));

        if (url != null && siteid != null&& savePath!=null) {
            this.url = url;
            this.siteid = siteid;
            this.savePath=savePath;
            fetchPageObject(this);
        }
    }

    private static Logger testResultLog = Logger.getLogger("TestResult");
    String advancedSearch = ".//a[contains(text(),'高级搜索')]";

    public void advancedSearchMethod() throws InterruptedException {
        try {
            WebElement advancedSearch = driver.findElement(By.xpath(".//a[contains(text(),'高级搜索')]"));
            if (isExist(By.xpath(".//a[contains(text(),'高级搜索')]"))) ;
            advancedSearch.click();
            joinTheParameters();
//        WebElement answer = driver.findElement(By.xpath(".//*[@class='s_righ_cont']/div[1]/div[1]/ul/li[1]"));
//        if (isExist(By.xpath(".//*[@class='s_righ_cont']/div[1]/div[1]/ul/li[1]"))) ;
//        String a = answer.getText();
//        String b = "客服：";
//        if (a.equals(b)) {
//            logger("pass");
//        } else {
//            logger("advancedSearchMethod no pass");
//        }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase effectiveConsultation(高级搜索) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public void searchOnlineConsultationRecordMethod() throws InterruptedException {
        try {
            WebElement searchOnlineConsultationRecord = driver.findElement(By.xpath(".//*[@class='s_righ_top']/table/tbody/tr[2]/td[2]/input[1]"));
            searchOnlineConsultationRecord.click();
            Thread.sleep(1000);
            WebElement a = driver.findElement(By.xpath(".//*[@class='s_righ_cont']/div[1]/div[1]/ul[1]/li[1]"));
            String answer = driver.getTitle();
            String b = "Ntalker_互动记录";
            if (b.equals(answer)) {
                logger(siteid+"pass  高级搜索");
            } else {
                logger("searchOnlineConsultationRecordMethod no pass");
            }
            WebElement keywordinput = driver.findElement(By.xpath(".//*[@class='s_righ_top']/table/tbody/tr[3]/td[2]/input[1]"));
            keywordinput.sendKeys("你好");
            WebElement maketure = driver.findElement(By.xpath(ture));
            maketure.click();
            joinTheParameters();
            advancedSearchMethod();
            WebElement allCustomerService = driver.findElement(By.xpath(".//*[@class='s_righ_cont']/div[1]/div[1]/ul/li[2]/span"));
            //  if (isExist(By.xpath(".//*[@class='s_righ_cont']/div[1]/div[1]/ul/li[2]/span")));
            allCustomerService.click();
            List<WebElement> arr = driver.findElements(By.xpath(".//*[@id='jdkfid']/div[2]/ul[1]/li"));
            if (arr.size() >= 1) {
                WebElement W = driver.findElement(By.xpath(".//*[@id='jdkfid']/div[2]/ul/li[1]/span"));
                W.click();
                List<WebElement> arr1 = driver.findElements(By.xpath(".//*[@class='list_div']/ul/div[2]/div[2]/ul/li[1]"));
                if (arr1.size() >= 1) {
                    WebElement admin = driver.findElement(By.xpath(".//*[@id='jdkfid']/div[2]/ul[1]/li[1]/ul/li[1]"));
                    admin.click();
                    WebElement ture = driver.findElement(By.xpath(".//*[@class='search_consult']/div[1]/ul/div[2]/div[3]/input[1]"));
                    ture.click();
                } else return;
            } else return;
//            for (WebElement element : elements) {
//                String text1 = element.getText();
//                System.out.println(text1);
//                {
//                    logger(text1);
//                }
            WebElement save = driver.findElement(By.xpath(".//*[@class='s_righ_top']/table/tbody/tr[4]/td[3]/input[2]"));
            save.click();
            WebElement name = driver.findElement(By.xpath(".//*[@id='tc_div_serch']/input[1]"));
            name.sendKeys("自动化测试");
            WebElement cancel = driver.findElement(By.xpath(".//*[@id='tc_div_serch']/input[3]"));
            cancel.click();
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase effectiveConsultation(高级搜索) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }







    public void  searchThePhoneRecordsMethod()throws  InterruptedException {
        try {
            WebElement searchThePhoneRecords = driver.findElement(By.xpath(".//*[@class='s_righ_top']/table/tbody/tr[2]/td[2]/input[2]"));
            if (isExistNew(".//*[@class='s_righ_top']/table/tbody/tr[2]/td[2]/input[2]")) ;
            searchThePhoneRecords.click();
            WebElement answer = driver.findElement(By.xpath(".//*[@class='s_righ_cont']/div[2]/div[1]/ul/li[1]"));
            if (isExistNew(".//*[@class='s_righ_cont']/div[2]/div[1]/ul/li[1]")) ;
            String a = answer.getText();
            String b = "通话时长：";
            if (a.equals(b)) {
                logger("pass");
            } else {
                logger("searchThePhoneRecordsMethod no pass");
            }
            WebElement callTime = driver.findElement(By.xpath(".//*[@class='s_righ_cont']/div[2]/div[1]/ul/li/ul/li[1]/select[1]"));
            Select select = new Select(callTime);
            select.selectByIndex(1);
            WebElement dateTimePicker = driver.findElement(By.xpath(".//*[@class='search_call']/div[1]/ul[1]/li[2]/ul/li[2]/input"));
            dateTimePicker.sendKeys("10");
            WebElement maketure = driver.findElement(By.xpath(ture));
            maketure.click();
            joinTheParameters();
//        WebElement  answer2 = driver.findElement(By.xpath("html/body/div[3]"));
//        answer2.getText();
//        {logger(answer2);}
//        String a1 = "没有记录";
//        if (a1.equals(answer2)){logger("pass");}
//        else {logger("searchOnlineConsultationRecordMethod no pass");}
            WebElement today = driver.findElement(By.xpath(".//a[contains(text(),'今天')]"));
            today.click();
//        WebElement today1=driver.findElement(By.xpath(".//a[contains(text(),'今天')]"));
            elementWaitAndHandle(".//a[contains(text(),'今天')]", new SeleniumUtilInterface() {
                @Override
                public void action(WebElement element) throws InterruptedException {
                    if (element.getAttribute("class").equals(answer1)) {
                        logger(siteid+"高级搜索 today pass");
                    } else {
                        logger("today no pass");
                    }
                }
            });

            WebElement yesterday = driver.findElement(By.xpath(".//a[contains(text(),'昨天')]"));
            yesterday.click();
//        WebElement yesterday1 = driver.findElement(By.xpath(".//a[contains(text(),'今天')]"));
            elementWaitAndHandle(".//a[contains(text(),'昨天')]", new SeleniumUtilInterface() {
                @Override
                public void action(WebElement element) throws InterruptedException {
                    if (element.getAttribute("class").equals(answer1)) {
                        logger(siteid+"高级搜索 yesterday pass");
                    } else {
                        logger("yesterday no pass");
                    }
                }
            });
            WebElement nearlyThreeDays = driver.findElement(By.xpath(".//a[contains(text(),'近三天')]"));
            nearlyThreeDays.click();
//        WebElement nearlyThreeDays1 =driver.findElement(By.xpath(".//a[contains(text(),'近三天')]"));
            elementWaitAndHandle(".//a[contains(text(),'近三天')]", new SeleniumUtilInterface() {
                @Override
                public void action(WebElement element) throws InterruptedException {
                    if (element.getAttribute("class").equals(answer1)) {
                        logger(siteid+"高级搜索 nearlyThreeDays pass");
                    } else {
                        logger("nearlyThreeDays no pass");
                    }
                }
            });
            WebElement nearlyAWeek = driver.findElement(By.xpath(".//a[contains(text(),'近一周')]"));
            nearlyAWeek.click();
            Thread.sleep(1000);
            WebElement nearlyAWeek1 = driver.findElement(By.xpath(".//a[contains(text(),'近一周')]"));
            if (nearlyAWeek1.getAttribute("class").equals(answer1)) {
                logger(siteid+"高级搜索 nearlyWeek pass");
            } else {
                logger("nearlyAWeek no pass");
            }


            WebElement nearlyAMouth = driver.findElement(By.xpath(".//a[contains(text(),'近一月')]"));
            nearlyAMouth.click();
            Thread.sleep(1000);
            WebElement nearlyAMouth1 = driver.findElement(By.xpath(".//a[contains(text(),'近一月')]"));
            if (nearlyAMouth1.getAttribute("class").equals(answer1)) {
                logger(siteid+"高级搜索 nearlyAMouth pass");
            } else {
                logger("nearlyAMouth no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase effectiveConsultation(高级搜索) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }
    }
    public void searchTheMessageRecordMethod()throws InterruptedException {
        try {
            WebElement searchTheMessageRecord = driver.findElement(By.xpath(".//*[@class='s_righ_top']/table/tbody/tr[2]/td[2]/input[3]"));
            // WebElement okBtn =driver.findElement(By.xpath(""));
            searchTheMessageRecord.click();
            Thread.sleep(1000);
            WebElement processMode = driver.findElement(By.xpath(".//*[@class='s_righ_cont']/div[3]/div[3]/ul/li[1]"));
            String a = processMode.getText();
            String b = "处理方式：";
            if (a.equals(b)) {
                logger(siteid+"高级搜索 搜索留言记录 pass");
            } else {
                logger("searchTheMessageRecordMethod no pass");
            }
            WebElement keyWordInput = driver.findElement(By.xpath(".//*[@class='s_righ_top']/table/tbody/tr[3]/td[2]/input[1]"));
            keyWordInput.sendKeys("18310846747");
            WebElement maketure = driver.findElement(By.xpath(ture));
            maketure.click();
            joinTheParameters();
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase effectiveConsultation(高级搜索) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }
    }

}




