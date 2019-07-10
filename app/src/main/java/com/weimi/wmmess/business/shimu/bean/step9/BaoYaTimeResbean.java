package com.weimi.wmmess.business.shimu.bean.step9;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/24
 * Describle:
 */
@Entity
public class BaoYaTimeResbean {

    @Id
    private Long id;

    @Property(nameInDb = "baoYaTimeResbeanId")
    private Long baoYaTimeResbeanId;

    private String time;

    private String timeOne;

    private String timeTwo;

    private String timeThree;

    private String timeAverageValue;

    private String timeBeizhu;

    public String getTimeBeizhu() {
        return this.timeBeizhu;
    }

    public void setTimeBeizhu(String timeBeizhu) {
        this.timeBeizhu = timeBeizhu;
    }

    public String getTimeAverageValue() {
        return this.timeAverageValue;
    }

    public void setTimeAverageValue(String timeAverageValue) {
        this.timeAverageValue = timeAverageValue;
    }

    public String getTimeThree() {
        return this.timeThree;
    }

    public void setTimeThree(String timeThree) {
        this.timeThree = timeThree;
    }

    public String getTimeTwo() {
        return this.timeTwo;
    }

    public void setTimeTwo(String timeTwo) {
        this.timeTwo = timeTwo;
    }

    public String getTimeOne() {
        return this.timeOne;
    }

    public void setTimeOne(String timeOne) {
        this.timeOne = timeOne;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getBaoYaTimeResbeanId() {
        return this.baoYaTimeResbeanId;
    }

    public void setBaoYaTimeResbeanId(Long baoYaTimeResbeanId) {
        this.baoYaTimeResbeanId = baoYaTimeResbeanId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 827099884)
    public BaoYaTimeResbean(Long id, Long baoYaTimeResbeanId, String time,
            String timeOne, String timeTwo, String timeThree,
            String timeAverageValue, String timeBeizhu) {
        this.id = id;
        this.baoYaTimeResbeanId = baoYaTimeResbeanId;
        this.time = time;
        this.timeOne = timeOne;
        this.timeTwo = timeTwo;
        this.timeThree = timeThree;
        this.timeAverageValue = timeAverageValue;
        this.timeBeizhu = timeBeizhu;
    }

    @Generated(hash = 1017799980)
    public BaoYaTimeResbean() {
    }


}
