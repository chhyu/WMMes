package com.weimi.wmmess.business.shimu.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.history.HistoryResbean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/9
 * Describle:
 */
public interface IShiMuHistoryView extends IBaseView {

    void onLoadShiMuRecodeSuccess(List<HistoryResbean> historyResbeans);
}
