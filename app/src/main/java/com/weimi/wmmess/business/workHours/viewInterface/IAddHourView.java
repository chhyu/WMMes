package com.weimi.wmmess.business.workHours.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.workHours.bean.WorkHourDetailResbean;

/**
 * Create by chhyu
 * on 2019/7/23
 * Describle:
 */
public interface IAddHourView extends IBaseView {

    void onAddHourSuccess();

    void onUpdateHourSuccess();

    void onGetWorkHourDetailSuccess(WorkHourDetailResbean workHourDetailResbean);
}
