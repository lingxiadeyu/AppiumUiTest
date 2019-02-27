package com.yixin.Operation;

/*
截图操作
 */

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
    public static void Screenshot(AndroidDriver driver, String ScreenName) throws IOException {
        //设置时间格式，截图的文件要加上时间戳这样不会重复
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        //生成时间戳
        String dataString = simpleDateFormat.format(new Date());
        //设置存放图片目录
        String dir_name = System.getProperty("user.dir")+"\\异常图片";
        System.out.println("异常图片目录:"+dir_name);
        //防止出现图片目录被删除，所以先判断目录是否存在，如果不存在就新建一个目录
        if (!(new File(dir_name).isDirectory())){
            //如果不存在就创建一个目录
            new File(dir_name).mkdir();
        }
        //调用方法获取页面截屏
        File screenfile = driver.getScreenshotAs(OutputType.FILE);
        //复制图片到指定目录,放到dir_name目录下，命名是时间戳+测试用例名称
        FileUtils.copyFile(screenfile,new File(dir_name+"\\"+dataString+ScreenName+".jpg"));
        //打印图片名称,
        System.out.println("异常图片名称:"+dir_name+"\\"+dataString+ScreenName+".jpg");

    }
}
