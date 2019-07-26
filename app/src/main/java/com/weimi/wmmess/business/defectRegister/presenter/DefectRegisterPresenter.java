package com.weimi.wmmess.business.defectRegister.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BaseObserver;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.WMObserver;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.defectRegister.api.DefectRegisterService;
import com.weimi.wmmess.business.defectRegister.bean.DefectDetailResbean;
import com.weimi.wmmess.business.defectRegister.bean.DefectParentResbean;
import com.weimi.wmmess.business.defectRegister.bean.DefectRegisterResbean;
import com.weimi.wmmess.business.defectRegister.params.WmMesWorkshopProcedureDefectRegister;
import com.weimi.wmmess.business.defectRegister.viewInterface.IAddDefectRegisterView;
import com.weimi.wmmess.business.defectRegister.viewInterface.IChooseDefectView;
import com.weimi.wmmess.business.defectRegister.viewInterface.IDefectRegisterView;
import com.weimi.wmmess.business.procedureOutput.api.ProcedureOutputService;
import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputResbean;
import com.weimi.wmmess.business.procedureOutput.viewInterface.IProcedureOutputView;
import com.weimi.wmmess.business.workHours.viewInterface.IAddHourView;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.constants.HostAddress;
import com.weimi.wmmess.http.HttpClient;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.params.GeneralParam;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by chhyu
 * on 2019/7/25
 * Describle:
 */
public class DefectRegisterPresenter extends BasePresenter {

    private DefectRegisterService service;

    public DefectRegisterPresenter(IBaseView view) {
        super(view);
        service = HttpClient
                .builder()
                .baseUrl(HostAddress.HOST_API)
                .build()
                .getRetrofit()
                .create(DefectRegisterService.class);
    }

    public void loadDefectRegisterList(GeneralParam param) {
        service.loadDefectRegisterputList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<ListModel<DefectRegisterResbean>>() {
                    @Override
                    public void onSuccess(ListModel<DefectRegisterResbean> listModel) {
                        ((IDefectRegisterView) mBaseView).onLoadDefectRegisterListSuccess(listModel);
                    }
                });
    }

    public void loadDefectList() {
        service.listTreeMesWorkshopDefect()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
//                .compose(getLifecycleProvider().bindToLifecycle())
//                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<List<DefectParentResbean>>() {
                    @Override
                    public void onSuccess(List<DefectParentResbean> defectParentResbeans) {
                        ((IChooseDefectView) mBaseView).onLoadDefectTreeSuccess(defectParentResbeans);
                    }
                });
    }

    public void doCheckDataAndAddDefectRegister(WmMesWorkshopProcedureDefectRegister params) {
        if (!checkDataIsLegal(params)) {
            return;
        }
        service.insertMesWorkshopDefectRegister(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<Object>() {

                    @Override
                    public void onSuccess(Object o) {
                        ((IAddDefectRegisterView) mBaseView).onAddSuccess();
                    }
                });
    }

    private boolean checkDataIsLegal(WmMesWorkshopProcedureDefectRegister params) {
        if (params == null) {
            return false;
        }
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
        if (StringUtils.isEmpty(params.getActualEndTime())) {
            ToastUtils.showShort("请选择实际开始时间");
            return false;
        }
        if (StringUtils.isEmpty(params.getActualEndTime())) {
            ToastUtils.showShort("请选择实际结束时间");
            return false;
        }
        return true;
    }

    public void doUpdateDefectRegister(WmMesWorkshopProcedureDefectRegister params) {
        if (!checkDataIsLegal(params)) {
            return;
        }
        service.updateMesWorkshopDefectRegister(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<Object>() {

                    @Override
                    public void onSuccess(Object o) {
                        ((IAddDefectRegisterView) mBaseView).onUpdateSuccess();
                    }
                });
    }

    /**
     * @param recordId
     */
    public void getMesWorkshopDefectRegisterById(String recordId) {
        service.getMesWorkshopDefectRegisterById(recordId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<DefectDetailResbean>() {

                    @Override
                    public void onSuccess(DefectDetailResbean defectDetailResbean) {
                        ((IAddDefectRegisterView) mBaseView).onLoadDefectDetailSuccess(defectDetailResbean);
                    }
                });
    }


    public void deleteItem(String recordId) {
        service.deleteMesWorkshopDefectRegister(recordId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<Object>() {

                    @Override
                    public void onSuccess(Object object) {
                        ((IDefectRegisterView) mBaseView).onDeleteSuccess();
                    }
                });
    }
}
