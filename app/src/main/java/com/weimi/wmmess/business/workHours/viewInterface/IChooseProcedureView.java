package com.weimi.wmmess.business.workHours.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.workHours.bean.ProcedureResbean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public interface IChooseProcedureView extends IBaseView {

    void onLoadProcedureListSuccess(List<ProcedureResbean> list);
}
