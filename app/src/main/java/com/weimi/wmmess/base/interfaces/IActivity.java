package com.weimi.wmmess.base.interfaces;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

/**
 * Activity初始化接口
 * <p>
 * Created by chhyu on 2019/4/27.
 */
public interface IActivity {
    @LayoutRes
    int initLayout();

    void initView(@Nullable Bundle savedInstanceState);

    void initData();

    boolean useEventBus();
}
