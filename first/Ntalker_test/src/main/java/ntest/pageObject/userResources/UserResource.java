package ntest.pageObject.userResources;

import ntest.pageObject.BasePageObject;
import ntest.util.SeleniumUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Created by Administrator on 2017/5/8.
 */
public class UserResource extends BasePageObject{
    /*左侧菜单栏*/
    String all = ".//*[@class='lefts']/ul/li[1]/div";
    String todayAdd=".//*[@class='lefts']/ul/li[2]/div";
    String unallocated=".//*[@class='lefts']/ul/li[3]/div";
    String assignedToMe=".//*[@class='lefts']/ul/li[4]/div";
    String completed=".//*[@class='lefts']/ul/li[5]/div";
    String noneffective=".//*[@class='lefts']/ul/li[6]/div";
    String iCreated=".//*[@class='lefts']/ul/li[7]/div";
    String commonlyUsedSearch=".//*[@class='lefts']/ul/li[8]/div";
    // String answer="background:#dcdcdc;";
    public   UserResource (String url, String siteid,String savePath) {
        if (url != null && siteid != null&& savePath !=null) {
            this.url = url;
            this.siteid = siteid;
            this.savePath=savePath;
            fetchPageObject(this);
        }
    }
    public void  pageJumpsMethod(String xpathStr)throws  InterruptedException {
        try {
            accordingToWait(xpathStr);
            WebElement element = driver.findElement(By.xpath(xpathStr));
            element.click();
            Thread.sleep(1000);
            WebElement element1 = driver.findElement(By.xpath(xpathStr));
            if (element1.getAttribute("style") != null) {
                logger(siteid+"用户资源"+"pass");
            } else {
                logger(siteid+"用户资源 no pass");
            }
        }catch (Exception  e){
            if (driver.getTitle().equals("404 Page Not Found") || driver.getTitle().contains("502") || driver.getTitle().equals(null)) {
                logger(siteid +"UserResource"+ xpathStr );
            }
            ((JavascriptExecutor) driver).executeScript("history.back()");
        }
    }
}


