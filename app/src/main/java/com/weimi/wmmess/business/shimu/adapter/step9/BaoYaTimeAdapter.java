package com.weimi.wmmess.business.shimu.adapter.step9;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaTimeResbean;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.utils.NumFormatUtil;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/5/22
 * Describle: 第九步--保压时间adapter
 */
public class BaoYaTimeAdapter extends RecyclerView.Adapter<BaoYaTimeAdapter.ViewHolder> {

    private Context context;
    private List<BaoYaTimeResbean> datas;
    public static int ET_BAOYA_TIME = 0;
    public static int ET_TIME_ONE = 1;
    public static int ET_TIME_TWO = 2;
    public static int ET_TIME_THREE = 3;
    public static int ET_TIME_OAVERAGE = 4;
    public static int ET_TIME_BEIZHU = 5;

    public BaoYaTimeAdapter(Context context, List<BaoYaTimeResbean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.step9_time_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        BaoYaTimeResbean bean = datas.get(i);
        holder.etTime.setTag(i);
        holder.etOne.setTag(i);
        holder.etTwo.setTag(i);
        holder.etThree.setTag(i);
        holder.etAverageValue.setTag(i);
        holder.etBeiZhu.setTag(i);

        holder.etTime.setText(bean.getTime());
        holder.etOne.setText(bean.getTimeOne());
        holder.etTwo.setText(bean.getTimeTwo());
        holder.etThree.setText(bean.getTimeThree());
        holder.etAverageValue.setText(bean.getTimeAverageValue());
        holder.etBeiZhu.setText(bean.getTimeBeizhu());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public interface OnTimeEditCompleteListener {
        void onComplete(String text, int position, int ET_YALI_BeiZhu);
    }

    private OnTimeEditCompleteListener listener;

    public void setOnTimeEditCompleteListener(OnTimeEditCompleteListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final EditText etTime, etOne, etTwo, etThree, etAverageValue, etBeiZhu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etTime = itemView.findViewById(R.id.etTime);
            etOne = itemView.findViewById(R.id.etOne);
            etTwo = itemView.findViewById(R.id.etTwo);
            etThree = itemView.findViewById(R.id.etThree);
            etAverageValue = itemView.findViewById(R.id.etAverageValue);
            etBeiZhu = itemView.findViewById(R.id.etBeiZhu);

            etTime.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etOne.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etThree.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etAverageValue.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

            etTime.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etTime.getTag();
                    String text = etTime.getText().toString();
                    datas.get(position).setTime(text);
                    calculateYaLiAverageVulue(etAverageValue, position);
                    if (listener != null) {
                        listener.onComplete(text, position, ET_BAOYA_TIME);
                    }
                }
            });

            etOne.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etOne.getTag();
                    String text = etOne.getText().toString();
                    datas.get(position).setTimeOne(text);
                    calculateYaLiAverageVulue(etAverageValue, position);
                    if (listener != null) {
                        listener.onComplete(text, position, ET_TIME_ONE);
                    }
                }
            });
            etTwo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etTwo.getTag();
                    String text = etTwo.getText().toString();
                    datas.get(position).setTimeTwo(text);
                    calculateYaLiAverageVulue(etAverageValue, position);
                    if (listener != null) {
                        listener.onComplete(text, position, ET_TIME_TWO);
                    }
                }
            });
            etThree.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etThree.getTag();
                    String text = etThree.getText().toString();
                    datas.get(position).setTimeThree(text);
                    calculateYaLiAverageVulue(etAverageValue, position);
                    if (listener != null) {
                        listener.onComplete(text, position, ET_TIME_THREE);
                    }
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
                    datas.get(position).setTimeBeizhu(text);
                    calculateYaLiAverageVulue(etAverageValue, position);
                    if (listener != null) {
                        listener.onComplete(text, position, ET_TIME_BEIZHU);
                    }
                }
            });
        }
    }

    private void calculateYaLiAverageVulue(EditText etAverageValue, int position) {
        BaoYaTimeResbean bean = datas.get(position);
        if (StringUtils.isEmpty(bean.getTimeOne()) || bean.getTimeOne().equals("0")) {
            ToastUtils.showShort("请输入第一模数据");
            return;
        }
        if (StringUtils.isEmpty(bean.getTimeTwo()) || bean.getTimeTwo().equals("0")) {
            ToastUtils.showShort("请输入第二模数据");
            return;
        }
        if (StringUtils.isEmpty(bean.getTimeThree()) || bean.getTimeThree().equals("0")) {
            ToastUtils.showShort("请输入第三模数据");
            return;
        }
        double one = Double.parseDouble(bean.getTimeOne());
        double two = Double.parseDouble(bean.getTimeTwo());
        double three = Double.parseDouble(bean.getTimeThree());
        double averageVulue = (one + two + three) / 3;
        bean.setTimeAverageValue(String.valueOf(NumFormatUtil.getDoubleNumberString(averageVulue)));
        etAverageValue.setText(String.valueOf(NumFormatUtil.getDoubleNumberString(averageVulue)));
    }
}
