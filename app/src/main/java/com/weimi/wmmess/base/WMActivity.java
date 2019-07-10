package com.weimi.wmmess.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.permission.PermissionsManager;
import com.weimi.wmmess.base.permission.PermissionsResultAction;

public abstract class WMActivity<P extends BasePresenter> extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        checkPermissionIfNecessary();
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    private Fragment saveFg;

    protected void switchFragment(final int rid, Fragment to) {
        if (to == null) {
            // may throw you a IllegalArgumentException be better
            return;
        }
        if (saveFg != to) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                if (saveFg != null) {
                    ft.hide(saveFg);
                }
                ft.add(rid, to).show(to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                if (saveFg != null) {
                    ft.hide(saveFg);
                }
                ft.show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            saveFg = to;
        }
    }
}
