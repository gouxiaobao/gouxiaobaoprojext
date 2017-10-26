package ntest.action.check;

import ntest.action.Action_WEB_FileTest;
import ntest.action.result.MessagesResult;
import org.apache.log4j.Logger;

/**
 * Created by guo on 2017/3/28.
 */
public class FileMessageResultCheck {
    private static Logger testResultLog  =  Logger.getLogger("TestResult");
    private MessagesResult kfMessagesResult = null;
    private MessagesResult webMessagesResult = null;

    public FileMessageResultCheck(MessagesResult _kfMessagesResult,MessagesResult _webMessagesResult){
        this.kfMessagesResult = _kfMessagesResult;
        this.webMessagesResult = _webMessagesResult;
    }
    public boolean kfResultCheck(){
        Action_WEB_FileTest action_web_fileTest = new Action_WEB_FileTest();
        if (!action_web_fileTest.doDownloadAction(kfMessagesResult.getFileTestResult(), webMessagesResult.WEB_recvMessages)){
            testResultLog.info("客服发送文件失败");
            return false;
        }
        testResultLog.info("客服发送文件成功");
        return true;
    }
    public boolean webResultCheck(){
        Action_WEB_FileTest action_web_fileTest = new Action_WEB_FileTest();
        if (!action_web_fileTest.doDownloadAction(webMessagesResult.getFileTestResult(), kfMessagesResult.KF_recvMessages)){
            testResultLog.info("访客发送文件失败");
            return false;
        }
        testResultLog.info("访客发送文件成功");
        return true;
    }
}
