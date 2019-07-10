package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.step6.Step6Resbean;

public class Step6Presenter extends BasePresenter {

    private static final int RESULT_NG = 2;

    public Step6Presenter(IBaseView view) {
        super(view);
    }

    /**
     * 检查是否存在NG选项
     *
     * @param step6Resbean
     * @return
     */
    public boolean checkIsHaveNG(Step6Resbean step6Resbean) {
        if (step6Resbean.getResultOne() == RESULT_NG) {
            ToastUtils.showShort("存在NG选项,请检查分析注塑机的射嘴是否合适、模具的流道和进胶点是否合适");
            return true;
        }
        if (step6Resbean.getResultTwo() == RESULT_NG) {
            ToastUtils.showShort("存在NG选项,请检查分析注塑机的射嘴是否合适、模具的流道和进胶点是否合适");
            return true;
        }
        if (step6Resbean.getResultThree() == RESULT_NG) {
            ToastUtils.showShort("存在NG选项,请检查分析注塑机的射嘴是否合适、模具的流道和进胶点是否合适");
            return true;
        }
        if (step6Resbean.getResultFour() == RESULT_NG) {
            ToastUtils.showShort("存在NG选项,请检查分析注塑机的射嘴是否合适、模具的流道和进胶点是否合适");
            return true;
        }
        if (step6Resbean.getResultFive() == RESULT_NG) {
            ToastUtils.showShort("存在NG选项,请检查分析注塑机的射嘴是否合适、模具的流道和进胶点是否合适");
            return true;
        }
        return false;
    }
}
