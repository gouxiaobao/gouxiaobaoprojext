package ntest.pageObject.settingModule;

import ntest.pageObject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Administrator on 2017/5/15.
 */
public class PlatformSettingSmkoing extends BasePageObject{

    private static Logger testResultLog  =  Logger.getLogger("TestResult");

    String  personalSetting = ".//a[text()='个人设置']";
    String  personalInformation = ".//a[text()='个人信息']";
    String  changePassword = ".//a[text()='修改密码']";
    String  peopleCommonlyUsedWords = ".//a[text()='个人常用话术']";
    String  chatSettings = ".//a[text()='聊天设置']";
    /*企业设置*/
    String  enterprisesSetUp = ".//a[text()='企业设置']";
    String  enterprisesInformation = ".//a[text()='企业信息']";
    String  webWidget = ".//a[text()='Web聊窗']";
    String  consultingTheReceptionManagementPage = ".//a[text()='咨询接待页签管理']";
    String  aUnifiedCustomerService = ".//a[text()='统一客服']";
    String  enterprisesCommonlyUsedWords = ".//a[text()='企业常用话术']";
    String  consultingType = ".//a[text()='咨询类型']";
    String  consultingEvaluation = ".//a[text()='咨询评价']";
    String  leaveWords = ".//a[text()='留言']";
    String  leaveWordsMessageSignature = ".//a[text()='留言回复签名']";
    String  receptionTime = ".//a[text()='接待时间']";
    String  qualityControl=".//a[text()='质检']";
    String  expressionManagement=".//a[text()='表情管理']";
    String  commodityInformationSet=".//a[text()='商品信息设置']";
    String  collaborativeSessionSettings=".//a[text()='协同会话设置']";


    /*平台管理*/
    String  platformManagement = ".//a[text()='平台管理']";
    String  platformAccountManagement = ".//a[text()='平台帐号管理']";
    String  merchantAccountManagement = ".//a[text()='商户帐号管理']";
    String  consultingTheBinding = ".//a[text()='咨询绑定']";
    String  platformConfiguration = ".//a[text()='平台设置']";


    /*配置管理*/
    String  configurationManagement = ".//a[text()='配置管理']";
    String  receptionGroup = ".//a[text()='接待组']";

    /*呼叫中心*/
    String  callCenter = ".//a[text()='呼叫中心']";
    String  telephoneAtMonitoring = ".//a[text()='电话坐席监控']";
    String  telephoneBill = ".//a[text()='电话账单']";
    String  qualityControlSet = ".//a[text()='质检设置']";
    /*关键页面*/
    String  theKeyPages = ".//a[text()='关键页面']";
    /*自动应答*/
    String  automaticAnswer = ".//a[text()='自动应答']";
    /*黑名单*/
    String  blackList = ".//a[text()='黑名单']";
    /*通知*/
    String  giveNotice = ".//a[text()='通知']";

    String  answer = "企业设置";




    public PlatformSettingSmkoing (String url,String siteid,String savepath) {
        this.url=url;
        this.siteid=siteid;
        this.savePath=savepath;
        fetchPageObject(this);
    }


