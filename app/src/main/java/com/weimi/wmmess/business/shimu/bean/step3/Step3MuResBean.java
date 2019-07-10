package com.weimi.wmmess.business.shimu.bean.step3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.weimi.wmmess.business.shimu.bean.DaoSession;

/**
 * Create by chhyu
 * on 2019/4/28
 * Describle: 第三步水路流量测试记录resbean（动模，定模，滑块）
 */
@Entity
public class Step3MuResBean {

    @Id
    private Long step3Id;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    @ToMany(referencedJoinProperty = "fixSideResbeanId")
    private List<FixSideResbean> fixSideResbeanList;

    @ToMany(referencedJoinProperty = "moveSideResbeanId")
    private List<MoveSideResbean> moveSideResbeanList;

    @ToMany(referencedJoinProperty = "slideResbeanId")
    private List<SlideResbean> slideResbeanList;

    @ToMany(referencedJoinProperty = "shuiLuDateId")
    private List<Step3ShuiLuDate> shuiLuDateList;

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
    @Generated(hash = 44188975)
    public synchronized void resetShuiLuDateList() {
        shuiLuDateList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 280252)
    public List<Step3ShuiLuDate> getShuiLuDateList() {
        if (shuiLuDateList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step3ShuiLuDateDao targetDao = daoSession.getStep3ShuiLuDateDao();
            List<Step3ShuiLuDate> shuiLuDateListNew = targetDao._queryStep3MuResBean_ShuiLuDateList(step3Id);
            synchronized (this) {
                if(shuiLuDateList == null) {
                    shuiLuDateList = shuiLuDateListNew;
                }
            }
        }
        return shuiLuDateList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1594489378)
    public synchronized void resetSlideResbeanList() {
        slideResbeanList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 694994530)
    public List<SlideResbean> getSlideResbeanList() {
        if (slideResbeanList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SlideResbeanDao targetDao = daoSession.getSlideResbeanDao();
            List<SlideResbean> slideResbeanListNew = targetDao._queryStep3MuResBean_SlideResbeanList(step3Id);
            synchronized (this) {
                if(slideResbeanList == null) {
                    slideResbeanList = slideResbeanListNew;
                }
            }
        }
        return slideResbeanList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 145018942)
    public synchronized void resetMoveSideResbeanList() {
        moveSideResbeanList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1004290589)
    public List<MoveSideResbean> getMoveSideResbeanList() {
        if (moveSideResbeanList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MoveSideResbeanDao targetDao = daoSession.getMoveSideResbeanDao();
            List<MoveSideResbean> moveSideResbeanListNew = targetDao._queryStep3MuResBean_MoveSideResbeanList(step3Id);
            synchronized (this) {
                if(moveSideResbeanList == null) {
                    moveSideResbeanList = moveSideResbeanListNew;
                }
            }
        }
        return moveSideResbeanList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1530191614)
    public synchronized void resetFixSideResbeanList() {
        fixSideResbeanList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1104705300)
    public List<FixSideResbean> getFixSideResbeanList() {
        if (fixSideResbeanList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FixSideResbeanDao targetDao = daoSession.getFixSideResbeanDao();
            List<FixSideResbean> fixSideResbeanListNew = targetDao._queryStep3MuResBean_FixSideResbeanList(step3Id);
            synchronized (this) {
                if(fixSideResbeanList == null) {
                    fixSideResbeanList = fixSideResbeanListNew;
                }
            }
        }
        return fixSideResbeanList;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 365171590)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStep3MuResBeanDao() : null;
    }

    /** Used for active entity operations. */
    @Generated(hash = 401751893)
    private transient Step3MuResBeanDao myDao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }

    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }

    public Long getStep3Id() {
        return this.step3Id;
    }

    public void setStep3Id(Long step3Id) {
        this.step3Id = step3Id;
    }

    @Generated(hash = 1596653108)
    public Step3MuResBean(Long step3Id, boolean currentStepIsChecked) {
        this.step3Id = step3Id;
        this.currentStepIsChecked = currentStepIsChecked;
    }

    @Generated(hash = 1434263235)
    public Step3MuResBean() {
    }


}
