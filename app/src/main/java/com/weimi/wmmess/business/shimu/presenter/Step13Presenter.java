package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.api.ShiMuServive;
import com.weimi.wmmess.business.shimu.bean.step13.Step13Resbean;
import com.weimi.wmmess.constant.HostAddress;
import com.weimi.wmmess.http.HttpClient;

public class Step13Presenter extends BasePresenter {

    public Step13Presenter(IBaseView view) {
        super(view);

    }

    /**
     * 检查数据是否有null
     *
     * @param step13Resbean
     * @return
     */
    public boolean checkDataIsHaveNull(Step13Resbean step13Resbean) {
        if (StringUtils.isEmpty(step13Resbean.getJinLiaoDuanSD())) {
            ToastUtils.showShort("请输入进料段设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getJinLiaoDuanSJ())) {
            ToastUtils.showShort("请输入进料段实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getJinLiaoDuanMin())) {
            ToastUtils.showShort("请输入进料段最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getJinLiaoDuanMax())) {
            ToastUtils.showShort("请输入进料段最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getJinLiaoDuanTime())) {
            ToastUtils.showShort("请输入进料段时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getHouDuanSD())) {
            ToastUtils.showShort("请输入后段设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHouDuanSJ())) {
            ToastUtils.showShort("请输入后段实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHouDuanMin())) {
            ToastUtils.showShort("请输入后段最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHouDuanMax())) {
            ToastUtils.showShort("请输入后段最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHouDuanTime())) {
            ToastUtils.showShort("请输入后段时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getZhongJianDuanSD())) {
            ToastUtils.showShort("请输入中间段设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getZhongJianDuanSJ())) {
            ToastUtils.showShort("请输入中间段实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getZhongJianDuanMin())) {
            ToastUtils.showShort("请输入中间段最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getZhongJianDuanMax())) {
            ToastUtils.showShort("请输入中间段最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getZhongJianDuanTime())) {
            ToastUtils.showShort("请输入中间段时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getQianDuanSD())) {
            ToastUtils.showShort("请输入前段设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianDuanSJ())) {
            ToastUtils.showShort("请输入前段实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianDuanMin())) {
            ToastUtils.showShort("请输入前段最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianDuanMax())) {
            ToastUtils.showShort("请输入前段最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianDuanTime())) {
            ToastUtils.showShort("请输入前段时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getPenZuiSD())) {
            ToastUtils.showShort("请输入喷嘴设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getPenZuiSJ())) {
            ToastUtils.showShort("请输入喷嘴实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getPenZuiMin())) {
            ToastUtils.showShort("请输入喷嘴最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getPenZuiMax())) {
            ToastUtils.showShort("请输入喷嘴最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getPenZuiTime())) {
            ToastUtils.showShort("请输入喷嘴时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getSheChu1SuSD())) {
            ToastUtils.showShort("请输入射出1速设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu1SuSJ())) {
            ToastUtils.showShort("请输入射出1速实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu1SuMin())) {
            ToastUtils.showShort("请输入射出1速最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu1SuMax())) {
            ToastUtils.showShort("请输入射出1速最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu1SuTime())) {
            ToastUtils.showShort("请输入射出1速时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getSheChu2SuSD())) {
            ToastUtils.showShort("请输入射出2速设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu2SuSJ())) {
            ToastUtils.showShort("请输入射出2速实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu2SuMin())) {
            ToastUtils.showShort("请输入射出2速最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu2SuMax())) {
            ToastUtils.showShort("请输入射出2速最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu2SuTime())) {
            ToastUtils.showShort("请输入射出2速时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getSheChu3SuSD())) {
            ToastUtils.showShort("请输入射出3速设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu3SuSJ())) {
            ToastUtils.showShort("请输入射出3速实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu3SuMin())) {
            ToastUtils.showShort("请输入射出3速最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu3SuMax())) {
            ToastUtils.showShort("请输入射出3速最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu3SuTime())) {
            ToastUtils.showShort("请输入射出3速时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getSheChu4SuSD())) {
            ToastUtils.showShort("请输入射出4速设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu4SuSJ())) {
            ToastUtils.showShort("请输入射出4速实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu4SuMin())) {
            ToastUtils.showShort("请输入射出4速最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu4SuMax())) {
            ToastUtils.showShort("请输入射出4速最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChu4SuTime())) {
            ToastUtils.showShort("请输入射出4速时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getEt1DuanWeiZhiSD())) {
            ToastUtils.showShort("请输入1段位置设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt1DuanWeiZhiSJ())) {
            ToastUtils.showShort("请输入1段位置实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt1DuanWeiZhiMin())) {
            ToastUtils.showShort("请输入1段位置最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt1DuanWeiZhiMax())) {
            ToastUtils.showShort("请输入1段位置最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt1DuanWeiZhiTime())) {
            ToastUtils.showShort("请输入1段位置时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getEt2DuanWeiZhiSD())) {
            ToastUtils.showShort("请输入2段位置设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt2DuanWeiZhiSJ())) {
            ToastUtils.showShort("请输入2段位置实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt2DuanWeiZhiMin())) {
            ToastUtils.showShort("请输入2段位置最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt2DuanWeiZhiMax())) {
            ToastUtils.showShort("请输入2段位置最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt2DuanWeiZhiTime())) {
            ToastUtils.showShort("请输入2段位置时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getEt3DuanWeiZhiSD())) {
            ToastUtils.showShort("请输入3段位置设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt3DuanWeiZhiSJ())) {
            ToastUtils.showShort("请输入3段位置实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt3DuanWeiZhiMin())) {
            ToastUtils.showShort("请输入3段位置最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt3DuanWeiZhiMax())) {
            ToastUtils.showShort("请输入3段位置最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt3DuanWeiZhiTime())) {
            ToastUtils.showShort("请输入3段位置时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getEt4DuanWeiZhiSD())) {
            ToastUtils.showShort("请输入4段位置设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt4DuanWeiZhiSJ())) {
            ToastUtils.showShort("请输入4段位置实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt4DuanWeiZhiMin())) {
            ToastUtils.showShort("请输入4段位置最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt4DuanWeiZhiMax())) {
            ToastUtils.showShort("请输入4段位置最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getEt4DuanWeiZhiTime())) {
            ToastUtils.showShort("请输入4段位置时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getVPWeiZhiSD())) {
            ToastUtils.showShort("请输入V-P位置设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getVPWeiZhiSJ())) {
            ToastUtils.showShort("请输入V-P位置实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getVPWeiZhiMin())) {
            ToastUtils.showShort("请输入V-P位置最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getVPWeiZhiMax())) {
            ToastUtils.showShort("请输入V-P位置最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getVPWeiZhiTime())) {
            ToastUtils.showShort("请输入V-P位置时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi1SD())) {
            ToastUtils.showShort("请输入射出压力1设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi1SJ())) {
            ToastUtils.showShort("请输入射出压力1实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi1Min())) {
            ToastUtils.showShort("请输入射出压力1最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi1Max())) {
            ToastUtils.showShort("请输入射出压力1最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi1Time())) {
            ToastUtils.showShort("请输入射出压力1时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi2SD())) {
            ToastUtils.showShort("请输入射出压力2设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi2SJ())) {
            ToastUtils.showShort("请输入射出压力2实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi2Min())) {
            ToastUtils.showShort("请输入射出压力2最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi2Max())) {
            ToastUtils.showShort("请输入射出压力2最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi2Time())) {
            ToastUtils.showShort("请输入射出压力2时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi3SD())) {
            ToastUtils.showShort("请输入射出压力3设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi3SJ())) {
            ToastUtils.showShort("请输入射出压力3实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi3Min())) {
            ToastUtils.showShort("请输入射出压力3最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi3Max())) {
            ToastUtils.showShort("请输入射出压力3最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi3Time())) {
            ToastUtils.showShort("请输入射出压力3时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi4SD())) {
            ToastUtils.showShort("请输入射出压力4设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi4SJ())) {
            ToastUtils.showShort("请输入射出压力4实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi4Min())) {
            ToastUtils.showShort("请输入射出压力4最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi4Max())) {
            ToastUtils.showShort("请输入射出压力4最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuYaLi4Time())) {
            ToastUtils.showShort("请输入射出压力4时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getLengQueShiJianSD())) {
            ToastUtils.showShort("请输入冷却时间设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getLengQueShiJianSJ())) {
            ToastUtils.showShort("请输入冷却时间实际");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getLengQueShiJianMin())) {
            ToastUtils.showShort("请输入冷却时间最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getLengQueShiJianMax())) {
            ToastUtils.showShort("请输入冷却时间最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getLengQueShiJianTime())) {
            ToastUtils.showShort("请输入冷却时间记录时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getSheChuShiJianSD())) {
            ToastUtils.showShort("请输入射出时间设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuShiJianSJ())) {
            ToastUtils.showShort("请输入射出时间实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuShiJianMin())) {
            ToastUtils.showShort("请输入射出时间最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuShiJianMax())) {
            ToastUtils.showShort("请输入射出时间最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSheChuShiJianTime())) {
            ToastUtils.showShort("请输入射出时间记录时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getZhouQiShiJianSD())) {
            ToastUtils.showShort("请输入周期时间设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getZhouQiShiJianSJ())) {
            ToastUtils.showShort("请输入周期时间实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getZhouQiShiJianMin())) {
            ToastUtils.showShort("请输入周期时间最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getZhouQiShiJianMax())) {
            ToastUtils.showShort("请输入周期时间最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getZhouQiShiJianTime())) {
            ToastUtils.showShort("请输入周期时间记录时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getBaoYa1DuanSD())) {
            ToastUtils.showShort("请输入保压1段设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa1DuanSJ())) {
            ToastUtils.showShort("请输入保压1段实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa1DuanMin())) {
            ToastUtils.showShort("请输入保压1段最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa1DuanMax())) {
            ToastUtils.showShort("请输入保压1段最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa1DuanTime())) {
            ToastUtils.showShort("请输入保压1段时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getBaoYa2DuanSD())) {
            ToastUtils.showShort("请输入保压2段设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa2DuanSJ())) {
            ToastUtils.showShort("请输入保压2段实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa2DuanMin())) {
            ToastUtils.showShort("请输入保压2段最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa2DuanMax())) {
            ToastUtils.showShort("请输入保压2段最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa2DuanTime())) {
            ToastUtils.showShort("请输入保压2段时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getBaoYa3DuanSD())) {
            ToastUtils.showShort("请输入保压3段设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa3DuanSJ())) {
            ToastUtils.showShort("请输入保压3段实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa3DuanMin())) {
            ToastUtils.showShort("请输入保压3段最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa3DuanMax())) {
            ToastUtils.showShort("请输入保压3段最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa3DuanTime())) {
            ToastUtils.showShort("请输入保压3段时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getBaoYa1SuSD())) {
            ToastUtils.showShort("请输入保压1速设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa1SuSJ())) {
            ToastUtils.showShort("请输入保压1速实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa1SuMin())) {
            ToastUtils.showShort("请输入保压1速最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa1SuMax())) {
            ToastUtils.showShort("请输入保压1速最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa1SuTime())) {
            ToastUtils.showShort("请输入保压1速时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getBaoYa2SuSD())) {
            ToastUtils.showShort("请输入保压2速设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa2SuSJ())) {
            ToastUtils.showShort("请输入保压2速实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa2SuMin())) {
            ToastUtils.showShort("请输入保压2速最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa2SuMax())) {
            ToastUtils.showShort("请输入保压2速最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa2SuTime())) {
            ToastUtils.showShort("请输入保压2速时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getBaoYa3SuSD())) {
            ToastUtils.showShort("请输入保压3速设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa3SuSJ())) {
            ToastUtils.showShort("请输入保压3速实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa3SuMin())) {
            ToastUtils.showShort("请输入保压3速最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa3SuMax())) {
            ToastUtils.showShort("请输入保压3速最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYa3SuTime())) {
            ToastUtils.showShort("请输入保压3速时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian1SD())) {
            ToastUtils.showShort("请输入保压时间1设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian1SJ())) {
            ToastUtils.showShort("请输入保压时间1实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian1Min())) {
            ToastUtils.showShort("请输入保压时间1最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian1Max())) {
            ToastUtils.showShort("请输入保压时间1最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian1Time())) {
            ToastUtils.showShort("请输入保压时间1记录时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian2SD())) {
            ToastUtils.showShort("请输入保压时间2设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian2SJ())) {
            ToastUtils.showShort("请输入保压时间2实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian2Min())) {
            ToastUtils.showShort("请输入保压时间2最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian2Max())) {
            ToastUtils.showShort("请输入保压时间2最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian2Time())) {
            ToastUtils.showShort("请输入保压时间2记录时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian3SD())) {
            ToastUtils.showShort("请输入保压时间3设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian3SJ())) {
            ToastUtils.showShort("请输入保压时间3实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian3Min())) {
            ToastUtils.showShort("请输入保压时间3最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian3Max())) {
            ToastUtils.showShort("请输入保压时间3最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBaoYaShiJian3Time())) {
            ToastUtils.showShort("请输入保压时间3记录时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getLuoGanZhuanSuSD())) {
            ToastUtils.showShort("请输入螺杆转速设定值");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getLuoGanZhuanSuSD())) {
            ToastUtils.showShort("请输入螺杆转速设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getLuoGanZhuanSuSJ())) {
            ToastUtils.showShort("请输入螺杆转速实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getLuoGanZhuanSuMin())) {
            ToastUtils.showShort("请输入螺杆转速最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getLuoGanZhuanSuMax())) {
            ToastUtils.showShort("请输入螺杆转速最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getLuoGanZhuanSuTime())) {
            ToastUtils.showShort("请输入螺杆转速时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getBeiYaSD())) {
            ToastUtils.showShort("请输入背压设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBeiYaSJ())) {
            ToastUtils.showShort("请输入背压实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBeiYaMin())) {
            ToastUtils.showShort("请输入背压最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBeiYaMax())) {
            ToastUtils.showShort("请输入背压最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getBeiYaTime())) {
            ToastUtils.showShort("请输入背压时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getSongTuiWeiZhiSD())) {
            ToastUtils.showShort("请输入松退位置设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSongTuiWeiZhiSJ())) {
            ToastUtils.showShort("请输入松退位置实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSongTuiWeiZhiMin())) {
            ToastUtils.showShort("请输入松退位置最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSongTuiWeiZhiMax())) {
            ToastUtils.showShort("请输入松退位置最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSongTuiWeiZhiTime())) {
            ToastUtils.showShort("请输入松退位置时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getYongLiaoLiangSD())) {
            ToastUtils.showShort("请输入用料量设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getYongLiaoLiangSJ())) {
            ToastUtils.showShort("请输入用料量实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getYongLiaoLiangMin())) {
            ToastUtils.showShort("请输入用料量最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getYongLiaoLiangMax())) {
            ToastUtils.showShort("请输入用料量最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getYongLiaoLiangTime())) {
            ToastUtils.showShort("请输入用料量时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getHuanChongLiangSD())) {
            ToastUtils.showShort("请输入缓冲量设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHuanChongLiangSJ())) {
            ToastUtils.showShort("请输入缓冲量实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHuanChongLiangMin())) {
            ToastUtils.showShort("请输入缓冲量最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHuanChongLiangMax())) {
            ToastUtils.showShort("请输入缓冲量最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHuanChongLiangTime())) {
            ToastUtils.showShort("请输入缓冲量时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getMuJuBaoHuYaLiSD())) {
            ToastUtils.showShort("请输入模具保护压力设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getMuJuBaoHuYaLiSJ())) {
            ToastUtils.showShort("请输入模具保护压力实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getMuJuBaoHuYaLiMin())) {
            ToastUtils.showShort("请输入模具保护压力最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getMuJuBaoHuYaLiMax())) {
            ToastUtils.showShort("请输入模具保护压力最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getMuJuBaoHuYaLiTime())) {
            ToastUtils.showShort("请输入模具保护压力时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getMuJuBaoHuWeiZhiSD())) {
            ToastUtils.showShort("请输入模具保护位置设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getMuJuBaoHuWeiZhiSJ())) {
            ToastUtils.showShort("请输入模具保护位置实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getMuJuBaoHuWeiZhiMin())) {
            ToastUtils.showShort("请输入模具保护位置最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getMuJuBaoHuWeiZhiMax())) {
            ToastUtils.showShort("请输入模具保护位置最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getMuJuBaoHuWeiZhiTime())) {
            ToastUtils.showShort("请输入模具保护位置时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getSuoMoLiSD())) {
            ToastUtils.showShort("请输入锁模力设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSuoMoLiSJ())) {
            ToastUtils.showShort("请输入锁模力实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSuoMoLiMin())) {
            ToastUtils.showShort("请输入锁模力最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSuoMoLiMax())) {
            ToastUtils.showShort("请输入锁模力最大 值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getSuoMoLiTime())) {
            ToastUtils.showShort("请输入锁模力时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getDingChuWeiZhiSD())) {
            ToastUtils.showShort("请输入顶出位置设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDingChuWeiZhiSJ())) {
            ToastUtils.showShort("请输入顶出位置实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDingChuWeiZhiMin())) {
            ToastUtils.showShort("请输入顶出位置最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDingChuWeiZhiMax())) {
            ToastUtils.showShort("请输入顶出位置最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDingChuWeiZhiTime())) {
            ToastUtils.showShort("请输入顶出位置时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getDingMoWenDuSD())) {
            ToastUtils.showShort("请输入定模温度设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDingMoWenDuSJ())) {
            ToastUtils.showShort("请输入定模温度实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDingMoWenDuMin())) {
            ToastUtils.showShort("请输入定模温度最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDingMoWenDuMax())) {
            ToastUtils.showShort("请输入定模温度最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDingMoWenDuTime())) {
            ToastUtils.showShort("请输入定模温度时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getDongMoWenDuSD())) {
            ToastUtils.showShort("请输入动模温度设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDongMoWenDuSJ())) {
            ToastUtils.showShort("请输入动模温度实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDongMoWenDuMin())) {
            ToastUtils.showShort("请输入动模温度最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDongMoWenDuMax())) {
            ToastUtils.showShort("请输入动模温度最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getDongMoWenDuTime())) {
            ToastUtils.showShort("请输入动模温度时间 ");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getHongLiaoWenDuSD())) {
            ToastUtils.showShort("请输入动烘料温度设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHongLiaoWenDuSJ())) {
            ToastUtils.showShort("请输入动烘料温度实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHongLiaoWenDuMin())) {
            ToastUtils.showShort("请输入动烘料温度最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHongLiaoWenDuMax())) {
            ToastUtils.showShort("请输入动烘料温度最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHongLiaoWenDuTime())) {
            ToastUtils.showShort("请输入动烘料温度时间");
            return true;
        }


        if (StringUtils.isEmpty(step13Resbean.getHuiLiaoBiLiSD())) {
            ToastUtils.showShort("请输入回料比例设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHuiLiaoBiLiSJ())) {
            ToastUtils.showShort("请输入回料比例实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHuiLiaoBiLiMin())) {
            ToastUtils.showShort("请输入回料比例最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHuiLiaoBiLiMax())) {
            ToastUtils.showShort("请输入回料比例最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getHuiLiaoBiLiTime())) {
            ToastUtils.showShort("请输入回料比例时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getQianJianShuLiangSD())) {
            ToastUtils.showShort("请输入嵌件数量设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianJianShuLiangSJ())) {
            ToastUtils.showShort("请输入嵌件数量实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianJianShuLiangMin())) {
            ToastUtils.showShort("请输入嵌件数量最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianJianShuLiangMax())) {
            ToastUtils.showShort("请输入嵌件数量最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianJianShuLiangTime())) {
            ToastUtils.showShort("请输入嵌件数量时间");
            return true;
        }

        if (StringUtils.isEmpty(step13Resbean.getQianJianBianHaoSD())) {
            ToastUtils.showShort("请输入嵌件编号设定值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianJianBianHaoSJ())) {
            ToastUtils.showShort("请输入嵌件编号实际值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianJianBianHaoMin())) {
            ToastUtils.showShort("请输入嵌件编号最小值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianJianBianHaoMax())) {
            ToastUtils.showShort("请输入嵌件编号最大值");
            return true;
        }
        if (StringUtils.isEmpty(step13Resbean.getQianJianBianHaoTime())) {
            ToastUtils.showShort("请输入嵌件编号时间");
            return true;
        }
        return false;
    }







}
