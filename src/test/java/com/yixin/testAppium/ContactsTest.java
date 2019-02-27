package com.yixin.testAppium;


import com.meituan.Pages.HomePages.HomePage;
import com.meituan.Pages.ListItemPage;
import com.meituan.Util.Action;
import com.yixin.BusinessLayer.Infrastructure_ClickAllCategory;
import com.yixin.BusinessLayer.Infrastructure_Login;
import com.yixin.Operation.ElementPressAndSwipe;
import com.yixin.Operation.Screenshot;
import com.yixin.Operation.SwipeCenter;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.AndroidElement;

import java.io.File;

import java.io.IOException;
import java.net.URL;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import org.openqa.selenium.remote.DesiredCapabilities;

/*
特权加油的包：
app-release.apk
com.yx.addgasoline
com.yx.addgasoline.common.view.SplashActivity

蘑菇智行app的包：
app-debug-androidTest.apk
com.zhidao.mobile
com.zhidao.mobile.ui.activity.SplashActivity"

安卓手机设备：PBVGK16918902121  8.0.0
模拟器设备： 5.1.0

哔哩哔哩
bilibili.apk
tv.danmaku.bili
tv.danmaku.bili.ui.splash.SplashActivity

美团
meituan-9.11.601.apk
com.sankuai.meituan
com.meituan.android.pt.homepage.activity.Welcome

*/
public class ContactsTest {
    //创建driver对象
//    private AppiumDriver driver;
    private AndroidDriver driver;

    //创建log4j
    private static Logger logger = Logger.getLogger(ContactsTest.class);


    //创建执行前配置方法
    @BeforeMethod
    public void setUp() throws Exception {

        // set up appium

        //获取当前根目录下的文件，当前根目录就是D:\Springboot\MyApplicationthree,项目名是MyApplicationthree
        File classpathRoot = new File(System.getProperty("user.dir"));
        //获取apps目录下的文件
        File appDir = new File(classpathRoot, "/apps");
        //获取蘑菇智行.apk，需要提前把该apk放到apps文件夹下
        File app = new File(appDir, "meituan-9.11.601.apk");

        //创建设备属性对象
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //设置设备系统为Android
        capabilities.setCapability("platformName", "Android");
        //设置设备名称为PBVGK16918902121，可以通过usb连接手机，在cmd中输入命令，adb devices 看到设备名称
        capabilities.setCapability("deviceName","PBVGK16918902121");
        //设置设备安卓系统，可以在手机的设置中查看系统号
        capabilities.setCapability("platformVersion", "8.0.0");

        //获取app的绝对路径
        capabilities.setCapability("app", app.getAbsolutePath());
        //设置app的包名，通过usb连接手机，在手机中打开app，在cmd中输入命令adb shell dumpsys window | findstr mCurrentFocus 查看包名
        capabilities.setCapability("appPackage", "com.sankuai.meituan");
        //设置app的入口，通过反编译可以查到该值，参考apk反编译文档获取该值
        capabilities.setCapability("appActivity", "com.meituan.android.pt.homepage.activity.Welcome");
//        //设置每次运行不重新启动被测试app
        capabilities.setCapability("noReset","true");
        //使用Unicode编码方式发送字符串
        capabilities.setCapability("unicodeKeyboard","True");
        //隐藏键盘 ,这样才能输入中文
        capabilities.setCapability("resetKeyboard","True");

        //设置测试机与idea的通讯协议
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println("App is launched!");

    }

    //@Test
    public void addContact() throws InterruptedException {

        //定位获取到text的值，直接定位到元素
        WebElement element = driver.findElementByName("追番");
        //对元素操作点击动作
        element.click();

        //通过4种方式获取一个元素
        //页面上多个元素的classname相同，使用List接收元素信息
//        List<AndroidElement> textFieldsList = driver.findElementsByClassName("android.widget.ImageView");
        //通过xpath获取
//        List<AndroidElement> textFieldsList = driver.findElementsByXPath("//android.widget.ImageView[contains(@resource-id,'tv.danmaku.bili:id/icon')]");
        //通过id获取，这个元素的id名称是resource_id,通过id获取也行
//        List<AndroidElement> textFieldsList = driver.findElementsById("tv.danmaku.bili:id/icon");



        /*
1、使用占位符，通过传参的方式传控件信息，如果android.widget.ImageView 更改了，只更改这个类中的就行，测试类中的不用更改
 */
//        public class DataCenter {
//            private static String Category_head="//android.widget.ImageView[contains(@resource-id,'%1$s')]";
//            public static String  StringFormat(String StrText){
//                return String.format(Category_head,StrText);
//            }
//        }
//        //通过调DataCenter类中的方法，传入占位符参数
//        List<AndroidElement> textFieldsList = driver.findElementsByXPath(DataCenter.StringFormat("tv.danmaku.bili:id/icon"));
//        //获取第3个元素操作点击动作
//        textFieldsList.get(2).click();

        //返回上一级目录
        WebElement element1 = driver.findElement(By.className("android.widget.ImageButton"));
        element1.click();

        System.out.println("App is done!");

    }

