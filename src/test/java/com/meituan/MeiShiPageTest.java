package com.meituan;

import com.meituan.Util.GetDataProvider;
import com.meituan.Util.TestSuite;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.appium.java_client.TouchAction;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/*
美食栏目--门店详情页测试
 */
public class MeiShiPageTest extends TestSuite {

    private static Log log = LogFactory.getLog(MeiShiPageTest.class);

    /*
    测试编号:021
    测试场景：进入门店详情页面，查看门店名称
    预期结果：判断门店名称与门店列表中名称一致
     */
    @Test(description = "进入门店详情页面，查看门店名称")
    public void test021() throws InterruptedException {
        //点击美食
        List<WebElement> getmeishi = meiTuanBusiness.GetElements(By.className(homePage.serviceballclass));
        getmeishi.get(0).click();

        Thread.sleep(2000);
        action.Swipe(1,2);

        //选择第一个门店
        List<WebElement> getmerchanttitle = meiTuanBusiness.GetElements(By.id(meiShiHomePage.merchantid));
        String getmerchantnames = getmerchanttitle.get(0).getText();
        getmerchanttitle.get(0).click();

        //获取门店详情中的门店名称进行比对门店名称
        String getfoodpoiname = action.gettext(By.id(meiShiMerchantDetailPage.foodpoinameid));
        Assert.assertEquals(getmerchantnames,getfoodpoiname);

    }
    /*
    测试编号:022
    测试场景：点击人气榜进入人气榜页面，找到门店后点击回到门店详情页面
    预期结果：1、从详情页提取门店名称和人气排行榜名次
             2、点击人品排行榜进入排行详情页面，滑动页面查找门店名称
             3、点击门店名称，回到详情页，获取门店名称，判断门店名称是否与第一次进入详情页面的门店一致，如果一致说明点击的正确
     */
    @Test(description = "点击人气榜进入人气榜页面，找到门店后点击回到门店详情页面",dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test022(String merchantnamedata) throws InterruptedException, IOException {

        //点击美食
        List<WebElement> getmeishi = meiTuanBusiness.GetElements(By.className(homePage.serviceballclass));
        getmeishi.get(0).click();

//        //选择第一个门店
//        List<WebElement> getmerchanttitle = meiTuanBusiness.GetElements(By.id(meiShiHomePage.merchantid));
//        getmerchanttitle.get(1).click();

        //调业务层
//        //进入门店详情页面
//        meiTuanBusiness.enterMerShiMerchantDetailPage();

        Thread.sleep(1000);
        //根据门店名称滑动页面去找对应的门店
        action.Swipe(By.name(merchantnamedata),1);
        action.click(By.name(merchantnamedata));

        //判断热卖榜排名是否存在，不存在直接报错
        Assert.assertTrue(action.isElementPresented(By.id(meiShiMerchantDetailPage.foodpoirankid)));

        //获取门店详情中的门店名称进行比对门店名称
        String getfoodpoiname = action.gettext(By.id(meiShiMerchantDetailPage.foodpoinameid));
        System.out.println(getfoodpoiname);

        Thread.sleep(1000);
        //获取热卖榜排名信息，获取名次并打印出来
        String foodpoiranktext = action.gettext(By.id(meiShiMerchantDetailPage.foodpoirankid));
        System.out.println(foodpoiranktext);

        Thread.sleep(1000);
        //用这个方法截取只能截取到一位数字，用下面的方法能截取到多位数字
//            String ranknum = foodpoiranktext.substring(foodpoiranktext.length()-2,foodpoiranktext.length()-1);
//            System.out.println(ranknum);

        //用正则表达式的方法能截取到多位数字
        //string的replace方法是替换，根据正则表达式获取要替换的信息，下面是替换成空字符串，达到截取字符串的目的
        String ranknum = foodpoiranktext.replaceAll("^.*第","");
        System.out.println(ranknum);
        ranknum = ranknum.replace("名","");
        System.out.println(ranknum);


        Thread.sleep(1000);
        action.click(By.id(meiShiMerchantDetailPage.foodpoirankid));

        action.Screenshot("测试截图02201");

        //因为排名页面是webview的页面，把全部元素都加载出来，点击无效，所以边滑页面边找元素并点击
        while (true){
            try {
                action.click(By.name(getfoodpoiname));
                Thread.sleep(500);
                driver.findElement(By.id(meiShiMerchantDetailPage.foodpoirankid));
                break;
            }catch (Exception e){
                action.Swipe(1,1);
            }
        }


        String getfoodpoinametwo = action.gettext(By.id(meiShiMerchantDetailPage.foodpoinameid));
        Assert.assertEquals(getfoodpoiname,getfoodpoinametwo);
        Reporter.log("查看人气榜单成功");


        //此代码作废:如果热卖榜排名存在，就进入下面的代码中
//        if (action.isElementPresented(By.id(meiShiMerchantDetailPage.foodpoirankid))){
////            if (getfoodpoiname.equals(getfoodpoinametwo)){
////                System.out.println("查看人气榜单成功");
////                Reporter.log("查看人气榜单成功");
////            }else {
////                System.out.println("查看人气榜单失败");
////                Reporter.log("查看人气榜单失败");
////            }
//
//        }else {
//            action.Screenshot("测试截图02202");
//            System.out.println("不存在人气榜");
//            Reporter.log("不存在人气榜");
//        }

    }

    /*
    测试编号:023
    测试场景：尝试打电话操作
    预期结果：进入打电话页面
     */
    @Test(description = "尝试打电话操作",dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test023(String getmerchantname) throws InterruptedException, IOException {
        //进入门店详情页面
        meiTuanBusiness.enterMerShiMerchantDetailPage(getmerchantname);
        action.click(By.id(meiShiMerchantDetailPage.foodpoitelephoneimgid));
        action.Screenshot("测试截图02301");

    }
    /*
    测试编号:024
    测试场景：如果有买单进入买单页面，输入支付金额、显示优惠金额
    预期结果：1、判断优惠金额是否正确
             2、判断实付金额是否正确
     */
    @Test(description = "如果有买单进入买单页面，输入支付金额、显示优惠金额",dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test024(String getmerchantname) throws InterruptedException, IOException {

        //定义保留两位小数
        DecimalFormat df = new DecimalFormat(".00");
        //支付金额
        String payamountString = "400.55";
        //把支付金额转换成double类型
        double payamountdouble = Double.parseDouble(payamountString);

        //进入门店详情页面
        meiTuanBusiness.enterMerShiMerchantDetailPage(getmerchantname);
        //判断是否有买单按钮，如果有进入买单支付页面
        if (action.isElementPresented(By.id(meiShiMerchantDetailPage.foodtextviewpoipayinfobuttonid))){
            action.click(By.id(meiShiMerchantDetailPage.foodtextviewpoipayinfobuttonid));
            //如果出现“当前时段暂不支持买单”，退出用例
            if (action.isElementPresented(By.name(meiShiMerchantDetailPage.nopay))){
                action.Screenshot("测试截图024001");
                System.out.println("当前时段暂不支持买单");
                Reporter.log("当前时段暂不支持买单");

            }else {
                System.out.println("当前时段支持买单");
                action.click(By.id(meiShiMerchantDetailPage.clickcostid));
                action.sendkeys(By.id(meiShiMerchantDetailPage.sendmoneyid), payamountString);
                //隐藏键盘
                driver.hideKeyboard();

                //先判断是否有优惠，如果没有，则实际支付金额就等于支付金额
                if (action.isElementPresented(By.id(meiShiMerchantDetailPage.titleid))) {
                    System.out.println("有优惠金额");
                    //如果提取出来的内容包含“折”字，则按折扣计算优惠金额，如果提取出来的内容包含“每满”字则走另外一种计算方式
                    //提取出每满多少元减多少元金额
                    String discountAmount = action.gettext(By.id(meiShiMerchantDetailPage.titleid));
                    System.out.println(discountAmount);
                    if (discountAmount.indexOf("折") != -1) {
                        System.out.println("按折扣方式计算金额");
                        double discountAmountRate = Double.parseDouble(discountAmount.replaceAll("折", ""));
                        System.out.println(discountAmountRate);
                        //计算出优惠金额
                        double n = payamountdouble*(1 - discountAmountRate / 10);

                        //获取优惠金额
                        List<WebElement> moneylabellist = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.moneylabelid));
                        double actualcutamount = Double.parseDouble(moneylabellist.get(0).getText());
                        Assert.assertEquals(df.format(actualcutamount), df.format(n));
                        System.out.println("优惠金额计算正确");
                        Reporter.log("优惠金额计算正确");

                        //获取实付金额，判断实付金额是否正确
                        double actualamount = Double.parseDouble(moneylabellist.get(1).getText());
                        System.out.println(actualamount);
                        double expectactualamount = payamountdouble - actualcutamount;
                        Assert.assertEquals(df.format(actualamount), df.format(expectactualamount));
                        System.out.println("实际支付金额计算正确");
                        Reporter.log("实际支付金额计算正确");

                        action.Screenshot("测试截图024002");
                        //获取提交按钮中的金额，判断等于实际支付金额
                        String submitcontent = action.gettext(By.id(meiShiMerchantDetailPage.submitid));
                        String[] submitamountlist = submitcontent.split("元");
                        double submitamount = Double.parseDouble(submitamountlist[0]);
                        Assert.assertEquals(df.format(submitamount), df.format(expectactualamount));
                        System.out.println("按钮中显示的实际支付金额正确");
                        Reporter.log("按钮中显示的实际支付金额正确");
                        action.click(By.id(meiShiMerchantDetailPage.submitid));
                        Thread.sleep(1000);
                        action.Screenshot("测试截图024003");

                    } else if (discountAmount.indexOf("每满") != -1) {
                        System.out.println("按满减方式计算金额");
                        //分割提取出来的内容，获取每满XX元和减XX元
                        String[] fullamountlist = discountAmount.split("减");
                        System.out.println(fullamountlist[0]);
                        System.out.println(fullamountlist[1]);

                        //再通过替换获取到具体的数值
                        int fullamount = Integer.parseInt(fullamountlist[0].replaceAll("每满", ""));
                        int cutamount = Integer.parseInt(fullamountlist[1].replaceAll("元", ""));
                        System.out.println("每满" + fullamount + "元,减" + cutamount + "元");

                        //计算出优惠金额
                        int n = (int) payamountdouble/ fullamount;
                        System.out.println(n);
                        int m = n * cutamount;
                        System.out.println("计算出来的优惠金额是" + df.format(m) + "元");

                        //获取优惠金额
                        List<WebElement> moneylabellist = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.moneylabelid));
                        int actualcutamount = Integer.parseInt(moneylabellist.get(0).getText());

                        Assert.assertEquals(m, actualcutamount);
                        System.out.println("优惠金额计算正确");
                        Reporter.log("优惠金额计算正确");

                        //获取实付金额，判断实付金额是否正确
                        double actualamount = Double.parseDouble(moneylabellist.get(1).getText());
                        System.out.println(actualamount);
                        double expectactualamount = payamountdouble - actualcutamount;
                        Assert.assertEquals(df.format(actualamount), df.format(expectactualamount));
                        System.out.println("实际支付金额计算正确");
                        Reporter.log("实际支付金额计算正确");

                        action.Screenshot("测试截图024002");
                        //获取提交按钮中的金额，判断等于实际支付金额
                        String submitcontent = action.gettext(By.id(meiShiMerchantDetailPage.submitid));
                        String[] submitamountlist = submitcontent.split("元");
                        double submitamount = Double.parseDouble(submitamountlist[0]);
                        Assert.assertEquals(df.format(expectactualamount), df.format(submitamount));
                        System.out.println("按钮中显示的实际支付金额正确");
                        Reporter.log("按钮中显示的实际支付金额正确");
                        action.click(By.id(meiShiMerchantDetailPage.submitid));
                        Thread.sleep(1000);
                        action.Screenshot("测试截图024003");

                    } else {
                        System.out.println("不计算金额报错");
                    }
                } else {
                    System.out.println("没有优惠金额");
                    double actualamount = Double.parseDouble(action.gettext(By.id(meiShiMerchantDetailPage.moneylabelid)));
                    System.out.println(actualamount);
                    double expectactualamount = payamountdouble;
                    Assert.assertEquals(actualamount, expectactualamount);
                    System.out.println("实际支付金额计算正确");
                    Reporter.log("实际支付金额计算正确");

                    action.Screenshot("测试截图024002");
                    //获取提交按钮中的金额，判断等于实际支付金额
                    String submitcontent = action.gettext(By.id(meiShiMerchantDetailPage.submitid));
                    String[] submitamountlist = submitcontent.split("元");
                    int submitamount = Integer.parseInt(submitamountlist[0]);
                    Assert.assertEquals(expectactualamount, submitamount);
                    System.out.println("按钮中显示的实际支付金额正确");
                    Reporter.log("按钮中显示的实际支付金额正确");
                    action.click(By.id(meiShiMerchantDetailPage.submitid));
                    Thread.sleep(1000);
                    action.Screenshot("测试截图024003");


                }
            }
            }

