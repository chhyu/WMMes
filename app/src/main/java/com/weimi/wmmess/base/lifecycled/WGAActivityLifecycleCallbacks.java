package com.weimi.wmmess.base.lifecycled;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.weimi.wmmess.base.interfaces.IActivity;
import com.weimi.wmmess.base.interfaces.IToolbar;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Jason on 2018/8/15.
 */
public class WGAActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    /**
     * 是否从后台启用
     */
    private boolean fromBack = false;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // 调用IActivity接口
        if (activity instanceof IActivity) {
            activity.setContentView(((IActivity) activity).initLayout());

            ((IToolbar) activity).initToolbar();

            ((IActivity) activity).initView(savedInstanceState);
            ((IActivity) activity).initData();
            if (((IActivity) activity).useEventBus()) {
                EventBus.getDefault().register(activity);
            }
        }
        //禁止应用内截屏
//        if (!AppUtils.isAppDebug()) {
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
//        }

        //强制横屏  已确定所有界面全部横屏
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        //验证是否有指纹锁
//        if (BaseApplication.getInstance().isLockLive() && !(activity instanceof ILifecycleFilterView)) { //开启验证
//            openLockActivity(activity);
//        }
        if (fromBack) {
            onFromBack();
            fromBack = false;
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
//        if (isBackground(activity) && hasLock()) {
//            BaseApplication.getInstance().setLockLive(true);
//        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activity instanceof IActivity) {
            if (((IActivity) activity).useEventBus()) {
                EventBus.getDefault().unregister(activity);
            }
        }
    }

    /**
     * 是否设置指纹锁或手势锁
     *
     * @return
     */
//    private boolean hasLock() {
//        return SPUtils.getInstance().getBoolean(ConstantValues.SF_Fingerprint) ||
//                !StringUtils.isEmpty(SPUtils.getInstance().getString(ConstantValues.SF_GestureLock));
//    }

    /**
     * 打开指纹解锁或手势解锁界面
     *
     * @param activity
     */
//    private void openLockActivity(Activity activity) {
//        List<String> filterActivity = Arrays.asList(
//                "com.netrust.module.main.SplashActivity",
//                "com.netrust.module.main.login.LoginActivity",
//                "com.netrust.module.main.activity.GestureLockActivity",
//                "com.netrust.module.main.activity.FingerLockActivity");
//
//        String clzName = activity.getClass().getName();
//        if (!filterActivity.contains(clzName)) {
//            if (SPUtils.getInstance().getBoolean(ConstantValues.SF_Fingerprint)) {  //指纹锁
//                ARouter.getInstance()
//                        .build(RoutePath.MAIN_FINGER)
//                        .withTransition(R.anim.anim_main_alpha_enter_400, R.anim.anim_main_alpha_exit_400)
//                        .navigation();
//            } else if (!StringUtils.isEmpty(SPUtils.getInstance().getString(ConstantValues.SF_GestureLock))) {  //手势锁
//                ARouter.getInstance()
//                        .build(RoutePath.MAIN_GESTURE)
//                        .withTransition(R.anim.anim_main_alpha_enter_400, R.anim.anim_main_alpha_exit_400)
//                        .navigation();
//            }
//        }
//    }

    /**
     * 从后台回到前台
     */
    private void onFromBack() {

    }

}
