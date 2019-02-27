package com.meituan.Pages.OrderPages;
/*
退款页面
 */

import io.appium.java_client.android.AndroidDriver;

public class RefundPage {
    AndroidDriver androidDriver;

    public RefundPage(AndroidDriver androidDriver){
        this.androidDriver = androidDriver;
    }

    //退款状态
    public String  refundstatusid = "com.sankuai.meituan:id/status";
    //退款进度
    public String refundname = "退款进度";
    //退款成功
    public String refundsuccess = "退款成功";
    //退款已入账
    public String refunddone = "退款已入账";
}