        else {
            System.out.println("没有买单按钮");
            Reporter.log("没有买单按钮");
        }

    }
    /*
    测试编号:025
    测试场景：如果有买单进入买单页面，输入支付金额、勾选输入不参与优惠金额、优惠金额显示有变化，预期结果不变化
    预期结果：1、判断优惠金额是否正确
             2、判断实付金额是否正确
     */
    @Test(description = "如果有买单进入买单页面，输入支付金额、勾选输入不参与优惠金额、优惠金额显示有变化，预期结果不变化",dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test025(String getmerchantname) throws IOException, InterruptedException {

        //定义保留两位小数
        DecimalFormat df = new DecimalFormat(".00");
        //支付金额
        String payamountString = "400.55";
        //把支付金额转换成double类型
        double payamountdouble = Double.parseDouble(payamountString);

        //进入门店详情页面
        meiTuanBusiness.enterMerShiMerchantDetailPage(getmerchantname);
        //判断是否有买单按钮，如果有进入买单支付页面
        if (action.isElementPresented(By.id(meiShiMerchantDetailPage.foodtextviewpoipayinfobuttonid))){
            action.click(By.id(meiShiMerchantDetailPage.foodtextviewpoipayinfobuttonid));
            //如果出现“当前时段暂不支持买单”，退出用例
            if (action.isElementPresented(By.name(meiShiMerchantDetailPage.nopay))){
                action.Screenshot("测试截图025001");
                System.out.println("当前时段暂不支持买单");
                Reporter.log("当前时段暂不支持买单");

            }else {
                System.out.println("当前时段支持买单");
                action.click(By.id(meiShiMerchantDetailPage.clickcostid));
                action.sendkeys(By.id(meiShiMerchantDetailPage.sendmoneyid),payamountString);

                //勾选不参与优惠
                action.click(By.id(meiShiMerchantDetailPage.nodiscountcheckboxid));
                //点击不参与优惠金额输入框
                action.click(By.id(meiShiMerchantDetailPage.nodiscounthintid));
                //输入不参与优惠金额
                action.sendkeys(By.id(meiShiMerchantDetailPage.nodiscountid),"30.33");
                //隐藏键盘
                driver.hideKeyboard();
                //获取输入的不参与优惠金额
                double nodiscountamount = Double.parseDouble(action.gettext(By.id(meiShiMerchantDetailPage.nodiscountid)));
                System.out.println("不参与优惠的金额"+nodiscountamount);

                //先判断是否有优惠，如果没有，则实际支付金额就等于支付金额
                if (action.isElementPresented(By.id(meiShiMerchantDetailPage.titleid))){
                    System.out.println("有优惠金额");
                 //如果提取出来的内容包含“折”字，则按折扣计算优惠金额，如果提取出来的内容包含“每满”字则走另外一种计算方式
                //提取出每满多少元减多少元金额
                String discountAmount = action.gettext(By.id(meiShiMerchantDetailPage.titleid));
                System.out.println(discountAmount);
                    if (discountAmount.indexOf("折") != -1){
                        System.out.println("按折扣方式计算金额");
                        double discountAmountRate = Double.parseDouble(discountAmount.replaceAll("折",""));
                        System.out.println(discountAmountRate);
                        //计算出优惠金额
                        double n = (payamountdouble-nodiscountamount)*(1-discountAmountRate/10);

                        //获取优惠金额
                        List<WebElement> moneylabellist = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.moneylabelid));
                        double actualcutamount = Double.parseDouble(moneylabellist.get(0).getText());
                        Assert.assertEquals(df.format(actualcutamount),df.format(n));
                        System.out.println("优惠金额计算正确");
                        Reporter.log("优惠金额计算正确");

                        //获取实付金额，判断实付金额是否正确
                        double actualamount = Double.parseDouble(moneylabellist.get(1).getText());
                        System.out.println(actualamount);
                        double expectactualamount = payamountdouble-actualcutamount;
                        Assert.assertEquals(df.format(actualamount),df.format(expectactualamount));
                        System.out.println("实际支付金额计算正确");
                        Reporter.log("实际支付金额计算正确");

                        action.Screenshot("测试截图025002");
                        //获取提交按钮中的金额，判断等于实际支付金额
                        String submitcontent = action.gettext(By.id(meiShiMerchantDetailPage.submitid));
                        String[] submitamountlist = submitcontent.split("元");
                        double submitamount = Double.parseDouble(submitamountlist[0]);
                        Assert.assertEquals(df.format(submitamount),df.format(expectactualamount));
                        System.out.println("按钮中显示的实际支付金额正确");
                        Reporter.log("按钮中显示的实际支付金额正确");
                        action.click(By.id(meiShiMerchantDetailPage.submitid));
                        Thread.sleep(1000);
                        action.Screenshot("测试截图025003");

                    }else if (discountAmount.indexOf("每满") != -1){
                        System.out.println("按满减方式计算金额");
                        //分割提取出来的内容，获取每满XX元和减XX元
                        String[] fullamountlist = discountAmount.split("减");
                        System.out.println(fullamountlist[0]);
                        System.out.println(fullamountlist[1]);

                        //再通过替换获取到具体的数值
                        int fullamount = Integer.parseInt(fullamountlist[0].replaceAll("每满",""));
                        int cutamount = Integer.parseInt(fullamountlist[1].replaceAll("元",""));
                        System.out.println("每满"+fullamount+"元,减"+cutamount+"元");

                        //计算出优惠金额
                        int n = (int)(payamountdouble-nodiscountamount)/fullamount;
                        System.out.println(n);
                        int m = n*cutamount;
                        System.out.println("计算出来的优惠金额是"+df.format(m)+"元");

                        //获取优惠金额
                        List<WebElement> moneylabellist = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.moneylabelid));
                        int actualcutamount = Integer.parseInt(moneylabellist.get(0).getText());

                        Assert.assertEquals(m,actualcutamount);
                        System.out.println("优惠金额计算正确");
                        Reporter.log("优惠金额计算正确");

                        //获取实付金额，判断实付金额是否正确
                        double actualamount = Double.parseDouble(moneylabellist.get(1).getText());
                        System.out.println(actualamount);
                        double expectactualamount = payamountdouble-actualcutamount;
                        Assert.assertEquals(df.format(actualamount),df.format(expectactualamount));
                        System.out.println("实际支付金额计算正确");
                        Reporter.log("实际支付金额计算正确");

                        action.Screenshot("测试截图025002");
                        //获取提交按钮中的金额，判断等于实际支付金额
                        String submitcontent = action.gettext(By.id(meiShiMerchantDetailPage.submitid));
                        String[] submitamountlist = submitcontent.split("元");
                        double submitamount = Double.parseDouble(submitamountlist[0]);
                        Assert.assertEquals(df.format(expectactualamount),df.format(submitamount));
                        System.out.println("按钮中显示的实际支付金额正确");
                        Reporter.log("按钮中显示的实际支付金额正确");
                        action.click(By.id(meiShiMerchantDetailPage.submitid));
                        Thread.sleep(1000);
                        action.Screenshot("测试截图025003");

                    }else {
                        System.out.println("不计算金额报错");
                    }
                }else{
                    System.out.println("没有优惠金额");
                    double actualamount = Double.parseDouble(action.gettext(By.id(meiShiMerchantDetailPage.moneylabelid)));
                    System.out.println(actualamount);
                    double expectactualamount = payamountdouble;
                    Assert.assertEquals(actualamount,expectactualamount);
                    System.out.println("实际支付金额计算正确");
                    Reporter.log("实际支付金额计算正确");

                    action.Screenshot("测试截图025002");
                    //获取提交按钮中的金额，判断等于实际支付金额
                    String submitcontent = action.gettext(By.id(meiShiMerchantDetailPage.submitid));
                    String[] submitamountlist = submitcontent.split("元");
                    int submitamount = Integer.parseInt(submitamountlist[0]);
                    Assert.assertEquals(expectactualamount,submitamount);
                    System.out.println("按钮中显示的实际支付金额正确");
                    Reporter.log("按钮中显示的实际支付金额正确");
                    action.click(By.id(meiShiMerchantDetailPage.submitid));
                    Thread.sleep(1000);
                    action.Screenshot("测试截图025003");
                }
            }

        }else {
            System.out.println("没有买单按钮");
            Reporter.log("没有买单按钮");
        }
    }
    /*
    测试编号:026
    测试场景：如果有买单进入买单页面，勾选掉优惠金额，判断实付金额
    预期结果：1、勾选掉优惠金额，判断实付金额
     */
    @Test(description = "如果有买单进入买单页面，勾选掉优惠金额，判断实付金额",dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test026(String getmerchantname) throws IOException, InterruptedException {
        //定义保留两位小数
        DecimalFormat df = new DecimalFormat(".00");
        //支付金额
        String payamountString = "400.55";
        //把支付金额转换成double类型
        double payamountdouble = Double.parseDouble(payamountString);

        //进入门店详情页面
        meiTuanBusiness.enterMerShiMerchantDetailPage(getmerchantname);
        //判断是否有买单按钮，如果有进入买单支付页面
        if (action.isElementPresented(By.id(meiShiMerchantDetailPage.foodtextviewpoipayinfobuttonid))){
            action.click(By.id(meiShiMerchantDetailPage.foodtextviewpoipayinfobuttonid));
            //如果出现“当前时段暂不支持买单”，退出用例
            if (action.isElementPresented(By.name(meiShiMerchantDetailPage.nopay))){
                action.Screenshot("测试截图026001");
                System.out.println("当前时段暂不支持买单");
                Reporter.log("当前时段暂不支持买单");

            }else {
                System.out.println("当前时段支持买单");
                action.click(By.id(meiShiMerchantDetailPage.clickcostid));
                action.sendkeys(By.id(meiShiMerchantDetailPage.sendmoneyid),payamountString);
                //隐藏键盘
                driver.hideKeyboard();

                //先判断是否有优惠金额
                if (action.isElementPresented(By.id(meiShiMerchantDetailPage.titleid))){
                    System.out.println("有优惠金额");
                    //勾选掉优惠金额
                    action.click(By.id(meiShiMerchantDetailPage.checkboxid));
                    //获取实付金额，判断实付金额是否正确
                    double actualamount = Double.parseDouble(action.gettext(By.id(meiShiMerchantDetailPage.moneylabelid)));
                    System.out.println(df.format(actualamount));
                    double expectactualamount = payamountdouble;
                    Assert.assertEquals(df.format(actualamount),df.format(expectactualamount));
                    System.out.println("实际支付金额计算正确");
                    Reporter.log("实际支付金额计算正确");

                    action.Screenshot("测试截图026002");
                    //获取提交按钮中的金额，判断等于实际支付金额
                    String submitcontent = action.gettext(By.id(meiShiMerchantDetailPage.submitid));
                    String[] submitamountlist = submitcontent.split("元");
                    double submitamount = Double.parseDouble(submitamountlist[0]);
                    Assert.assertEquals(df.format(expectactualamount),df.format(submitamount));
                    System.out.println("按钮中显示的实际支付金额正确");
                    Reporter.log("按钮中显示的实际支付金额正确");
                    action.click(By.id(meiShiMerchantDetailPage.submitid));
                    Thread.sleep(1000);
                    action.Screenshot("测试截图026003");

                }else {
                    System.out.println("没有优惠金额选项");
                    Reporter.log("没有优惠金额选项");
                }
            }

        }else {
            System.out.println("没有买单按钮");
            Reporter.log("没有买单按钮");
        }
    }
    /*
    测试编号:027
    测试场景：如果有抢购进入抢购页面,点击抢购按钮进入单个代金券的支付页面
    预期结果：操作抢购进入支付页面
     */
    @Test(description = "如果有抢购进入抢购页面",dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test027(String getmerchantname) throws IOException, InterruptedException {
        //进入门店详情页面
        meiTuanBusiness.enterMerShiMerchantDetailPage(getmerchantname);

        //先判断是否有代金券抢购按钮
        Assert.assertTrue(action.isElementPresented(By.id(meiShiMerchantDetailPage.foodtextviewpoivoucheritembuyid)));
        System.out.println("有抢购按钮");
        List<WebElement>  dealitemprices = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.foodtextviewpoidealitempriceid));
        String dealitemprice = dealitemprices.get(0).getText();

        action.click(By.id(meiShiMerchantDetailPage.foodtextviewpoivoucheritembuyid));
        String dealprice = action.gettext(By.id(meiShiMerchantDetailPage.dealpriceid));
        Assert.assertEquals(dealitemprice,dealprice);

        double dealpriceint = Double.parseDouble(dealprice.replaceAll("¥",""));

        action.click(By.id(meiShiMerchantDetailPage.increasegoodsnumid));
        int goodsnum = Integer.parseInt(action.gettext(By.id(meiShiMerchantDetailPage.goodsnumid)));
        String total = action.gettext(By.id(meiShiMerchantDetailPage.totalid));
        double totalint = Double.parseDouble(total.replaceAll("¥",""));
        String totalpricepaymoney = action.gettext(By.id(meiShiMerchantDetailPage.totalpricepaymoneyid));
        double totalpricepaymoneyint = Double.parseDouble(totalpricepaymoney.replaceAll("¥",""));

        double expecttotal = goodsnum*dealpriceint;
        Assert.assertEquals(expecttotal,totalint);
        Assert.assertEquals(expecttotal,totalpricepaymoneyint);

        //校验提交按钮中的金额
        String submitprice = action.gettext(By.id(meiShiMerchantDetailPage.submitpriceid));
        double submitpriceint = Double.parseDouble(submitprice.replaceAll("¥",""));
        Assert.assertEquals(expecttotal,submitpriceint);
        action.Screenshot("测试截图027001");
        action.click(By.id(meiShiMerchantDetailPage.submitordercontainerid));
        System.out.println("校验成功");
        Reporter.log("抢购支付页面测试成功");
        Thread.sleep(1000);
        action.Screenshot("测试截图027002");
    }
    /*
    测试编号:028
    测试场景：如果有抢购进入抢购页面，点击代金券区域，进入选择代金券页面
    预期结果：1、选择不同的代金券，点击抢购，进入支付页面
     */
    @Test(description = "如果有抢购进入抢购页面，点击代金券区域，进入选择代金券页面",dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test028(String getmerchantname) throws InterruptedException, IOException {
        //进入门店详情页面
        meiTuanBusiness.enterMerShiMerchantDetailPage(getmerchantname);

        //先判断是否有代金券抢购按钮
        Assert.assertTrue(action.isElementPresented(By.id(meiShiMerchantDetailPage.foodtextviewpoivoucheritembuyid)));
        System.out.println("有抢购按钮");
        //点击代金券抢购金额
        List<WebElement>  dealitemprices = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.foodtextviewpoidealitempriceid));
        String dealitemprice = dealitemprices.get(0).getText();
        dealitemprices.get(0).click();
        Thread.sleep(1000);
        action.Screenshot("测试截图028001");

        //进入选择代金券页面，比对详情页的抢购金额和选择代金券页面的抢购金额
        String saleprice = action.gettext(By.id(meiShiMerchantDetailPage.salepriceid));
