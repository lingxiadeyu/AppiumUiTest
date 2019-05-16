package com.meituan;

import com.meituan.Server.Servers;
import com.meituan.Util.SendMailUtil;
import com.meituan.Util.XmlUtil;
import org.testng.TestNG;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
一键启动appium服务、生成testng.xml、执行testng.xml
 */
public class ExecMain {


//    private static String path = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException, InterruptedException, MessagingException {
        Servers servers = new Servers();
        SendMailUtil sendMailUtil = new SendMailUtil();
        XmlUtil xmlUtil = new XmlUtil();
        String testngxmldir = "src/main/resources/testng2.xml";

        if (servers.killserver()){
            try{
                //启动服务,启动服务的日志在D:\logs\设备号.log文件中可以看到
                System.out.println("---------------------开始启动服务---------------------------");
                List<Integer> portsp = servers.execcommand();
                System.out.println("---------------------服务启动结束---------------------------");

                //生成testng.xml
                System.out.println("---------------------开始生成testng.xml---------------------");
                List<String> classnames = new ArrayList<String>();
                classnames.add("com.meituan.Testmain");
                classnames.add("com.meituan.HomePageTest");
                xmlUtil.createTestngXml(classnames,testngxmldir,portsp);
                System.out.println("---------------------生成testng.xml结束---------------------");

                //执行testng.xml
                System.out.println("---------------------开始执行testng.xml---------------------");
                List<String> suites = new ArrayList<String>();
                suites.add(testngxmldir);//可以设置多个testng.xml文件同时执行
                TestNG testNG = new TestNG();
                testNG.setTestSuites(suites);
                testNG.run();
                System.out.println("---------------------执行testng.xml结束---------------------");
            }catch (Exception e){
                e.printStackTrace();
            }

        }else {
            System.out.println("appium服务进程没有被杀死");
            sendMailUtil.send("appium服务进程没有被杀死,请人工删除进程。Taskkill -F -PID node.exe");
        }

    }
}
