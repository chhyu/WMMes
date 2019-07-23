package com.weimi.wmmess.business.workHours.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.business.workHours.bean.DeviceChildResbean;
import com.weimi.wmmess.business.workHours.bean.EmployeeResbean;
import com.weimi.wmmess.business.workHours.bean.ProcedureResbean;
import com.weimi.wmmess.business.workHours.bean.WorkCenterResbean;
import com.weimi.wmmess.business.workHours.bean.WorkHourDetailResbean;
import com.weimi.wmmess.business.workHours.bean.WorkTimeTypeResbean;
import com.weimi.wmmess.business.workHours.bean.WorkTypeResbean;
import com.weimi.wmmess.business.workHours.params.WmMesWorkshopProcedureWorkhoursRegister;
import com.weimi.wmmess.business.workHours.presenter.WorkHourPresenter;
import com.weimi.wmmess.business.workHours.viewInterface.IAddHourView;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;
import com.weimi.wmmess.utils.TimeUtils;
import com.weimi.wmmess.widget.datepick.DatePickDialog;
import com.weimi.wmmess.widget.datepick.bean.DateType;

import static com.weimi.wmmess.utils.TimeUtils.formatLocalDataTime;

/**
 * 新增工时
 */
public class AddWorkHourActivity extends WMActivity<WorkHourPresenter> implements View.OnClickListener, IAddHourView {

    public static final String WORK_HOUR_ID = "work_hour_id";

    public static final String WORK_ORDER = "work_order";
    public static final String WORK_PROCEDURE = "work_procedure";
    public static final String WORK_CENTER = "work_center";
    public static final String WORK_TIME_TYPE = "work_time_type";
    public static final String WORK_EMPLOYEE = "WORK_Employee";
    public static final String WORK_TYPE = "work_type";
    public static final String WORK_DEVICE = "work_device";
    private RelativeLayout rlWorkOrder, rlProcedure, rlWorkCenter, rlWorkHourType, rlSinglePrice, rlWorkTime, rlActualStartTime;
    private RelativeLayout rlDeviceName, rlEmployee, rlWorkType2, rlAmount, rlShift, rlDuration, rlActualEndTime;
    private static final int REQUEST_WORK_ORDER = 110;
    private static final int REQUEST_WORK_PROCEDURE = 111;
    private static final int REQUEST_WORK_CENTER = 112;
    private static final int REQUEST_WORK_TIME_TYPE = 113;
    private static final int REQUEST_DEVICE = 114;
    private static final int REQUEST_EMPLOYEE = 115;
    private static final int REQUEST_WORK_TYPE = 116;
    private WorkOrderListResbean workOrderListResbean;
    private TextView tvWorkOrder, tvProcedure, tvWorkCenter, tvWorkHourType, tvDeviceName, tvEmployee, tvWorkType;
    private TextView tvActualStartTime, tvWorkTime, tvActualEndTime;
    private EditText etSinglePrice, etAmount, etShift, etDurationTime;

    private int PLAN_START_TIME = 1;      //计划开始时间
    private int ACTUAL_START_TIME = 2;    //实际开始时间
    private int ACTUAL_END_TIME = 3;      //实际结束时间
    private WorkHourPresenter presenter;
    private Button btnConfirm, btnCancel;

    private long startTime;
    private long endTime;
    private WmMesWorkshopProcedureWorkhoursRegister params;

