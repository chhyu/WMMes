package com.weimi.wmmess.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.base.interfaces.IFragment;
import com.weimi.wmmess.widget.kprogresshud.KProgressHUD;
import com.weimi.wmmess.widget.kprogresshud.LoadingView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Jason on 2018/7/19.
 */
public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements IFragment,IBaseView {
    //region Variable
    private final Handler handler = new Handler();
    private boolean destroyed;
    protected P mPresenter;
    private KProgressHUD hud;
    private KProgressHUD loadHud;
    private long lastClick = 0;
    protected Activity mActivity;
    protected View rootView;
    //endregion

    //region Lifecycle
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        destroyed = false;
        //如果要使用eventbus请将此方法返回true
        if (useEventBus()) {
            EventBus.getDefault().register(this);//注册到事件主线
        }
        initData();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(initLayout(), container, false);
        initView(savedInstanceState, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyed = true;
        //如果要使用eventbus请将此方法返回true
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
        if (hud != null) {
            hud.dismiss();
            hud = null;
        }
        if (loadHud != null) {
            loadHud.dismiss();
            loadHud = null;
        }
    }
    //endregion

    //region HUD
    @Override
    public void showProgress() {
        showProgress("加载中...");
    }

    @Override
    public void showProgress(String msg) {
        if (hud == null) {
            hud = KProgressHUD.create(mActivity)
                    .setCustomView(new LoadingView(mActivity))
                    .setLabel(msg);
        }
        hud.show();
    }

    @Override
    public void hideProgress() {
        if (hud != null) {
            hud.dismiss();
        }
    }

    public void showSuccessPrompt(@StringRes int msg, boolean finish) {
        showSuccessPrompt(getString(msg), finish);
    }

    public void showSuccessPrompt(String msg, boolean finish) {
        showLoadHud(R.drawable.comm_icon_load_success, msg, finish);
    }

    public void showFailedPrompt(@StringRes int msg, boolean finish) {
        showFailedPrompt(getString(msg), finish);
    }

    public void showFailedPrompt(String msg, boolean finish) {
        showLoadHud(R.drawable.comm_icon_load_failed, msg, finish);
    }

    public void showErrorPrompt(@StringRes int msg, boolean finish) {
        showFailedPrompt(getString(msg), finish);
    }

    public void showErrorPrompt(String msg, boolean finish) {
        showLoadHud(R.drawable.comm_icon_load_error, msg, finish);
    }

    private void showLoadHud(@DrawableRes int imgId, String msg, boolean finish) {
        ImageView imageView = new ImageView(mActivity);
        imageView.setImageResource(imgId);
        if (loadHud == null) {
            loadHud = KProgressHUD.create(mActivity);
        }
        loadHud.setCustomView(imageView)
                .setLabel(msg)
                .show();

        getHandler().postDelayed(() ->
        {
            loadHud.dismiss();
            if (finish) {
                mActivity.finish();
            }
        }, 2000);
    }
    //endregion

    //region Public

    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

    protected final boolean isDestroyed() {
        return destroyed;
    }

    protected final Handler getHandler() {
        return handler;
    }

    protected final void postRunnable(final Runnable runnable) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                // validate
                // TODO use getActivity ?
                if (!isAdded()) {
                    return;
                }

                // run
                runnable.run();
            }
        });
    }

    protected final void postDelayed(final Runnable runnable, long delay) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // validate
                // TODO use getActivity ?
                if (!isAdded()) {
                    return;
                }

                // run
                runnable.run();
            }
        }, delay);
    }

    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

}
