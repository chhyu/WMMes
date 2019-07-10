package com.weimi.wmmess.business.shimu.bean;

import com.weimi.wmmess.business.shimu.bean.problem.ProblemResbean;
import com.weimi.wmmess.business.shimu.bean.step1.Step1Resbean;
import com.weimi.wmmess.business.shimu.bean.step10.Step10Resbean;
import com.weimi.wmmess.business.shimu.bean.step11.Step11Resbean;
import com.weimi.wmmess.business.shimu.bean.step12.Step12ResBean;
import com.weimi.wmmess.business.shimu.bean.step13.Step13Resbean;
import com.weimi.wmmess.business.shimu.bean.step2.Step2Resbean;
import com.weimi.wmmess.business.shimu.bean.step3.Step3MuResBean;
import com.weimi.wmmess.business.shimu.bean.step4.Step4Resbean;
import com.weimi.wmmess.business.shimu.bean.step5.Step5Resbean;
import com.weimi.wmmess.business.shimu.bean.step6.Step6Resbean;
import com.weimi.wmmess.business.shimu.bean.step7.Step7Resbean;
import com.weimi.wmmess.business.shimu.bean.step8.Step8Resbean;
import com.weimi.wmmess.business.shimu.bean.step9.Step9Resbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.weimi.wmmess.business.shimu.bean.step13.Step13ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step12.Step12ResBeanDao;
import com.weimi.wmmess.business.shimu.bean.step11.Step11ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step9.Step9ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step7.Step7ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step6.Step6ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step5.Step5ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step4.Step4ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.Step3MuResBeanDao;
import com.weimi.wmmess.business.shimu.bean.step2.Step2ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step1.Step1ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step8.Step8ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step10.Step10ResbeanDao;

/**
 * Create by chhyu
 * on 2019/5/10
 * Describle:所有数据（其它每个步骤的数据都与该类一对一关联）
 */
@Entity
public class ShiMuResbean {

    @Id(autoincrement = true)
    private Long id;

    /**
     * 当前时间
     */
    private String currentTime;

    /**
     * 是否已经完成
     */
    private boolean isComplete;

    private Long step1Id;
    private Long step2Id;
    private Long step3Id;
    private Long step4Id;
    private Long step5Id;
    private Long step6Id;
    private Long step7Id;
    private Long step8Id;
    private Long step9Id;
    private Long step10Id;
    private Long step11Id;
    private Long step12Id;
    private Long step13Id;
    private Long headInfoId;
    private Long problemId;


    @ToOne(joinProperty = "problemId")
    private ProblemResbean problemResbean;

    @ToOne(joinProperty = "headInfoId")
    private HeadInfoResbean headInfoResbean;

    @ToOne(joinProperty = "step1Id")
    private Step1Resbean step1Resbean;

    @ToOne(joinProperty = "step2Id")
    private Step2Resbean step2Resbean;

    @ToOne(joinProperty = "step3Id")
    private Step3MuResBean step3MuResBean;

    @ToOne(joinProperty = "step4Id")
    private Step4Resbean step4Resbean;

    @ToOne(joinProperty = "step5Id")
    private Step5Resbean step5Resbean;

    @ToOne(joinProperty = "step6Id")
    private Step6Resbean step6Resbean;

    @ToOne(joinProperty = "step7Id")
    private Step7Resbean step7Resbean;

    @ToOne(joinProperty = "step8Id")
    private Step8Resbean step8Resbean;

    @ToOne(joinProperty = "step9Id")
    private Step9Resbean step9Resbean;

    @ToOne(joinProperty = "step10Id")
    private Step10Resbean step10Resbean;

    @ToOne(joinProperty = "step11Id")
    private Step11Resbean step11Resbean;

    @ToOne(joinProperty = "step12Id")
    private Step12ResBean step12Resbean;

