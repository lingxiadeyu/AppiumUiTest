package com.meituan;
import com.meituan.Pages.*;
import com.meituan.Util.Action;
import com.meituan.Util.ElementSource;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestChromeDriver {
    public AppiumDriver driver;

    @BeforeMethod
    public void AndroidSettings() throws MalformedURLException {

        //创建设备属性对象
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //设置设备系统为Android
        capabilities.setCapability("platformName", "Android");
        //设置设备名称为PBVGK16918902121，可以通过usb连接手机，在cmd中输入命令，adb devices 看到设备名称
        capabilities.setCapability("deviceName","PBVGK16918902121");
        //设置设备安卓系统，可以在手机的设置中查看系统号
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        //使用Unicode编码方式发送字符串
        capabilities.setCapability("unicodeKeyboard","True");
        //隐藏键盘 ,这样才能输入中文
        capabilities.setCapability("resetKeyboard","True");
        //设置测试机与idea的通讯协议
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("driver is launched!");


    }

    @Test
    public void testchromedriver() throws InterruptedException {
        driver.get("http://i.meituan.com/guide");
        Thread.sleep(2000);
        //不太明白为什么要切换到这个原生app中，可以在appium日志中查看context的值，类似handles
        driver.context("NATIVE_APP");
        //定位到继续访问触屏版点击
        driver.tap(1,500,1400,3);
        Thread.sleep(1000);

        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts){
            System.out.println(context);
        }

        //切换到webview页面
        driver.context("WEBVIEW_1");

        //定位元素
        List<WebElement> elements = driver.findElements(By.className("icon-list page next"));
        elements.get(0).click();
//        driver.findElement(By.className("single-line")).click();
        Thread.sleep(1000);

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
