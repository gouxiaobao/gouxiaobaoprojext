package ntest.pageObject.settingModule;
import ntest.pageObject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingPageBToC extends BasePageObject {
    private static Logger testResultLog  =  Logger.getLogger("TestResult");
    //账户管理
    String	accountManageLink = ".//div[@id='account']/a";
    String createGroupBtn = ".//*[@id='button3']";
    String creatGroupBtn = ".//*[@id='button4']";
    String newGroupInput = ".//*[@id='groupname']";
    String newGroupConfirmBtn = ".//div[@id='div_addgroup']//input[@class='but ma_r10' and @type='button']";
    String robotTrueRadio = ".//input[@type='radio'][2]";
    String showRolesBtn = ".//div[@id='current_pri']";
    String roleBtn = ".//div[@id='input_div_list']/div[contains(text(),'管理员')]";
    String showGroupsBtn = ".//*[@id='current_group']/parent::div";
    String groupBtn = ".//*[@id='input_div_list2']/*[contains(text(),'XN_TEST')]";
    String accountNameInput = ".//*[@id='accountName']";
    String externalNameInput = ".//*[@id='externalName']";
    String nickNameInput = ".//*[@id='nickName']";
    String pwdInput = ".//*[@id='pass']";
    String pwdConfirmInput = ".//*[@id='pass2']";
    String maxDealPerDayInput = ".//*[@id='hand_day_num']";
    String maxDealInput = ".//*[@id='hand_all_num']";
    String confirmBtn = ".//*[@id='button2']";
    //企业管理-企业信息
    String enterPriseSettingLink = ".//a[contains(text(),'企业设置')]";
    String enterPriseInfoEditBtn = ".//*[@id='button']";
    String enterPriseLogoChangeBtn = ".//div[contains(text(),'修改')]";
    String enterPriseInfoLink = ".//a[contains(text(),'企业信息')]";
    String enterPriseNameInput = ".//*[@id='ename']";
    String enterPriseInfoSaveBtn = ".//*[@id='btsave']";
    //企业管理-web聊窗
    String webConsoleLink =".//a[contains(text(),'Web聊窗')]";
    String createNewItemBtn = ".//*[@id='button3']";
    String questionDetailTextView = ".//*[@id='question']";
    String answerDetailTextView = ".//*[@id='answer']";
    String confirmItemBtn  = ".//*[@id='new_edit']";
    //query management
    String queryPageManagementLink = ".//a[contains(text(),'咨询接待页签管理')]";
    String effectBtn  = ".//button[contains(text(),'立即生效')]";


    String downloadSimpleFileBtn = ".//a[contains(text(),'下载模版')]";
    String importCustomFileBtn = ".//a[contains(text(),'导入')]";
    String selectCustomFileBtn = ".//a[contains(text(),'选择文件')]";
    String trueImportCustomFileBtn = ".//*[@id='daoru_tcc']//*[contains(text(),'导 入')]";
    //企业管理-表情管理
    String facePicManagementLink = ".//*[contains(text(),'表情管理')]";
    String facePicDefaultGroupLink =".//a[contains(text(),'默认表情')]";
    String facePicCodeGroupLink =".//a[contains(text(),'符号表情')]";
    String addFaceGroupLink = ".//*[@class='fzd']/div[@class='fq']";
    String addFaceGroupPopInput = ".//*[@id='newexpgp']/div[2]/input";
    String addFaceGroupPopConfirmBtn = ".//*[@id='newexpgp']/div[3]/a[1]";
    //配置管理-接待组
    String settingManagementLink = ".//a[contains(text(),'配置管理')]";
    String receptionLink = ".//*[@id='reception']/li[1]/a";
    String addNewReceptionBtn = ".//button[contains(text(),'新增接待组')]";
    String newReceptionNameInput =  ".//*[@id='groupname']";
    String addNewReceptionNextBtn = ".//*[@id='next']";
    String rankTypeSelection  =".//*[@id='button_yxj']" ;
    String userCheckBox = ".//span[contains(text(),'steven/')]/preceding-sibling::input";
    String receptionRowBtn = ".//span[contains(text(),'小能技术支持(勿删)')]/parent::li/parent::ul//*[@class='icon_right']";
    String newGroupStyleConfirmBtn = ".//*[@id='fsave']";
    //呼叫中心
    String callCenterLink = ".//a[contains(text(),'呼叫中心')]";
    String phoneSetLink = ".//*[@id='phoneset']/li[1]/a";
    //关键页面
    String keyPageLink = ".//a[contains(text(),'关键页面')]";
    //自动应答
    String autoResponseLink = ".//a[contains(text(),'自动应答')]";
    //黑名单
    String blackPaperLink = ".//a[contains(text(),'黑名单')]";
    //通知
    String noticesLink = ".//a[contains(text(),'通知')]";

    public boolean  noticesHandle() throws InterruptedException {if(!clickElement(noticesLink))return false;return true;}
    public boolean blackPaperHandle () throws InterruptedException {if(!clickElement(blackPaperLink))return false;return true;}
    public boolean autoResponseHandle() throws InterruptedException {if(!clickElement(autoResponseLink))return false;return true;}
    public boolean keyPageHandle() throws InterruptedException {if(!clickElement(keyPageLink))return false;return true;}
    public boolean callCenterHandle () throws InterruptedException {if(!clickElement(callCenterLink) || !clickElement(phoneSetLink)) return false; return true;}
    public boolean createGroupHandle() throws InterruptedException {
        if(!clickElement(accountManageLink)||!clickElement(createGroupBtn)||
                !sendKeyElement(newGroupInput,"NX_TEST") || !clickElement(newGroupConfirmBtn)) return false; return true;
    }
    public boolean checkNewGroup () throws InterruptedException {
        /*点击配置管理*/
        String settingLinkXpathStr = ".//a[contains(text(),'配置管理')]";
        if(!isExistNew(".//a[contains(text(),'配置管理')]/following-sibling::div[@class='icon_posi icon01']") )
            if(!clickElement(settingLinkXpathStr)) return false;

        /*点击接待组*/
        if (!clickElement(".//*[@id='reception']/li[1]/a")) return false;

        Thread.sleep(2000);
        if(isExistNew(".//p[contains(text(),'__XN__TEST_______')]")) {
            testResultLog.info("存在接待组");
            return true;
        }
        else {
            testResultLog.info("不存在接待组");
        }
        return false;
    }
    public boolean createNewReceptionGroup() throws InterruptedException {
        checkNewGroup();
        if (!clickElement(addNewReceptionBtn)) return false;
        sendKeyElement(newReceptionNameInput,"__XN__TEST_______");
        /*人工优先
        if (isExist(By.xpath(".//*[@id='button_yxj']"))){
            Select dropList = new Select(rankTypeSelection);
            Thread.sleep(1000);
            dropList.selectByIndex(0);
            Thread.sleep(1000);
        }*/
        List<WebElement>groupNames = driver.findElements(By.xpath(".//div[@class='grcyy_title']/ul/li[@class='l1']/span[@class='f_b']"));

        for (WebElement tempElement:groupNames) {
            if (tempElement.getText().equals("小能技术支持(勿删)")){
                clickElement(strAppend("小能技术支持(勿删)"));
                break;
            }
            if (tempElement.getText().equals("ntalker技术支持(勿删)")){
                clickElement(strAppend("ntalker技术支持(勿删)"));
                break;
            }
            if (tempElement.getText().equals("ntalker测试(勿删)")){
                clickElement(strAppend("ntalker测试(勿删)"));
                break;
            }
            if (tempElement.getText().equals("Ntalker测试勿删除")){
                clickElement(strAppend("Ntalker测试勿删除"));
                break;
            }
            if (tempElement.getText().equals("ntalker测试用(勿删)")){
                clickElement(strAppend("ntalker测试用(勿删)"));
                break;
            }
            if (tempElement.getText().equals("ntalker技术支持(勿删)")){
                clickElement(strAppend("ntalker技术支持(勿删)"));
                break;
            }
            if (tempElement.getText().equals("技术支持(勿删)")){
                clickElement(strAppend("技术支持(勿删)"));
                break;
            }
            if (tempElement.getText().contains("技术支持")){
                clickElement(strAppend("技术支持"));
                break;
            }
            if (tempElement.getText().equals("小能测试")){
                clickElement(strAppend("小能测试"));
                break;
            }
            if (tempElement.getText().equals("小能测试勿删")){
                clickElement(strAppend("小能测试勿删"));
                break;
            }
//            if (tempElement.getText().equals("研发测试帐号")){
//                String temp = strAppend("研发测试帐号");
//                isExistAndClick(temp);
//                break;
//            }
            if (tempElement.getText().equals("小能测试勿删")){
                clickElement(strAppend("小能测试勿删"));
                break;
            }
            if (tempElement.getText().equals("技术支持(勿删)")){
                String temp = strAppend("技术支持(勿删)");
                clickElement(temp);
                break;
            }
        }
        if(!clickElement(userCheckBox)||!clickElement(addNewReceptionNextBtn) || !clickElement(newGroupStyleConfirmBtn))return false;
        return true;
    }
    public String strAppend(String name){
        return ".//span[contains(text(),'"+name+"')]/parent::li/parent::ul//*[@class='icon_right']";
    }
    public void deleteNewReceptionGroup() throws InterruptedException {
        checkNewGroup();
        if(isExist(".//p[contains(text(),'__XN__TEST_______')]")) {
        /*点击组名为'__XN__TEST_______'对应的删除按钮*/
            String deleteLinkXpathStr = ".//p[contains(text(),'__XN__TEST_______')]/parent::div/p/a[contains(text(),'删除')]";
        /*寻找同名组，存在则删除失败，该记录的程序停止*/
            List<WebElement> nameArray = driver.findElements(By.xpath(deleteLinkXpathStr));
            if (nameArray.size() > 1) {
                System.out.println("存在同名元素删除接待组失败" + this.url);
                testResultLog.info("存在同名元素删除接待组失败" + this.url);
                return;
            }
        /*点击删除*/
            if (!clickElement(deleteLinkXpathStr)) return;
        /*点击确认按钮*/
            String confirmBtnXpathStr = ".//input[@onclick='settingArray.delSetting()']";
            if (!clickElement(confirmBtnXpathStr)) return;
        /*刷新页面*/
            driver.navigate().refresh();
        /*点击删除按钮后该组仍存在，则打印删除失败*/
            if (isExistNew(deleteLinkXpathStr)) {
                System.out.println("删除组失败" + this.url);
                testResultLog.info("删除组失败" + this.url);
                return;
            }
        }else {
            testResultLog.info("删除接待组失败"+this.url);
        }
    }
    public boolean settingManage() throws InterruptedException {
        if(!clickElement(settingManagementLink)||
                !clickElement(receptionLink) ||
                !clickElement(addNewReceptionBtn) ||
                !sendKeyElement(newReceptionNameInput,"__XN__TEST")
                ) return false;
        Select dropList = new Select(driver.findElement(By.xpath(rankTypeSelection)));
        dropList.selectByIndex(0);
        if (!clickElement(receptionRowBtn) ||
                !clickElement(userCheckBox)||
                !clickElement(addNewReceptionNextBtn) ||
                !clickElement(newGroupStyleConfirmBtn))
            return false;
        return true;
    }
    /*表情管理
   * 返回resultMap其中 key中为String，分组名； value为Integer，分组的表情数量
   * */
    public Map faceManage() throws InterruptedException {
        if (!clickElement(enterPriseSettingLink)||
                !clickElement(facePicManagementLink) ||
                !clickElement(facePicDefaultGroupLink)) return null;
        List<WebElement> arr = driver.findElements(By.xpath(".//*[@id='exp_default_0']/li/div/img"));
        if (!clickElement(facePicCodeGroupLink)) return null;
        List<WebElement> arr1 = driver.findElements(By.xpath(".//*[@id='exp_default_1']/li/div/img"));
        ArrayList<String> tabTitleStrArr = new ArrayList<String>();
        List<WebElement> tabTitleArr = driver.findElements(By.xpath(".//*[@class='erf']"));
        for (int i = 0; i < tabTitleArr.size(); i++) {
            tabTitleStrArr.add(tabTitleArr.get(i).getText());
        }
        ArrayList<Integer> tabFaceNumArr = new ArrayList<Integer>();
        List<WebElement> tabFaceArr = driver.findElements(By.xpath(".//*[contains(@id,'gpexp')]"));
        for (int i = 0; i <tabFaceArr.size(); i++) {
            tabFaceArr.get(i).click();
            List<WebElement> objs = driver.findElements(By.xpath(".//ul[@style='display: block;']/li/div/img"));
            tabFaceNumArr.add(objs.size());
        }
        Map resultMap = listsToMap(tabTitleStrArr,tabFaceNumArr);
        System.out.println(resultMap);
        return resultMap;
    }
    /*企业常用话术*/
    public boolean enterpriseUsage() throws Exception {
        String enterpriseUsageLink = ".//a[contains(text(),'企业常用话术')]";
        String exportEnterpriseUsageBtn = ".//a[contains(text(),'导出')]";
        String sampleDownloadBtn = ".//a[contains(text(),'下载模版')]";
        if (!clickElement(enterpriseUsageLink) ||
                !clickElement(exportEnterpriseUsageBtn) ||
                !clickElement(sampleDownloadBtn)) return false;
        return true;
    }

    /*咨询评价
    * 返回Map 其中key中enableValue表示否支持主动咨询 true为支持 false为不支持
    * key中enableNum表示几条消息后，允许访客主动发起评价
    * 终端打印结果示例 {enableNum=1, enableValue=true}
    * */
    public Map evaluation() throws InterruptedException {
        String evaluationLink = ".//a[contains(.,'咨询评价')]";
        String activeEvaluateCheckbox = ".//*[@id='zhudong']";
        Map resultMap = new HashMap();
        if(!clickElement(evaluationLink)) return null;
        Object enableValue = elementWaitAndHandle(activeEvaluateCheckbox, new EvaluationInterface() {
            @Override
            public Object action(WebElement element) {
                return element.getAttribute("checked");
            }
        });
        resultMap.put("enableValue",enableValue);
        String activeNumInput = ".//*[@id='zdopt']";
        Object enableNum = elementWaitAndHandle(activeNumInput, new EvaluationInterface() {
            @Override
            public Object action(WebElement element) {
                return element.getAttribute("value");
            }
        });
        resultMap.put("enableNum",enableNum);
        return resultMap;
    }
    //    public SettingPageBToC (String url) {
//        this.url = url;
//        fetchPageObject(this);
//    }
    public SettingPageBToC(String url, String savePath, String siteid) {
        if (url != null && savePath != null && siteid != null) {
            this.url = url;
            this.siteid = siteid;
            this.savePath = savePath;
            fetchPageObject(this);
        }
    }
}