    //@Test(description = "xpath技术应用")
    public void testxpath() throws InterruptedException {

//        //通过子级去找
//        WebElement element = driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView/child::android.view.ViewGroup[1]/android.widget.FrameLayout"));
//        element.click();

        //通过父级去找,会用到parent、fowlling:sibling
        //driver.findElement(By.xpath("//android.widget.FrameLayout{contains(@resource-id,'tv.danmaku.bili:id/cover_layout')}/parent::android.view.ViewGroup/fowlling:sibling::android.view.ViewGroup[2]/android.widget.FrameLayout/android.widget.ImageView")).click();


        //找到第一个元素点击,默认是第一个元素
//        driver.findElement(By.id("tv.danmaku.bili:id/cover")).click();
        //找到第二个元素点击，因为id都一样，所有通过list来接收，通过elements.get(1).click();点击第二个元素
        List<AndroidElement> elements = driver.findElementsById("tv.danmaku.bili:id/cover");
        elements.get(0).click();
        Thread.sleep(5000);
        //点击屏幕
        driver.findElementById("tv.danmaku.bili:id/video_view_biliplayer").click();
        //点击右上角返回键
        driver.findElementByClassName("android.widget.ImageButton").click();

        System.out.println("App is done!");

    }

    //@Test(description = "Xpath中的UiSelector")
    public void testuiselector(){
//        driver.findElementByAndroidUIAutomator("new UiSelector.Description.Contains(\"" + descriptionname + "\")");
    }

    //@Test(description = "滑动操作")
    public void testswip() throws InterruptedException {

        //调取SwipeCenter类中的方法，实现向上、向下、向左、向右滑动
        //向上滑动
        SwipeCenter.swipeToUp(driver,500,2);
        //向下滑动
        SwipeCenter.swipeToDown(driver,500,1);
        //向左滑动
        SwipeCenter.swipeToLeft(driver,500,1);
        //向右滑动
        SwipeCenter.swipeToRight(driver,500,1);

        //本来想实现一个不停的滑动去找元素，直到找到为止，后来发现没有意义，可以直接定位元素，没必要通过这种方式去做，通常的遇到找元素的是去在页面等待元素的出现然后去定位元素去操作
        //try--catch的方式是不对的，catch中不能存可能会有异常的代码
//        WebElement element = null;
//        try {
//            //先去找元素
//            element = driver.findElementByName("百万播放");
//        }catch(NoSuchElementException e){
//            e.printStackTrace();
//            try{
//                SwipeCenter.swipeToLeft(driver,500,1);
//                element = driver.findElementByName("百万播放");
//            }catch (NoSuchElementException e1){
//                e1.printStackTrace();
//                SwipeCenter.swipeToLeft(driver,500,1);
//                element = driver.findElementByName("百万播放");
//                //找到后再点击click
//                element.click();
//            }
//        }




        //该代码会有异常，不能这样写
//        do {
//            element = driver.findElementByName("时间表");
//            SwipeCenter.swipeToLeft(driver,500,1);
//        }while (element.getText() != "时间表");
//        element.click();



    }

    //@Test(description = "location坐标定位")
    public void testlocation() throws InterruptedException {
        //tap模拟手指点击
        //先等待2秒，等待元素出来
        Thread.sleep(2000);
        //使用tap方法，第一个参数是模拟一个手指去点，第二个参数是X点，第三个是y点，第四个是持续点击0.5秒
        //通过Ui Automator viewer定位看到坐标点是[846,362][1080,482]，取X值在846~1080之间，取Y值在362~482之间
        driver.tap(1,900,420,500);
        Thread.sleep(2000);
        System.out.println("坐标定位成功");
    }

    //@Test(description = "获取webview页面元素")
    public void testwebview(){

        //获取driver中的handles，利用foreach循环查找如果是webview的页面就进入webview页面，转换成html在浏览器中打开再去定位到某个元素
        //定位成功之后再转回到NATIVE_APP 页面
        Set<String> contextset = driver.getContextHandles();
        for (String context : contextset ){
            System.out.println("页面上context是："+context);


            if (context.toLowerCase().contains("webview")){
                driver.context(context);//进入webview页面
                System.out.println("进入成功");
                break;
            }
        }
        //通过封装的方法把webview源码转换成html，然后在浏览器中打开去定位到元素
        //封装的方法还没有实现，如果找到webview页面，获取到源码使用chrome-development tool 来定位元素
        //使用步骤参考：https://blog.csdn.net/wanglin_lin/article/details/77100028
//        PageResourceGetter.initialize("uers/dir/prg.html",driver.getPageSource());
        //定位到元素
        driver.findElement(By.id("username")).sendKeys("appium.test@gmail.com");
        //再回到原生app
        driver.switchTo().window("NATIVE_APP");

    }

