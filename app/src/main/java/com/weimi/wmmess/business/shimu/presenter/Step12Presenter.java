package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.step12.Step12ResBean;

public class Step12Presenter extends BasePresenter {
    public Step12Presenter(IBaseView view) {
        super(view);
    }

    /**
     * @param step12ResBean
     * @return
     */
    public boolean checkDataIsLegal(Step12ResBean step12ResBean) {
        if (StringUtils.isEmpty(step12ResBean.getAddZero())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getAddOne())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getAddTwo())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getAddThree())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getAddFour())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getAddFive())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getAddHopperOne())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getAddHopperTwo())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getAddOutSide())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getAddSize())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getAddZhiLiangOne())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getAddZhiLiangTwo())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }


        if (StringUtils.isEmpty(step12ResBean.getSubtractZero())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getSubtractOne())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getSubtractTwo())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getSubtractThree())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getSubtractFour())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getSubtractFive())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getSubtractHopperOne())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getSubtractHopperTwo())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getSubtractZhiLiangOne())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getSubtractZhiLiangTwo())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }


        if (StringUtils.isEmpty(step12ResBean.getAddWendu())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }

        if (StringUtils.isEmpty(step12ResBean.getMuJuAddWaiGuan())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }

        if (StringUtils.isEmpty(step12ResBean.getMuJuAddChiCun())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }

        if (StringUtils.isEmpty(step12ResBean.getMuJuAddZhiLiang())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }

        if (StringUtils.isEmpty(step12ResBean.getSubtractWenDu())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getMuJuSubtractWaiGuan())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getMuJuSubtractChiCun())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        if (StringUtils.isEmpty(step12ResBean.getMuJuSubtractZhiLiang())) {
            ToastUtils.showShort("请将数据填写完整");
            return false;
        }
        return true;
    }
}
