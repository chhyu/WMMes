package com.weimi.wmmess.business.shimu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weimi.wmmess.R;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbean;
import com.weimi.wmmess.business.shimu.bean.history.HistoryResbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by chhyu
 * on 2019/5/28
 * Describle:
 */
public class ShiMuHistoryAdapter extends RecyclerView.Adapter<ShiMuHistoryAdapter.ViewHolder> {

    private Context context;
    private List<HistoryResbean> historyResbeans;

    public ShiMuHistoryAdapter(Context context, List<HistoryResbean> historyResbeans) {
        this.context = context;
        this.historyResbeans = historyResbeans;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_history_list, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        HistoryResbean historyResbean = historyResbeans.get(i);
        holder.tvProductName.setText(historyResbean.getProductName());
        holder.tvTime.setText(historyResbean.getUpdateTime());
        holder.tvModelKernel.setText("模仁：" + historyResbean.getModelKernelName());
        holder.tvModelFrame.setText("模架：" + historyResbean.getModelFrameName());
        holder.tvEquipmentName.setText("设备名称：" + historyResbean.getEquipmentName());
        holder.tvProcedureName.setText("工序名称：" + historyResbean.getProcedureName());

        holder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onHistoryItemClickListener != null) {
                    onHistoryItemClickListener.onItemClick(historyResbean);
                }
            }
        });
    }

    public void addDatas(List<HistoryResbean> historyResbeanList) {
        if (historyResbeanList == null || historyResbeanList.size() == 0) {
            return;
        }
        if (historyResbeans == null) {
            historyResbeans = new ArrayList<>();
        } else {
            historyResbeans.clear();
            for (int i = 0; i < historyResbeanList.size(); i++) {
                historyResbeans.add(historyResbeanList.get(i));
            }
        }
        notifyDataSetChanged();
    }

    public interface OnHistoryItemClickListener {
        void onItemClick(HistoryResbean historyResbean);
    }

    public OnHistoryItemClickListener onHistoryItemClickListener;

    public void setOnHistoryItemClickListener(OnHistoryItemClickListener onHistoryItemClickListener) {
        this.onHistoryItemClickListener = onHistoryItemClickListener;
    }

    @Override
    public int getItemCount() {
        return historyResbeans == null ? 0 : historyResbeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTime;
        private final RelativeLayout rlItem;
        private final TextView tvProductName, tvModelKernel, tvModelFrame, tvEquipmentName, tvProcedureName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlItem = itemView.findViewById(R.id.rlItem);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvModelKernel = itemView.findViewById(R.id.tvModelKernel);
            tvModelFrame = itemView.findViewById(R.id.tvModelFrame);
            tvEquipmentName = itemView.findViewById(R.id.tvEquipmentName);
            tvProcedureName = itemView.findViewById(R.id.tvProcedureName);
        }
    }
}
