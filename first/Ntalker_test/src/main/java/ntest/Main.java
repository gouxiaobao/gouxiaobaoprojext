package ntest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ntest.action.ExecuteTest;
import ntest.action.ResulInspect;
import ntest.bean.MonitorTask;
import ntest.util.ReadExcel;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Iterator;
/**
 * Created by guo on 2017/3/4.
 */
public class Main {

    protected static org.slf4j.Logger log = LoggerFactory.getLogger(Main.class);
//    private static Logger testResultLog  =  Logger.getLogger("TestResult");

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure("./config/log4j.properties");
        Main.dataDriver();
    }

    public static void dataDriver () throws Exception {
        ResulInspect resulInspect = new ResulInspect();
        String path = "./config/TestCase.xls";
        if (path == null){
            return;
        }
        ReadExcel readInputFile= new ReadExcel(path);
        JSONArray _array=readInputFile.readExcelXlsx();
        if(_array == null){
            return;
        }
        for (Iterator iterator = _array.iterator(); iterator.hasNext();) {
            JSONObject job = (JSONObject) iterator.next();
            MonitorTask _monitorTask = new MonitorTask();
            _monitorTask.siteid = job.get("siteid").toString();
            _monitorTask.waiterName = job.get("waiterName").toString();
            _monitorTask.waiterPassword = job.get("waiterPassword").toString();
//

//            if (_monitorTask.siteid .contains("1000")){}

            log.debug("---------------------------------------------"+_monitorTask.siteid+"StartTest---------------------------------------------");
            resulInspect.init(_monitorTask.siteid);
            if (resulInspect.moitorTaskCheck(_monitorTask)) {
                ExecuteTest executeTest = new ExecuteTest(_monitorTask);
                executeTest.set_resulInspect(resulInspect);
//                executeTest.testActivity();
                for (int x = 0; x <= readInputFile.getFistColumn().size(); x++) {
                    if (readInputFile.getFistColumn().get(0).toString().contains("_1000")) {
                        executeTest.platformDoAction();

                    } else {
                        executeTest.doAction();
                    }
//                executeTest.Test();
                }
            }
            resulInspect.end(_monitorTask.siteid);
            log.debug("---------------------------------------------"+_monitorTask.siteid+"EndTest---------------------------------------------");
        }
    }
}
