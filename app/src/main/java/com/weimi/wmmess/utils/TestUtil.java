package com.weimi.wmmess.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.business.shimu.bean.DaoSession;
import com.weimi.wmmess.business.shimu.bean.HeadInfoResbean;
import com.weimi.wmmess.business.shimu.bean.HeadInfoResbeanDao;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbean;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbeanDao;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemItemResbean;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemItemResbeanDao;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemResbean;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step1.Step1Resbean;
import com.weimi.wmmess.business.shimu.bean.step1.Step1ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step10.Step10Resbean;
import com.weimi.wmmess.business.shimu.bean.step10.Step10ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step10.YaLiTestResbean;
import com.weimi.wmmess.business.shimu.bean.step10.YaLiTestResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step11.Step11Resbean;
import com.weimi.wmmess.business.shimu.bean.step11.Step11ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step12.Step12ResBean;
import com.weimi.wmmess.business.shimu.bean.step12.Step12ResBeanDao;
import com.weimi.wmmess.business.shimu.bean.step13.Step13Resbean;
import com.weimi.wmmess.business.shimu.bean.step13.Step13ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step2.Step2Resbean;
import com.weimi.wmmess.business.shimu.bean.step2.Step2ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.FixSideResbean;
import com.weimi.wmmess.business.shimu.bean.step3.FixSideResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.MoveSideResbean;
import com.weimi.wmmess.business.shimu.bean.step3.MoveSideResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.SlideResbean;
import com.weimi.wmmess.business.shimu.bean.step3.SlideResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.Step3MuResBean;
import com.weimi.wmmess.business.shimu.bean.step3.Step3MuResBeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.Step3ShuiLuDate;
import com.weimi.wmmess.business.shimu.bean.step3.Step3ShuiLuDateDao;
import com.weimi.wmmess.business.shimu.bean.step4.KongZhiDianResbean;
import com.weimi.wmmess.business.shimu.bean.step4.KongZhiDianResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step4.Step4Resbean;
import com.weimi.wmmess.business.shimu.bean.step4.Step4ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step4.ZhuLiuDaoResbean;
import com.weimi.wmmess.business.shimu.bean.step4.ZhuLiuDaoResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step5.Step5Resbean;
import com.weimi.wmmess.business.shimu.bean.step5.Step5ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step6.Step6Resbean;
import com.weimi.wmmess.business.shimu.bean.step6.Step6ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step7.Step7Resbean;
import com.weimi.wmmess.business.shimu.bean.step7.Step7ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step7.ZhuSuResbean;
import com.weimi.wmmess.business.shimu.bean.step7.ZhuSuResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step8.ChongTianPingHengResbean;
import com.weimi.wmmess.business.shimu.bean.step8.ChongTianPingHengResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step8.Step8Resbean;
import com.weimi.wmmess.business.shimu.bean.step8.Step8ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaTimeResbean;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaTimeResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaYaLiResbean;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaYaLiResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step9.Step9Resbean;
import com.weimi.wmmess.business.shimu.bean.step9.Step9ResbeanDao;

import org.greenrobot.greendao.query.LazyList;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/5/14
 * Describle:
 */
public class TestUtil {

    public static void getAllDbDatas() {
        ShiMuResbeanDao shiMuResbeanDao = MainApplication.daoSession.getShiMuResbeanDao();
        List<ShiMuResbean> shiMuResbeans = shiMuResbeanDao.loadAll();
        for (int i = 0; i < shiMuResbeans.size(); i++) {
//            Log.e("lmsg", "ShiMuResbean==" + JSON.toJSONString(shiMuResbeans.get(i)));
//            Log.e("lmsg", "step2Resbean==" + JSON.toJSONString(MainApplication.daoSession.getStep2ResbeanDao().loadAll()));
//            Log.e("lmsg", "step3Resbean==" + JSON.toJSONString(MainApplication.daoSession.getStep3MuResBeanDao().loadAll()));
//            Log.e("lmsg", "step4Resbean==" + JSON.toJSONString(MainApplication.daoSession.getStep4ResbeanDao().loadAll()));
//            Log.e("lmsg", "step5Resbean==" + JSON.toJSONString(MainApplication.daoSession.getStep5ResbeanDao().loadAll()));
//            Log.e("lmsg", "step9Resbean==" + JSON.toJSONString(MainApplication.daoSession.getStep9ResbeanDao().loadAll()));
//            Log.e("lmsg", "step9ResbeanYaLi==" + JSON.toJSONString(MainApplication.daoSession.getBaoYaYaLiResbeanDao().loadAll()));
//            Log.e("lmsg", "step9ResbeanTime==" + JSON.toJSONString(MainApplication.daoSession.getBaoYaTimeResbeanDao().loadAll()));
        }
    }

