package com.meituan.Pages;
/*
微信页面，会涉及到分享、支付等
 */

import io.appium.java_client.android.AndroidDriver;

public class WeixinPage {
    AndroidDriver androidDriver;

    public WeixinPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    //微信好友
    public String friendname = "文件传输助手";
    //给朋友留言
    public String aziid = "com.tencent.mm:id/azi";
    //点击发送
    public String az_id = "com.tencent.mm:id/az_";
    //点击留在微信
    public String stayweixinname = "留在微信";
    //微信信息中门店名称
    public String aoyid = "com.tencent.mm:id/aoy";


}
