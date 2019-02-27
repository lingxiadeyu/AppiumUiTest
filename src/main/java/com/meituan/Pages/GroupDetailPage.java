package com.meituan.Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
/*
商户详情页
 */

public class GroupDetailPage {
    public AndroidDriver androidDriver;

    public GroupDetailPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }
    public String merchantname = "com.sankuai.meituan:id/food_poi_name";

    //导航按钮,右上角3个点
    public String threepointid = "com.sankuai.meituan:id/btn_more_poi_dark";

    //分享商家
    public String sharemerchantname = "分享商家";
    //分享图片,List
    public String shareimageid = "com.sankuai.meituan:id/share_image";





}
