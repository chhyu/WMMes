package com.weimi.wmmess.business.defectRegister.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.defectRegister.bean.DefectParentResbean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/25
 * Describle:
 */
public interface IChooseDefectView extends IBaseView {

    void onLoadDefectTreeSuccess(List<DefectParentResbean> list);
}
