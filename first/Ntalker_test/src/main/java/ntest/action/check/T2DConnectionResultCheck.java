package ntest.action.check;

import ntest.action.result.T2DConnectionResult;
import org.apache.log4j.Logger;

/**
 * Created by guo on 2017/3/27.
 */
public class T2DConnectionResultCheck {
    private static Logger testResultLog  =  Logger.getLogger("TestResult");
    private T2DConnectionResult t2DConnectionResult;

    public T2DConnectionResultCheck(T2DConnectionResult t2DConnectionResult) {
        this.t2DConnectionResult = t2DConnectionResult;
    }

    public boolean connectResultCheck() {

        //判断连接状态
        if(t2DConnectionResult.KF_T2D_connectStatus == -1){
            testResultLog.info("客服连接t2d服务器失败");
            return false;
        }else if (t2DConnectionResult.KF_T2D_connectStatus == 0){
            testResultLog.info("客服连接t2d服务器失败");
            return false;
        }else if (t2DConnectionResult.KF_T2D_connectStatus == 2){
            testResultLog.info("客服连接t2d服务器失败");
            return false;
        }
        testResultLog.info("客服连接t2d服务器成功");
        return true;

    }

    public boolean loginResultCheck() {
        //判断连接状态
        if(t2DConnectionResult.KF_T2D_loginStatus == -1){
            testResultLog.info("客服登录t2d服务器失败");
            return false;
        }else if (t2DConnectionResult.KF_T2D_loginStatus == 0){
            testResultLog.info("客服登录t2d服务器失败");
            return false;
        }else if (t2DConnectionResult.KF_T2D_loginStatus == 2){
            testResultLog.info("客服登录t2d服务器失败");
            return false;
        }else if (t2DConnectionResult.KF_token == false){
            testResultLog.info("客服登录t2d服务器失败，无法后去token数据");
            return false;
        }
        testResultLog.info("客服登录t2d服务器成功");
        return true;

    }
}
