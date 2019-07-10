package com.weimi.wmmess.business.shimu.bean.step6;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/15
 * Describle:
 */
@Entity
public class Step6Resbean {
    @Id
    private Long step6Id;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    //设定注塑压力
    private double sheDingZhuSuYaLi;

    private double shijiOne;
    private double shijiTwo;
    private double shijiThree;
    private double shijiFour;
    private double shijiFive;

    private int resultOne;
    private int resultTwo;
    private int resultThree;
    private int resultFour;
    private int resultFive;
    public int getResultFive() {
        return this.resultFive;
    }
    public void setResultFive(int resultFive) {
        this.resultFive = resultFive;
    }
    public int getResultFour() {
        return this.resultFour;
    }
    public void setResultFour(int resultFour) {
        this.resultFour = resultFour;
    }
    public int getResultThree() {
        return this.resultThree;
    }
    public void setResultThree(int resultThree) {
        this.resultThree = resultThree;
    }
    public int getResultTwo() {
        return this.resultTwo;
    }
    public void setResultTwo(int resultTwo) {
        this.resultTwo = resultTwo;
    }
    public int getResultOne() {
        return this.resultOne;
    }
    public void setResultOne(int resultOne) {
        this.resultOne = resultOne;
    }
    public double getShijiFive() {
        return this.shijiFive;
    }
    public void setShijiFive(double shijiFive) {
        this.shijiFive = shijiFive;
    }
    public double getShijiFour() {
        return this.shijiFour;
    }
    public void setShijiFour(double shijiFour) {
        this.shijiFour = shijiFour;
    }
    public double getShijiThree() {
        return this.shijiThree;
    }
    public void setShijiThree(double shijiThree) {
        this.shijiThree = shijiThree;
    }
    public double getShijiTwo() {
        return this.shijiTwo;
    }
    public void setShijiTwo(double shijiTwo) {
        this.shijiTwo = shijiTwo;
    }
    public double getShijiOne() {
        return this.shijiOne;
    }
    public void setShijiOne(double shijiOne) {
        this.shijiOne = shijiOne;
    }
    public double getSheDingZhuSuYaLi() {
        return this.sheDingZhuSuYaLi;
    }
    public void setSheDingZhuSuYaLi(double sheDingZhuSuYaLi) {
        this.sheDingZhuSuYaLi = sheDingZhuSuYaLi;
    }
    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }
    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }
    public Long getStep6Id() {
        return this.step6Id;
    }
    public void setStep6Id(Long step6Id) {
        this.step6Id = step6Id;
    }
    @Generated(hash = 922359620)
    public Step6Resbean(Long step6Id, boolean currentStepIsChecked,
            double sheDingZhuSuYaLi, double shijiOne, double shijiTwo,
            double shijiThree, double shijiFour, double shijiFive, int resultOne,
            int resultTwo, int resultThree, int resultFour, int resultFive) {
        this.step6Id = step6Id;
        this.currentStepIsChecked = currentStepIsChecked;
        this.sheDingZhuSuYaLi = sheDingZhuSuYaLi;
        this.shijiOne = shijiOne;
        this.shijiTwo = shijiTwo;
        this.shijiThree = shijiThree;
        this.shijiFour = shijiFour;
        this.shijiFive = shijiFive;
        this.resultOne = resultOne;
        this.resultTwo = resultTwo;
        this.resultThree = resultThree;
        this.resultFour = resultFour;
        this.resultFive = resultFive;
    }
    @Generated(hash = 416423184)
    public Step6Resbean() {
    }


}