    @Override
    public int initLayout() {
        return R.layout.activity_add_work_hour;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        rlWorkOrder = findViewById(R.id.rlWorkOrder);
        rlProcedure = findViewById(R.id.rlProcedure);
        rlWorkCenter = findViewById(R.id.rlWorkCenter);
        rlWorkHourType = findViewById(R.id.rlWorkHourType);
        rlSinglePrice = findViewById(R.id.rlSinglePrice);
        rlWorkTime = findViewById(R.id.rlWorkTime);
        rlActualStartTime = findViewById(R.id.rlActualStartTime);
        rlActualEndTime = findViewById(R.id.rlActualEndTime);

        rlDeviceName = findViewById(R.id.rlDeviceName);
        rlEmployee = findViewById(R.id.rlEmployee);
        rlWorkType2 = findViewById(R.id.rlWorkType);
        rlAmount = findViewById(R.id.rlAmount);
        rlShift = findViewById(R.id.rlShift);
        rlDuration = findViewById(R.id.rlDuration);
        tvActualStartTime = findViewById(R.id.tvActualStartTime);
        tvActualEndTime = findViewById(R.id.tvActualEndTime);

        tvWorkOrder = findViewById(R.id.tvWorkOrder);
        tvProcedure = findViewById(R.id.tvProcedure);
        tvWorkCenter = findViewById(R.id.tvWorkCenter);
        tvWorkHourType = findViewById(R.id.tvWorkHourType);
        tvDeviceName = findViewById(R.id.tvDeviceName);
        tvEmployee = findViewById(R.id.tvEmployee);
        tvWorkType = findViewById(R.id.tvWorkType);
        tvWorkTime = findViewById(R.id.tvWorkTime);
        tvActualStartTime = findViewById(R.id.tvActualStartTime);

        etSinglePrice = findViewById(R.id.etSinglePrice);
        etAmount = findViewById(R.id.etAmount);
        etShift = findViewById(R.id.etShift);
        etDurationTime = findViewById(R.id.etDurationTime);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

        rlWorkOrder.setOnClickListener(this);
        rlProcedure.setOnClickListener(this);
        rlWorkCenter.setOnClickListener(this);
        rlWorkHourType.setOnClickListener(this);
        rlDeviceName.setOnClickListener(this);
        rlEmployee.setOnClickListener(this);
        rlWorkType2.setOnClickListener(this);
        rlWorkTime.setOnClickListener(this);
        rlActualStartTime.setOnClickListener(this);
        rlActualEndTime.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("新增工时");
        presenter = new WorkHourPresenter(this);
        params = new WmMesWorkshopProcedureWorkhoursRegister();

        String workHourId = getIntent().getStringExtra(WORK_HOUR_ID);
        if (!StringUtils.isEmpty(workHourId)) {
            presenter.getMesWorkshopWorkhoursById(workHourId);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rlWorkOrder:
                intent = new Intent(AddWorkHourActivity.this, ChooseWorkOrderActivity.class);
                startActivityForResult(intent, REQUEST_WORK_ORDER);
                break;
            case R.id.rlProcedure:
                if (workOrderListResbean == null) {
                    toastShort("请先选择工单，再选择工序");
                    return;
                }
                intent = new Intent(AddWorkHourActivity.this, ChooseProcedureActivity.class);
                intent.putExtra(ChooseProcedureActivity.WORK_ORDER_BOM_ID, workOrderListResbean.getBomId());
                intent.putExtra(ChooseProcedureActivity.WORK_ORDER_CRAFT_ID, workOrderListResbean.getCraftId());
                startActivityForResult(intent, REQUEST_WORK_PROCEDURE);
                break;
            case R.id.rlWorkCenter:
                intent = new Intent(AddWorkHourActivity.this, ChooseWorkCenterActivity.class);
                startActivityForResult(intent, REQUEST_WORK_CENTER);
                break;
            case R.id.rlWorkHourType:
                intent = new Intent(AddWorkHourActivity.this, ChooseWorkTimeTypeActivity.class);
                startActivityForResult(intent, REQUEST_WORK_TIME_TYPE);
                break;
            case R.id.rlDeviceName:
                toastShort("设备名称");
                intent = new Intent(AddWorkHourActivity.this, ChooseDeviceActivity.class);
                startActivityForResult(intent, REQUEST_DEVICE);
                break;
            case R.id.rlEmployee:
                intent = new Intent(AddWorkHourActivity.this, ChooseEmployeeActivity.class);
                startActivityForResult(intent, REQUEST_EMPLOYEE);
                break;
            case R.id.rlWorkType:
                intent = new Intent(AddWorkHourActivity.this, ChooseWorkTypeActivity.class);
                startActivityForResult(intent, REQUEST_WORK_TYPE);
                break;
            case R.id.rlWorkTime:
                showChooseDateDialog(PLAN_START_TIME);
                break;
            case R.id.rlActualStartTime:
                showChooseDateDialog(ACTUAL_START_TIME);
                break;
            case R.id.rlActualEndTime:
                showChooseDateDialog(ACTUAL_END_TIME);
                break;
            case R.id.btnConfirm:
                doAddWorkHour();
                break;
            case R.id.btnCancel:
                finish();
                break;
            default:
                break;
        }
    }

