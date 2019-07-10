package com.weimi.wmmess.base;

import android.support.annotation.Keep;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.base.interfaces.IPresenter;

/**
 * <p>Presenter的基类</p>
 */
@Keep
public abstract class BasePresenter implements IPresenter {
    protected IBaseView mBaseView;

    public BasePresenter(IBaseView view) {
        mBaseView = view;
    }

    /**
     * 对 ACTIVITY 生命周期进行管理
     *
     * @return
     */
    protected final LifecycleProvider getLifecycleProvider() {
        LifecycleProvider provider = null;
        if (null != mBaseView && mBaseView instanceof LifecycleProvider) {
            provider = (LifecycleProvider) mBaseView;
        }
        return provider;
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {
        mBaseView = null;
    }
}