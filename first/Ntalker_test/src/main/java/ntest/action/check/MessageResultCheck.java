package ntest.action.check;

import ntest.action.result.MessagesResult;
import org.apache.log4j.Logger;

/**
 * Created by guo on 2017/3/28.
 */
public class MessageResultCheck {
    private static Logger testResultLog  =  Logger.getLogger("TestResult");
    private MessagesResult kfMessagesResult = null;
    private MessagesResult webMessagesResult = null;
    private boolean istext = true;

    public MessageResultCheck(MessagesResult _kfMessagesResult, MessagesResult _webMessagesResult,boolean _istext){
        this.kfMessagesResult = _kfMessagesResult;
        this.webMessagesResult = _webMessagesResult;
        this.istext = _istext;
    }

    public boolean kfResultCheck(){
        if (!kfMessagesResult.getKF_sendMessages().equals(webMessagesResult.getWEB_recvMessages())){
            if (istext){
                testResultLog.info("客服发送文字信息失败");
            }else{
                testResultLog.info("客服发送表情信息失败");
            }
            return false;
        }
        if (istext){
            testResultLog.info("客服发送文字信息成功");
        }else{
            testResultLog.info("客服发送表情信息成功");
        }
        return true;
    }
    public boolean webResultCheck(){
        String webMessage = webMessagesResult.getWEB_sendMessages();
        String kfMessage = kfMessagesResult.getKF_recvMessages();
        if (!webMessage.equals(kfMessage)){
            if (istext){
                testResultLog.info("访客发送文字信息失败");
            }else{
                testResultLog.info("访客发送表情信息失败");
            }
            return false;
        }
        if (istext){
            testResultLog.info("访客发送文字信息成功");
        }else{
            testResultLog.info("访客发送表情信息成功");
        }
        return true;
    }
}
