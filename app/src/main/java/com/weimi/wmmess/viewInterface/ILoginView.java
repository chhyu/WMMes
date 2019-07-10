package com.weimi.wmmess.viewInterface;

import com.weimi.wmmess.base.interfaces.IBaseView;

/**
 * Create by chhyu
 * on 2019/6/28
 * Describle:
 */
public interface ILoginView extends IBaseView {

    void onLoginSuccess();

    void onLoginFailed(String msg);

    void onLoginError(String msg);
}
