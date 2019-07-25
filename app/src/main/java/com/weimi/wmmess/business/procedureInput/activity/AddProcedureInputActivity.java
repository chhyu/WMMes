package com.weimi.wmmess.business.procedureInput.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.business.procedureInput.bean.PorcedureInputDetailResbean;
import com.weimi.wmmess.business.procedureInput.params.WmMesWorkshopProcedureInputRegister;
import com.weimi.wmmess.business.procedureInput.presenter.ProcedureInputPresenter;
import com.weimi.wmmess.business.procedureInput.viewInterface.IAddProcedureInputView;
import com.weimi.wmmess.business.workHours.activity.AddWorkHourActivity;
import com.weimi.wmmess.business.workHours.activity.ChooseDeviceActivity;
import com.weimi.wmmess.business.workHours.activity.ChooseEmployeeActivity;
import com.weimi.wmmess.business.workHours.activity.ChooseProcedureActivity;
import com.weimi.wmmess.business.workHours.activity.ChooseWorkCenterActivity;
import com.weimi.wmmess.business.workHours.activity.ChooseWorkOrderActivity;
import com.weimi.wmmess.business.workHours.bean.DeviceChildResbean;
import com.weimi.wmmess.business.workHours.bean.EmployeeResbean;
import com.weimi.wmmess.business.workHours.bean.ProcedureResbean;
import com.weimi.wmmess.business.workHours.bean.WorkCenterResbean;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;
import com.weimi.wmmess.utils.TimeUtils;
import com.weimi.wmmess.widget.datepick.DatePickDialog;
import com.weimi.wmmess.widget.datepick.bean.DateType;

import static com.weimi.wmmess.business.workHours.activity.ChooseDeviceActivity.WORK_DEVICE;
import static com.weimi.wmmess.business.workHours.activity.ChooseEmployeeActivity.WORK_EMPLOYEE;

public class AddProcedureInputActivity extends WMActivity<ProcedureInputPresenter> implements View.OnClickListener, IAddProcedureInputView {

    public static final String RECORD_ID = "record_id";
    private RelativeLayout rlWorkOrder, rlProcedure, rlWorkCenter, rlPlanStartTime, rlActualStartTime;
    private RelativeLayout rlDeviceName, rlEmployee, rlAmount, rlPlanEndTime, rlActualEndTime;
    private TextView tvWorkOrder, tvProcedure, tvWorkCenter, tvPlanStartTime, tvActualStartTime;
    private TextView tvDeviceName, tvEmployee, tvPlanEndTime, tvActualEndTime;
    private EditText etAmount;
    private Button btnConfirm, btnCancel;
    private ProcedureInputPresenter presenter;
    private static final int REQUEST_WORK_ORDER = 210;
    private static final int REQUEST_WORK_PROCEDURE = 211;
    private static final int REQUEST_WORK_CENTER = 212;
    private static final int REQUEST_DEVICE = 214;
    private static final int REQUEST_EMPLOYEE = 215;
    private WorkOrderListResbean workOrderListResbean;
    private WmMesWorkshopProcedureInputRegister params;
    private int PLAN_START_TIME = 1;
    private int PLAN_END_TIME = 2;
    private int ACTUAL_START_TIME = 3;
    private int ACTUAL_END_TIME = 4;
    private long planStartTime;
    private long planEndTime;
    private long actualStartTime;
    private long actualEndTime;

