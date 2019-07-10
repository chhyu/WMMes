package com.weimi.wmmess.business.workOrder.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;
import com.weimi.wmmess.model.ListModel;

/**
 * Create by chhyu
 * on 2019/7/2
 * Describle:
 */
public interface IWorkOrderListView extends IBaseView {

    void onLoadWorkOrderListSuccess(ListModel<WorkOrderListResbean> workOrderListResbeans);

    void onWorkOrderTypeChoosed(String typeEnum, String text);

    void onWorkOrderStateChoosed(String stateEnum, String text);

    void onCheckWorkOrderHavingParameterSuccess(boolean isHave, String id, String orderId);
}
