package com.weimi.wmmess.business.shimu.bean.step8;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Create by chhyu
 * on 2019/5/29
 * Describle:
 */
@Entity
public class ChongTianPingHengResbean {

    @Id
    private Long id;

    @Property(nameInDb = "chongTianResbeanId")
    private Long chongTianResbeanId;

    private String xueHao;

    private String one;

    private String two;

    private String three;

    private String four;

    private String five;

    private String six;

    private String min;

    private String max;

    private String averageValue;

    @Generated(hash = 1684435771)
    public ChongTianPingHengResbean(Long id, Long chongTianResbeanId, String xueHao,
            String one, String two, String three, String four, String five,
            String six, String min, String max, String averageValue) {
        this.id = id;
        this.chongTianResbeanId = chongTianResbeanId;
        this.xueHao = xueHao;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.six = six;
        this.min = min;
        this.max = max;
        this.averageValue = averageValue;
    }

    @Generated(hash = 1765185312)
    public ChongTianPingHengResbean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChongTianResbeanId() {
        return this.chongTianResbeanId;
    }

    public void setChongTianResbeanId(Long chongTianResbeanId) {
        this.chongTianResbeanId = chongTianResbeanId;
    }

    public String getXueHao() {
        return this.xueHao;
    }

    public void setXueHao(String xueHao) {
        this.xueHao = xueHao;
    }

    public String getOne() {
        return this.one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return this.two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return this.three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return this.four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getFive() {
        return this.five;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public String getSix() {
        return this.six;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public String getMin() {
        return this.min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return this.max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getAverageValue() {
        return this.averageValue;
    }

    public void setAverageValue(String averageValue) {
        this.averageValue = averageValue;
    }


}
