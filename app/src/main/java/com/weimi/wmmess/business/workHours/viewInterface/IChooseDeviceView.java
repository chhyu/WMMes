package com.weimi.wmmess.business.workHours.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.workHours.bean.DeviceParentResbean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public interface IChooseDeviceView extends IBaseView {

    void onLoadDeviceListSuccess(List<DeviceParentResbean> list);
}
