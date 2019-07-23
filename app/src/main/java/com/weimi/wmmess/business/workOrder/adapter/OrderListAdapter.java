package com.weimi.wmmess.business.workOrder.adapter;

import android.content.Context;
import android.widget.TextView;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;

/**
 * Create by chhyu
 * on 2019/7/1
 * Describle: 工单列表adapter
 */
public class OrderListAdapter extends CommAdapter<WorkOrderListResbean> {

    public OrderListAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, WorkOrderListResbean workOrderListResbean, int position) {
        super.convert(holder, workOrderListResbean, position);
        TextView tvWorkOrderName = holder.getView(R.id.tvWorkOrderName);
        TextView tvWorkOrderCode = holder.getView(R.id.tvWorkOrderCode);
        TextView tvProductType = holder.getView(R.id.tvProductType);
        TextView tvPlanCount = holder.getView(R.id.tvPlanCount);
        TextView tvState = holder.getView(R.id.tvState);
        TextView tvStartTime = holder.getView(R.id.tvStartTime);
        TextView tvEndTime = holder.getView(R.id.tvEndTime);

        tvWorkOrderName.setText(workOrderListResbean.getWorkOrderCode());
        tvWorkOrderCode.setText(workOrderListResbean.getWorkOrderId());
        tvProductType.setText(workOrderListResbean.getWorkOrderType());
        tvPlanCount.setText(workOrderListResbean.getPlanCount() + "");
        tvStartTime.setText(workOrderListResbean.getPlanStartTime());
        tvEndTime.setText(workOrderListResbean.getPlanEndTime());
    }
}
