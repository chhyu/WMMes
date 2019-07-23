package com.weimi.wmmess.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static LocalDateTime formatLocalDataTime(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(time, df);
        return ldt;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String formatLocalDataTime(LocalDateTime time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String localTime = df.format(time);
        return localTime;
    }

}
