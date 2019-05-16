package com.meituan.Base;

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
import com.meituan.Server.Servers;
import com.meituan.Util.DosCmd;
import com.meituan.Util.ElementSource;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

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

//    public AppiumDriver<WebElement> appiumDriver;
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

//从testng.xml中获取udid和port的参数值
    @BeforeMethod
    @Parameters({"port","udid"})
    public void AndroidSettings(String port,String udid) throws Exception {

        //设置测试机与idea的通讯协议，CapabilitiesSetting.getCapabilititesSetting()获取driver的配置信息。
//        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), CapabilitiesSetting.getCapabilititesSetting());
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:"+port+"/wd/hub"), CapabilitiesSetting.getCapabilititesSetting(udid));
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
