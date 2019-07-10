package com.weimi.wmmess.business.shimu.bean.step4;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/14
 * Describle: 主流道温度数据实体类
 */
@Entity
public class ZhuLiuDaoResbean {

    @Id
    private Long id;

    @Property(nameInDb = "zhuliuDaoId")
    private Long zhuliuDaoId;

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
    public Long getZhuliuDaoId() {
        return this.zhuliuDaoId;
    }
    public void setZhuliuDaoId(Long zhuliuDaoId) {
        this.zhuliuDaoId = zhuliuDaoId;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1451919514)
    public ZhuLiuDaoResbean(Long id, Long zhuliuDaoId, int index, String record) {
        this.id = id;
        this.zhuliuDaoId = zhuliuDaoId;
        this.index = index;
        this.record = record;
    }
    @Generated(hash = 64510350)
    public ZhuLiuDaoResbean() {
    }


}