    //@Test(description = "截屏")
    public void testscreenshot() throws InterruptedException, IOException {
        //正常请求的截图
        Thread.sleep(1000);
        Screenshot.Screenshot(driver,"test001");
        Thread.sleep(1000);

        //异常的截图放到catch中
        try{
            driver.findElementByName("百万播放");
        }catch (Exception e){
            Screenshot.Screenshot(driver,"test001");
        }


    }

    //@Test(description = "控件滑动")
    public void testactionswipe() throws InterruptedException {
        //调用封装的方法实现控件滑动

        driver.findElementByName("追番").click();
        driver.findElementByName("时间表").click();
        List<WebElement> elements = driver.findElementsById("tv.danmaku.bili:id/date_item");
        WebElement element = elements.get(2);

        ElementPressAndSwipe.Element_HuaWithTouchAction(driver,element,"左滑");
        ElementPressAndSwipe.Element_HuaWithTouchAction(driver,element,"右滑");

        ElementPressAndSwipe.Element_HuaWithSwipe(driver,element,"左滑");
        ElementPressAndSwipe.Element_HuaWithSwipe(driver,element,"右滑");
    }

    //@Test(description = "控件的输入")
    public void testsendkeys() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElementById("tv.danmaku.bili:id/expand_search").click();
        Thread.sleep(1000);
        driver.findElementById("tv.danmaku.bili:id/search_src_text").sendKeys("test");
        Thread.sleep(1000);

    }

    //@Test(description = "控件的长按")
    public void testlongpress() throws IOException {
        //获取控件的X、Y坐标点，通过坐标点长按

        WebElement element = driver.findElementByName("追番");
        int X = element.getLocation().getX();
        int Y = element.getLocation().getY();

        TouchAction action = new TouchAction(driver);
        //通过坐标点长按
        action.longPress(X,Y).perform();
        //通过定位到元素长按
        action.longPress(driver.findElementByName("时间表")).perform();
    }

    //@Test(description = "尝试从配置文件中获取元素信息，结果行不通")
//    public void testgetresource() throws IOException {
//        String elementname = ElementResource.getElementResource("nameone");
//        System.out.println(elementname);
//        driver.findElementByName("追番").click();
//        driver.findElementByName(elementname).click();
//        //能够从配置文件中获取数据，但是有问题 获取不到
//    }

    //@Test(description = "横竖屏--实现不了，目前报错定位不到元素")
    public void testscreenorientation() throws InterruptedException, IOException {
        //先定位到播放视频页面，这样才可以演示横竖屏操作
        List<AndroidElement> elements = driver.findElementsById("tv.danmaku.bili:id/cover");
        elements.get(0).click();
        Thread.sleep(5000);
        //点击屏幕
        driver.findElementById("tv.danmaku.bili:id/video_view_biliplayer").click();
        //先获取页面的横竖屏
        System.out.println(driver.getOrientation());
        //设置横向
        driver.rotate(ScreenOrientation.LANDSCAPE);
        Thread.sleep(5000);
//        //截屏
//        Screenshot.Screenshot(driver,"横竖屏");
//        System.out.println(driver.getOrientation());
//        Thread.sleep(5000);
//        //再设置竖向
//        driver.rotate(ScreenOrientation.PORTRAIT);
//        //截屏
//        Screenshot.Screenshot(driver,"横竖屏");
    }


    //@Test(description = "跨应用操作,实现不了报错")
    public void testgetotherapk() throws InterruptedException {
        driver.startActivity("com.zhidao.mobile","com.zhidao.mobile.ui.activity.SplashActivity");
        Thread.sleep(2000);

    }

    //@Test(description = "测试登录")
    public void testlogin() throws Exception {
        //先测试登录按钮是否能用
//        driver.findElement(By.name("追番")).click();
//        Thread.sleep(5000);
////        driver.tap(1,500,500,500);
////        driver.findElement(By.id("tv.danmaku.bili:id/content")).click();
//        driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'tv.danmaku.bili:id/content')]")).click();
//        Thread.sleep(5000);


        //调用操作层方法
        Infrastructure_ClickAllCategory clickAllCategory = new Infrastructure_ClickAllCategory();
        clickAllCategory.run(driver);
        Infrastructure_Login login = new Infrastructure_Login();
        login.run(driver);

    }

    //@Test(description = "测试输入为中文")
    public void testchinese() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("tv.danmaku.bili:id/expand_search")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("tv.danmaku.bili:id/search_src_text")).click();
        //输入中文前先设置使用Unicode编码方式发送字符串和隐藏键盘

        //在setup方法中写入以下两句代码可以实现输入中文
        //使用Unicode编码方式发送字符串
