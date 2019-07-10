package com.weimi.wmmess.business.shimu.bean.step4;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/14
 * Describle: 控制点温度数据实体类
 */
@Entity
public class KongZhiDianResbean {

    @Id
    private Long id;

    @Property(nameInDb = "kongZhiDianId")
    private Long kongZhiDianId;

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
    public Long getKongZhiDianId() {
        return this.kongZhiDianId;
    }
    public void setKongZhiDianId(Long kongZhiDianId) {
        this.kongZhiDianId = kongZhiDianId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1236630289)
    public KongZhiDianResbean(Long id, Long kongZhiDianId, int index, String record) {
        this.id = id;
        this.kongZhiDianId = kongZhiDianId;
        this.index = index;
        this.record = record;
    }
    @Generated(hash = 1552194310)
    public KongZhiDianResbean() {
    }


}