//            String salepriceint = saleprice.replaceAll("","");
        Assert.assertEquals(dealitemprice,saleprice);
        //点击立即抢购
        action.click(By.name(meiShiMerchantDetailPage.lijiqianggoubuttonname));

        //接下来的代码跟上一个用例的代码一致
        String dealprice = action.gettext(By.id(meiShiMerchantDetailPage.dealpriceid));
        //比对支付页面的代购金额和详情页的抢购金额
        Assert.assertEquals(dealitemprice,dealprice);

        int dealpriceint = Integer.parseInt(dealprice.replaceAll("¥",""));

        action.click(By.id(meiShiMerchantDetailPage.increasegoodsnumid));
        int goodsnum = Integer.parseInt(action.gettext(By.id(meiShiMerchantDetailPage.goodsnumid)));
        String total = action.gettext(By.id(meiShiMerchantDetailPage.totalid));
        int totalint = Integer.parseInt(total.replaceAll("¥",""));
        String totalpricepaymoney = action.gettext(By.id(meiShiMerchantDetailPage.totalpricepaymoneyid));
        int totalpricepaymoneyint = Integer.parseInt(totalpricepaymoney.replaceAll("¥",""));

        int expecttotal = goodsnum*dealpriceint;
        Assert.assertEquals(expecttotal,totalint);
        Assert.assertEquals(expecttotal,totalpricepaymoneyint);

        //校验提交按钮中的金额
        String submitprice = action.gettext(By.id(meiShiMerchantDetailPage.submitpriceid));
        int submitpriceint = Integer.parseInt(submitprice.replaceAll("¥",""));
        Assert.assertEquals(expecttotal,submitpriceint);
        action.Screenshot("测试截图028002");
        action.click(By.id(meiShiMerchantDetailPage.submitordercontainerid));
        System.out.println("校验成功");
        Reporter.log("抢购支付页面测试成功");
        Thread.sleep(1000);
        action.Screenshot("测试截图028003");
    }
    /*
    测试编号:029
    测试场景：如果有到店吃套餐，点击抢购，滑动套餐名称
    预期结果：滑动套餐名称，比对滑动前后套餐名称不一致，才能滑动成功
     */
    @Test(description = "如果有到店吃套餐，点击抢购，滑动套餐名称",dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test029(String getmerchantname) throws InterruptedException {

        //进入门店详情页面
        meiTuanBusiness.enterMerShiMerchantDetailPage(getmerchantname);
        Thread.sleep(2000);
        action.Swipe(1,1);
        //判断有到店吃套餐抢购按钮
        Assert.assertTrue(action.isElementPresented(By.id(meiShiMerchantDetailPage.foodpoidealbuttonbuyid)));
        System.out.println("有到店吃套餐抢购按钮");
        Reporter.log("有到店吃套餐抢购按钮");

        List<WebElement> foodpoidealbuttonbuys = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.foodpoidealbuttonbuyid));
        foodpoidealbuttonbuys.get(0).click();

        List<WebElement> ActionBarsBefore = meiTuanBusiness.GetElements(By.className(meiShiMerchantDetailPage.ActionBarsBeforeclassname));

        List<WebElement> gettexts = meiTuanBusiness.GetElements(By.className(meiShiMerchantDetailPage.gettextsclassname));
        String ActionBarBeforeFirsttext = gettexts.get(1).getText();
        Thread.sleep(500);

        action.swipeelement(ActionBarsBefore.get(0),"左滑",1);
        Thread.sleep(500);
        List<WebElement> ActionBarsAfter = meiTuanBusiness.GetElements(By.className(meiShiMerchantDetailPage.gettextsclassname));
        String ActionBarsAftertext = ActionBarsAfter.get(1).getText();
        Thread.sleep(500);
        System.out.println("滑动前套餐名称是："+ActionBarBeforeFirsttext);
        System.out.println("滑动后套餐名称是："+ActionBarsAftertext);
        //比较两个套餐名称不相等
        Assert.assertNotEquals(ActionBarBeforeFirsttext,ActionBarsAftertext);
    }
    /*
    测试编号:030
    测试场景：如果有到店吃套餐，点击抢购，选择套餐名称进入支付抢购页面
    预期结果：进入支付抢购页面，点击增加套餐券数量再减少计算金额正确
     */
    @Test(description = "如果有到店吃套餐，点击抢购，选择套餐名称进入支付抢购页面",dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test030(String getmerchantname) throws InterruptedException, IOException {
        DecimalFormat df = new DecimalFormat("0.00");
        //进入门店详情页面
        meiTuanBusiness.enterMerShiMerchantDetailPage(getmerchantname);
        Thread.sleep(2000);
        action.Swipe(1,1);
        //判断有到店吃套餐抢购按钮
        Assert.assertTrue(action.isElementPresented(By.id(meiShiMerchantDetailPage.foodpoidealbuttonbuyid)));
        System.out.println("有到店吃套餐抢购按钮");
        Reporter.log("有到店吃套餐抢购按钮");

        List<WebElement> foodpoidealbuttonbuys = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.foodpoidealbuttonbuyid));
        foodpoidealbuttonbuys.get(0).click();

        //获取套餐名称，截取出金额
        List<WebElement> gettexts = meiTuanBusiness.GetElements(By.className(meiShiMerchantDetailPage.gettextsclassname));
        String ActionBarFirsttext = gettexts.get(1).getText();
        String[] ActionBarFirsttextmoney = ActionBarFirsttext.split("元");
        double ActionBarFirsttextmoneyint = Double.parseDouble(ActionBarFirsttextmoney[0]);

        //获取套餐实际要支付的金额
        String saleprice = action.gettext(By.id(meiShiMerchantDetailPage.salepriceid));
        double salepriceint = Double.parseDouble(saleprice.replaceAll("¥",""));
        //比对两个金额相等
        Assert.assertEquals(df.format(ActionBarFirsttextmoneyint),df.format(salepriceint));
        action.Screenshot("测试截图030001");

        //点击立即抢购按钮，进入支付页面
        action.click(By.name(meiShiMerchantDetailPage.lijiqianggoubuttonname));
        //接下来的代码跟test028用例的代码一致
        String dealprice = action.gettext(By.id(meiShiMerchantDetailPage.dealpriceid));
        double dealpriceint = Double.parseDouble(dealprice.replaceAll("¥",""));
        //比对支付页面的代购金额和详情页的套餐抢购金额一致
        Assert.assertEquals(df.format(ActionBarFirsttextmoneyint),df.format(dealpriceint));

        //点击两遍增加数量按钮，再点击一遍减少数量按钮
        action.click(By.id(meiShiMerchantDetailPage.increasegoodsnumid));
        action.click(By.id(meiShiMerchantDetailPage.increasegoodsnumid));
        action.click(By.id(meiShiMerchantDetailPage.decreasegoodsnumid));

        //获取数量
        int goodsnum = Integer.parseInt(action.gettext(By.id(meiShiMerchantDetailPage.goodsnumid)));
        String total = action.gettext(By.id(meiShiMerchantDetailPage.totalid));
        double totalint = Double.parseDouble(total.replaceAll("¥",""));
        String totalpricepaymoney = action.gettext(By.id(meiShiMerchantDetailPage.totalpricepaymoneyid));
        double totalpricepaymoneyint = Double.parseDouble(totalpricepaymoney.replaceAll("¥",""));

        double expecttotal = goodsnum*dealpriceint;
        Assert.assertEquals(expecttotal,totalint);
        Assert.assertEquals(expecttotal,totalpricepaymoneyint);

        //校验提交按钮中的金额
        String submitprice = action.gettext(By.id(meiShiMerchantDetailPage.submitpriceid));
        double submitpriceint = Double.parseDouble(submitprice.replaceAll("¥",""));
        Assert.assertEquals(expecttotal,submitpriceint);
        action.Screenshot("测试截图030002");
        action.click(By.id(meiShiMerchantDetailPage.submitordercontainerid));
        System.out.println("校验成功");
        Reporter.log("抢购支付页面测试成功");
        action.Screenshot("测试截图030003");
    }
    /*
    测试编号:031
    测试场景：查看用户评论，点击评论内容先进入评论列表页面，再点击评论内容进入评论详情页面，比对用户昵称和评论内容，最后再返回到用户评论首页
    预期结果：1、昵称和评论内容比对正确
             2、回退到用户评论首页正确
     */
    @Test(description = "查看用户评论，点击评论内容先进入评论列表页面，再点击评论内容进入评论详情页面，比对用户昵称和评论内容，最后再返回到用户评论首页",dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test031(String getmerchantname) throws InterruptedException, IOException {
        //进入门店详情页面
        meiTuanBusiness.enterMerShiMerchantDetailPage(getmerchantname);

        //判断有用户评论信息
        Assert.assertTrue(action.isElementPresented(By.name(meiShiMerchantDetailPage.usercommentname)));
        System.out.println("有用户评论信息");

        action.click(By.name(meiShiMerchantDetailPage.usercommentname));
        action.Screenshot("测试截图031001");

        //获取用户昵称
        List<WebElement> getusername = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.usernameid));
        String username = getusername.get(0).getText();
        //获取用户评论内容
        List<WebElement> getcomment = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.commentdescriptionid));
        String comment = getcomment.get(0).getText();

        //点击用户评论进入评论列表页面
        getcomment.get(0).click();
        action.Screenshot("测试截图031002");
        //获取用户昵称
        List<WebElement> getusernameforlist = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.feedusernameid));
        String usernameforlist = getusernameforlist.get(0).getText();
        //获取用户评论内容
        List<WebElement> getfeedcontentforlist = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.feedcontentid));
        String feedcontentforlist = getfeedcontentforlist.get(0).getText();

        Assert.assertEquals(username,usernameforlist);
