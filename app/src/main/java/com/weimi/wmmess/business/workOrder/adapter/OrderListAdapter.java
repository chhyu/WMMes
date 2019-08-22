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
        TextView tvWorkOrderCode = holder.getView(R.id.tvWorkOrderCode);

        TextView tvBomCode = holder.getView(R.id.tvBomCode);
        TextView tvProductCode = holder.getView(R.id.tvProductCode);
        TextView tvProductType = holder.getView(R.id.tvProductType);
        TextView tvModelKernel = holder.getView(R.id.tvModelKernel);

        TextView tvBomName = holder.getView(R.id.tvBomName);
        TextView tvProductName = holder.getView(R.id.tvProductName);
        TextView tvCraftName = holder.getView(R.id.tvCraftName);
        TextView tvModelFrame = holder.getView(R.id.tvModelFrame);

        tvWorkOrderCode.setText(workOrderListResbean.getWorkOrderCode());

        tvBomCode.setText(workOrderListResbean.getBomCode());
        tvProductCode.setText(workOrderListResbean.getProductCode());
        tvProductType.setText(workOrderListResbean.getWorkOrderType());
        tvModelKernel.setText(workOrderListResbean.getModelFrameCode());

        tvBomName.setText(workOrderListResbean.getBomName());
        tvProductName.setText(workOrderListResbean.getProductName());
        tvCraftName.setText(workOrderListResbean.getCraftName());
        tvModelFrame.setText(workOrderListResbean.getModelFrameCode());
    }
}
