package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemItemResbean;

import java.util.List;

public class ProblemRecordPresenter extends BasePresenter {
    public ProblemRecordPresenter(IBaseView view) {
        super(view);
    }

    public boolean checkDataIsLegal(List<ProblemItemResbean> problemItemResbeans) {
        int lastPosition = problemItemResbeans.size() - 1;
        if (StringUtils.isEmpty(problemItemResbeans.get(lastPosition).getDescribe())) {
            ToastUtils.showShort("请输入问题描述");
            return false;
        }
        if (StringUtils.isEmpty(problemItemResbeans.get(lastPosition).getReason())) {
            ToastUtils.showShort("请输入问题原因");
            return false;
        }
        if (StringUtils.isEmpty(problemItemResbeans.get(lastPosition).getSolution())) {
            ToastUtils.showShort("请输入问题解决方法");
            return false;
        }
        if (StringUtils.isEmpty(problemItemResbeans.get(lastPosition).getCompleteDate())) {
            ToastUtils.showShort("请输入问题完成日期");
            return false;
        }
        if (StringUtils.isEmpty(problemItemResbeans.get(lastPosition).getPerson())) {
            ToastUtils.showShort("请输入问题完成日期");
            return false;
        }
        return true;
    }
}
