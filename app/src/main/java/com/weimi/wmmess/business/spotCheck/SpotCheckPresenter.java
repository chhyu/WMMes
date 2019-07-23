package com.weimi.wmmess.business.spotCheck;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.weimi.wmmess.base.BaseObserver;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.spotCheck.api.SpotCheckService;
import com.weimi.wmmess.constants.HostAddress;
import com.weimi.wmmess.http.HttpClient;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by chhyu
 * on 2019/7/19
 * Describle:
 */
public class SpotCheckPresenter extends BasePresenter {
    private SpotCheckService service;

    public SpotCheckPresenter(IBaseView view) {
        super(view);
        service = HttpClient
                .builder()
                .baseUrl(HostAddress.HOST_API)
                .build()
                .getRetrofit()
                .create(SpotCheckService.class);
    }

    public void getSpotCheckProjectList() {
        service.getSpotCheckProjectList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<ListModel<Object>>>() {

                    @Override
                    public void onSuccess(ResultModel<ListModel<Object>> result) {
                        Log.e("lmsg", "spotCheckList==" + JSON.toJSONString(result.getData().getRecords()));
                    }
                });
    }
}