//            Assert.assertEquals(comment,feedcontentforlist);

        //点击用户评论进入评论详情页面
        getfeedcontentforlist.get(0).click();
        //再次获取用户昵称
        List<WebElement> getusernamefordetail = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.feedusernameid));
        String usernamefordetail = getusernamefordetail.get(0).getText();
        //再次获取用户评论内容
        List<WebElement> getfeedcontentfordetail = meiTuanBusiness.GetElements(By.id(meiShiMerchantDetailPage.feedcontentid));
        String feedcontentfordetail = getfeedcontentfordetail.get(0).getText();

        Assert.assertEquals(username,usernamefordetail);
//            Assert.assertEquals(comment,getfeedcontentfordetail);

        //返回到用户评论首页
        action.back();
        action.back();

        Assert.assertTrue(action.isElementPresented(By.name(meiShiMerchantDetailPage.usercommentname)),"回到用户评论首页");
        action.Screenshot("测试截图031003");
    }
    /*
    测试编号:032
    测试场景：选购商品，进入购物车计算商品金额与选购的商品金额一致（此用例作废，因为弹出购物车是popwindow无法聚焦导致定位购物车中的商品金额
             失败，只能需要安卓开发把聚焦打开才行）
    预期结果：成功清空购物车
     */
    @Test(description = "选购商品",enabled = false,dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test032(String getmerchantname) throws InterruptedException {

        //定义金额为两位小数
        DecimalFormat df = new DecimalFormat(".00");
        //进入外卖门店详情页面
        meiTuanBusiness.enterWaiMaiMerchantDetailPage(getmerchantname);

        //获取商户优惠
        action.click(By.id(waiMaiMerchantDetailPage.showheaderbuttonid));
        List<WebElement> gettxtpoiactivity = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtpoiactivityid));
        String[] gettxtpoiactivitysplit =gettxtpoiactivity.get(0).getText().split(";");
        String[] gettxtpoiactivitysplit1 = gettxtpoiactivitysplit[0].split("减");
        //满XX元
        String txtpoiactivityfull = gettxtpoiactivitysplit1[0].replaceAll("满","");
        //减XX元
        String txtpoiactivitysale = gettxtpoiactivitysplit1[1];
        System.out.println("满"+txtpoiactivityfull+"减"+txtpoiactivitysale);
        action.Swipe(1,1);

        //先获取商品价格
        List<WebElement> txtstickyfoodListadapterfoodpricelist = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtstickyfoodListadapterfoodpriceid));
        double txtstickyfoodListadapterfoodprice = Double.parseDouble(txtstickyfoodListadapterfoodpricelist.get(0).getText().replaceAll("¥",""));
        System.out.println("商品价格"+txtstickyfoodListadapterfoodprice);

        //先判断是展示商品规格还是加号选择商品
        if (action.isElementPresented(By.id(waiMaiMerchantDetailPage.btnchooseskuid))){

            action.click(By.id(waiMaiMerchantDetailPage.btnchooseskuid));
            //获取商品价格
            double txtprice = Double.parseDouble(action.gettext(By.id(waiMaiMerchantDetailPage.txtpriceid)));
            System.out.println("商品规格弹框中商品价格："+txtprice);
            Assert.assertEquals(df.format(txtstickyfoodListadapterfoodprice),df.format(txtprice));
            //点击加入购物车
            action.click(By.id(waiMaiMerchantDetailPage.txtaddshopcartid));
            //关闭选择商品规格
            action.click(By.id(waiMaiMerchantDetailPage.btndialogcloseid));

            //获取选购商品的数量
            int txtskufoodcount = Integer.parseInt(action.gettext(By.id(waiMaiMerchantDetailPage.txtskufoodcountid)));

        }else if (action.isElementPresented(By.id(waiMaiMerchantDetailPage.imgfoodCountaddid))){

            action.click(By.id(waiMaiMerchantDetailPage.imgfoodCountaddid));
            //获取选购商品的数量
            List<WebElement> txtfoodCountnumbers = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtfoodCountnumberid));
            int txtfoodCountnumber = Integer.parseInt(txtfoodCountnumbers.get(0).getText());

        }else {
            System.out.println("选择商品失败");
        }

        //选择完商品后进入购物车计算
        //先获取购物车金额
        double txtdealInfoprice = Double.parseDouble(action.gettext(By.id(waiMaiMerchantDetailPage.txtdealInfopriceid)).replaceAll("¥",""));
        System.out.println("购物车金额"+txtdealInfoprice);
        Thread.sleep(500);
        action.click(By.id(waiMaiMerchantDetailPage.txtdealInfopriceid));
