package com.weimi.wmmess.business.workHours.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;
import com.weimi.wmmess.model.ListModel;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public interface IChooseWorkOrderView extends IBaseView {

    void onLoadWorkOrderListSuccess(ListModel<WorkOrderListResbean> listModel);
}
