package com.weimi.wmmess.business.defectRegister.adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.defectRegister.bean.DefectRegisterResbean;
import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputResbean;

/**
 * Create by chhyu
 * on 2019/7/23
 * Describle:
 */
public class DefectRegisterListAdapter extends CommAdapter<DefectRegisterResbean> {
    public DefectRegisterListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, DefectRegisterResbean defectRegisterResbean, int position) {
        super.convert(holder, defectRegisterResbean, position);
        if (StringUtils.isEmpty(defectRegisterResbean.getDefectName())) {
            holder.setText(R.id.tvDefectName, "无");
        } else {
            holder.setText(R.id.tvDefectName, defectRegisterResbean.getDefectName());
        }
        holder.setText(R.id.tvProcedureName, defectRegisterResbean.getProcedureName());
        holder.setText(R.id.tvWorkOrder, defectRegisterResbean.getWorkOrderCode());
        holder.setText(R.id.tvWorkCenter, defectRegisterResbean.getWorkcenterName());
        holder.setText(R.id.tvEmployeeName, defectRegisterResbean.getEmpName());
        holder.setText(R.id.tvDevice, defectRegisterResbean.getEquipmentName());
        holder.setText(R.id.tvStartTime, defectRegisterResbean.getActualStartTime());
        holder.setText(R.id.tvEndTime, defectRegisterResbean.getActualEndTime());

        RelativeLayout rlRootView = holder.getView(R.id.rlRootView);
        rlRootView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(defectRegisterResbean);
            }
        });

        Button btnDelete = holder.getView(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSwipItemClick(defectRegisterResbean);
            }
        });
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T t);

        void onSwipItemClick(T t);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
