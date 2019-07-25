package com.weimi.wmmess.business.procedureOutput.adapter;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputResbean;

/**
 * Create by chhyu
 * on 2019/7/23
 * Describle:
 */
public class ProcedureOutputListAdapter extends CommAdapter<ProcedureOutputResbean> {
    public ProcedureOutputListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ProcedureOutputResbean procedureOutputResbean, int position) {
        super.convert(holder, procedureOutputResbean, position);

        holder.setText(R.id.tvProcedureName, procedureOutputResbean.getProcedureName());
        holder.setText(R.id.tvWorkOrder, procedureOutputResbean.getWorkOrderCode());
        holder.setText(R.id.tvWorkCenter, procedureOutputResbean.getWorkcenterName());
        holder.setText(R.id.tvEmployeeName, procedureOutputResbean.getEmpName());
        holder.setText(R.id.tvDevice, procedureOutputResbean.getEquipmentName());
        holder.setText(R.id.tvStartTime, procedureOutputResbean.getActualStartTime());
        holder.setText(R.id.tvEndTime, procedureOutputResbean.getActualEndTime());

        RelativeLayout rlRootView = holder.getView(R.id.rlRootView);
        rlRootView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProcedureOutputItemClick(procedureOutputResbean);
            }
        });

        Button btnDelete = holder.getView(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSwipProcedureOutputItemClick(procedureOutputResbean);
            }
        });
    }

    public interface OnProcedureOutputItemClickListener {
        void onProcedureOutputItemClick(ProcedureOutputResbean procedureOutputResbean);

        void onSwipProcedureOutputItemClick(ProcedureOutputResbean procedureOutputResbean);
    }

    private OnProcedureOutputItemClickListener listener;

    public void setOnProcedureOutputItemClickListener(OnProcedureOutputItemClickListener listener) {
        this.listener = listener;
    }
}
