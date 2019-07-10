package com.weimi.wmmess.base.interfaces;


import android.support.annotation.Keep;
import android.view.View;

/**
 * <p>View接口的基类</p>
 */
@Keep
public interface IBaseView{
    void showProgress();

    void showProgress(String msg);

    void hideProgress();

}
