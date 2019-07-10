package com.weimi.wmmess.business.shimu.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.craftParams.CraftConfirmResbean;
import com.weimi.wmmess.business.shimu.params.MobileTrialModeSaveParam;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/8
 * Describle:
 */
public interface ICraftConfirmView extends IBaseView {

    void onLoadCraftParamsSuccess(List<CraftConfirmResbean> list);

    void onSaveCraftParamsSuccess();
}
