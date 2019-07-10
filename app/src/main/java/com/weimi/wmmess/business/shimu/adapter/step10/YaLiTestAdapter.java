package com.weimi.wmmess.business.shimu.adapter.step10;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.weimi.wmmess.R;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.bean.step10.YaLiTestResbean;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/5/29
 * Describle:
 */
public class YaLiTestAdapter extends RecyclerView.Adapter<YaLiTestAdapter.ViewHolder> {

    private Context context;
    private List<YaLiTestResbean> yaLiTestResbeans;

    public YaLiTestAdapter(Context context, List<YaLiTestResbean> yaLiTestResbeans) {
        this.context = context;
        this.yaLiTestResbeans = yaLiTestResbeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_step10_yali_test, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        YaLiTestResbean yaLiTestResbean = yaLiTestResbeans.get(i);

        holder.etLengqueShiJian.setTag(i);
        holder.etWaiGuanZhiLiang.setTag(i);
        holder.etChiCun.setTag(i);
        holder.etBeiZhu.setTag(i);

        holder.etLengqueShiJian.setText(yaLiTestResbean.getLengQueTime());
        holder.etWaiGuanZhiLiang.setText(yaLiTestResbean.getZhiLiang());
        holder.etChiCun.setText(yaLiTestResbean.getChiCun());
        holder.etBeiZhu.setText(yaLiTestResbean.getBeiZhu());
    }

    @Override
    public int getItemCount() {
        return yaLiTestResbeans == null ? 0 : yaLiTestResbeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private EditText etLengqueShiJian, etWaiGuanZhiLiang, etChiCun, etBeiZhu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etLengqueShiJian = itemView.findViewById(R.id.etLengqueShiJian);
            etWaiGuanZhiLiang = itemView.findViewById(R.id.etWaiGuanZhiLiang);
            etChiCun = itemView.findViewById(R.id.etChiCun);
            etBeiZhu = itemView.findViewById(R.id.etBeiZhu);

            etLengqueShiJian.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etChiCun.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

            etLengqueShiJian.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etLengqueShiJian.getTag();
                    String text = etLengqueShiJian.getText().toString();
                    YaLiTestResbean yaLiTestResbean = yaLiTestResbeans.get(position);
                    yaLiTestResbean.setLengQueTime(text);
                }
            });
            etWaiGuanZhiLiang.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etWaiGuanZhiLiang.getTag();
                    String text = etWaiGuanZhiLiang.getText().toString();
                    YaLiTestResbean yaLiTestResbean = yaLiTestResbeans.get(position);
                    yaLiTestResbean.setZhiLiang(text);
                }
            });
            etChiCun.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etChiCun.getTag();
                    String text = etChiCun.getText().toString();
                    YaLiTestResbean yaLiTestResbean = yaLiTestResbeans.get(position);
                    yaLiTestResbean.setChiCun(text);
                }
            });
            etBeiZhu.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etBeiZhu.getTag();
                    String text = etBeiZhu.getText().toString();
                    YaLiTestResbean yaLiTestResbean = yaLiTestResbeans.get(position);
                    yaLiTestResbean.setBeiZhu(text);
                }
            });
        }
    }
}
