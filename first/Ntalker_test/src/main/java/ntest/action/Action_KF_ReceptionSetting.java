package ntest.action;

import ntest.action.result.KFSettingResult;
import ntest.bean.Waiter;
import ntest.pageObject.settingModule.SettingPageBToC;
import ntest.util.HttpSender;
import ntest.util.SeleniumUtil;
import ntest.util.Statics;

/**
 * Created by guo on 2017/4/19.
 */
public class Action_KF_ReceptionSetting {



    private Waiter waiter = null;
    private KFSettingResult kfSettingResult = new KFSettingResult();

    private SettingPageBToC page = null;

    public Action_KF_ReceptionSetting(Waiter _waiter){
        waiter = _waiter;
    }
    //*检查客服端是否设置接待时间*/
    public KFSettingResult getReceptionTime(){

        String jsonString = null;
        String userid = Statics.GetKFUserIdByUid(waiter._monitorTask.siteid,waiter._monitorTask.waiterName);

        String settingurl = waiter._monitorTask._flashServerResult.setting.replaceAll("personsetting/index","worktime/work_time");
        String httpurl = settingurl.replaceAll("userid=###USERID###&token=###TOKEN###","")+"userid=" +userid+
                "&token=" +waiter._monitorTask.token+
                "&disp=enterprise";

        HttpSender httpSender = new HttpSender();
        jsonString=httpSender.getInfos(httpurl);
        if (jsonString == null){
            kfSettingResult.receptionTime = 0;
            return kfSettingResult;
        }
        if (jsonString.indexOf("checked/>") != -1){
            kfSettingResult.receptionTime = 2;
        }
        return kfSettingResult;
    }

    private void init(){
        String site =  waiter._monitorTask._flashServerResult.setting.substring(0,32);//32
         page = new SettingPageBToC(
                SeleniumUtil.fetchSettingURLWithToken(site,waiter._monitorTask.siteid,waiter._monitorTask.waiterName,waiter._monitorTask.token),
                System.getProperty("user.dir") + "\\src\\main\\java\\ntest\\other\\download\\" + waiter._monitorTask.siteid,
                waiter._monitorTask.siteid);
    }
//    /*创建接待组*/
    public KFSettingResult addReceptionGroup(){
        init();
        if (page == null){
            kfSettingResult.receptionGroup = false;
            return kfSettingResult;
        }
        try {
            page.load();
            page.isLoaded();
            if (page.checkNewGroup()){
                kfSettingResult.receptionGroup = true;
                page.quit();
                return kfSettingResult;
            }
//            /*创建接待组*/
            page.createNewReceptionGroup();
            Thread.sleep(1000);
            if (page.checkNewGroup()){
                kfSettingResult.receptionGroup = true;
                page.quit();
                return kfSettingResult;
            }
        } catch (InterruptedException e) {
            kfSettingResult.receptionGroup = false;
            page.quit();
            e.printStackTrace();
            return kfSettingResult;
        }
        page.quit();
        kfSettingResult.receptionGroup = false;
        return kfSettingResult;
    }
//    /*删除接待组*/
    public KFSettingResult deleteReceptionGroup(){
        System.out.println("test11111111111");
        init();
        if (page == null){
            kfSettingResult.receptionGroupDel = false;
            return kfSettingResult;
        }
        try {
            page.load();
            page.isLoaded();
            if (page.checkNewGroup() == false){
                kfSettingResult.receptionGroupDel = true;
                page.quit();
                return kfSettingResult;
            }
//            创建接待组
            page.deleteNewReceptionGroup();
            Thread.sleep(1000);
            if (page.checkNewGroup()== false){
                kfSettingResult.receptionGroupDel = true;
                page.quit();
                return kfSettingResult;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            kfSettingResult.receptionGroupDel = false;
            page.quit();
            return kfSettingResult;
        }
        kfSettingResult.receptionGroupDel = false;
        page.quit();
        return kfSettingResult;
    }
}