    public   void  PlatformSettingPageJump() throws  InterruptedException{
        ifGoOn();
        accordingToWait(personalSetting);
        driver.findElement(By.xpath(personalSetting)).click();
        Thread.sleep(1000);

        try {
            accordingToWait(personalInformation);
            driver.findElement(By.xpath(personalInformation)).click();
            String personalInformationTitle = "个人设置 个人信息";
            if (personalInformationTitle.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("personalInformation no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(个人信息) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(changePassword);
            driver.findElement(By.xpath(changePassword)).click();
            String changePasswordTitle = "个人设置 修改密码";
            if (changePasswordTitle.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("changePassword no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(个人设置 修改密码) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }


        try {
            accordingToWait(peopleCommonlyUsedWords);
            driver.findElement(By.xpath(peopleCommonlyUsedWords)).click();
            String peopleCommonlyUsedWordsTitle = "个人设置 个人常用话术";
            if (peopleCommonlyUsedWordsTitle.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("peopleCommonlyUsedWords no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(个人设置 个人常用话术) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }



        try {
            accordingToWait(chatSettings);
            driver.findElement(By.xpath(chatSettings)).click();
            String chatSettingsTitle = "个人设置 聊天设置";
            if (chatSettingsTitle.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("chatSettings no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(个人设置 聊天设置) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }



        try {
            accordingToWait(enterprisesSetUp);
            driver.findElement(By.xpath(enterprisesSetUp)).click();
            Thread.sleep(1000);
        }catch (Exception e){


        }


        try {
            accordingToWait(enterprisesInformation);
            driver.findElement(By.xpath(enterprisesInformation)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("enterprisesInformation no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(企业信息) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }


        try {
            accordingToWait(webWidget);
            driver.findElement(By.xpath(webWidget)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("webWidget no pass");
            }

        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(Web聊窗) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }


        try {
            accordingToWait(consultingTheReceptionManagementPage);
            driver.findElement(By.xpath(consultingTheReceptionManagementPage)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("consultingTheReceptionManagementPage no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(咨询接待页签管理) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }


        try {
            accordingToWait(aUnifiedCustomerService);
            driver.findElement(By.xpath(aUnifiedCustomerService)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("aUnifiedCustomerService no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(统一客服) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }

        try {
            accordingToWait(enterprisesCommonlyUsedWords);
            driver.findElement(By.xpath(enterprisesCommonlyUsedWords)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("enterprisesCommonlyUsedWords no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(企业常用话术) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }



        try {
            accordingToWait(consultingType);
            driver.findElement(By.xpath(consultingType)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("consultingType no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(咨询类型) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }



        try {
            accordingToWait(consultingEvaluation);
            driver.findElement(By.xpath(consultingEvaluation)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("consultingEvaluation no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(咨询评价) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }


        try {
            accordingToWait(leaveWords);
            driver.findElement(By.xpath(leaveWords)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("leaveWords no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(留言) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }



        try {
            accordingToWait(leaveWordsMessageSignature);
            driver.findElement(By.xpath(leaveWordsMessageSignature)).click();
            String leaveWordsMessageSignatureTitle = "留言回复签名";
            if (leaveWordsMessageSignatureTitle.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("leaveWordsMessageSignature no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(留言回复签名) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }

//
//        driver.findElement(By.xpath(".//*[@class='s_left_cont']/ul[3]"));
//        ((JavascriptExecutor) driver).executeScript("document.getElementById('enterprise').scrollTop = 10000");
//        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000)");
//        Thread.sleep(3000);





        try {
            accordingToWait(receptionTime);
            driver.findElement(By.xpath(receptionTime)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("receptionTime no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(接待时间) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }


        try {
            accordingToWait(qualityControl);
            driver.findElement(By.xpath(qualityControl)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("qualityControl no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(质检) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }

        try {
            accordingToWait(expressionManagement);
            driver.findElement(By.xpath(expressionManagement)).click();
            String expressionManagementTitle = driver.getTitle();
            if (expressionManagementTitle.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("expressionManagement no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(表情管理) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }


        try {
            accordingToWait(commodityInformationSet);
            driver.findElement(By.xpath(commodityInformationSet)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("commodityInformationSet no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(商品信息设置) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }


        try {
            accordingToWait(collaborativeSessionSettings);
            driver.findElement(By.xpath(collaborativeSessionSettings)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("collaborativeSessionSettings no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(协同会话设置) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }


        try {
            accordingToWait(platformManagement);
            driver.findElement(By.xpath(platformManagement)).click();
            Thread.sleep(1000);
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(平台管理) 404or502 or null ");
            }

            ((JavascriptExecutor) driver).executeScript("history.back()");
        }


       try {
           accordingToWait(platformAccountManagement);
           driver.findElement(By.xpath(platformAccountManagement)).click();
           String platformAccountManagementTitle = "平台帐号管理";
           if (platformAccountManagementTitle.equals(driver.getTitle())) {
               logger("pass");
           } else {
               logger("platformAccountManagement no pass");
           }
       }catch (Exception e){
           if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
               logger(siteid + "PlatformSettingSmkoing(平台帐号管理) 404or502 or null ");
           }
           ((JavascriptExecutor) driver).executeScript("history.back()");
       }



        try {
            accordingToWait(merchantAccountManagement);
            driver.findElement(By.xpath(merchantAccountManagement)).click();
            String merchantAccountManagementTitle = "平台管理";
            if (merchantAccountManagementTitle.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("merchantAccountManagement no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(商户帐号管理) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }


        try {
            accordingToWait(consultingTheBinding);
            driver.findElement(By.xpath(consultingTheBinding)).click();
            String consultingTheBindingTitle = "平台管理";
            if (consultingTheBindingTitle.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("consultingTheBinding no pass");
            }
        }catch (Exception  e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(咨询绑定) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }

       try {
           accordingToWait(platformConfiguration);
           driver.findElement(By.xpath(platformConfiguration)).click();
           String platformConfigurationTitle = "平台设置";
           if (platformConfigurationTitle.equals(driver.getTitle())) {
               logger("pass");
           } else {
               logger("platformConfiguration no pass");
           }
       }catch (Exception  e){
           if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
               logger(siteid + "PlatformSettingSmkoing(平台设置) 404or502 or null ");
           }
           ((JavascriptExecutor) driver).executeScript("history.back()");
       }


        try {
            accordingToWait(configurationManagement);
            driver.findElement(By.xpath(configurationManagement)).click();
            Thread.sleep(1000);
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(配置管理) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }



        try {
            accordingToWait(receptionGroup);
            driver.findElement(By.xpath(receptionGroup)).click();
            String receptionGroupTitle = "接待组";
            if (receptionGroupTitle.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("receptionGroup no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(接待组) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
//
//        accordingToWait(callCenter);
//        driver.findElement(By.xpath(callCenter)).click();
//        Thread.sleep(1000);
//
//        driver.findElement(By.xpath(telephoneAtMonitoring)).click();
//        String   telephoneAtMonitoringAnswer   ="电话坐席监控";
//        if (telephoneAtMonitoringAnswer.equals(driver.getTitle())){logger("pass");}
//        else {logger("telephoneAtMonitoring no pass");}
//
//        driver.findElement(By.xpath(telephoneBill)).click();
//        String  telephoneBillAnswer  ="电话配置";
//        if (telephoneBillAnswer.equals(driver.getTitle())){logger("pass");}
//        else {logger("telephoneBill no pass");}
//
//
//        driver.findElement(By.xpath(qualityControlSet)).click();
//        String  qualityControlSetAnswer = "电话质检";
//        if (qualityControlSetAnswer.equals(driver.getTitle())){logger("pass");}
//        else {logger("qualityControlSet no pass");}


       try {
           accordingToWait(theKeyPages);
           driver.findElement(By.xpath(theKeyPages)).click();
           String theKeyPagesAnswer = "关键页面";
           if (theKeyPagesAnswer.equals(driver.getTitle())) {
               logger("pass");
           } else {
               logger("theKeyPages no pass");
           }
       }catch (Exception e){
           if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
               logger(siteid + "PlatformSettingSmkoing(关键页面) 404or502 or null ");
           }
           ((JavascriptExecutor) driver).executeScript("history.back()");
       }


       try {
           accordingToWait(automaticAnswer);
           driver.findElement(By.xpath(automaticAnswer)).click();
           String automaticAnswer1 = "自动应答";
           if (automaticAnswer1.equals(driver.getTitle())) {
               logger("pass");
           } else {
               logger("automaticAnswer no pass");
           }
       }catch (Exception e){
           if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
               logger(siteid + "PlatformSettingSmkoing(自动应答) 404or502 or null ");
           }
           ((JavascriptExecutor) driver).executeScript("history.back()");
       }



        try {
            accordingToWait(blackList);
            driver.findElement(By.xpath(blackList)).click();
            if (answer.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("blackList no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(黑名单) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }


        try {
            accordingToWait(giveNotice);
            driver.findElement(By.xpath(giveNotice)).click();
            String giveNoticeTitle = "通知";
            if (giveNoticeTitle.equals(driver.getTitle())) {
                logger("pass");
            } else {
                logger("giveNotice no pass");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "PlatformSettingSmkoing(通知) 404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }


}




