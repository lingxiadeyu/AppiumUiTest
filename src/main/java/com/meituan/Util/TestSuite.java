package com.meituan.Util;

import com.meituan.Assert.MeiTuanAssert;
import com.meituan.Business.MeiTuanBusiness;
import com.meituan.Pages.*;
import com.meituan.Pages.HomePages.CitynamePage;
import com.meituan.Pages.HomePages.HomePage;
import com.meituan.Pages.HomePages.SearchMerchantPage;
import com.meituan.Pages.MainPages.MainPage;
import com.meituan.Pages.MainPages.SettingPage;
import com.meituan.Pages.MeiShiPages.MeiShiHomePage;
import com.meituan.Pages.MeiShiPages.MeiShiMerchantDetailPage;
import com.meituan.Pages.OrderPages.EvaluatePage;
import com.meituan.Pages.MainPages.LoginPage;
import com.meituan.Pages.OrderPages.OrderPage;
import com.meituan.Pages.OrderPages.RefundPage;
import com.meituan.Pages.WaiMaiPages.WaiMaiHomePage;
import com.meituan.Pages.WaiMaiPages.WaiMaiMerchantDetailPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/*
初始化AndroidSettings
美团入口activity：com.meituan.android.pt.homepage.activity.Welcome

也是业务层
edaed87d
PBVGK16918902121
 */

public class TestSuite {

    public AppiumDriver<WebElement> appiumDriver;
    public AndroidDriver driver;
    public HomePage homePage;//首页
    public ListItemPage listItemPage;//门店列表页
    public MainPage mainPage;//我的页面
    public LoginPage loginPage;//登录页面
    public SettingPage settingPage;//设置页面
    public GroupDetailPage groupDetailPage;
    public CitynamePage citynamePage;//选择城市页面
    public SearchMerchantPage searchMerchantPage;//搜索页面
    public OrderPage orderPage;//我的订单页面
    public EvaluatePage evaluatePage;//评价页面
    public RefundPage refundPage;//退款页面
    public WeixinPage weixinPage;//微信页面
    public MeiShiHomePage meiShiHomePage;//美食业务--首页
    public MeiShiMerchantDetailPage meiShiMerchantDetailPage;//美食业务--门店详情页
    public WaiMaiHomePage waiMaiHomePage;//外卖业务--首页
    public WaiMaiMerchantDetailPage waiMaiMerchantDetailPage;//外卖业务--门店详情页
    public Action action;//所有操作
    public ElementSource elementSource;//从yaml中获取资源信息
    public MeiTuanBusiness meiTuanBusiness;
    public MeiTuanAssert meiTuanAssert;

    @BeforeMethod
    public void AndroidSettings() throws MalformedURLException {
        //获取当前根目录下的文件，当前根目录就是D:\Springboot\MyApplicationthree,项目名是MyApplicationthree
        File classpathRoot = new File(System.getProperty("user.dir"));
        //获取apps目录下的文件
        File appDir = new File(classpathRoot, "/apps");
        //获取蘑菇智行.apk，需要提前把该apk放到apps文件夹下
        File app = new File(appDir, "meituan-9.12.801.apk");

        //创建设备属性对象
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //设置设备系统为Android
        capabilities.setCapability("platformName", "Android");
        //设置设备名称为PBVGK16918902121，可以通过usb连接手机，在cmd中输入命令，adb devices 看到设备名称
        capabilities.setCapability("deviceName","edaed87d");
        //设置设备安卓系统，可以在手机的设置中查看系统号
        capabilities.setCapability("platformVersion", "6.0.1");

        //获取app的绝对路径
//        capabilities.setCapability("app", app.getAbsolutePath());
        //设置app的包名，通过usb连接手机，在手机中打开app，在cmd中输入命令adb shell dumpsys window | findstr mCurrentFocus 查看包名
        capabilities.setCapability("appPackage", "com.sankuai.meituan");
        //设置app的入口，通过反编译可以查到该值，参考apk反编译文档获取该值
        capabilities.setCapability("appActivity", "com.meituan.android.pt.homepage.activity.MainActivity");
        //设置每次运行不清空app的数据
        capabilities.setCapability("noReset","true");
//        //设置每次运行不重签名app
//        capabilities.setCapability("noSign","true");
        //使用Unicode编码方式发送字符串
        capabilities.setCapability("unicodeKeyboard","True");
        //恢复键盘输入法，也可以说是隐藏键盘（要输入中文，需要先把手机上的输入法调整成appium android input manager for unicode)
        //如果需要把键盘调出来只能手工的去改输入法
        capabilities.setCapability("resetKeyboard","True");

        //设置测试机与idea的通讯协议
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println("App is launched!");

        homePage = new HomePage(driver);
        listItemPage = new ListItemPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        settingPage = new SettingPage(driver);
        groupDetailPage = new GroupDetailPage(driver);
        citynamePage = new CitynamePage(driver);
        searchMerchantPage = new SearchMerchantPage(driver);
        orderPage = new OrderPage(driver);
        evaluatePage = new EvaluatePage(driver);
        refundPage = new RefundPage(driver);
        weixinPage = new WeixinPage(driver);
        meiShiHomePage = new MeiShiHomePage(driver);
        meiShiMerchantDetailPage = new MeiShiMerchantDetailPage(driver);
        waiMaiHomePage = new WaiMaiHomePage(driver);
        waiMaiMerchantDetailPage = new WaiMaiMerchantDetailPage(driver);
        action = new Action(driver);
        elementSource = new ElementSource();
        meiTuanBusiness = new MeiTuanBusiness(driver);
        meiTuanAssert = new MeiTuanAssert(driver);

    }

}
