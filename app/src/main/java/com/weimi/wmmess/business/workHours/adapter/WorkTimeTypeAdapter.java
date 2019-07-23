package com.weimi.wmmess.business.workHours.adapter;

import android.content.Context;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.workHours.bean.WorkTimeTypeResbean;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public class WorkTimeTypeAdapter extends CommAdapter<WorkTimeTypeResbean> {
    public WorkTimeTypeAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, WorkTimeTypeResbean workTimeTypeResbean, int position) {
        super.convert(holder, workTimeTypeResbean, position);

        holder.setText(R.id.tvWorkTimeTypeName, workTimeTypeResbean.getWorkhourKindName());
        holder.setText(R.id.tvWorkTimeTypeId, workTimeTypeResbean.getKindId());
        holder.setText(R.id.tvWorkTimeTypeCode, workTimeTypeResbean.getWorkhourKindCode());
        holder.setText(R.id.tvUpdateTime, workTimeTypeResbean.getUpdateTime());
    }
}
