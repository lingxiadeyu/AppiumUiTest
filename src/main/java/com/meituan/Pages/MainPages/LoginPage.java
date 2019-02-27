package com.meituan.Pages.MainPages;

/*
登录页面
 */

import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
    public AndroidDriver androidDriver;

    public LoginPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }


    public String passwordlogin = "密码登录";
    public String username = "请输入手机号";
    public String usernameresourceid = "com.sankuai.meituan:id/passport_mobile_phone";
    public String password = "请输入密码";
    public String loginbutton = "登录";

}
