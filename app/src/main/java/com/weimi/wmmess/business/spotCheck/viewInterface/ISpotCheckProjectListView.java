package com.weimi.wmmess.business.spotCheck.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.spotCheck.bean.SpotCheckProjectResbean;
import com.weimi.wmmess.model.ListModel;

/**
 * Create by chhyu
 * on 2019/7/26
 * Describle:
 */
public interface ISpotCheckProjectListView extends IBaseView {

    void onLoadSpotCheckProjectListSuccess(ListModel<SpotCheckProjectResbean> listModel);
}
