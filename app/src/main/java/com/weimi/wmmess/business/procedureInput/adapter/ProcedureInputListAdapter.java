package com.weimi.wmmess.business.procedureInput.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.procedureInput.bean.ProcedureInputResbean;

/**
 * Create by chhyu
 * on 2019/7/23
 * Describle:
 */
public class ProcedureInputListAdapter extends CommAdapter<ProcedureInputResbean> {
    public ProcedureInputListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ProcedureInputResbean procedureInputResbean, int position) {
        super.convert(holder, procedureInputResbean, position);

        holder.setText(R.id.tvProcedureName, procedureInputResbean.getProcedureName());
        holder.setText(R.id.tvWorkOrder, procedureInputResbean.getWorkOrderCode());
        holder.setText(R.id.tvWorkCenter, procedureInputResbean.getWorkcenterName());
        holder.setText(R.id.tvEmployeeName, procedureInputResbean.getEmpName());
        holder.setText(R.id.tvDevice, procedureInputResbean.getEquipmentName());
        holder.setText(R.id.tvStartTime, procedureInputResbean.getActualStartTime());
        holder.setText(R.id.tvEndTime, procedureInputResbean.getActualEndTime());

        RelativeLayout rlRootView = holder.getView(R.id.rlRootView);
        rlRootView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProcedureInputItemClick(procedureInputResbean);
            }
        });

        Button btnDelete = holder.getView(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSwipProcedureInputItemClick(procedureInputResbean);
            }
        });
    }

    public interface OnProcedureInputItemClickListener {
        void onProcedureInputItemClick(ProcedureInputResbean procedureInputResbean);

        void onSwipProcedureInputItemClick(ProcedureInputResbean procedureInputResbean);
    }

    private OnProcedureInputItemClickListener listener;

    public void setOnProcedureInputItemClickListener(OnProcedureInputItemClickListener listener) {
        this.listener = listener;
    }
}
