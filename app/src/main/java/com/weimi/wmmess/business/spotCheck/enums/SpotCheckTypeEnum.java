package com.weimi.wmmess.business.spotCheck.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Create by chhyu
 * on 2019/7/27
 * Describle: 点检类型
 */
public class SpotCheckTypeEnum {
    public static final String DAY = "DAY";             //日点检
    public static final String WEEK = "WEEK";           //周点检
    public static final String MONTH = "MONTH";         //月点检
    public static final String SEASON = "SEASON";       //季点检
    public static final String YEER = "YEER";           //年点检

    @StringDef({
            DAY,
            WEEK,
            MONTH,
            SEASON,
            YEER
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface SpotCheckType {

    }
}
