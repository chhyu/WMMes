package com.weimi.wmmess.business.shimu.bean.step3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/13
 * Describle: 滑块数据
 */
@Entity
public class SlideResbean {

    @Id
    private Long id;

    @Property(nameInDb = "slideResbeanId")
    private Long slideResbeanId;

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
    public Long getSlideResbeanId() {
        return this.slideResbeanId;
    }
    public void setSlideResbeanId(Long slideResbeanId) {
        this.slideResbeanId = slideResbeanId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1689743859)
    public SlideResbean(Long id, Long slideResbeanId, int index, String record) {
        this.id = id;
        this.slideResbeanId = slideResbeanId;
        this.index = index;
        this.record = record;
    }
    @Generated(hash = 871597373)
    public SlideResbean() {
    }

}
