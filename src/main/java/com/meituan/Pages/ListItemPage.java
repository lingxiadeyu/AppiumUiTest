package com.meituan.Pages;

import io.appium.java_client.android.AndroidDriver;
/*
门店列表页
 */
public class ListItemPage extends PageBase{
    public AndroidDriver androidDriver;

    public ListItemPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public  String startelement = "虾吃虾涮（安贞店）";
    public  String endelement = "金湘玉食府";








}
