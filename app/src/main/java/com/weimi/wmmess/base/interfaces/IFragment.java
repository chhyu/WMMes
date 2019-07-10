package com.weimi.wmmess.base.interfaces;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Fragment初始化接口
 *
 * Created by Jason on 2018/8/15.
 */
public interface IFragment {
    @LayoutRes
    int initLayout();

    void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView);

    void initData();
}
