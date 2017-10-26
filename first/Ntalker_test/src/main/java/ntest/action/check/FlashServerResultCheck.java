package ntest.action.check;

import ntest.action.result.FlashServerResult;
import org.apache.log4j.Logger;

/**
 * Created by guo on 2017/3/27.
 */
public class FlashServerResultCheck {
    private static Logger testResultLog  =  Logger.getLogger("TestResult");
    private FlashServerResult _flashServerResult;

    public void set_flashServerResult(FlashServerResult _flashServerResult) {
        this._flashServerResult = _flashServerResult;
    }

    public boolean resultCheck(){
        if (_flashServerResult.tchatmqttserver == null){
            testResultLog.info("获取tchatmqtt服务器地址失败");
            return false;
        }
        if (_flashServerResult.t2dmqttserver == null){
            testResultLog.info("获取t2dmqtt服务器地址失败");
            return false;
        }
        if (_flashServerResult.t2dserverstatus==null){
            testResultLog.info("获取t2dhttp服务器地址失败");
            return false;
        }
        if (_flashServerResult.fileserver == null){
            testResultLog.info("获取文件服务器地址失败");
            return false;
        }
        if (_flashServerResult.trailserver == null){
            testResultLog.info("获取轨迹服务器地址失败");
            return false;
        }

        testResultLog.info("成功获取tchat、t2d、文件服务器、轨迹服务器地址");
        return true;
    }
}
