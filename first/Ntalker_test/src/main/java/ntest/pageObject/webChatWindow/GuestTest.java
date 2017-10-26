package ntest.pageObject.webChatWindow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizhixia on 2017/4/20.
 */
public class GuestTest {
    public static void main(String[] args) throws IOException, AWTException, InterruptedException {
        startTest();
    }

    public static void startTest() throws InterruptedException {
        String siteid = "kf_4607";
        String currentPath = System.getProperty("user.dir");
        String savePath = currentPath + "\\src\\main\\java\\ntest\\other\\download\\" + siteid; //历史消息保存路径
        File fileSavePath = new File(savePath);
        if (!fileSavePath.exists()) {
            fileSavePath.mkdirs();
        }else {
            for (File file :
                    fileSavePath.listFiles()) {
                file.delete();
            }
        }
        ProfilesIni pi = new ProfilesIni();
        FirefoxProfile profile = pi.getProfile("default");
//        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList",2); //设置成0 代表下载到浏览器默认下载路径；设置成2 则可以保存到指定目录。
        profile.setPreference("browser.download.manager.showWhenStarting",false); //是否显示开始，Ture 为显示，Flase 为不显示。
        profile.setPreference("browser.download.dir",savePath);   //用于指定你所下载文件的目录。
        profile.setPreference(
                "browser.helperApps.neverAsk.saveToDisk",
                "application/zip,text/plain,application/vnd.ms-excel,text/csv,text/comma-separated-values,application/octet-stream,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        WebDriver webDriver = new FirefoxDriver(profile);
        String url = "http://nt-v1-proxy.ntalker.com:140/kf_4607/index.php";

        Map evaluateMap = new HashMap();
        evaluateMap.put("enableValue", "true");
        evaluateMap.put("enableNum", "1");

        Map<String, Integer> emoticonMap = new HashMap<String, Integer>();
//        emoticonMap.put("默认表情", 20);
//        emoticonMap.put("符号表情", 60);
        emoticonMap.put("大发", 5);

        emoticonMap.put("b", 0);
        emoticonMap.put("新", 6);

        Guest guest = new Guest(webDriver, url, savePath, evaluateMap, emoticonMap);
        guest.openChatPage();
//        guest.openChatWindow("kf_4607_1489050360764", "lizhixia_kf");
        guest.openChatWindow("kf_4607_1488854360823", "lizhixia_kf");

        guest.sendTextMessage("__XN__");

//        guest.sendAllEmoticon();
//        guest.sendDefaultEmoticon();
//        guest.sendSymbolEmoticon();
//        guest.sendCustomEmoticon();

//        guest.checkoutEmoticon();
//        String defalutExePath = currentPath + "\\src\\main\\java\\ntest\\other\\upload.exe";
//        String defautlTitle = "[CLASS:#32770]";
//        String imagePath = currentPath + "\\src\\main\\java\\ntest\\other\\images\\3.jpg";
//        String defaultText = "Edit1";
//        String defaultButton = "Button1";
//        guest.sendImage(defalutExePath, defautlTitle, imagePath, defaultText, defaultButton);
//        String filePath = currentPath + "\\src\\main\\java\\ntest\\other\\file\\test.txt";
//        guest.sendFile(defalutExePath, defautlTitle, filePath, defaultText, defaultButton);
//
        guest.downloadChatMessage();
        guest.activeEvaluate();
        guest.sendEvaluate();
        String captureExePath = currentPath + "\\src\\main\\java\\ntest\\other\\mousedoubleclick.exe";
        guest.screenCapture(captureExePath);

//        guest.endChat();
    }



//    public static void startTest(String siteId, String settingId, String kfId, String url, String message, String uploadExePath, String imagePath, String filePath, String captureExePath) {
//        String currentPath = System.getProperty("user.dir");
//        String savePath = currentPath + "\\src\\main\\java\\ntest\\other\\download\\" + siteId;
//        File fileSavePath = new File(savePath);
//        if (!fileSavePath.exists()) {
//            fileSavePath.mkdirs();
//        }else {
//            for (File file :
//                    fileSavePath.listFiles()) {
//                file.delete();
//            }
//        }
//        ProfilesIni pi = new ProfilesIni();
//        FirefoxProfile profile = pi.getProfile("default");
//        profile.setPreference("browser.download.folderList",2); //设置成0 代表下载到浏览器默认下载路径；设置成2 则可以保存到指定目录。
//        profile.setPreference("browser.download.manager.showWhenStarting",false); //是否显示开始，Ture 为显示，Flase 为不显示。
//        profile.setPreference("browser.download.dir",savePath);   //用于指定你所下载文件的目录。
//        profile.setPreference(
//                "browser.helperApps.neverAsk.saveToDisk",
//                "application/zip,text/plain,application/vnd.ms-excel,text/csv,text/comma-separated-values,application/octet-stream,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//        WebDriver webDriver = new FirefoxDriver(profile);
//
//        Guest guest = new Guest(webDriver, url, savePath, );
//        guest.openChatPage();
//
//        guest.openChatWindow(settingId, kfId);
//        guest.sendTextMessage(message);
//
//        guest.sendAllEmoticon();
//        guest.checkoutEmoticon();
//        String defalutExePath = currentPath + "\\src\\main\\java\\ntest\\other\\upload.exe";
//        String defautlTitle = "[CLASS:#32770]";
//        String defaultImagePath = currentPath + "\\src\\main\\java\\ntest\\other\\images\\3.jpg";
//        String defaultText = "Edit1";
//        String defaultButton = "Button1";
//        guest.sendImage(defalutExePath, defautlTitle, defaultImagePath, defaultText, defaultButton);
//        String defaultFilePath = currentPath + "\\src\\main\\java\\ntest\\other\\file\\test.txt";
//        guest.sendFile(defalutExePath, defautlTitle, defaultFilePath, defaultText, defaultButton);
//
//        guest.downloadChatMessage();
//        guest.activeEvaluate();
//        guest.sendEvaluate();
//        String defaultCaptureExePath = currentPath + "\\src\\main\\java\\ntest\\other\\mousedoubleclick.exe";
//        guest.screenCapture(defaultCaptureExePath);
////        guest.endChat();
//    }
}
