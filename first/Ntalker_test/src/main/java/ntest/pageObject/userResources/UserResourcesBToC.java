package ntest.pageObject.userResources;

import ntest.pageObject.BasePageObject;
import ntest.pageObject.KPIModule.SecondDriver;
import ntest.util.SeleniumUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
public class UserResourcesBToC extends BasePageObject {
    //我的资源左侧链接
    @FindBy(xpath = ".//div[@class='cj menu_today']")
    public WebElement todayAddtionLink;//今日新增
    @FindBy(xpath = ".//div[@class='cj menu_all']")
    public WebElement allPageLink;//全部
    @FindBy(xpath = ".//div[@class='cj menu_currentservicer']")
    public WebElement myLink;// 分配给我的
    @FindBy(xpath = ".//div[@class='cj menu_finished']")
    public WebElement finishedLink;//已完成的
    @FindBy(xpath = ".//div[@class='cj menu_invalid']")
    public WebElement invalidLink;//无效的
    @FindBy(xpath = ".//div[@class='cj menu_servicerid']")
    public WebElement createdByMeLink;//我创建的
    @FindBy(xpath = ".//div[@class='cj menu_unappropriated']")
    public WebElement unappropriatedLink;//未分配的
    //添加用户
    @FindBy(xpath = ".//div[@class='yxs']/div[@class='site' and contains(text(),'添加')]")
    public WebElement addUserBtn;
    @FindBy(xpath = ".//*[@id='u_registername']")
    public WebElement userRegisterNameInput;
    @FindBy(xpath = ".//*[@id='u_mobilephone']")
    public WebElement mobilePhoneInput;
    @FindBy(xpath = ".//*[@id='u_name']")
    public WebElement userNameInput;
    @FindBy(xpath = ".//*[@id='ok_button']")
    public WebElement okBtn;
    @FindBy(xpath = ".//iframe[@id='add_crm']")
    public WebElement formIframe;
    //导入导出数据
    @FindBy(xpath = " .//div[@class='site' and contains(text(),'导入')]")
    public WebElement importDataBtn;
    @FindBy(xpath = ".//*[@id='scan']")
    public WebElement scanFileBtn;
    @FindBy(xpath = ".//dt/p/a[contains(text(),'下载格式规范')]")
    public WebElement sampleFileLink;
    @FindBy(xpath = ".//a[@class='lq' and contains(text(),'导 入')]")
    public WebElement importCSVFileBtn;
    @FindBy(xpath = ".//a[@class='qx' and contains(text(),'返　回')]")
    public WebElement importCSVThenBackBtn;

    public void uploadFileHandle () throws Exception {
        //上传
        importDataBtn.click();
//        SeleniumUtil.userInfoCSVUploadFile();
        scanFileBtn.click();
        importCSVFileBtn.click();
        importCSVThenBackBtn.click();
    }

    public boolean downloadFileHandle()  throws Exception{
        //下载
        String importDataBtn  = " .//div[@class='site' and contains(text(),'导入')]";
        String sampleFileLink = ".//dt/p/a[contains(text(),'下载格式规范')]";
        if(!clickElement(importDataBtn)||!clickElement(sampleFileLink)) return false;
        Thread.sleep(1000);
        String downloadUrl = SeleniumUtil.handleAlertText(driver);
        System.out.println(downloadUrl);
        SecondDriver secondDriver = new SecondDriver(downloadUrl,savePath,siteid);
        secondDriver.load();
        secondDriver.isLoaded();
        Thread.sleep(3000);
        secondDriver.quit();
        return true;
    }

    public void addNewUser (String registName, String phoneNum, String userName) throws InterruptedException {
        if (registName == null || phoneNum == null || userName == null){System.out.println("User input error!");return;}
        allPageLink.click();
        Thread.sleep(2000);
        addUserBtn.click();
        Thread.sleep(2000);
        driver.switchTo().frame(formIframe);
        userRegisterNameInput.sendKeys(registName);
        Thread.sleep(1000);
        mobilePhoneInput.sendKeys(phoneNum);
        Thread.sleep(1000);
        userNameInput.sendKeys(userName);
        okBtn.click();
        Thread.sleep(1000);
        allPageLink.click();
        Thread.sleep(2000);
        //查看用户
        List<WebElement> userList = driver.findElements(By.xpath(".//div[@id='mCSB_1_container']/li/div/p/a[@class='cq czBtn']"));
        if (userList.size() > 0) {
            System.out.println(userList.get(0).getAttribute("title"));
            userList.get(0).click();
        }
        Thread.sleep(2000);
        driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@id='look_info']")));
        String dd= driver.findElement(By.xpath(".//input[@id='u_name']")).getAttribute("value");
        if (dd.equals(userName)) {
            System.out.println("通过");
        }else {
            System.out.println("未通过");
        }
    }

    public void userAssign() throws InterruptedException {
        allPageLink.click();
        Thread.sleep(2000);
        List<WebElement> assignList = driver.findElements(By.xpath(".//div[@id='mCSB_1_container']/li/div/p/a[@class='fp czBtn']"));
        if (assignList.size() > 0) {
            System.out.println(assignList.get(0).getAttribute("title"));
            assignList.get(0).click();
        }
        WebElement assignListBtn = driver.findElement(By.xpath(".//input[@id='allotUserid']/following-sibling::dd/p[contains(text(),'分配给：')]/following-sibling::div"));
        assignListBtn.click();
        Thread.sleep(1000);
        WebElement listFrame = driver.findElement(By.xpath(".//div[@class='aui_loading']/following-sibling::iframe"));
        driver.switchTo().frame(listFrame);
        WebElement openList = driver.findElement(By.xpath(".//*[contains(text(),'自动化测试组')]/parent::a/preceding-sibling::span"));
        openList.click();
        Thread.sleep(1000);
        WebElement selectLink = driver.findElement(By.xpath(".//span[contains(text(),'nsp1')]"));
        selectLink.click();
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
        WebElement confirmBtn = driver.findElement(By.xpath(".//div[@id='div_allotInfo']/div[@class='bost']/div[@class='yi']/a[@class='bc']"));
        confirmBtn.click();Thread.sleep(1000);
        WebElement successBtn = driver.findElement(By.xpath(".//div[@class='custor_confirm']/div[@class='bost_a']/div/a[@class='bc']"));
        successBtn.click();
    }

    public void clickLinkTest() {
        allPageLink.click();
        todayAddtionLink.click();
        unappropriatedLink.click();
        myLink.click();
        finishedLink.click();
        invalidLink.click();
        createdByMeLink.click();
    }

    public UserResourcesBToC (String url) {
        this.url = url;
        fetchPageObject(this);
    }

    public UserResourcesBToC(String url, String savePath, String siteid) {
        if (url != null && savePath != null && siteid != null) {
            this.url = url;
            this.siteid = siteid;
            this.savePath = savePath;
            fetchPageObject(this);
        }
    }
}
