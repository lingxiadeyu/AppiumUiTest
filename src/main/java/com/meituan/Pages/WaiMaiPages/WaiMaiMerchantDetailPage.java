package com.meituan.Pages.WaiMaiPages;

import io.appium.java_client.android.AndroidDriver;

/*
外卖商户详情页
 */
public class WaiMaiMerchantDetailPage {
    AndroidDriver androidDriver;

    public WaiMaiMerchantDetailPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }
    //进入外卖页面，先点击搜索，根据搜索定位某个门店
    //搜索框
    public String actionsearchid = "com.sankuai.meituan:id/action_search";
    //输入搜索内容
    public String txtsearchkeywordid = "com.sankuai.meituan:id/txt_search_keyword";
    //点击搜索按钮
    public String searchtvid = "com.sankuai.meituan:id/search_tv";
    //点击门店名称
    public String poilistpoinameid = "com.sankuai.meituan:id/textview_poi_name";



    //获取商户优惠,按钮
    public String showheaderbuttonid = "com.sankuai.meituan:id/show_header_button";
    //获取商户优惠，list，需要提取出优惠金额,只需要提取第一个优惠金额就可以
    public String txtpoiactivityid = "com.sankuai.meituan:id/txt_poi_activity";
    //商品价格,list,取第一个商品金额
    public String txtstickyfoodListadapterfoodpriceid = "com.sankuai.meituan:id/txt_stickyfoodList_adapter_food_price";
    //选择商品规格
    public String btnchooseskuid = "com.sankuai.meituan:id/btn_choose_sku";
    //添加商品数量
    public String imgfoodCountaddid = "com.sankuai.meituan:id/img_foodCount_add";
    //加入购物车
    public String txtaddshopcartid = "com.sankuai.meituan:id/txt_add_shopcart";
    //弹框中的价格
    public String txtpriceid = "com.sankuai.meituan:id/txt_price";
    //关闭对话框
    public String btndialogcloseid = "com.sankuai.meituan:id/btn_dialog_close";
    //选购商品数量,list
    public String txtfoodCountnumberid = "com.sankuai.meituan:id/txt_foodCount_number";
    //选规格上面的选购商品数量
    public String txtskufoodcountid = "com.sankuai.meituan:id/txt_skufood_count";



    //点击购物车,同时也是金额
    public String txtdealInfopriceid = "com.sankuai.meituan:id/txt_dealInfo_price";
    //配送费,需要提取出最后一位配送费金额
    public String txtshippingfeeid = "com.sankuai.meituan:id/txt_shipping_fee";
    //购物车中金额
    public String txtfoodpriceid = "com.sankuai.meituan:id/txt_food_price";
    //购物车中数量,与上面的商品数量一致，可能会有问题
    //包装费
    public String txtboxfeeid = "com.sankuai.meituan:id/txt_box_fee";
    //清空购物车
    public String btnclearid = "com.sankuai.meituan:id/btn_clear";

    //选择完商品后点击去结算
    public String txtdealInfosubmitid = "com.sankuai.meituan:id/txt_dealInfo_submit";

    //提交订单页面
    //提交订单页面title
    public String txtactionbartitleid = "com.sankuai.meituan:id/txt_actionbar_title";
    //每个商品的单价*数量价格
    public String txtproductsubtotalid = "com.sankuai.meituan:id/txt_product_sub_total";
    //商品数量
    public String txtfoodcountid = "com.sankuai.meituan:id/txt_food_count";
    //包装费
    public String txtpackcostid = "com.sankuai.meituan:id/txt_pack_cost";
    //配送费
    public String txtdelivercostid = "com.sankuai.meituan:id/txt_deliver_cost";
    //门店新客立减
    public String newguestdicountname = "门店新客立减";
    //满减优惠
    public String  fullreductiondiscountname = "满减优惠";
    //门店新客立减金额，满减优惠金额,需要用list获取
    public String txtdiscountinfoid = "com.sankuai.meituan:id/txt_discount_info";


    //小计金额和支付金额id一致，用list获取
    public String txttotalpricediscountedid = "com.sankuai.meituan:id/txt_total_price_discounted";
//    //支付金额
//    public String txttotalpricediscountedid = "com.sankuai.meituan:id/txt_total_price_discounted";
    //提交订单
    public String txtordersubmitid = "com.sankuai.meituan:id/txt_order_submit";




}
