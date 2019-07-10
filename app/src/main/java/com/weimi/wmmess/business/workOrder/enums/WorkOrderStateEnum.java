package com.weimi.wmmess.business.workOrder.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Create by chhyu
 * on 2019/7/2
 * Describle: 工单状态
 */
public class WorkOrderStateEnum {
    public static final String START = "START";                 //工单下达
    public static final String READYING = "READYING";           //工单准备
    public static final String PROCEDUCING = "PROCEDUCING";     //生产中
    public static final String FINISH = "FINISH";               //生产完成
    public static final String EXCEPTION = "EXCEPTION";         //生产异常

    @StringDef({
            START,
            READYING,
            PROCEDUCING,
            FINISH,
            EXCEPTION
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface WorkOrderState {

    }
}
