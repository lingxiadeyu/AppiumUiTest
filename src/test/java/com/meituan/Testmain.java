package com.meituan;

import com.meituan.Util.ElementSource;
import com.meituan.Util.GetDataProvider;
import com.meituan.Util.TestSuite;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.impl.xpath.regex.Match;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.Test;

import javax.xml.ws.Response;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testmain extends TestSuite {
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
        AppiumDriver<WebElement> driver;

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


        Thread.sleep(5000);
        while(true){
            try {
                driver.findElement(By.name("老城一锅（小关店）"));
                break;
            }catch (Exception e){
                driver.swipe(540,448,540,344,500);
            }
        }
        Thread.sleep(1000);
        driver.findElement(By.name("老城一锅（小关店）")).click();

    }

    @Test(description = "string的split方法是根据分隔符分割字符串得到string数组")
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

    @Test
    public void test0010(){
        int a = 15;
        double b = 20.29;
        double c = a/b;
        int d = (int)(a/b);


        System.out.println(d);

    }

    @Test(enabled = false,description = "map存储数据，并对map排序")
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

    @Test(description = "从yaml中读取文件")
    public void getdatafromyaml(){
        ElementSource elementSource = new ElementSource();
        System.out.println(elementSource.getElementsource("merchantname"));
    }

    @Test(dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void getdatafordataprovider(String name){
        System.out.println(name);
    }

}
