package ntest.util;

import ntest.pageObject.SeleniumUtilInterface;
import ntest.pageObject.XNCallback;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class SeleniumUtil {
    private static Logger testResultLog  =  Logger.getLogger("TestResult");

    public final String FireFoxDownloadPath = "D:\\Downloads";
    public final String DownloadFileExePath = "d:\\downloadFile.exe";
    public final String UploadFileExePath = "d:\\uploadFile.exe";
    public final String UploadLogoPicExePath = "d:/uploadLogoPic.exe";
    public final String EnterpriseUsageUploadFile = "d:/enterpriseUsageUploadFile.exe";
    public final String UploadCSVFile ="d:/uploadCSVFile.exe";

    public static void main(String[] args) {
        /*递归遍历目录
        getFile(fireFoxDownloadPath);*/
        /*非递归遍历目录*/
        getFileAtPath( "D:\\Downloads");
    }

    //处理文件夹
    public static File[] fileArray (String path) {
        if (path == null) return null;
        File file = new File(path);
        return file.listFiles();
    }
    public static long getFileSize (String path) {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
//            testResultLog.info(WordsIndex.FILE_NOT_EXIST);
            return -1;
        }
        return file.length();
    }
    public static void getFileAtPath(String path) {
        for(int i=0;i<fileArray(path).length;i++) {
            if (fileArray(path)[i].isFile()) {
                System.out.println(fileArray(path)[i].getName());
            }
        }
    }

    public static boolean filesHandle(List arr, XNCallback action) {
        for (int i=0;i<arr.size();i++){

        }
        return false;
    }

    public static long getFileNumAtPath(String path) {
       return fileArray(path).length;
    }
    private static void getFile(String path){
        for(int i=0;i<fileArray(path).length;i++) {
            if (fileArray(path)[i].isFile()) {
                System.out.println(fileArray(path)[i].getName());
            }else if (fileArray(path)[i].isDirectory()) {
                getFile(fileArray(path)[i].getPath());
            }
        }
    }
    //桌面操作程序调用
    public static void exeHandle(String path) throws Exception {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
//            testResultLog.info(WordsIndex.FILE_NOT_EXIST);
            return;
        }
        Runtime.getRuntime().exec(path);
    }

    //提示框处理
    public static String handleAlertText(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            String text = alert.getText();
            alert.accept();
            return text;
        }catch (NoAlertPresentException exception) {
            exception.printStackTrace();
            return null;
        }
    }
    public static String fetchSettingURLWithToken(String getFlashServer, String siteid, String waiterName,String token) {
        // "D:\\Downloads"
       // http://downt.ntalker.com/index.php/personsetting/index?userid=aq_1000_ISME9754_T2D_ntalker_steven&token=05516e55de59b6ac7d6277aa35707d0a&disp=personal
       // http://nt-v1-setting.ntalker.com/index.php/personsetting/index?userid=kf_4607_ISME9754_T2D_nsp1&token=6a2de28fcb092e6c5f2909f1fd07aa2e&disp=personal
        return getFlashServer+"/index.php/personsetting/index?userid="+siteid+"_ISME9754_T2D_"+waiterName+"&token="+token+"&disp=personal";
    }
    public static  String fetchUserResourceURLWithToken (String getFlashServer, String siteid, String waiterName,String token) {
        return  getFlashServer+"/sticket/CrmUser/index?search=all&kfuserid="+siteid+"_ISME9754_T2D_"+waiterName+"&token="+token;
    }
    public static String fetchActivityRecordURLWithToken (String getFlashServer,String siteid, String waiterName, String token) {
        return getFlashServer+"/index.php?c=message&m=counts&actiontype=counts&userid="+siteid+"_ISME9754_T2D_"+waiterName+"&token="+token+"&httpdebug=air";
    }
    public static String fetchKPIURLWithToken (String getFlashServer,String siteid, String waiterName, String token) {
        return getFlashServer+"/index.php/summary/index?userid="+siteid+"_ISME9754_T2D_"+waiterName+"&siteid="+siteid+"&token="+token;
    }
    //页面元素状态判断
    //页面元素在页面中存在
    public static void isElementPresence(WebDriver driver,By by,int seconds) {
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public static void isElementPresence(WebDriver driver,By by) {
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public static boolean isExist(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        }catch (NoSuchElementException e) {
            return false;
        }
    }
    public static void clickElement(By elementBy, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
        driver.findElement(elementBy).click();
    }
    //页面元素处于被选中状态
    public static void isElementSelected(WebDriver driver, WebElement element, SeleniumUtilInterface obj) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.elementToBeSelected(element));
        obj.action(element);
    }
}