package com.weimi.wmmess.business.shimu.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.craftParams.ModelInfoResbean;

/**
 * Create by chhyu
 * on 2019/7/9
 * Describle:
 */
public interface IModelView extends IBaseView {

    void getModelInfoSuccess(ModelInfoResbean modelInfoResbean);
}
