package com.weimi.wmmess.base;

import android.support.annotation.Keep;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

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

    /**
     * 给popupwindow添加动画
     *
     * @param popView
     */
    protected void showAnimation(View popView) {
        AnimationSet animationSet = new AnimationSet(false);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1.0f);
        alphaAnimation.setDuration(300);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f
        );
        translateAnimation.setDuration(300);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        popView.startAnimation(animationSet);
    }
}