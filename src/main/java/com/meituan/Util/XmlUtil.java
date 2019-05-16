package com.meituan.Util;

import com.meituan.Server.Servers;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/*
自动生成testng.xml文件,直接传入需要创建的类就行
 */
public class XmlUtil {

    //多台设备执行的classname相同
    public  void createTestngXml(String classname,String testngxmldir,List<Integer> portsp) throws Exception {
        Servers servers = new Servers();
        //获取连接的设备信息
        List<String> devices = servers.getDevices();
//        //获取设备的端口信息,不能这样写，会存在重新生成端口号的问题
//        List<Integer> ports = servers.getPortList(4723);


        //创建文件
        Document document = DocumentHelper.createDocument();
        //创建root元素 suite
        Element root = DocumentHelper.createElement("suite");
        //并把它设置为root
        document.setRootElement(root);
        //为root元素 suite 增加三个属性
        root.addAttribute("name","suite");
        root.addAttribute("parallel","tests");
        root.addAttribute("thread-count",String.valueOf(devices.size()));//进程数等于设备数，有几台设备就设置几个进程

        //根据多个设备生成多个test集,生成test集中数据信息
        for (int j=0;j<devices.size();j++){
            Element test = root.addElement("test");
            test.addAttribute("name",devices.get(j));

            Element paraudid = test.addElement("parameter");
            paraudid.addAttribute("name","udid");
            paraudid.addAttribute("value",devices.get(j));

            Element paraport = test.addElement("parameter");
            paraport.addAttribute("name","port");
            paraport.addAttribute("value",String.valueOf(portsp.get(j)));

            Element classes = test.addElement("classes");

            Element classnode = classes.addElement("class");
            classnode.addAttribute("name",classname);
        }

        //增加listeners元素
        Element listeners = root.addElement("listeners");
        //为listeners元素增加子元素listener
        Element listener = listeners.addElement("listener");
        listener.addAttribute("class-name","com.meituan.Util.ExtentTestNGIReporterListener");


        //往xml中写入以上数据
        OutputFormat outputFormat = new OutputFormat("    ", true);
        XMLWriter xmlWriter;
        try {
            xmlWriter = new XMLWriter(new FileOutputStream(testngxmldir),outputFormat);
            xmlWriter.write(document);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //每台设备执行的测试类不同，classname值不同，需要传不同的值
    public void createTestngSingleXml(List<String> classnames,String testngxmldir,List<Integer> portsp) throws Exception {
        Servers servers = new Servers();
        //获取连接的设备信息
        List<String> devices = servers.getDevices();
//        //获取设备的端口信息
//        List<Integer> ports = servers.getPortList(4723);


        //创建文件
        Document document = DocumentHelper.createDocument();
        //创建root元素 suite
        Element root = DocumentHelper.createElement("suite");
        //并把它设置为root
        document.setRootElement(root);
        //为root元素 suite 增加三个属性
        root.addAttribute("name","suite");
        root.addAttribute("parallel","tests");
        root.addAttribute("thread-count",String.valueOf(devices.size()));//进程数等于设备数，有几台设备就设置几个进程

        //根据多个设备生成多个test集,生成test集中数据信息
        for (int j=0;j<devices.size();j++){
            Element test = root.addElement("test");
            test.addAttribute("name",devices.get(j));

            Element paraudid = test.addElement("parameter");
            paraudid.addAttribute("name","udid");
            paraudid.addAttribute("value",devices.get(j));

            Element paraport = test.addElement("parameter");
            paraport.addAttribute("name","port");
            paraport.addAttribute("value",String.valueOf(portsp.get(j)));

            Element classes = test.addElement("classes");

            Element classnode = classes.addElement("class");
            classnode.addAttribute("name",classnames.get(j));
        }

        //增加listeners元素
        Element listeners = root.addElement("listeners");
        //为listeners元素增加子元素listener
        Element listener = listeners.addElement("listener");
        listener.addAttribute("class-name","com.meituan.Util.ExtentTestNGIReporterListener");


        //往xml中写入以上数据
        OutputFormat outputFormat = new OutputFormat("    ", true);
        XMLWriter xmlWriter;
        try {
            xmlWriter = new XMLWriter(new FileOutputStream(testngxmldir),outputFormat);
            xmlWriter.write(document);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //每台设备执行的测试类相同，测试类有多个
    public void createTestngXml(List<String> classnames,String testngxmldir,List<Integer> portsp) throws Exception {
        Servers servers = new Servers();
        //获取连接的设备信息
        List<String> devices = servers.getDevices();
//        //获取设备的端口信息
//        List<Integer> ports = servers.getPortList(4723);


        //创建文件
        Document document = DocumentHelper.createDocument();
        //创建root元素 suite
        Element root = DocumentHelper.createElement("suite");
        //并把它设置为root
        document.setRootElement(root);
        //为root元素 suite 增加三个属性
        root.addAttribute("name","suite");
        root.addAttribute("parallel","tests");
        root.addAttribute("thread-count",String.valueOf(devices.size()));//进程数等于设备数，有几台设备就设置几个进程

        //根据多个设备生成多个test集,生成test集中数据信息
        for (int j=0;j<devices.size();j++){
            Element test = root.addElement("test");
            test.addAttribute("name",devices.get(j));

            Element paraudid = test.addElement("parameter");
            paraudid.addAttribute("name","udid");
            paraudid.addAttribute("value",devices.get(j));

            Element paraport = test.addElement("parameter");
            paraport.addAttribute("name","port");
            paraport.addAttribute("value",String.valueOf(portsp.get(j)));

            Element classes = test.addElement("classes");

            for (String classname : classnames){
                Element classnode = classes.addElement("class");
                classnode.addAttribute("name",classname);
            }
        }

        //增加listeners元素
        Element listeners = root.addElement("listeners");
        //为listeners元素增加子元素listener
        Element listener = listeners.addElement("listener");
        listener.addAttribute("class-name","com.meituan.Util.ExtentTestNGIReporterListener");


        //往xml中写入以上数据
        OutputFormat outputFormat = new OutputFormat("    ", true);
        XMLWriter xmlWriter;
        try {
            xmlWriter = new XMLWriter(new FileOutputStream(testngxmldir),outputFormat);
            xmlWriter.write(document);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        XmlUtil xmlUtil = new XmlUtil();
        String testngxmldir = "src/main/resources/testng2.xml";

        //多台设备执行的classname相同
//        xmlUtil.createTestngXml("com.meituan.Testmain");

        List<String> classnames = new ArrayList<String>();
        classnames.add("com.meituan.Testmain");
        classnames.add("com.meituan.Testmain2");

        //每台设备执行的测试类不同，classname值不同，需要传不同的值
//        xmlUtil.createTestngSingleXml(classnames);

        //每台设备执行的测试类相同，测试类有多个
//        xmlUtil.createTestngXml(classnames,testngxmldir);

    }
}
