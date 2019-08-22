package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.api.ShiMuServive;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemItemResbean;
import com.weimi.wmmess.constants.HostAddress;
import com.weimi.wmmess.http.HttpClient;

import java.util.List;

public class ProblemRecordPresenter extends BasePresenter {
    private ShiMuServive service;

    public ProblemRecordPresenter(IBaseView view) {
        super(view);
        service = HttpClient
                .builder()
                .baseUrl(HostAddress.HOST_API)
                .build()
                .getRetrofit()
                .create(ShiMuServive.class);

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

    /**
     * 记录试模遇到的问题
     */
    public void recodeTrialPeoblem(){}
}
