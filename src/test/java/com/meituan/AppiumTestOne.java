package com.meituan;

import com.meituan.Util.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AppiumTestOne extends TestSuite {

    //@Test(description = "tap练习")
    public void testtap() throws InterruptedException {
//        action.tap(By.xpath(homePage.getElement(homePage.serviceballxpath,homePage.getserviceballs().get(0))));
        action.tap(By.name(elementSource.getElementsource("serviceball")));
        Thread.sleep(1000);
        List<WebElement> list = driver.findElements(By.className("com.sankuai.meituan:id/info_container"));
        list.get(0).click();

//        action.tap(By.name(elementSource.getElementsource("startelement")));

        //断言，判断进入页面后元素存在
        Assert.assertEquals(action.isElementPresented(By.id(groupDetailPage.merchantname)),true);
    }

    //@Test(description = "练习控件的滑动")
    public void testcategorySwipe() throws InterruptedException {
        action.bannerSwipe(By.id(homePage.bannerid),"左滑",2);
        Thread.sleep(1000);
    }

    //@Test(description = "登录")
    public void testlogin() throws InterruptedException {
        Thread.sleep(100);
        //xpath定位不准确，可能会点击到旁边的元素
//        action.click(By.xpath(homePage.getElement(homePage.serviceballxpath,homePage.getserviceballs().get(5))));
        //使用name定位准确一些
//        action.click(By.name(homePage.getserviceballs().get(5)));

        //先判断是否登录，如果登录先退出登录，然后再登录

        if (!(action.isElementPresented(By.name(mainPage.clicklogin)))){
            //点击右上角设置按钮，退出登录，重新登录
            action.click(By.id(mainPage.settingbutton));
            action.click(By.id(settingPage.logoutbutton));
            action.click(By.id(settingPage.logoutdialogbox1));

        }

        //重新登录
        Thread.sleep(100);
        action.click(By.name(mainPage.clicklogin));
        Thread.sleep(100);

        try{
            //使用密码登录有时可能会不出现
            action.click(By.name(loginPage.passwordlogin));
        }catch (Exception e){
            action.sendkeys(By.id(loginPage.usernameresourceid),"18600228767");
            action.sendkeys(By.name(loginPage.password),"12345678");
            Thread.sleep(500);
            action.click(By.name(loginPage.loginbutton));
            Thread.sleep(500);
        }
        //登录成功校验某个元素的存在，目前一般情况下都让输入验证码或者图片验证码等信息。

    }

    //@Test(description = "从yaml文件中读取数据")
    public void testgetelementfromyaml(){
        System.out.println(elementSource.getElementsource("serviceball"));
    }

//    @Test(description = "模拟安卓的返回键")
    public void testback() throws InterruptedException {
        action.click(By.name(elementSource.getElementsource("serviceball")));
        action.back();
        Thread.sleep(1000);
        action.click(By.name(elementSource.getElementsource("serviceballtwo")));
        action.back();
    }

//    @Test(description = "用例1")
//    public void test0011(){
//        action.Swipe(1,1);
//        action.click(By.name(homePage.ordername));
//
//    }
//    @Test(description = "用例1")
//    public void test0012(){
//        action.click(By.name(homePage.ordername));
//
//    }
//    @Test(description = "用例1")
//    public void test0013(){
//        action.click(By.name(homePage.ordername));
//
//    }
    @Test(description = "用例1")
    public void test0014(){
        action.click(By.name(homePage.ordername));

    }



    @AfterMethod
    public void teardown() throws InterruptedException {
        driver.quit();
        Thread.sleep(1000);
    }


}
