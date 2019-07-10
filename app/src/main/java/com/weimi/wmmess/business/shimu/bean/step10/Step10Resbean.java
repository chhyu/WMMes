package com.weimi.wmmess.business.shimu.bean.step10;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.weimi.wmmess.business.shimu.bean.DaoSession;

/**
 * Create by chhyu
 * on 2019/5/17
 * Describle: 第十步数据
 */
@Entity
public class Step10Resbean {
    @Id
    private Long step10Id;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    /**
     * 确定冷却时间
     */
    private String confirmTime;

    @ToMany(referencedJoinProperty = "yaLiTestResbeanId")
    private List<YaLiTestResbean> yaLiTestResbeans;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1061986359)
    private transient Step10ResbeanDao myDao;

    @Generated(hash = 1585327267)
    public Step10Resbean(Long step10Id, boolean currentStepIsChecked,
            String confirmTime) {
        this.step10Id = step10Id;
        this.currentStepIsChecked = currentStepIsChecked;
        this.confirmTime = confirmTime;
    }

    @Generated(hash = 1354730368)
    public Step10Resbean() {
    }

    public Long getStep10Id() {
        return this.step10Id;
    }

    public void setStep10Id(Long step10Id) {
        this.step10Id = step10Id;
    }

    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }

    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }

    public String getConfirmTime() {
        return this.confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 645803481)
    public List<YaLiTestResbean> getYaLiTestResbeans() {
        if (yaLiTestResbeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            YaLiTestResbeanDao targetDao = daoSession.getYaLiTestResbeanDao();
            List<YaLiTestResbean> yaLiTestResbeansNew = targetDao
                    ._queryStep10Resbean_YaLiTestResbeans(step10Id);
            synchronized (this) {
                if (yaLiTestResbeans == null) {
                    yaLiTestResbeans = yaLiTestResbeansNew;
                }
            }
        }
        return yaLiTestResbeans;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1636522031)
    public synchronized void resetYaLiTestResbeans() {
        yaLiTestResbeans = null;
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
    @Generated(hash = 166728045)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStep10ResbeanDao() : null;
    }



}

