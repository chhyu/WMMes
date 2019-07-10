package com.weimi.wmmess.business.shimu.bean.step5;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/14
 * Describle: 第五步数据实体类
 */
@Entity
public class Step5Resbean {

    @Id
    private Long step5Id;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    private String kaiMoSheDingSj;

    private String kaiMoShiJiSj;

    private String dingChuSheDingSj;

    private String dingChuShiJiSj;

    private String dingTuiSheDingSj;

    private String dingTuiShiJiSj;

    private String heMoSheDingSj;

    private String heMoShiJiSj;

    private String lengQueSj;

    private String xunHuanZhouQi;

    private int one;

    private int two;

    private int three;

    private int four;

    private int five;

    private int six;

    private int seven;

    private int eight;

    private int nine;

    private int ten;

    private int eleven;

    public int getEleven() {
        return this.eleven;
    }

    public void setEleven(int eleven) {
        this.eleven = eleven;
    }

    public int getTen() {
        return this.ten;
    }

    public void setTen(int ten) {
        this.ten = ten;
    }

    public int getNine() {
        return this.nine;
    }

    public void setNine(int nine) {
        this.nine = nine;
    }

    public int getEight() {
        return this.eight;
    }

    public void setEight(int eight) {
        this.eight = eight;
    }

    public int getSeven() {
        return this.seven;
    }

    public void setSeven(int seven) {
        this.seven = seven;
    }

    public int getSix() {
        return this.six;
    }

    public void setSix(int six) {
        this.six = six;
    }

    public int getFive() {
        return this.five;
    }

    public void setFive(int five) {
        this.five = five;
    }

    public int getFour() {
        return this.four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public int getThree() {
        return this.three;
    }

    public void setThree(int three) {
        this.three = three;
    }

    public int getTwo() {
        return this.two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public int getOne() {
        return this.one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public String getXunHuanZhouQi() {
        return this.xunHuanZhouQi;
    }

    public void setXunHuanZhouQi(String xunHuanZhouQi) {
        this.xunHuanZhouQi = xunHuanZhouQi;
    }

    public String getLengQueSj() {
        return this.lengQueSj;
    }

    public void setLengQueSj(String lengQueSj) {
        this.lengQueSj = lengQueSj;
    }

    public String getHeMoShiJiSj() {
        return this.heMoShiJiSj;
    }

    public void setHeMoShiJiSj(String heMoShiJiSj) {
        this.heMoShiJiSj = heMoShiJiSj;
    }

    public String getHeMoSheDingSj() {
        return this.heMoSheDingSj;
    }

    public void setHeMoSheDingSj(String heMoSheDingSj) {
        this.heMoSheDingSj = heMoSheDingSj;
    }

    public String getDingTuiShiJiSj() {
        return this.dingTuiShiJiSj;
    }

    public void setDingTuiShiJiSj(String dingTuiShiJiSj) {
        this.dingTuiShiJiSj = dingTuiShiJiSj;
    }

    public String getDingTuiSheDingSj() {
        return this.dingTuiSheDingSj;
    }

    public void setDingTuiSheDingSj(String dingTuiSheDingSj) {
        this.dingTuiSheDingSj = dingTuiSheDingSj;
    }

    public String getDingChuShiJiSj() {
        return this.dingChuShiJiSj;
    }

    public void setDingChuShiJiSj(String dingChuShiJiSj) {
        this.dingChuShiJiSj = dingChuShiJiSj;
    }

    public String getDingChuSheDingSj() {
        return this.dingChuSheDingSj;
    }

    public void setDingChuSheDingSj(String dingChuSheDingSj) {
        this.dingChuSheDingSj = dingChuSheDingSj;
    }

    public String getKaiMoShiJiSj() {
        return this.kaiMoShiJiSj;
    }

    public void setKaiMoShiJiSj(String kaiMoShiJiSj) {
        this.kaiMoShiJiSj = kaiMoShiJiSj;
    }

    public String getKaiMoSheDingSj() {
        return this.kaiMoSheDingSj;
    }

    public void setKaiMoSheDingSj(String kaiMoSheDingSj) {
        this.kaiMoSheDingSj = kaiMoSheDingSj;
    }

    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }

    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }

    public Long getStep5Id() {
        return this.step5Id;
    }

    public void setStep5Id(Long step5Id) {
        this.step5Id = step5Id;
    }

    @Generated(hash = 403907991)
    public Step5Resbean(Long step5Id, boolean currentStepIsChecked,
            String kaiMoSheDingSj, String kaiMoShiJiSj, String dingChuSheDingSj,
            String dingChuShiJiSj, String dingTuiSheDingSj, String dingTuiShiJiSj,
            String heMoSheDingSj, String heMoShiJiSj, String lengQueSj,
            String xunHuanZhouQi, int one, int two, int three, int four, int five,
            int six, int seven, int eight, int nine, int ten, int eleven) {
        this.step5Id = step5Id;
        this.currentStepIsChecked = currentStepIsChecked;
        this.kaiMoSheDingSj = kaiMoSheDingSj;
        this.kaiMoShiJiSj = kaiMoShiJiSj;
        this.dingChuSheDingSj = dingChuSheDingSj;
        this.dingChuShiJiSj = dingChuShiJiSj;
        this.dingTuiSheDingSj = dingTuiSheDingSj;
        this.dingTuiShiJiSj = dingTuiShiJiSj;
        this.heMoSheDingSj = heMoSheDingSj;
        this.heMoShiJiSj = heMoShiJiSj;
        this.lengQueSj = lengQueSj;
        this.xunHuanZhouQi = xunHuanZhouQi;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.six = six;
        this.seven = seven;
        this.eight = eight;
        this.nine = nine;
        this.ten = ten;
        this.eleven = eleven;
    }

    @Generated(hash = 607175242)
    public Step5Resbean() {
    }


}
