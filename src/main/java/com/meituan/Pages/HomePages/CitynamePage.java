package com.meituan.Pages.HomePages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
/*
搜索城市页面
 */
public class CitynamePage {

    AndroidDriver androidDriver;

    public CitynamePage(AndroidDriver androidDriver){
        this.androidDriver = androidDriver;
    }

    //获取热门城市
    public List gethotcity(){
        List<WebElement> hotcity = androidDriver.findElements(By.id("com.sankuai.meituan:id/city_area_item"));
        return hotcity;
    }

    //切换县区
    public String cityselectareaid="com.sankuai.meituan:id/city_select_area";
    //选择县区，和上面的获取热门城市的resourceid一样，可以默认获取第二个县区
    public List cityselectarea(){
        List<WebElement> selectarea = androidDriver.findElements(By.id("com.sankuai.meituan:id/city_area_item"));
        return selectarea;
    }

    //根据首字母筛选后定位热门城市
    public List citylisttextview(){
        List<WebElement> citylisttextview = androidDriver.findElements(By.id("com.sankuai.meituan:id/citylist_textview"));
        return citylisttextview;
    }

    //进入切换城市页面搜索城市
    public String EditTextclass = "android.widget.EditText";
    public String searchcityid = "com.sankuai.meituan:id/citylist_textview";

    //转到上一层级
    public String imagebuttonclass = "android.widget.ImageButton";

    //选择国际/港澳台
    public String indicatortextname = "国际/港澳台";
    public String tabtextname = "港澳台";
    public List getGangAoTaiCityname(){
        List<WebElement> getGangAoTaiCityname = androidDriver.findElements(By.id("com.sankuai.meituan:id/city_name"));
        return getGangAoTaiCityname;

    }
    //获取当前定位，从港澳台城市中返回到当前定位
    public String locatecontentid = "com.sankuai.meituan:id/locate_content";


}
