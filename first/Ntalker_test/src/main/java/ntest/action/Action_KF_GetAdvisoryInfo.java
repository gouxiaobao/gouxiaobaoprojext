package ntest.action;

import ntest.action.result.GetAdvisoryInfoResult;
import ntest.bean.MonitorTask;
import ntest.bean.Visitor;
import ntest.bean.Waiter;
import ntest.util.HttpSender;

/**
 * Created by guo on 2017/3/17.
 * �ͻ������������Ʒ��Ϣ��������¼
 * ����������������ǩ�����Ӳ��ҷŻؽ��
 */
public class Action_KF_GetAdvisoryInfo {

    private Waiter _waiter;
    private Visitor _visitor;
    private MonitorTask _monitorTask;
    private GetAdvisoryInfoResult _result= new GetAdvisoryInfoResult();
    private HttpSender httpSender = new HttpSender();

    public  Action_KF_GetAdvisoryInfo(Waiter waiter,Visitor visitor,MonitorTask monitorTask){
        this._waiter=waiter;
        this._visitor=visitor;
        this._monitorTask =monitorTask;
    }
    public GetAdvisoryInfoResult execute(){

        _result.phoneRecord = phoneRecord();
        _result.getFastReply = getFastReply();
        _result.getAllHistorymsg = getAllHistorymsg();
        _result.getusertrail = getusertrail();

        return _result;
    }
    // �ͻ���Ϣ
    /**/
    public String phoneRecord(){
        if(_monitorTask._flashServerResult.phoneRecord != null){

        String httpUrl=_monitorTask._flashServerResult.phoneRecord+"/sticket/index.php?m=Home&c=CrmUser&a=showOneTab" +
//                "&userid=kf_9517_ISME9754_guest48C84D25-4EA3-A2" +
//                "&kfuserid=kf_9517_ISME9754_T2D_ralf" +
//                "&siteid=kf_9517" +
                "&userid=" + _visitor.myuserId+
                "&kfuserid=" +_waiter._myuserId+
                "&siteid=" +_monitorTask.siteid+
                "&token=" +_monitorTask.token+
                "&username=%E5%AE%A2%E4%BA%BA4287";
            return httpSender.getInfos(httpUrl);
        }
        return null;
    }
    //��������
    public String getFastReply(){
        if (_monitorTask._flashServerResult.getFastReply != null){
            String httpUrl=_monitorTask._flashServerResult.getFastReply+"/index.php?c=enterprise&m=FlashFastReply" +
                    "&userid=" +_waiter._myuserId+
                    "&token=" +_monitorTask.token+
                    "&rnd=A573C116-3DE5-A";
            return httpSender.getInfos(httpUrl);
        }
        return null;
    }
    //������¼
    public String getAllHistorymsg(){
        if (_monitorTask._flashServerResult.getAllHistorymsg != null){
            String httpUrl=_monitorTask._flashServerResult.getAllHistorymsg+"/index.php/interactive_record/record_show?" +
                    "userid=" +_waiter._myuserId+
                    "&siteid=" +_monitorTask.siteid+
                    "&dstuid=" +_visitor.myuserId+
                    "&token=" +_monitorTask.token+
                    "&checkurl=" +
                    "&rnd=59686E1B-5140-5434-3ECC-DB6DCD566ED5";
            return httpSender.getInfos(httpUrl);
        }
        return null;
    }
    //��Ʒ��Ϣ
    /*
    public String CommodityInfoPage(){
        if (_monitorTask._flashServerResult.CommodityInfoPageUrl != null){
            String httpUrl = "//goodsinfo/api.php?" +
                    "siteid=kf_4125" +
                    "&itemid=ntalker_test" +
                    "&itemparam=" +
                    "&sellerid=" +
                    "&user_id=" +
                    "&type=" +
                    "2&ts=1490151319411";
            return httpSender.getInfos(httpUrl);
        }
        return null;
    }
    */
    //�켣��Ϣ
    public String  getusertrail(){
        if (_monitorTask._flashServerResult.getusertrail != null){
            String httpUrl=_monitorTask._flashServerResult.getusertrail+"/index.php/access_trace/trace_show?" +
                    "userid=" +_waiter._myuserId+
                    "&siteid=" +_monitorTask.siteid+
                    "&dstuid=" +_visitor.myuserId+
                    "&token=" +_monitorTask.token+
                    "&checkurl=" +
                    "&rnd=A735B6E0-1951-B1D5-2523-F0402E685CFB";
            return httpSender.getInfos(httpUrl);
        }
        return null;
    }
}
