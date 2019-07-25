package com.weimi.wmmess.business.procedureOutput.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputDetailResbean;

/**
 * Create by chhyu
 * on 2019/7/25
 * Describle:
 */
public interface IAddProcedureOutputView extends IBaseView {

    void onInsertProcedureInputSuccess();

    void onUpdateProcedureInputSuccess();

    void onGetProcedureDetailSuccess(ProcedureOutputDetailResbean procedureOutputDetailResbean);
}
