package com.meituan;

import com.meituan.Server.Servers;
import com.meituan.Util.ElementSource;
import com.meituan.Base.GetDataProvider;
import com.meituan.Base.TestSuite;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testmain extends TestSuite{
    private static Log logger = LogFactory.getLog(Testmain.class);


    //    @Test
    public void teststring(){
//        String name1 = "亚运村火锅人气榜第13名";
//        //string的substring方法是根据下标来截取字符串
//        String name2 = name1.substring(name1.length()-2,name1.length()-1);
//
//        System.out.println(name1.length());
//        System.out.println(name1.length()-2);
//        System.out.println(name1.length()-1);
//        System.out.println(name2);

//        String b = "昨东今8888";
//        String a = "^*东*";
//
//        //使用正则表达式比对结果
//        Pattern pattern = Pattern.compile(a);
//        Matcher matcher = pattern.matcher(b);
//        boolean res = matcher.find();
//        System.out.println(res);


//        String[] serviceballs = {"美食","电影/演出","酒店住宿","休闲娱乐","外卖","结婚/摄影","民宿/公寓","周边游/旅游","丽人/美发",
//                "亲子/乐园","景点/门票","生活服务","超市/生鲜","健身/游泳","更多"};
//        for (String s : serviceballs){
//            System.out.println(s);
//        }


//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入表示星期的英文单词：");
//        String week= sc.next();
//        System.out.println(week);
//
//        switch (week) {
//            case "MONDAY":
//                System.out.println("星期一");break;
//            case "TUESDAY":
//                System.out.println("星期二");break;
//            case "WEDNESDAY":
//                System.out.println("星期三");break;
//            case "THURSDAY":
//                System.out.println("星期四");break;
//            case "FRIDAY":
//                System.out.println("星期五");break;
//            case "SATURDAY":
//                System.out.println("星期六");break;
//            case "SUNDAY":
//                System.out.println("星期日");break;
//            default:
//                System.out.println("单词输入错误！");
//        }

        String str = "亚运村火锅人气榜第13名";
        String a="^*第";
        //编译正则表达式
        Pattern pattern = Pattern.compile(a);
        //匹配目标值
        Matcher matcher = pattern.matcher(str);

        if (matcher.find())
        {
//            str = str.replace("^*第","");
            str = str.replaceAll("^.*第","");
            System.out.println(str);
            str = str.replace("名","");
            System.out.println(str);
//            String str1,str2;
//            str1=str.Substring(0,m.Index);
//            str2=str.Substring(m.Index+a.Length,str.Length-a.Length-m.Index);
//            Response.Write(str1+str2);
        }

    }

//    @Test
    public void testwebdriver() throws MalformedURLException, InterruptedException {
        AndroidDriver<WebElement> driver;

//        //获取当前根目录下的文件，当前根目录就是D:\Springboot\MyApplicationthree,项目名是MyApplicationthree
//        File classpathRoot = new File(System.getProperty("user.dir"));
//        //获取apps目录下的文件
//        File appDir = new File(classpathRoot, "/apps");
//        //获取蘑菇智行.apk，需要提前把该apk放到apps文件夹下
//        File app = new File(appDir, "meituan-9.11.601.apk");

        //创建设备属性对象
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //设置设备系统为Android
        capabilities.setCapability("platformName", "Android");
        //设置设备名称为PBVGK16918902121，可以通过usb连接手机，在cmd中输入命令，adb devices 看到设备名称
        capabilities.setCapability("deviceName","edaed87d");
        //设置设备安卓系统，可以在手机的设置中查看系统号
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("udid","edaed87d");

        //获取app的绝对路径
//        capabilities.setCapability("app", app.getAbsolutePath());
        //设置app的包名，通过usb连接手机，在手机中打开app，在cmd中输入命令adb shell dumpsys window | findstr mCurrentFocus 查看包名
        capabilities.setCapability("appPackage", "com.sankuai.meituan");
        //设置app的入口，通过反编译可以查到该值，参考apk反编译文档获取该值
        capabilities.setCapability("appActivity", "com.meituan.android.pt.homepage.activity.MainActivity");
        //设置每次运行不清空app的数据
        capabilities.setCapability("noReset","true");
        //使用Unicode编码方式发送字符串
        capabilities.setCapability("unicodeKeyboard","True");
        //恢复键盘输入法
        capabilities.setCapability("resetKeyboard","True");

        //设置测试机与idea的通讯协议
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        System.out.println("App is launched!");



        driver.findElement(By.id("com.sankuai.meituan:id/search_edit"));
        driver.findElement(By.id("com.sankuai.meituan:id/search_edit")).sendKeys("22222");

//        Thread.sleep(5000);
//        while(true){
//            try {
//                driver.findElement(By.name("老城一锅（小关店）"));
//                break;
//            }catch (Exception e){
//                driver.swipe(540,448,540,344,500);
//            }
//        }
//        Thread.sleep(1000);
//        driver.findElement(By.name("老城一锅（小关店）")).click();

    }

    //@Test(description = "string的split方法是根据分隔符分割字符串得到string数组")
    public void testsplit(){
        String str="取名次第11111名";
        //把"第"当成一个分隔符,截取字符串，存储到string数组中，数组中有两个值，strstart[0]="取名次"，strstart[1]="11111名"
        String[] strstart = str.split("第");
        for (String strs : strstart){
            System.out.println(strs);
        }

        //对strstart[1]="11111名"再根据“名”进行分割，获取到“111111”
        String[] strend = strstart[1].split("名");
        for (String strsend : strend){
            System.out.println(strsend);
        }

        Reporter.log("测试执行通过");
        logger.trace("我是trace信息");
        logger.debug("我是debug信息");
        logger.info("我是info信息");
        logger.warn("我是warn信息");
        logger.error("我是error信息");
        logger.fatal("我是fatal信息");


    }

   // @Test
    public void test0010(){
        int a = 15;
        double b = 20.29;
        double c = a/b;
        int d = (int)(a/b);


        System.out.println(d);

    }

    //@Test(enabled = false,description = "map存储数据，并对map排序")
    public void test0011(){

        //定义金额为两位小数
        DecimalFormat df = new DecimalFormat(".00");
//        //进入外卖门店详情页面
//        meiTuanBusiness.enterWaiMaiMerchantDetailPage();
        //定义map用来存储满减优惠金额
        Map<String,String> map = new HashMap<String, String>();


        //获取商户优惠，用于计算后面的优惠金额
//        action.click(By.id(waiMaiMerchantDetailPage.showheaderbuttonid));
        List<WebElement> gettxtpoiactivity = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtpoiactivityid));

        System.out.println(gettxtpoiactivity.get(0).getText());
        String[] gettxtpoiactivitysplit =gettxtpoiactivity.get(0).getText().split(";");

        for (int i=0;i<gettxtpoiactivitysplit.length;i++){
            String[] gettxtpoiactivitysplit1 = gettxtpoiactivitysplit[i].split("减");
            String gettxtpoiactivitysplit2 =  gettxtpoiactivitysplit1[0].replaceAll("满","");
            map.put(gettxtpoiactivitysplit2,gettxtpoiactivitysplit1[1]);
        }

        Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> entry = it.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        //对map中的key和value做排序
        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                int flag = o1.getValue().compareTo(o2.getValue());
                if (flag == 0 ){
                    return o1.getKey().compareTo(o2.getKey());
                }
                return flag;
            }
        });

    }

    //@Test(description = "从yaml中读取文件")
    public void getdatafromyaml(){
        ElementSource elementSource = new ElementSource();
        System.out.println(elementSource.getElementsource("merchantname"));
    }

    //@Test(dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void getdatafordataprovider(String name){
        System.out.println(name);
    }

    //@Test(description = "用uiautomator")
    public void useuiautomator(){
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.sankuai.meituan:id/search_layout\")").click();
//        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.sankuai.meituan:id/search_edit\")").sendKeys("2222");

