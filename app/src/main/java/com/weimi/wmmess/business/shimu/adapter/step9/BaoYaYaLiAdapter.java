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
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaYaLiResbean;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.utils.NumFormatUtil;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/5/22
 * Describle: 第九步：保压压力adapter
 */
public class BaoYaYaLiAdapter extends RecyclerView.Adapter<BaoYaYaLiAdapter.ViewHolder> {

    private Context context;
    private List<BaoYaYaLiResbean> datas;
    private ViewHolder holder;
    public static int ET_BAOYA_YALI = 0;
    public static int ET_YALI_ONE = 1;
    public static int ET_YALI_TWO = 2;
    public static int ET_YALI_THREE = 3;
    public static int ET_YALI_OAVERAGE_VALUE = 4;
    public static int ET_YALI_BeiZhu = 5;

    public BaoYaYaLiAdapter(Context context, List<BaoYaYaLiResbean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item, null);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        BaoYaYaLiResbean bean = datas.get(i);

        holder.etYaLi.setTag(i);
        holder.etOne.setTag(i);
        holder.etTwo.setTag(i);
        holder.etThree.setTag(i);
        holder.etAverageValue.setTag(i);
        holder.etBeiZhu.setTag(i);

        holder.etYaLi.setText(bean.getYali());
        holder.etOne.setText(bean.getYaLiOne());
        holder.etTwo.setText(bean.getYaLiTwo());
        holder.etThree.setText(bean.getYaLiThree());
        holder.etAverageValue.setText(bean.getYaLiAverageValue());
        holder.etBeiZhu.setText(bean.getYaLiBeizhu());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public interface OnYaLiEditCompleteListener {
        void onComplete(String text, int position, int ET_YALI_BeiZhu);
    }

    private OnYaLiEditCompleteListener listener;

    public void setOnYaLiEditCompleteListener(OnYaLiEditCompleteListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final EditText etYaLi, etOne, etTwo, etThree, etAverageValue, etBeiZhu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etYaLi = itemView.findViewById(R.id.etYaLi);
            etOne = itemView.findViewById(R.id.etOne);
            etTwo = itemView.findViewById(R.id.etTwo);
            etThree = itemView.findViewById(R.id.etThree);
            etAverageValue = itemView.findViewById(R.id.etAverageValue);
            etBeiZhu = itemView.findViewById(R.id.etBeiZhu);

            etYaLi.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etOne.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etThree.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etAverageValue.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

            etYaLi.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etYaLi.getTag();
                    datas.get(position).setYali(etYaLi.getText().toString());
                    calculateYaLiAverageVulue(etAverageValue, position);
//                    if (isPass) {
                    if (listener != null) {
                        listener.onComplete(etYaLi.getText().toString(), position, ET_BAOYA_YALI);
//                        }
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
                    datas.get(position).setYaLiOne(etOne.getText().toString());
                    calculateYaLiAverageVulue(etAverageValue, position);
//                    if (isPass) {
                    if (listener != null) {
                        listener.onComplete(etOne.getText().toString(), position, ET_YALI_ONE);
//                        }
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
                    datas.get(position).setYaLiTwo(etTwo.getText().toString());
                    calculateYaLiAverageVulue(etAverageValue, position);
//                    if (isPass) {
                    if (listener != null) {
                        listener.onComplete(etTwo.getText().toString(), position, ET_YALI_TWO);
//                        }
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
                    String etText = etThree.getText().toString();
                    datas.get(position).setYaLiThree(etText);
                    calculateYaLiAverageVulue(etAverageValue, position);
//                    if (isPass) {
                    if (listener != null) {
                        listener.onComplete(etText, position, ET_YALI_THREE);
//                        }
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
                    calculateYaLiAverageVulue(etAverageValue, position);
//                    if (isPass) {
                    if (listener != null) {
                        listener.onComplete(etBeiZhu.getText().toString(), position, ET_YALI_BeiZhu);
//                        }
                    }
                }
            });
        }
    }

    private void calculateYaLiAverageVulue(EditText etAverageValue, int position) {
        BaoYaYaLiResbean bean = datas.get(position);
        if (StringUtils.isEmpty(bean.getYaLiOne()) || bean.getYaLiOne().equals("0")) {
            ToastUtils.showShort("请输入第一模数据");
            return;
        }
        if (StringUtils.isEmpty(bean.getYaLiTwo()) || bean.getYaLiTwo().equals("0")) {
            ToastUtils.showShort("请输入第二模数据");
            return;
        }
        if (StringUtils.isEmpty(bean.getYaLiThree()) || bean.getYaLiThree().equals("0")) {
            ToastUtils.showShort("请输入第三模数据");
            return;
        }
        double one = Double.parseDouble(bean.getYaLiOne());
        double two = Double.parseDouble(bean.getYaLiTwo());
        double three = Double.parseDouble(bean.getYaLiThree());
        double averageVulue = (one + two + three) / 3;
        bean.setYaLiAverageValue(String.valueOf(NumFormatUtil.getDoubleNumberString(averageVulue)));
        etAverageValue.setText(String.valueOf(NumFormatUtil.getDoubleNumberString(averageVulue)));

    }

}
