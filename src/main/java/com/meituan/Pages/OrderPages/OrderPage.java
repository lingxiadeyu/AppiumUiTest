package com.meituan.Pages.OrderPages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
我的订单页面
 */
public class OrderPage {
    AndroidDriver androidDriver;

    public OrderPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    //如果没有登录，先点击登录按钮
    public String loginbuttonid = "com.sankuai.meituan:id/button_signin";
    public String logintipsid = "com.sankuai.meituan:id/text_signin_tips";

    //如果登录成功就能看到我的订单的title
    public String titleid = "com.sankuai.meituan:id/order_title";

    //待付款
    public String ordertabunpaidid = "com.sankuai.meituan:id/order_tab_unpaid";
    //待使用
    public String ordertabunusedid = "com.sankuai.meituan:id/order_tab_unused";
    //待评价
    public String ordertabneedfeedbackid = "com.sankuai.meituan:id/order_tab_need_feedback";
    //退款/售后
    public String ordertabrefundid = "com.sankuai.meituan:id/order_tab_refund";
    //全部订单
    public String ordertaballid = "com.sankuai.meituan:id/order_tab_all";


    //评价按钮
    public String evaluatename = "评价";
    //待评价页面的评价按钮
    public String evaluateid = "com.sankuai.meituan:id/to_review_btn";
    //暂无订单id
    public String noorderid = "com.sankuai.meituan:id/main_message";
    //随便逛逛
    public String noOrderbuttonid = "com.sankuai.meituan:id/button";


    //订单已取消\订单已完成
    public String orderdetailtitleid = "com.sankuai.meituan:id/order_detail_title";
    //已取消\已完成等状态
    public List<WebElement> getorderstatus(){
        List<WebElement> getorderstatus = androidDriver.findElements(By.id("com.sankuai.meituan:id/status"));
        return getorderstatus;
    }



}
