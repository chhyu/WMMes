package com.weimi.wmmess.business.workHours.adapter;

import android.content.Context;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.workHours.bean.WorkTypeResbean;

/**
 * Create by chhyu
 * on 2019/7/23
 * Describle:
 */
public class WorkTypeAdapter extends CommAdapter<WorkTypeResbean> {
    public WorkTypeAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, WorkTypeResbean workTypeResbean, int position) {
        super.convert(holder, workTypeResbean, position);

        holder.setText(R.id.tvWorkTypeName,workTypeResbean.getWorkKindName());
        holder.setText(R.id.tvWorkTypeId,workTypeResbean.getKindId());
        holder.setText(R.id.tvWorkTypeCode,workTypeResbean.getWorkKindCode());
        holder.setText(R.id.tvUpdateTime,workTypeResbean.getUpdateTime());
    }
}
