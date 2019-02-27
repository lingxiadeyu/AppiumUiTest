package com.meituan.Pages.HomePages;

import com.meituan.Pages.PageBase;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
/*
搜索页面
 */
public class SearchMerchantPage extends PageBase {
    AndroidDriver androidDriver;

    public SearchMerchantPage(AndroidDriver androidDriver){
        this.androidDriver = androidDriver;
    }

    //搜索框
    public String searcheditid = "com.sankuai.meituan:id/search_edit";
    //搜索按钮
    public String searchbuttonid = "com.sankuai.meituan:id/search";
    //搜索门店结果、根据地点搜索结果都需要用到这个方法
    public List getsearchmerchantname(){
        List<WebElement> getsearchmerchantname = androidDriver.findElements(By.id("com.sankuai.meituan:id/title"));
        return getsearchmerchantname;
    }

    //搜索门店、地址结果
    public String searchmerchanxpath = "//com.sankuai.meituan:id/category[contains(@text,'全部分类')]";
    //搜索省份城市结果
    public String searchareaxpath = "//android.widget.TextView[contains(@text,'%1$s')]";

    //热门搜索和历史记录,选第一个是热门搜索，选最后一个是历史记录
    public List getsearchtagname(){
        List<WebElement> getsearchtagname = androidDriver.findElements(By.id("com.sankuai.meituan:id/tag_text"));
        return getsearchtagname;
    }



}
