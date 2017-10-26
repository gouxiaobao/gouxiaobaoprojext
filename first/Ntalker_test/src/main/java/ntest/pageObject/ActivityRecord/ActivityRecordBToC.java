package ntest.pageObject.ActivityRecord;

import ntest.pageObject.BasePageObject;
import ntest.pageObject.KPIModule.SecondDriver;
import ntest.pageObject.SeleniumUtilInterface;
import ntest.util.SeleniumUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25 0025.
 */
public class ActivityRecordBToC extends BasePageObject {
    private static Logger testResultLog  =  Logger.getLogger("TestResult");
    String trueQueryLink = ".//a[contains(text(),'有效咨询')]"; //query link
    String listXpath = ".//*[@id='ngrid']/tbody/tr";// chat list
    String listFirstRowXpath = ".//*[@id='ngrid']/tbody/tr[1]";// first row of chat list
    String exportBtn = ".//*[@id='btn_search']";
    String downloadTitleInput = ".//*[@id='download_title']";
    String chooseAllCheckBox = ".//*[@id='qxcheckbox']";
    String exportConfirmBtn = ".//*[@id='download_button']";
    String exportManagementLink = ".//div[@class='s_left_cont']/div/a[contains(text(),'导出管理')]";
    String downloadBtn = ".//table[@class='table_s']/tbody/tr[2]/td/div[@text_val='下载']";
    String deleteBtn =  ".//table[@class='table_s']/tbody/tr[2]/td/div[@text_val='删除']";
    String confirmBtn = ".//*[@id='download_delect']/span/input[@class='but_50' and @value='是']";
    public ActivityRecordBToC(ActivityRecordBToCModule module) {
        if (module.url != null && module.savePath != null && module.siteid != null) {
            this.url = module.url;
            this.siteid = module.siteid;
            this.savePath = module.savePath;
            fetchPageObject(this);
        }
    }

    public ActivityRecordBToC(String url, String savePath, String siteid) {
        if (url != null && savePath != null && siteid != null) {
            this.url = url;
            this.siteid = siteid;
            this.savePath = savePath;
            fetchPageObject(this);
        }
    }
    //导出管理
    public boolean downloadAllRecord(String exportName, String downloadPath, String exeFilePath) throws Exception {
        /*导出文件*/
        if (!clickElement(trueQueryLink)
                ||!clickElement(exportBtn)
                ||!sendKeyElement(downloadTitleInput,exportName)
                ||!clickElement(chooseAllCheckBox)
                ||!clickElement(exportConfirmBtn)
                ||!clickElement(exportManagementLink))
            return false;
        /*下载文件*/
        if (!clickElement(downloadBtn)) return false;
        String secondUrl = SeleniumUtil.handleAlertText(driver);
        if (secondUrl == null) return false;
        SecondDriver secondDriver = new SecondDriver(secondUrl);
        secondDriver.load();
        secondDriver.isLoaded();
        int count1 = SeleniumUtil.fileArray(downloadPath).length;
        secondDriver.downloadFile("d:\\downloadFile.exe");
        /*删除文件*/
        if(!clickElement(deleteBtn)) return false;
        if (!clickElement(confirmBtn)) return false;
        driver.navigate().refresh();
        Thread.sleep(60000);
//        checkFilesNum(count1,downloadPath);
        return true;
    }
    public void sessionInfoHandle() throws InterruptedException {
        fetchFirstRow(trueQueryLink);
        WebElement element = driver.findElement(By.xpath(".//*[@id='scene_info_li']"));
        element.click();
    }
    public void userTraceHandle() throws InterruptedException {
        fetchFirstRow(trueQueryLink);
        WebElement element = driver.findElement(By.xpath(".//*[@id='trail_info_li']"));
        element.click();
    }
    public boolean fetchFirstRow(String xpathStr) throws InterruptedException {
        if(!clickElement(xpathStr)) return false;
        List<WebElement> elementsArr = driver.findElements(By.xpath(listXpath));
        if(elementsArr.isEmpty() || elementsArr.size()==0 || elementsArr == null) return false;
        if(!clickElement(listFirstRowXpath)) return false;
        return true;
    }
    public void userInfoHandle() throws InterruptedException {
        fetchFirstRow(trueQueryLink);
        WebElement element = driver.findElement(By.xpath(".//*[@id='custom_info_li']"));
        element.click();
        WebElement userName = driver.findElement(By.xpath(".//td[contains(.,'客人：')]/span"));
        userName.getText();
    }
    public boolean downloadChatLog (String filePath,String exeFilePath) throws Exception {
        if(!fetchFirstRow(trueQueryLink)) return false;
        if(!clickElement(".//*[@id='button3']")) return false;
        String alertString = SeleniumUtil.handleAlertText(driver);
        if (alertString == null) return false;
        ActivityRecordBToCModule module = new ActivityRecordBToCModule();
        module.url = url;
        module.siteid = siteid;
        module.savePath =savePath;
        SecondDriver newDriver = new SecondDriver(module);
        if (newDriver == null) return false;
        newDriver.load();
        newDriver.isLoaded();
//        int count1 = SeleniumUtil.fileArray(filePath).length;
//        newDriver.downloadFile(exeFilePath);
        Thread.sleep(60000);
//        checkFilesNum(count1,filePath);
        return true;
    }
    /* 校验文件个数是否符合逻辑*/
    public void checkFilesNum (int numBefore,String downloadPath) {
        if (numBefore == (SeleniumUtil.fileArray(downloadPath).length-1)) {
            System.out.println("下载成功");
        }else {
            System.out.println("下载失败");
        }
    }
}