//        1.	findElementByAndroidUIAutomator
//        该方法的参数是使用uiautomator这个工具进行定位的方式，参数直接是将uiautomator定位方式以字符串形式传递
//        1.1	uiautomator的id定位方式
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"resourceId\")");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceIdMatches(\"regex\")");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceIdMatches(\"com.sankuai.meituan:id/search_edit\")").sendKeys("3333");

//
//        1.2 uiautomator的text定位方式
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"汉堡王\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains (\"欢乐\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().textMatches (\"regex\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().textStartsWith(\"大熊猫\")").click();
//
//        1.3 uiautomator的classname定位方式
        driver.findElementByAndroidUIAutomator("new UiSelector().className(\"className\")");
        driver.findElementByAndroidUIAutomator("new UiSelector().classNameMatches (\"regex\")");
//        1.4 可以指定查找到的第几个，红色部分括号里写上索引即可
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"登录\").instance(1)");
//        1.5 滑动到指定文本的元素
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).getChildByText(new UiSelector().className(\"android.widget.TextView\"), \"你想滑动到的元素字符串 \")");
//        1.6 根据元素其他属性进行定位，
        driver.findElementsByAndroidUIAutomator("new UiSelector().resourceId(\"android:id/checkbox\").checked(false)");

    }

    //@Test(description = "练习xpath")
    public void testxpath() throws IOException, InterruptedException {
        //android.widget.LinearLayout/android.widget.LinearLayout[@id='com.sankuai.meituan:id/city_area_select_layout']/child::com.sankuai.meituan:id/city_select_area[@id='com.sankuai.meituan:id/city_select_area']

//        driver.findElement(By.id("com.sankuai.meituan:id/city_name")).click();
//        driver.findElement(By.xpath("//android.widget.TextView{contains(@resource-id,'com.sankuai.meituan:id/city_select_area')}")).click();

        //android.widget.TextView[@id='com.sankuai.meituan:id/city_select_area']/parent::android.widget.LinearLayout

//        html.xapth('//li/parent::') 通过父级去定位
//        html.xapth('//li/child::') 直接子节点
//        html.xapth('//li/ancestor::') 所有祖先节点
//        html.xapth('//li'/attribute::) 获取所有的属性值
//        html.xapth('//li/descendant::') 获取所有的子孙节点


        //用id的xpath定位
//        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.sankuai.meituan:id/search_layout']")).click();
//        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.sankuai.meituan:id/search_edit']")).sendKeys("2222");


        //用child定位
//        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.sankuai.meituan:id/search_layout']/child::android.widget.TextView[@resource-id='com.sankuai.meituan:id/search_edit']")).click();

        //用parent定位
//        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.sankuai.meituan:id/search_edit']/parent::android.widget.LinearLayout[@resource-id='com.sankuai.meituan:id/search_layout']")).click();

        //用ancestor定位,可以定位到爷爷辈，也可以定位到爷爷辈的爷爷辈，所有的祖先信息都可以
//        driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.sankuai.meituan:id/actionbar_scan_iv']/ancestor::android.widget.LinearLayout[@resource-id='com.sankuai.meituan:id/actionbar_scan_container']")).click();
//        driver.findElement(By.xpath("//android.widget.ImageView[@resource-id='com.sankuai.meituan:id/actionbar_scan_iv']/ancestor::android.widget.LinearLayout[@resource-id='com.sankuai.meituan:id/navigation_bar_view']")).click();

        //用descendant定位，可以定位到孙子辈的孙子辈以下的所有元素
//        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.sankuai.meituan:id/navigation_bar_view']/descendant::android.widget.LinearLayout[@resource-id='com.sankuai.meituan:id/actionbar_scan_container']")).click();
        driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='com.sankuai.meituan:id/navigation_bar_view']/descendant::android.widget.RelativeLayout")).click();

    }
    //@Test(description = "练习css")
    public void testcss(){

//        driver.findElement(By.cssSelector("#com.sankuai.meituan:id/search_layout")).click();
//        driver.findElement(By.cssSelector("#com.sankuai.meituan:id/search_edit")).sendKeys("2222");
        driver.findElement(By.cssSelector("#resource-id='com.sankuai.meituan:id/search_layout'")).click();

    }
    //@Test(description = "设置输入法")
    public void testime() throws InterruptedException {
        //执行前改输入法为unicode
        action.setimeforUnicode();

        action.click(By.id(searchMerchantPage.searcheditid));
        action.sendkeys(By.id(searchMerchantPage.searcheditid),"汉堡王");
        Thread.sleep(1000);

        //执行后改输入法为搜狗输入法
        action.setimeforSougou();
    }
    @Test(description = "启动appium服务")
    public void teststartappium() throws Exception {
        //1、通过usb连接真机，启动模拟器，在cmd中输入adb devices查看有两台设备连接
        //2、手动执行Servers中的main方法，通过命令行的方式启动两台设备的服务
        //3、执行testng.xml配置thread-count=2，线程数为2，配置两个test信息，配置端口号和设备号等参数信息
        action.click(By.id(searchMerchantPage.searcheditid));
        action.sendkeys(By.id(searchMerchantPage.searcheditid),"222222");
        Thread.sleep(1000);

        Assert.assertEquals("1","1","断言成功");
        action.Screenshot("001");
        action.Screenshot("002");

    }

    @Test(description = "启动appium服务")
    public void teststartappium2() throws Exception {
        //1、通过usb连接真机，启动模拟器，在cmd中输入adb devices查看有两台设备连接
        //2、手动执行Servers中的main方法，通过命令行的方式启动两台设备的服务
        //3、执行testng.xml配置thread-count=2，线程数为2，配置两个test信息，配置端口号和设备号等参数信息
        action.click(By.id(searchMerchantPage.searcheditid));
        action.sendkeys(By.id(searchMerchantPage.searcheditid),"222222");
        Thread.sleep(1000);

        Assert.assertEquals("1","1","断言成功");
        action.Screenshot("测试截图001");

    }
    @AfterMethod
    public void teardown() throws InterruptedException {
        driver.quit();
        Thread.sleep(500);
    }

}