    private void doAddWorkHour() {
        String amount = etAmount.getText().toString();
        String singlePrice = etSinglePrice.getText().toString();
        String shift = etShift.getText().toString();
        String durationTime = etDurationTime.getText().toString();
        if (StringUtils.isEmpty(amount)) {
            amount = "0";
        }
        if (StringUtils.isEmpty(singlePrice)) {
            singlePrice = "0";
        }
        if (StringUtils.isEmpty(durationTime)) {
            durationTime = "0";
        }
        params.setAmount(Integer.parseInt(amount));
        params.setSinglePrice(Double.parseDouble(singlePrice));
        params.setShift(shift);
        params.setWorkTime(Double.parseDouble(durationTime));
        if (StringUtils.isEmpty(params.getRecordId())) {
            presenter.doChechkDataAndAddWorkHour(params);
        } else {
            presenter.doUpdateWorkHour(params);
        }
    }

    /**
     * 弹出选择时间dialog
     *
     * @param timeType
     */
    private void showChooseDateDialog(int timeType) {
        DatePickDialog dialog = new DatePickDialog(AddWorkHourActivity.this);
        //点击外部不消失
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        //设置上下年分限制
        dialog.setYearLimt(15);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(DateType.TYPE_ALL);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
        //设置选择回调
        dialog.setOnChangeListener(null);
        //设置点击确定按钮回调
        dialog.setOnSureListener(date -> {
            if (timeType == ACTUAL_START_TIME) {
                startTime = date.getTime();
                if (endTime > 0 && startTime > endTime) {
                    toastShort("结束时间早于开始时间");
                    return;
                }
                String startTime = TimeUtils.getFormatTime(date);
                tvActualStartTime.setText(startTime);
                params.setActualStartTime(startTime);
            } else if (timeType == ACTUAL_END_TIME) {
                endTime = date.getTime();
                if (endTime < startTime) {
                    toastShort("结束时间早于开始时间");
                    return;
                }
                String endTime = TimeUtils.getFormatTime(date);
                tvActualEndTime.setText(endTime);
                params.setActualEndTime(endTime);
            } else if (timeType == PLAN_START_TIME) {
                String planStartTime = TimeUtils.getFormatTime(date);
                tvWorkTime.setText(planStartTime);
                params.setWorkStartTime(planStartTime);
            } else {
                toastShort("时间类型参数错误");
                return;
            }
        });
        dialog.setOnDialogCancelListener(() -> dialog.dismiss());
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_WORK_ORDER) {//工单
                workOrderListResbean = JSON.parseObject(data.getStringExtra(WORK_ORDER), WorkOrderListResbean.class);
                params.setWorkOrderId(workOrderListResbean.getWorkOrderId());
                params.setWorkOrderCode(workOrderListResbean.getWorkOrderCode());
                tvWorkOrder.setText(workOrderListResbean.getWorkOrderCode());
            } else if (requestCode == REQUEST_WORK_PROCEDURE) {//工序
                ProcedureResbean procedureResbean = JSON.parseObject(data.getStringExtra(WORK_PROCEDURE), ProcedureResbean.class);
                params.setProcedureId(procedureResbean.getProcedureId());
                params.setProcedureName(procedureResbean.getProcedureName());
                tvProcedure.setText(procedureResbean.getProcedureName());
            } else if (requestCode == REQUEST_WORK_CENTER) { //工作中心
                WorkCenterResbean workCenterResbean = JSON.parseObject(data.getStringExtra(WORK_CENTER), WorkCenterResbean.class);
                params.setWorkcenterId(workCenterResbean.getWorkcenterId());
                params.setWorkcenterName(workCenterResbean.getWorkcenterName());
                tvWorkCenter.setText(workCenterResbean.getWorkcenterName());
            } else if (requestCode == REQUEST_WORK_TIME_TYPE) { //工时种类
                WorkTimeTypeResbean workTimeTypeResbean = JSON.parseObject(data.getStringExtra(WORK_TIME_TYPE), WorkTimeTypeResbean.class);
                params.setWorkHourKindId(workTimeTypeResbean.getKindId());
                params.setWorkHourKindName(workTimeTypeResbean.getWorkhourKindName());
                tvWorkHourType.setText(workTimeTypeResbean.getWorkhourKindName());
            } else if (requestCode == REQUEST_DEVICE) { //设备
                DeviceChildResbean deviceChildResbean = JSON.parseObject(data.getStringExtra(WORK_DEVICE), DeviceChildResbean.class);
                params.setEquipmentId(deviceChildResbean.getEquipmentId());
                params.setEquipmentName(deviceChildResbean.getEquipmentName());
                params.setEquipmentCode(deviceChildResbean.getEquipmentCode());
                tvDeviceName.setText(deviceChildResbean.getEquipmentName());
            } else if (requestCode == REQUEST_EMPLOYEE) { //员工
                EmployeeResbean employeeResbean = JSON.parseObject(data.getStringExtra(WORK_EMPLOYEE), EmployeeResbean.class);
                params.setEmpId(employeeResbean.getEmpId());
                params.setEmpCode(employeeResbean.getEmpCode());
                params.setEmpName(employeeResbean.getEmpName());
                tvEmployee.setText(employeeResbean.getEmpName());
            } else if (requestCode == REQUEST_WORK_TYPE) {  //工种
                WorkTypeResbean workTypeResbean = JSON.parseObject(data.getStringExtra(WORK_TYPE), WorkTypeResbean.class);
                params.setWorkKindId(workTypeResbean.getKindId());
                params.setWorkKindName(workTypeResbean.getWorkKindName());
                tvWorkType.setText(workTypeResbean.getWorkKindName());
            }
        }
    }

    @Override
    public void onAddHourSuccess() {
        ToastUtils.showShort("新增成功");
        finish();
    }

    @Override
    public void onUpdateHourSuccess() {
        ToastUtils.showShort("更新成功");
        finish();
    }

    @Override
    public void onGetWorkHourDetailSuccess(WorkHourDetailResbean workHourDetailResbean) {
        //将数据赋值给params
        params.copyData2Self(workHourDetailResbean);
        tvWorkOrder.setText(workHourDetailResbean.getWorkOrderCode());
        tvProcedure.setText(workHourDetailResbean.getProcedureName());
        tvWorkCenter.setText(workHourDetailResbean.getWorkcenterName());
        tvWorkHourType.setText(workHourDetailResbean.getWorkHourKindName());
        etSinglePrice.setText(workHourDetailResbean.getSinglePrice() + "");
        tvWorkTime.setText(workHourDetailResbean.getWorkStartTime());
        tvActualStartTime.setText(workHourDetailResbean.getActualStartTime());

        tvDeviceName.setText(workHourDetailResbean.getEquipmentName());
        tvEmployee.setText(workHourDetailResbean.getEmpName());
        tvWorkType.setText(workHourDetailResbean.getWorkKindName());
        etAmount.setText(workHourDetailResbean.getAmount() + "");
        etShift.setText(workHourDetailResbean.getShift());
        etDurationTime.setText(workHourDetailResbean.getWorkTime() + "");
        tvActualEndTime.setText(workHourDetailResbean.getActualEndTime());
    }
}
