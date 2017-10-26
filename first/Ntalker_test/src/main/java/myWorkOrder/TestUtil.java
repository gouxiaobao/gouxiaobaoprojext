package myWorkOrder;

import ntest.pageObject.BasePageObject;
import org.openqa.selenium.WebElement;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ntalker on 2017/5/27.
 */
public class TestUtil extends BasePageObject {

    static Map allElement = new HashMap<String,Map>();

    static {
        Map myWork;
        //全部工单
        myWork = new HashMap<String,AbstractTest>();

        myWork.put("全部工单", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        //我的工单
        myWork = new HashMap<String, AbstractTest>();
        myWork.put("我的工单", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });


        myWork.put("待处理的", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });


        myWork.put("已完成的", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });


        myWork.put("处理中的", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });


        myWork.put("已完成的", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });


        myWork.put("转派工单", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        allElement.put("我的工单",myWork);
        myWork = null;

        //我的提醒
        myWork = new HashMap<String, AbstractTest>();
        myWork.put("我的提醒", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        myWork.put("过去30天", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        myWork.put("过去7天", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        myWork.put("昨天", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        myWork.put("今日提醒", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        myWork.put("3天内提醒", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        myWork.put("7天内提醒", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });
        allElement.put("我的提醒",myWork);
        myWork=null;


        myWork = new HashMap<String, AbstractTest>();

        myWork.put("下属工单", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        myWork.put("待处理的", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        myWork.put("处理中的", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        myWork.put("已完成的", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });

        myWork.put("转派工单", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });
        allElement.put("下属工单",myWork);
        myWork=null;

        myWork = new HashMap<String,AbstractTest>();
        myWork.put("过去30天", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });
        myWork.put("过去七天", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });
        myWork.put("昨天", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });
        myWork.put("今日提醒", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });
        myWork.put("3天内提醒", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });
        myWork.put("7天内提醒", new AbstractTest() {
            @Override
            void innerRun(Object[] objects) {

            }
        });
        allElement.put("下属提醒",myWork);



        allElement.put("我的提醒",myWork);
        //下属工单
        //下属提醒
        //常用搜索
    }
}

//内部抽象类
abstract class AbstractTest {

    //在此处实现方法，参数由数组形式传入
    abstract void innerRun(Object[] objects);

    //在outerRun方法中实现异常处理，对象直接调用outerRun方法
    public void outerRun(Object[] objects){
        try {
            this.innerRun(objects);
        } catch (Exception e) {

        }
    }
}