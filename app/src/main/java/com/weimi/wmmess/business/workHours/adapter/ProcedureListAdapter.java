package com.weimi.wmmess.business.workHours.adapter;

import android.content.Context;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.workHours.bean.ProcedureResbean;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public class ProcedureListAdapter extends CommAdapter<ProcedureResbean> {

    public ProcedureListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ProcedureResbean procedureResbean, int position) {
        super.convert(holder, procedureResbean, position);
        holder.setText(R.id.tvProcedureName, procedureResbean.getProcedureName());
        holder.setText(R.id.tvProcedureId, procedureResbean.getProcedureId());
        holder.setText(R.id.tvCreateTime, procedureResbean.getCreateTime());
        holder.setText(R.id.tvUpdateTime, procedureResbean.getUpdateTime());
    }
}
