package com.weimi.wmmess.base;

/**
 * Created by BuFA on 2018/8/2.
 * fragmentStore顶部notice
 */

public class BeanNoticeView {

    private int noticeId;
    private String noticeTitle;
    private String dateTime;


    public BeanNoticeView(int id, String title, String date) {
        this.noticeId = id;
        this.noticeTitle = title;
        this.dateTime = date;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }
}
