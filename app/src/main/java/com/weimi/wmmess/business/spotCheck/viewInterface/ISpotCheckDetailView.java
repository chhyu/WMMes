package com.weimi.wmmess.business.spotCheck.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.spotCheck.bean.SpotCheckItemResbean;
import com.weimi.wmmess.business.spotCheck.bean.SpotCheckProjectResbean;
import com.weimi.wmmess.model.ListModel;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/26
 * Describle:
 */
public interface ISpotCheckDetailView extends IBaseView {

    void onLoadSpotCheckItemSuccess(List<SpotCheckItemResbean> list);

    void onCheckedSpotCheckType(String checkType,String spotCheckText);
}
