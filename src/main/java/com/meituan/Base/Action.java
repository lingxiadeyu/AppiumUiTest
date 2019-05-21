package com.meituan.Base;

/*
元素的操作
 */


import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.meituan.Util.DosCmd;
import io.appium.java_client.SwipeElementDirection;
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
import org.testng.Reporter;
import org.openqa.selenium.Dimension;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
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
     * @param by
     * @param UporDownorRightorLeft 向上或者向下滑动,1向上滑，2向下滑,3左滑，4右滑
     *
     */
    public void Swipe(By by, int UporDownorRightorLeft){

        WebElement element1;
        androidDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //获取页面的宽度和高度
        int width = androidDriver.manage().window().getSize().width;
        int height = androidDriver.manage().window().getSize().height;


        //如果滑动到底部，true为false，不再继续查找元素
        while (true){
            try {
                //找到元素退出当前循环，找不到元素进入catch语句中做滑动页面
                element1 = androidDriver.findElement(by);
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
     * @param swipenums 滑动次数
     *
     */
    public void Swipe(int UporDownorRightorLeft,int swipenums){


        //获取页面的宽度和高度
        int width = androidDriver.manage().window().getSize().width;
        int height = androidDriver.manage().window().getSize().height;

        for (int i=0;i<swipenums;i++){
            //UporDownorRightorLeft == 1 向上滑动
            if (UporDownorRightorLeft == 1){
                androidDriver.swipe(width/2,height*3/4,width/2,height/4,1000);
            }
            //UporDownorRightorLeft == 2 向下滑动
            if (UporDownorRightorLeft == 2){
                androidDriver.swipe(width/2,height/4,width/2,height*3/4,1000);
            }
            //UporDownorRightorLeft == 3 向左滑动
            if (UporDownorRightorLeft == 3){
                androidDriver.swipe(width*3/4,height/2,width/4,height/2,1000);
            }
            //UporDownorRightorLeft == 4 向右滑动
            if (UporDownorRightorLeft == 4){
                androidDriver.swipe(width/4,height/2,width*3/4,height/2,1000);
            }
        }
    }

    /**
     * 滑动某个控件,有两种情况，第一种是根据元素的y坐标和屏幕的x坐标去滑动；第二种是根据元素的x坐标和屏幕的y坐标去滑动
     * @param element
     * @param swipeway
     * @param nums
     * @throws InterruptedException
     */
    public  void swipeelement(WebElement element, String swipeway,int nums) throws InterruptedException {
        //先获取控件的起点x坐标值
//        WebElement element = androidDriver.findElement(by);
        int xStartPoint = element.getLocation().getX();
        //获取控件的宽度,计算出控件的x终点坐标值
        int xEndPoint = xStartPoint+element.getSize().getWidth();
        //获取控件的Y起点坐标值
        int yStartPoint = element.getLocation().getY();
        int yEndPoint = yStartPoint+element.getSize().getHeight();

        System.out.println(xStartPoint+","+xEndPoint+","+yStartPoint+","+yEndPoint);
        //获取页面的宽度和高度
        int width = androidDriver.manage().window().getSize().width;
        int height = androidDriver.manage().window().getSize().height;

        for (int i=0;i<nums;i++){
            if (swipeway.equals("左滑")){
                //可以根据控件的宽度调整滑动多宽，可以调整20为30、40等等，以下方法类似
                Thread.sleep(500);
                androidDriver.swipe(width*3/4,(yEndPoint-yStartPoint)/2+yStartPoint,width/4,(yEndPoint-yStartPoint)/2+yStartPoint,500);
                System.out.println("滑动成功");
                Thread.sleep(500);
            }
            if (swipeway.equals("右滑")){
                Thread.sleep(500);
                androidDriver.swipe(width/4,(yEndPoint-yStartPoint)/2+yStartPoint,width*3/4,(yEndPoint-yStartPoint)/2+yStartPoint,500);
                Thread.sleep(500);
            }
            if (swipeway.equals("上滑")){
                //可以根据控件的宽度调整滑动多宽，可以调整20为30、40等等，以下方法类似
                Thread.sleep(500);
                androidDriver.swipe((xEndPoint-xStartPoint)/2+xStartPoint,height*3/4,(xEndPoint-xStartPoint)/2+xStartPoint,height/4,500);
                System.out.println("滑动成功");
                Thread.sleep(500);
            }
            if (swipeway.equals("下滑")){
                Thread.sleep(500);
                androidDriver.swipe((xEndPoint-xStartPoint)/2+xStartPoint,height/4,(xEndPoint-xStartPoint)/2+xStartPoint,height*3/4,500);
                Thread.sleep(500);
            }

        }
        //还可以根据AndroidElement的swipe方法的SwipeElementDirection.UP/DOWN/RIGHT/LEFT等方法去移动控件
    }

    /**
     * 利用TouchAction滑动banner
     * @param by
     * @param swipeway
     * @param nums
     * @throws InterruptedException
     */

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

            //可以通过坐标移动，也可以通过元素移动
//            touchAction.longpress(webelement).waitAction(500).moveTo(webelement).release().perform();

        }
    }

    /**
     * 此方法可以应用在滑动商户列表，验证滑动到底部
     * @param by
     * @throws InterruptedException
     */
    public void swipeUntilBoundary(By by) throws InterruptedException {
        //定义两个list用来存放滑动前和滑动后的元素信息
        List<String> strbefore = new ArrayList<String>();
        List<String> strafter = new ArrayList<String>();

        Boolean flag = false;
        while (!flag){
            List<WebElement> elements = androidDriver.findElements(by);
            for (WebElement element : elements){
                strbefore.add(element.getText());
            }
            Thread.sleep(1000);
            //滑动
            Swipe(1,1);
            Thread.sleep(1000);
            //再次获取当前页面的element list信息
            List<WebElement> elementsafter = androidDriver.findElements(by);
            for (WebElement element : elementsafter){
                strafter.add(element.getText());
            }
            //如果两个list相等，说明滑动到底部，不能再滑动了。
            // flag为true，则while循环不再进行，退出循环，如果flag为false则while循环继续
            //调比对两个list的内容的方法
            flag = compareTwoList(strbefore,strafter);

        }

    }

    //使用touchaction的tap方法点击元素
    public void tap(By by){
        touchAction.tap(androidDriver.findElement(by)).release().perform();
    }
    //使用touchaction的longpress方法长按元素，也可以通过坐标定位
    public void longpress(By by){
        touchAction.longPress(androidDriver.findElement(by)).release().perform();
    }
    //使用driver的tap方法输入坐标点击
    public void tap(int fingers,int x,int y,int duration){
        androidDriver.tap(fingers,x,y,duration);
    }

    //隐式等待
    public void implicitlyWait(int time){
        androidDriver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
    }


    //输入信息
    public void sendkeys(By by,String keyvalue){
        //先点击
        this.click(by);
        //把光标移动到末尾键
        androidDriver.sendKeyEvent(123);
        //获取元素内容的长度
        int size = androidDriver.findElement(by).getText().length();
        if (size == 0){

        }else {
            //循环删除内容
            for (int i=0;i<size;i++){
                androidDriver.sendKeyEvent(AndroidKeyCode.BACKSPACE);
            }
        }

        //没有上面的内容也行，因为sendkeys会自动清空内容再输入
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
    //模拟安卓的返回键,模拟多次返回
    public void back(int nums){
        if (nums>0){
            for (int i=0;i<nums;i++){
                androidDriver.sendKeyEvent(AndroidKeyCode.BACK);
            }
        }else {
            System.out.println("参数有误");
        }

    }
    //模拟安卓的home键
    public void presshome(){
        implicitlyWait(1);
        androidDriver.sendKeyEvent(AndroidKeyCode.HOME);
    }
    //模拟安卓的回车键
    public void pressenter(){
        implicitlyWait(1);
        androidDriver.sendKeyEvent(AndroidKeyCode.ENTER);
    }
    //模拟安卓的删除键
    public void pressbackspace(){
        implicitlyWait(1);
        androidDriver.sendKeyEvent(AndroidKeyCode.BACKSPACE);
    }
    //模拟安卓的删除键,模拟多次删除
    public void pressbackspace(int nums){
        if (nums > 0){
            for (int i=0;i<nums;i++){
                implicitlyWait(1);
                androidDriver.sendKeyEvent(AndroidKeyCode.BACKSPACE);
            }

        }else {
            System.out.println("参数错误");
        }
    }

    /**
     * 通过在cmd中输入adb命令唤醒屏幕
     * @param udid
     */
//    public void wakeup(String udid){
//        DosCmd dc=new DosCmd();
//        if (androidDriver.isLocked()){
//            dc.execCmdConsole("adb -s "+udid+" shell am start -n io.appium.unlock/io.appium.unlock.Unlock");
//        }else {
//            System.out.println("屏幕已开启，不需要唤醒");
//        }
//    }




    //等待某个元素出现，等待时间为5秒,在5秒内不停的查找这个元素，找到就接着往下执行，超过5秒找不到就报错。
    public void waitforelementpresent(By by){
        try{
            new WebDriverWait(androidDriver,5000).until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    //判断某个元素是否可以点击
    public void waitforelementisEnable(By by){
        try{
            new WebDriverWait(androidDriver,1000).until(ExpectedConditions.elementToBeClickable(by));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //截取全屏操作
    public  void Screenshot(String ScreenName) throws IOException {
        //设置时间格式，截图的文件要加上时间戳这样不会重复
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        //生成时间戳
        String dataString = simpleDateFormat.format(new Date());
        //设置存放图片目录
        String dir_name = System.getProperty("user.dir")+"\\testimages";
        //防止出现图片目录被删除，所以先判断目录是否存在，如果不存在就新建一个目录
        if (!(new File(dir_name).isDirectory())){
            //如果不存在就创建一个目录
            new File(dir_name).mkdir();
        }

        //调用方法获取页面截屏
        File screenfile = androidDriver.getScreenshotAs(OutputType.FILE);
        //复制图片到指定目录,放到dir_name目录下，命名是时间戳+测试用例名称
        FileUtils.copyFile(screenfile,new File(dir_name+"\\"+dataString+ScreenName+".jpg"));
        //图片名称
        String photoname = dataString+ScreenName+".jpg";
//        System.out.println("测试截图名称:"+dir_name+"\\"+dataString+ScreenName+".jpg");

        //把测试截图添加到测试报告中，通过本地jenkins查看报告
        //http://localhost:8080/job/appiumTest/ws/testimages/2019-05-16-15-22-43测试截图kkkk.jpg
//        Reporter.log("<a href=http://localhost:8080/job/appiumTest/ws/testimages/" +photoname+ " target=_blank>"+photoname+"</a>", true);
//        Reporter.log("<img src=http://localhost:8080/job/appiumTest/ws/testimages/"+photoname+" style=width:30px;height:30px img/>", true);

        //把测试截图添加到测试报告中，通过服务器上的jenkins查看报告
        //http://localhost:8080/job/appiumTest/ws/testimages/2019-05-16-15-22-43测试截图kkkk.jpg
        Reporter.log("<a href=http://localhost:8080/job/appiumTest/ws/testimages/" +photoname+ " target=_blank>"+photoname+"</a>", true);
        Reporter.log("<img src=http://localhost:8080/job/appiumTest/ws/testimages/"+photoname+" style=width:30px;height:30px img/>", true);

    }

    //针对元素进行截图操作
    public void takeScreenForElement(By by,String screenname) throws IOException {
        //设置时间格式，截图的文件要加上时间戳不会重复
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        //生成时间戳
        String dataString = simpleDateFormat.format(new Date());
        //设置存放图片目录
        String dir_name = System.getProperty("user.dir")+"\\测试截图";
        //防止出现图片目录被删除，所以先判断目录是否存在，如果不存在就新建一个目录
        if (!(new File(dir_name).isDirectory())){
            new File(dir_name).mkdir();
        }
        //获取元素信息针对元素进行截屏
        WebElement element = androidDriver.findElement(by);
        //获取元素的起始坐标点
        Point location = element.getLocation();
        //获取元素的长和宽
        Dimension size = element.getSize();
        //截屏操作，保存截屏信息为字节
        byte[] imagebyte = androidDriver.getScreenshotAs(OutputType.BYTES);
        //读取字节
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imagebyte));
        //按元素的起始坐标和长宽进行截取
        BufferedImage croppedImage = originalImage.getSubimage(location.getX(),location.getY(),size.width,size.height);

        try{
            //把字节再写成图片，保存在测试截图目录下
            ImageIO.write(croppedImage,"png",new File(dir_name+"\\"+dataString+screenname+".png"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //获取元素的text值
    public String gettext(By by){
        return androidDriver.findElement(by).getText();
    }

    /**
     * 切换输入法,测试时输入中文时需要用Android的unicode的输入法，需要调键盘时就要切换到搜狗输入法等
     * 进入cmd中输入adb shell ime list -s 查看手机中安装的输入法，目前有两种：
     * io.appium.android.ime/.UnicodeIME
     * com.sohu.inputmethod.sogouoem/.SogouIME
     * 切换时可以输入adb shell ime set com.sohu.inputmethod.sogouoem/.SogouIME  切换回搜狗输入法
     */
    public void setimeforUnicode(){
        DosCmd dc=new DosCmd();
        try {
//            //获取默认的输入法
//            List<String> res=dc.execCmdConsole("adb -s "+udid+" shell \"settings get secure default_input_method\"");
//            //设置输入法
//            dc.execCmdConsole("adb shell \"ime set "+res.get(0)+"\"");

            //正常情况下，手机上的输入法是搜狗输入法，在测试前先获取所有的输入法，然后修改成Android unicode的输入法，测试后再修改回搜狗输入法
            // 获取手机上的输入法
            List<String> imes = dc.execCmdConsole("adb shell ime list -s");
            //默认第一个是Android unicode的输入法,重新设置输入法为Android unicode的输入法
            dc.execCmdConsole("adb shell ime set "+imes.get(0));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void setimeforSougou(){
        DosCmd dc=new DosCmd();
        try {
//            //获取默认的输入法
//            List<String> res=dc.execCmdConsole("adb -s "+udid+" shell \"settings get secure default_input_method\"");
//            //设置输入法
//            dc.execCmdConsole("adb shell \"ime set "+res.get(0)+"\"");

            //正常情况下，手机上的输入法是搜狗输入法，在测试前先获取所有的输入法，然后修改成Android unicode的输入法，测试后再修改回搜狗输入法
            // 获取手机上的输入法
            List<String> imes = dc.execCmdConsole("adb shell ime list -s");
            //默认第三个是搜狗的输入法,重新设置输入法为搜狗的输入法
            dc.execCmdConsole("adb shell ime set "+imes.get(2));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /**
     *获取屏幕的分辨率，不知道为什么要写这个方法
     *根据设备id获取设备的分辨率
     * @param udid
     * @return
     */
//    public int[] getMobileSize(String udid){
//        DosCmd dc=new DosCmd();
//        //定义一个int数组用来存放获取的分辨率
//        int[] sizeint = new int[2];
//        //进入cmd中执行dos命令
//        List[String] reslist = dc.execCmdConsole("adb -s "+udid+" shell wm size");
//        String[] size = new String[2];
//        if (reslist.size > 0){
//            size = reslist.get[0].split(": ")[1].split("x");
//        }
//        sizeint[0]=Integer.valueOf(size[0]);
//        sizeint[1]=Integer.valueOf(size[1]);
//        return sizeint;
//    }

    // 比对两个list中的内容是否一致

    /**
     * 比对两个list中的内容检查是否一致
     * 此场景可以应用在滑动前后内容的比对，通过比对能证明滑动在操作
     * @param listone
     * @param listtwo
     * @return
     */
    public Boolean compareTwoList(List<String> listone,List<String> listtwo){
        //定义一个布尔值,赋值为false
        Boolean compareresult = false;
        //先判断两个list的值是否为空和null
        if ((listone != null && !listone.isEmpty())&&(listtwo != null && !listtwo.isEmpty()) ){
            //判断两个list的长度是否一致
            if (listone.size() == listtwo.size()){
                //然后for循环判断两个list中的值，不一致的话就break退出，并赋值为false
                for (int i=0;i<listone.size();i++){
                    if (listone.get(i).equals(listtwo.get(i))){
                        compareresult = true;
                    }else {
                        compareresult = false;
                        break;
                    }
                }
            }else {
                System.out.println("两个list大小不一致");
                Reporter.log("两个list大小不一致");
            }
        }else {
            System.out.println("list为空或者为null");
            Reporter.log("list为空或者为null");
        }

        return compareresult;

    }

    /**
     * 根据list中的下标查出element并返回
     * @param by
     * @param index
     * @return
     */
    public WebElement getElementbyIndex(By by,int index){
        List<WebElement> elements = androidDriver.findElements(by);
        WebElement element = null;
        if (elements != null && !elements.isEmpty()){
            if (index >= 0 && index < elements.size()){
                element = elements.get(index);
            }else {
                System.out.println("index值不正确");
            }
        }else {
            System.out.println("elementslist为空或者为null");
        }
        return element;
    }


    /**
     * 从element集合中选出符合某个text值的元素并返回
     * @param by
     * @param text
     * @return
     */
    public WebElement compareTextFromList(By by,String text){
        List<WebElement> elements = androidDriver.findElements(by);
        WebElement element = null;
        //判断elements list的值是否为空和null
        if (elements != null && !elements.isEmpty()){
                //然后for循环判断list中元素属性为text的值是否等于预期的值，如果等于就返回相应的element
                for (int i=0;i<elements.size();i++){
                    if (elements.get(i).getAttribute("text").equals(text)){
                        element = elements.get(i);
                        break;
                    }
                }
            }else {
            System.out.println("elements list为空或者为null");
        }
        return element;
    }

}
