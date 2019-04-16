package com.meituan.Pages.MeiShiPages;

import io.appium.java_client.android.AndroidDriver;

/*
美食业务--首页
 */
public class MeiShiHomePage {
    AndroidDriver androidDriver;

    public MeiShiHomePage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    //门店名称
    public String merchantid = "com.sankuai.meituan:id/poi_name";


    //门店图片，因为会出现超值优惠，与门店名称的id存在重复,所以用门店图片定位
    public String poiimageid = "com.sankuai.meituan:id/poi_image";

    //搜索框
    public String homepageactionsearchid = "com.sankuai.meituan:id/homepage_action_search";
    //搜索框输入
    public String searcheditid = "com.sankuai.meituan:id/search_edit";
    //搜索按钮
    public String searchid = "com.sankuai.meituan:id/search";
    //搜索内容
    public String foodsearchtitleid = "com.sankuai.meituan:id/foodsearch_title";

}
