package com.weimi.wmmess.business.shimu.bean;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.weimi.wmmess.business.shimu.bean.HeadInfoResbean;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemItemResbean;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemResbean;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbean;
import com.weimi.wmmess.business.shimu.bean.step1.Step1Resbean;
import com.weimi.wmmess.business.shimu.bean.step10.Step10Resbean;
import com.weimi.wmmess.business.shimu.bean.step10.YaLiTestResbean;
import com.weimi.wmmess.business.shimu.bean.step11.Step11Resbean;
import com.weimi.wmmess.business.shimu.bean.step12.Step12ResBean;
import com.weimi.wmmess.business.shimu.bean.step13.Step13Resbean;
import com.weimi.wmmess.business.shimu.bean.step2.Step2Resbean;
import com.weimi.wmmess.business.shimu.bean.step3.FixSideResbean;
import com.weimi.wmmess.business.shimu.bean.step3.MoveSideResbean;
import com.weimi.wmmess.business.shimu.bean.step3.SlideResbean;
import com.weimi.wmmess.business.shimu.bean.step3.Step3MuResBean;
import com.weimi.wmmess.business.shimu.bean.step3.Step3ShuiLuDate;
import com.weimi.wmmess.business.shimu.bean.step4.KongZhiDianResbean;
import com.weimi.wmmess.business.shimu.bean.step4.Step4Resbean;
import com.weimi.wmmess.business.shimu.bean.step4.ZhuLiuDaoResbean;
import com.weimi.wmmess.business.shimu.bean.step5.Step5Resbean;
import com.weimi.wmmess.business.shimu.bean.step6.Step6Resbean;
import com.weimi.wmmess.business.shimu.bean.step7.Step7Resbean;
import com.weimi.wmmess.business.shimu.bean.step7.ZhuSuResbean;
import com.weimi.wmmess.business.shimu.bean.step8.ChongTianPingHengResbean;
import com.weimi.wmmess.business.shimu.bean.step8.Step8Resbean;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaTimeResbean;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaYaLiResbean;
import com.weimi.wmmess.business.shimu.bean.step9.Step9Resbean;

