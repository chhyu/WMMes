package com.weimi.wmmess.business.workHours.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.workHours.bean.WorkCenterResbean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public interface IChooseWorkCenterView extends IBaseView {

    void onLoadWorkCenterSuccess(List<WorkCenterResbean> list);
}
