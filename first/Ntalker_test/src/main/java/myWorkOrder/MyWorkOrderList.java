package myWorkOrder;

import ntest.bean.MonitorTask;
import ntest.pageObject.ActivityRecord.ActivityRecordBToCModule;
import ntest.pageObject.BasePageObject;
import ntest.util.SeleniumUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ntalker on 2017/5/26.
 */
public class MyWorkOrderList extends BasePageObject{
//    private MonitorTask _monitorTask = null;
//    public ExecuteTest(MonitorTask monitorTask) {
//        this._monitorTask = monitorTask;
//    }

    public MyWorkOrderList(String url) {
        this.url = url;
      fetchPageObject(this);
    }


    public void  myWorkOrderList(){
        List<ArrayList> arr =(List<ArrayList>)driver.findElement(By.xpath(".//*[@class='s_left_cont']/div "));
        for (int x=1; x<arr.size();x++) {
            System.out.println(arr.get(x).size());
        }

        Map map = new HashMap<String,String>();
        map.put("","value");
        Object value = map.get("key");

        // Allwork_select Allwork_delete
        //Allwork - Map  select - ******
        map.size();

    }


    public static void main(String [] args){
        String url = "http://nt-v1-record.ntalker.com/index.php?c=call&m=call_record&action=search&userid=kf_4607_ISME9754_T2D_ntalker_steven&token=7d55ee5ac7bf4912d4dcc7b2ce7552d1&begindate=2017/05/25&enddate=2017/05/25&enddate=2017/05/25&begindate=2017/05/25&thlx=1&thsc==,10,1";
          MyWorkOrderList  gxb = new MyWorkOrderList (url);
          gxb.load();
          gxb.isLoaded();
          gxb.myWorkOrderList();
      }
}
