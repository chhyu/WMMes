package com.weimi.wmmess.business.workHours.adapter;

import android.content.Context;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.workHours.bean.WorkCenterResbean;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public class WorkCenterAdapter extends CommAdapter<WorkCenterResbean> {
    public WorkCenterAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, WorkCenterResbean workCenterResbean, int position) {
        super.convert(holder, workCenterResbean, position);
        holder.setText(R.id.tvWorkCenterName, workCenterResbean.getWorkcenterName());
        holder.setText(R.id.tvWorkCenterId, workCenterResbean.getWorkcenterId());
        holder.setText(R.id.tvWorkCenterCode, workCenterResbean.getWorkcenterCode());
        holder.setText(R.id.tvWorkCenterDes, workCenterResbean.getWorkcenterDesc());
    }
}