//        Thread.sleep(2000);
//        Set<String> handle = driver.getContextHandles();
//        for (String s : handle){
//            System.out.println(s);
//        }
//        //切换到原生app，定位弹框中的数据
//        driver.context("NATIVE_APP");


        //再获取购物车中商品金额和商品数量，与上面获取的商品金额和商品数量比较是否一致
//        Thread.sleep(1000);
//        List<WebElement> txtfoodpricelist = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtfoodpriceid));
//        double txtfoodprice = Double.parseDouble(txtfoodpricelist.get(0).getText().replaceAll("¥",""));
       //定位不到popwindow弹框里的内容，需要Android开发设置焦点可用才行，代码如下：
//        View contentView = LayoutInflater.from(mContext).inflate(R.layout.popwindow,null);
//        这行代码下面 添加这行代码即可.
//        contentView.setFocusable(True); 
//        重新给你打包 即可获取到这个弹层的元素.

//        double txtfoodprice = Double.parseDouble(action.gettext(By.id(waiMaiMerchantDetailPage.txtfoodpriceid)).replaceAll("¥",""));
//        System.out.println("购物车中商品金额"+txtfoodprice);
//        //获取商品数量
//        List<WebElement> txtfoodCountnumbersInShoppingCart = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtfoodCountnumberid));
//        int txtfoodCountnumberInShoppingCart = Integer.parseInt(txtfoodCountnumbersInShoppingCart.get(0).getText());
//        System.out.println(txtfoodCountnumberInShoppingCart);

        //比较商品金额
