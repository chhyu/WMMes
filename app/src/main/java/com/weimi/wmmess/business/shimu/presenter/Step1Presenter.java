package com.weimi.wmmess.business.shimu.presenter;

import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.step1.Step1Resbean;

public class Step1Presenter extends BasePresenter {
    public Step1Presenter(IBaseView view) {
        super(view);
    }

    /**
     * 检查是否有为准备好的选项
     *
     * @param step1Resbean
     * @return
     */
    public boolean checkIsHaveFalse(Step1Resbean step1Resbean) {
        if (!step1Resbean.getOneIsChecked()) {
            return true;
        }
        if (!step1Resbean.getTwoIsChecked()) {
            return true;
        }
        if (!step1Resbean.getThreeIsChecked()) {
            return true;
        }
        if (!step1Resbean.getFourIsChecked()) {
            return true;
        }
        if (!step1Resbean.getFiveIsChecked()) {
            return true;
        }
        if (!step1Resbean.getSixIsChecked()) {
            return true;
        }
        return false;
    }
}
