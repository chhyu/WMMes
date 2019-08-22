package com.weimi.wmmess.business.shimu.params;

/**
 * Create by chhyu
 * on 2019/7/5
 * Describle: 试模工艺参数参数
 */
public class MobileTrialModeParam {

    private String parameterName;  //参数名称
    private String upperLimit;      //最大值
    private String lowerLimit;      //最小值
    private String defaultValue;    //设定值
    private String actualValue;     //实际值
    private String checkDate;       //检查日期
    private String parameterType;   //参数类型

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(String lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }
}
