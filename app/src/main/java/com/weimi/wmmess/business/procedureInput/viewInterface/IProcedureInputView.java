package com.weimi.wmmess.business.procedureInput.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.procedureInput.bean.ProcedureInputResbean;
import com.weimi.wmmess.model.ListModel;

/**
 * Create by chhyu
 * on 2019/7/23
 * Describle:
 */
public interface IProcedureInputView extends IBaseView {

    void onLoadProcedureInputListSuccess(ListModel<ProcedureInputResbean> listModel);

    void onDeteleProcedureInputItemSuccess();
}
