package com.weimi.wmmess.business.shimu.bean.step3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/13
 * Describle:第三步水路数据
 */
@Entity
public class Step3ShuiLuDate {

    @Id
    private Long id;

    @Property(nameInDb = "shuiLuDateId")
    private Long shuiLuDateId;

    private int index;

    private String record;

    public String getRecord() {
        return this.record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Long getShuiLuDateId() {
        return this.shuiLuDateId;
    }

    public void setShuiLuDateId(Long shuiLuDateId) {
        this.shuiLuDateId = shuiLuDateId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 830245423)
    public Step3ShuiLuDate(Long id, Long shuiLuDateId, int index, String record) {
        this.id = id;
        this.shuiLuDateId = shuiLuDateId;
        this.index = index;
        this.record = record;
    }

    @Generated(hash = 52219463)
    public Step3ShuiLuDate() {
    }


}