    @Override
    public int initLayout() {
        return R.layout.activity_add_procedure_input;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        rlWorkOrder = findViewById(R.id.rlWorkOrder);
        rlProcedure = findViewById(R.id.rlProcedure);
        rlWorkCenter = findViewById(R.id.rlWorkCenter);
        rlPlanStartTime = findViewById(R.id.rlPlanStartTime);
        rlActualStartTime = findViewById(R.id.rlActualStartTime);

        rlDeviceName = findViewById(R.id.rlDeviceName);
        rlEmployee = findViewById(R.id.rlEmployee);
        rlAmount = findViewById(R.id.rlAmount);
        rlPlanEndTime = findViewById(R.id.rlPlanEndTime);
        rlActualEndTime = findViewById(R.id.rlActualEndTime);

        tvWorkOrder = findViewById(R.id.tvWorkOrder);
        tvProcedure = findViewById(R.id.tvProcedure);
        tvWorkCenter = findViewById(R.id.tvWorkCenter);
        tvPlanStartTime = findViewById(R.id.tvPlanStartTime);
        tvActualStartTime = findViewById(R.id.tvActualStartTime);

        tvDeviceName = findViewById(R.id.tvDeviceName);
        tvEmployee = findViewById(R.id.tvEmployee);
        etAmount = findViewById(R.id.etAmount);
        tvPlanEndTime = findViewById(R.id.tvPlanEndTime);
        tvActualEndTime = findViewById(R.id.tvActualEndTime);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

        rlWorkOrder.setOnClickListener(this);
        rlProcedure.setOnClickListener(this);
        rlWorkCenter.setOnClickListener(this);
        rlPlanStartTime.setOnClickListener(this);
        rlActualStartTime.setOnClickListener(this);

        rlDeviceName.setOnClickListener(this);
        rlEmployee.setOnClickListener(this);
        rlAmount.setOnClickListener(this);
        rlPlanEndTime.setOnClickListener(this);
        rlActualEndTime.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("新增工序投入");
        presenter = new ProcedureInputPresenter(this);
        params = new WmMesWorkshopProcedureInputRegister();
        String recordId = getIntent().getStringExtra(RECORD_ID);
        if (!StringUtils.isEmpty(recordId)) {
            presenter.getMesWorkshopProcedureInputById(recordId);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rlWorkOrder:
                intent = new Intent(AddProcedureInputActivity.this, ChooseWorkOrderActivity.class);
                startActivityForResult(intent, REQUEST_WORK_ORDER);
                break;
            case R.id.rlProcedure:
                if (workOrderListResbean == null) {
                    toastShort("请先选择工单再选工序");
                    return;
                }
                intent = new Intent(AddProcedureInputActivity.this, ChooseProcedureActivity.class);
                intent.putExtra(ChooseProcedureActivity.WORK_ORDER_CRAFT_ID, workOrderListResbean.getCraftId());
                intent.putExtra(ChooseProcedureActivity.WORK_ORDER_BOM_ID, workOrderListResbean.getBomId());
                startActivityForResult(intent, REQUEST_WORK_PROCEDURE);
                break;
            case R.id.rlWorkCenter:
                intent = new Intent(AddProcedureInputActivity.this, ChooseWorkCenterActivity.class);
                startActivityForResult(intent, REQUEST_WORK_CENTER);
                break;
            case R.id.rlDeviceName:
                intent = new Intent(AddProcedureInputActivity.this, ChooseDeviceActivity.class);
                startActivityForResult(intent, REQUEST_DEVICE);
                break;
            case R.id.rlEmployee:
                intent = new Intent(AddProcedureInputActivity.this, ChooseEmployeeActivity.class);
                startActivityForResult(intent, REQUEST_EMPLOYEE);
                break;
            case R.id.rlPlanStartTime:
                showChooseDateDialog(PLAN_START_TIME);
                break;
            case R.id.rlPlanEndTime:
                showChooseDateDialog(PLAN_END_TIME);
                break;
            case R.id.rlActualStartTime:
                showChooseDateDialog(ACTUAL_START_TIME);
                break;
            case R.id.rlActualEndTime:
                showChooseDateDialog(ACTUAL_END_TIME);
                break;
            case R.id.btnConfirm:
                String amount = etAmount.getText().toString();
                if (StringUtils.isEmpty(amount)) {
                    amount = "0";
                }
                params.setAmount(Integer.parseInt(amount));
                if (StringUtils.isEmpty(params.getRecordId())) {
                    presenter.checkDataAndInsertProcedureInput(params);
                } else {
                    presenter.updateProcedureInput(params);
                }
                break;
            case R.id.btnCancel:
                finish();
                break;
            default:
                break;
        }
    }


