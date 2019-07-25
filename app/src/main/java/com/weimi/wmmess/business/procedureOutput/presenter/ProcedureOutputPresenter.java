package com.weimi.wmmess.business.procedureOutput.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BaseObserver;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.WMObserver;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.procedureInput.bean.PorcedureInputDetailResbean;
import com.weimi.wmmess.business.procedureInput.params.WmMesWorkshopProcedureInputRegister;
import com.weimi.wmmess.business.procedureInput.viewInterface.IAddProcedureInputView;
import com.weimi.wmmess.business.procedureInput.viewInterface.IProcedureInputView;
import com.weimi.wmmess.business.procedureOutput.api.ProcedureOutputService;
import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputDetailResbean;
import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputResbean;
import com.weimi.wmmess.business.procedureOutput.params.WmMesWorkshopProcedureOutputRegister;
import com.weimi.wmmess.business.procedureOutput.viewInterface.IAddProcedureOutputView;
import com.weimi.wmmess.business.procedureOutput.viewInterface.IProcedureOutputView;
import com.weimi.wmmess.business.workHours.api.WorkHourService;
import com.weimi.wmmess.business.workHours.bean.WorkHourListResbean;
import com.weimi.wmmess.business.workHours.viewInterface.IWorkHourView;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.constants.HostAddress;
import com.weimi.wmmess.http.HttpClient;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.params.GeneralParam;

import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by chhyu
 * on 2019/7/24
 * Describle:
 */
public class ProcedureOutputPresenter extends BasePresenter {

    private ProcedureOutputService service;

    public ProcedureOutputPresenter(IBaseView view) {
        super(view);
        service = HttpClient
                .builder()
                .baseUrl(HostAddress.HOST_API)
                .build()
                .getRetrofit()
                .create(ProcedureOutputService.class);
    }

    public void loadProcedureOutputList(GeneralParam param) {
        service.loadProcedureOutputList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<ListModel<ProcedureOutputResbean>>() {
                    @Override
                    public void onSuccess(ListModel<ProcedureOutputResbean> listModel) {
                        ((IProcedureOutputView) mBaseView).onLoadProcedureOutputSuccess(listModel);
                    }
                });
    }

    /**
     * 检查数据和新增
     *
     * @param params
     */
    public void checkDataAndInsertProcedureOut(WmMesWorkshopProcedureOutputRegister params) {
        if (!checkDataIsLegal(params)) {
            return;
        }
        service.insertMesWorkshopProcedureOutput(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<Object>>() {
                    @Override
                    public void onSuccess(ResultModel<Object> result) {
                        String status = result.getStatus();
                        if (status.equals(Constants.RESPONSE_SUCCESS)) {
                            ((IAddProcedureOutputView) mBaseView).onInsertProcedureInputSuccess();
                        } else {
                            ToastUtils.showShort("新增失败，请稍后再试");
                        }
                    }
                });
    }

    /**
     * 检查数据是否合法
     *
     * @param params
     * @return
     */
    private boolean checkDataIsLegal(WmMesWorkshopProcedureOutputRegister params) {
        if (StringUtils.isEmpty(params.getWorkOrderId())) {
            ToastUtils.showShort("请选择工单");
            return false;
        }
        if (StringUtils.isEmpty(params.getEquipmentId())) {
            ToastUtils.showShort("请选择设备");
            return false;
        }

        if (StringUtils.isEmpty(params.getEmpId())) {
            ToastUtils.showShort("请选择员工");
            return false;
        }
        if (StringUtils.isEmpty(params.getPlanStartTime())) {
            ToastUtils.showShort("请选择计划开始时间");
            return false;
        }
        if (StringUtils.isEmpty(params.getPlanEndTime())) {
            ToastUtils.showShort("请选择计划结束时间");
            return false;
        }
        if (StringUtils.isEmpty(params.getActualStartTime())) {
            ToastUtils.showShort("请选择实际开始时间");
            return false;
        }
        if (StringUtils.isEmpty(params.getActualEndTime())) {
            ToastUtils.showShort("请选择实际结束时间");
            return false;
        }
        return true;
    }

    /**
     * 更新
     *
     * @param params
     */
    public void updateProcedureOutput(WmMesWorkshopProcedureOutputRegister params) {
        boolean isLegal = checkDataIsLegal(params);
        if (!isLegal) {
            return;
        }
        service.updateMesWorkshopProcedureOutput(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<Object>>() {
                    @Override
                    public void onSuccess(ResultModel<Object> result) {
                        String status = result.getStatus();
                        if (status.equals(Constants.RESPONSE_SUCCESS)) {
                            ((IAddProcedureOutputView) mBaseView).onUpdateProcedureInputSuccess();
                        } else {
                            ToastUtils.showShort("更新失败，请稍后再试");
                        }
                    }
                });
    }

    /**
     * 获得投出
     *
     * @param recordId
     */
    public void getMesWorkshopProcedureOutputById(String recordId) {
        service.getMesWorkshopProcedureOutputById(recordId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<ProcedureOutputDetailResbean>() {
                    @Override
                    public void onSuccess(ProcedureOutputDetailResbean procedureOutputDetailResbean) {
                        ((IAddProcedureOutputView) mBaseView).onGetProcedureDetailSuccess(procedureOutputDetailResbean);
                    }
                });
    }


    public void deleteProcedureOutputItem(ProcedureOutputResbean procedureOutputResbean) {
        service.deleteMesWorkshopProcedureOutput(procedureOutputResbean.getRecordId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<Object>>() {
                    @Override
                    public void onSuccess(ResultModel<Object> result) {
                        String status = result.getStatus();
                        if (status.equals(Constants.RESPONSE_SUCCESS)) {
                            ((IProcedureOutputView) mBaseView).onDeteleProcedureOutputItemSuccess();
                        } else {
                            ToastUtils.showShort("删除失败，请稍后再试");
                        }
                    }
                });
    }
}