    @ToOne(joinProperty = "step13Id")
    private Step13Resbean step13Resbean;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1103170427)
    private transient ShiMuResbeanDao myDao;

    @Generated(hash = 1653505865)
    public ShiMuResbean(Long id, String currentTime, boolean isComplete,
            Long step1Id, Long step2Id, Long step3Id, Long step4Id, Long step5Id,
            Long step6Id, Long step7Id, Long step8Id, Long step9Id, Long step10Id,
            Long step11Id, Long step12Id, Long step13Id, Long headInfoId,
            Long problemId) {
        this.id = id;
        this.currentTime = currentTime;
        this.isComplete = isComplete;
        this.step1Id = step1Id;
        this.step2Id = step2Id;
        this.step3Id = step3Id;
        this.step4Id = step4Id;
        this.step5Id = step5Id;
        this.step6Id = step6Id;
        this.step7Id = step7Id;
        this.step8Id = step8Id;
        this.step9Id = step9Id;
        this.step10Id = step10Id;
        this.step11Id = step11Id;
        this.step12Id = step12Id;
        this.step13Id = step13Id;
        this.headInfoId = headInfoId;
        this.problemId = problemId;
    }

    @Generated(hash = 1841214813)
    public ShiMuResbean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public Long getStep1Id() {
        return this.step1Id;
    }

    public void setStep1Id(Long step1Id) {
        this.step1Id = step1Id;
    }

    public Long getStep2Id() {
        return this.step2Id;
    }

    public void setStep2Id(Long step2Id) {
        this.step2Id = step2Id;
    }

    public Long getStep3Id() {
        return this.step3Id;
    }

    public void setStep3Id(Long step3Id) {
        this.step3Id = step3Id;
    }

    public Long getStep4Id() {
        return this.step4Id;
    }

    public void setStep4Id(Long step4Id) {
        this.step4Id = step4Id;
    }

    public Long getStep5Id() {
        return this.step5Id;
    }

    public void setStep5Id(Long step5Id) {
        this.step5Id = step5Id;
    }

    public Long getStep6Id() {
        return this.step6Id;
    }

    public void setStep6Id(Long step6Id) {
        this.step6Id = step6Id;
    }

    public Long getStep7Id() {
        return this.step7Id;
    }

    public void setStep7Id(Long step7Id) {
        this.step7Id = step7Id;
    }

    public Long getStep8Id() {
        return this.step8Id;
    }

    public void setStep8Id(Long step8Id) {
        this.step8Id = step8Id;
    }

    public Long getStep9Id() {
        return this.step9Id;
    }

    public void setStep9Id(Long step9Id) {
        this.step9Id = step9Id;
    }

    public Long getStep10Id() {
        return this.step10Id;
    }

    public void setStep10Id(Long step10Id) {
        this.step10Id = step10Id;
    }

    public Long getStep11Id() {
        return this.step11Id;
    }

    public void setStep11Id(Long step11Id) {
        this.step11Id = step11Id;
    }

    public Long getStep12Id() {
        return this.step12Id;
    }

    public void setStep12Id(Long step12Id) {
        this.step12Id = step12Id;
    }

    public Long getStep13Id() {
        return this.step13Id;
    }

    public void setStep13Id(Long step13Id) {
        this.step13Id = step13Id;
    }

    public Long getHeadInfoId() {
        return this.headInfoId;
    }

    public void setHeadInfoId(Long headInfoId) {
        this.headInfoId = headInfoId;
    }

    public Long getProblemId() {
        return this.problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    @Generated(hash = 981893807)
    private transient Long problemResbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1409374488)
    public ProblemResbean getProblemResbean() {
        Long __key = this.problemId;
        if (problemResbean__resolvedKey == null
                || !problemResbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ProblemResbeanDao targetDao = daoSession.getProblemResbeanDao();
            ProblemResbean problemResbeanNew = targetDao.load(__key);
            synchronized (this) {
                problemResbean = problemResbeanNew;
                problemResbean__resolvedKey = __key;
            }
        }
        return problemResbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 575139325)
    public void setProblemResbean(ProblemResbean problemResbean) {
        synchronized (this) {
            this.problemResbean = problemResbean;
            problemId = problemResbean == null ? null
                    : problemResbean.getProblemId();
            problemResbean__resolvedKey = problemId;
        }
    }

    @Generated(hash = 570317223)
    private transient Long headInfoResbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1901356822)
    public HeadInfoResbean getHeadInfoResbean() {
        Long __key = this.headInfoId;
        if (headInfoResbean__resolvedKey == null
                || !headInfoResbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HeadInfoResbeanDao targetDao = daoSession.getHeadInfoResbeanDao();
            HeadInfoResbean headInfoResbeanNew = targetDao.load(__key);
            synchronized (this) {
                headInfoResbean = headInfoResbeanNew;
                headInfoResbean__resolvedKey = __key;
            }
        }
        return headInfoResbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1526005419)
    public void setHeadInfoResbean(HeadInfoResbean headInfoResbean) {
        synchronized (this) {
            this.headInfoResbean = headInfoResbean;
            headInfoId = headInfoResbean == null ? null
                    : headInfoResbean.getHeadInfoId();
            headInfoResbean__resolvedKey = headInfoId;
        }
    }

    @Generated(hash = 977493038)
    private transient Long step1Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 447318354)
    public Step1Resbean getStep1Resbean() {
        Long __key = this.step1Id;
        if (step1Resbean__resolvedKey == null
                || !step1Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step1ResbeanDao targetDao = daoSession.getStep1ResbeanDao();
            Step1Resbean step1ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step1Resbean = step1ResbeanNew;
                step1Resbean__resolvedKey = __key;
            }
        }
        return step1Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 59854944)
    public void setStep1Resbean(Step1Resbean step1Resbean) {
        synchronized (this) {
            this.step1Resbean = step1Resbean;
            step1Id = step1Resbean == null ? null
                    : step1Resbean.getStep1ResbeanId();
            step1Resbean__resolvedKey = step1Id;
        }
    }

    @Generated(hash = 1649768886)
    private transient Long step2Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1895730841)
    public Step2Resbean getStep2Resbean() {
        Long __key = this.step2Id;
        if (step2Resbean__resolvedKey == null
                || !step2Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step2ResbeanDao targetDao = daoSession.getStep2ResbeanDao();
            Step2Resbean step2ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step2Resbean = step2ResbeanNew;
                step2Resbean__resolvedKey = __key;
            }
        }
        return step2Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 913893068)
    public void setStep2Resbean(Step2Resbean step2Resbean) {
        synchronized (this) {
            this.step2Resbean = step2Resbean;
            step2Id = step2Resbean == null ? null : step2Resbean.getStep2Id();
            step2Resbean__resolvedKey = step2Id;
        }
    }

    @Generated(hash = 1030465789)
    private transient Long step3MuResBean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1864566213)
    public Step3MuResBean getStep3MuResBean() {
        Long __key = this.step3Id;
        if (step3MuResBean__resolvedKey == null
                || !step3MuResBean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step3MuResBeanDao targetDao = daoSession.getStep3MuResBeanDao();
            Step3MuResBean step3MuResBeanNew = targetDao.load(__key);
            synchronized (this) {
                step3MuResBean = step3MuResBeanNew;
                step3MuResBean__resolvedKey = __key;
            }
        }
        return step3MuResBean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 125143455)
    public void setStep3MuResBean(Step3MuResBean step3MuResBean) {
        synchronized (this) {
            this.step3MuResBean = step3MuResBean;
            step3Id = step3MuResBean == null ? null : step3MuResBean.getStep3Id();
            step3MuResBean__resolvedKey = step3Id;
        }
    }

    @Generated(hash = 1329839094)
    private transient Long step4Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1791750726)
    public Step4Resbean getStep4Resbean() {
        Long __key = this.step4Id;
        if (step4Resbean__resolvedKey == null
                || !step4Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step4ResbeanDao targetDao = daoSession.getStep4ResbeanDao();
            Step4Resbean step4ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step4Resbean = step4ResbeanNew;
                step4Resbean__resolvedKey = __key;
            }
        }
        return step4Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 857963792)
    public void setStep4Resbean(Step4Resbean step4Resbean) {
        synchronized (this) {
            this.step4Resbean = step4Resbean;
            step4Id = step4Resbean == null ? null : step4Resbean.getStep4Id();
            step4Resbean__resolvedKey = step4Id;
        }
    }

    @Generated(hash = 201093301)
    private transient Long step5Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 796319471)
    public Step5Resbean getStep5Resbean() {
        Long __key = this.step5Id;
        if (step5Resbean__resolvedKey == null
                || !step5Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step5ResbeanDao targetDao = daoSession.getStep5ResbeanDao();
            Step5Resbean step5ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step5Resbean = step5ResbeanNew;
                step5Resbean__resolvedKey = __key;
            }
        }
        return step5Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 182974060)
    public void setStep5Resbean(Step5Resbean step5Resbean) {
        synchronized (this) {
            this.step5Resbean = step5Resbean;
            step5Id = step5Resbean == null ? null : step5Resbean.getStep5Id();
            step5Resbean__resolvedKey = step5Id;
        }
    }

    @Generated(hash = 1356230974)
    private transient Long step6Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 163039485)
    public Step6Resbean getStep6Resbean() {
        Long __key = this.step6Id;
        if (step6Resbean__resolvedKey == null
                || !step6Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step6ResbeanDao targetDao = daoSession.getStep6ResbeanDao();
            Step6Resbean step6ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step6Resbean = step6ResbeanNew;
                step6Resbean__resolvedKey = __key;
            }
        }
        return step6Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1643225493)
    public void setStep6Resbean(Step6Resbean step6Resbean) {
        synchronized (this) {
            this.step6Resbean = step6Resbean;
            step6Id = step6Resbean == null ? null : step6Resbean.getStep6Id();
            step6Resbean__resolvedKey = step6Id;
        }
    }

    @Generated(hash = 624996514)
    private transient Long step7Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1314823041)
    public Step7Resbean getStep7Resbean() {
        Long __key = this.step7Id;
        if (step7Resbean__resolvedKey == null
                || !step7Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step7ResbeanDao targetDao = daoSession.getStep7ResbeanDao();
            Step7Resbean step7ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step7Resbean = step7ResbeanNew;
                step7Resbean__resolvedKey = __key;
            }
        }
        return step7Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 255036547)
    public void setStep7Resbean(Step7Resbean step7Resbean) {
        synchronized (this) {
            this.step7Resbean = step7Resbean;
            step7Id = step7Resbean == null ? null : step7Resbean.getStep7Id();
            step7Resbean__resolvedKey = step7Id;
        }
    }

    @Generated(hash = 1466266612)
    private transient Long step8Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 297642733)
    public Step8Resbean getStep8Resbean() {
        Long __key = this.step8Id;
        if (step8Resbean__resolvedKey == null
                || !step8Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step8ResbeanDao targetDao = daoSession.getStep8ResbeanDao();
            Step8Resbean step8ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step8Resbean = step8ResbeanNew;
                step8Resbean__resolvedKey = __key;
            }
        }
        return step8Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1831224869)
    public void setStep8Resbean(Step8Resbean step8Resbean) {
        synchronized (this) {
            this.step8Resbean = step8Resbean;
            step8Id = step8Resbean == null ? null : step8Resbean.getStep8Id();
            step8Resbean__resolvedKey = step8Id;
        }
    }

    @Generated(hash = 1643010141)
    private transient Long step9Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 2129495209)
    public Step9Resbean getStep9Resbean() {
        Long __key = this.step9Id;
        if (step9Resbean__resolvedKey == null
                || !step9Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step9ResbeanDao targetDao = daoSession.getStep9ResbeanDao();
            Step9Resbean step9ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step9Resbean = step9ResbeanNew;
                step9Resbean__resolvedKey = __key;
            }
        }
        return step9Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 507859944)
    public void setStep9Resbean(Step9Resbean step9Resbean) {
        synchronized (this) {
            this.step9Resbean = step9Resbean;
            step9Id = step9Resbean == null ? null : step9Resbean.getStep9Id();
            step9Resbean__resolvedKey = step9Id;
        }
    }

    @Generated(hash = 889757403)
    private transient Long step10Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 797468180)
    public Step10Resbean getStep10Resbean() {
        Long __key = this.step10Id;
        if (step10Resbean__resolvedKey == null
                || !step10Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step10ResbeanDao targetDao = daoSession.getStep10ResbeanDao();
            Step10Resbean step10ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step10Resbean = step10ResbeanNew;
                step10Resbean__resolvedKey = __key;
            }
        }
        return step10Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2085102454)
    public void setStep10Resbean(Step10Resbean step10Resbean) {
        synchronized (this) {
            this.step10Resbean = step10Resbean;
            step10Id = step10Resbean == null ? null : step10Resbean.getStep10Id();
            step10Resbean__resolvedKey = step10Id;
        }
    }

    @Generated(hash = 1774813140)
    private transient Long step11Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 142424029)
    public Step11Resbean getStep11Resbean() {
        Long __key = this.step11Id;
        if (step11Resbean__resolvedKey == null
                || !step11Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step11ResbeanDao targetDao = daoSession.getStep11ResbeanDao();
            Step11Resbean step11ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step11Resbean = step11ResbeanNew;
                step11Resbean__resolvedKey = __key;
            }
        }
        return step11Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2143980781)
    public void setStep11Resbean(Step11Resbean step11Resbean) {
        synchronized (this) {
            this.step11Resbean = step11Resbean;
            step11Id = step11Resbean == null ? null : step11Resbean.getStep11Id();
            step11Resbean__resolvedKey = step11Id;
        }
    }

    @Generated(hash = 1474303286)
    private transient Long step12Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 166315020)
    public Step12ResBean getStep12Resbean() {
        Long __key = this.step12Id;
        if (step12Resbean__resolvedKey == null
                || !step12Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step12ResBeanDao targetDao = daoSession.getStep12ResBeanDao();
            Step12ResBean step12ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step12Resbean = step12ResbeanNew;
                step12Resbean__resolvedKey = __key;
            }
        }
        return step12Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2069140)
    public void setStep12Resbean(Step12ResBean step12Resbean) {
        synchronized (this) {
            this.step12Resbean = step12Resbean;
            step12Id = step12Resbean == null ? null : step12Resbean.getStep12Id();
            step12Resbean__resolvedKey = step12Id;
        }
    }

    @Generated(hash = 1099145411)
    private transient Long step13Resbean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 276171814)
    public Step13Resbean getStep13Resbean() {
        Long __key = this.step13Id;
        if (step13Resbean__resolvedKey == null
                || !step13Resbean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Step13ResbeanDao targetDao = daoSession.getStep13ResbeanDao();
            Step13Resbean step13ResbeanNew = targetDao.load(__key);
            synchronized (this) {
                step13Resbean = step13ResbeanNew;
                step13Resbean__resolvedKey = __key;
            }
        }
        return step13Resbean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1067061826)
    public void setStep13Resbean(Step13Resbean step13Resbean) {
        synchronized (this) {
            this.step13Resbean = step13Resbean;
            step13Id = step13Resbean == null ? null : step13Resbean.getStep13Id();
            step13Resbean__resolvedKey = step13Id;
        }
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
    @Generated(hash = 1428687453)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getShiMuResbeanDao() : null;
    }

 
}
