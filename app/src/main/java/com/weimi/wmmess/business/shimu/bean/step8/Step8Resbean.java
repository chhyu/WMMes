package com.weimi.wmmess.business.shimu.bean.step8;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.weimi.wmmess.business.shimu.bean.DaoSession;

/**
 * Create by chhyu
 * on 2019/5/16
 * Describle:
 */
@Entity
public class Step8Resbean {

    @Id
    private Long step8Id;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    private String minAverageValue;

    private String maxAverageValue;

    private String averageValueZhiCha;

    private String averageValueBiZhiCha;

    private String duanSheOne;

    private String duanSheTwo;

    private String duanSheThree;

    private String duanSheFour;

    private String duanSheFive;

    private String duanSheSix;

    private String duanShePicOne;

    private String duanShePicTwo;

    private String duanShePicThree;

    private String duanShePicFour;

    private String duanShePicFive;

    private String duanShePicSix;

    @ToMany(referencedJoinProperty = "chongTianResbeanId")
    private List<ChongTianPingHengResbean> chongTianPingHengResbeans;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 955463622)
    private transient Step8ResbeanDao myDao;

    @Generated(hash = 672782053)
    public Step8Resbean(Long step8Id, boolean currentStepIsChecked,
            String minAverageValue, String maxAverageValue,
            String averageValueZhiCha, String averageValueBiZhiCha,
            String duanSheOne, String duanSheTwo, String duanSheThree,
            String duanSheFour, String duanSheFive, String duanSheSix,
            String duanShePicOne, String duanShePicTwo, String duanShePicThree,
            String duanShePicFour, String duanShePicFive, String duanShePicSix) {
        this.step8Id = step8Id;
        this.currentStepIsChecked = currentStepIsChecked;
        this.minAverageValue = minAverageValue;
        this.maxAverageValue = maxAverageValue;
        this.averageValueZhiCha = averageValueZhiCha;
        this.averageValueBiZhiCha = averageValueBiZhiCha;
        this.duanSheOne = duanSheOne;
        this.duanSheTwo = duanSheTwo;
        this.duanSheThree = duanSheThree;
        this.duanSheFour = duanSheFour;
        this.duanSheFive = duanSheFive;
        this.duanSheSix = duanSheSix;
        this.duanShePicOne = duanShePicOne;
        this.duanShePicTwo = duanShePicTwo;
        this.duanShePicThree = duanShePicThree;
        this.duanShePicFour = duanShePicFour;
        this.duanShePicFive = duanShePicFive;
        this.duanShePicSix = duanShePicSix;
    }

    @Generated(hash = 145646598)
    public Step8Resbean() {
    }

    public Long getStep8Id() {
        return this.step8Id;
    }

    public void setStep8Id(Long step8Id) {
        this.step8Id = step8Id;
    }

    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }

    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }

    public String getMinAverageValue() {
        return this.minAverageValue;
    }

    public void setMinAverageValue(String minAverageValue) {
        this.minAverageValue = minAverageValue;
    }

    public String getMaxAverageValue() {
        return this.maxAverageValue;
    }

    public void setMaxAverageValue(String maxAverageValue) {
        this.maxAverageValue = maxAverageValue;
    }

    public String getAverageValueZhiCha() {
        return this.averageValueZhiCha;
    }

    public void setAverageValueZhiCha(String averageValueZhiCha) {
        this.averageValueZhiCha = averageValueZhiCha;
    }

    public String getAverageValueBiZhiCha() {
        return this.averageValueBiZhiCha;
    }

    public void setAverageValueBiZhiCha(String averageValueBiZhiCha) {
        this.averageValueBiZhiCha = averageValueBiZhiCha;
    }

    public String getDuanSheOne() {
        return this.duanSheOne;
    }

    public void setDuanSheOne(String duanSheOne) {
        this.duanSheOne = duanSheOne;
    }

    public String getDuanSheTwo() {
        return this.duanSheTwo;
    }

    public void setDuanSheTwo(String duanSheTwo) {
        this.duanSheTwo = duanSheTwo;
    }

    public String getDuanSheThree() {
        return this.duanSheThree;
    }

    public void setDuanSheThree(String duanSheThree) {
        this.duanSheThree = duanSheThree;
    }

    public String getDuanSheFour() {
        return this.duanSheFour;
    }

    public void setDuanSheFour(String duanSheFour) {
        this.duanSheFour = duanSheFour;
    }

    public String getDuanSheFive() {
        return this.duanSheFive;
    }

    public void setDuanSheFive(String duanSheFive) {
        this.duanSheFive = duanSheFive;
    }

    public String getDuanSheSix() {
        return this.duanSheSix;
    }

    public void setDuanSheSix(String duanSheSix) {
        this.duanSheSix = duanSheSix;
    }

    public String getDuanShePicOne() {
        return this.duanShePicOne;
    }

    public void setDuanShePicOne(String duanShePicOne) {
        this.duanShePicOne = duanShePicOne;
    }

    public String getDuanShePicTwo() {
        return this.duanShePicTwo;
    }

    public void setDuanShePicTwo(String duanShePicTwo) {
        this.duanShePicTwo = duanShePicTwo;
    }

    public String getDuanShePicThree() {
        return this.duanShePicThree;
    }

    public void setDuanShePicThree(String duanShePicThree) {
        this.duanShePicThree = duanShePicThree;
    }

    public String getDuanShePicFour() {
        return this.duanShePicFour;
    }

    public void setDuanShePicFour(String duanShePicFour) {
        this.duanShePicFour = duanShePicFour;
    }

    public String getDuanShePicFive() {
        return this.duanShePicFive;
    }

    public void setDuanShePicFive(String duanShePicFive) {
        this.duanShePicFive = duanShePicFive;
    }

    public String getDuanShePicSix() {
        return this.duanShePicSix;
    }

    public void setDuanShePicSix(String duanShePicSix) {
        this.duanShePicSix = duanShePicSix;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1591616632)
    public List<ChongTianPingHengResbean> getChongTianPingHengResbeans() {
        if (chongTianPingHengResbeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ChongTianPingHengResbeanDao targetDao = daoSession
                    .getChongTianPingHengResbeanDao();
            List<ChongTianPingHengResbean> chongTianPingHengResbeansNew = targetDao
                    ._queryStep8Resbean_ChongTianPingHengResbeans(step8Id);
            synchronized (this) {
                if (chongTianPingHengResbeans == null) {
                    chongTianPingHengResbeans = chongTianPingHengResbeansNew;
                }
            }
        }
        return chongTianPingHengResbeans;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1382623782)
    public synchronized void resetChongTianPingHengResbeans() {
        chongTianPingHengResbeans = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1982725051)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStep8ResbeanDao() : null;
    }

   
}
