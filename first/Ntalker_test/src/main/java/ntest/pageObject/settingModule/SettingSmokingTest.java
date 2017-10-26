package ntest.pageObject.settingModule;

import ntest.pageObject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Created by Administrator on 2017/5/10.
 */
public class SettingSmokingTest extends BasePageObject {
    /*个人设置*/
    String personalInformation = ".//a[text()='个人信息']";
    String changePassword = ".//a[text()='修改密码']";
    String peopleCommonlyUsedWords = ".//a[text()='个人常用话术']";
    String chatSettings = ".//a[text()='聊天设置']";
    /*企业设置*/
    String enterprisesSetUp = ".//a[text()='企业设置']";
    String enterprisesInformation = ".//a[text()='企业信息']";
    String webWidget = ".//a[text()='Web聊窗']";
    String consultingTheReceptionManagementPage = ".//a[text()='咨询接待页签管理']";
    String weiboDocking = ".//a[text()='微博对接']";
    String wapWidget = ".//a[text()='Wap聊窗']";
    String aUnifiedCustomerService = ".//a[text()='统一客服']";
    String enterprisesCommonlyUsedWords = ".//a[text()='企业常用话术']";
    String consultingType = ".//a[text()='咨询类型']";
    String consultingEvaluation = ".//a[text()='咨询评价']";
    String leaveWords = ".//a[text()='留言']";
    /*帐户管理*/
    String accountManagement = ".//a[text()='帐户管理']";
    /*配置管理*/
    String configurationManagement = ".//a[text()='配置管理']";
    String receptionGroup = ".//a[text()='接待组']";
    String interfaceSettings = ".//a[text()='界面设置']";
    /*呼叫中心*/
    String callCenter = ".//a[text()='呼叫中心']";
    String telephoneAtMonitoring = ".//a[text()='电话坐席监控']";
    String telephoneBill = ".//a[text()='电话账单']";
    String qualityControlSet = ".//a[text()='质检设置']";
    /*关键页面*/
    String theKeyPages = ".//a[text()='关键页面']";
    /*自动应答*/
    String automaticAnswer = ".//a[text()='自动应答']";
    /*黑名单*/
    String blackList = ".//a[text()='黑名单']";
    /*通知*/
    String giveNotice = ".//a[text()='通知']";

    String answer = "企业设置";
    private static Logger testResultLog = Logger.getLogger("TestResult");


    public SettingSmokingTest(String url, String siteid,String savePath) {
        this.url = url;
        this.siteid = siteid;
        this.savePath = savePath;
        fetchPageObject(this);
    }

