package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.step7.Step7Resbean;
import com.weimi.wmmess.business.shimu.bean.step7.ZhuSuResbean;

import java.util.List;

public class Step7Presenter extends BasePresenter {
    public Step7Presenter(IBaseView view) {
        super(view);
    }

    public boolean checkDataIsHaveNull(Step7Resbean step7Resbean) {
        if (StringUtils.isEmpty(step7Resbean.getZuijiaShijian())) {
            ToastUtils.showShort("请输入最佳注塑时间");
            return true;
        }
        if (StringUtils.isEmpty(step7Resbean.getZuijiaSuDu())) {
            ToastUtils.showShort("请输入最佳注塑速度");
            return true;
        }
        return false;
    }


    public boolean checkDataIsLegal(List<ZhuSuResbean> zhuSuResbeanList) {
        ZhuSuResbean zhuSuResbean = zhuSuResbeanList.get(zhuSuResbeanList.size() - 1);
        if (StringUtils.isEmpty(zhuSuResbean.getZhuSuSuDu())) {
            ToastUtils.showShort("请输入注塑速度");
            return false;
        }
        if (StringUtils.isEmpty(zhuSuResbean.getZhuSuTime())) {
            ToastUtils.showShort("请输入注塑时间");
            return false;
        }

        if (StringUtils.isEmpty(zhuSuResbean.getYaLiMax())) {
            ToastUtils.showShort("请输入注塑峰值压力");
            return false;
        }
        return true;
    }
}
