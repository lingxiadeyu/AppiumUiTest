package com.yixin.Operation;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class SwipeCenter {

    /*
    方法参数介绍：
    driver（就是把appiumdriver对象传进来）
    during（这里是填写毫秒数，这里的 毫秒数越小 滑动的速度越快~ 一般设定在500~1000，如果你想快速滑动 那就可以设置的更加小）
    num（是只滑动的次数，如相册多张图片翻页测试什么的滑动或者滑动到列表底部。就直接输入次数就行了）
     */

    //上滑 swipeToUp
    public static void swipeToUp(AndroidDriver driver, int during, int num) throws InterruptedException {
        //先获取屏幕的分辨率
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Thread.sleep(2000);
        for (int i=0;i<num;i++){
            driver.swipe(width/2,height*3/4,width/2,height/4,during);
            Thread.sleep(2000);
        }

    }
    //下拉 swipeToDown
    public static void swipeToDown(AndroidDriver<WebElement> driver,int during,int num) throws InterruptedException {
        //先获取屏幕的分辨率
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        //等待2秒
        Thread.sleep(2000);
        for (int i=0;i<num;i++){
            driver.swipe(width/2,height/4,width/2,height*3/4,during);
            //等待2秒
            Thread.sleep(2000);
        }

    }
    //向左滑动 swipeToLeft
    public static void swipeToLeft(AndroidDriver<WebElement> driver,int during,int num) throws InterruptedException {
        //先获取屏幕的分辨率
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        //等待2秒
        Thread.sleep(2000);
        for (int i=0;i<num;i++){
            driver.swipe(width*3/4,height/2,width/4,height/2,during);
            //等待2秒查看界面变动
            Thread.sleep(2000);
        }

    }
    //向右滑动 swipeToRight
    public static void swipeToRight(AndroidDriver<WebElement> driver,int during,int num) throws InterruptedException {
        //先获取屏幕的分辨率
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        //等待2秒
        Thread.sleep(2000);
        for (int i=0;i<num;i++){
            driver.swipe(width/4,height/2,width*3/4,height/2,during);
            //等待2秒查看界面变动
            Thread.sleep(2000);
        }

    }



}
