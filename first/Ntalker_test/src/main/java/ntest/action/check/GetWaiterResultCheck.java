package ntest.action.check;

import ntest.action.result.GetWaiterResult;
import org.apache.log4j.Logger;

/**
 * Created by guo on 2017/3/27.
 */
public class GetWaiterResultCheck {
    private static Logger testResultLog  =  Logger.getLogger("TestResult");
    GetWaiterResult getWaiterResult = null;

    public GetWaiterResultCheck(GetWaiterResult _getWaiterResult){
        this.getWaiterResult = _getWaiterResult;
    }

    public boolean resultCheck(){
        if (!getWaiterResult.WEB_settingID){
            testResultLog.info("访客无法获取settingId失败！！请检查客服端-设置-接待组是否正常！！");
            return false;

        }
        if (getWaiterResult.WEB_destKFUid == null){
            testResultLog.info("访客请求t2d服务器分配客服失败KFuserid:"+getWaiterResult.WEB_destKFUid);
            return false;
        }
        if (getWaiterResult.WEB_destKFStatus == 0){
            testResultLog.info("访客请求t2d服务器分配客服失败"+"客服状态"+getWaiterResult.WEB_destKFStatus);
            return false;
        }
        if (getWaiterResult.WEB_TCHAT_sessionID == null){
            testResultLog.info("访客请求t2d服务器分配客服失败sessionID:"+getWaiterResult.WEB_TCHAT_sessionID);
            return false;
        }

        testResultLog.info("访客请求t2d服务器分配客服成功");
        return true;
    }
}