    public static void loadAllDatas() {
        //试模
        ShiMuResbeanDao shiMuResbeanDao = MainApplication.daoSession.getShiMuResbeanDao();
        List<ShiMuResbean> shiMuResbeans = shiMuResbeanDao.loadAll();
        Log.e("lmsg", "shiMuResbeans==" + JSON.toJSONString(shiMuResbeans));

        //step1
        Step1ResbeanDao step1ResbeanDao = MainApplication.daoSession.getStep1ResbeanDao();
        List<Step1Resbean> step1Resbeans = step1ResbeanDao.loadAll();
        Log.e("lmsg", "step1Resbeans==" + JSON.toJSONString(step1Resbeans));

        //step2
        Step2ResbeanDao step2ResbeanDao = MainApplication.daoSession.getStep2ResbeanDao();
        List<Step2Resbean> step2Resbeans = step2ResbeanDao.loadAll();
        Log.e("lmsg", "step2Resbeans==" + JSON.toJSONString(step2Resbeans));

        //step3
        Step3MuResBeanDao step3MuResBeanDao = MainApplication.daoSession.getStep3MuResBeanDao();
        List<Step3MuResBean> step3MuResBeans = step3MuResBeanDao.loadAll();
        Log.e("lmsg", "step3MuResBeans==" + JSON.toJSONString(step3MuResBeans));

        FixSideResbeanDao fixSideResbeanDao = MainApplication.daoSession.getFixSideResbeanDao();
        List<FixSideResbean> fixSideResbeans = fixSideResbeanDao.loadAll();
        Log.e("lmsg", "fixSideResbeans==" + JSON.toJSONString(fixSideResbeans));

        MoveSideResbeanDao moveSideResbeanDao = MainApplication.daoSession.getMoveSideResbeanDao();
        List<MoveSideResbean> moveSideResbeans = moveSideResbeanDao.loadAll();
        Log.e("lmsg", "moveSideResbeans==" + JSON.toJSONString(moveSideResbeans));

        SlideResbeanDao slideResbeanDao = MainApplication.daoSession.getSlideResbeanDao();
        List<SlideResbean> slideResbeans = slideResbeanDao.loadAll();
        Log.e("lmsg", "slideResbeans==" + JSON.toJSONString(slideResbeans));

        Step3ShuiLuDateDao step3ShuiLuDateDao = MainApplication.daoSession.getStep3ShuiLuDateDao();
        List<Step3ShuiLuDate> step3ShuiLuDates = step3ShuiLuDateDao.loadAll();
        Log.e("lmsg", "step3ShuiLuDates==" + JSON.toJSONString(step3ShuiLuDates));

        //step4
        Step4ResbeanDao step4ResbeanDao = MainApplication.daoSession.getStep4ResbeanDao();
        List<Step4Resbean> step4Resbeans = step4ResbeanDao.loadAll();
        Log.e("lmsg", "step4Resbeans==" + JSON.toJSONString(step4Resbeans));

        ZhuLiuDaoResbeanDao zhuLiuDaoResbeanDao = MainApplication.daoSession.getZhuLiuDaoResbeanDao();
        List<ZhuLiuDaoResbean> zhuLiuDaoResbeans = zhuLiuDaoResbeanDao.loadAll();
        Log.e("lmsg", "zhuLiuDaoResbeans==" + JSON.toJSONString(zhuLiuDaoResbeans));

        KongZhiDianResbeanDao kongZhiDianResbeanDao = MainApplication.daoSession.getKongZhiDianResbeanDao();
        List<KongZhiDianResbean> kongZhiDianResbeans = kongZhiDianResbeanDao.loadAll();
        Log.e("lmsg", "kongZhiDianResbeans==" + JSON.toJSONString(kongZhiDianResbeans));

        //step5
        Step5ResbeanDao step5ResbeanDao = MainApplication.daoSession.getStep5ResbeanDao();
        List<Step5Resbean> step5Resbeans = step5ResbeanDao.loadAll();
        Log.e("lmsg", "step5Resbeans==" + JSON.toJSONString(step5Resbeans));

        //step6
        Step6ResbeanDao step6ResbeanDao = MainApplication.daoSession.getStep6ResbeanDao();
        List<Step6Resbean> step6Resbeans = step6ResbeanDao.loadAll();
        Log.e("lmsg", "step6Resbeans==" + JSON.toJSONString(step6Resbeans));

        //step7
        Step7ResbeanDao step7ResbeanDao = MainApplication.daoSession.getStep7ResbeanDao();
        List<Step7Resbean> step7Resbeans = step7ResbeanDao.loadAll();
        Log.e("lmsg", "step7Resbeans==" + JSON.toJSONString(step7Resbeans));

        ZhuSuResbeanDao zhuSuResbeanDao = MainApplication.daoSession.getZhuSuResbeanDao();
        List<ZhuSuResbean> zhuSuResbeans = zhuSuResbeanDao.loadAll();
        Log.e("lmsg", "zhuSuResbeans==" + JSON.toJSONString(zhuSuResbeans));

        //step8
        Step8ResbeanDao step8ResbeanDao = MainApplication.daoSession.getStep8ResbeanDao();
        List<Step8Resbean> step8Resbeans = step8ResbeanDao.loadAll();
        Log.e("lmsg", "step8Resbeans==" + JSON.toJSONString(step8Resbeans));

        ChongTianPingHengResbeanDao chongTianPingHengResbeanDao = MainApplication.daoSession.getChongTianPingHengResbeanDao();
        List<ChongTianPingHengResbean> chongTianPingHengResbeans = chongTianPingHengResbeanDao.loadAll();
        Log.e("lmsg", "chongTianPingHengResbeans==" + JSON.toJSONString(chongTianPingHengResbeans));

        //step9
        Step9ResbeanDao step9ResbeanDao = MainApplication.daoSession.getStep9ResbeanDao();
        List<Step9Resbean> step9Resbeans = step9ResbeanDao.loadAll();
        Log.e("lmsg", "step9Resbeans==" + JSON.toJSONString(step9Resbeans));

        BaoYaYaLiResbeanDao baoYaYaLiResbeanDao = MainApplication.daoSession.getBaoYaYaLiResbeanDao();
        List<BaoYaYaLiResbean> baoYaYaLiResbeans = baoYaYaLiResbeanDao.loadAll();
        Log.e("lmsg", "baoYaYaLiResbeans==" + JSON.toJSONString(baoYaYaLiResbeans));

        BaoYaTimeResbeanDao baoYaTimeResbeanDao = MainApplication.daoSession.getBaoYaTimeResbeanDao();
        List<BaoYaTimeResbean> baoYaTimeResbeans = baoYaTimeResbeanDao.loadAll();
        Log.e("lmsg", "baoYaTimeResbeans==" + JSON.toJSONString(baoYaTimeResbeans));

        //step10
        Step10ResbeanDao step10ResbeanDao = MainApplication.daoSession.getStep10ResbeanDao();
        List<Step10Resbean> step10Resbeans = step10ResbeanDao.loadAll();
        Log.e("lmsg", "step10Resbeans==" + JSON.toJSONString(step10Resbeans));

        YaLiTestResbeanDao yaLiTestResbeanDao = MainApplication.daoSession.getYaLiTestResbeanDao();
        List<YaLiTestResbean> yaLiTestResbeans = yaLiTestResbeanDao.loadAll();
        Log.e("lmsg", "yaLiTestResbeans==" + JSON.toJSONString(yaLiTestResbeans));

        //step11
        Step11ResbeanDao step11ResbeanDao = MainApplication.daoSession.getStep11ResbeanDao();
        List<Step11Resbean> step11Resbeans = step11ResbeanDao.loadAll();
        Log.e("lmsg", "step11Resbeans==" + JSON.toJSONString(step11Resbeans));

        //step12
        Step12ResBeanDao step12ResBeanDao = MainApplication.daoSession.getStep12ResBeanDao();
        List<Step12ResBean> step12ResBeans = step12ResBeanDao.loadAll();
        Log.e("lmsg", "step12ResBeans==" + JSON.toJSONString(step12ResBeans));

        //step13
        Step13ResbeanDao step13ResbeanDao = MainApplication.daoSession.getStep13ResbeanDao();
        List<Step13Resbean> step13Resbeans = step13ResbeanDao.loadAll();
        Log.e("lmsg", "step13Resbeans==" + JSON.toJSONString(step13Resbeans));

        //headInfo
        HeadInfoResbeanDao headInfoResbeanDao = MainApplication.daoSession.getHeadInfoResbeanDao();
        List<HeadInfoResbean> headInfoResbeans = headInfoResbeanDao.loadAll();
        Log.e("lmsg", "headInfoResbeans==" + JSON.toJSONString(headInfoResbeans));

        //problem
        ProblemResbeanDao problemResbeanDao = MainApplication.daoSession.getProblemResbeanDao();
        List<ProblemResbean> problemResbeans = problemResbeanDao.loadAll();
        Log.e("lmsg", "problemResbeans==" + JSON.toJSONString(problemResbeans));

        ProblemItemResbeanDao problemItemResbeanDao = MainApplication.daoSession.getProblemItemResbeanDao();
        List<ProblemItemResbean> problemItemResbeans = problemItemResbeanDao.loadAll();
        Log.e("lmsg", "problemItemResbeans==" + JSON.toJSONString(problemItemResbeans));
    }

