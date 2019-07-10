package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.step10.YaLiTestResbean;

import java.util.List;

public class Step10Presenter extends BasePresenter {
    public Step10Presenter(IBaseView view) {
        super(view);
    }

    public boolean checkDataIsHaveNull(List<YaLiTestResbean> yaLiTestResbeans) {
        YaLiTestResbean yaLiTestResbean = yaLiTestResbeans.get(yaLiTestResbeans.size() - 1);
        if (StringUtils.isEmpty(yaLiTestResbean.getLengQueTime())) {
            ToastUtils.showShort("请输入冷却时间");
            return true;
        }
        if (StringUtils.isEmpty(yaLiTestResbean.getZhiLiang())) {
            ToastUtils.showShort("请输入外观质量");
            return true;
        }
        if (StringUtils.isEmpty(yaLiTestResbean.getChiCun())) {
            ToastUtils.showShort("请输入尺寸");
            return true;
        }
        return false;
    }
}