//        Assert.assertEquals(df.format(txtstickyfoodListadapterfoodprice),df.format(txtfoodprice));
        //比较商品数量
//        Assert.assertEquals(txtfoodCountnumber,txtfoodCountnumberInShoppingCart);


        //清空购物车
        action.click(By.id(waiMaiMerchantDetailPage.btnclearid));
        Thread.sleep(1000);


    }
    /*
    测试编号:033
    测试场景：选购商品，清空购物车,此用例作废，清空购物车也是在popwindow弹框中所以有可能会定位不到
    预期结果：成功清空购物车
     */
    @Test(description = "清空购物车",enabled = false,dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test033(String getmerchantname) throws InterruptedException {


        //进入外卖门店详情页面
        meiTuanBusiness.enterWaiMaiMerchantDetailPage(getmerchantname);

        //先判断是展示商品规格还是加号选择商品
        if (action.isElementPresented(By.id(waiMaiMerchantDetailPage.btnchooseskuid))){

            action.click(By.id(waiMaiMerchantDetailPage.btnchooseskuid));
            //点击加入购物车
            action.click(By.id(waiMaiMerchantDetailPage.txtaddshopcartid));
            //关闭选择商品规格
            action.click(By.id(waiMaiMerchantDetailPage.btndialogcloseid));

        }else if (action.isElementPresented(By.id(waiMaiMerchantDetailPage.imgfoodCountaddid))){

            action.click(By.id(waiMaiMerchantDetailPage.imgfoodCountaddid));
        }else {
            System.out.println("选择商品失败");
        }

        //选择完商品后进入购物车计算
        //先获取购物车金额
        Thread.sleep(500);
        action.click(By.id(waiMaiMerchantDetailPage.txtdealInfopriceid));
        Thread.sleep(1000);
        //清空购物车
        driver.findElement(By.name("清空购物车")).click();
        Thread.sleep(1000);
        Assert.assertTrue(action.isElementPresented(By.id("com.sankuai.meituan:id/ll_empty_shopping_cart")));
//        action.click(By.id(waiMaiMerchantDetailPage.btnclearid));
        Thread.sleep(1000);
    }
    /*
    测试编号:034
    测试场景：根据满XX元起送，判断要选购的商品数量，判断出优惠金额
    预期结果：进入提交订单页面，计算金额
     */
    @Test(description = "进入提交订单页面，计算金额",dataProvider = "getdata",dataProviderClass = GetDataProvider.class)
    public void test034(String getmerchantname) throws InterruptedException, IOException {
        //定义金额为两位小数
        DecimalFormat df = new DecimalFormat(".00");
        //进入外卖门店详情页面
        meiTuanBusiness.enterWaiMaiMerchantDetailPage(getmerchantname);
        //定义map用来存储满减优惠金额，因为map不是有序的，所以不方便后面比较数据，只能用list
//        Map<String,String> map = new HashMap<String, String>();
        List list = new ArrayList();


        //获取商户优惠，用于计算后面的优惠金额
        action.click(By.id(waiMaiMerchantDetailPage.showheaderbuttonid));
        List<WebElement> gettxtpoiactivity = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtpoiactivityid));
        System.out.println(gettxtpoiactivity.get(0).getText());
        String[] gettxtpoiactivitysplit =gettxtpoiactivity.get(0).getText().split(";");

        for (String s : gettxtpoiactivitysplit){
            String[] gettxtpoiactivitysplit1 = s.split("减");
            for (String s1 : gettxtpoiactivitysplit1){
                String gettxtpoiactivitysplit2 =  s1.replaceAll("满","");
                list.add(gettxtpoiactivitysplit2);
            }
        }
        //遍历list
