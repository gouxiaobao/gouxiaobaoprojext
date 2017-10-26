//package ntest.test;
//
//import com.sun.corba.se.spi.orbutil.threadpool.NoSuchWorkQueueException;
//import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
//import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;
//import ntest.pageObject.BasePageObject;
//import org.openqa.selenium.WebDriver;
//
//import java.io.IOException;
//
///**
// * Created by Administrator on 2017/5/18.
// */
//
//public class Test{
//    static WebDriver driver;
//    public static void main(String[] args) {
//
//        new  AbTest() {
//            @Override
//            abstract    public void runBody(WebDriver driver, String xpathStr) throws Exception {
//                driver.getTitle();
//            }
//        }.run(driver,"xpathStr");
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                new AbTest() {
//                    @Override
//                    public void runBody(WebDriver driver, String xpathStr) throws Exception {
////                        driver.getTitle();
//                    }
//                }.run(driver,"xpathStr");
//            }
//        };
//
//        new Thread(runnable).start();
//
//
//
//        Thread thread0;
//        Thread thread2;
//        Thread thread3;
//        Thread thread4;
//        Thread thread5;
//        Thread thread6;
//        Thread thread7;
//        Thread thread8;
//        Thread thread9;
//
//
//        String[] s = {};
//
//        for (int i = 0 ; i < s.length ; i ++){
//            final String k = s[i];
//            new Thread(){
//                @Override
//                public void run(){
//                    new AbTest() {
//                        @Override
//                        public void runBody(WebDriver driver, String xpathStr) throws Exception {
//                            driver.getTitle();
//                        }
//                    }.run(driver,k);
//                }
//            }.start();
//        }
//
//        new ThreadPool() {
//            @Override
//            public void close() throws IOException {
//
//            }
//
//            @Override
//            public WorkQueue getAnyWorkQueue() {
//                return null;
//            }
//
//            @Override
//            public WorkQueue getWorkQueue(int queueId) throws NoSuchWorkQueueException {
//                return null;
//            }
//
//            @Override
//            public int numberOfWorkQueues() {
//                return 0;
//            }
//
//            @Override
//            public int minimumNumberOfThreads() {
//                return 0;
//            }
//
//            @Override
//            public int maximumNumberOfThreads() {
//                return 0;
//            }
//
//            @Override
//            public long idleTimeoutForThreads() {
//                return 0;
//            }
//
//            @Override
//            public int currentNumberOfThreads() {
//                return 0;
//            }
//
//            @Override
//            public int numberOfAvailableThreads() {
//                return 0;
//            }
//
//            @Override
//            public int numberOfBusyThreads() {
//                return 0;
//            }
//
//            @Override
//            public long currentProcessedCount() {
//                return 0;
//            }
//
//            @Override
//            public long averageWorkCompletionTime() {
//                return 0;
//            }
//
//            @Override
//            public String getName() {
//                return null;
//            }
//        };
//
//    }
//}
