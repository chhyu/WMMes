package com.weimi.wmmess.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.interfaces.IActivity;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.base.interfaces.IToolbar;
import com.weimi.wmmess.base.permission.PermissionsManager;
import com.weimi.wmmess.base.permission.PermissionsResultAction;
import com.weimi.wmmess.widget.kprogresshud.KProgressHUD;
import com.weimi.wmmess.widget.kprogresshud.LoadingView;

/**
 * Created by Jason on 2018/7/17.
 */
public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements IActivity, IToolbar, IBaseView {

    //region Variable
    private boolean destroyed = false;
    private Handler handler;
    private Toast toast;
    protected P mPresenter;
    private KProgressHUD hud;
    private KProgressHUD loadHud;
    private Toolbar toolbar;
    private TextView tvTitle;
    protected Activity mActivity;
    /**
     * 上次点击时间
     */
    private long lastClick = 0;
    //endregion

    //region Lifecycle
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
        requestPermissionOfNecessary();
    }

    private void requestPermissionOfNecessary() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                Log.e("lmsg", "pass");
            }

            @Override
            public void onDenied(String permission) {
                Log.e("lmsg", "refuse");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyed = true;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
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

    //region IToolbar
    @Override
    public void initToolbar() {
        //这里全局给Activity设置toolbar和title
        if (findViewById(R.id.toolbar) != null) { //找到 Toolbar 并且替换 Actionbar
            toolbar = findViewById(R.id.toolbar);
            if (this instanceof AppCompatActivity) {
                setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(v -> finish());
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setActionBar((android.widget.Toolbar) findViewById(R.id.toolbar));
                    getActionBar().setDisplayShowTitleEnabled(false);
                    ((android.widget.Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(v -> finish());
                }
            }

            if (findViewById(R.id.tvToolTitle) != null) {
                tvTitle = findViewById(R.id.tvToolTitle);
                tvTitle.setText(getTitle());
            }
        }
    }

    @Override
    public Toolbar getToolbar() {
        return this.toolbar;
    }

    @Override
    public void setTitle(int titleId) {
        if (tvTitle != null) {
            tvTitle.setText(titleId);
        } else {
            super.setTitle(titleId);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        } else {
            setTitle(title);
        }
    }

    //endregion

    //region HUD
    @Override
    public void showProgress() {
        showProgress("");
    }

    @Override
    public void showProgress(String msg) {
        if (hud == null) {
            hud = KProgressHUD.create(this)
                    .setCancellable(true)
                    .setCustomView(new LoadingView(this));
            if (!StringUtils.isEmpty(msg)) {
                hud.setLabel(msg);
            }
        }
        if (hud != null && !hud.isShowing()) {
            hud.show();
        }
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
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imgId);
        if (loadHud == null) {
            loadHud = KProgressHUD.create(this)
                    .setCancellable(true)
                    .setBackgroundColor(ContextCompat.getColor(this, R.color.md_grey_800));
        }
        loadHud.setCustomView(imageView)
                .setLabel(msg)
                .show();

        getHandler().postDelayed(() ->
        {
            loadHud.dismiss();
            if (finish) {
                finish();
            }
        }, 2000);
    }
    //endregion

    //region Public
    @Override
    public boolean useEventBus() {
        return false;
    }

    public void toastLong(CharSequence msg) {
        if (null == toast) {
            toast = Toast.makeText(Utils.getApp(), msg, Toast.LENGTH_LONG);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }

    public void toastShort(CharSequence msg) {
        if (null == toast) {
            toast = Toast.makeText(Utils.getApp(), msg, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }

    public boolean isDestroyedCompatible() {
        if (Build.VERSION.SDK_INT >= 17) {
            return isDestroyedCompatible17();
        } else {
            return destroyed || super.isFinishing();
        }
    }

    @TargetApi(17)
    private boolean isDestroyedCompatible17() {
        return super.isDestroyed();
    }

    protected final Handler getHandler() {
        if (handler == null) {
            handler = new Handler(getMainLooper());
        }
        return handler;
    }

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

    //endregion

/*    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (startActivitySelfCheck(intent)) {
            // 查看源码得知 startActivity 最终也会调用 startActivityForResult
            super.startActivityForResult(intent, requestCode, options);
        }
    }

    private String mActivityJumpTag;
    private long mActivityJumpTime;

    *//**
     * 检查当前 Activity 是否重复跳转了，不需要检查则重写此方法并返回 true 即可
     *
     * @param intent          用于跳转的 Intent 对象
     * @return 检查通过返回true, 检查不通过返回false
     *//*
    protected boolean startActivitySelfCheck(Intent intent) {
        // 默认检查通过
        boolean result = true;
        // 标记对象
        String tag;
        if (intent.getComponent() != null) { // 显式跳转
            tag = intent.getComponent().getClassName();
        }else if (intent.getAction() != null) { // 隐式跳转
            tag = intent.getAction();
        }else {
            return result;
        }

        if (tag.equals(mActivityJumpTag) && mActivityJumpTime >= SystemClock.uptimeMillis() - 500) {
            // 检查不通过
            result = false;
        }

        // 记录启动标记和时间
        mActivityJumpTag = tag;
        mActivityJumpTime = SystemClock.uptimeMillis();
        return result;
    }*/
}
