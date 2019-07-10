package com.weimi.wmmess.base;

import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.interfaces.IObserver;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 第61行 主动返回服务器错误信息
 */

public abstract class BaseObserver<T> implements Observer<T>, IObserver<T> {
    private static final String TAG = "LeeObserver";

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T model) {
        onSuccess(model);
    }

    @Override
    public void onError(Throwable e) {
//        onFailed(e.getMessage());
        String a = e.getMessage();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public abstract void onSuccess(T result);

    @Override
    public void onFailed(String errorMessage) {
        Log.e(TAG, errorMessage);
        ToastUtils.showShort(errorMessage);
    }
}