//        capabilities.setCapability("unicodeKeyboard","True");
//        //隐藏键盘 ,这样才能输入中文
//        capabilities.setCapability("resetKeyboard","True");


        driver.findElement(By.id("tv.danmaku.bili:id/search_src_text")).sendKeys("哔哩哔哩");
        Thread.sleep(2000);


    }

    //@Test(description = "模拟键盘输入")
    public void testkeyboard() throws InterruptedException {
        //注意：要打开键盘时setup方法中的如下两行代码要注释掉：
        //使用Unicode编码方式发送字符串
        //capabilities.setCapability("unicodeKeyboard","True");
        //隐藏键盘 ,这样才能输入中文
        //capabilities.setCapability("resetKeyboard","True");


        Thread.sleep(1000);
        driver.findElement(By.id("tv.danmaku.bili:id/expand_search")).click();
        Thread.sleep(1000);
        //appium执行时会调起键盘，键盘上的信息无法定位到id或class，只能通过猜测坐标去点击， 比如下图中w的坐标猜测可能是150,1200
        driver.tap(1,150,1200,1);
        Thread.sleep(1000);

        //模拟Android key的输入，3代表是按以下home键
        driver.sendKeyEvent(3);
        Thread.sleep(1000);
    }

    //@Test(description = "模拟多个测试用例一起执行")
    public void testruncases() throws Exception {

        //在setup前加上@BeforeMethod,在tearDown前加上@AfterMethod每个用例执行之前都要重新运行一下setup和tearDown
        Thread.sleep(2000);
        driver.findElement(By.name("直播")).click();
        Thread.sleep(1000);

    }

    //@Test(description = "测试登录")
    public void testlogintwo() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.name("追番")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'tv.danmaku.bili:id/content')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("你的手机号/邮箱")).sendKeys("18600228767");
        driver.findElement(By.name("请输入密码")).sendKeys("123456");
        Thread.sleep(1000);
        driver.findElement(By.id("tv.danmaku.bili:id/btn_login")).click();
        Thread.sleep(1000);

    }

    //@Test(description = "测试美团")
    public void testmeituan() throws InterruptedException {
        System.out.println("启动成功");

        Thread.sleep(1000);
        driver.findElement(By.name("美食")).click();
        driver.findElement(By.id("com.sankuai.meituan:id/txt_search_keyword")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("com.sankuai.meituan:id/search_edit")).sendKeys("2222");
        Thread.sleep(1000);
//android.widget.FrameLayout[0]/child::android.widget.LinearLayout

    }

    @Test(description = "测试美团xpath")
    public void testmeituanxpath() throws InterruptedException {


        Thread.sleep(1000);
        driver.findElement(By.name("美食")).click();
        Thread.sleep(1000);
        SwipeCenter.swipeToUp(driver,500,1);
        Thread.sleep(1000);
        //通过child找到子元素，通过[3]定位到第3个列表信息
//        driver.findElement(By.xpath("//android.widget.ListView/child::android.widget.LinearLayout[3]")).click();
        //通过child找到子元素，通过[last()]定位到最后一个列表信息
//        driver.findElement(By.xpath("//android.widget.ListView/child::android.widget.LinearLayout[last()]")).click();
        //通过child找到子元素，通过[last()-1]定位到倒数第二个列表信息
        WebElement element = driver.findElement(By.xpath("//android.widget.ListView/child::android.widget.LinearLayout[last()]"));
        Thread.sleep(1000);
        //使用try catch验证元素是否存在，如果不存在会报异常
        try{
            element.click();//元素存在就点击，不存在点击会报异常。
        }catch (Exception e){

        }

    }

    //@Test(description = "测试使用数组定义元素内容")
    public void testhomepage() throws InterruptedException {
        List<String> list = new ArrayList();
        list.add("美食");
        list.add("电影/演出");
        list.add("酒店住宿");


        for (int i=0;i<list.size();i++){
            driver.findElement(By.name(String.format("%1$s",list.get(i)))).click();
            Thread.sleep(1000);
            driver.tap(1,100,150,500);
            Thread.sleep(1000);
        }

    }

    @Test(description = "从一个元素滑动到另一个元素，直到找到元素为止")
    public void testelementswipe() throws InterruptedException {
        //先进到首页，点击美食
        Action action = new Action(driver);
        HomePage homePage = new HomePage(driver);
        ListItemPage listItemPage = new ListItemPage(driver);

//        action.click(By.name(homePage.getserviceballs().get(0)));
        Thread.sleep(1000);

        //直接滑动去找元素
        action.Swipe(By.name(listItemPage.endelement),1);
        //找到元素后去点击
        action.click(By.name(listItemPage.endelement));



    }





    @AfterMethod
    public void tearDown() throws Exception {

        driver.quit();

    }

}
