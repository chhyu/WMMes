package com.weimi.wmmess.business.shimu.bean.step7;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/27
 * Describle:
 */
@Entity
public class ZhuSuResbean {

    @Id
    private Long id;

    /*@Property(nameInDb = "zhuSuResbeanId")*/
    @NotNull
    private Long zhuSuResbeanId;

    @NotNull
    private String zhuSuSuDu;

    @NotNull
    private String zhuSuTime;

    @NotNull
    private String yaLiMax;

    @NotNull
    private String yaliAndTime;

    public String getYaliAndTime() {
        return this.yaliAndTime;
    }

    public void setYaliAndTime(String yaliAndTime) {
        this.yaliAndTime = yaliAndTime;
    }

    public String getYaLiMax() {
        return this.yaLiMax;
    }

    public void setYaLiMax(String yaLiMax) {
        this.yaLiMax = yaLiMax;
    }

    public String getZhuSuTime() {
        return this.zhuSuTime;
    }

    public void setZhuSuTime(String zhuSuTime) {
        this.zhuSuTime = zhuSuTime;
    }

    public String getZhuSuSuDu() {
        return this.zhuSuSuDu;
    }

    public void setZhuSuSuDu(String zhuSuSuDu) {
        this.zhuSuSuDu = zhuSuSuDu;
    }

    public Long getZhuSuResbeanId() {
        return this.zhuSuResbeanId;
    }

    public void setZhuSuResbeanId(Long zhuSuResbeanId) {
        this.zhuSuResbeanId = zhuSuResbeanId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 1484697325)
    public ZhuSuResbean(Long id, @NotNull Long zhuSuResbeanId,
            @NotNull String zhuSuSuDu, @NotNull String zhuSuTime,
            @NotNull String yaLiMax, @NotNull String yaliAndTime) {
        this.id = id;
        this.zhuSuResbeanId = zhuSuResbeanId;
        this.zhuSuSuDu = zhuSuSuDu;
        this.zhuSuTime = zhuSuTime;
        this.yaLiMax = yaLiMax;
        this.yaliAndTime = yaliAndTime;
    }

    @Generated(hash = 731542737)
    public ZhuSuResbean() {
    }


}
