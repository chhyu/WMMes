package com.weimi.wmmess.business.spotCheck.presenter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.WMObserver;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.spotCheck.api.SpotCheckService;
import com.weimi.wmmess.business.spotCheck.bean.EquipmentTypeResbean;
import com.weimi.wmmess.business.spotCheck.bean.ModelFrameResbean;
import com.weimi.wmmess.business.spotCheck.bean.ModelKernelResbean;
import com.weimi.wmmess.business.spotCheck.bean.SpotCheckItemResbean;
import com.weimi.wmmess.business.spotCheck.bean.SpotCheckProjectResbean;
import com.weimi.wmmess.business.spotCheck.enums.SpotCheckTypeEnum;
import com.weimi.wmmess.business.spotCheck.params.MobileInspectRecordParam;
import com.weimi.wmmess.business.spotCheck.params.MobileListInspectDetailParam;
import com.weimi.wmmess.business.spotCheck.params.WmMesManageEquipmentCheckDetail;
import com.weimi.wmmess.business.spotCheck.viewInterface.IChooseModelFrameView;
import com.weimi.wmmess.business.spotCheck.viewInterface.IChooseModelKernelView;
import com.weimi.wmmess.business.spotCheck.viewInterface.ISpotCheckDetailView;
import com.weimi.wmmess.business.spotCheck.viewInterface.ISpotCheckProjectListView;
import com.weimi.wmmess.business.workOrder.enums.WorkOrderTypeEnum;
import com.weimi.wmmess.business.workOrder.viewInterface.IChooseEquipmentTypeView;
import com.weimi.wmmess.business.workOrder.viewInterface.IWorkOrderListView;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.constants.HostAddress;
import com.weimi.wmmess.http.HttpClient;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.params.GeneralParam;

import java.util.ArrayList;
import java.util.List;

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

    public void getSpotCheckProjectList(GeneralParam param) {
        service.getSpotCheckProjectList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<ListModel<SpotCheckProjectResbean>>() {

                    @Override
                    public void onSuccess(ListModel<SpotCheckProjectResbean> spotCheckProjectResbeanListModel) {
                        ((ISpotCheckProjectListView) mBaseView).onLoadSpotCheckProjectListSuccess(spotCheckProjectResbeanListModel);
                    }
                });
    }

    /**
     * 获取点检内容
     *
     * @param inspectDetailParam
     */
    public void getCheckContent(MobileListInspectDetailParam inspectDetailParam) {
        service.listCheckContent(inspectDetailParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<List<SpotCheckItemResbean>>() {

                    @Override
                    public void onSuccess(List<SpotCheckItemResbean> list) {
                        ((ISpotCheckDetailView) mBaseView).onLoadSpotCheckItemSuccess(list);
                    }
                });
    }

    /**
     * 提交点检结果
     *
     * @param datas
     * @param param
     */
    public void submitCheckResult(List<SpotCheckItemResbean> datas, MobileInspectRecordParam param) {
        if (datas == null || datas.size() <= 0) {
            return;
        }

        List<WmMesManageEquipmentCheckDetail> list = new ArrayList<>();
        for (SpotCheckItemResbean bean : datas) {
            WmMesManageEquipmentCheckDetail detail = new WmMesManageEquipmentCheckDetail();
            detail.setContent(bean.getContent());
            detail.setContentId(bean.getContentId());
            detail.setResult(String.valueOf(bean.isCheckResult()));
            list.add(detail);
        }
        param.setCheckDetails(list);

        service.insertInspectContentRecord(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<Object>() {

                    @Override
                    public void onSuccess(Object object) {
                        Log.e("lmsg", "contentRecord==" + JSON.toJSONString(object));
                    }
                });
    }

    /**
     * 选择工单类型
     *
     * @param activity
     * @param parent
     */
    public void showChooseSpotCheckTypePopupwindow(Activity activity, View parent) {
        View popView = View.inflate(activity, R.layout.item_choose_spot_check_type, null);
        showAnimation(popView);//开启动画
        PopupWindow mPopWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//        mPopWindow.update();
        // 设置背景颜色变暗
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.7f;
        window.setAttributes(lp);
        mPopWindow.setOnDismissListener(() -> {
            lp.alpha = 1f;
            window.setAttributes(lp);
        });

        TextView tvDay = popView.findViewById(R.id.tvDay);
        TextView tvWeek = popView.findViewById(R.id.tvWeek);
        TextView tvMonth = popView.findViewById(R.id.tvMonth);
        TextView tvSeason = popView.findViewById(R.id.tvSeason);
        TextView tvYeer = popView.findViewById(R.id.tvYeer);
        TextView tvCancel = popView.findViewById(R.id.tvCancel);

        tvDay.setOnClickListener(v -> {
            ((ISpotCheckDetailView) mBaseView).onCheckedSpotCheckType(SpotCheckTypeEnum.DAY, activity.getString(R.string.sopt_check_type_of_day));
            mPopWindow.dismiss();
        });
        tvWeek.setOnClickListener(v -> {
            ((ISpotCheckDetailView) mBaseView).onCheckedSpotCheckType(SpotCheckTypeEnum.WEEK, activity.getString(R.string.sopt_check_type_of_week));
            mPopWindow.dismiss();
        });
        tvMonth.setOnClickListener(v -> {
            ((ISpotCheckDetailView) mBaseView).onCheckedSpotCheckType(SpotCheckTypeEnum.MONTH, activity.getString(R.string.sopt_check_type_of_month));
            mPopWindow.dismiss();
        });
        tvSeason.setOnClickListener(v -> {
            ((ISpotCheckDetailView) mBaseView).onCheckedSpotCheckType(SpotCheckTypeEnum.SEASON, activity.getString(R.string.sopt_check_type_of_season));
            mPopWindow.dismiss();
        });
        tvYeer.setOnClickListener(v -> {
            ((ISpotCheckDetailView) mBaseView).onCheckedSpotCheckType(SpotCheckTypeEnum.YEER, activity.getString(R.string.sopt_check_type_of_yeer));
            mPopWindow.dismiss();
        });
        tvCancel.setOnClickListener(v -> mPopWindow.dismiss());
    }

    /**
     * 获取设备类型列表
     */
    public void getEquipmentTypeList() {
        service.getEquipmentTypeList(Constants.FACILITY_TYPE_CODE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<List<EquipmentTypeResbean>>() {
                    @Override
                    public void onSuccess(List<EquipmentTypeResbean> list) {
                        ((IChooseEquipmentTypeView) mBaseView).onGetEquipmentTypeSuccess(list);
                    }
                });
    }

    public void getModelFrameList(GeneralParam param) {
        service.pageMesModelFrame(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<ListModel<ModelFrameResbean>>() {

                    @Override
                    public void onSuccess(ListModel<ModelFrameResbean> modelFrameResbeanListModel) {
                        ((IChooseModelFrameView) mBaseView).onGetModelFrameListSuccess(modelFrameResbeanListModel);
                    }
                });
    }

    public void getModelKernelList(GeneralParam param) {
        service.pageMesModelKernel(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new WMObserver<ListModel<ModelKernelResbean>>() {

                    @Override
                    public void onSuccess(ListModel<ModelKernelResbean> modelKernelResbeanListModel) {
                        ((IChooseModelKernelView) mBaseView).onGetModelKernelListSuccess(modelKernelResbeanListModel);
                    }
                });
    }
}
