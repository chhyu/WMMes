package com.weimi.wmmess.business.workOrder.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by chhyu on 2019/7/2.
 */
public class WorkOrderTypeEnum {
    public static final String TRIAL_MODEL = "trialModel";          //试模工单
    public static final String TRIALP_RODUCE = "trialProduce";      //试做工单
    public static final String REWORK = "rework";                   //返工工单
    public static final String PRODUCE = "produce";                 //生产工单

    public static final String UNKNOWN = "unknown";                 //未知


    // 声明构造器
    @StringDef({TRIAL_MODEL,
            TRIALP_RODUCE,
            REWORK,
            PRODUCE,
            UNKNOWN})
    @Retention(RetentionPolicy.SOURCE) //表示注解所存活的时间,在运行时,而不会存在. class 文件.
    public @interface WorkOrderType {

    }
}
