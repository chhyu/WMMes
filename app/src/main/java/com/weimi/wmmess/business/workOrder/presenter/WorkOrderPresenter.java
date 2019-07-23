package com.weimi.wmmess.business.workOrder.presenter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseObserver;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.workOrder.api.WorkOrderServive;
import com.weimi.wmmess.business.workOrder.bean.CraftParamsResbean;
import com.weimi.wmmess.business.workOrder.bean.CraftRebean;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;
import com.weimi.wmmess.business.workOrder.enums.WorkOrderStateEnum;
import com.weimi.wmmess.business.workOrder.enums.WorkOrderTypeEnum;
import com.weimi.wmmess.business.workOrder.params.WorkOrderVO;
import com.weimi.wmmess.business.workOrder.viewInterface.ICraftParamsView;
import com.weimi.wmmess.business.workOrder.viewInterface.IWorkOrderListView;
import com.weimi.wmmess.constants.HostAddress;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.http.HttpClient;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.business.workOrder.params.MobileWorkOrderParam;
import com.weimi.wmmess.params.GeneralParam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by chhyu
 * on 2019/7/1
 * Describle:
 */
public class WorkOrderPresenter extends BasePresenter {

    private WorkOrderServive service;

    public WorkOrderPresenter(IBaseView view) {
        super(view);
        service = HttpClient
                .builder()
                .baseUrl(HostAddress.HOST_API)
                .build()
                .getRetrofit()
                .create(WorkOrderServive.class);
    }

    public void loadWorkOrderList(MobileWorkOrderParam mobileWorkOrderParam) {
        service.getWorkOrderList(mobileWorkOrderParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<ListModel<WorkOrderListResbean>>>() {

                    @Override
                    public void onSuccess(ResultModel<ListModel<WorkOrderListResbean>> result) {
                        Log.e("lmsg", "result==" + JSON.toJSONString(result.getData()));
                        ListModel<WorkOrderListResbean> data = result.getData();
                        ((IWorkOrderListView) mBaseView).onLoadWorkOrderListSuccess(data);
                    }
                });
    }


    /**
     * 获取当前时间
     *
     * @return 服务端需要的String类型
     */
    public String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DATE);
        int mHour = c.get(Calendar.HOUR_OF_DAY);//时
        int mMinute = c.get(Calendar.MINUTE);//分
