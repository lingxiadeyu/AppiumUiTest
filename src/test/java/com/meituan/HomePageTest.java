package com.meituan;
import com.lowagie.text.DocumentException;
import com.meituan.Business.MeiTuanBusiness;
import com.meituan.Util.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePageTest extends TestSuite {



    /*
    测试编号:001
    测试场景：切换城市--选择热门城市
    预期结果：判断切换后的城市是选择的城市
     */
    @Test(description = "切换城市--选择热门城市")
    public void test001() throws InterruptedException, IOException {
        //第一个执行的用例需要先做上滑操作，这样才能解锁手机，当然第一个用例需要是不依赖上滑操作的用例，如果依赖，上滑后可能找不到元素。
        action.Swipe(1,1);

        //先点击切换城市
        action.click(By.id(homePage.citynameid));

        //获取热门城市列表，因为热门城市有变化，所以获取最后一个热门城市的name，并点击
        List<WebElement> hotcity = citynamePage.gethotcity();
        Thread.sleep(1000);
        String cityname = hotcity.get(hotcity.size() - 1).getText();
        System.out.println(cityname);
        Thread.sleep(1000);
        hotcity.get(hotcity.size() - 1).click();
        //测试截图
        action.Screenshot("测试编号001");
        //判断选择的城市是点击的cityname
//        Assert.assertEquals(driver.findElement(By.id(homePage.citynameid)).getText(),cityname);
        Assert.assertEquals(action.gettext(By.id(homePage.citynameid)), cityname);

    }

    /*
测试编号:002
测试场景：切换城市--切换县区
预期结果：判断切换后的县区是选择的县区
 */
    @Test(description = "切换城市--切换县区")
    public void test002() throws InterruptedException, IOException {
        //先点击切换城市
        action.click(By.id(homePage.citynameid));
        //点击切换县区
        action.click(By.id(citynamePage.cityselectareaid));
        //强制等待2秒，等待刷新出县区
        Thread.sleep(1000);
        //获取县区列表，因为县区有变化并且与热门城市的resourceid一样，所以获取第二个县区，并点击
        List<WebElement> cityselectarea = citynamePage.cityselectarea();
        Thread.sleep(1000);
        String areaname = cityselectarea.get(1).getText();
        System.out.println(areaname);
        Thread.sleep(1000);
        cityselectarea.get(1).click();
        //测试截图
        action.Screenshot("测试编号002");
        //判断选择的城市是点击的cityname
//        Assert.assertEquals(driver.findElement(By.id(homePage.citynameid)).getText(),cityname);
        Assert.assertEquals(action.gettext(By.id(homePage.citynameid)), areaname);

    }

    /*
    测试编号:003
    测试场景：切换城市--选择指定城市,先滑动页面1次，点击右侧定位热门城市，选择最后的Z，到了Z开头的页面，选择第一个城市
    预期结果：判断切换后的城市是选择的城市
     */
    @Test(description = "切换城市--选择指定城市")
    public void test003() throws InterruptedException {
        //先点击切换城市
        action.click(By.id(homePage.citynameid));
        Thread.sleep(1000);
        //先滑动页面，滑动一次
        action.Swipe(1, 1);
        Thread.sleep(1000);
        //点击右侧定位热门城市坐标,选择Z字母
        action.tap(1, 1075, 1770, 500);
        //到了Z开头的页面，选择第一个城市
        List<WebElement> citylisttextview = citynamePage.citylisttextview();
        String citylisttext = citylisttextview.get(1).getText();
        System.out.println(citylisttext);
        Thread.sleep(1000);
        //点击城市
        citylisttextview.get(1).click();
        Thread.sleep(1000);

        //判断选择的城市信息正确
        Assert.assertEquals(action.gettext(By.id(homePage.citynameid)), citylisttext);
    }

    /*
    测试编号:004
    测试场景：切换城市--搜索指定城市
    预期结果：点击搜索出的城市判断城市正确
     */
    @Test(description = "切换城市--搜索指定城市")
    public void test004() throws InterruptedException, IOException {
        //先点击切换城市
        action.click(By.id(homePage.citynameid));
        //搜索
        action.sendkeys(By.className(citynamePage.EditTextclass), "北京");
        Thread.sleep(1000);
        action.Screenshot("测试编号004");
        String cityname = action.gettext(By.id(citynamePage.searchcityid));
        //点击搜索出的城市
        action.click(By.id(citynamePage.searchcityid));
        Thread.sleep(1000);
        //判断选择的城市信息正确
        Assert.assertEquals(action.gettext(By.id(homePage.citynameid)), cityname);

    }

    /*
    测试编号:005
    测试场景：切换城市--返回首页
    预期结果：无
     */
    @Test(description = "切换城市--返回首页")
    public void test005() throws IOException, InterruptedException {
        //先点击切换城市
        action.click(By.id(homePage.citynameid));
        action.Screenshot("测试编号005");
        Thread.sleep(1000);
        action.back();

    }

    /*
    测试编号:006
    测试场景：切换城市--选择国际/港澳台城市
    预期结果：无
     */
    @Test(description = "切换城市--选择国际/港澳台城市")
    public void test006() throws IOException, InterruptedException {
        //先点击切换城市
        action.click(By.id(homePage.citynameid));
        //选择国际/港澳台
        action.click(By.name(citynamePage.indicatortextname));
        //选择港澳台
        action.click(By.name(citynamePage.tabtextname));
        Thread.sleep(1000);
        //测试截图
        action.Screenshot("测试截图00601");
        //选择香港
        List<WebElement> getGangAoTaiCityname = citynamePage.getGangAoTaiCityname();
        String cityname = getGangAoTaiCityname.get(0).getText();
        getGangAoTaiCityname.get(0).click();
        String homepagecityname = action.gettext(By.id(homePage.citynameid));

        //判断选的城市是香港
        //判断两个字符串是否有包含字段，用indexof，结果不等于-1就代表包含
        if (cityname.indexOf(homepagecityname) != -1) {
            System.out.println("比对成功");
        }
        //测试截图
        action.Screenshot("测试截图00602");
        Thread.sleep(1000);
        //返回到港澳台页面选择当前定位城市
        action.click(By.id(homePage.citynameid));
        Thread.sleep(500);
        //获取当前定位的城市
        String locatecontentcityname = action.gettext(By.id(citynamePage.locatecontentid));
        action.click(By.id(citynamePage.locatecontentid));
        //判断选择的城市信息正确
        Assert.assertEquals(action.gettext(By.id(homePage.citynameid)), locatecontentcityname);
        //测试截图
        action.Screenshot("测试截图00603");
    }

    /*
    测试编号:007
    测试场景：输入门店名称搜索
    预期结果：判断第一条门店名称包含搜索内容
     */
    @Test(description = "输入搜索内容搜索")
    public void test007() throws IOException, InterruptedException {
        //点击首页搜索框
        action.click(By.id(homePage.searchlayoutid));

        //进入搜索页面，输入搜索内容
        String searchcontent = "先花店";
        action.sendkeys(By.id(searchMerchantPage.searcheditid), searchcontent);
        action.Screenshot("测试截图00701");
        //点击搜索按钮
        action.click(By.id(searchMerchantPage.searchbuttonid));

        Thread.sleep(1000);
        //比对搜索结果
        List<WebElement> getsearchmerchantname = searchMerchantPage.getsearchmerchantname();
        String merchantname = getsearchmerchantname.get(0).getText();
        if (merchantname.indexOf(searchcontent) != -1) {

            System.out.println("比对成功");
        }
        Thread.sleep(1000);
        action.Screenshot("测试截图00702");

    }

    /*
    测试编号:008
    测试场景：根据城市、省份搜索
    预期结果：判断城市地点正确
    */
    @Test(description = "根据城市、省份搜索")
    public void test008() throws IOException, InterruptedException {
        //点击首页搜索框
        action.click(By.id(homePage.searchlayoutid));
        //进入搜索页面，输入搜索内容
        String searchcontent = "上海";
        action.sendkeys(By.id(searchMerchantPage.searcheditid), searchcontent);
        action.Screenshot("测试截图00801");
        //点击搜索按钮
        action.click(By.id(searchMerchantPage.searchbuttonid));

        Thread.sleep(1000);

        //如果这个元素存在就代表搜索成功，不用比对结果
        if (action.isElementPresented(By.xpath(searchMerchantPage.getElement(searchMerchantPage.searchareaxpath, "上海")))) {
            System.out.println("搜索成功");
            action.click(By.xpath(searchMerchantPage.getElement(searchMerchantPage.searchareaxpath, "上海")));
        }
        Thread.sleep(1000);
        action.Screenshot("测试截图00802");
    }

    /*
    测试编号:009
    测试场景：根据地址搜索
    预期结果：判断搜索出的地址正确
     */
    @Test(description = "根据地址搜索")
    public void test009() throws IOException, InterruptedException {
        //与根据门店名称查询的比对方式是一样的 只是用模糊比对方法

        //点击首页搜索框
        action.click(By.id(homePage.searchlayoutid));
        //进入搜索页面，输入搜索内容
        String searchcontent = "东四十条";
        action.sendkeys(By.id(searchMerchantPage.searcheditid), searchcontent);
        action.Screenshot("测试截图00901");
        //点击搜索按钮
        action.click(By.id(searchMerchantPage.searchbuttonid));

        Thread.sleep(1000);
        //比对搜索结果
        List<WebElement> getsearchmerchantname = searchMerchantPage.getsearchmerchantname();
        String merchantname = getsearchmerchantname.get(0).getText();


        //使用正则表达式比对结果
        String a = "^*东四十条*";
        //编译正则表达式
        Pattern pattern = Pattern.compile(a);
        //匹配目标值
        Matcher matcher = pattern.matcher(merchantname);
        // 字符串是否与正则表达式相匹配
        boolean res = matcher.find();
        if (res) {
            System.out.println("比对成功");
        }

        Thread.sleep(1000);
        action.Screenshot("测试截图00902");

    }

    /*
    测试编号:010
    测试场景：选择热门搜索（历史记录）内容搜索
    预期结果：判断第一条内容包含搜索内容
     */
    @Test(description = "选择热门搜索（历史记录）内容搜索")
    public void test010() throws IOException, InterruptedException {
        //点击首页搜索框
        action.click(By.id(homePage.searchlayoutid));

        Thread.sleep(1000);
        //进入搜索页面，点击第一个就是热门搜索，点击最后一个就是历史记录
        List<WebElement> getsearchtagname = searchMerchantPage.getsearchtagname();
        String searchcontent = getsearchtagname.get(getsearchtagname.size() - 1).getText();
        getsearchtagname.get(getsearchtagname.size() - 1).click();
        System.out.println(searchcontent);
        action.Screenshot("测试截图01001");

        Thread.sleep(1000);

        //如果这个元素存在就代表搜索成功，不用比对结果
        if (action.isElementPresented(By.xpath(searchMerchantPage.getElement(searchMerchantPage.searchareaxpath, searchcontent)))) {
            System.out.println("搜索成功");
            action.click(By.xpath(searchMerchantPage.getElement(searchMerchantPage.searchareaxpath, searchcontent)));
        }
        Thread.sleep(1000);
        action.Screenshot("测试截图01002");

    }

    /*
    测试编号:011
    测试场景：banner滑动后点击
    预期结果：滑动3次后截图并点击成功
     */
    @Test(description = "banner滑动后点击")
    public void test011() throws InterruptedException, IOException {
        action.bannerSwipe(By.id(homePage.bannerid), "左滑", 3);
        action.click(By.id(homePage.bannerid));
        Thread.sleep(1000);
        action.Screenshot("测试截图011");

    }

    /*
    测试编号:012
    测试场景：点击每一个服务球并返回
    预期结果：点击后截图查看
     */
    @Test(description = "点击每一个服务球并返回")
    public void test012() throws IOException, InterruptedException {

        List<WebElement> serviceballs = homePage.getserviceballs();
        //只点击15个服务球，不点击下面的导航球
        for (int i = 0; i < 15; i++) {
            //获取坐标y值，判断y值在一定范围内才让点击，保证点击的是正确的。
            int y = serviceballs.get(i).getLocation().getY();
            System.out.println(y);
            if (y >= 532 && y <= 1260) {
                serviceballs.get(i).click();
                Thread.sleep(2000);
                action.Screenshot("测试截图012" + i);
                action.back();
                Thread.sleep(500);
            }

        }
    }

    /*
    测试编号:013
    测试场景：滑动页面找到指定的门店
    预期结果：断言门店是否正确
     */
    @Test(description = "滑动页面找到指定的门店")
    public void test013() {
        action.Swipe(1, 1);
        List<WebElement> getmerchanttitle = homePage.getmerchanttitle();
        getmerchanttitle.get(0).click();
        //查看门店详情页的元素是否存在
    }



    /*
    测试编号:014
    测试场景：查看待付款订单
    预期结果：判断暂无订单，点击随便逛逛，回到首页
     */
    @Test(description = "查看待付款订单，判断暂无订单，点击随便逛逛，回到首页")
    public void test014() throws IOException, InterruptedException {
        meiTuanBusiness.enterorderpage();
        action.click(By.id(orderPage.unpaidorder));

        if (action.isElementPresented(By.id(orderPage.noorderid))) {
            System.out.println("没有待付款的订单");
            action.Screenshot("测试截图014");
            action.click(By.id(orderPage.noOrderbuttonid));
            if (action.isElementPresented(By.name(homePage.homepage))) {
                System.out.println("进入首页");
            }
        } else {
            System.out.println("有待付款的订单");
            //有待付款订单的情况暂未测试
        }

    }

    /*
    测试编号:015
    测试场景：查看待使用订单
    预期结果：判断暂无订单，点击随便逛逛，回到首页
    */
    @Test(description = "查看待使用订单，判断暂无订单，点击随便逛逛，回到首页")
    public void test015() throws IOException, InterruptedException {
        meiTuanBusiness.enterorderpage();
        action.click(By.id(orderPage.unusedid));

        if (action.isElementPresented(By.id(orderPage.noorderid))) {
            System.out.println("没有待使用的订单");
            action.Screenshot("测试截图015");
            action.click(By.id(orderPage.noOrderbuttonid));
            if (action.isElementPresented(By.name(homePage.homepage))) {
                System.out.println("进入首页");
            }
        } else {
            System.out.println("有待使用的订单");
            //有待付款订单的情况暂未测试
        }

    }

    /*
    测试编号:016
    测试场景：查看待评价订单
    预期结果：判断有订单,参与评价
     */
    @Test(description = "查看待评价订单,判断有订单去参与评价")
    public void test016() throws IOException, InterruptedException {
        meiTuanBusiness.enterorderpage();
        action.click(By.id(orderPage.feedbackid));

        if (action.isElementPresented(By.id(orderPage.noorderid))) {
            System.out.println("没有待评价的订单");
            action.Screenshot("测试截图01601");
            action.click(By.id(orderPage.noOrderbuttonid));
            if (action.isElementPresented(By.name(homePage.homepage))) {
                System.out.println("进入首页");
            }
        } else if (action.isElementPresented(By.id(orderPage.evaluateid))) {
            System.out.println("有待评价的订单");

            //点击评价按钮，进入评价页面
            action.click(By.id(orderPage.evaluateid));
            Thread.sleep(1000);
            action.Screenshot("测试截图01602");

            //如果有满意按钮，走以下评价页面
            if (action.isElementPresented(By.id(evaluatePage.satisfiedtxttabid))) {
                //点击满意按钮
                action.click(By.id(evaluatePage.satisfiedtxttabid));
                //选择五星
                action.click(By.id(evaluatePage.rating_commentid));
                //选择质量和包装星级
                List<WebElement> getratingid = evaluatePage.getratingid();
                getratingid.get(0).click();
                getratingid.get(1).click();
                //滑动屏幕
                action.Swipe(1, 1);
                //输入评价内容
                action.sendkeys(By.id(evaluatePage.editcommentid), "送的很快，态度很好");

                //提交
                action.click(By.id(evaluatePage.btnsubmitcommentid));

                //提交成功后的判断
                if (action.isElementPresented(By.id(evaluatePage.tvsharecommenttitleid))) {
                    System.out.println("分享成功");
                    action.click(By.id(evaluatePage.imgsharecommentcloseid));
                } else {
                    System.out.println("分享不成功");
                }
            } else if (action.isElementPresented(By.id(evaluatePage.ugcaddreviewid))) {//如果有发布按钮，走以下评价页面
                action.click(By.id(evaluatePage.ugcscorelabelperfectid));
                action.sendkeys(By.id(evaluatePage.reviewcontentid), "很不错的一次体验啊，感觉很好");
                action.click(By.id(evaluatePage.ugcaddreviewid));
                //提交成功后的判断
                if (action.isElementPresented(By.id(evaluatePage.successthankstipid))) {
                    System.out.println("分享成功");
                    action.click(By.id(evaluatePage.reviewsuccessid));
                } else {
                    System.out.println("分享不成功");
                }
            } else {
                System.out.println("进入评价页面失败");
            }
        }
    }

    /*
    测试编号:017
    测试场景：查看退款、售后订单
    预期结果：判断有订单,参与评价
     */
    @Test(description = "查看退款、售后订单")
    public void test017() throws IOException, InterruptedException {
        meiTuanBusiness.enterorderpage();
        action.click(By.id(orderPage.refundid));
        if (action.isElementPresented(By.id(orderPage.noorderid))) {
            System.out.println("没有退款的订单");
            action.Screenshot("测试截图01701");
            action.click(By.id(orderPage.noOrderbuttonid));
            if (action.isElementPresented(By.name(homePage.homepage))) {
                System.out.println("进入首页");
            }
        }else if (action.isElementPresented(By.id(refundPage.refundstatusid))){
            System.out.println("有退款的订单");
            //先判断订单状态为退款成功，可能会有其他的状态，目前没有数据测试不准确
            Assert.assertEquals(action.gettext(By.id(refundPage.refundstatusid)),"退款成功");

        }else {
            System.out.println("进入退款/售后页面失败");
        }


    }
    /*
    测试编号:018
    测试场景：点击退款进度进入退款进度页面
    预期结果：判断有退款成功和退款已入账等字段信息
     */
    @Test(description = "点击退款进度进入退款进度页面")
    public void test018() throws IOException, InterruptedException {
        meiTuanBusiness.enterorderpage();
        action.click(By.id(orderPage.refundid));
        if (action.isElementPresented(By.id(orderPage.noorderid))) {
            System.out.println("没有退款的订单");
            action.Screenshot("测试截图01801");
            action.click(By.id(orderPage.noOrderbuttonid));
            if (action.isElementPresented(By.name(homePage.homepage))) {
                System.out.println("进入首页");
            }
        }else if (action.isElementPresented(By.id(refundPage.refundstatusid))){
            System.out.println("有退款的订单");
            action.Screenshot("测试截图01802");
            //先判断订单状态为退款成功，可能会有其他的状态，目前没有数据测试不准确
            Assert.assertEquals(action.gettext(By.id(refundPage.refundstatusid)),"退款成功");
            action.click(By.name(refundPage.refundname));
            if (action.isElementPresented(By.name(refundPage.refundsuccess)) &&
            action.isElementPresented(By.name(refundPage.refunddone))){
                System.out.println("退款成功");
                action.Screenshot("测试截图01802");
            }

        }else {
            System.out.println("进入退款/售后页面失败");
        }
    }

    /*
    测试编号:019
    测试场景：查看全部订单
    预期结果：判断订单状态与订单详情中的状态一致
     */
    @Test(description = "查看全部订单")
    public void test019() throws IOException, InterruptedException {
        meiTuanBusiness.enterorderpage();
        action.click(By.id(orderPage.allorderid));
        List<WebElement> getorderstatus = orderPage.getorderstatus();


        if (action.isElementPresented(By.id(orderPage.noorderid))) {
            System.out.println("没有订单");
            action.Screenshot("测试截图01901");
            action.click(By.id(orderPage.noOrderbuttonid));
            if (action.isElementPresented(By.name(homePage.homepage))) {
                System.out.println("进入首页");
            }
        }else if (getorderstatus.get(0).isDisplayed()){
            System.out.println("有订单");
            action.Screenshot("测试截图01902");
            //获取订单状态，进入订单详情中，判断订单状态与订单详情中的订单状态一致
            String orderstatus = getorderstatus.get(0).getText();
            getorderstatus.get(0).click();
            String orderdetailstatus = action.gettext(By.id(orderPage.orderdetailtitleid));
            if (orderdetailstatus.indexOf(orderstatus) != -1){
                System.out.println("订单状态正确");
            }else {
                System.out.println("订单状态不正确");
            }

        }else {
            System.out.println("进入订单页面失败");
        }
    }

    /*
    测试编号:020
    测试场景：跳转到微信去分享
    预期结果：判断进入到微信页面
     */
    @Test(description = "调起微信分享门店信息")
    public void test020() throws InterruptedException {
        //选择门店
        Thread.sleep(1000);
        action.Swipe(1,1);
        List<WebElement> getElements = meiTuanBusiness.GetElements(By.id(homePage.getmerchanttitleid));
        getElements.get(0).click();

        String merchantsname = getElements.get(0).getText();

        //进入门店详情点击分享商家
        action.click(By.id(groupDetailPage.threepointid));
        action.click(By.name(groupDetailPage.sharemerchantname));
        List<WebElement> getshareelements = meiTuanBusiness.GetElements(By.id(groupDetailPage.shareimageid));
        getshareelements.get(0).click();

        //进入微信选择朋友分享内容
        action.click(By.name(weixinPage.friendname));
        action.sendkeys(By.id(weixinPage.aziid),"hello");
        action.click(By.id(weixinPage.az_id));
        action.click(By.name(weixinPage.stayweixinname));

        //断言分享成功
        action.click(By.name(weixinPage.friendname));
        String sharemerchantsname = action.gettext(By.id(weixinPage.aoyid));
        if (merchantsname.equals(sharemerchantsname)){
            System.out.println("分享成功");
        }
    }



    @AfterMethod
    public void teardown() throws InterruptedException {
        driver.quit();
        Thread.sleep(1000);
    }

}
