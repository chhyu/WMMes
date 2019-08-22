package com.weimi.wmmess.business.spotCheck.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.business.spotCheck.adapter.SpotCheckItemAdapter;
import com.weimi.wmmess.business.spotCheck.bean.EquipmentTypeResbean;
import com.weimi.wmmess.business.spotCheck.bean.ModelFrameResbean;
import com.weimi.wmmess.business.spotCheck.bean.ModelKernelResbean;
import com.weimi.wmmess.business.spotCheck.bean.SpotCheckItemResbean;
import com.weimi.wmmess.business.spotCheck.params.MobileInspectRecordParam;
import com.weimi.wmmess.business.spotCheck.params.MobileListInspectDetailParam;
import com.weimi.wmmess.business.spotCheck.presenter.SpotCheckPresenter;
import com.weimi.wmmess.business.spotCheck.viewInterface.ISpotCheckDetailView;
import com.weimi.wmmess.business.workHours.activity.ChooseDeviceActivity;
import com.weimi.wmmess.business.workHours.activity.ChooseWorkOrderActivity;
import com.weimi.wmmess.business.workHours.bean.DeviceChildResbean;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import static com.weimi.wmmess.business.spotCheck.activity.ChooseModelFrameActivity.MODEL_FRAME;
import static com.weimi.wmmess.business.workHours.activity.ChooseDeviceActivity.WORK_DEVICE;
import static com.weimi.wmmess.business.workHours.activity.ChooseWorkOrderActivity.WORK_ORDER;

public class SpotCheckDetailActivity extends WMActivity<SpotCheckPresenter> implements ISpotCheckDetailView, View.OnClickListener {

    private static final int REQUEST_WORK_ORDER = 510;
    private static final int REQUEST_DEVICE_TYPE = 511;
    private static final int REQUEST_MODEL_KERNEL = 512;
    private static final int REQUEST_MODEL_FRAME = 513;
    private static final int REQUEST_DEVICE = 514;
    private static final String MODEL_FRAME = "modelFrame";
    private static final String MODEL_KERNEL = "modelKernel";
    private static final String EQUIPMENT = "equipment";
    private SpotCheckPresenter presenter;
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private MaskView maskview;
    private SpotCheckItemAdapter adapter;
    private RelativeLayout rlWorkOrder, rlDevice, rlChooseSpotType, rlEquipmentType;
    private TextView tvWorkOrder, tvDeviceName, tvSpotCheckType, tvEquipmentType;
    private MobileInspectRecordParam param;
    private MobileListInspectDetailParam inspectDetailParam;
    private EquipmentTypeResbean equipmentTypeResbean;

    @Override
    public int initLayout() {
        return R.layout.activity_spot_check_detail;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        swipeMenuRecyclerView = findViewById(R.id.swipeMenuRecyclerView);
        maskview = findViewById(R.id.maskview);
        rlWorkOrder = findViewById(R.id.rlWorkOrder);
        rlDevice = findViewById(R.id.rlDevice);
        rlChooseSpotType = findViewById(R.id.rlChooseSpotType);
        rlEquipmentType = findViewById(R.id.rlEquipmentType);
        tvWorkOrder = findViewById(R.id.tvWorkOrder);
        tvDeviceName = findViewById(R.id.tvDeviceName);
        tvSpotCheckType = findViewById(R.id.tvSpotCheckType);
        tvEquipmentType = findViewById(R.id.tvEquipmentType);

        rlWorkOrder.setOnClickListener(this);
        rlDevice.setOnClickListener(this);
        rlChooseSpotType.setOnClickListener(this);
        rlEquipmentType.setOnClickListener(this);

        TextView tvOption = findViewById(R.id.tvOption);
        tvOption.setVisibility(View.VISIBLE);
        tvOption.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("点检");
        presenter = new SpotCheckPresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        swipeMenuRecyclerView.setLayoutManager(layoutManager);
        adapter = new SpotCheckItemAdapter(this, R.layout.item_spot_check_item);
        swipeMenuRecyclerView.setAdapter(adapter);
        param = new MobileInspectRecordParam();
        inspectDetailParam = new MobileListInspectDetailParam();
    }

    @Override
    public void onLoadSpotCheckItemSuccess(List<SpotCheckItemResbean> list) {
        if (list != null && list.size() > 0) {
            maskview.setVisibility(View.GONE);
            swipeMenuRecyclerView.setVisibility(View.VISIBLE);
            if (list.size() > 0) {
                adapter.clearItems();
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
            }
        } else {
            maskview.setVisibility(View.VISIBLE);
            swipeMenuRecyclerView.setVisibility(View.GONE);
            maskview.show();
        }
    }

    @Override
    public void onCheckedSpotCheckType(String checkType, String spotCheckText) {
        inspectDetailParam.setCycleCode(checkType);
        tvSpotCheckType.setText(spotCheckText);
        requestSpotCheckDetail(inspectDetailParam);
    }

    @Override
    public void onClick(View v) {
        View parent = findViewById(android.R.id.content);
        Intent intent;
        switch (v.getId()) {
            case R.id.rlWorkOrder:
                intent = new Intent(SpotCheckDetailActivity.this, ChooseWorkOrderActivity.class);
                startActivityForResult(intent, REQUEST_WORK_ORDER);
                break;
            case R.id.rlDevice:
                handleChooseDeviceAction();
                break;
            case R.id.tvOption:
                handleSubmitAction();
                break;
            case R.id.rlChooseSpotType:
                presenter.showChooseSpotCheckTypePopupwindow(this, parent);
                break;
            case R.id.rlEquipmentType:
                intent = new Intent(SpotCheckDetailActivity.this, ChooseEquipmentTypeActivity.class);
                startActivityForResult(intent, REQUEST_DEVICE_TYPE);
                break;
            default:
                break;
        }
    }

