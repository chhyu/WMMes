package com.weimi.wmmess.business.shimu.bean.problem;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.weimi.wmmess.business.shimu.bean.DaoSession;

/**
 * Create by chhyu
 * on 2019/5/27
 * Describle:
 */
@Entity
public class ProblemResbean {

    @Id
    private Long problemId;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    @ToMany(referencedJoinProperty = "problemItemResbeanId")
    private List<ProblemItemResbean> problemItemResbeans;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 56348635)
    private transient ProblemResbeanDao myDao;

    @Generated(hash = 1192060637)
    public ProblemResbean(Long problemId, boolean currentStepIsChecked) {
        this.problemId = problemId;
        this.currentStepIsChecked = currentStepIsChecked;
    }

    @Generated(hash = 1588135730)
    public ProblemResbean() {
    }

    public Long getProblemId() {
        return this.problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }

    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 935233632)
    public List<ProblemItemResbean> getProblemItemResbeans() {
        if (problemItemResbeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProblemItemResbeanDao targetDao = daoSession.getProblemItemResbeanDao();
            List<ProblemItemResbean> problemItemResbeansNew = targetDao
                    ._queryProblemResbean_ProblemItemResbeans(problemId);
            synchronized (this) {
                if (problemItemResbeans == null) {
                    problemItemResbeans = problemItemResbeansNew;
                }
            }
        }
        return problemItemResbeans;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2098580023)
    public synchronized void resetProblemItemResbeans() {
        problemItemResbeans = null;
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
    @Generated(hash = 129632974)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getProblemResbeanDao() : null;
    }

}