import com.weimi.wmmess.business.shimu.bean.HeadInfoResbeanDao;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemItemResbeanDao;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemResbeanDao;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step1.Step1ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step10.Step10ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step10.YaLiTestResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step11.Step11ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step12.Step12ResBeanDao;
import com.weimi.wmmess.business.shimu.bean.step13.Step13ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step2.Step2ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.FixSideResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.MoveSideResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.SlideResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.Step3MuResBeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.Step3ShuiLuDateDao;
import com.weimi.wmmess.business.shimu.bean.step4.KongZhiDianResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step4.Step4ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step4.ZhuLiuDaoResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step5.Step5ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step6.Step6ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step7.Step7ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step7.ZhuSuResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step8.ChongTianPingHengResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step8.Step8ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaTimeResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaYaLiResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step9.Step9ResbeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig headInfoResbeanDaoConfig;
    private final DaoConfig problemItemResbeanDaoConfig;
    private final DaoConfig problemResbeanDaoConfig;
    private final DaoConfig shiMuResbeanDaoConfig;
    private final DaoConfig step1ResbeanDaoConfig;
    private final DaoConfig step10ResbeanDaoConfig;
    private final DaoConfig yaLiTestResbeanDaoConfig;
    private final DaoConfig step11ResbeanDaoConfig;
    private final DaoConfig step12ResBeanDaoConfig;
    private final DaoConfig step13ResbeanDaoConfig;
    private final DaoConfig step2ResbeanDaoConfig;
    private final DaoConfig fixSideResbeanDaoConfig;
    private final DaoConfig moveSideResbeanDaoConfig;
    private final DaoConfig slideResbeanDaoConfig;
    private final DaoConfig step3MuResBeanDaoConfig;
    private final DaoConfig step3ShuiLuDateDaoConfig;
    private final DaoConfig kongZhiDianResbeanDaoConfig;
    private final DaoConfig step4ResbeanDaoConfig;
    private final DaoConfig zhuLiuDaoResbeanDaoConfig;
    private final DaoConfig step5ResbeanDaoConfig;
    private final DaoConfig step6ResbeanDaoConfig;
    private final DaoConfig step7ResbeanDaoConfig;
    private final DaoConfig zhuSuResbeanDaoConfig;
    private final DaoConfig chongTianPingHengResbeanDaoConfig;
    private final DaoConfig step8ResbeanDaoConfig;
    private final DaoConfig baoYaTimeResbeanDaoConfig;
    private final DaoConfig baoYaYaLiResbeanDaoConfig;
    private final DaoConfig step9ResbeanDaoConfig;

    private final HeadInfoResbeanDao headInfoResbeanDao;
    private final ProblemItemResbeanDao problemItemResbeanDao;
    private final ProblemResbeanDao problemResbeanDao;
    private final ShiMuResbeanDao shiMuResbeanDao;
    private final Step1ResbeanDao step1ResbeanDao;
    private final Step10ResbeanDao step10ResbeanDao;
    private final YaLiTestResbeanDao yaLiTestResbeanDao;
    private final Step11ResbeanDao step11ResbeanDao;
    private final Step12ResBeanDao step12ResBeanDao;
    private final Step13ResbeanDao step13ResbeanDao;
    private final Step2ResbeanDao step2ResbeanDao;
    private final FixSideResbeanDao fixSideResbeanDao;
    private final MoveSideResbeanDao moveSideResbeanDao;
    private final SlideResbeanDao slideResbeanDao;
    private final Step3MuResBeanDao step3MuResBeanDao;
    private final Step3ShuiLuDateDao step3ShuiLuDateDao;
    private final KongZhiDianResbeanDao kongZhiDianResbeanDao;
    private final Step4ResbeanDao step4ResbeanDao;
    private final ZhuLiuDaoResbeanDao zhuLiuDaoResbeanDao;
    private final Step5ResbeanDao step5ResbeanDao;
    private final Step6ResbeanDao step6ResbeanDao;
    private final Step7ResbeanDao step7ResbeanDao;
    private final ZhuSuResbeanDao zhuSuResbeanDao;
    private final ChongTianPingHengResbeanDao chongTianPingHengResbeanDao;
    private final Step8ResbeanDao step8ResbeanDao;
    private final BaoYaTimeResbeanDao baoYaTimeResbeanDao;
    private final BaoYaYaLiResbeanDao baoYaYaLiResbeanDao;
    private final Step9ResbeanDao step9ResbeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        headInfoResbeanDaoConfig = daoConfigMap.get(HeadInfoResbeanDao.class).clone();
        headInfoResbeanDaoConfig.initIdentityScope(type);

        problemItemResbeanDaoConfig = daoConfigMap.get(ProblemItemResbeanDao.class).clone();
        problemItemResbeanDaoConfig.initIdentityScope(type);

        problemResbeanDaoConfig = daoConfigMap.get(ProblemResbeanDao.class).clone();
        problemResbeanDaoConfig.initIdentityScope(type);

        shiMuResbeanDaoConfig = daoConfigMap.get(ShiMuResbeanDao.class).clone();
        shiMuResbeanDaoConfig.initIdentityScope(type);

        step1ResbeanDaoConfig = daoConfigMap.get(Step1ResbeanDao.class).clone();
        step1ResbeanDaoConfig.initIdentityScope(type);

        step10ResbeanDaoConfig = daoConfigMap.get(Step10ResbeanDao.class).clone();
        step10ResbeanDaoConfig.initIdentityScope(type);

        yaLiTestResbeanDaoConfig = daoConfigMap.get(YaLiTestResbeanDao.class).clone();
        yaLiTestResbeanDaoConfig.initIdentityScope(type);

        step11ResbeanDaoConfig = daoConfigMap.get(Step11ResbeanDao.class).clone();
        step11ResbeanDaoConfig.initIdentityScope(type);

        step12ResBeanDaoConfig = daoConfigMap.get(Step12ResBeanDao.class).clone();
        step12ResBeanDaoConfig.initIdentityScope(type);

        step13ResbeanDaoConfig = daoConfigMap.get(Step13ResbeanDao.class).clone();
        step13ResbeanDaoConfig.initIdentityScope(type);

        step2ResbeanDaoConfig = daoConfigMap.get(Step2ResbeanDao.class).clone();
        step2ResbeanDaoConfig.initIdentityScope(type);

        fixSideResbeanDaoConfig = daoConfigMap.get(FixSideResbeanDao.class).clone();
        fixSideResbeanDaoConfig.initIdentityScope(type);

        moveSideResbeanDaoConfig = daoConfigMap.get(MoveSideResbeanDao.class).clone();
        moveSideResbeanDaoConfig.initIdentityScope(type);

        slideResbeanDaoConfig = daoConfigMap.get(SlideResbeanDao.class).clone();
        slideResbeanDaoConfig.initIdentityScope(type);

        step3MuResBeanDaoConfig = daoConfigMap.get(Step3MuResBeanDao.class).clone();
        step3MuResBeanDaoConfig.initIdentityScope(type);

        step3ShuiLuDateDaoConfig = daoConfigMap.get(Step3ShuiLuDateDao.class).clone();
        step3ShuiLuDateDaoConfig.initIdentityScope(type);

        kongZhiDianResbeanDaoConfig = daoConfigMap.get(KongZhiDianResbeanDao.class).clone();
        kongZhiDianResbeanDaoConfig.initIdentityScope(type);

        step4ResbeanDaoConfig = daoConfigMap.get(Step4ResbeanDao.class).clone();
        step4ResbeanDaoConfig.initIdentityScope(type);

        zhuLiuDaoResbeanDaoConfig = daoConfigMap.get(ZhuLiuDaoResbeanDao.class).clone();
        zhuLiuDaoResbeanDaoConfig.initIdentityScope(type);

        step5ResbeanDaoConfig = daoConfigMap.get(Step5ResbeanDao.class).clone();
        step5ResbeanDaoConfig.initIdentityScope(type);

        step6ResbeanDaoConfig = daoConfigMap.get(Step6ResbeanDao.class).clone();
        step6ResbeanDaoConfig.initIdentityScope(type);

        step7ResbeanDaoConfig = daoConfigMap.get(Step7ResbeanDao.class).clone();
        step7ResbeanDaoConfig.initIdentityScope(type);

        zhuSuResbeanDaoConfig = daoConfigMap.get(ZhuSuResbeanDao.class).clone();
        zhuSuResbeanDaoConfig.initIdentityScope(type);

        chongTianPingHengResbeanDaoConfig = daoConfigMap.get(ChongTianPingHengResbeanDao.class).clone();
        chongTianPingHengResbeanDaoConfig.initIdentityScope(type);

        step8ResbeanDaoConfig = daoConfigMap.get(Step8ResbeanDao.class).clone();
        step8ResbeanDaoConfig.initIdentityScope(type);

        baoYaTimeResbeanDaoConfig = daoConfigMap.get(BaoYaTimeResbeanDao.class).clone();
        baoYaTimeResbeanDaoConfig.initIdentityScope(type);

        baoYaYaLiResbeanDaoConfig = daoConfigMap.get(BaoYaYaLiResbeanDao.class).clone();
        baoYaYaLiResbeanDaoConfig.initIdentityScope(type);

        step9ResbeanDaoConfig = daoConfigMap.get(Step9ResbeanDao.class).clone();
        step9ResbeanDaoConfig.initIdentityScope(type);

        headInfoResbeanDao = new HeadInfoResbeanDao(headInfoResbeanDaoConfig, this);
        problemItemResbeanDao = new ProblemItemResbeanDao(problemItemResbeanDaoConfig, this);
        problemResbeanDao = new ProblemResbeanDao(problemResbeanDaoConfig, this);
        shiMuResbeanDao = new ShiMuResbeanDao(shiMuResbeanDaoConfig, this);
        step1ResbeanDao = new Step1ResbeanDao(step1ResbeanDaoConfig, this);
        step10ResbeanDao = new Step10ResbeanDao(step10ResbeanDaoConfig, this);
        yaLiTestResbeanDao = new YaLiTestResbeanDao(yaLiTestResbeanDaoConfig, this);
        step11ResbeanDao = new Step11ResbeanDao(step11ResbeanDaoConfig, this);
        step12ResBeanDao = new Step12ResBeanDao(step12ResBeanDaoConfig, this);
        step13ResbeanDao = new Step13ResbeanDao(step13ResbeanDaoConfig, this);
        step2ResbeanDao = new Step2ResbeanDao(step2ResbeanDaoConfig, this);
        fixSideResbeanDao = new FixSideResbeanDao(fixSideResbeanDaoConfig, this);
        moveSideResbeanDao = new MoveSideResbeanDao(moveSideResbeanDaoConfig, this);
        slideResbeanDao = new SlideResbeanDao(slideResbeanDaoConfig, this);
        step3MuResBeanDao = new Step3MuResBeanDao(step3MuResBeanDaoConfig, this);
        step3ShuiLuDateDao = new Step3ShuiLuDateDao(step3ShuiLuDateDaoConfig, this);
        kongZhiDianResbeanDao = new KongZhiDianResbeanDao(kongZhiDianResbeanDaoConfig, this);
        step4ResbeanDao = new Step4ResbeanDao(step4ResbeanDaoConfig, this);
        zhuLiuDaoResbeanDao = new ZhuLiuDaoResbeanDao(zhuLiuDaoResbeanDaoConfig, this);
        step5ResbeanDao = new Step5ResbeanDao(step5ResbeanDaoConfig, this);
        step6ResbeanDao = new Step6ResbeanDao(step6ResbeanDaoConfig, this);
        step7ResbeanDao = new Step7ResbeanDao(step7ResbeanDaoConfig, this);
        zhuSuResbeanDao = new ZhuSuResbeanDao(zhuSuResbeanDaoConfig, this);
        chongTianPingHengResbeanDao = new ChongTianPingHengResbeanDao(chongTianPingHengResbeanDaoConfig, this);
        step8ResbeanDao = new Step8ResbeanDao(step8ResbeanDaoConfig, this);
        baoYaTimeResbeanDao = new BaoYaTimeResbeanDao(baoYaTimeResbeanDaoConfig, this);
        baoYaYaLiResbeanDao = new BaoYaYaLiResbeanDao(baoYaYaLiResbeanDaoConfig, this);
        step9ResbeanDao = new Step9ResbeanDao(step9ResbeanDaoConfig, this);

        registerDao(HeadInfoResbean.class, headInfoResbeanDao);
        registerDao(ProblemItemResbean.class, problemItemResbeanDao);
        registerDao(ProblemResbean.class, problemResbeanDao);
        registerDao(ShiMuResbean.class, shiMuResbeanDao);
        registerDao(Step1Resbean.class, step1ResbeanDao);
        registerDao(Step10Resbean.class, step10ResbeanDao);
        registerDao(YaLiTestResbean.class, yaLiTestResbeanDao);
        registerDao(Step11Resbean.class, step11ResbeanDao);
        registerDao(Step12ResBean.class, step12ResBeanDao);
        registerDao(Step13Resbean.class, step13ResbeanDao);
        registerDao(Step2Resbean.class, step2ResbeanDao);
        registerDao(FixSideResbean.class, fixSideResbeanDao);
        registerDao(MoveSideResbean.class, moveSideResbeanDao);
        registerDao(SlideResbean.class, slideResbeanDao);
        registerDao(Step3MuResBean.class, step3MuResBeanDao);
        registerDao(Step3ShuiLuDate.class, step3ShuiLuDateDao);
        registerDao(KongZhiDianResbean.class, kongZhiDianResbeanDao);
        registerDao(Step4Resbean.class, step4ResbeanDao);
        registerDao(ZhuLiuDaoResbean.class, zhuLiuDaoResbeanDao);
        registerDao(Step5Resbean.class, step5ResbeanDao);
        registerDao(Step6Resbean.class, step6ResbeanDao);
        registerDao(Step7Resbean.class, step7ResbeanDao);
        registerDao(ZhuSuResbean.class, zhuSuResbeanDao);
        registerDao(ChongTianPingHengResbean.class, chongTianPingHengResbeanDao);
        registerDao(Step8Resbean.class, step8ResbeanDao);
        registerDao(BaoYaTimeResbean.class, baoYaTimeResbeanDao);
        registerDao(BaoYaYaLiResbean.class, baoYaYaLiResbeanDao);
        registerDao(Step9Resbean.class, step9ResbeanDao);
    }
    
    public void clear() {
        headInfoResbeanDaoConfig.clearIdentityScope();
        problemItemResbeanDaoConfig.clearIdentityScope();
        problemResbeanDaoConfig.clearIdentityScope();
        shiMuResbeanDaoConfig.clearIdentityScope();
        step1ResbeanDaoConfig.clearIdentityScope();
        step10ResbeanDaoConfig.clearIdentityScope();
        yaLiTestResbeanDaoConfig.clearIdentityScope();
        step11ResbeanDaoConfig.clearIdentityScope();
        step12ResBeanDaoConfig.clearIdentityScope();
        step13ResbeanDaoConfig.clearIdentityScope();
        step2ResbeanDaoConfig.clearIdentityScope();
        fixSideResbeanDaoConfig.clearIdentityScope();
        moveSideResbeanDaoConfig.clearIdentityScope();
        slideResbeanDaoConfig.clearIdentityScope();
        step3MuResBeanDaoConfig.clearIdentityScope();
        step3ShuiLuDateDaoConfig.clearIdentityScope();
        kongZhiDianResbeanDaoConfig.clearIdentityScope();
        step4ResbeanDaoConfig.clearIdentityScope();
        zhuLiuDaoResbeanDaoConfig.clearIdentityScope();
        step5ResbeanDaoConfig.clearIdentityScope();
        step6ResbeanDaoConfig.clearIdentityScope();
        step7ResbeanDaoConfig.clearIdentityScope();
        zhuSuResbeanDaoConfig.clearIdentityScope();
        chongTianPingHengResbeanDaoConfig.clearIdentityScope();
        step8ResbeanDaoConfig.clearIdentityScope();
        baoYaTimeResbeanDaoConfig.clearIdentityScope();
        baoYaYaLiResbeanDaoConfig.clearIdentityScope();
        step9ResbeanDaoConfig.clearIdentityScope();
    }

    public HeadInfoResbeanDao getHeadInfoResbeanDao() {
        return headInfoResbeanDao;
    }

    public ProblemItemResbeanDao getProblemItemResbeanDao() {
        return problemItemResbeanDao;
    }

    public ProblemResbeanDao getProblemResbeanDao() {
        return problemResbeanDao;
    }

    public ShiMuResbeanDao getShiMuResbeanDao() {
        return shiMuResbeanDao;
    }

    public Step1ResbeanDao getStep1ResbeanDao() {
        return step1ResbeanDao;
    }

    public Step10ResbeanDao getStep10ResbeanDao() {
        return step10ResbeanDao;
    }

    public YaLiTestResbeanDao getYaLiTestResbeanDao() {
        return yaLiTestResbeanDao;
    }

    public Step11ResbeanDao getStep11ResbeanDao() {
        return step11ResbeanDao;
    }

    public Step12ResBeanDao getStep12ResBeanDao() {
        return step12ResBeanDao;
    }

    public Step13ResbeanDao getStep13ResbeanDao() {
        return step13ResbeanDao;
    }

    public Step2ResbeanDao getStep2ResbeanDao() {
        return step2ResbeanDao;
    }

    public FixSideResbeanDao getFixSideResbeanDao() {
        return fixSideResbeanDao;
    }

    public MoveSideResbeanDao getMoveSideResbeanDao() {
        return moveSideResbeanDao;
    }

    public SlideResbeanDao getSlideResbeanDao() {
        return slideResbeanDao;
    }

    public Step3MuResBeanDao getStep3MuResBeanDao() {
        return step3MuResBeanDao;
    }

    public Step3ShuiLuDateDao getStep3ShuiLuDateDao() {
        return step3ShuiLuDateDao;
    }

    public KongZhiDianResbeanDao getKongZhiDianResbeanDao() {
        return kongZhiDianResbeanDao;
    }

    public Step4ResbeanDao getStep4ResbeanDao() {
        return step4ResbeanDao;
    }

    public ZhuLiuDaoResbeanDao getZhuLiuDaoResbeanDao() {
        return zhuLiuDaoResbeanDao;
    }

    public Step5ResbeanDao getStep5ResbeanDao() {
        return step5ResbeanDao;
    }

    public Step6ResbeanDao getStep6ResbeanDao() {
        return step6ResbeanDao;
    }

    public Step7ResbeanDao getStep7ResbeanDao() {
        return step7ResbeanDao;
    }

    public ZhuSuResbeanDao getZhuSuResbeanDao() {
        return zhuSuResbeanDao;
    }

    public ChongTianPingHengResbeanDao getChongTianPingHengResbeanDao() {
        return chongTianPingHengResbeanDao;
    }

    public Step8ResbeanDao getStep8ResbeanDao() {
        return step8ResbeanDao;
    }

    public BaoYaTimeResbeanDao getBaoYaTimeResbeanDao() {
        return baoYaTimeResbeanDao;
    }

    public BaoYaYaLiResbeanDao getBaoYaYaLiResbeanDao() {
        return baoYaYaLiResbeanDao;
    }

    public Step9ResbeanDao getStep9ResbeanDao() {
        return step9ResbeanDao;
    }

}
