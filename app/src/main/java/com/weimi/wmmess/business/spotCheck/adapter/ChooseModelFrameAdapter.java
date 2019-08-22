package com.weimi.wmmess.business.spotCheck.adapter;

import android.content.Context;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.spotCheck.bean.ModelFrameResbean;

/**
 * Create by chhyu
 * on 2019/7/27
 * Describle:
 */
public class ChooseModelFrameAdapter extends CommAdapter<ModelFrameResbean> {
    public ChooseModelFrameAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ModelFrameResbean modelFrameResbean, int position) {
        super.convert(holder, modelFrameResbean, position);
        holder.setText(R.id.tvModelName, modelFrameResbean.getModelFrameName());
    }
}
