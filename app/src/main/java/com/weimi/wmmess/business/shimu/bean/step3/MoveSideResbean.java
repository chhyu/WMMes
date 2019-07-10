package com.weimi.wmmess.business.shimu.bean.step3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/13
 * Describle: 动模数据
 */
@Entity
public class MoveSideResbean {

    @Id
    private Long id;

    @Property(nameInDb = "moveSideResbeanId")
    private Long moveSideResbeanId;

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
    public Long getMoveSideResbeanId() {
        return this.moveSideResbeanId;
    }
    public void setMoveSideResbeanId(Long moveSideResbeanId) {
        this.moveSideResbeanId = moveSideResbeanId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1038981695)
    public MoveSideResbean(Long id, Long moveSideResbeanId, int index, String record) {
        this.id = id;
        this.moveSideResbeanId = moveSideResbeanId;
        this.index = index;
        this.record = record;
    }
    @Generated(hash = 350825851)
    public MoveSideResbean() {
    }

}
