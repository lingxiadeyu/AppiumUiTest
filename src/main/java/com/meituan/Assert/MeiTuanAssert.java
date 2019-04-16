package com.meituan.Assert;

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
import com.meituan.Util.Action;
import com.meituan.Util.ElementSource;
import io.appium.java_client.android.AndroidDriver;
/*
断言类，为每一个测试用例提供断言方法
 */
public class MeiTuanAssert {
    AndroidDriver driver;
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
    public MeiShiHomePage meiShiHomePage;//美食业务--首页
    public MeiShiMerchantDetailPage meiShiMerchantDetailPage;//美食业务--门店详情页
    public Action action;//所有操作
    public ElementSource elementSource;//从yaml中获取资源信息

    public MeiTuanAssert(AndroidDriver androidDriver) {
        this.driver = androidDriver;
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
        meiShiHomePage = new MeiShiHomePage(driver);
        meiShiMerchantDetailPage = new MeiShiMerchantDetailPage(driver);
        action = new Action(driver);
        elementSource = new ElementSource();

    }


}
