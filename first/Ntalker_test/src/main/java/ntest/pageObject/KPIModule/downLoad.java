package ntest.pageObject.KPIModule;

import ntest.pageObject.BasePageObject;
import ntest.util.ReadExcel;
import ntest.util.SeleniumUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;

/**
 * Created by Administrator on 2017/5/12.
 */
public class downLoad  extends BasePageObject{


    public  downLoad (String url,String siteid, String  savePath) {
        this.url = url;
        this.siteid = siteid;
        this.savePath = savePath;
        fetchPageObject(this);
    }


    public void downLoadMethod() throws Exception {
        accordingToWait(".//*[@class='s_righ_top']/div/div[2]/input");
        driver.findElement(By.xpath(".//*[@class='s_righ_top']/div/div[2]/input")).click();
        Thread.sleep(1000);
        SeleniumUtil.exeHandle("\\D:\\downloadFile.exe");
        Thread.sleep(1000);




//        Thread.sleep(1000);
//        action.sendKeys(Keys.DOWN);

//        Actions  action = new Actions(driver);
//        action.sendKeys(Keys.ENTER);
//        ReadExcel readExcel = new ReadExcel("D:\\自动化测试下载图片");







    }



    public  static  void  main (String [] args) throws Exception {
        downLoad gxb = new  downLoad("http://nt-v1-kpi.ntalker.com/index.php/summary/survey?userid=kf_4607_ISME9754_T2D_test002&token=599f1a8e2593d59647fa950b7196c2dd","kf_4604","D:\\自动化测试下载图片");
        gxb.load();
        gxb.isLoaded();
        gxb.downLoadMethod();

    }


    /*public static List<List<Object>> readExcel(File file) throws IOException {
        String fileName = file.getName();
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                .substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(extension)) {
            return read2003Excel(file);
        } else if ("xlsx".equals(extension)) {
            return read2007Excel(file);
        } else {
            throw new IOException("不支持的文件类型");
        }*/
}


