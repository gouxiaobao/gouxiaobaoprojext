package ntest.action.check;

import ntest.action.result.KFSettingResult;
import org.apache.log4j.Logger;

/**
 * Created by guo on 2017/4/20.
 */
public class KF_EnviromentCheck {
    private static Logger testResultLog  =  Logger.getLogger("TestResult");
    private KFSettingResult kfSettingResult = null;

    public KF_EnviromentCheck(KFSettingResult _kfSettingResult){
        this.kfSettingResult = _kfSettingResult;

    }
    //检查是否存在接待时间
    public boolean receptionSettingCheck(){
        if (kfSettingResult.receptionTime ==1){
            testResultLog.info("客服端接待时间为打开状态，自动化测试停止");
            return false;
        }else{
            testResultLog.info("客服端接待时间为关闭状态，继续自动化测试");
        }

        if (kfSettingResult.receptionGroup==false ){
            testResultLog.info("客服端创建接待组__XN__TEST_______失败，自动化测试停止");
            return false;
        }else{
            testResultLog.info("客服端创建接待组__XN__TEST_______成功，继续自动化测试");
        }
        return true;
    }
    //检查是否删除自创建的接待组
    public boolean receptionSettingCleanCheck(){

        if (kfSettingResult.receptionGroupDel == false ){
            testResultLog.info("客服端__XN__TEST_______接待组删除失败");
            return false;
        }
        testResultLog.info("客服端__XN__TEST_______接待组删除成功");
        return true;
    }
}
