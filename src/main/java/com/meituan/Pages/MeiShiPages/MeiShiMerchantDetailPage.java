package com.meituan.Pages.MeiShiPages;

import io.appium.java_client.android.AndroidDriver;
/*
美食业务--门店详情页
 */
public class MeiShiMerchantDetailPage {

    AndroidDriver androidDriver;

    public MeiShiMerchantDetailPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    //门店名称
    public String foodpoinameid = "com.sankuai.meituan:id/food_poi_name";

    //门店人气排行榜
    public String foodpoirankid = "com.sankuai.meituan:id/food_poi_rank";

    //拨号按钮
    public String foodpoitelephoneimgid = "com.sankuai.meituan:id/food_poi_telephone_img";

    //买单按钮
    public String foodtextviewpoipayinfobuttonid = "com.sankuai.meituan:id/food_text_view_poi_pay_info_button";
    //消费总额,先点击再输入金额
    public String clickcostid = "com.sankuai.meituan:id/cost_hint";
    public String sendmoneyid = "com.sankuai.meituan:id/cost";

    //勾选不参与优惠金额
    public String nodiscountcheckboxid = "com.sankuai.meituan:id/no_discount_checkbox";
    //点击不参与优惠金额输入框
    public String nodiscounthintid = "com.sankuai.meituan:id/no_discount_hint";
    //输入不参与优惠金额
    public String nodiscountid = "com.sankuai.meituan:id/no_discount";

    //优惠金额,需要提取出来每满多少减多少元
    public String titleid = "com.sankuai.meituan:id/title";
    //优惠金额勾选按钮
    public String checkboxid = "com.sankuai.meituan:id/check_box";
    //优惠金额和实付金额
    public String moneylabelid = "com.sankuai.meituan:id/money_label";
    //确认买单按钮,要提取出金额，判断进入是不是实付金额
    public String submitid = "com.sankuai.meituan:id/submit";

    //当前时段暂不支持买单
    public String nopay = "当前时段暂不支持买单";
    //确认支付按钮
    public String payButtonid = "com.sankuai.meituan:id/payButton";

    //代金券，抢购按钮
    public String foodtextviewpoivoucheritembuyid = "com.sankuai.meituan:id/food_text_view_poi_voucher_item_buy";
    //代金券，折扣金额,需要用list获取第一个值
    public String foodtextviewpoidealitempriceid = "com.sankuai.meituan:id/food_text_view_poi_deal_item_price";

    //购买代金券页面，代金券金额
    public String dealpriceid = "com.sankuai.meituan:id/deal_price";
    //购买代金券页面，减少数量
    public String decreasegoodsnumid = "com.sankuai.meituan:id/decrease_goods_num";
    //购买代金券页面，增加数量
    public String increasegoodsnumid = "com.sankuai.meituan:id/increase_goods_num";
    ////购买代金券页面，购买数量
    public String goodsnumid = "com.sankuai.meituan:id/goods_num";
    //购买代金券页面，小计金额
    public String totalid = "com.sankuai.meituan:id/total";
    //购买代金券页面，实付金额
    public String totalpricepaymoneyid = "com.sankuai.meituan:id/total_price_pay_money";
    //购买代金券页面，提交金额
    public String submitpriceid = "com.sankuai.meituan:id/submit_price";
    //购买代金券页面，提交按钮
    public String submitordercontainerid = "com.sankuai.meituan:id/submit_order_container";


    //选择代金券页面，实付金额
    public String salepriceid = "com.sankuai.meituan:id/sale_price";
    //选择代金券页面，立即抢购按钮
    public String lijiqianggoubuttonname = "立即抢购";

    //到店吃套餐抢购套餐页面
    //抢购按钮
    public String foodpoidealbuttonbuyid = "com.sankuai.meituan:id/food_poi_deal_button_buy";
    //选择套餐抢购页面，滑动套餐控件
    public String  ActionBarsBeforeclassname = "android.support.v7.app.ActionBar$b";
    //选择套餐抢购页面，套餐名称
    public String gettextsclassname = "android.widget.TextView";

    //用户评论页面
    public String  usercommentname = "用户评论";
    //用户昵称
    public String usernameid = "com.sankuai.meituan:id/user_name";
    //用户评论内容
    public String  commentdescriptionid = "com.sankuai.meituan:id/comment_description";
    //进入评价列表页面
    //用户昵称
    public String feedusernameid = "com.sankuai.meituan:id/feed_user_name";
    //用户评论内容
    public String feedcontentid = "com.sankuai.meituan:id/feed_content";
    //进入评价详情页面，用户昵称和用户评论内容和评价列表中控件的一致


}