    /**
     * 删除未完成的数据
     */
    public static void deleteNotCompleteDate() {
        try {
            //试模
            MainApplication.daoSession.getShiMuResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //step1
            MainApplication.daoSession.getStep1ResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //step2
            MainApplication.daoSession.getStep2ResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //step3
            MainApplication.daoSession.getStep3MuResBeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getFixSideResbeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getMoveSideResbeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getSlideResbeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getStep3ShuiLuDateDao().deleteByKey(MainApplication.thisTimeId);

            //step4
            MainApplication.daoSession.getStep4ResbeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getZhuLiuDaoResbeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getKongZhiDianResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //step5
            MainApplication.daoSession.getStep5ResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //step6
            MainApplication.daoSession.getStep6ResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //step7
            MainApplication.daoSession.getStep7ResbeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getZhuSuResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //step8
            MainApplication.daoSession.getStep8ResbeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getChongTianPingHengResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //step9
            MainApplication.daoSession.getStep9ResbeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getBaoYaYaLiResbeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getBaoYaTimeResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //step10
            MainApplication.daoSession.getStep10ResbeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getYaLiTestResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //step11
            MainApplication.daoSession.getStep11ResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //step12
            MainApplication.daoSession.getStep12ResBeanDao().deleteByKey(MainApplication.thisTimeId);

            //step13
            MainApplication.daoSession.getStep13ResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //headInfo
            MainApplication.daoSession.getHeadInfoResbeanDao().deleteByKey(MainApplication.thisTimeId);

            //problem
            MainApplication.daoSession.getProblemResbeanDao().deleteByKey(MainApplication.thisTimeId);

            MainApplication.daoSession.getProblemItemResbeanDao().deleteByKey(MainApplication.thisTimeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
