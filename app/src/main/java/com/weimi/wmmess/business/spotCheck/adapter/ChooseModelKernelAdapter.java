package com.weimi.wmmess.business.spotCheck.adapter;

import android.content.Context;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.spotCheck.bean.ModelFrameResbean;
import com.weimi.wmmess.business.spotCheck.bean.ModelKernelResbean;

/**
 * Create by chhyu
 * on 2019/7/27
 * Describle:
 */
public class ChooseModelKernelAdapter extends CommAdapter<ModelKernelResbean> {
    public ChooseModelKernelAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ModelKernelResbean modelKernelResbean, int position) {
        super.convert(holder, modelKernelResbean, position);
        holder.setText(R.id.tvModelName, modelKernelResbean.getModelKernelName());
    }
}
