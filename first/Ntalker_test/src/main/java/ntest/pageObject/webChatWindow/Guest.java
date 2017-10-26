package ntest.pageObject.webChatWindow;

import org.apache.log4j.Logger;
import org.apache.xpath.operations.Bool;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.awt.*;
import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

import ntest.pageObject.BasePageObject;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

//import static ntest.util.SeleniumUtil.isExist;


/**
 * Created by lizhixia on 2017/4/17.
 */
public class Guest extends BasePageObject implements Runnable{
    private static Logger testResultLog  =  Logger.getLogger("TestResult");
    private WebDriver driver;
    private String url;
    private boolean isVip;
    private boolean isLogin;
    private boolean isConnectedKF;
    private boolean isConnectedRobot;
    private String guestName;
    private String guestId;
    private String guestSource;
    private String gusetCurrentPage;
    private String goodsInfo;
    private String browser;
    private String savePath;
    private Map<Object, Object> evaluateMap;
    private Map<String, Integer> emoticonMap;

    String textareaXpath = "//div[@class=\"chat-view-window-editor\"]/textarea"; //文本输入框
    String submitBtnXpath = "//div[@class=\"chat-view-submit\"]"; //发送按钮
    String endBtnXpath = "//span[@class=\"chat-view-end-session\"]"; //结束会话按钮
    String closeChatWindowBtnXpath = "//div[@class=\"ntalk-button-close\"]"; //关闭聊窗按钮
    String emoticonBtnXpath = "//div[@class=\"chat-view-button chat-view-face\"]"; //表情按钮
    String defaultEmoticonGroupXpath = "//div[@class=\"chat-view-face-group-0 chat-view-face-group\"]/img"; //默认表情组
    String symbolEmoticonXpath = "//div[@class=\"chat-view-face-tag chat-view-face-tag-1\"]"; //符号表情按钮
    String evaluateBtnXpath = "//div[@class=\"chat-view-button chat-view-evaluate\"]"; //评价按钮
    String symbolEmoticonGroupXpath = "//div[@class=\"chat-view-face-group-1 chat-view-face-group\"]/img"; //符号表情组
    String customEmoticonBtnXpath = "//div[@class=\"chat-view-face-tag chat-view-face-tag-2\"]"; //自定义表情按钮
    String customEmoticonGroupXpath = "//div[@class=\"chat-view-face-group-2 chat-view-face-group\"]";
    String verySatisfiedBtnXpath = "//input[@id=\"evaluation_0\"]"; //评价窗口非常满意按钮
    String hasSolutionBtnXpath = "//input[@id='problem_0']"; //评价窗口已解决按钮
    String suggestTextareaXpath = "//textarea[@name=\"proposal\"]"; //评价窗口建议
    String evaluateSubmitXpath = "//input[@class=\"view-alert-submit\"]"; //评价提交按钮
    String downloadMessageXpath = "//div[@class=\"chat-view-button chat-view-history\"]"; //下载聊天信息按钮
    String uploadImageXpath = "//input[@type=\"file\" and @title=\"图片\"]"; //图片上传按钮
    String uploadFileXpath = "//input[@type=\"file\" and @title=\"文件\"]"; //图片上传按钮
    String robotSwitchManualXpath = "//div[@class=\"chat-view-switch-manual chat-view-robot-button\"]"; //机器人转人工按钮
    String emoticonToolbarXpath = "//div[@class=\"chat-view-face-tags\"]/div"; //表情工具栏
    String screenCaptureBtnXpath = "//div[@class=\"chat-view-button chat-view-capture\"]"; //截屏按钮
    String hideWindowBtnXpath = "//div[@class=\"chat-view-capture-options\"]";
    String historyMessageXpath = "//div[@class=\"chat-view-window-history\"]/ul/li[contains(@class,'J')]//div[@class=\"view-history-body\"]"; //访客会话历史记录
    String historyImageXpath = "//div[@class=\"chat-view-window-history\"]/ul/li[contains(@class,'J')]//img";
    String historyFileXpath = "//div[@class=\"chat-view-window-history\"]/ul/li[contains(@class,'J')]//div[@class=\"view-fileupload-status\"]";
    String historyEvaluateXpath = "//div[@class=\"view-history-system\"]//div";
    //TODO
    String messageBtnXpath = "//li[@class=\"system\"]//a[contains(@onclick, \"switchUI\")]"; //留言按钮
    String messageOfSwitchManual = "//li[@class=\"robot_toast2\"]//div[@class=\"view-history-body\"]";


