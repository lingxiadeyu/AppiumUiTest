package com.meituan.Pages.HomePages;

import com.meituan.Pages.PageBase;
import com.meituan.Util.ElementSource;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/*
首页页面元素
 */
public class HomePage extends PageBase {
    public AndroidDriver androidDriver;

    public HomePage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    //定位城市
    public String citynameid = "com.sankuai.meituan:id/city_name";

    //搜索框,点击后跳转到搜索页面
    public String searchlayoutid = "com.sankuai.meituan:id/search_layout";


    //banner图片
    public String bannerid = "com.sankuai.meituan:id/mtadvert_ad_view";

    //使用xpath，动态的获取不同的服务球信息
    public String serviceballxpath = "//android.view.View[contains(@content-desc,'%1$s')]";
    public String serviceballclass = "android.view.View";

    //获取服务球的classname，利用list的for循环点击不同的服务球
    public List<WebElement> getserviceballs(){
        List<WebElement> serviceballs = androidDriver.findElements(By.className(serviceballclass));
        return serviceballs;
    }

    //首页按钮
    public String homepage = "首页";
    //我的订单
    public String ordername = "订单";

    //获取门店名称
    public List<WebElement> getmerchanttitle(){
        List<WebElement> getmerchanttitle = androidDriver.findElements(By.id("com.sankuai.meituan:id/title"));
        return getmerchanttitle;
    }

    //获取门店名称resourceid
    public String getmerchanttitleid = "com.sankuai.meituan:id/title";



    //从yaml文件中获取数据信息









}
