package com.meituan.Pages.MainPages;

import io.appium.java_client.android.AndroidDriver;
/*
设置页面
 */
public class SettingPage {
    public AndroidDriver androidDriver;

    public SettingPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }


    //退出账号按钮
    public String logoutbutton = "com.sankuai.meituan:id/logout";
    //退出账户对话框提示,退出按钮
    public String logoutdialogbox1 = "android:id/button1";
    //退出账户对话框提示,取消按钮
    public String logoutdialogbox2 = "android:id/button2";
}
