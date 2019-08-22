package com.weimi.wmmess.business.spotCheck.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.spotCheck.bean.ModelFrameResbean;
import com.weimi.wmmess.model.ListModel;

/**
 * Create by chhyu
 * on 2019/7/27
 * Describle:
 */
public interface IChooseModelFrameView extends IBaseView {

    void onGetModelFrameListSuccess(ListModel<ModelFrameResbean> listModel);
}
