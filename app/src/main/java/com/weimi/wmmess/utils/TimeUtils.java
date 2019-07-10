package com.weimi.wmmess.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by chhyu
 * on 2019/7/3
 * Describle:
 */
public class TimeUtils {


    public static String getFormatTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(date);
        return time;
    }
}
