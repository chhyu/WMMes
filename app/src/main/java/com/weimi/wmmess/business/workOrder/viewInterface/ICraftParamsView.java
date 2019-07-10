package com.weimi.wmmess.business.workOrder.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.params.MobileTrialModeSaveParam;
import com.weimi.wmmess.business.workOrder.bean.CraftRebean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/4
 * Describle:
 */
public interface ICraftParamsView extends IBaseView {

    void onLoadCraftParamsListSuccessed(List<CraftRebean> craftParamsResbeanList);

}
