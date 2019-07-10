package com.weimi.wmmess.business.shimu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.weimi.wmmess.R;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.bean.step4.KongZhiDianResbean;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/5/14
 * Describle: 第四步主流道温度测试
 */
public class KongZhiDianAdapter extends RecyclerView.Adapter<KongZhiDianAdapter.ViewHolder> {

    private Context context;
    private List<KongZhiDianResbean> kongZhiDianResbeans;
    private KongZhiDianResbean kongZhiDianResbean;

    public KongZhiDianAdapter(Context context, List<KongZhiDianResbean> kongZhiDianResbeans) {
        this.context = context;
        this.kongZhiDianResbeans = kongZhiDianResbeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.step3_record_layout, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        kongZhiDianResbean = kongZhiDianResbeans.get(i);
        holder.tvPosition.setText(String.valueOf(kongZhiDianResbean.getIndex()));
        if (TextUtils.isEmpty(kongZhiDianResbean.getRecord())) {
            holder.etRecord.setText("");
        } else {
            holder.etRecord.setText(kongZhiDianResbean.getRecord());
        }
    }

    @Override
    public int getItemCount() {
        return kongZhiDianResbeans == null ? 0 : kongZhiDianResbeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPosition;
        private final EditText etRecord;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            etRecord = itemView.findViewById(R.id.etRecord);
            etRecord.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

            etRecord.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (kongZhiDianResbean != null) {
                        kongZhiDianResbean.setRecord(etRecord.getText().toString());
                    }
                }
            });
        }
    }
}
