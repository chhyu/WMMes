package com.weimi.wmmess.business.shimu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.weimi.wmmess.R;
import com.weimi.wmmess.business.shimu.bean.step3.Step3ShuiLuDate;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/4/28
 * Describle:
 */
public class Step3ShuiLuLianjieAdapter extends RecyclerView.Adapter<Step3ShuiLuLianjieAdapter.ViewHolder> {

    private List<Step3ShuiLuDate> resBeanList;
    private Context context;
    private Step3ShuiLuDate step3ShuiLuDate;

    public Step3ShuiLuLianjieAdapter(List<Step3ShuiLuDate> resBeanList, Context context) {
        this.resBeanList = resBeanList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = View.inflate(context, R.layout.step3_shuilulianjie_record_layout, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        step3ShuiLuDate = resBeanList.get(i);
        holder.tvPosition.setText(String.valueOf(step3ShuiLuDate.getIndex()));
        if (TextUtils.isEmpty(step3ShuiLuDate.getRecord())) {
            holder.etRecord.setText("");
        } else {
            holder.etRecord.setText(step3ShuiLuDate.getRecord());
        }
    }

    @Override
    public int getItemCount() {
        return resBeanList == null ? 0 : resBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvPosition;
        private final EditText etRecord;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            etRecord = itemView.findViewById(R.id.etRecord);

            etRecord.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (step3ShuiLuDate != null) {
                        step3ShuiLuDate.setRecord(etRecord.getText().toString());
                    }
                }
            });
        }
    }
}
