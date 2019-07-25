package com.weimi.wmmess.business.procedureInput.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.procedureInput.bean.PorcedureInputDetailResbean;
import com.weimi.wmmess.business.procedureInput.bean.ProcedureInputResbean;
import com.weimi.wmmess.model.ListModel;

/**
 * Create by chhyu
 * on 2019/7/24
 * Describle:
 */
public interface IAddProcedureInputView extends IBaseView {

    void onInsertProcedureInputSuccess();

    void onGetProcedureDetailSuccess(PorcedureInputDetailResbean porcedureInputDetailResbean);

    void onUpdateProcedureInputSuccess();
}