//        int mSecond = c.get(Calendar.SECOND);
        return addZero(mYear, mMonth, mDay, mHour, mMinute);
    }

    /**
     * 获取当前时间String类型
     *
     * @param mYear
     * @param mMonth
     * @param mDay
     * @param mHour
     * @param mMinute
     * @return
     */
    public String getCurrentStringTime(int mYear, int mMonth, int mDay, int mHour, int mMinute) {
        return addZero(mYear, mMonth, mDay, mHour, mMinute);
    }

    private String addZero(int year, int month, int day, int hour, int minute) {
        String time = "";
        if (hour == 0) {
            if (minute == 0) {//整点
                time = year + "-" + month + "-" + day + " " + "00" + ":" + "00";
            } else if (minute < 10) {//个位数
                time = year + "-" + month + "-" + day + " " + "00" + ":" + "0" + minute;
            } else {//两位数
                time = year + "-" + month + "-" + day + " " + "00" + ":" + minute;
            }
        } else if (hour < 10) {
            if (minute == 0) {//整点
                time = year + "-" + month + "-" + day + " " + "0" + hour + ":" + "00";
            } else if (minute < 10) {//个位数
                time = year + "-" + month + "-" + day + " " + "0" + hour + ":" + "0" + minute;
            } else {//两位数
                time = year + "-" + month + "-" + day + " " + "0" + hour + ":" + minute;
            }
        } else {
            if (minute == 0) {//整点
                time = year + "-" + month + "-" + day + " " + hour + ":" + "00";
            } else if (minute < 10) {//个位数
                time = year + "-" + month + "-" + day + " " + hour + ":" + "0" + minute;
            } else {//两位数
                time = year + "-" + month + "-" + day + " " + hour + ":" + minute;
            }
        }
        return time;
    }

    /**
     * 选择工单类型
     *
     * @param activity
     * @param parent
     */
    public void showChooseWorkOrderTypePopupwindow(Activity activity, View parent) {
        View popView = View.inflate(activity, R.layout.item_choose_work_order_type, null);
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

        TextView tvTrialModel = popView.findViewById(R.id.tvTrialModel);
        TextView tvTrialProduce = popView.findViewById(R.id.tvTrialProduce);
        TextView tvRework = popView.findViewById(R.id.tvRework);
        TextView tvProduce = popView.findViewById(R.id.tvProduce);
        TextView tvCancel = popView.findViewById(R.id.tvCancel);

        tvTrialModel.setOnClickListener(v -> {
            ((IWorkOrderListView) mBaseView).onWorkOrderTypeChoosed(WorkOrderTypeEnum.TRIAL_MODEL, activity.getString(R.string.work_order_type_of_trialModel));
            mPopWindow.dismiss();
        });
        tvTrialProduce.setOnClickListener(v -> {
            ((IWorkOrderListView) mBaseView).onWorkOrderTypeChoosed(WorkOrderTypeEnum.TRIALP_RODUCE, activity.getString(R.string.work_order_type_of_trialProduce));
            mPopWindow.dismiss();
        });
        tvRework.setOnClickListener(v -> {
            ((IWorkOrderListView) mBaseView).onWorkOrderTypeChoosed(WorkOrderTypeEnum.REWORK, activity.getString(R.string.work_order_type_of_rework));
            mPopWindow.dismiss();
        });
        tvProduce.setOnClickListener(v -> {
            ((IWorkOrderListView) mBaseView).onWorkOrderTypeChoosed(WorkOrderTypeEnum.PRODUCE, activity.getString(R.string.work_order_type_of_produce));
            mPopWindow.dismiss();
        });
        tvCancel.setOnClickListener(v -> mPopWindow.dismiss());
    }


    /**
     * 选择工单状态
     *
     * @param activity
     * @param parent
     */
    public void showChooseWorkOrderStatePopupwindow(Activity activity, View parent) {
        View popView = View.inflate(activity, R.layout.item_choose_work_order_state, null);
        showAnimation(popView);//开启动画
        PopupWindow mPopWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        // 设置背景颜色变暗
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.7f;
        window.setAttributes(lp);
        mPopWindow.setOnDismissListener(() -> {
            lp.alpha = 1f;
            window.setAttributes(lp);
        });

        TextView tvOrderStart = popView.findViewById(R.id.tvOrderStart);
        TextView tvReading = popView.findViewById(R.id.tvReading);
        TextView tvProceducing = popView.findViewById(R.id.tvProceducing);
        TextView tvOrderFinish = popView.findViewById(R.id.tvOrderFinish);
        TextView tvOrderException = popView.findViewById(R.id.tvOrderException);
        TextView tvCancel = popView.findViewById(R.id.tvCancel);

        tvOrderStart.setOnClickListener(v -> {
            ((IWorkOrderListView) mBaseView).onWorkOrderStateChoosed(WorkOrderStateEnum.START, activity.getString(R.string.work_order_state_of_start));
            mPopWindow.dismiss();
        });
        tvReading.setOnClickListener(v -> {
            ((IWorkOrderListView) mBaseView).onWorkOrderStateChoosed(WorkOrderStateEnum.READYING, activity.getString(R.string.work_order_state_of_readying));
            mPopWindow.dismiss();
        });
        tvProceducing.setOnClickListener(v -> {
            ((IWorkOrderListView) mBaseView).onWorkOrderStateChoosed(WorkOrderStateEnum.PROCEDUCING, activity.getString(R.string.work_order_state_of_proceducing));
            mPopWindow.dismiss();
        });
        tvOrderFinish.setOnClickListener(v -> {
            ((IWorkOrderListView) mBaseView).onWorkOrderStateChoosed(WorkOrderStateEnum.FINISH, activity.getString(R.string.work_order_state_of_finish));
            mPopWindow.dismiss();
        });
        tvOrderException.setOnClickListener(v -> {
            ((IWorkOrderListView) mBaseView).onWorkOrderStateChoosed(WorkOrderStateEnum.EXCEPTION, activity.getString(R.string.work_order_state_of_exception));
            mPopWindow.dismiss();
        });
        tvCancel.setOnClickListener(v -> mPopWindow.dismiss());
    }

    /**
     * 给popupwindow添加动画
     *
     * @param popView
     */
    private void showAnimation(View popView) {
        AnimationSet animationSet = new AnimationSet(false);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1.0f);
        alphaAnimation.setDuration(300);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f
        );
        translateAnimation.setDuration(300);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        popView.startAnimation(animationSet);
    }

    /**
     * 检查工单是否有数据
     *
     * @param orderId
     * @param procedureId
     */
    public void checkWorkOrderHavingParameter(String orderId, String procedureId) {
        if (StringUtils.isEmpty(orderId)) {
            ToastUtils.showShort("工单ID为null");
            return;
        }
        service.checkWorkOrderHavingParameter(orderId, Constants.DEFAULT_PROCEDURE_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<Boolean>>() {
                    @Override
                    public void onSuccess(ResultModel<Boolean> result) {
                        ((IWorkOrderListView) mBaseView).onCheckWorkOrderHavingParameterSuccess(result.getData(), orderId, procedureId);
                    }
                });
    }

    public void requestCraftParamsList(String orderId, int size, int current) {
        if (StringUtils.isEmpty(orderId)) {
            ToastUtils.showShort("工单ID为null");
            return;
        }
        MobileWorkOrderParam mobileWorkOrderParam = new MobileWorkOrderParam();
        GeneralParam param = new GeneralParam();
        WorkOrderVO workOrderVO = new WorkOrderVO();
        param.setCurrent(current);
        param.setSize(size);
        workOrderVO.setWorkOrderId(orderId);
        mobileWorkOrderParam.setParam(param);
        mobileWorkOrderParam.setWorkOrderVO(workOrderVO);

        service.listWorkOrderParameterByOrderId(mobileWorkOrderParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
//                .compose(getLifecycleProvider().bindToLifecycle())
//                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<ListModel<CraftParamsResbean>>>() {

                    @Override
                    public void onSuccess(ResultModel<ListModel<CraftParamsResbean>> result) {
                        List<CraftParamsResbean> records = result.getData().getRecords();
                        List<CraftRebean> craftRebeans = getCraftResBeans(records);
                        ((ICraftParamsView) mBaseView).onLoadCraftParamsListSuccessed(craftRebeans);
                    }
                });
    }

    public List<CraftRebean> getCraftResBeans(List<CraftParamsResbean> records) {
        if (records == null || records.size() == 0) {
            return new ArrayList<>();
        }
        List<CraftRebean> craftRebeans = new ArrayList<>();
        Set<String> produceCodes = new HashSet<>();
        for (int i = 0; i < records.size(); i++) {
            produceCodes.add(records.get(i).getProductCode());
        }
        for (String produceCode : produceCodes) {
            CraftRebean craftRebean = new CraftRebean();
            craftRebean.setProductCode(produceCode);
            List<CraftParamsResbean> craftParamsResbeanList = new ArrayList<>();
            craftRebean.setCraftParamsResbeans(craftParamsResbeanList);
            craftRebeans.add(craftRebean);
        }

        for (int i = 0; i < craftRebeans.size(); i++) {
            for (int j = 0; j < records.size(); j++) {
                String productCode = craftRebeans.get(i).getProductCode();
                String productCode1 = records.get(j).getProductCode();
                if (productCode.equals(productCode1)) {
                    craftRebeans.get(i).getCraftParamsResbeans().add(records.get(j));
                }
            }
        }
        return craftRebeans;
    }
}
