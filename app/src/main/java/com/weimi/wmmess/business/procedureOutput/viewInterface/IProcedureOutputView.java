package com.weimi.wmmess.business.procedureOutput.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputResbean;
import com.weimi.wmmess.model.ListModel;

/**
 * Create by chhyu
 * on 2019/7/24
 * Describle:
 */
public interface IProcedureOutputView extends IBaseView {

    void onLoadProcedureOutputSuccess(ListModel<ProcedureOutputResbean> listModel);

    void onDeteleProcedureOutputItemSuccess();

}
