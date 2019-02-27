package com.meituan.Pages;
/*
存放每个页面共用的内容，比如每个页面的左上角的返回
 */
public class PageBase {

    public  String getElement(String originalStr,String newValue){
        return String.format(originalStr,newValue);
    }
}
