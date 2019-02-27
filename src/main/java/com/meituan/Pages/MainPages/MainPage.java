package com.meituan.Pages.MainPages;

import io.appium.java_client.android.AndroidDriver;

/*
我的页面
 */
public class MainPage {
    public AndroidDriver androidDriver;

    public MainPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public String clicklogin = "请点击登录";

    //左上角设置按钮
    public String settingbutton = "com.sankuai.meituan:id/settings";

}
