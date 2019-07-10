package com.weimi.wmmess.business.shimu.bean.step7;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.weimi.wmmess.business.shimu.bean.DaoSession;

/**
 * Create by chhyu
 * on 2019/5/15
 * Describle: 第七步数据
 */
@Entity
public class Step7Resbean {
    @Id
    private Long step7Id;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    @NotNull
    private String zuijiaShijian;

    @NotNull
    private String zuijiaSuDu;

    @ToMany(referencedJoinProperty = "zhuSuResbeanId")
    private List<ZhuSuResbean> zhuSuResbeanList;

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
    @Generated(hash = 8674106)
    public synchronized void resetZhuSuResbeanList() {
        zhuSuResbeanList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1240068048)
    public List<ZhuSuResbean> getZhuSuResbeanList() {
        if (zhuSuResbeanList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ZhuSuResbeanDao targetDao = daoSession.getZhuSuResbeanDao();
            List<ZhuSuResbean> zhuSuResbeanListNew = targetDao._queryStep7Resbean_ZhuSuResbeanList(step7Id);
            synchronized (this) {
                if(zhuSuResbeanList == null) {
                    zhuSuResbeanList = zhuSuResbeanListNew;
                }
            }
        }
        return zhuSuResbeanList;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 924219968)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStep7ResbeanDao() : null;
    }

    /** Used for active entity operations. */
    @Generated(hash = 799402478)
    private transient Step7ResbeanDao myDao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public String getZuijiaSuDu() {
        return this.zuijiaSuDu;
    }

    public void setZuijiaSuDu(String zuijiaSuDu) {
        this.zuijiaSuDu = zuijiaSuDu;
    }

    public String getZuijiaShijian() {
        return this.zuijiaShijian;
    }

    public void setZuijiaShijian(String zuijiaShijian) {
        this.zuijiaShijian = zuijiaShijian;
    }

    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }

    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }

    public Long getStep7Id() {
        return this.step7Id;
    }

    public void setStep7Id(Long step7Id) {
        this.step7Id = step7Id;
    }

    @Generated(hash = 1760728158)
    public Step7Resbean(Long step7Id, boolean currentStepIsChecked,
            @NotNull String zuijiaShijian, @NotNull String zuijiaSuDu) {
        this.step7Id = step7Id;
        this.currentStepIsChecked = currentStepIsChecked;
        this.zuijiaShijian = zuijiaShijian;
        this.zuijiaSuDu = zuijiaSuDu;
    }

    @Generated(hash = 1846767954)
    public Step7Resbean() {
    }

}