    public void personalJump() throws InterruptedException {

        try {
            accordingToWait(personalInformation);
            driver.findElement(By.xpath(personalInformation)).click();
            String personalInformationAnswer = "个人设置 个人信息";
            if (personalInformationAnswer.equals(driver.getTitle())) {
                logger(siteid + ":设置-个人设置 个人信息 pass");
            } else {
                logger(personalInformationAnswer);
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null))
                logger(siteid + "SettingSmokingTest(个人设置 个人信息) 404or502 or null ");
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(changePassword);
            driver.findElement(By.xpath(changePassword)).click();
            String changePasswordAnswer = "个人设置 修改密码";
            if (changePasswordAnswer.equals(driver.getTitle())) {
                logger(siteid + ":设置-个人设置 修改密码 pass");
            } else {
                logger(changePasswordAnswer + "no nass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(个人设置 修改密码)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(peopleCommonlyUsedWords);
            driver.findElement(By.xpath(peopleCommonlyUsedWords)).click();
            String peopleCommonlyUsedWordsAnswer = "个人设置 个人常用话术";
            if (peopleCommonlyUsedWordsAnswer.equals(driver.getTitle())) {
                logger(siteid + ":设置-个人设置 个人常用话术 pass");
            } else {
                logger(peopleCommonlyUsedWordsAnswer + "no  pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(个人设置 个人常用话术)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }
        try {
            accordingToWait(chatSettings);
            driver.findElement(By.xpath(chatSettings)).click();
            String chatSettingsAnswer = "个人设置 聊天设置";
            if (chatSettingsAnswer.equals(driver.getTitle())) {
                logger(siteid + ":设置-个人设置 聊天设置 pass");
            } else {
                logger(chatSettingsAnswer + "no  pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(个人设置 聊天设置)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        accordingToWait(enterprisesSetUp);
        driver.findElement(By.xpath(enterprisesSetUp)).click();
        Thread.sleep(1000);



        try {
            accordingToWait(enterprisesInformation);
            driver.findElement(By.xpath(enterprisesInformation)).click();
            if (answer.equals(driver.getTitle())) {
                logger(siteid + ":设置-企业信息 pass");
            } else {
                logger("enterprisesInformation（企业信息） no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(企业信息)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(webWidget);
            driver.findElement(By.xpath(webWidget)).click();
            if (answer.equals(driver.getTitle())) {
                logger(siteid + ":设置-web聊窗 pass");
            } else {
                logger("webWidget no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(WEB聊窗)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(consultingTheReceptionManagementPage);
            driver.findElement(By.xpath(consultingTheReceptionManagementPage)).click();
            if (answer.equals(driver.getTitle())) {
                logger(siteid + ":设置-咨询接待页签管理 pass");
            } else {
                logger("consultingTheReceptionManagementPage no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(咨询接待页签管理)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }


        try {
            accordingToWait(weiboDocking);
            driver.findElement(By.xpath(weiboDocking)).click();
            if (answer.equals(driver.getTitle())) {
                logger(siteid + ":设置-微博对接 pass");
            } else {
                logger("weiboDocking no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(微博对接)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }


        try {
            accordingToWait(wapWidget);
            driver.findElement(By.xpath(wapWidget)).click();
            String wapWidgetAnswer = "Wap聊窗";
            if (wapWidgetAnswer.equals(driver.getTitle())) {
                logger(siteid+":设置-wap聊窗 pass");
            } else {
                logger("apWidget no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(Wap聊窗)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(aUnifiedCustomerService);
            driver.findElement(By.xpath(aUnifiedCustomerService)).click();
            if (answer.equals(driver.getTitle())) {
                logger(siteid+":设置-统一客服 pass");
            } else {
                logger("aUnifiedCustomerService no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(统一客服)404or502 or null ");

            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }


        try {
            accordingToWait(enterprisesCommonlyUsedWords);
            driver.findElement(By.xpath(enterprisesCommonlyUsedWords)).click();
            if (answer.equals(driver.getTitle())) {
                logger(siteid+":设置-企业常用话术 pass");
            } else {
                logger("enterprisesCommonlyUsedWords no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(企业常用话术)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(consultingType);
            driver.findElement(By.xpath(consultingType)).click();
            if (answer.equals(driver.getTitle())) {
                logger(siteid+":设置-咨询类型 pass");
            } else {
                logger("consultingType no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(咨询类型)404or502 or null ");

            }

            ((JavascriptExecutor) driver).executeScript("history.back()");

        }


        try {
            accordingToWait(consultingEvaluation);
            driver.findElement(By.xpath(consultingEvaluation)).click();
            if (answer.equals(driver.getTitle())) {
                logger(siteid+":设置-咨询评价 pass");
            } else {
                logger("consultingEvaluation no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(咨询评价)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(leaveWords);
            driver.findElement(By.xpath(leaveWords)).click();
            if (answer.equals(driver.getTitle())) {
                logger(siteid+":设置-留言 pass");
            } else {
                logger("leaveWords no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(留言)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");


        }


        try {
            accordingToWait(accountManagement);
            driver.findElement(By.xpath(accountManagement)).click();
            String accountManagementAnswer = "账户管理";
            if (accountManagementAnswer.equals(driver.getTitle())) {
                logger(siteid+":设置-账户管理 pass");
            } else {
                logger("accountManagement no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(账户管理)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        accordingToWait(configurationManagement);
        driver.findElement(By.xpath(configurationManagement)).click();
        Thread.sleep(1000);


        try {
            accordingToWait(receptionGroup);
            driver.findElement(By.xpath(receptionGroup)).click();
            String receptionGroupAnswer = "接待组";
            if (receptionGroupAnswer.equals(driver.getTitle())) {
                logger(siteid+":设置-接待组 pass");
            } else {
                logger("receptionGroup no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(接待组)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(interfaceSettings);
            driver.findElement(By.xpath(interfaceSettings)).click();
            String interfaceSettingsAnswer = "界面设置";
            if (interfaceSettingsAnswer.equals(driver.getTitle())) {
                logger(siteid+":设置-界面设置 pass");
            } else {
                logger("interfaceSettings no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(界面设置)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        accordingToWait(callCenter);
        driver.findElement(By.xpath(callCenter)).click();
        Thread.sleep(1000);

        try {
            accordingToWait(telephoneAtMonitoring);
            driver.findElement(By.xpath(telephoneAtMonitoring)).click();
            String telephoneAtMonitoringAnswer = "电话坐席监控";
            if (telephoneAtMonitoringAnswer.equals(driver.getTitle())) {
                logger(siteid+":设置-电话坐席监控pass");
            } else {
                logger("telephoneAtMonitoring no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(电话坐席监控)404or502 or null ");

            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(telephoneBill);
            driver.findElement(By.xpath(telephoneBill)).click();
            String telephoneBillAnswer = "电话配置";
            if (telephoneBillAnswer.equals(driver.getTitle())) {
                logger(siteid+":设置-电话配置 pass");
            } else {
                logger("telephoneBill no pass");
            }

        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(电话配置)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }


        try {
            accordingToWait(qualityControlSet);
            driver.findElement(By.xpath(qualityControlSet)).click();
            String qualityControlSetAnswer = "电话质检";
            if (qualityControlSetAnswer.equals(driver.getTitle())) {
                logger(siteid+":设置-电话质检 pass");
            } else {
                logger("qualityControlSet no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(电话质检)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }


        try {
            accordingToWait(theKeyPages);
            driver.findElement(By.xpath(theKeyPages)).click();
            String theKeyPagesAnswer = "关键页面";
            if (theKeyPagesAnswer.equals(driver.getTitle())) {
                logger(siteid+":设置-关键页面 pass");
            } else {
                logger("theKeyPages no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(关键页面)404or502 or null ");

            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(automaticAnswer);
            driver.findElement(By.xpath(automaticAnswer)).click();
            String automaticAnswer1 = "自动应答";
            if (automaticAnswer1.equals(driver.getTitle())) {
                logger(siteid+":设置-自动应答 pass");
            } else {
                logger("automaticAnswer no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(自动应答)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }

        try {
            accordingToWait(blackList);
            driver.findElement(By.xpath(blackList)).click();
            if (answer.equals(driver.getTitle())) {
                logger(siteid+":设置-黑名单 pass");
            } else {
                logger("blackList no pass");
            }
        }catch (Exception  e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest(黑名单)404or502 or null ");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }


        try{
            accordingToWait(giveNotice);
            driver.findElement(By.xpath(giveNotice)).click();
            String giveNoticeAnswer = "通知";
            if (giveNoticeAnswer.equals(driver.getTitle())) {
                logger(siteid+":设置-通知 pass");
            } else {
                logger("giveNotice no pass");
            }
        } catch (Exception e) {
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid + "SettingSmokingTest (通知) 404or502or null");
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");

        }
    }
}

