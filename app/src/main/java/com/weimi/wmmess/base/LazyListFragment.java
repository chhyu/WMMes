package com.weimi.wmmess.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by chhyu on 2019/6/28.
 */
public abstract class LazyListFragment<P extends BasePresenter> extends BaseFragment<P> {
    // 当前可见标识
    protected boolean isVisible;
    private boolean isViewCreated;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
    }

    /**
     * 在这里实现Fragment数据的缓加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreated && getUserVisibleHint()) {
            isVisible = true;
            isViewCreated = false;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 当前可见时执行的函数
     */
    protected abstract void lazyLoad();

    protected void onInvisible() {

    }

}
