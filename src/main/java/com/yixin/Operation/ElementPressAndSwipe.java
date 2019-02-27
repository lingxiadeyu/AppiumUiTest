package com.yixin.Operation;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class ElementPressAndSwipe {
    //利用TouchAction 拖动控件
    public static void Element_HuaWithTouchAction(AndroidDriver driver, WebElement element, String swipeway) throws InterruptedException {
        //先获取控件的起点x坐标值
        int xStartPoint = element.getLocation().getX();
        //获取控件的宽度,计算出控件的x终点坐标值
        int xEndPoint = xStartPoint+element.getSize().getWidth();
        //获取控件的Y起点坐标值
        int yStartPoint = element.getLocation().getY();
        System.out.println("X起点坐标是"+xStartPoint);
        System.out.println("X终点坐标是"+xEndPoint);
        System.out.println("Y起点坐标是"+yStartPoint);

        //利用TouchAction类实现拖动
        TouchAction action = new TouchAction(driver);

        if (swipeway.equals("左滑")){
            Thread.sleep(1000);
            //可以根据控件的宽度调整滑动多宽，可以调整20为30、40等等，以下方法类似
            action.press(xEndPoint-20,yStartPoint+20).waitAction(1000).moveTo(xStartPoint,yStartPoint+20).release().perform();
            Thread.sleep(1000);

        }
        if (swipeway.equals("右滑")){
            Thread.sleep(1000);
            action.press(xStartPoint+20,yStartPoint+20).waitAction(1000).moveTo(xEndPoint,yStartPoint+20).release().perform();
            Thread.sleep(1000);
        }

    }
    //利用滑动 滑动控件
    public static void Element_HuaWithSwipe(AndroidDriver driver,WebElement element, String swipeway) throws InterruptedException {
        //先获取控件的起点x坐标值
        int xStartPoint = element.getLocation().getX();
        //获取控件的宽度,计算出控件的x终点坐标值
        int xEndPoint = xStartPoint+element.getSize().getWidth();
        //获取控件的Y起点坐标值
        int yStartPoint = element.getLocation().getY();
        System.out.println("X起点坐标是"+xStartPoint);
        System.out.println("X终点坐标是"+xEndPoint);
        System.out.println("Y起点坐标是"+yStartPoint);


        if (swipeway.equals("左滑")){
            driver.swipe(xEndPoint-20,yStartPoint+20,xStartPoint+10,yStartPoint+20,500);
        }
        if (swipeway.equals("右滑")){
            driver.swipe(xStartPoint+10,yStartPoint+20,xEndPoint-20,yStartPoint+20,500);
        }

    }
}
