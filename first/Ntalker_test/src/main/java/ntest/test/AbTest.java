package ntest.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;

/**
 * Created by Administrator on 2017/5/18.
 */
public abstract class AbTest  {

    private static Logger testResultLog = Logger.getLogger("TestResult");

    abstract public void runBody(String xpathStr,String url) throws Exception;

    public void logger(Object obj) {
        System.out.println(obj);
        testResultLog.info(obj);
    }


    public void run(WebDriver driver,String xpathStr,String url) {
        try {
            runBody(xpathStr,url);
        } catch (TimeoutException e) {
            logger(By.xpath(xpathStr));
        } catch (NoSuchElementException e) {
            if (driver.getTitle().equals("404 Page Not Found")||driver.getTitle().contains("502")||driver.getTitle().equals(null))
            {logger("KpiSmokingTestCase surveyMethod(概况) 404or502or null");}
            logger(By.xpath(xpathStr));
        } catch (NullPointerException e) {
            logger(By.xpath(xpathStr));
        } catch (ElementNotVisibleException e) {
            logger(By.xpath(xpathStr));
        } catch (Exception e){
            logger(e.getMessage());
            StackTraceElement[] stackTrace = e.getStackTrace();
            for(int i = 0 ; i < stackTrace.length ; i ++){
                logger(stackTrace[i]);
            }
        }
    }

}