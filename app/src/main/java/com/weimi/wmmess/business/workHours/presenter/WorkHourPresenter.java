package com.weimi.wmmess.business.workHours.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BaseObserver;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.workHours.api.WorkHourService;
import com.weimi.wmmess.business.workHours.bean.DeviceParentResbean;
import com.weimi.wmmess.business.workHours.bean.EmployeeResbean;
import com.weimi.wmmess.business.workHours.bean.ProcedureResbean;
import com.weimi.wmmess.business.workHours.bean.WorkCenterResbean;
import com.weimi.wmmess.business.workHours.bean.WorkHourDetailResbean;
import com.weimi.wmmess.business.workHours.bean.WorkHourListResbean;
import com.weimi.wmmess.business.workHours.bean.WorkTimeTypeResbean;
import com.weimi.wmmess.business.workHours.bean.WorkTypeResbean;
import com.weimi.wmmess.business.workHours.params.WmMesWorkshopProcedureWorkhoursRegister;
import com.weimi.wmmess.business.workHours.viewInterface.IAddHourView;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseDeviceView;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseEmployeeView;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseProcedureView;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseTimeTypeView;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseWorkCenterView;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseWorkOrderView;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseWorkTypeView;
import com.weimi.wmmess.business.workHours.viewInterface.IWorkHourView;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;
import com.weimi.wmmess.business.workOrder.params.MobileWorkOrderParam;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.constants.HostAddress;
import com.weimi.wmmess.http.HttpClient;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.params.GeneralParam;
import com.weimi.wmmess.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public class WorkHourPresenter extends BasePresenter {
    private WorkHourService service;

    public WorkHourPresenter(IBaseView view) {
        super(view);
        service = HttpClient
                .builder()
                .baseUrl(HostAddress.HOST_API)
                .build()
                .getRetrofit()
                .create(WorkHourService.class);
    }

    /**
     * 加载工时列表
     *
     * @param param
     */
    public void loadWorkHourList(GeneralParam param) {
        service.loadWorkHourList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<ListModel<WorkHourListResbean>>>() {

                    @Override
                    public void onSuccess(ResultModel<ListModel<WorkHourListResbean>> result) {
                        ((IWorkHourView) mBaseView).onLoadWorkHourListSuccess(result.getData());
                    }
                });
    }


    /**
     * 删除当前工时条目
     *
     * @param workHourListResbean
     */
    public void deleteCurrentWorkHourItem(WorkHourListResbean workHourListResbean) {
        service.deleteWorkHour(workHourListResbean.getRecordId())
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
                            ((IWorkHourView) mBaseView).onDeleteWorkHourSuccess();
                        }
                    }
                });
    }

    /**
     * 加载工单
     *
     * @param param
     */
    public void loadMesCommonProductWorkOrder(MobileWorkOrderParam param) {
        service.pageMesCommonProductWorkOrder(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<ListModel<WorkOrderListResbean>>>() {

                    @Override
                    public void onSuccess(ResultModel<ListModel<WorkOrderListResbean>> result) {
                        ((IChooseWorkOrderView) mBaseView).onLoadWorkOrderListSuccess(result.getData());
                    }
                });
    }

    /**
     * 获取工序
     *
     * @param bomId
     * @param craftId
     */
    public void getBomProcedureList(String bomId, String craftId) {
        service.getBomProcedureList(bomId, craftId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<List<ProcedureResbean>>>() {

                    @Override
                    public void onSuccess(ResultModel<List<ProcedureResbean>> result) {
                        List<ProcedureResbean> data = result.getData();
                        ((IChooseProcedureView) mBaseView).onLoadProcedureListSuccess(data);
                    }
                });
    }

    /**
     * 获取工作中心
     */
    public void getWorkcenterList() {
        service.getWorkcenterList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<List<WorkCenterResbean>>>() {
                    @Override
                    public void onSuccess(ResultModel<List<WorkCenterResbean>> result) {
                        ((IChooseWorkCenterView) mBaseView).onLoadWorkCenterSuccess(result.getData());
                    }
                });
    }

    /**
     * 加载工时种类
     *
     * @param generalParam
     */
    public void loadWorkTimeTypeList(GeneralParam generalParam) {
        service.pageWorkHourKind(generalParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<ListModel<WorkTimeTypeResbean>>>() {
                    @Override
                    public void onSuccess(ResultModel<ListModel<WorkTimeTypeResbean>> result) {
                        ((IChooseTimeTypeView) mBaseView).onLoadWorkTimeTypeSuccess(result.getData());
                    }
                });
    }

    /**
     * 获取设备列表
     */
    public void loadDeviceList() {
        service.listAllEquipmentsByType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
//                .compose(getLifecycleProvider().bindToLifecycle())
//                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<List<DeviceParentResbean>>>() {
                    @Override
                    public void onSuccess(ResultModel<List<DeviceParentResbean>> result) {
                        ((IChooseDeviceView) mBaseView).onLoadDeviceListSuccess(result.getData());
                    }
                });
    }

    /**
     * 获取员工列表
     *
     * @param param
     */
    public void requestEmployeeList(GeneralParam param) {
        service.pageOrgEmp(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<ListModel<EmployeeResbean>>>() {
                    @Override
                    public void onSuccess(ResultModel<ListModel<EmployeeResbean>> result) {
                        ((IChooseEmployeeView) mBaseView).onLoadEmployeeListSuccess(result.getData());
                    }
                });
    }

    /**
     * 获取工种列表
     *
     * @param param
     */
    public void requestWorkTypeList(GeneralParam param) {
        service.pageWorkKind(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<ListModel<WorkTypeResbean>>>() {
                    @Override
                    public void onSuccess(ResultModel<ListModel<WorkTypeResbean>> result) {
                        ((IChooseWorkTypeView) mBaseView).onLoadWorkTypeSuccess(result.getData());
                    }
                });
    }

    /**
     * 检查数据和新增
     *
     * @param params
     */
    public void doChechkDataAndAddWorkHour(WmMesWorkshopProcedureWorkhoursRegister params) {
        if (params == null) {
            return;
        }
        if (StringUtils.isEmpty(params.getWorkOrderId())) {
            ToastUtils.showShort("请选择工单");
            return;
        }
        if (StringUtils.isEmpty(params.getEquipmentId())) {
            ToastUtils.showShort("请选择设备");
            return;
        }
        if (StringUtils.isEmpty(params.getEmpId())) {
            ToastUtils.showShort("请选择员工");
            return;
        }
        if (StringUtils.isEmpty(params.getActualEndTime())) {
            ToastUtils.showShort("请选择实际开始时间");
            return;
        }
        if (StringUtils.isEmpty(params.getActualEndTime())) {
            ToastUtils.showShort("请选择实际结束时间");
            return;
        }

        service.insertMesWorkshopWorkhours(params)
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
                            ((IAddHourView) mBaseView).onAddHourSuccess();
                        }
                    }
                });
    }

    /**
     * 获取工时信息（编辑）
     *
     * @param workHourId
     */
    public void getMesWorkshopWorkhoursById(String workHourId) {
        service.getMesWorkshopWorkhoursById(workHourId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<WorkHourDetailResbean>>() {
                    @Override
                    public void onSuccess(ResultModel<WorkHourDetailResbean> result) {
                        ((IAddHourView) mBaseView).onGetWorkHourDetailSuccess(result.getData());
                    }
                });
    }

    /**
     * 工时更新
     *
     * @param params
     */
    public void doUpdateWorkHour(WmMesWorkshopProcedureWorkhoursRegister params) {
        service.updateMesWorkshopWorkhours(params)
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
                            ((IAddHourView) mBaseView).onUpdateHourSuccess();
                        }
                    }
                });
    }
}
