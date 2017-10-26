package ntest.pageObject.ActivityRecord;

import ntest.pageObject.BasePageObject;
import ntest.pageObject.SeleniumUtilInterface;
import org.apache.bcel.generic.IF_ACMPEQ;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * Created by Administrator on 2017/5/3.
 */
public class ActivitySmokingTestCase extends BasePageObject {


    public ActivitySmokingTestCase(String url,String siteid,String savePath) {
        if (url !=null && savePath !=null && siteid !=null) {
            this.url = url;
            this.savePath =url;
            this.siteid = siteid;
            fetchPageObject(this);
        }
    }
    private static Logger testResultLog = Logger.getLogger("TestResult");

    /*整体统计*/
    String answer = "Ntalker_互动记录";
    String theOverallStatistical = ".//a[contains(text(),'整体统计')]";
    /*有效咨询*/
    String effectiveConsultation = ".//a[contains(text(),'有效咨询')]";
    String haveBoughtConsulting = ".//a[contains(text(),'已购买咨询')]";
    String unpurchasedConsulting = ".//a[contains(text(),'未购买咨询')]";
    String hasSummarizedConsulting = ".//a[contains(text(),'已总结咨询')]";
    String notSummarizeConsulting = ".//a[contains(text(),'未总结咨询')]";
    String commodityLinkConsulting = ".//a[contains(text(),'商品环节咨询')]";
    String shoppingCartConsulting = ".//a[contains(text(),'购物车咨询')]";
    String consultingTheOrderPage = ".//a[contains(text(),'订单页咨询')]";
    String paymentPageConsulting = ".//a[contains(text(),'支付页咨询')]";
    String otherLinkConsulting = ".//a[contains(text(),'其他环节咨询')]";
    /*无效咨询*/
    String invalidConsulting = ".//a[contains(text(),'无效咨询')]";
    String visitorsNoAnswer = ".//a[contains(text(),'访客无应答')]";
    String customerNoAnswer = ".//a[contains(text(),'客服无应答')]";
    /*全部留言*/
    String allMessages = ".//a[contains(text(),'全部留言')]";
    String processed = ".//a[contains(text(),'已处理')]";
    String notProcessed=".//a[contains(text(),'未处理')]";
    /*电话记录*/
    String telephoneRecord=".//a[contains(text(),'电话记录')]";
    String answeringTheRecordOfIncomingCalls=".//a[contains(text(),'呼入接听记录')]";
    String notAnswerIncomingCalls=".//a[contains(text(),'呼入未接记录')]";
    String exhaleOnRecord=".//a[contains(text(),'呼出接听记录')]";
    String exhaleNotRecord=".//a[contains(text(),'呼出未接记录')]";


    String  colleaguesSessionRecord=".//a[contains(text(),'同事会话记录')]";
    String  exportManagement=".//a[contains(text(),'导出管理')]";
    String  commonlyUsedSearch=".//a[contains(text(),'导出管理')]";




