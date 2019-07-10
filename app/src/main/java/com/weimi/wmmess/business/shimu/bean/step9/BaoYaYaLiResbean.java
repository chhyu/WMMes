package com.weimi.wmmess.business.shimu.bean.step9;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/23
 * Describle:
 */
@Entity
public class BaoYaYaLiResbean {
    @Id
    private Long id;

    @Property(nameInDb = "baoYaYaLiResbeanId")
    private Long baoYaYaLiResbeanId;

    private String yali;

    private String yaLiOne;

    private String yaLiTwo;

    private String yaLiThree;

    private String yaLiAverageValue;

    private String yaLiBeizhu;

    public String getYaLiBeizhu() {
        return this.yaLiBeizhu;
    }

    public void setYaLiBeizhu(String yaLiBeizhu) {
        this.yaLiBeizhu = yaLiBeizhu;
    }

    public String getYaLiAverageValue() {
        return this.yaLiAverageValue;
    }

    public void setYaLiAverageValue(String yaLiAverageValue) {
        this.yaLiAverageValue = yaLiAverageValue;
    }

    public String getYaLiThree() {
        return this.yaLiThree;
    }

    public void setYaLiThree(String yaLiThree) {
        this.yaLiThree = yaLiThree;
    }

    public String getYaLiTwo() {
        return this.yaLiTwo;
    }

    public void setYaLiTwo(String yaLiTwo) {
        this.yaLiTwo = yaLiTwo;
    }

    public String getYaLiOne() {
        return this.yaLiOne;
    }

    public void setYaLiOne(String yaLiOne) {
        this.yaLiOne = yaLiOne;
    }

    public String getYali() {
        return this.yali;
    }

    public void setYali(String yali) {
        this.yali = yali;
    }

    public Long getBaoYaYaLiResbeanId() {
        return this.baoYaYaLiResbeanId;
    }

    public void setBaoYaYaLiResbeanId(Long baoYaYaLiResbeanId) {
        this.baoYaYaLiResbeanId = baoYaYaLiResbeanId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 2143431833)
    public BaoYaYaLiResbean(Long id, Long baoYaYaLiResbeanId, String yali,
            String yaLiOne, String yaLiTwo, String yaLiThree,
            String yaLiAverageValue, String yaLiBeizhu) {
        this.id = id;
        this.baoYaYaLiResbeanId = baoYaYaLiResbeanId;
        this.yali = yali;
        this.yaLiOne = yaLiOne;
        this.yaLiTwo = yaLiTwo;
        this.yaLiThree = yaLiThree;
        this.yaLiAverageValue = yaLiAverageValue;
        this.yaLiBeizhu = yaLiBeizhu;
    }

    @Generated(hash = 2004651359)
    public BaoYaYaLiResbean() {
    }



}