    public void run() {
        try {
            Thread.sleep(10000);
            File savePathDir = new File(savePath);
            String zipPath = savePath + File.separator + "chatmessages.zip";
            File zipFile = new File(zipPath);
            if ((new File(zipPath).exists())&&FileHandler.isZipped(zipPath)) {
                (new Zip()).unzip(zipFile, (new File(savePath)));
            }
            File destFile = new File(savePath+File.separator+"messages.txt");
            if (destFile.exists()&&destFile.length()>0) {
                System.out.println("解压成功且文件不为空");
                testResultLog.info("解压成功且文件不为空");
            }else {
                System.out.println("解压成功但文件为空");
                testResultLog.info("解压成功但文件为空");
            }
        } catch (IOException e) {
            testResultLog.info("解压出错");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Guest(WebDriver webDriver, String url, String savePath, Map evaluateMap, Map emoticonMap) {
        this.driver = webDriver;
        this.url = url;
        this.savePath = savePath;
        this.evaluateMap = evaluateMap;
        this.emoticonMap = emoticonMap;
//        guestName = (String)((JavascriptExecutor)driver).executeScript("return nTalk.global.uname;");
    }
    @Override
    public boolean isExist(String xpathStr) {
        try {
            driver.findElement(By.xpath(xpathStr));
            System.out.println("元素存在"+xpathStr);
            testResultLog.info("元素存在"+xpathStr);
            return true;
        }catch (NoSuchElementException e) {
            System.out.println("元素不存在"+xpathStr);
            testResultLog.info("元素不存在"+xpathStr);
            return false;
        }
    }

    public WebDriver createDriver(String path) {

        return driver;
    }
    public void openChatPage() {
        try {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url += "http://";
            }
            this.driver.get(this.url);
            testResultLog.info(String.format("%s---测试页面打开成功", url));
        }catch (Exception e) {
            testResultLog.info(String.format("%s---测试页面打开出错", url));
            e.printStackTrace();
        }
    }
    public void openChatWindow(String settingId, String kfId) {
        if (kfId == null || settingId == null) {
            testResultLog.info(String.format("接待组id或客服id为空，settingid: %s, kfid: %s", settingId, kfId));
        }
        String script = String.format("nTalk.im_openInPageChat('%s', '', '%s');", settingId, settingId+ "_" + kfId);
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(script);
            //不限制前端页展示消息条数
            js.executeScript("nTalk.chatManage.get().view._maxNumber = Infinity;");
        }catch (Exception e) {
            testResultLog.info(String.format("%s---聊窗打开失败", script));
            e.printStackTrace();
        }
    }
    public Boolean getIsConnectKF() {
        try {
            testResultLog.info("检查是否连接到人工客服");
            driver.findElement(By.xpath(messageBtnXpath));
            testResultLog.info("访客进入排队");
        }catch (NoSuchElementException e) {
            testResultLog.info("成功连接到人工客服");
//            e.printStackTrace();
            isConnectedKF = true;
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean getIsConnectedRobot() {
        try {
            testResultLog.info("检查是否连接到机器人");
            driver.findElement(By.xpath(robotSwitchManualXpath));
            isConnectedRobot = true;
            testResultLog.info("访客连接到机器人");
        }catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return isConnectedRobot;
    }
    public void login() {
        driver.findElement(By.xpath(""));
    }
    public void logout() {

    }

    public void getGusetId() {

    }
    public void getGuestSource() {

    }
    public void setIsVip() {

    }
    public Boolean getIsVip() {
        String result = (String)((JavascriptExecutor)driver).executeScript("return nTalk.global.isvip;");
        if (result == "1") {
            isVip = true;
        }
       return isVip;
    }
    public void setUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url += "http://";
        }
        this.url = url;
    }
    public String getUrl(String url) {
        return url;
    }
    public String getGuestName() {
        return guestName;
    }
    public void switchManual() {
        try {
            WebElement element = driver.findElement(By.xpath(robotSwitchManualXpath));
            element.click();
            testResultLog.info("连接到机器人，开始转接人工客服");
        }catch (Exception e) {
//            e.printStackTrace();
        }
    }
    public Boolean resultSwitchManual() throws InterruptedException {
        String responseRobot;
        try {
            Thread.sleep(1000);
//            WebDriverWait wait = new WebDriverWait(driver, 5);
//            wait.until(new ExpectedCondition<WebElement>() {
//                @Override
//                public WebElement
//            });
           responseRobot =  driver.findElement(By.xpath(messageOfSwitchManual)).getText();
            if (responseRobot.equals("已成功为您转接人工客服！")) {
                isConnectedRobot = false;
                return true;
            }
        }catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }
    public void sendTextMessage(String message) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until();
        if (!isConnectedKF&&getIsConnectedRobot()) {
            switchManual();
            if (!resultSwitchManual()) {
                return;
            }
        }

