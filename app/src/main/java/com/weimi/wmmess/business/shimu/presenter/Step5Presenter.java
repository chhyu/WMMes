package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.step5.Step5Resbean;

public class Step5Presenter extends BasePresenter {

    private static final int RESULT_NG = 2;
    private static final int RESULT_ZERO = 0;

    public Step5Presenter(IBaseView view) {
        super(view);
    }

    /**
     * 检查是否有数据为填写
     *
     * @param step5Resbean
     * @return
     */
    public boolean checkDateIsHaveNull(Step5Resbean step5Resbean) {
        if (StringUtils.isEmpty(step5Resbean.getKaiMoSheDingSj())) {
            ToastUtils.showShort("请输入开模设定时间");
            return true;
        }
        if (StringUtils.isEmpty(step5Resbean.getKaiMoShiJiSj())) {
            ToastUtils.showShort("请输入开模实际时间");
            return true;
        }

        if (StringUtils.isEmpty(step5Resbean.getDingChuSheDingSj())) {
            ToastUtils.showShort("请输入顶出设定时间");
            return true;
        }
        if (StringUtils.isEmpty(step5Resbean.getDingChuShiJiSj())) {
            ToastUtils.showShort("请输入顶出实际时间");
            return true;
        }

        if (StringUtils.isEmpty(step5Resbean.getDingTuiSheDingSj())) {
            ToastUtils.showShort("请输入顶退设定时间");
            return true;
        }
        if (StringUtils.isEmpty(step5Resbean.getDingTuiShiJiSj())) {
            ToastUtils.showShort("请输入顶退实际时间");
            return true;
        }

        if (StringUtils.isEmpty(step5Resbean.getHeMoSheDingSj())) {
            ToastUtils.showShort("请输入合模设定时间");
            return true;
        }
        if (StringUtils.isEmpty(step5Resbean.getHeMoShiJiSj())) {
            ToastUtils.showShort("请输入合模实际时间");
            return true;
        }

        if (step5Resbean.getOne() == RESULT_ZERO) {
            ToastUtils.showShort("存在未点检项目，请核实");
            return true;
        }
        if (step5Resbean.getTwo() == RESULT_ZERO) {
            ToastUtils.showShort("存在未点检项目，请核实");
            return true;
        }
        if (step5Resbean.getThree() == RESULT_ZERO) {
            ToastUtils.showShort("存在未点检项目，请核实");
            return true;
        }
        if (step5Resbean.getFour() == RESULT_ZERO) {
            ToastUtils.showShort("存在未点检项目，请核实");
            return true;
        }
        if (step5Resbean.getFive() == RESULT_ZERO) {
            ToastUtils.showShort("存在未点检项目，请核实");
            return true;
        }
        if (step5Resbean.getSix() == RESULT_ZERO) {
            ToastUtils.showShort("存在未点检项目，请核实");
            return true;
        }
        if (step5Resbean.getSeven() == RESULT_ZERO) {
            ToastUtils.showShort("存在未点检项目，请核实");
            return true;
        }
        if (step5Resbean.getEight() == RESULT_ZERO) {
            ToastUtils.showShort("存在未点检项目，请核实");
            return true;
        }
        if (step5Resbean.getNine() == RESULT_ZERO) {
            ToastUtils.showShort("存在未点检项目，请核实");
            return true;
        }
        if (step5Resbean.getEleven() == RESULT_ZERO) {
            ToastUtils.showShort("存在未点检项目，请核实");
            return true;
        }
        return false;
    }

    /**
     * 检测是否存在NG选项
     *
     * @param step5Resbean
     * @return
     */
    public boolean checkIsHaveNG(Step5Resbean step5Resbean) {
        if (step5Resbean.getOne() == RESULT_NG) {
            return true;
        }
        if (step5Resbean.getTwo() == RESULT_NG) {
            return true;
        }
        if (step5Resbean.getThree() == RESULT_NG) {
            return true;
        }
        if (step5Resbean.getFour() == RESULT_NG) {
            return true;
        }
        if (step5Resbean.getFive() == RESULT_NG) {
            return true;
        }
        if (step5Resbean.getSix() == RESULT_NG) {
            return true;
        }
        if (step5Resbean.getSeven() == RESULT_NG) {
            return true;
        }
        if (step5Resbean.getEight() == RESULT_NG) {
            return true;
        }
        if (step5Resbean.getNine() == RESULT_NG) {
            return true;
        }
        if (step5Resbean.getTen() == RESULT_NG) {
            return true;
        }
        if (step5Resbean.getEleven() == RESULT_NG) {
            return true;
        }
        return false;
    }
}
