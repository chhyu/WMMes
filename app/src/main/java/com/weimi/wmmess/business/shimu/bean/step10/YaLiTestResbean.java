package com.weimi.wmmess.business.shimu.bean.step10;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/29
 * Describle:
 */
@Entity
public class YaLiTestResbean {

    @Id
    private Long id;

    @Property(nameInDb = "yaLiTestResbeanId")
    private Long yaLiTestResbeanId;

    private String lengQueTime;

    private String zhiLiang;

    private String ChiCun;

    private String beiZhu;

    @Generated(hash = 946111753)
    public YaLiTestResbean(Long id, Long yaLiTestResbeanId, String lengQueTime,
            String zhiLiang, String ChiCun, String beiZhu) {
        this.id = id;
        this.yaLiTestResbeanId = yaLiTestResbeanId;
        this.lengQueTime = lengQueTime;
        this.zhiLiang = zhiLiang;
        this.ChiCun = ChiCun;
        this.beiZhu = beiZhu;
    }

    @Generated(hash = 1604632583)
    public YaLiTestResbean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYaLiTestResbeanId() {
        return this.yaLiTestResbeanId;
    }

    public void setYaLiTestResbeanId(Long yaLiTestResbeanId) {
        this.yaLiTestResbeanId = yaLiTestResbeanId;
    }

    public String getLengQueTime() {
        return this.lengQueTime;
    }

    public void setLengQueTime(String lengQueTime) {
        this.lengQueTime = lengQueTime;
    }

    public String getZhiLiang() {
        return this.zhiLiang;
    }

    public void setZhiLiang(String zhiLiang) {
        this.zhiLiang = zhiLiang;
    }

    public String getChiCun() {
        return this.ChiCun;
    }

    public void setChiCun(String ChiCun) {
        this.ChiCun = ChiCun;
    }

    public String getBeiZhu() {
        return this.beiZhu;
    }

    public void setBeiZhu(String beiZhu) {
        this.beiZhu = beiZhu;
    }


    
}
