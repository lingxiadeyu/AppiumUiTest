package com.meituan.Pages.WaiMaiPages;

import io.appium.java_client.android.AndroidDriver;

public class WaiMaiHomePage {
    AndroidDriver androidDriver;

    public WaiMaiHomePage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    //门店名称
    public String textviewpoinameid = "com.sankuai.meituan:id/textview_poi_name";



}
