package com.weimi.wmmess.business.shimu.bean.step4;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.weimi.wmmess.business.shimu.bean.DaoSession;

/**
 * Create by chhyu
 * on 2019/5/14
 * Describle:
 */
@Entity
public class Step4Resbean {

    @Id
    private Long step4Id;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    @ToMany(referencedJoinProperty = "zhuliuDaoId")
    private List<ZhuLiuDaoResbean> zhuLiuDaoResbeans;

    @ToMany(referencedJoinProperty = "kongZhiDianId")
    private List<KongZhiDianResbean> kongZhiDianResbeans;
    
    private String penzui;//喷嘴温度

    private String one; //一段温度

    private String two; //二段温度

    private String three; //三段温度

    private String four; //四段温度

    private String five;  //五段温度

    private String xiaLiaoKou; //下料口

    private String checkTemp; //实际原料熔融温度（用温度测试仪测量）

    private String dingMoSheDing;//定模设定温度

    private String dongMoSheDing;//动模设定温度

    private String huaKuaiSheDing;//滑块设定温度

    private String dingMoShiJi;//定模实际温度

    private String dongMoShiJi;//动模实际温度

    private String huaKuaiShiJi;//滑块实际温度

    private double luoGanZhiJing; //螺杆直径

    private double yuanLiao; //原料

    private double luoGanZhuanSu;//螺杆转速

    private double zhuanFenZhong;//  换算成 转/分钟

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
    @Generated(hash = 1167133032)
    public synchronized void resetKongZhiDianResbeans() {
        kongZhiDianResbeans = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 279901539)
    public List<KongZhiDianResbean> getKongZhiDianResbeans() {
        if (kongZhiDianResbeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KongZhiDianResbeanDao targetDao = daoSession.getKongZhiDianResbeanDao();
            List<KongZhiDianResbean> kongZhiDianResbeansNew = targetDao._queryStep4Resbean_KongZhiDianResbeans(step4Id);
            synchronized (this) {
                if(kongZhiDianResbeans == null) {
                    kongZhiDianResbeans = kongZhiDianResbeansNew;
                }
            }
        }
        return kongZhiDianResbeans;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 893449650)
    public synchronized void resetZhuLiuDaoResbeans() {
        zhuLiuDaoResbeans = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1935412020)
    public List<ZhuLiuDaoResbean> getZhuLiuDaoResbeans() {
        if (zhuLiuDaoResbeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ZhuLiuDaoResbeanDao targetDao = daoSession.getZhuLiuDaoResbeanDao();
            List<ZhuLiuDaoResbean> zhuLiuDaoResbeansNew = targetDao._queryStep4Resbean_ZhuLiuDaoResbeans(step4Id);
            synchronized (this) {
                if(zhuLiuDaoResbeans == null) {
                    zhuLiuDaoResbeans = zhuLiuDaoResbeansNew;
                }
            }
        }
        return zhuLiuDaoResbeans;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 12187167)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStep4ResbeanDao() : null;
    }

    /** Used for active entity operations. */
    @Generated(hash = 1639627825)
    private transient Step4ResbeanDao myDao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public double getZhuanFenZhong() {
        return this.zhuanFenZhong;
    }

    public void setZhuanFenZhong(double zhuanFenZhong) {
        this.zhuanFenZhong = zhuanFenZhong;
    }

    public double getLuoGanZhuanSu() {
        return this.luoGanZhuanSu;
    }

    public void setLuoGanZhuanSu(double luoGanZhuanSu) {
        this.luoGanZhuanSu = luoGanZhuanSu;
    }

    public double getYuanLiao() {
        return this.yuanLiao;
    }

    public void setYuanLiao(double yuanLiao) {
        this.yuanLiao = yuanLiao;
    }

    public double getLuoGanZhiJing() {
        return this.luoGanZhiJing;
    }

    public void setLuoGanZhiJing(double luoGanZhiJing) {
        this.luoGanZhiJing = luoGanZhiJing;
    }

    public String getHuaKuaiShiJi() {
        return this.huaKuaiShiJi;
    }

    public void setHuaKuaiShiJi(String huaKuaiShiJi) {
        this.huaKuaiShiJi = huaKuaiShiJi;
    }

    public String getDongMoShiJi() {
        return this.dongMoShiJi;
    }

    public void setDongMoShiJi(String dongMoShiJi) {
        this.dongMoShiJi = dongMoShiJi;
    }

    public String getDingMoShiJi() {
        return this.dingMoShiJi;
    }

    public void setDingMoShiJi(String dingMoShiJi) {
        this.dingMoShiJi = dingMoShiJi;
    }

    public String getHuaKuaiSheDing() {
        return this.huaKuaiSheDing;
    }

    public void setHuaKuaiSheDing(String huaKuaiSheDing) {
        this.huaKuaiSheDing = huaKuaiSheDing;
    }

    public String getDongMoSheDing() {
        return this.dongMoSheDing;
    }

    public void setDongMoSheDing(String dongMoSheDing) {
        this.dongMoSheDing = dongMoSheDing;
    }

    public String getDingMoSheDing() {
        return this.dingMoSheDing;
    }

    public void setDingMoSheDing(String dingMoSheDing) {
        this.dingMoSheDing = dingMoSheDing;
    }

    public String getCheckTemp() {
        return this.checkTemp;
    }

    public void setCheckTemp(String checkTemp) {
        this.checkTemp = checkTemp;
    }

    public String getXiaLiaoKou() {
        return this.xiaLiaoKou;
    }

    public void setXiaLiaoKou(String xiaLiaoKou) {
        this.xiaLiaoKou = xiaLiaoKou;
    }

    public String getFive() {
        return this.five;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public String getFour() {
        return this.four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getThree() {
        return this.three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getTwo() {
        return this.two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getOne() {
        return this.one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getPenzui() {
        return this.penzui;
    }

    public void setPenzui(String penzui) {
        this.penzui = penzui;
    }

    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }

    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }

    public Long getStep4Id() {
        return this.step4Id;
    }

    public void setStep4Id(Long step4Id) {
        this.step4Id = step4Id;
    }

    @Generated(hash = 1433748149)
    public Step4Resbean(Long step4Id, boolean currentStepIsChecked, String penzui,
            String one, String two, String three, String four, String five,
            String xiaLiaoKou, String checkTemp, String dingMoSheDing,
            String dongMoSheDing, String huaKuaiSheDing, String dingMoShiJi,
            String dongMoShiJi, String huaKuaiShiJi, double luoGanZhiJing,
            double yuanLiao, double luoGanZhuanSu, double zhuanFenZhong) {
        this.step4Id = step4Id;
        this.currentStepIsChecked = currentStepIsChecked;
        this.penzui = penzui;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.five = five;
        this.xiaLiaoKou = xiaLiaoKou;
        this.checkTemp = checkTemp;
        this.dingMoSheDing = dingMoSheDing;
        this.dongMoSheDing = dongMoSheDing;
        this.huaKuaiSheDing = huaKuaiSheDing;
        this.dingMoShiJi = dingMoShiJi;
        this.dongMoShiJi = dongMoShiJi;
        this.huaKuaiShiJi = huaKuaiShiJi;
        this.luoGanZhiJing = luoGanZhiJing;
        this.yuanLiao = yuanLiao;
        this.luoGanZhuanSu = luoGanZhuanSu;
        this.zhuanFenZhong = zhuanFenZhong;
    }

    @Generated(hash = 877636049)
    public Step4Resbean() {
    }


}
