package com.yixin.Operation;

import org.openqa.selenium.By;

public class ElementOperation {

    //通过传入name值获取元素定位
    public static By byname(String namevalue){
        return By.name(String.format("%1$s",namevalue));
    }

    //通过传入xpath值获取元素定位
    public static By byxpath(String xpathvalue){
        return By.xpath(String.format("%1$s",xpathvalue));
    }
}
