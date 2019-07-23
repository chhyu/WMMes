package com.weimi.wmmess.base;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.weimi.wmmess.base.interfaces.IObserver;
import com.weimi.wmmess.model.ResultModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public abstract class WMObserver<T> implements Observer<ResultModel<T>>, IObserver<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResultModel<T> result) {
        if (result == null) {
            onFailed("请求错误，请稍后再试");
        } else if (result.getStatus().equals("1")) {
            onSuccess(result.getData());
        } else {
            String status = result.getStatus();
            if (!StringUtils.isEmpty(status)) {
                onFailed(String.format("Code:%s %s", status, "error"));
            } else {
                onFailed("请求错误，请稍后再试");
            }
        }
    }

    @Override
    public void onError(Throwable t) {
        onFailed("请求错误，请稍后再试");
        Logger.e(t, "");
//        Activity activity = ActivityUtils.getTopActivity();
//        if (activity instanceof BaseActivity) {
//            t.printStackTrace();
//            ((BaseActivity) activity).showErrorPrompt("请求失败", false);
//        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onFailed(String msg) {
        ToastUtils.showLong(msg);
//        Activity activity = ActivityUtils.getTopActivity();
//        if (activity instanceof BaseActivity) {
//            ((BaseActivity) activity).showFailedPrompt(msg, false);
//        }
    }
}

