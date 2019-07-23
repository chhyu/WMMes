package com.weimi.wmmess.business.workHours.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.workHours.bean.WorkTypeResbean;
import com.weimi.wmmess.model.ListModel;

/**
 * Create by chhyu
 * on 2019/7/23
 * Describle:
 */
public interface IChooseWorkTypeView extends IBaseView {

    void onLoadWorkTypeSuccess(ListModel<WorkTypeResbean> listModel);
}
