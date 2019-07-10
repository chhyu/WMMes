package com.weimi.wmmess.business.shimu.presenter;

import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.step2.Step2Resbean;

public class Step2Presenter extends BasePresenter {

    private static final int RESULT_NG = 2;
    private static final int RESULT_ZERO = 0;

    public Step2Presenter(IBaseView view) {
        super(view);
    }

    /**
     * 检查是否有NG选项
     *
     * @param step2Resbean
     * @return 是-->有，否-->没有
     */
    public boolean checkIsHaveNg(Step2Resbean step2Resbean) {
        if (step2Resbean == null) {
            return true;
        }
        if (step2Resbean.getA() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getB() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getC() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getD() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getE() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getF() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getG() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getH() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getI() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getJ() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getK() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getL() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getM() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getN() == RESULT_NG) {
            return true;
        }
        if (step2Resbean.getO() == RESULT_NG) {
            return true;
        }
        return false;
    }

    /**
     * 检查是否有选项未赋值
     *
     * @param step2Resbean
     * @return
     */
    public boolean checkIsHaveNotSet(Step2Resbean step2Resbean) {
        if (step2Resbean == null) {
            return true;
        }
        if (step2Resbean.getA() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getB() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getC() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getD() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getE() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getF() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getG() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getH() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getI() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getJ() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getK() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getL() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getM() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getN() == RESULT_ZERO) {
            return true;
        }
        if (step2Resbean.getO() == RESULT_ZERO) {
            return true;
        }
        return false;
    }
}