//        for (Object s2 : list){
//            System.out.println(s2.toString());
//        }
        action.Swipe(1,1);

        //获取商品价格
        List<WebElement> txtstickyfoodListadapterfoodpricelist = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtstickyfoodListadapterfoodpriceid));
        double txtstickyfoodListadapterfoodprice = Double.parseDouble(txtstickyfoodListadapterfoodpricelist.get(0).getText().replaceAll("¥",""));
        System.out.println("商品列表中商品价格："+txtstickyfoodListadapterfoodprice);

        //获取满XX元起送
        String[] txtdealInfo = action.gettext(By.id(waiMaiMerchantDetailPage.txtdealInfosubmitid)).split("起");
        double txtdealInfomoney = Double.parseDouble(txtdealInfo[0].replaceAll("¥",""));
        System.out.println("满"+df.format(txtdealInfomoney)+"元起送");

        //计算要选购多少商品才能满足起送金额,
        int num = (int)(txtdealInfomoney/txtstickyfoodListadapterfoodprice);
        int nums = num+1;

        //先判断是展示商品规格还是加号选择商品
        if (action.isElementPresented(By.id(waiMaiMerchantDetailPage.btnchooseskuid))){

            action.click(By.id(waiMaiMerchantDetailPage.btnchooseskuid));
            //获取商品价格
            double txtprice = Double.parseDouble(action.gettext(By.id(waiMaiMerchantDetailPage.txtpriceid)));
            System.out.println("商品规格弹框中商品价格："+txtprice);
            Assert.assertEquals(df.format(txtstickyfoodListadapterfoodprice),df.format(txtprice));
            //点击加入购物车
            action.click(By.id(waiMaiMerchantDetailPage.txtaddshopcartid));
            //判断还需要增加几个商品
            if (num <= 0){
                System.out.println("不再选购商品");
            }else {
                for (int i=0;i<num;i++){
                    //点击增加商品数量按钮
                    action.click(By.id(waiMaiMerchantDetailPage.imgfoodCountaddid));
                }
            }
            //关闭选择商品规格
            action.click(By.id(waiMaiMerchantDetailPage.btndialogcloseid));
            Thread.sleep(500);
            //获取选购商品的数量
            int txtskufoodcount = Integer.parseInt(action.gettext(By.id(waiMaiMerchantDetailPage.txtskufoodcountid)));

            //判断商品数量选择的是否正确
            Assert.assertEquals(txtskufoodcount,nums);

        }else if (action.isElementPresented(By.id(waiMaiMerchantDetailPage.imgfoodCountaddid))){

            for (int i=0;i<nums;i++){
                action.click(By.id(waiMaiMerchantDetailPage.imgfoodCountaddid));
            }
            Thread.sleep(500);
            //获取选购商品的数量
            List<WebElement> txtfoodCountnumbers = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtfoodCountnumberid));
            int txtfoodCountnumber = Integer.parseInt(txtfoodCountnumbers.get(0).getText());
            //判断商品数量选择的是否正确
            Assert.assertEquals(txtfoodCountnumber,nums);
        }else {
            System.out.println("选择商品失败");
        }

        action.Screenshot("测试截图034001");
        Thread.sleep(500);
        //进入提交订单页面
        action.click(By.id(waiMaiMerchantDetailPage.txtdealInfosubmitid));
        //判断进入提交订单页面
        Assert.assertTrue(action.isElementPresented(By.id(waiMaiMerchantDetailPage.txtactionbartitleid)));

        //判断商品金额、优惠金额、配送费、小计、总额等金额
        //因为有些折扣商品只能选择一个，如果选择多个会出现两条商品信息的情况，所以需要用list来获取商品数量*商品单价的金额


        //获取商品数量*商品单价的金额
        double txtproductsubtotal = 0;

        List<WebElement> txtproductsublist = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtproductsubtotalid));
        for (WebElement element : txtproductsublist){
            txtproductsubtotal = txtproductsubtotal+Double.parseDouble(element.getText().replaceAll("¥",""));
        }
        System.out.println("商品数量*商品单价的金额:"+df.format(txtproductsubtotal));

        //获取商品数量
        int txtfoodcount = 0;
        List<WebElement> txtfoodcountlist = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtfoodcountid));
        for (WebElement element : txtfoodcountlist){
            txtfoodcount = txtfoodcount+Integer.parseInt(element.getText().replaceAll("x",""));
        }
        //比对结果页面商品数量与计算出来的数量一致
        Assert.assertEquals(txtfoodcount,nums);


        //获取包装费
        double txtpackcost = Double.parseDouble(action.gettext(By.id(waiMaiMerchantDetailPage.txtpackcostid)).replaceAll("¥",""));
        System.out.println("商品包装费是："+txtpackcost);
        //获取配送费
        double txtdelivercost = Double.parseDouble(action.gettext(By.id(waiMaiMerchantDetailPage.txtdelivercostid)).replaceAll("¥",""));
        System.out.println("商品配送费是："+txtdelivercost);

        //拿商品数量*商品单价的金额判断出满减优惠，下面可能会用到
        int n = 0;
        for (int i=0;i<list.size();i=i+2) {
            System.out.println(list.get(i).toString() + ";" + list.get(i + 1).toString());

            if (txtproductsubtotal < Integer.parseInt(list.get(0).toString())) {
                n = 0;
                System.out.println("计算得出满减优惠是："+n);
                break;
            } else if (txtproductsubtotal == Integer.parseInt(list.get(i).toString())) {
                n = Integer.parseInt(list.get(i + 1).toString());
                System.out.println("计算得出满减优惠是："+n);
                break;
            } else if (txtproductsubtotal > Integer.parseInt(list.get(i).toString()) && txtproductsubtotal < Integer.parseInt(list.get(i + 2).toString())) {
                n = Integer.parseInt(list.get(i + 1).toString());
                System.out.println("计算得出满减优惠是："+n);
                break;
            }

        }

        //判断是否有门店新客立减，如果有获取立减金额
        if (action.isElementPresented(By.name(waiMaiMerchantDetailPage.newguestdicountname))){
            List<WebElement> newguestdicount = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtdiscountinfoid));
            double newguestdicountint = Double.parseDouble(newguestdicount.get(0).getText().replaceAll("-￥",""));
            System.out.println("门店新客立减金额是："+newguestdicountint);
            //判断是否有满减优惠
            if (action.isElementPresented(By.name(waiMaiMerchantDetailPage.fullreductiondiscountname))){
                System.out.println("门店新客立减和满减优惠都在");
                int fullreductiondiscountint = Integer.parseInt(newguestdicount.get(1).getText().replaceAll("-￥",""));
                System.out.println("满减优惠金额是："+fullreductiondiscountint);

                //比对满减优惠与计算出来的一致
                Assert.assertEquals(fullreductiondiscountint,n);

                //计算总金额
                double txttotalpricediscountedExpect = txtproductsubtotal + txtpackcost + txtdelivercost - newguestdicountint - fullreductiondiscountint;
                System.out.println("计算出来总金额是:"+txttotalpricediscountedExpect);

                //获取实际支付总金额
                List<WebElement> txttotalpricediscountedActual = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txttotalpricediscountedid));
                double txttotalpricediscounteddouble = Double.parseDouble(txttotalpricediscountedActual.get(0).getText().replaceAll("¥",""));
                Assert.assertEquals(df.format(txttotalpricediscountedExpect),df.format(txttotalpricediscounteddouble));

            }else {
                System.out.println("只有门店新客立减，没有满减优惠");

                //计算总金额
                double txttotalpricediscountedExpect = txtproductsubtotal + txtpackcost + txtdelivercost - newguestdicountint;
                System.out.println("计算出来总金额是:"+txttotalpricediscountedExpect);
                //获取实际支付总金额
                List<WebElement> txttotalpricediscountedActual = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txttotalpricediscountedid));
                double txttotalpricediscounteddouble = Double.parseDouble(txttotalpricediscountedActual.get(0).getText().replaceAll("¥",""));
                Assert.assertEquals(df.format(txttotalpricediscountedExpect),df.format(txttotalpricediscounteddouble));
            }
        }else {
            //判断是否有满减优惠
            if (action.isElementPresented(By.name(waiMaiMerchantDetailPage.fullreductiondiscountname))){
                System.out.println("只有满减优惠在");
                List<WebElement> newguestdicount = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txtdiscountinfoid));
                int fullreductiondiscountint = Integer.parseInt(newguestdicount.get(0).getText().replaceAll("-￥",""));
                System.out.println("满减优惠金额是："+fullreductiondiscountint);
                //比对满减优惠与计算出来的一致
                Assert.assertEquals(fullreductiondiscountint,n);

                //计算总金额
                double txttotalpricediscountedExpect = txtproductsubtotal + txtpackcost + txtdelivercost - fullreductiondiscountint;
                System.out.println("计算出来总金额是:"+txttotalpricediscountedExpect);
                //获取实际支付总金额
                List<WebElement> txttotalpricediscountedActual = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txttotalpricediscountedid));
                double txttotalpricediscounteddouble = Double.parseDouble(txttotalpricediscountedActual.get(0).getText().replaceAll("¥",""));
                Assert.assertEquals(df.format(txttotalpricediscountedExpect),df.format(txttotalpricediscounteddouble));

            }else {
                System.out.println("门店新客立减和满减优惠都不在");
                //计算总金额
                double txttotalpricediscountedExpect = txtproductsubtotal + txtpackcost + txtdelivercost;
                System.out.println("计算出来总金额是:"+txttotalpricediscountedExpect);
                //获取实际支付总金额
                List<WebElement> txttotalpricediscountedActual = meiTuanBusiness.GetElements(By.id(waiMaiMerchantDetailPage.txttotalpricediscountedid));
                double txttotalpricediscounteddouble = Double.parseDouble(txttotalpricediscountedActual.get(0).getText().replaceAll("¥",""));
                Assert.assertEquals(df.format(txttotalpricediscountedExpect),df.format(txttotalpricediscounteddouble));
            }

        }
        action.Screenshot("测试截图034002");
        Thread.sleep(500);
        //点击提交订单
//        action.click(By.id(waiMaiMerchantDetailPage.txtordersubmitid));
//        Thread.sleep(500);
//        action.Screenshot("测试截图034003");
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        driver.quit();
        Thread.sleep(500);
    }
}
