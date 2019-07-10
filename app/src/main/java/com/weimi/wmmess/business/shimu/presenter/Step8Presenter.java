package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.step8.ChongTianPingHengResbean;
import com.weimi.wmmess.business.shimu.bean.step8.Step8Resbean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Step8Presenter extends BasePresenter {
    public Step8Presenter(IBaseView view) {
        super(view);
    }

    /**
     * 检查数据是否合法(暂时先判断图片和切换位置是否为空)
     *
     * @param step8Resbean
     * @return
     */
    public boolean checkDataIsLegal(Step8Resbean step8Resbean) {
        if (StringUtils.isEmpty(step8Resbean.getDuanSheOne())) {
            ToastUtils.showShort("请输入Step1数据");
            return false;
        }
        if (StringUtils.isEmpty(step8Resbean.getDuanSheTwo())) {
            ToastUtils.showShort("请输入Step2数据");
            return false;
        }
        if (StringUtils.isEmpty(step8Resbean.getDuanSheThree())) {
            ToastUtils.showShort("请输入Step3数据");
            return false;
        }
        if (StringUtils.isEmpty(step8Resbean.getDuanSheFour())) {
            ToastUtils.showShort("请输入Step4数据");
            return false;
        }
        if (StringUtils.isEmpty(step8Resbean.getDuanSheFive())) {
            ToastUtils.showShort("请输入Step5数据");
            return false;
        }
        if (StringUtils.isEmpty(step8Resbean.getDuanSheSix())) {
            ToastUtils.showShort("请输入Step6数据");
            return false;
        }
        if (StringUtils.isEmpty(step8Resbean.getDuanShePicOne())) {
            ToastUtils.showShort("请设置切换位置1处照片");
            return false;
        }
        if (StringUtils.isEmpty(step8Resbean.getDuanShePicTwo())) {
            ToastUtils.showShort("请设置切换位置2处照片");
            return false;
        }
        if (StringUtils.isEmpty(step8Resbean.getDuanShePicThree())) {
            ToastUtils.showShort("请设置切换位置3处照片");
            return false;
        }
        if (StringUtils.isEmpty(step8Resbean.getDuanShePicFour())) {
            ToastUtils.showShort("请设置切换位置4处照片");
            return false;
        }
        if (StringUtils.isEmpty(step8Resbean.getDuanShePicFive())) {
            ToastUtils.showShort("请设置切换位置5处照片");
            return false;
        }
        if (StringUtils.isEmpty(step8Resbean.getDuanShePicSix())) {
            ToastUtils.showShort("请设置切换位置6处照片");
            return false;
        }
        return true;
    }

    public boolean checkDataIsHaveNull(List<ChongTianPingHengResbean> chongTianPingHengResbeanList) {
        ChongTianPingHengResbean bean = chongTianPingHengResbeanList.get(chongTianPingHengResbeanList.size() - 1);
        if (StringUtils.isEmpty(bean.getXueHao())) {
            ToastUtils.showShort("请输入穴号");
            return true;
        }
        if (StringUtils.isEmpty(bean.getOne())) {
            ToastUtils.showShort("请输入第一列数据");
            return true;
        }
        if (StringUtils.isEmpty(bean.getTwo())) {
            ToastUtils.showShort("请输入第二列数据");
            return true;
        }
        if (StringUtils.isEmpty(bean.getThree())) {
            ToastUtils.showShort("请输入第三列数据");
            return true;
        }
        if (StringUtils.isEmpty(bean.getFour())) {
            ToastUtils.showShort("请输入第四列数据");
            return true;
        }
        if (StringUtils.isEmpty(bean.getFive())) {
            ToastUtils.showShort("请输入第五列数据");
            return true;
        }
        if (StringUtils.isEmpty(bean.getSix())) {
            ToastUtils.showShort("请输入第六列数据");
            return true;
        }
        return false;
    }
}
