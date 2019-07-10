package com.weimi.wmmess.business.shimu.bean.step9;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.weimi.wmmess.business.shimu.bean.DaoSession;

/**
 * Create by chhyu
 * on 2019/5/21
 * Describle:
 */
@Entity
public class Step9Resbean {

    @Id
    private Long step9Id;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    private String yaLiMin;

    private String yaLiMax;

    private String yaLiConfirm;

    private String timeMin;

    private String timeMax;

    private String TimeConfirm;

    @ToMany(referencedJoinProperty = "baoYaYaLiResbeanId")
    private List<BaoYaYaLiResbean> baoYaYaLiResbeans;

    @ToMany(referencedJoinProperty = "baoYaTimeResbeanId")
    private List<BaoYaTimeResbean> baoYaTimeResbeans;

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

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 303176990)
    public synchronized void resetBaoYaTimeResbeans() {
        baoYaTimeResbeans = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1183698284)
    public List<BaoYaTimeResbean> getBaoYaTimeResbeans() {
        if (baoYaTimeResbeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BaoYaTimeResbeanDao targetDao = daoSession.getBaoYaTimeResbeanDao();
            List<BaoYaTimeResbean> baoYaTimeResbeansNew = targetDao._queryStep9Resbean_BaoYaTimeResbeans(step9Id);
            synchronized (this) {
                if(baoYaTimeResbeans == null) {
                    baoYaTimeResbeans = baoYaTimeResbeansNew;
                }
            }
        }
        return baoYaTimeResbeans;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 953221123)
    public synchronized void resetBaoYaYaLiResbeans() {
        baoYaYaLiResbeans = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 669539048)
    public List<BaoYaYaLiResbean> getBaoYaYaLiResbeans() {
        if (baoYaYaLiResbeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BaoYaYaLiResbeanDao targetDao = daoSession.getBaoYaYaLiResbeanDao();
            List<BaoYaYaLiResbean> baoYaYaLiResbeansNew = targetDao._queryStep9Resbean_BaoYaYaLiResbeans(step9Id);
            synchronized (this) {
                if(baoYaYaLiResbeans == null) {
                    baoYaYaLiResbeans = baoYaYaLiResbeansNew;
                }
            }
        }
        return baoYaYaLiResbeans;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 164171384)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStep9ResbeanDao() : null;
    }

    /** Used for active entity operations. */
    @Generated(hash = 1282020492)
    private transient Step9ResbeanDao myDao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public String getTimeConfirm() {
        return this.TimeConfirm;
    }

    public void setTimeConfirm(String TimeConfirm) {
        this.TimeConfirm = TimeConfirm;
    }

    public String getTimeMax() {
        return this.timeMax;
    }

    public void setTimeMax(String timeMax) {
        this.timeMax = timeMax;
    }

    public String getTimeMin() {
        return this.timeMin;
    }

    public void setTimeMin(String timeMin) {
        this.timeMin = timeMin;
    }

    public String getYaLiConfirm() {
        return this.yaLiConfirm;
    }

    public void setYaLiConfirm(String yaLiConfirm) {
        this.yaLiConfirm = yaLiConfirm;
    }

    public String getYaLiMax() {
        return this.yaLiMax;
    }

    public void setYaLiMax(String yaLiMax) {
        this.yaLiMax = yaLiMax;
    }

    public String getYaLiMin() {
        return this.yaLiMin;
    }

    public void setYaLiMin(String yaLiMin) {
        this.yaLiMin = yaLiMin;
    }

    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }

    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }

    public Long getStep9Id() {
        return this.step9Id;
    }

    public void setStep9Id(Long step9Id) {
        this.step9Id = step9Id;
    }

    @Generated(hash = 2136131622)
    public Step9Resbean(Long step9Id, boolean currentStepIsChecked, String yaLiMin,
            String yaLiMax, String yaLiConfirm, String timeMin, String timeMax,
            String TimeConfirm) {
        this.step9Id = step9Id;
        this.currentStepIsChecked = currentStepIsChecked;
        this.yaLiMin = yaLiMin;
        this.yaLiMax = yaLiMax;
        this.yaLiConfirm = yaLiConfirm;
        this.timeMin = timeMin;
        this.timeMax = timeMax;
        this.TimeConfirm = TimeConfirm;
    }

    @Generated(hash = 1455754743)
    public Step9Resbean() {
    }


}
