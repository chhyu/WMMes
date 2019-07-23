package com.weimi.wmmess.business.workHours.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.workHours.WorkHourActivity;
import com.weimi.wmmess.business.workHours.activity.AddWorkHourActivity;
import com.weimi.wmmess.business.workHours.bean.WorkHourListResbean;
import com.weimi.wmmess.widget.emptyView.ICallback;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public class WorkHourAdapter extends CommAdapter<WorkHourListResbean> {
    public WorkHourAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, WorkHourListResbean workHourListResbean, int position) {
        super.convert(holder, workHourListResbean, position);
        holder.setText(R.id.tvUserName, workHourListResbean.getEmpName());
        holder.setText(R.id.tvWorkOrder, workHourListResbean.getWorkOrderCode());
        holder.setText(R.id.tvGongXu, workHourListResbean.getProcedureName());
        holder.setText(R.id.tvBanCi, workHourListResbean.getShift());
        holder.setText(R.id.tvStartTime, workHourListResbean.getActualStartTime());
        holder.setText(R.id.tvEndTime, workHourListResbean.getActualEndTime());
        holder.setText(R.id.tvEndTime, workHourListResbean.getActualEndTime());

        holder.getView(R.id.rlRootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onWorkHourItemClickListener != null) {
                    onWorkHourItemClickListener.onWorkHourItemClick(workHourListResbean);
                }
            }
        });

        Button btnDelete = holder.getView(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(workHourListResbean);
            }
        });
    }

    private OnSwipeDeleteClickListener listener;

    public void setOnSwipeDeleteClickListener(OnSwipeDeleteClickListener listener) {
        this.listener = listener;
    }

    public interface OnSwipeDeleteClickListener {
        void onDeleteClick(WorkHourListResbean workHourListResbean);
    }

    public interface OnWorkHourItemClickListener {
        void onWorkHourItemClick(WorkHourListResbean workHourListResbean);
    }

    private OnWorkHourItemClickListener onWorkHourItemClickListener;

    public void setOnWorkHourItemClickListener(OnWorkHourItemClickListener onWorkHourItemClickListener) {
        this.onWorkHourItemClickListener = onWorkHourItemClickListener;
    }

}
