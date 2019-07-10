package com.weimi.wmmess.business.shimu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weimi.wmmess.R;
import com.weimi.wmmess.business.shimu.bean.StepResbean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/4/28
 * Describle:
 */
public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.ViewHolder> {

    private Context context;
    private List<StepResbean> stepResbeanList;
    private boolean isHistory;

    public StepListAdapter(Context context, List<StepResbean> stepResbeanList, boolean isHistory) {
        this.context = context;
        this.isHistory = isHistory;
        this.stepResbeanList = stepResbeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_step_list, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        StepResbean stepResbean = stepResbeanList.get(i);
        holder.tvStepName.setText(stepResbean.getStepName());
        if (stepResbean.isChecked() || isHistory) {
            holder.rlStepItem.setBackgroundResource(R.drawable.shape_step_bg_checked);
            holder.ivCheckedState.setVisibility(View.VISIBLE);
        } else {
            holder.rlStepItem.setBackgroundResource(R.drawable.shape_step_bg_normal);
            holder.ivCheckedState.setVisibility(View.GONE);
        }
        holder.rlStepItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRcvItemClickListener != null) {
                    onRcvItemClickListener.onRcvItemClick(stepResbean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stepResbeanList == null ? 0 : stepResbeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvStepName;
        private final ImageView ivCheckedState;
        private final RelativeLayout rlStepItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlStepItem = itemView.findViewById(R.id.rlStepItem);
            ivCheckedState = itemView.findViewById(R.id.ivCheckedState);
            tvStepName = itemView.findViewById(R.id.tvStepName);
        }
    }

    public interface OnRcvItemClickListener {
        void onRcvItemClick(StepResbean stepResbean);
    }

    private OnRcvItemClickListener onRcvItemClickListener;

    public void setOnRcvItemClickListener(OnRcvItemClickListener onRcvItemClickListener) {
        this.onRcvItemClickListener = onRcvItemClickListener;
    }
}
