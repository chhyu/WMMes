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
import com.weimi.wmmess.business.shimu.bean.step3.SlideResbean;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/5/13
 * Describle:滑块adapter
 */
public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.ViewHolder> {

    private List<SlideResbean> slideResbeanList;
    private Context context;
    private SlideResbean slideResbean;

    public SlideAdapter(List<SlideResbean> slideResbeanList, Context context) {
        this.slideResbeanList = slideResbeanList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = View.inflate(context, R.layout.step3_record_layout, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        slideResbean = slideResbeanList.get(i);
        holder.tvPosition.setText(String.valueOf(slideResbean.getIndex()));
        if (TextUtils.isEmpty(slideResbean.getRecord())) {
            holder.etRecord.setText("");
        } else {
            holder.etRecord.setText(slideResbean.getRecord());
        }
    }

    @Override
    public int getItemCount() {
        return slideResbeanList == null ? 0 : slideResbeanList.size();
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
                    if (slideResbean != null) {
                        slideResbean.setRecord(etRecord.getText().toString());
                    }
                }
            });
        }
    }
}
