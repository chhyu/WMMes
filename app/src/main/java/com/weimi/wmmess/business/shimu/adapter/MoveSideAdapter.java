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
import com.weimi.wmmess.business.shimu.bean.step3.MoveSideResbean;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/4/28
 * Describle:第三步动模adapter
 */
public class MoveSideAdapter extends RecyclerView.Adapter<MoveSideAdapter.ViewHolder> {

    private List<MoveSideResbean> moveSideResbeanList;
    private Context context;
    private MoveSideResbean moveSideResbean;

    public MoveSideAdapter(List<MoveSideResbean> moveSideResbeanList, Context context) {
        this.moveSideResbeanList = moveSideResbeanList;
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
        moveSideResbean = moveSideResbeanList.get(i);
        holder.tvPosition.setText(String.valueOf(moveSideResbean.getIndex()));
        if (TextUtils.isEmpty(moveSideResbean.getRecord())) {
            holder.etRecord.setText("");
        } else {
            holder.etRecord.setText(moveSideResbean.getRecord());
        }
    }

    @Override
    public int getItemCount() {
        return moveSideResbeanList == null ? 0 : moveSideResbeanList.size();
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
                    if (moveSideResbean != null) {
                        moveSideResbean.setRecord(etRecord.getText().toString());
                    }
                }
            });
        }
    }
}