        if (getIsConnectKF()&&isExist(textareaXpath)) {
            testResultLog.info("聊窗打开成功");
            driver.findElement(By.xpath(textareaXpath)).sendKeys(message);
            driver.findElement(By.xpath(submitBtnXpath)).click();
        }
        Boolean result = checkOutMessage(message);
        if (result) {
            testResultLog.info(String.format("%s---消息发送成功", message));
            System.out.println(String.format("%s---消息发送成功", message));
        }else {
            testResultLog.info(String.format("%s---消息发送失败", message));
            System.out.println(String.format("%s---消息发送失败", message));
        }
    }
    public void sendTextMessage(String[] messages) throws InterruptedException {
        for (String message :
                messages) {
            sendTextMessage(message);
        }
    }
    public void sendTextMessage(File file) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String content = reader.readLine();
            while (content != null) {
                sendTextMessage(content);
                System.in.read();
                System.in.skip(2);
                content = reader.readLine();
            }
            reader.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Boolean checkoutEmoticon() {
        driver.findElement(By.xpath(emoticonBtnXpath)).click();
        List<WebElement> toobarElements = driver.findElements(By.xpath(emoticonToolbarXpath));
        if (emoticonMap.size() == toobarElements.size() - 2) {
            System.out.println(String.format("共有%d组表情", emoticonMap.size()));
            testResultLog.info(String.format("共有%d组表情", emoticonMap.size()));
        } else {
            System.out.println(String.format("两边表情组数量不一致，客户端有%d组表情，访客端%d组表情", emoticonMap.size(), toobarElements.size()));
            testResultLog.info(String.format("两边表情组数量不一致，客户端有%d组表情，访客端%d组表情", emoticonMap.size(), toobarElements.size()));
            return false;
        }
        try {
            for (int index = 2; index < toobarElements.size(); index++) {
                WebElement customEmoticonElement = driver.findElements(By.xpath(emoticonToolbarXpath)).get(index);
                String emoticonGroupXpath = String.format("//div[@class=\"chat-view-face-group-%s chat-view-face-group\"]/img", index);
                List<WebElement> customEmoticonElements = driver.findElements(By.xpath(emoticonGroupXpath));
                String title = customEmoticonElement.getAttribute("title").toString();
                Integer number = customEmoticonElements.size();
                System.out.println(String.format("%s---%d",title, number));
                if (emoticonMap.get(title) == number) {
                    System.out.println(String.format("%s表情组表情数量为%d", title, number));
                    testResultLog.info(String.format("%s表情组表情数量为%d", title, number));
                } else {
                    System.out.println(String.format("", emoticonMap.get(title)));
                    System.out.println(String.format("表情数量不一致，客户端%s表情组表情数量为%d，访客端为%d", title, emoticonMap.get(title), number));
                    testResultLog.info(String.format("表情数量不一致，客户端%s表情组表情数量为%d，访客端为%d", title, emoticonMap.get(title), number));
                    return false;
                }
            }
            testResultLog.info("表情数量校验成功");
            return true;
        }catch (NoSuchElementException e){
            testResultLog.info("校验表情出错，有元素未找到");
            System.out.println("校验表情出错，有元素未找到");
            e.printStackTrace();
        }catch (Exception e) {
            testResultLog.info("校验所有表情出错");
            e.printStackTrace();
        }
        return false;
    }
    public void sendAllEmoticon() {
        driver.findElement(By.xpath(emoticonBtnXpath)).click();
        List<WebElement> toobarElements = driver.findElements(By.xpath(emoticonToolbarXpath));
        try {
            for (int index = 0; index < toobarElements.size(); index++) {
                WebElement customEmoticonElement = driver.findElements(By.xpath(emoticonToolbarXpath)).get(index);
                String emoticonGroupXpath = String.format("//div[@class=\"chat-view-face-group-%s chat-view-face-group\"]/img", index);
                List<WebElement> emoticonElements = driver.findElements(By.xpath(emoticonGroupXpath));
                if (emoticonElements.size() >= 3) {
                    for (int i = 0; i < 3; i++) {
                        driver.findElement(By.xpath(emoticonBtnXpath)).click();
                        customEmoticonElement.click();
                        WebElement emoticonElement = emoticonElements.get(i);
                        emoticonElement.click();

                    }
                }else {
                    for (WebElement element :
                            emoticonElements) {

                        driver.findElement(By.xpath(emoticonBtnXpath)).click();
                        customEmoticonElement.click();
                        element.click();


                    }
                }
                if (index == 0) {
                    driver.findElement(By.xpath(submitBtnXpath)).click();
                }
            }
            testResultLog.info("发送所有表情成功");
        }catch (NoSuchElementException e){
            testResultLog.info("发送所有表情出错，有元素未找到");
            System.out.println("发送所有表情未找到对应元素");
            e.printStackTrace();
        }catch (Exception e) {
            testResultLog.info("发送所有表情出错");
            e.printStackTrace();
        }
    }
    public void sendDefaultEmoticon() {
        try {
            driver.findElement(By.xpath(emoticonBtnXpath)).click();
            List<WebElement> defautltEmoticonElements = driver.findElements(By.xpath(defaultEmoticonGroupXpath));
            for (WebElement element :
                    defautltEmoticonElements) {
                driver.findElement(By.xpath(emoticonBtnXpath)).click();
                element.click();
            }
            driver.findElement(By.xpath(submitBtnXpath)).click();
        }catch (Exception e) {
            testResultLog.info("默认表情发送失败");
            e.printStackTrace();
        }
        testResultLog.info("默认表情发送成功");
    }
    public void sendSymbolEmoticon() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        if (isExist(emoticonBtnXpath)) {
            driver.findElement(By.xpath(emoticonBtnXpath)).click();
        }
        //todo:当前表情框已弹出
        if (isExist(textareaXpath)) {
            driver.findElements(By.xpath(textareaXpath)).clear();
        }
        js.executeScript("function sendFace(){\n" +
                "\tnTalk.chatManage.get().view._maxNumber = Infinity;\n" +
                "\tvar faceArr = nTalk('.chat-view-face-group-1 img');\n" +
                "\tfor( var i=0; i<faceArr.length ; i++ ){\n" +
                "\t\tnTalk.chatManage.get().send({type:2,emotion:1,msg:'current face',url:faceArr.eq(i).attr('src'),sourceurl:faceArr.eq(i).attr('sourceurl'),oldfile:'',size:'',extension:''})\n" +
                "\t}\n" +
                "}\n" +
                "sendFace();\n");
    }
    public void sendCustomEmoticon() {
        if (isExist(emoticonBtnXpath)){
            driver.findElement(By.xpath(emoticonBtnXpath)).click();
        }
        List<WebElement> toobarElements = driver.findElements(By.xpath(emoticonToolbarXpath));
        try {
            for (int index = 2; index < toobarElements.size(); index++) {
                WebElement customEmoticonElement = driver.findElements(By.xpath(emoticonToolbarXpath)).get(index);
                String emoticonGroupXpath = String.format("//div[@class=\"chat-view-face-group-%s chat-view-face-group\"]/img", index);
                List<WebElement> customEmoticonElements = driver.findElements(By.xpath(emoticonGroupXpath));
                for (WebElement element :
                        customEmoticonElements) {
                    driver.findElement(By.xpath(emoticonBtnXpath)).click();
                    customEmoticonElement.click();
                    element.click();
                }
            }
            testResultLog.info("发送自定义表情成功");
        }catch (NoSuchElementException e){
            testResultLog.info("未找到自定义表情按钮");
            e.printStackTrace();
        }catch (Exception e) {
            testResultLog.info("发送自定义表情失败");
            e.printStackTrace();
        }
    }
    public void sendImage(String exePath, String title, String imagePath, String text, String button) {
        uploadByAutoIT(exePath, title, imagePath, text, button);
        if (isExist(uploadImageXpath)) {
            driver.findElement(By.xpath(uploadImageXpath)).click();
        }
    }
    public void sendFile(String exePath, String title, String filePath, String text, String button) {
        uploadByAutoIT(exePath, title, filePath, text, button);
        if (isExist(uploadFileXpath)) {
            driver.findElement(By.xpath(uploadFileXpath)).click();
        }
    }

    /**
     * @param exePath  可执行文件位置
     * @param title 控件标题
     * @param filePath 文件路径
     * @param text 控件标识
     * @param button 按钮标识
     */
    public static void uploadByAutoIT(String exePath, String title, String filePath, String text, String button) {
        String defalutExePath = ".\\src\\main\\java\\ntest\\other\\upload.exe";
        if (!defalutExePath.equals(exePath)) {
            defalutExePath = exePath;
        }
        String defautlTitle = "[CLASS:#32770]";
        if (!defautlTitle.equals(title)) {
            defautlTitle = title;
        }
        String defaultText = "Edit1";
        if (!defaultText.equals(text)) {
            defaultText = text;
        }
        String defaultButton = "Button1";
        if (!defaultButton.equals(button)) {
            defaultButton = button;
        }
        try {
            Runtime.getRuntime().exec(String.format("%s %s %s %s %s", defalutExePath, defautlTitle, filePath, defaultText, defaultButton));

            testResultLog.info(String.format("%s---发送成功", filePath));
        }catch (Exception e) {
            testResultLog.info(String.format("%s---发送失败", filePath));
            e.printStackTrace();
        }
    }
    public void downloadChatMessage() {
        driver.findElement(By.xpath(downloadMessageXpath)).click();
        Thread thread = new Thread(this);
        thread.start();
    }
