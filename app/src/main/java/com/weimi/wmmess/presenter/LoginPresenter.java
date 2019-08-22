package com.weimi.wmmess.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.weimi.wmmess.api.MainServive;
import com.weimi.wmmess.base.BaseObserver;
import com.weimi.wmmess.base.CommonPresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.constants.HostAddress;
import com.weimi.wmmess.http.HttpClient;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.params.LoginParams;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.utils.RSAEncryptUtils;
import com.weimi.wmmess.viewInterface.ILoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by chhyu
 * on 2019/6/28
 * Describle:
 */
public class LoginPresenter extends CommonPresenter {
    private MainServive service;

    public LoginPresenter(IBaseView view) {
        super(view);
        service = HttpClient
                .builder()
                .baseUrl(HostAddress.HOST_API)
                .build()
                .getRetrofit()
                .create(MainServive.class);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    public void doLogin(String username, String password) {
        LoginParams user = new LoginParams();
        user.setLoginName(username);
        String pwd = RSAEncryptUtils.encrypt(password);
        user.setPassword(pwd);

        service.doLogin(user) //获取Token
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
//                .doOnSubscribe(disposable -> mBaseView.showProgress())
//                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<String>>() {

                    @Override
                    public void onSuccess(ResultModel<String> result) {
                        Log.e("lmsg", "onSuccess==" + JSON.toJSONString(result));
                        if(result.getStatus().equals("1")){
                            ConfigUtils.setTicket(result.getData());
                            ((ILoginView) mBaseView).onLoginSuccess();
                        }else {
                            onFailed(result.getStatus());
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.e("lmsg", "onFailed==" + JSON.toJSONString(msg));
                        ((ILoginView) mBaseView).onLoginFailed(msg);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("lmsg", "onFailed==" + JSON.toJSONString(t.getMessage()));
                        ((ILoginView) mBaseView).onLoginError(t.getMessage());
                    }
                });
    }
}
