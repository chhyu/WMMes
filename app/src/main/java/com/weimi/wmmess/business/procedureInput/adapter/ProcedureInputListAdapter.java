package com.weimi.wmmess.business.procedureInput.adapter;

import android.content.Context;

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

    }
}
