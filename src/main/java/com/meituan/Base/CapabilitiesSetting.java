package com.meituan.Base;

import com.meituan.Util.ElementSource;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

/*
设置driver的初始化信息,从yaml中获取配置信息
 */
public class CapabilitiesSetting {

    public static DesiredCapabilities getCapabilititesSetting(String udid){
        ElementSource elementSource = new ElementSource();

        //获取当前根目录下的文件，当前根目录就是D:\Springboot\AppiumTest
        File classpathRoot = new File(System.getProperty("user.dir"));
        //获取apps目录下的文件
        File appDir = new File(classpathRoot, "/apps");
        //需要提前把该apk放到apps文件夹下
        File app = new File(appDir, elementSource.getElementsource("appDir"));

        //创建设备属性对象
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //设置设备系统为Android
        capabilities.setCapability("platformName",elementSource.getElementsource("platformName"));
        //设置设备名称为edaed87d，可以通过usb连接手机，在cmd中输入命令，adb devices 看到设备名称
        // （这个名称写什么都行，通常情况下测试时只会连一台设备，appium会直接去连，如果有多台用udid参数指定设备号）
        capabilities.setCapability("deviceName",elementSource.getElementsource("deviceName"));
        //udid指定设备信息，如果有多台设备这个值会有用,所以用参数的形式传值
        capabilities.setCapability("udid",udid);
        //设置设备安卓系统，可以在手机的设置中查看系统号
        capabilities.setCapability("platformVersion", elementSource.getElementsource("platformVersion"));

        //获取app的绝对路径，如果要重装app的话可以打开配置
//        capabilities.setCapability("app", app.getAbsolutePath());
        //设置app的包名，在cmd中进入aapt.exe的目录输入命令aapt dump badging E:\apk\es3.apk | findstr "package launchable-activity" 查看包名和activity
        capabilities.setCapability("appPackage", elementSource.getElementsource("appPackage"));
        //设置app的入口activity
        capabilities.setCapability("appActivity", elementSource.getElementsource("appActivity"));
        //设置每次运行不清空app的数据
        capabilities.setCapability("noReset",elementSource.getElementsource("noReset"));
//        //设置每次运行不重签名app
//        capabilities.setCapability("noSign",elementSource.getElementsource("noSign"));
        //设置超时时间,超过10分钟，客户端与服务器断开链接
//        capabilities.setCapability("newCommandTimeout",elementSource.getElementsource("newCommandTimeout"));
        //在Android4.2版本以下需要指定这个Selendroid
//        capabilities.setCapability("automationName",elementSource.getElementsource("automationName"));


        //使用Unicode编码方式发送字符串
        capabilities.setCapability("unicodeKeyboard","True");
        //恢复键盘输入法，也可以说是隐藏键盘（要输入中文，需要先把手机上的输入法调整成appium android input manager for unicode)
        //如果需要把键盘调出来只能手工的去改输入法
        capabilities.setCapability("resetKeyboard","True");
        return capabilities;

    }

}
