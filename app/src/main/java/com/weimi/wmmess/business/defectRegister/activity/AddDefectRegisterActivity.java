package com.weimi.wmmess.business.defectRegister.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.business.defectRegister.bean.DefectChildResbean;
import com.weimi.wmmess.business.defectRegister.bean.DefectDetailResbean;
import com.weimi.wmmess.business.defectRegister.params.WmMesWorkshopProcedureDefectRegister;
import com.weimi.wmmess.business.defectRegister.presenter.DefectRegisterPresenter;
import com.weimi.wmmess.business.defectRegister.viewInterface.IAddDefectRegisterView;
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
import static com.weimi.wmmess.business.workHours.activity.ChooseProcedureActivity.WORK_PROCEDURE;
import static com.weimi.wmmess.business.workHours.activity.ChooseWorkCenterActivity.WORK_CENTER;
import static com.weimi.wmmess.business.workHours.activity.ChooseWorkOrderActivity.WORK_ORDER;

public class AddDefectRegisterActivity extends WMActivity<DefectRegisterPresenter> implements View.OnClickListener, IAddDefectRegisterView {

    public static final String RECORD_ID = "record_id";
    private RelativeLayout rlWorkOrder, rlProcedure, rlWorkCenter, rlWorkTime, rlActualStartTime;
    private RelativeLayout rlDeviceName, rlEmployee, rlActualEndTime, rlDefect;
    private static final int REQUEST_WORK_ORDER = 410;
    private static final int REQUEST_WORK_PROCEDURE = 411;
    private static final int REQUEST_WORK_CENTER = 412;
    private static final int REQUEST_DEVICE = 414;
    private static final int REQUEST_EMPLOYEE = 415;
    private static final int REQUEST_DEFECT = 416;
    private WorkOrderListResbean workOrderListResbean;
    private TextView tvWorkOrder, tvProcedure, tvWorkCenter, tvDeviceName, tvEmployee, tvWorkType;
    private TextView tvActualStartTime, tvWorkTime, tvActualEndTime, tvDefectName;
    private EditText etSinglePrice, etAmount, etDurationTime;

    private int PLAN_START_TIME = 1;      //计划开始时间
    private int ACTUAL_START_TIME = 2;    //实际开始时间
    private int ACTUAL_END_TIME = 3;      //实际结束时间
    private DefectRegisterPresenter presenter;
    private Button btnConfirm, btnCancel;

    private long startTime;
    private long endTime;
    private WmMesWorkshopProcedureDefectRegister params;

