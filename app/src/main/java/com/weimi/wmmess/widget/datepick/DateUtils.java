package com.weimi.wmmess.widget.datepick;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by codbking on 2016/12/15.
 */

public class DateUtils {

    //获取小时
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.HOUR_OF_DAY);
    }

    //获取小时
    public static int getHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(calendar.HOUR_OF_DAY);
    }

    //获取分钟
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.MINUTE);
    }

    //获取分钟
    public static int getMinute() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(calendar.MINUTE);
    }

    //获取周
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //获取周
    public static int getWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //获取周
    public static int getWeek(int year, int moth, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, moth - 1, day - 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //获取年
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.YEAR);
    }

    //获取年
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(calendar.YEAR);
    }

    //获取月
    public static int getMoth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.MONTH) + 1;
    }

    //获取月
    public static int getMoth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(calendar.MONTH) + 1;
    }

    //获取日
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendar.DATE);
    }

    //获取日
    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(calendar.DATE);
    }

    public static Date getDate(int year, int moth, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, moth - 1, day, hour, minute);
        return calendar.getTime();
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        try {
            Date date = format.parse("2016-12-15 12");
            System.out.println(getHour(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数字转成中文
     *
     * @param day
     */
    public static String getWeekDay(Context context, int day) {
        day = day - 1;
        String weekDay = "";
        if (day > 7 || day < 0) {
            Toast.makeText(context, "数字不合法", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (day == 0) {
            weekDay = "周日";
        } else if (day == 1) {
            weekDay = "周一";
        } else if (day == 2) {
            weekDay = "周二";
        } else if (day == 3) {
            weekDay = "周三";
        } else if (day == 4) {
            weekDay = "周四";
        } else if (day == 5) {
            weekDay = "周五";
        } else if (day == 6) {
            weekDay = "周六";
        }
        return weekDay;
    }

    /**
     * 将数字转成中文
     *
     * @param day
     */
    public static String getWeekDay(int year, int month, int day) {
        String weekDay = "";
        int week = getWeek(year, month, day);
        if (week == 7) {
            weekDay = "周日";
        } else if (week == 1) {
            weekDay = "周一";
        } else if (week == 2) {
            weekDay = "周二";
        } else if (week == 3) {
            weekDay = "周三";
        } else if (week == 4) {
            weekDay = "周四";
        } else if (week == 5) {
            weekDay = "周五";
        } else if (week == 6) {
            weekDay = "周六";
        }
        return weekDay;
    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @return
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(startDate);
        beginCalendar.set(Calendar.HOUR_OF_DAY, 0);
        beginCalendar.set(Calendar.MINUTE, 0);
        beginCalendar.set(Calendar.SECOND, 0);
        beginCalendar.set(Calendar.MILLISECOND, 0);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((endCalendar.getTime().getTime() - beginCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }
}
