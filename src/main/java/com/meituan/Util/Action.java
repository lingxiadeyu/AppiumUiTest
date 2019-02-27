package com.meituan.Util;

/*
元素的操作
 */


import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Action {

    public AndroidDriver androidDriver;
    public TouchAction touchAction;

    public Action(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
        this.touchAction = new TouchAction(androidDriver);
    }

    //定义click事件，传入值为by内容，会从page类中返回by内容
    public void click(By by){
        try{
            //对元素先操作点击，如果失败就进入catch语句中，等待2秒，等待之后再操作点击
            androidDriver.findElement(by).click();

        }catch (Exception e){
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e1){
            }
            androidDriver.findElement(by).click();
        }
    }

    //定义click事件，传入值为webelement内容，会从page类中返回by内容
    public void click(WebElement element){
        try{
            //对元素先操作点击，如果失败就进入catch语句中，等待2秒，等待之后再操作点击
            element.click();

        }catch (Exception e){
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e1){
            }
            element.click();
        }
    }

    /**
     *直到找到元素不再滑动
     * @param endelement
     * @param UporDownorRightorLeft 向上或者向下滑动,1向上滑，2向下滑,3左滑，4右滑
     *
     */
    public void Swipe(By endelement, int UporDownorRightorLeft){

        WebElement element1;
        androidDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //获取页面的宽度和高度
        int width = androidDriver.manage().window().getSize().width;
        int height = androidDriver.manage().window().getSize().height;


        while (true){
            try {
                //找到元素退出当前循环，找不到元素进入catch语句中做滑动页面
                element1 = androidDriver.findElement(endelement);
                break;
            }catch (Exception e){
                switch (UporDownorRightorLeft){
                    case 1://"up"
                        androidDriver.swipe(width/2,height*3/4,width/2,height/4,500);
                        break;
                    case 2://"down"
                        androidDriver.swipe(width/2,height/4,width/2,height*3/4,500);
                        break;
                    case 3://"left"
                        androidDriver.swipe(width*3/4,height/2,width/4,height/2,500);
                        break;
                    case 4://"right"
                        androidDriver.swipe(width/4,height/2,width*3/4,height/2,500);
                        break;

                }
            }
        }
        androidDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }

    /**
     *滑动页面
     * @param UporDownorRightorLeft 向上或者向下滑动,1向上滑，2向下滑,3左滑，4右滑
     * @param swipenums
     *
     */
    public void Swipe(int UporDownorRightorLeft,int swipenums){


        //获取页面的宽度和高度
        int width = androidDriver.manage().window().getSize().width;
        int height = androidDriver.manage().window().getSize().height;

        for (int i=0;i<swipenums;i++){
            //UporDownorRightorLeft == 1 向上滑动
            if (UporDownorRightorLeft == 1){
                androidDriver.swipe(width/2,height*3/4,width/2,height/4,500);
            }
            //UporDownorRightorLeft == 2 向下滑动
            if (UporDownorRightorLeft == 2){
                androidDriver.swipe(width/2,height/4,width/2,height*3/4,500);
            }
            //UporDownorRightorLeft == 3 向左滑动
            if (UporDownorRightorLeft == 3){
                androidDriver.swipe(width*3/4,height/2,width/4,height/2,500);
            }
            //UporDownorRightorLeft == 4 向右滑动
            if (UporDownorRightorLeft == 4){
                androidDriver.swipe(width/4,height/2,width*3/4,height/2,500);
            }
        }
    }

    //使用touchaction的tap方法点击
    public void tap(By by){
        touchAction.tap(androidDriver.findElement(by)).release().perform();
    }
    //使用driver的tap方法输入坐标点击
    public void tap(int fingers,int x,int y,int duration){
        androidDriver.tap(fingers,x,y,duration);
    }


    //利用TouchAction滑动banner
    public  void bannerSwipe(By by, String swipeway,int nums) throws InterruptedException {
        //先获取控件的起点x坐标值
        WebElement element = androidDriver.findElement(by);
        int xStartPoint = element.getLocation().getX();
        //获取控件的宽度,计算出控件的x终点坐标值
        int xEndPoint = xStartPoint+element.getSize().getWidth();
        //获取控件的Y起点坐标值
        int yStartPoint = element.getLocation().getY();
        for (int i=0;i<nums;i++){
            if (swipeway.equals("左滑")){
                //可以根据控件的宽度调整滑动多宽，可以调整20为30、40等等，以下方法类似
                Thread.sleep(500);
                touchAction.press(xEndPoint-20,yStartPoint+40).waitAction(100).moveTo(xStartPoint,yStartPoint+40).release().perform();
                System.out.println("滑动成功");
                Thread.sleep(500);
            }
            if (swipeway.equals("右滑")){
                Thread.sleep(500);
                touchAction.press(xStartPoint+20,yStartPoint+40).waitAction(500).moveTo(xEndPoint,yStartPoint+40).release().perform();
                Thread.sleep(500);
            }

        }
    }

    //输入信息
    public void sendkeys(By by,String keyvalue){
        //先点击
        this.click(by);

        //把光标移动到末尾键
        androidDriver.sendKeyEvent(123);
        //获取元素内容的长度
        int size = androidDriver.findElement(by).getText().length();
        //循环删除内容
        for (int i=0;i<size;i++){
            androidDriver.sendKeyEvent(AndroidKeyCode.BACKSPACE);
        }
        androidDriver.findElement(by).sendKeys(keyvalue);
    }

    //判断元素是否存在
    public boolean isElementPresented(By by){
        boolean isDisplayed = false;
        try {
            isDisplayed = androidDriver.findElement(by).isDisplayed();
        }catch (Exception e){
            isDisplayed = false;
        }
        return isDisplayed;
    }

    //模拟安卓的返回键
    public void back(){
        androidDriver.sendKeyEvent(AndroidKeyCode.BACK);
    }

    //等待某个元素出现，等待时间为1秒
    public void waitforelementpresent(By by){
        try{
            new WebDriverWait(androidDriver,1000).until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    //判断某个元素是否可以点击
    public void waitforelementisEnable(By by){
        try{
            new WebDriverWait(androidDriver,1000).until(ExpectedConditions.elementToBeClickable(by));
        }catch (Exception e){

        }
    }

    //截图操作
    public  void Screenshot(String ScreenName) throws IOException {
        //设置时间格式，截图的文件要加上时间戳这样不会重复
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        //生成时间戳
        String dataString = simpleDateFormat.format(new Date());
        //设置存放图片目录
        String dir_name = System.getProperty("user.dir")+"\\测试截图";
        //防止出现图片目录被删除，所以先判断目录是否存在，如果不存在就新建一个目录
        if (!(new File(dir_name).isDirectory())){
            //如果不存在就创建一个目录
            new File(dir_name).mkdir();
        }
        //调用方法获取页面截屏
        File screenfile = androidDriver.getScreenshotAs(OutputType.FILE);
        //复制图片到指定目录,放到dir_name目录下，命名是时间戳+测试用例名称
        FileUtils.copyFile(screenfile,new File(dir_name+"\\"+dataString+ScreenName+".jpg"));
        //打印图片名称,
        System.out.println("测试截图名称:"+dir_name+"\\"+dataString+ScreenName+".jpg");
    }

    //获取元素的text值
    public String gettext(By by){
        return androidDriver.findElement(by).getText();
    }








}
