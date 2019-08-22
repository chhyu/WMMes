package com.weimi.wmmess.business.spotCheck.adapter;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.spotCheck.bean.SpotCheckItemResbean;

/**
 * Create by chhyu
 * on 2019/7/26
 * Describle:
 */
public class SpotCheckItemAdapter extends CommAdapter<SpotCheckItemResbean> {

    public SpotCheckItemAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, SpotCheckItemResbean spotCheckItemResbean, int position) {
        super.convert(holder, spotCheckItemResbean, position);

        holder.setText(R.id.tvIndex, position + 1 + "");
        holder.setText(R.id.tvSpotCheckName, spotCheckItemResbean.getContent());
        RadioGroup rgCheckItem = holder.getView(R.id.rgCheckItem);
        RadioButton rbResultTrue = holder.getView(R.id.rbResultTrue);
        RadioButton rbResultFalse = holder.getView(R.id.rbResultFalse);
        if (spotCheckItemResbean.isCheckResult()) {
            rbResultTrue.setChecked(true);
            rbResultFalse.setChecked(false);
        } else {
            rbResultTrue.setChecked(false);
            rbResultFalse.setChecked(true);
        }
        rgCheckItem.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbResultTrue:
                    spotCheckItemResbean.setCheckResult(true);
                    break;
                case R.id.rbResultFalse:
                    spotCheckItemResbean.setCheckResult(false);
                    break;
                default:
                    break;
            }
        });
    }
}