    /**
     * 弹出选择时间dialog
     *
     * @param timeType
     */
    private void showChooseDateDialog(int timeType) {
        DatePickDialog dialog = new DatePickDialog(AddProcedureInputActivity.this);
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
                actualStartTime = date.getTime();
                if (actualEndTime > 0 && actualStartTime > actualEndTime) {
                    toastShort("实际结束时间早于实际开始时间");
                    return;
                }
                String startTime = TimeUtils.getFormatTime(date);
                tvActualStartTime.setText(startTime);
                params.setActualStartTime(startTime);
            } else if (timeType == ACTUAL_END_TIME) {
                actualEndTime = date.getTime();
                if (actualEndTime < actualStartTime) {
                    toastShort("实际结束时间早于实际开始时间");
                    return;
                }
                String endTime = TimeUtils.getFormatTime(date);
                tvActualEndTime.setText(endTime);
                params.setActualEndTime(endTime);
            } else if (timeType == PLAN_START_TIME) {
                planStartTime = date.getTime();
                if (planEndTime > 0 && planStartTime > planEndTime) {
                    toastShort("计划结束时间早于计划开始时间");
                    return;
                }
                String planStartTime = TimeUtils.getFormatTime(date);
                tvPlanStartTime.setText(planStartTime);
                params.setPlanStartTime(planStartTime);
            } else if (timeType == PLAN_END_TIME) {
                planEndTime = date.getTime();
                if (planEndTime < planStartTime) {
                    toastShort("计划结束时间早于计划开始时间");
                    return;
                }
                String planEndTime = TimeUtils.getFormatTime(date);
                tvPlanEndTime.setText(planEndTime);
                params.setPlanEndTime(planEndTime);
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
            if (requestCode == REQUEST_WORK_ORDER) {
                workOrderListResbean = JSON.parseObject(data.getStringExtra(ChooseWorkOrderActivity.WORK_ORDER), WorkOrderListResbean.class);
                tvWorkOrder.setText(workOrderListResbean.getWorkOrderCode());
                params.setWorkOrderCode(workOrderListResbean.getWorkOrderCode());
                params.setWorkOrderId(workOrderListResbean.getWorkOrderId());
            } else if (requestCode == REQUEST_WORK_PROCEDURE) {
                ProcedureResbean procedureResbean = JSON.parseObject(data.getStringExtra(ChooseProcedureActivity.WORK_PROCEDURE), ProcedureResbean.class);
                tvProcedure.setText(procedureResbean.getProcedureName());
                params.setProcedureId(procedureResbean.getProcedureId());
                params.setProcedureName(procedureResbean.getProcedureName());
            } else if (requestCode == REQUEST_WORK_CENTER) {
                WorkCenterResbean workCenterResbean = JSON.parseObject(data.getStringExtra(ChooseWorkCenterActivity.WORK_CENTER), WorkCenterResbean.class);
                tvWorkCenter.setText(workCenterResbean.getWorkcenterName());
                params.setWorkcenterId(workCenterResbean.getWorkcenterId());
                params.setWorkcenterName(workCenterResbean.getWorkcenterName());
            } else if (requestCode == REQUEST_DEVICE) {
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
            }
        }
    }

    @Override
    public void onInsertProcedureInputSuccess() {
        toastShort("新增成功");
        finish();
    }

    @Override
    public void onGetProcedureDetailSuccess(PorcedureInputDetailResbean bean) {
        params.copyData2Self(bean);

        tvWorkOrder.setText(bean.getWorkOrderCode());
        tvProcedure.setText(bean.getProcedureName());
        tvWorkCenter.setText(bean.getWorkcenterName());
        tvPlanStartTime.setText(bean.getPlanStartTime());
        tvActualStartTime.setText(bean.getActualStartTime());

        tvDeviceName.setText(bean.getEquipmentName());
        tvEmployee.setText(bean.getEmpName());
        etAmount.setText(bean.getAmount() + "");
        tvPlanEndTime.setText(bean.getPlanEndTime());
        tvActualEndTime.setText(bean.getActualEndTime());
    }

    @Override
    public void onUpdateProcedureInputSuccess() {
        toastShort("更新成功");
        finish();
    }
}
