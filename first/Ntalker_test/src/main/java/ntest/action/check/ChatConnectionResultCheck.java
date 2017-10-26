package ntest.action.check;

import ntest.action.result.TChatConnectionResult;
import org.apache.log4j.Logger;

/**
 * Created by guo on 2017/3/28.
 */
public class ChatConnectionResultCheck {
        private static Logger testResultLog  =  Logger.getLogger("TestResult");
        private TChatConnectionResult chatConnectionResult = null;
        private boolean kfis = false;

        public ChatConnectionResultCheck(TChatConnectionResult _chatConnectionResult,boolean _kfis){

            this.chatConnectionResult = _chatConnectionResult;
            kfis = _kfis;
        }
        public boolean resultCheck() {

            if (chatConnectionResult.TCHAT_clientid == null){
                if (kfis){
                    testResultLog.info("客服连接chat服务器失败"+chatConnectionResult.TCHAT_clientid+"不正确");
                }else{
                    testResultLog.info("访客连接chat服务器失败"+chatConnectionResult.TCHAT_clientid+"不正确");
                }
                return false;
            }

            if (chatConnectionResult.TCHAT_sessionID == null){
                if (kfis){
                    testResultLog.info("客服连接chat服务器失败"+chatConnectionResult.TCHAT_sessionID+"不正确");
                }else{
                    testResultLog.info("访客连接chat服务器失败"+chatConnectionResult.TCHAT_sessionID+"不正确");
                }
                return false;
            }
            //判断连接状态
            if(chatConnectionResult.TCHAT_connectStatus == -1){
                if (kfis){
                    testResultLog.info("客服连接chat服务器失败"+"不正确");
                }else{
                    testResultLog.info("访客连接chat服务器失败"+"不正确");
                }
                return false;
            }else if (chatConnectionResult.TCHAT_connectStatus == 0){
                if (kfis){
                    testResultLog.info("客服连接chat服务器失败"+"不正确");
                }else{
                    testResultLog.info("访客连接chat服务器失败"+"不正确");
                }
                return false;
            }else if (chatConnectionResult.TCHAT_connectStatus == 2){
                if (kfis){
                    testResultLog.info("客服连接chat服务器失败"+"不正确");
                }else{
                    testResultLog.info("访客连接chat服务器失败"+"不正确");
                }
                return false;
            }

            if (kfis){
                testResultLog.info("客服连接chat服务器成功");
            }else{
                testResultLog.info("访客连接chat服务器成功");
            }

            return true;
        }
    }
