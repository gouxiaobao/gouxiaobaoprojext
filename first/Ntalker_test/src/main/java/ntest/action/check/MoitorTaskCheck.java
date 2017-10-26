package ntest.action.check;

import ntest.bean.MonitorTask;
import org.apache.log4j.Logger;

/**
 * Created by guo on 2017/3/27.
 */
public class MoitorTaskCheck {
    private static Logger testResultLog  =  Logger.getLogger("TestResult");
    private MonitorTask _monitorTask;

    public void set_monitorTask(MonitorTask _monitorTask) {
        this._monitorTask = _monitorTask;
    }

    public boolean resultCheck(){

        if (_monitorTask.siteid == null){
            testResultLog.info("siteid不正确,读取企业配置参数失败");
            return false;
        }
//        if (_monitorTask.settingID == null){
//            testResultLog.info("settingID不正确,读取企业配置参数失败");
//            return false;
//        }
        if (_monitorTask.waiterName == null){
            testResultLog.info("settingID不正确,读取企业配置参数失败");
            return false;
        }
        if (_monitorTask.waiterPassword == null){
            testResultLog.info("客服账号不正确,读取企业配置参数失败");
            return false;
        }
        if (_monitorTask.waiterPassword == null){
            testResultLog.info("客服密码不正确,读取企业配置参数失败");
            return false;
        }
        testResultLog.info(_monitorTask.siteid+"配置参数读取完成,开始进行测试.......");
        return true;
    }

}
