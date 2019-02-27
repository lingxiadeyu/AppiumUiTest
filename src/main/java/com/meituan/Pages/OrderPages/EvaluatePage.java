package com.meituan.Pages.OrderPages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
评价页面
 */
public class EvaluatePage {
    AndroidDriver androidDriver;

    public EvaluatePage(AndroidDriver androidDriver){
        this.androidDriver = androidDriver;
    }

    /*
    以下是水果店的评价页面
     */
    //不满意按钮
    public String unsatisfiedtxttabid = "com.sankuai.meituan:id/unsatisfied_txt_tab";
    //满意按钮
    public String satisfiedtxttabid = "com.sankuai.meituan:id/satisfied_txt_tab";
    //五星按钮
    public String rating_commentid = "com.sankuai.meituan:id/rating_comment";
    //质量、包装按钮
    public List<WebElement> getratingid(){
        List<WebElement> getratingid = androidDriver.findElements(By.id("com.sankuai.meituan:id/rating"));
        return getratingid;
    }
    //评价内容框
    public String editcommentid = "com.sankuai.meituan:id/edit_comment";
    //提交按钮
    public String btnsubmitcommentid = "com.sankuai.meituan:id/btn_submit_comment";
    //评价成功,分享评价
    public String  tvsharecommenttitleid = "com.sankuai.meituan:id/tv_share_comment_title";
    //评价成功，关闭按钮
    public String imgsharecommentcloseid = "com.sankuai.meituan:id/img_share_comment_close";

    /*
    以下是饭店的评价页面
     */
    //发布按钮
    public String ugcaddreviewid = "com.sankuai.meituan:id/ugc_add_review";
    //强烈推荐
    public String  ugcscorelabelperfectid = "com.sankuai.meituan:id/ugc_score_label_perfect";
    //评价内容框
    public String reviewcontentid = "com.sankuai.meituan:id/review_content";
    //评价完成
    public String successthankstipid = "com.sankuai.meituan:id/ugc_addreview_success_thanks_tip";
    //评价完成后点击完成
    public String reviewsuccessid = "com.sankuai.meituan:id/ugc_add_review_success";

}
