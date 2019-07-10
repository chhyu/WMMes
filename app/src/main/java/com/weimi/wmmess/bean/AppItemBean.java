package com.weimi.wmmess.bean;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;


/**
 * Created by chhyu on 2019/7/1.
 */
public class AppItemBean {
    @DrawableRes
    private int imageId;
    @ColorRes
    private int bgColor;
    private String text;
    private String target;
    private String appUrl;
    private Class aClass;


    public String getAppUrl() {
        return appUrl;
    }

    public AppItemBean setAppUrl(String appUrl) {
        this.appUrl = appUrl;
        return this;
    }

    public AppItemBean(int imageId, int bgColor, String text, Class aClass) {
        this.imageId = imageId;
        this.bgColor = bgColor;
        this.text = text;
        this.target = "";
        this.aClass = aClass;
    }

    public AppItemBean(int imageId, int bgColor, String text, String target) {
        this.imageId = imageId;
        this.bgColor = bgColor;
        this.text = text;
        this.target = target;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
