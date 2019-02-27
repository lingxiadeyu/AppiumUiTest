package com.meituan.Business;
import com.meituan.Pages.*;
import com.meituan.Pages.HomePages.CitynamePage;
import com.meituan.Pages.HomePages.HomePage;
import com.meituan.Pages.HomePages.SearchMerchantPage;
import com.meituan.Pages.MainPages.MainPage;
import com.meituan.Pages.MainPages.SettingPage;
import com.meituan.Pages.OrderPages.EvaluatePage;
import com.meituan.Pages.MainPages.LoginPage;
import com.meituan.Pages.OrderPages.OrderPage;
import com.meituan.Pages.OrderPages.RefundPage;
import com.meituan.Util.Action;
import com.meituan.Util.ElementSource;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

/*
业务层,写一些公用的方法，可以供多个测试用例使用
 */

public class MeiTuanBusiness{


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
    public Action action;//所有操作
    public ElementSource elementSource;//从yaml中获取资源信息

    public MeiTuanBusiness(AndroidDriver androidDriver) {
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
        action = new Action(driver);
        elementSource = new ElementSource();

    }


    //获取元素信息
    public List GetElements(By by){
        List getElements = driver.findElements(by);
        return getElements;
    }


        //从首页进入我的订单页面
    public void enterorderpage() throws InterruptedException, IOException {
        //点击我的订单
        action.click(By.name(homePage.ordername));
        //先判断是否登录
        if (action.isElementPresented(By.id(orderPage.loginbuttonid))) {
            action.Screenshot("测试截图014");
            Thread.sleep(1000);
            action.click(By.id(orderPage.loginbuttonid));
            //操作登录
            action.sendkeys(By.id(loginPage.usernameresourceid), "18600228767");
            action.sendkeys(By.name(loginPage.password), "Qin777QQ");
            action.click(By.name(loginPage.loginbutton));
            System.out.println("登录成功");
        }
        if (action.isElementPresented(By.id(orderPage.titleid))) {
            action.Screenshot("进入我的订单测试截图");
            System.out.println("进入订单页面成功");
        }
    }
}
