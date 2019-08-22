package com.weimi.wmmess.business.workOrder.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.spotCheck.bean.EquipmentTypeResbean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/27
 * Describle:
 */
public interface IChooseEquipmentTypeView extends IBaseView {

    void onGetEquipmentTypeSuccess(List<EquipmentTypeResbean> list);
}
