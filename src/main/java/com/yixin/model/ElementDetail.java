package com.yixin.model;


public class ElementDetail {

    private int id;
    private String page_name;//页面名称
    private String element_name;//元素名称
    private Integer indexs;//index值
    private String by_name;
    private String by_resourceid;
    private String by_classname;
    private String by_xpath;
    private String contextdesc;
    private Integer x_start_point;
    private Integer x_end_point;
    private Integer y_start_point;
    private Integer y_end_point;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public String getElement_name() {
        return element_name;
    }

    public void setElement_name(String element_name) {
        this.element_name = element_name;
    }

    public Integer getIndexs() {
        return indexs;
    }

    public void setIndexs(Integer indexs) {
        this.indexs = indexs;
    }

    public String getBy_name() {
        return by_name;
    }

    public void setBy_name(String by_name) {
        this.by_name = by_name;
    }

    public String getBy_resourceid() {
        return by_resourceid;
    }

    public void setBy_resourceid(String by_resourceid) {
        this.by_resourceid = by_resourceid;
    }

    public String getBy_classname() {
        return by_classname;
    }

    public void setBy_classname(String by_classname) {
        this.by_classname = by_classname;
    }

    public String getBy_xpath() {
        return by_xpath;
    }

    public void setBy_xpath(String by_xpath) {
        this.by_xpath = by_xpath;
    }

    public String getContextdesc() {
        return contextdesc;
    }

    public void setContextdesc(String contextdesc) {
        this.contextdesc = contextdesc;
    }

    public Integer getX_start_point() {
        return x_start_point;
    }

    public void setX_start_point(Integer x_start_point) {
        this.x_start_point = x_start_point;
    }

    public Integer getX_end_point() {
        return x_end_point;
    }

    public void setX_end_point(Integer x_end_point) {
        this.x_end_point = x_end_point;
    }

    public Integer getY_start_point() {
        return y_start_point;
    }

    public void setY_start_point(Integer y_start_point) {
        this.y_start_point = y_start_point;
    }

    public Integer getY_end_point() {
        return y_end_point;
    }

    public void setY_end_point(Integer y_end_point) {
        this.y_end_point = y_end_point;
    }

    @Override
    public String toString() {
        return "ElementDetail{" +
                "id=" + id +
                ", page_name='" + page_name + '\'' +
                ", element_name='" + element_name + '\'' +
                ", indexs=" + indexs +
                ", by_name='" + by_name + '\'' +
                ", by_resourceid='" + by_resourceid + '\'' +
                ", by_classname='" + by_classname + '\'' +
                ", by_xpath='" + by_xpath + '\'' +
                ", contextdesc='" + contextdesc + '\'' +
                ", x_start_point=" + x_start_point +
                ", x_end_point=" + x_end_point +
                ", y_start_point=" + y_start_point +
                ", y_end_point=" + y_end_point +
                '}';
    }
}

