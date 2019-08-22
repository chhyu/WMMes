package com.weimi.wmmess.business.spotCheck.adapter;

import android.content.Context;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.spotCheck.bean.EquipmentTypeResbean;

/**
 * Create by chhyu
 * on 2019/7/27
 * Describle:
 */
public class EquipmentTypeAdapter extends CommAdapter<EquipmentTypeResbean> {
    public EquipmentTypeAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, EquipmentTypeResbean equipmentTypeResbean, int position) {
        super.convert(holder, equipmentTypeResbean, position);

        holder.setText(R.id.tvEquipmentName,"名称："+equipmentTypeResbean.getName());
    }
}