    private void handleSubmitAction() {
        presenter.submitCheckResult(adapter.getDatas(), param);
    }

    private void handleChooseDeviceAction() {
        if (equipmentTypeResbean == null) {
            toastShort("请先选择设备类型");
            return;
        }
        Intent intent;
        String code = equipmentTypeResbean.getCode();
        if (code.equals(MODEL_FRAME)) {
            intent = new Intent(SpotCheckDetailActivity.this, ChooseModelFrameActivity.class);
            startActivityForResult(intent, REQUEST_MODEL_FRAME);
        } else if (code.equals(MODEL_KERNEL)) {
            intent = new Intent(SpotCheckDetailActivity.this, ChooseModelKernelActivity.class);
            startActivityForResult(intent, REQUEST_MODEL_KERNEL);
        } else if (code.equals(EQUIPMENT)) {
            intent = new Intent(SpotCheckDetailActivity.this, ChooseDeviceActivity.class);
            startActivityForResult(intent, REQUEST_DEVICE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_WORK_ORDER) {
                WorkOrderListResbean workOrderListResbean = JSON.parseObject(data.getStringExtra(WORK_ORDER), WorkOrderListResbean.class);
                param.setWorkOrderId(workOrderListResbean.getWorkOrderId());
                param.setWorkOrderCode(workOrderListResbean.getWorkOrderCode());
                tvWorkOrder.setText(workOrderListResbean.getWorkOrderCode());
            } else if (requestCode == REQUEST_DEVICE) { //设备
                DeviceChildResbean deviceChildResbean = JSON.parseObject(data.getStringExtra(WORK_DEVICE), DeviceChildResbean.class);
                param.setBusinessId(deviceChildResbean.getEquipmentId());
                param.setBusinessName(deviceChildResbean.getEquipmentName());
                param.setBusinessCode(deviceChildResbean.getEquipmentCode());
                tvDeviceName.setText(deviceChildResbean.getEquipmentName());
            } else if (requestCode == REQUEST_DEVICE_TYPE) {//设备类型
                equipmentTypeResbean = JSON.parseObject(data.getStringExtra(ChooseEquipmentTypeActivity.RESULT_EQUIPMENT_TYPE), EquipmentTypeResbean.class);
                if (StringUtils.isEmpty(lastEquipmentCode)) {
                    lastEquipmentCode = equipmentTypeResbean.getCode();
                    param.setBusinessType(equipmentTypeResbean.getCode());
                    inspectDetailParam.setCheckTypeCode(equipmentTypeResbean.getCode());
                    tvEquipmentType.setText(equipmentTypeResbean.getName());
                } else if (!StringUtils.isEmpty(lastEquipmentCode) && lastEquipmentCode.equals(equipmentTypeResbean.getCode())) {
                    lastEquipmentCode = equipmentTypeResbean.getCode();
                    param.setBusinessType(equipmentTypeResbean.getCode());
                    inspectDetailParam.setCheckTypeCode(equipmentTypeResbean.getCode());
                    tvEquipmentType.setText(equipmentTypeResbean.getName());
                } else if (!StringUtils.isEmpty(lastEquipmentCode) && !lastEquipmentCode.equals(equipmentTypeResbean.getCode())) {
                    lastEquipmentCode = equipmentTypeResbean.getCode();
                    param.setBusinessType(equipmentTypeResbean.getCode());
                    inspectDetailParam.setCheckTypeCode(equipmentTypeResbean.getCode());
                    tvEquipmentType.setText(equipmentTypeResbean.getName());

                    param.setBusinessId(null);
                    param.setBusinessName(null);
                    param.setBusinessCode(null);
                    tvDeviceName.setText(null);
                }
            } else if (requestCode == REQUEST_MODEL_FRAME) {
                ModelFrameResbean modelFrameResbean = JSON.parseObject(data.getStringExtra(ChooseModelFrameActivity.MODEL_FRAME), ModelFrameResbean.class);
                param.setBusinessId(modelFrameResbean.getModelFrameId());
                param.setBusinessName(modelFrameResbean.getModelFrameName());
                param.setBusinessCode(equipmentTypeResbean.getCode());
                tvDeviceName.setText(modelFrameResbean.getModelFrameName());
            } else if (requestCode == REQUEST_MODEL_KERNEL) {
                ModelKernelResbean modelKernelResbean = JSON.parseObject(data.getStringExtra(ChooseModelKernelActivity.MODEL_KERNEL), ModelKernelResbean.class);
                param.setBusinessId(modelKernelResbean.getModelKernelId());
                param.setBusinessName(modelKernelResbean.getModelKernelName());
                param.setBusinessCode(equipmentTypeResbean.getCode());
                tvDeviceName.setText(modelKernelResbean.getModelKernelName());
            }
        }
    }

    private String lastEquipmentCode;

    /**
     * 获取点检详情
     *
     * @param inspectDetailParam
     */
    private void requestSpotCheckDetail(MobileListInspectDetailParam inspectDetailParam) {
        if (StringUtils.isEmpty(inspectDetailParam.getCheckTypeCode())) {
            toastShort("请选择设备类型");
            return;
        } else if (StringUtils.isEmpty(inspectDetailParam.getCycleCode())) {
            toastShort("请选择点检类型");
            return;
        }
        presenter.getCheckContent(inspectDetailParam);
    }
}
