package com.weimi.wmmess.business.defectRegister.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.defectRegister.bean.DefectRegisterResbean;
import com.weimi.wmmess.model.ListModel;

/**
 * Create by chhyu
 * on 2019/7/25
 * Describle:
 */
public interface IDefectRegisterView extends IBaseView {

    void onLoadDefectRegisterListSuccess(ListModel<DefectRegisterResbean> listModel);

    void onDeleteSuccess();
}
