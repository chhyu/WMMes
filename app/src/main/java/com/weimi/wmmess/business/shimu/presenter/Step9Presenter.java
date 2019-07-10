package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaTimeResbean;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaYaLiResbean;
import com.weimi.wmmess.business.shimu.bean.step9.Step9Resbean;

import java.util.List;

public class Step9Presenter extends BasePresenter {
    public Step9Presenter(IBaseView view) {
        super(view);
    }

    public boolean checkDataIsHaveNull(Step9Resbean step9Resbean) {
        if (StringUtils.isEmpty(step9Resbean.getYaLiMin())) {
            ToastUtils.showShort("请输入最小保压压力");
            return true;
        }
        if (StringUtils.isEmpty(step9Resbean.getYaLiMax())) {
            ToastUtils.showShort("请输入最大保压压力");
            return true;
        }
        if (StringUtils.isEmpty(step9Resbean.getYaLiConfirm())) {
            ToastUtils.showShort("请输入确认保压压力");
            return true;
        }

        if (StringUtils.isEmpty(step9Resbean.getTimeMin())) {
            ToastUtils.showShort("请输入最小保压时间");
            return true;
        }
        if (StringUtils.isEmpty(step9Resbean.getTimeMax())) {
            ToastUtils.showShort("请输入最大保压时间");
            return true;
        }
        if (StringUtils.isEmpty(step9Resbean.getTimeConfirm())) {
            ToastUtils.showShort("请输入确认保压时间");
            return true;
        }
        return false;
    }

    public boolean checkYaLiDataIsLegal(List<BaoYaYaLiResbean> baoYaYaLiResbeans) {
        //判断保压压力是否没填
        if (StringUtils.isEmpty(baoYaYaLiResbeans.get(baoYaYaLiResbeans.size() - 1).getYali()) || baoYaYaLiResbeans.get(baoYaYaLiResbeans.size() - 1).getYali().equals("0")) {
            ToastUtils.showShort("请填写保压压力再进行添加");
            return false;
        }
        //判断shot1是否没填
        if (StringUtils.isEmpty(baoYaYaLiResbeans.get(baoYaYaLiResbeans.size() - 1).getYaLiOne()) || baoYaYaLiResbeans.get(baoYaYaLiResbeans.size() - 1).getYaLiOne().equals("0")) {
            ToastUtils.showShort("请填写当前压力第一模重量再进行添加");
            return false;
        }
        //判断shot2是否没填
        if (StringUtils.isEmpty(baoYaYaLiResbeans.get(baoYaYaLiResbeans.size() - 1).getYaLiTwo()) || baoYaYaLiResbeans.get(baoYaYaLiResbeans.size() - 1).getYaLiTwo().equals("0")) {
            ToastUtils.showShort("请填写当前压力第二模重量再进行添加");
            return false;
        }
        //判断shot3是否没填
        if (StringUtils.isEmpty(baoYaYaLiResbeans.get(baoYaYaLiResbeans.size() - 1).getYaLiThree()) || baoYaYaLiResbeans.get(baoYaYaLiResbeans.size() - 1).getYaLiThree().equals("0")) {
            ToastUtils.showShort("请填写当前压力第三模重量再进行添加");
            return false;
        }
        return true;
    }

    public boolean checkTimeDataIsLegal(List<BaoYaTimeResbean> baoYaTimeResbeanList) {
        //判断保压压力是否没填
        if (StringUtils.isEmpty(baoYaTimeResbeanList.get(baoYaTimeResbeanList.size() - 1).getTime()) || baoYaTimeResbeanList.get(baoYaTimeResbeanList.size() - 1).getTime().equals("0")) {
            ToastUtils.showShort("请填写保压压力再进行添加");
            return false;
        }
        //判断shot1是否没填
        if (StringUtils.isEmpty(baoYaTimeResbeanList.get(baoYaTimeResbeanList.size() - 1).getTimeOne()) || baoYaTimeResbeanList.get(baoYaTimeResbeanList.size() - 1).getTimeOne().equals("0")) {
            ToastUtils.showShort("请填写当前压力第一模重量再进行添加");
            return false;
        }
        //判断shot2是否没填
        if (StringUtils.isEmpty(baoYaTimeResbeanList.get(baoYaTimeResbeanList.size() - 1).getTimeTwo()) || baoYaTimeResbeanList.get(baoYaTimeResbeanList.size() - 1).getTimeTwo().equals("0")) {
            ToastUtils.showShort("请填写当前压力第二模重量再进行添加");
            return false;
        }
        //判断shot3是否没填
        if (StringUtils.isEmpty(baoYaTimeResbeanList.get(baoYaTimeResbeanList.size() - 1).getTimeThree()) || baoYaTimeResbeanList.get(baoYaTimeResbeanList.size() - 1).getTimeThree().equals("0")) {
            ToastUtils.showShort("请填写当前压力第三模重量再进行添加");
            return false;
        }
        return true;
    }
}
