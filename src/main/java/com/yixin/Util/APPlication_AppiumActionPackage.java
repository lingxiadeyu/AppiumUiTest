package com.yixin.Util;
import org.openqa.selenium.WebElement;
/*
定义webelement的click、sendkeys方法，定义页面对象信息
 */

public class APPlication_AppiumActionPackage {
    private WebElement element = null;

    public void setElement(WebElement element){
        this.element = element;
    }
    public void Click() throws Exception {
        try {
            element.click();
        }catch (Exception e){
            throw new Exception("click error");
        }
    }
    public void Send_keys(String text) throws Exception {
        try {
            element.sendKeys(text);
        }catch (Exception e){
            throw new Exception("send keys error");
        }
    }

}