package com.weimi.wmmess.business.defectRegister.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.defectRegister.bean.DefectDetailResbean;

/**
 * Create by chhyu
 * on 2019/7/25
 * Describle:
 */
public interface IAddDefectRegisterView extends IBaseView {

    void onAddSuccess();

    void onUpdateSuccess();

    void onLoadDefectDetailSuccess(DefectDetailResbean defectDetailResbean);
}
