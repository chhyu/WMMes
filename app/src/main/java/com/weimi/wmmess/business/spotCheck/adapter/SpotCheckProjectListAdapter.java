package com.weimi.wmmess.business.spotCheck.adapter;

import android.content.Context;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.spotCheck.bean.SpotCheckProjectResbean;

/**
 * Create by chhyu
 * on 2019/7/26
 * Describle:
 */
public class SpotCheckProjectListAdapter extends CommAdapter<SpotCheckProjectResbean> {

    public SpotCheckProjectListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, SpotCheckProjectResbean spotCheckProjectResbean, int position) {
        super.convert(holder, spotCheckProjectResbean, position);
        holder.setText(R.id.tvCheckTypeName, spotCheckProjectResbean.getCheckTypeName());
        holder.setText(R.id.tvCheckTypeId, spotCheckProjectResbean.getCheckTypeId());
        holder.setText(R.id.tvCheckTypeCode, spotCheckProjectResbean.getCheckTypeCode());
    }
}
