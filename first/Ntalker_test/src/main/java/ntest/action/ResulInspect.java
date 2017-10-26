package ntest.action;

import ntest.action.check.*;
import ntest.action.result.*;
import ntest.bean.MonitorTask;
import org.apache.log4j.Logger;

/**
 * Created by guo on 2017/3/27.
 */
public class ResulInspect {
        private static Logger testResultLog  =  Logger.getLogger("TestResult");

        public void init(String siteid){
                testResultLog.info("------------------------------"+siteid+"StartTest-------------------------------------------------");
        }
        public void end(String siteid){
                testResultLog.info("------------------------------"+siteid+"EndTest---------------------------------------------------");
        }
        //检查配置是否正确并且打印
        public boolean moitorTaskCheck(MonitorTask _monitorTask){
                MoitorTaskCheck _moitorTaskCheck = new MoitorTaskCheck();
                _moitorTaskCheck.set_monitorTask(_monitorTask);
                if (_moitorTaskCheck.resultCheck()){
                        return true;
                }
                return false;
        }

        //访客信息
        public boolean visitorCheck(){return true;}
        //客服信息
        public boolean waiterCheck(){return true;}

        //检查服务地址获取
        public boolean flashServerResultCheck(FlashServerResult flashServerResult){
                FlashServerResultCheck _flashServerResultCheck = new FlashServerResultCheck();
                _flashServerResultCheck.set_flashServerResult(flashServerResult);
                if (_flashServerResultCheck.resultCheck()){
                        return true;
                }
                return false;
        }
        //检查访客获取客服信息
        public boolean getWaiterResultCheck(GetWaiterResult getWaiterResult){
                GetWaiterResultCheck getWaiterResultCheck = new GetWaiterResultCheck(getWaiterResult);
                if (getWaiterResultCheck.resultCheck()){
                        return true;
                }
                return false;
        }
//        检查客服t2d连接
        public boolean t2DConnectionResultCheck( T2DConnectionResult kfConnectT2DResult){
                T2DConnectionResultCheck t2DConnectionResultCheck = new T2DConnectionResultCheck(kfConnectT2DResult);
                if (t2DConnectionResultCheck.connectResultCheck()){
                        return true;
                }
                return false;
        }
        //检查客服登录t2d
        public boolean t2DloginResultCheck( T2DConnectionResult kfConnectT2DResult){
                T2DConnectionResultCheck t2DConnectionResultCheck = new T2DConnectionResultCheck(kfConnectT2DResult);
                if (t2DConnectionResultCheck.loginResultCheck()){
                        return true;
                }
                return false;

        }
//        检查客服连接tcaht服务器
        public boolean kfConnectionResultCheck(TChatConnectionResult chatConnectionResult){
                ChatConnectionResultCheck chatConnectionResultCheck = new ChatConnectionResultCheck(chatConnectionResult,true);
                if (chatConnectionResultCheck.resultCheck()){
                        return true;
                }
                return false;
        }
//        检查访客连接tchat服务器
        public boolean webConnectionResultCheck( TChatConnectionResult chatConnectionResult){
                ChatConnectionResultCheck chatConnectionResultCheck = new ChatConnectionResultCheck(chatConnectionResult,false);
                if (chatConnectionResultCheck.resultCheck()){
                        return true;
                }
                return false;
        }
        //访客文字信息和表情信息校验
        public boolean webMessageResultCheck( MessagesResult web_messages,MessagesResult kf_messages,boolean istext){
                MessageResultCheck messageResultCheck = new MessageResultCheck(kf_messages,web_messages,istext);
                if (messageResultCheck.webResultCheck()) {
                        return true;
                }
                return false;
        }
        //客服文字信息和表情信息校验
        public boolean kfMessageResultCheck(MessagesResult kf_messages,MessagesResult web_messages,boolean istext){
                MessageResultCheck messageResultCheck = new MessageResultCheck(kf_messages,web_messages,istext);
                if (messageResultCheck.kfResultCheck()) {
                        return true;
                }
                return false;
        }
        //访客文件校验
        public boolean webFileMessageResultCheck( MessagesResult web_messages,MessagesResult kf_messages){
                FileMessageResultCheck fileMessageResultCheck = new FileMessageResultCheck(kf_messages,web_messages);
                if (fileMessageResultCheck.webResultCheck()){
                        return true;
                }
                return false;
        }
        //客服文件校验
        public boolean kfFileMessageResultCheck(MessagesResult kf_messages,MessagesResult web_messages){
                FileMessageResultCheck fileMessageResultCheck = new FileMessageResultCheck(kf_messages,web_messages);
                if (fileMessageResultCheck.kfResultCheck()){
                        return true;
                }
                return false;
        }
        //检查客服端环境是否适合进行自动化测试
        public boolean kfReceptionSettingCheck(KFSettingResult kfSettingResult){
                KF_EnviromentCheck kf_enviromentCheck = new KF_EnviromentCheck(kfSettingResult);
                if (kf_enviromentCheck.receptionSettingCheck()){
                        return true;
                }
                return false;
        }
        //检查客服端环境自动化测试完成后是否清理成功
        public boolean kfReceptionSettingCleanCheck(KFSettingResult kfSettingResult){
                KF_EnviromentCheck kf_enviromentCheck = new KF_EnviromentCheck(kfSettingResult);
                if (kf_enviromentCheck.receptionSettingCleanCheck()){
                        return true;
                }
                return false;
        }

}