    public void theOverallStatisticalMethod() throws InterruptedException {
        try {
            accordingToWait(theOverallStatistical);
            driver.findElement(By.xpath(theOverallStatistical)).click();
            joinTheParameters();
            String title = driver.getTitle();
            if (answer.equals(title)) {
                logger(siteid+"互动记录-整体统计 :pass");
            } else {
                logger("theOverallStatisticalMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase theOverallStatisticalMethod(整体统计) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }



    public void effectiveConsultationMethod() throws  InterruptedException {
        try {
            accordingToWait(effectiveConsultation);
            driver.findElement(By.xpath(effectiveConsultation)).click();
            joinTheParameters();
            String title = driver.getTitle();
            if (answer.equals(title)) {
                logger(siteid+"互动记录-有效咨询 :pass");
            } else {
                logger("effectiveConsultationMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase effectiveConsultation(有效咨询) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public void haveBoughtConsultingMethod()throws InterruptedException {
        try {
            driver.findElement(By.xpath(haveBoughtConsulting)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(haveBoughtConsulting)).getAttribute("class"))) {
                logger(siteid+"互动记录-已购买咨询 :pass");
            } else {
                logger("haveBoughtConsultingMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase haveBoughtConsulting(已购买咨询) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public void unpurchasedConsultingMethod() throws InterruptedException {
        try {
            accordingToWait(unpurchasedConsulting);
            driver.findElement(By.xpath(unpurchasedConsulting)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(unpurchasedConsulting)).getAttribute("class"))) {
                logger(siteid+"互动记录-未购买咨询 :pass");
            } else {
                logger("unpurchasedConsultingMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase unpurchasedConsulting  (未购买咨询) 404or502or null ");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public  void hasSummarizedConsultingMethod() throws InterruptedException {
        try {
            accordingToWait(hasSummarizedConsulting);
            driver.findElement(By.xpath(hasSummarizedConsulting)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(hasSummarizedConsulting)).getAttribute("class"))) {
                logger(siteid+"互动记录-已总结咨询 :pass");
            } else {
                logger("hasSummarizedConsultingMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase hasSummarizedConsulting(已总结咨询) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }


    public void  notSummarizeConsultingMethod() throws InterruptedException {
        try {
            accordingToWait(notSummarizeConsulting);
            driver.findElement(By.xpath(notSummarizeConsulting)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(notSummarizeConsulting)).getAttribute("class"))) {
                logger(siteid+"互动记录-未总结咨询 :pass");
            } else {
                logger("notSummarizeConsultingMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase notSummarizeConsulting(未总结咨询) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public  void  commodityLinkConsultingMethod() throws  InterruptedException {
        try {
            accordingToWait(commodityLinkConsulting);
            driver.findElement(By.xpath(commodityLinkConsulting)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(commodityLinkConsulting)).getAttribute("class"))) {
                logger(siteid+"互动记录-商品环节咨询 :pass");
            } else {
                logger("commodityLinkConsultingMethod  commodityLinkConsulting()404or502or null");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase commodityLinkConsulting(商品环节咨询) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }
    public  void shoppingCartConsultingMethod() throws  InterruptedException {
        try {
            accordingToWait(shoppingCartConsulting);
            driver.findElement(By.xpath(shoppingCartConsulting)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(shoppingCartConsulting)).getAttribute("class"))) {
                logger(siteid+"互动记录-购物车咨询 :pass");
            } else {
                logger("shoppingCartConsultingMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase shoppingCartConsulting(购物车咨询)404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }


    public void  consultingTheOrderPageMethod() throws InterruptedException {
        try {
            accordingToWait(consultingTheOrderPage);
            driver.findElement(By.xpath(consultingTheOrderPage)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(consultingTheOrderPage)).getAttribute("class"))) {
                logger(siteid+"互动记录-订单页咨询 :pass");
            } else {
                logger("consultingTheOrderPageMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase consultingTheOrderPage(订单页咨询) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }



    public  void paymentPageConsultingMethod () throws  InterruptedException {
        try {
            accordingToWait(paymentPageConsulting);
            driver.findElement(By.xpath(paymentPageConsulting)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(paymentPageConsulting)).getAttribute("class"))) {
                logger(siteid+"互动记录-支付页咨询 :pass");
            } else {
                logger("paymentPageConsultingMethod");
            }

        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase paymentPageConsulting(支付页咨询) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public void  otherLinkConsultingMethod() throws InterruptedException {
        try {
            accordingToWait(otherLinkConsulting);
            driver.findElement(By.xpath(otherLinkConsulting)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(otherLinkConsulting)).getAttribute("class"))) {
                logger(siteid+"互动记录-其他环节咨询 :pass");
            } else {
                logger("otherLinkConsultingMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase otherLinkConsulting(其他环节咨询) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }
    public  void invalidConsultingMethod() throws  InterruptedException {
        try {
            accordingToWait(invalidConsulting);
            driver.findElement(By.xpath(invalidConsulting)).click();
            joinTheParameters();
            String title = driver.getTitle();
            if (answer.equals(title)) {
                logger(siteid+"互动记录-无效咨询 :pass");
            } else {
                logger("invalidConsultingMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase invalidConsulting(无效咨询) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public void visitorsNoAnswerMethod()throws  InterruptedException {
        try {
            accordingToWait(visitorsNoAnswer);
            driver.findElement(By.xpath(visitorsNoAnswer)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(visitorsNoAnswer)).getAttribute("class"))) {
                logger(siteid+"互动记录-访客无应答 :pass");
            } else {
                logger("visitorsNoAnswerMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase visitorsNoAnswer(访客无应答) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }
    public  void  customerNoAnswerMethod() throws InterruptedException{
        try{
            accordingToWait(customerNoAnswer);
            driver.findElement(By.xpath(customerNoAnswer)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(customerNoAnswer)).getAttribute("class"))){logger(siteid+"互动记录-客服无应答 :pass");}
            else {logger("customerNoAnswerMethod");}
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase customerNoAnswer(客服无应答) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }


    public void  allMessagesMethod() throws InterruptedException{
        accordingToWait(allMessages);
        driver.findElement(By.xpath(allMessages)).click();
//        joinTheParameters();
//        String title = driver.getTitle();
//        if (answer.equals(title)){logger("pass");}
//        else {logger("allMessagesMethod");}
        Thread.sleep(1000);
    }

    public  void processedMethod() throws  InterruptedException {
        try {
            accordingToWait(processed);
            driver.findElement(By.xpath(processed)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(processed)).getAttribute("class"))) {
                logger(siteid+"互动记录-已处理 :pass");
            } else {
                logger("processedMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase processed(已处理) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }


    public  void notProcessedMethod() throws  InterruptedException {
        try {
            accordingToWait(notProcessed);
            driver.findElement(By.xpath(notProcessed)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(notProcessed)).getAttribute("class"))) {
                logger(siteid+"互动记录-未处理 :pass");
            } else {
                logger("notProcessedMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase notprocessed(未处理) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public void telephoneRecordMethod() throws InterruptedException{
        try{
            accordingToWait(telephoneRecord);
            driver.findElement(By.xpath(telephoneRecord)).click();
            String title = driver.getTitle();
            if (answer.equals(title)){logger(siteid+"互动记录-电话记录 :pass");}
            else {logger("telephoneRecordMethod");}
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase telephoneRecord(电话记录) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }
    public void  answeringTheRecordOfIncomingCallsMethod() throws InterruptedException {
        try {
            accordingToWait(answeringTheRecordOfIncomingCalls);
            driver.findElement(By.xpath(answeringTheRecordOfIncomingCalls)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(answeringTheRecordOfIncomingCalls)).getAttribute("class"))) {
                logger(siteid+"互动记录-呼入接听记录 :pass");
            } else {
                logger("answeringTheRecordOfIncomingCallsMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase answeringTheRecordOfIncomingCalls(呼入接听记录) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }


    public  void  notAnswerIncomingCallsMethod() throws  InterruptedException {
        try {
            accordingToWait(notAnswerIncomingCalls);
            driver.findElement(By.xpath(notAnswerIncomingCalls)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(notAnswerIncomingCalls)).getAttribute("class"))) {
                logger(siteid+"互动记录-呼入未接记录 :pass");
            } else {
                logger("notAnswerIncomingCallsMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase notAnswerIncomingCalls(呼入未接记录) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }

    public  void exhaleOnRecordMethod() throws InterruptedException {
        try {
            accordingToWait(exhaleOnRecord);
            driver.findElement(By.xpath(exhaleOnRecord)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(exhaleOnRecord)).getAttribute("class"))) {
                logger(siteid+"互动记录-呼出接听记录 :pass");
            } else {
                logger("exhaleOnRecordMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase exhaleOnRecord(呼出接听记录) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }
    public  void exhaleNotRecordMethod() throws InterruptedException{
        try{
            accordingToWait(exhaleNotRecord);
            driver.findElement(By.xpath(exhaleNotRecord)).click();
            joinTheParameters();
            String answer = "f_whit";
            if (answer.equals(driver.findElement(By.xpath(exhaleNotRecord)).getAttribute("class"))){logger(siteid+"互动记录-呼出未接记录 :pass");}
            else {logger("exhaleNotRecordMethod");}
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase exhaleNotRecord(呼出未接记录) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }


    public  void colleaguesSessionRecordMethod() throws InterruptedException{
        try {
            accordingToWait(colleaguesSessionRecord);
            driver.findElement(By.xpath(colleaguesSessionRecord)).click();
            joinTheParameters();
            String title = driver.getTitle();
            if (answer.equals(title)) {
                logger(siteid+"互动记录-同事会话记录 :pass");
            } else {
                logger("colleaguesSessionRecordMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase colleaguesSessionRecord(同事会话记录) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }


    public  void exportManagementMethod() throws InterruptedException {
        try {
            accordingToWait(exportManagement);
            driver.findElement(By.xpath(exportManagement)).click();
            joinTheParameters();
            Thread.sleep(1000);
            String title = driver.getTitle();
            if (answer.equals(title)){
                logger(siteid+"互动记录-导出管理 :pass");
            }else {
                logger(siteid+"exportManagementMethod");
            }
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase exportManagement(导出管理) 404or502or null");}
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }
    public  void commonlyUsedSearchMethod() throws InterruptedException{
        try{
            accordingToWait(commonlyUsedSearch);
            driver.findElement(By.xpath(commonlyUsedSearch)).click();
            joinTheParameters();
            Thread.sleep(1000);
            String title = driver.getTitle();
            if (answer.equals(title)){
                logger(siteid+"互动记录-常用搜索:pass");
            }else
            {logger(siteid+"commonlyUsedSearchMethod");}
        }catch (Exception e){
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger(siteid+"ActivitySmokingTestCase commonlyUsedSearch(常用搜索)404or502or null");}

        }
    }

}
