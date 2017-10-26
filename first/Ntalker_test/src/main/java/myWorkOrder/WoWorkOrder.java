package myWorkOrder;

import com.sun.jdi.InternalException;
import ntest.pageObject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by ntalker on 2017/5/27.
 */
public class WoWorkOrder extends BasePageObject {
     public WoWorkOrder (String url, String siteid) throws InternalException{
          this.url=url;
          this.siteid=siteid;
          fetchPageObject(this);
     }

     public void  myWorkOrder()throws  InternalException{
         List al = driver.findElements(By.xpath(".//*[@class='lefts']/ul/div"));

         for (int x = 0; x<al.size(); x++) {
             String s =  al.get(x).toString();
             String  a = s.split("xpath:")[1];
             System.out.println("al<"+x+">="+a);

//               logger("al["+x+"]"+al.get(x).toString());
//               al.get(x).get_attribute("innerText");
         }
     }

    public  static  void  main(String [] args){
         String url= "http://nt-v1-sticket.ntalker.com/sticket/index.php?m=Home&c=TicketData&a=index&kfuserid=kf_4607_ISME9754_T2D_ralf&siteid=kf_4607&token=8ebae6426447491bb47c014003ea25ea&rnd=1495867371252";
         String siteid = "kf_4607";
         WoWorkOrder gxb = new WoWorkOrder(url,siteid);
         gxb.load();
         gxb.isLoaded();
         gxb.myWorkOrder();

    }
}