    @Override
    public int initLayout() {
        return R.layout.activity_add_defect_register;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        rlWorkOrder = findViewById(R.id.rlWorkOrder);
        rlProcedure = findViewById(R.id.rlProcedure);
        rlWorkCenter = findViewById(R.id.rlWorkCenter);
        rlWorkTime = findViewById(R.id.rlWorkTime);
        rlActualStartTime = findViewById(R.id.rlActualStartTime);
        rlActualEndTime = findViewById(R.id.rlActualEndTime);

        rlDeviceName = findViewById(R.id.rlDeviceName);
        rlEmployee = findViewById(R.id.rlEmployee);
        rlDefect = findViewById(R.id.rlDefect);

        tvActualStartTime = findViewById(R.id.tvActualStartTime);
        tvActualEndTime = findViewById(R.id.tvActualEndTime);

        tvWorkOrder = findViewById(R.id.tvWorkOrder);
        tvProcedure = findViewById(R.id.tvProcedure);
        tvWorkCenter = findViewById(R.id.tvWorkCenter);
        tvDeviceName = findViewById(R.id.tvDeviceName);
        tvEmployee = findViewById(R.id.tvEmployee);
        tvWorkType = findViewById(R.id.tvWorkType);
        tvWorkTime = findViewById(R.id.tvWorkTime);
        tvActualStartTime = findViewById(R.id.tvActualStartTime);

        etSinglePrice = findViewById(R.id.etSinglePrice);
        etAmount = findViewById(R.id.etAmount);
        tvDefectName = findViewById(R.id.tvDefectName);
        etDurationTime = findViewById(R.id.etDurationTime);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

        rlWorkOrder.setOnClickListener(this);
        rlProcedure.setOnClickListener(this);
        rlWorkCenter.setOnClickListener(this);
        rlDeviceName.setOnClickListener(this);
        rlEmployee.setOnClickListener(this);
        rlWorkTime.setOnClickListener(this);
        rlActualStartTime.setOnClickListener(this);
        rlActualEndTime.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        rlDefect.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("新增缺陷登记");
        presenter = new DefectRegisterPresenter(this);
        params = new WmMesWorkshopProcedureDefectRegister();

        String recordId = getIntent().getStringExtra(RECORD_ID);
        if (!StringUtils.isEmpty(recordId)) {
            presenter.getMesWorkshopDefectRegisterById(recordId);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rlWorkOrder:
                intent = new Intent(AddDefectRegisterActivity.this, ChooseWorkOrderActivity.class);
                startActivityForResult(intent, REQUEST_WORK_ORDER);
                break;
            case R.id.rlProcedure:
                if (workOrderListResbean == null) {
                    toastShort("请先选择工单，再选择工序");
                    return;
                }
                intent = new Intent(AddDefectRegisterActivity.this, ChooseProcedureActivity.class);
                intent.putExtra(ChooseProcedureActivity.WORK_ORDER_BOM_ID, workOrderListResbean.getBomId());
                intent.putExtra(ChooseProcedureActivity.WORK_ORDER_CRAFT_ID, workOrderListResbean.getCraftId());
                startActivityForResult(intent, REQUEST_WORK_PROCEDURE);
                break;
            case R.id.rlWorkCenter:
                intent = new Intent(AddDefectRegisterActivity.this, ChooseWorkCenterActivity.class);
                startActivityForResult(intent, REQUEST_WORK_CENTER);
                break;
            case R.id.rlDeviceName:
                intent = new Intent(AddDefectRegisterActivity.this, ChooseDeviceActivity.class);
                startActivityForResult(intent, REQUEST_DEVICE);
                break;
            case R.id.rlEmployee:
                intent = new Intent(AddDefectRegisterActivity.this, ChooseEmployeeActivity.class);
                startActivityForResult(intent, REQUEST_EMPLOYEE);
                break;
            case R.id.rlDefect:
                intent = new Intent(AddDefectRegisterActivity.this, ChooseDefectActivity.class);
                startActivityForResult(intent, REQUEST_DEFECT);
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
                doAddDefectRegister();
                break;
            case R.id.btnCancel:
                finish();
                break;
            default:
                break;
        }
    }

    private void doAddDefectRegister() {
        String amount = etAmount.getText().toString();
        String singlePrice = etSinglePrice.getText().toString();
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
        params.setWorkTime(Double.parseDouble(durationTime));
        if (StringUtils.isEmpty(params.getRecordId())) {

            presenter.doCheckDataAndAddDefectRegister(params);
        } else {
            presenter.doUpdateDefectRegister(params);
        }
    }

    /**
     * 弹出选择时间dialog
     *
     * @param timeType
     */
    private void showChooseDateDialog(int timeType) {
        DatePickDialog dialog = new DatePickDialog(AddDefectRegisterActivity.this);
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
            } else if (requestCode == REQUEST_DEFECT) {
                DefectChildResbean childResbean = JSON.parseObject(data.getStringExtra(ChooseDefectActivity.DEFECT_REGISTER), DefectChildResbean.class);
                params.setDefectId(childResbean.getDefectId());
                params.setDefectName(childResbean.getDefectName());
            }
        }
    }

    @Override
    public void onAddSuccess() {
        toastShort("新增成功");
        finish();
    }

    @Override
    public void onUpdateSuccess() {
        toastShort("更新成功");
        finish();
    }

    @Override
    public void onLoadDefectDetailSuccess(DefectDetailResbean bean) {
        params.copyData2Self(bean);

        tvWorkOrder.setText(bean.getWorkOrderCode());
        tvProcedure.setText(bean.getProcedureName());
        tvWorkCenter.setText(bean.getWorkcenterName());
        etSinglePrice.setText(bean.getSinglePrice() + "");
        tvWorkTime.setText(bean.getWorkStartTime());
        tvActualStartTime.setText(bean.getActualStartTime());

        tvDeviceName.setText(bean.getEquipmentName());
        tvEmployee.setText(bean.getEmpName());
        etAmount.setText(bean.getAmount() + "");
        etDurationTime.setText(bean.getWorkTime() + "");
        tvActualEndTime.setText(bean.getActualEndTime());
        tvDefectName.setText(bean.getDefectName());
    }
}
