package ntest.pageObject;

import ntest.pageObject.settingModule.EvaluationInterface;
import ntest.util.SeleniumUtil;
import org.apache.log4j.Logger;
import org.apache.log4j.or.ObjectRenderer;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/5 0005.
 */
public class BasePageObject  extends LoadableComponent<BasePageObject> {
    //private static String str ="11111";
    private static Logger testResultLog = Logger.getLogger("TestResult");
    public static int defaultWaitElementTime = 10;
    public String siteid;
    public String currentPath = System.getProperty("user.dir");
    public String savePath = currentPath + "\\src\\main\\java\\ntest\\other\\download\\" + siteid;
    // public String savePath;
    public WebDriver driver;
    public String url;

    public void fetchPageObject(BasePageObject obj) {
//        System.setProperty("webdriver.firefox.bin", "file:///Applications/Firefox.app");
        File fileSavePath = new File(savePath);
        if (!fileSavePath.exists()) {
            fileSavePath.mkdirs();
        } else {
//            for (File file :
//                    fileSavePath.listFiles()) {
//                file.delete();
//            }
        }
        ProfilesIni pi = new ProfilesIni();
        FirefoxProfile profile = pi.getProfile("default");
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", savePath);
        profile.setPreference(
                "browser.helperApps.neverAsk.saveToDisk",
                "application/zip,text/plain,application/vnd.ms-excel,text/csv,text/comma-separated-values,application/octet-stream,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        driver = new FirefoxDriver(profile);
//        driver = new FirefoxDriver();
        PageFactory.initElements(driver, obj);
    }

    public void quit() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void load() {
        driver.get(url);
    }

    @Override
    public void isLoaded() throws Error {
    }

    public void logger(Object obj) {
        System.out.println(obj);
        testResultLog.info(obj);
    }

    public boolean elementWaitAndHandle(By elementBy, SeleniumUtilInterface handle) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, defaultWaitElementTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
            if (handle != null) handle.action(driver.findElement(elementBy));
            return true;
        } catch (NoSuchElementException e) {
            logger(elementBy);
            return false;
        } catch (TimeoutException e) {
            logger(elementBy);
            return false;
        }
    }

    public boolean elementWaitAndHandle(String exp, SeleniumUtilInterface handle) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, defaultWaitElementTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(exp)));
            if (handle != null)
                handle.action(driver.findElement(By.xpath(exp)));
            return true;
        } catch (TimeoutException e) {
            logger(By.xpath(exp));
            return false;
        } catch (NoSuchElementException e) {
            logger(By.xpath(exp));
            return false;
        }
    }   /* public boolean NewelementWaitAndHandle(String exp,SeleniumUtilInterface handle) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver,defaultWaitElementTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(exp)));
            if (handle != null)
                handle.action(driver.findElement(By.xpath(exp)));
            return true;
        }catch (TimeoutException e){
            logger(By.xpath(exp));
            return false;
        }catch (NoSuchElementException e){
            logger(By.xpath(exp));
            return false;
        }
    }*/

    public Object elementWaitAndHandle(String exp, EvaluationInterface handle) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, defaultWaitElementTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(exp)));
            if (handle != null) return handle.action(driver.findElement(By.xpath(exp)));
            return null;
        } catch (TimeoutException e) {
            logger(By.xpath(exp));
            return null;
        } catch (NoSuchElementException e) {
            logger(By.xpath(exp));
            return false;
        }
    }

    public Object elementWaitAndHandle(String exp, XNCallback handle) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, defaultWaitElementTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(exp)));
            if (handle != null) return handle.action(driver.findElements(By.xpath(exp)));
            return null;
        } catch (TimeoutException e) {
            logger(By.xpath(exp));
            return null;
        } catch (NoSuchElementException e) {
            logger(By.xpath(exp));
            return null;
        } catch (NullPointerException e) {
            logger(By.xpath(exp));
            return null;
        } catch (ElementNotVisibleException e) {
            logger(By.xpath(exp));
            return null;
        }
    }

    public boolean sendKeyElement(String xpathStr, final String contextStr) throws InterruptedException {
        if (!elementWaitAndHandle(By.xpath(xpathStr), new SeleniumUtilInterface() {
            @Override
            public void action(WebElement element) {
                element.sendKeys(contextStr);
            }
        })) return false;
        return true;
    }

    public boolean sendKeyElement(By by, final String contextStr) throws InterruptedException {
        if (!elementWaitAndHandle(by, new SeleniumUtilInterface() {
            @Override
            public void action(WebElement element) {
                element.sendKeys(contextStr);
            }
        })) return false;
        return true;
    }

    public boolean isExist(By by) throws InterruptedException {
        if (!elementWaitAndHandle(by, null)) return false;
        return true;


    }

    public boolean isExistNew(String xpathStr) {
        try {
            driver.findElement(By.xpath(xpathStr));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } catch (TimeoutException e) {
            logger(By.xpath(xpathStr));
            return false;
        }
    }

    public boolean isExist(String xpathStr) throws InterruptedException {
        if (!elementWaitAndHandle(By.xpath(xpathStr), null)) return false;
        return true;
    }

    public boolean clickElement(String xpathStr) throws InterruptedException {
        if (!elementWaitAndHandle(By.xpath(xpathStr), new SeleniumUtilInterface() {
            @Override
            public void action(WebElement element) {
                element.click();
            }
        })) return false;
        return true;
    }

    public boolean clickElement(By by) throws InterruptedException {
        if (!elementWaitAndHandle(by, new SeleniumUtilInterface() {
            @Override
            public void action(WebElement element) {
                element.click();
            }
        })) return false;
        return true;
    }

    public boolean elementListHandle(List<WebElement> elements, SeleniumUtilInterface handle) throws InterruptedException {
        if (elements.size() > 0) {
            for (WebElement element : elements) {
                handle.action(element);
            }
        }
        return false;
    }

    public Map listsToMap(List<String> keyArr, List<Integer> valueArr) {
        if (keyArr.size() != valueArr.size() || keyArr.size() == 0 || valueArr.size() == 0) return null;
        Map map = new HashMap();
        for (int i = 0; i < keyArr.size(); i++) {
            map.put(keyArr.get(i), valueArr.get(i));
        }
        return map;
    }

    public void joinTheParameters() throws InterruptedException {
        String Url1 = driver.getCurrentUrl();
        String Url2 = Url1 + "&httpdebug=air";
        load();
        isLoaded();
        driver.navigate().to(Url2);
    }


    public  void accordingToWait(String xpathStr) throws InterruptedException {
       try {
           new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathStr)));
       }catch (Exception  e){
           System.out.println(e.toString());
           System.out.println("-------------");
           System.out.println(e.getMessage());
           System.out.println("-------------");
           e.printStackTrace();
       }
    }

    public void  ifGoOn()throws  InterruptedException{
      try {
          if (driver.getPageSource().equals("请在客户端内打开")) {
              joinTheParameters();
          }
      }catch (Exception  e){
          System.out.println(e.toString());
          System.out.println("-------------");
          System.out.println(e.getMessage());
          System.out.println("-------------");
          e.printStackTrace();
      }
    }
}