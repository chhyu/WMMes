package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.step4.Step4Resbean;

public class Step4Presenter extends BasePresenter {


    public Step4Presenter(IBaseView view) {
        super(view);
    }

    /**
     * 检查数据是否有空数据
     *
     * @param step4Resbean
     * @return
     */
    public boolean checkDataIsHaveNull(Step4Resbean step4Resbean) {
        if (StringUtils.isEmpty(step4Resbean.getPenzui())) {
            ToastUtils.showShort("请输入喷嘴温度");
            return true;
        }

        if (StringUtils.isEmpty(step4Resbean.getOne())) {
            ToastUtils.showShort("请输入螺杆一段温度");
            return true;
        }

        if (StringUtils.isEmpty(step4Resbean.getTwo())) {
            ToastUtils.showShort("请输入螺杆二段温度");
            return true;
        }
        if (StringUtils.isEmpty(step4Resbean.getThree())) {
            ToastUtils.showShort("请输入螺杆三段温度");
            return true;
        }
        if (StringUtils.isEmpty(step4Resbean.getFour())) {
            ToastUtils.showShort("请输入螺杆四段温度");
            return true;
        }
        if (StringUtils.isEmpty(step4Resbean.getFive())) {
            ToastUtils.showShort("请输入螺杆五段温度");
            return true;
        }
        if (StringUtils.isEmpty(step4Resbean.getXiaLiaoKou())) {
            ToastUtils.showShort("请输入螺杆下料口温度");
            return true;
        }

        if (StringUtils.isEmpty(step4Resbean.getDingMoSheDing())) {
            ToastUtils.showShort("请输入定模设定温度");
            return true;
        }
        if (StringUtils.isEmpty(step4Resbean.getDingMoShiJi())) {
            ToastUtils.showShort("请输入定模实际温度");
            return true;
        }

        if (StringUtils.isEmpty(step4Resbean.getDongMoSheDing())) {
            ToastUtils.showShort("请输入动模设定温度");
            return true;
        }
        if (StringUtils.isEmpty(step4Resbean.getDongMoShiJi())) {
            ToastUtils.showShort("请输入动模实际温度");
            return true;
        }
        if (StringUtils.isEmpty(step4Resbean.getHuaKuaiSheDing())) {
            ToastUtils.showShort("请输入滑块设定温度");
            return true;
        }
        if (StringUtils.isEmpty(step4Resbean.getHuaKuaiShiJi())) {
            ToastUtils.showShort("请输入滑块实际温度");
            return true;
        }

        if (step4Resbean.getLuoGanZhiJing() <= 0) {
            ToastUtils.showShort("请输入螺杆直径");
            return true;
        }
        if (step4Resbean.getYuanLiao() <= 0) {
            ToastUtils.showShort("请输入原料");
            return true;
        }
        if (step4Resbean.getLuoGanZhuanSu() <= 0) {
            ToastUtils.showShort("请输入螺杆转速");
            return true;
        }
        return false;
    }
}
