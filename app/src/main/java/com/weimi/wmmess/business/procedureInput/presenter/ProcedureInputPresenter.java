package com.weimi.wmmess.business.procedureInput.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BaseObserver;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.procedureInput.api.ProcedureInputService;
import com.weimi.wmmess.business.procedureInput.bean.PorcedureInputDetailResbean;
import com.weimi.wmmess.business.procedureInput.bean.ProcedureInputResbean;
import com.weimi.wmmess.business.procedureInput.params.WmMesWorkshopProcedureInputRegister;
import com.weimi.wmmess.business.procedureInput.viewInterface.IAddProcedureInputView;
import com.weimi.wmmess.business.procedureInput.viewInterface.IProcedureInputView;
import com.weimi.wmmess.business.procedureOutput.params.WmMesWorkshopProcedureOutputRegister;
import com.weimi.wmmess.business.workHours.api.WorkHourService;
import com.weimi.wmmess.business.workHours.bean.WorkHourListResbean;
import com.weimi.wmmess.business.workHours.viewInterface.IWorkHourView;
import com.weimi.wmmess.constants.Constants;
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

    /**
     * 检查数据并新增工序投入
     *
     * @param params
     */
    public void checkDataAndInsertProcedureInput(WmMesWorkshopProcedureInputRegister params) {
        boolean isLegal = checkDataIsLegal(params);
        if (!isLegal) {
            return;
        }
        service.insertMesWorkshopProcedureInput(params)
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
                            ((IAddProcedureInputView) mBaseView).onInsertProcedureInputSuccess();
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
    private boolean checkDataIsLegal(WmMesWorkshopProcedureInputRegister params) {
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
     * 删除工序投入条目
     *
     * @param recordId
     */
    public void deleteProcedureInputItem(String recordId) {
        service.deleteMesWorkshopProcedureInput(recordId)
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
                            ((IProcedureInputView) mBaseView).onDeteleProcedureInputItemSuccess();
                        } else {
                            ToastUtils.showShort("删除失败，请稍后再试");
                        }
                    }
                });
    }

    /**
     * 获取工序详情信息
     *
     * @param recordId
     */
    public void getMesWorkshopProcedureInputById(String recordId) {
        service.getMesWorkshopProcedureInputById(recordId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<PorcedureInputDetailResbean>>() {
                    @Override
                    public void onSuccess(ResultModel<PorcedureInputDetailResbean> result) {
                        ((IAddProcedureInputView) mBaseView).onGetProcedureDetailSuccess(result.getData());
                    }
                });
    }

    /**
     * 更新
     *
     * @param params
     */
    public void updateProcedureInput(WmMesWorkshopProcedureInputRegister params) {
        boolean isLegal = checkDataIsLegal(params);
        if (!isLegal) {
            return;
        }
        service.updateMesWorkshopProcedureInput(params)
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
                            ((IAddProcedureInputView) mBaseView).onUpdateProcedureInputSuccess();
                        } else {
                            ToastUtils.showShort("更新失败，请稍后再试");
                        }
                    }
                });
    }

}
