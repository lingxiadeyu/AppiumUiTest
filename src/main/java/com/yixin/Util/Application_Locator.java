package com.yixin.Util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/*
定义元素定位，设置显示等待时间，如果元素在等待时间内没有出现就不再等待，获取页面对象信息
 */
public class Application_Locator {
    //    public static AndroidDriver<?> wd = null; //暂时不用，用APPiumDriver
    APPlication_AppiumActionPackage aap = null;

    public void setAap(APPlication_AppiumActionPackage aap){
        this.aap = aap;
    }

    //AndroidDriverWait的显示等待，元素出现才返回element
    private WebElement getWebElement(AppiumDriver appiumDriver,final By by){
        //先去掉AndroidDriverWait的显示等待，本例使用的是APPiumDriver的隐式等待
//        WebElement element = new AndroidDriverWait(wd,10).until(new ExpectedCondition<WebElement>() {
//            public WebElement apply(AndroidDriver androidDriver){
//                WebElement element1 = androidDriver.findElement(by);
//                if (element1.isDisplayed() != true){
//                    return null;
//                }else {
//                    return element1;
//                }
//            }
//
//
//        });

        //使用隐式等待
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement element = appiumDriver.findElement(by);
        return element;
    }

    public APPlication_AppiumActionPackage getLocator(AndroidDriver androidDriver,By by){
        WebElement element = getWebElement(androidDriver,by);
        aap = new APPlication_AppiumActionPackage();
        aap.setElement(element);
        return aap;
    }



}
