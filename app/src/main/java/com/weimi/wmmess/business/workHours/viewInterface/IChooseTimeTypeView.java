package com.weimi.wmmess.business.workHours.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.workHours.bean.WorkTimeTypeResbean;
import com.weimi.wmmess.model.ListModel;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public interface IChooseTimeTypeView extends IBaseView {

    void onLoadWorkTimeTypeSuccess(ListModel<WorkTimeTypeResbean> listModel);
}
