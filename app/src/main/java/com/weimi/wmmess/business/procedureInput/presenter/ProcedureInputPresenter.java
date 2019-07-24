package com.weimi.wmmess.business.procedureInput.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.weimi.wmmess.base.BaseObserver;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.procedureInput.api.ProcedureInputService;
import com.weimi.wmmess.business.procedureInput.bean.ProcedureInputResbean;
import com.weimi.wmmess.business.procedureInput.viewInterface.IProcedureInputView;
import com.weimi.wmmess.business.workHours.api.WorkHourService;
import com.weimi.wmmess.business.workHours.bean.WorkHourListResbean;
import com.weimi.wmmess.business.workHours.viewInterface.IWorkHourView;
import com.weimi.wmmess.constants.HostAddress;
import com.weimi.wmmess.http.HttpClient;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.params.GeneralParam;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by chhyu
 * on 2019/7/23
 * Describle:
 */
public class ProcedureInputPresenter extends BasePresenter {

    private ProcedureInputService service;

    public ProcedureInputPresenter(IBaseView view) {
        super(view);
        service = HttpClient
                .builder()
                .baseUrl(HostAddress.HOST_API)
                .build()
                .getRetrofit()
                .create(ProcedureInputService.class);
    }

    /**
     * 加载工序投入列表
     *
     * @param param
     */
    public void loadProcedureInputList(GeneralParam param) {
        service.loadProcedureInputList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<ListModel<ProcedureInputResbean>>>() {
                    @Override
                    public void onSuccess(ResultModel<ListModel<ProcedureInputResbean>> result) {
                        ((IProcedureInputView) mBaseView).onLoadProcedureInputListSuccess(result.getData());
                    }
                });
    }
}