//    public boolean switchToWindow(String windowTitle){
//        boolean flag = false;
//        try {
//            String currentHandle = driver.getWindowHandle();
//            Set<String> handles = driver.getWindowHandles();
//            for (String s : handles) {
//                if (s.equals(currentHandle))
//                    continue;
//                else {
//                    driver.switchTo().window(s);
//                    if (driver.getTitle().contains(windowTitle)) {
//                        flag = true;
//                        System.out.println("Switch to window: "
//                                + windowTitle + " successfully!");
//                        break;
//                    } else
//                        continue;
//                }
//            }
//        } catch (NoSuchWindowException e) {
////            System.out.println("Window: " + windowTitle
////                    + " cound not found!", e.fillInStackTrace());
//            flag = false;
//        }
//        return flag;
//    }

    /**
     * 主动评价
     */
    public void activeEvaluate() throws InterruptedException {
        if (evaluateMap.get("enableValue").toString().equals("true")) {
            for (int i = 0; i < Integer.valueOf(evaluateMap.get("enableNum").toString()); i++) {
                sendTextMessage("hello");
            }
        }else {
            testResultLog.info("不允许主动评价");
            return;
        }
        try {
            if (isExist(evaluateBtnXpath)) {
                driver.findElement(By.xpath(evaluateBtnXpath)).click();
            }
            sendEvaluate();
        }catch (Exception e) {
            testResultLog.info("主动评价出错");
            e.printStackTrace();
        }
    }
    /**
     * 发送评价
     */
    public void sendEvaluate() {
        try {
            if (isExist(verySatisfiedBtnXpath)) {
                driver.findElement(By.xpath(verySatisfiedBtnXpath)).click();
            }
            if (isExist(hasSolutionBtnXpath)) {
                driver.findElement(By.xpath(hasSolutionBtnXpath)).click();
            }
            if (isExist(suggestTextareaXpath)) {
                driver.findElement(By.xpath(suggestTextareaXpath)).sendKeys("测试");
            }
            driver.findElement(By.xpath(evaluateSubmitXpath)).click();
            if (checkoutEvaluate()) {
                testResultLog.info("评价发送成功");
            }
        }catch (Exception e) {
            testResultLog.info("发送评价失败");
            e.printStackTrace();
        }
    }
    public void screenCapture(String captureExePath) {
        try {
            Thread.sleep(1000);
            WebElement screenCaptureElement;
            if (isExist(screenCaptureBtnXpath)) {
                screenCaptureElement = driver.findElement(By.xpath(screenCaptureBtnXpath));
                screenCaptureElement.click();
            }
            Thread.sleep(1000);
            Runtime.getRuntime().exec(captureExePath);
        }catch (Exception e) {
            testResultLog.info("截图出错");
            e.printStackTrace();
        }
    }
    public void captureException (String filePath) throws IOException {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        org.apache.commons.io.FileUtils.copyFile(srcFile, new File(filePath));
    }
    public void closeChatWindow() {
        if (isExist(closeChatWindowBtnXpath)) {
            driver.findElement(By.xpath(closeChatWindowBtnXpath)).click();
        }
    }
    public void endChat() {
        if (isExist(endBtnXpath)) {
            driver.findElement(By.xpath(endBtnXpath)).click();
        }
    }
    public Boolean checkOutMessage(String message) {
        try {
            List<WebElement> guestMessageElements = driver.findElements(By.xpath(historyMessageXpath));
            String result = guestMessageElements.get(guestMessageElements.size()-1).getText();
            if (message.equals(result)) {
                return true;
            }
        }catch (Exception e) {
            testResultLog.info("消息校验出错");
            e.printStackTrace();
        }
        return false;
    }
    public Boolean checkoutImage() {
        try {
            List<WebElement> guestMessageElements = driver.findElements(By.xpath(historyImageXpath));
            String result = guestMessageElements.get(guestMessageElements.size()-1).getAttribute("sourceurl");
            if (result.contains("store")) {
                return true;
            }
        }catch (Exception e) {
            testResultLog.info("图像消息校验出错");
            e.printStackTrace();
        }
        return false;
    }

    public Boolean checkoutFile() {
        try {
            List<WebElement> guestMessageElements = driver.findElements(By.xpath(historyFileXpath));
            String result = guestMessageElements.get(guestMessageElements.size()-1).getText();
            if (result.equals("上传成功")) {
                return true;
            }
        }catch (Exception e) {
            testResultLog.info("文件消息校验出错");
            e.printStackTrace();
        }
        return false;
    }

    public Boolean checkoutEvaluate() {
        try {
            List<WebElement> guestMessageElements = driver.findElements(By.xpath(historyEvaluateXpath));
            String result = guestMessageElements.get(guestMessageElements.size()-1).getText();
            if (result.contains("已经提交")) {
                return true;
            }
        }catch (Exception e) {
            testResultLog.info("评价消息校验出错");
            e.printStackTrace();
        }
        return false;
    }
}
