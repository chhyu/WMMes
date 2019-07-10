package com.weimi.wmmess.business.shimu.bean.step3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/13
 * Describle: 定模数据
 */
@Entity
public class FixSideResbean {

    @Id
    private Long id;

    @Property(nameInDb = "fixSideResbeanId")
    private Long fixSideResbeanId;

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
    public Long getFixSideResbeanId() {
        return this.fixSideResbeanId;
    }
    public void setFixSideResbeanId(Long fixSideResbeanId) {
        this.fixSideResbeanId = fixSideResbeanId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 421459513)
    public FixSideResbean(Long id, Long fixSideResbeanId, int index, String record) {
        this.id = id;
        this.fixSideResbeanId = fixSideResbeanId;
        this.index = index;
        this.record = record;
    }
    @Generated(hash = 206073510)
    public FixSideResbean() {
    }


}
