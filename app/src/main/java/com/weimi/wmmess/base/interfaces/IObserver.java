package com.weimi.wmmess.base.interfaces;

/**
 * 数据监听者
 * <p>
 * Created by chhyu on 2019/6/28.
 */
public interface IObserver<T> {
    void onSuccess(T t);

    void onFailed(String msg);
}
