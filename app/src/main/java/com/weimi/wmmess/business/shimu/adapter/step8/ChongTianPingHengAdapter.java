package com.weimi.wmmess.business.shimu.adapter.step8;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.bean.step8.ChongTianPingHengResbean;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.utils.NumFormatUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Create by chhyu
 * on 2019/5/29
 * Describle:
 */
public class ChongTianPingHengAdapter extends RecyclerView.Adapter<ChongTianPingHengAdapter.ViewHolder> {

    private Context context;
    private List<ChongTianPingHengResbean> chongTianPingHengResbeanList;

    public static final int ET_CHONGTIAN_XUEHAO = 7;
    public static final int ET_CHONGTIAN_ONE = 1;
    public static final int ET_CHONGTIAN_TWO = 2;
    public static final int ET_CHONGTIAN_THREE = 3;
    public static final int ET_CHONGTIAN_FOUR = 4;
    public static final int ET_CHONGTIAN_FIVE = 5;
    public static final int ET_CHONGTIAN_SIX = 6;

    public ChongTianPingHengAdapter(Context context, List<ChongTianPingHengResbean> chongTianPingHengResbeanList) {
        this.context = context;
        this.chongTianPingHengResbeanList = chongTianPingHengResbeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_step8_chongtian_pingheng, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        ChongTianPingHengResbean chongTianPingHengResbean = chongTianPingHengResbeanList.get(i);

        holder.etXueHao.setTag(i);
        holder.etOne.setTag(i);
        holder.etTwo.setTag(i);
        holder.etThree.setTag(i);
        holder.etFour.setTag(i);
        holder.etFive.setTag(i);
        holder.etSix.setTag(i);

        holder.etXueHao.setText(chongTianPingHengResbean.getXueHao());
        holder.etOne.setText(chongTianPingHengResbean.getOne());
        holder.etTwo.setText(chongTianPingHengResbean.getTwo());
        holder.etThree.setText(chongTianPingHengResbean.getThree());
        holder.etFour.setText(chongTianPingHengResbean.getFour());
        holder.etFive.setText(chongTianPingHengResbean.getFive());
        holder.etSix.setText(chongTianPingHengResbean.getSix());
        holder.etMin.setText(chongTianPingHengResbean.getMin());
        holder.etMax.setText(chongTianPingHengResbean.getMax());
        holder.etAverageValue.setText(chongTianPingHengResbean.getAverageValue());
    }

    @Override
    public int getItemCount() {
        return chongTianPingHengResbeanList == null ? 0 : chongTianPingHengResbeanList.size();
    }

    public interface OnEditCompleteListener {
        void onComplete(String text, int position, int etIndex);
    }

    private OnEditCompleteListener listener;

    public void setOnEditCompleteListener(OnEditCompleteListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private EditText etXueHao, etOne, etTwo, etThree, etFour, etFive, etSix, etMin, etMax, etAverageValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etXueHao = itemView.findViewById(R.id.etXueHao);
            etOne = itemView.findViewById(R.id.etOne);
            etTwo = itemView.findViewById(R.id.etTwo);
            etThree = itemView.findViewById(R.id.etThree);
            etFour = itemView.findViewById(R.id.etFour);
            etFive = itemView.findViewById(R.id.etFive);
            etSix = itemView.findViewById(R.id.etSix);
            etMin = itemView.findViewById(R.id.etMin);
            etMax = itemView.findViewById(R.id.etMax);
            etAverageValue = itemView.findViewById(R.id.etAverageValue);

            etXueHao.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etOne.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etThree.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etFour.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etFive.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etSix.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

            etXueHao.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etXueHao.getTag();
                    String text = etXueHao.getText().toString();
                    ChongTianPingHengResbean chongTianPingHengResbean = chongTianPingHengResbeanList.get(position);
                    chongTianPingHengResbean.setXueHao(text);
                    boolean isTrue = calculateDate(etMin, etMax, etAverageValue, chongTianPingHengResbean);
                    if (isTrue) {
                        if (listener != null) {
                            listener.onComplete(text, position, ET_CHONGTIAN_XUEHAO);
                        }
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
                    ChongTianPingHengResbean chongTianPingHengResbean = chongTianPingHengResbeanList.get(position);
                    chongTianPingHengResbean.setOne(text);
                    boolean isTrue = calculateDate(etMin, etMax, etAverageValue, chongTianPingHengResbean);
                    if (isTrue) {
                        if (listener != null) {
                            listener.onComplete(text, position, ET_CHONGTIAN_ONE);
                        }
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
                    ChongTianPingHengResbean chongTianPingHengResbean = chongTianPingHengResbeanList.get(position);
                    chongTianPingHengResbean.setTwo(text);
                    boolean isTrue = calculateDate(etMin, etMax, etAverageValue, chongTianPingHengResbean);
                    if (isTrue) {
                        if (listener != null) {
                            listener.onComplete(text, position, ET_CHONGTIAN_TWO);
                        }
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
                    ChongTianPingHengResbean chongTianPingHengResbean = chongTianPingHengResbeanList.get(position);
                    chongTianPingHengResbean.setThree(text);
                    boolean isTrue = calculateDate(etMin, etMax, etAverageValue, chongTianPingHengResbean);
                    if (isTrue) {
                        if (listener != null) {
                            listener.onComplete(text, position, ET_CHONGTIAN_THREE);
                        }
                    }
                }
            });

            etFour.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etFour.getTag();
                    String text = etFour.getText().toString();
                    ChongTianPingHengResbean chongTianPingHengResbean = chongTianPingHengResbeanList.get(position);
                    chongTianPingHengResbean.setFour(text);
                    boolean isTrue = calculateDate(etMin, etMax, etAverageValue, chongTianPingHengResbean);
                    if (isTrue) {
                        if (listener != null) {
                            listener.onComplete(text, position, ET_CHONGTIAN_FOUR);
                        }
                    }
                }
            });

            etFive.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etFive.getTag();
                    String text = etFive.getText().toString();
                    ChongTianPingHengResbean chongTianPingHengResbean = chongTianPingHengResbeanList.get(position);
                    chongTianPingHengResbean.setFive(text);
                    boolean isTrue = calculateDate(etMin, etMax, etAverageValue, chongTianPingHengResbean);
                    if (isTrue) {
                        if (listener != null) {
                            listener.onComplete(text, position, ET_CHONGTIAN_FIVE);
                        }
                    }
                }
            });

            etSix.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etSix.getTag();
                    String text = etSix.getText().toString();
                    ChongTianPingHengResbean chongTianPingHengResbean = chongTianPingHengResbeanList.get(position);
                    chongTianPingHengResbean.setSix(text);
                    boolean isTrue = calculateDate(etMin, etMax, etAverageValue, chongTianPingHengResbean);
                    if (isTrue) {
                        if (listener != null) {
                            listener.onComplete(text, position, ET_CHONGTIAN_SIX);
                        }
                    }
                }
            });
        }
    }

    private boolean calculateDate(EditText etMin, EditText etMax, EditText etAverageValue, ChongTianPingHengResbean bean) {
        String one = bean.getOne();
        String two = bean.getTwo();
        String three = bean.getThree();
        String four = bean.getFour();
        String five = bean.getFive();
        String six = bean.getSix();
        if (StringUtils.isEmpty(one)) {
            ToastUtils.showShort("请输入第一列数据");
            return false;
        }
        if (StringUtils.isEmpty(two)) {
            ToastUtils.showShort("请输入第二列数据");
            return false;
        }
        if (StringUtils.isEmpty(three)) {
            ToastUtils.showShort("请输入第三列数据");
            return false;
        }
        if (StringUtils.isEmpty(four)) {
            ToastUtils.showShort("请输入第四列数据");
            return false;
        }
        if (StringUtils.isEmpty(five)) {
            ToastUtils.showShort("请输入第五列数据");
            return false;
        }
        if (StringUtils.isEmpty(six)) {
            ToastUtils.showShort("请输入第六列数据");
            return false;
        }
        try {
            List<String> list = Arrays.asList(one, two, three, four, five, six);
            String min = Collections.min(list);
            String max = Collections.max(list);

            String averageValue = String.valueOf((Double.parseDouble(one) + Double.parseDouble(two) + Double.parseDouble(three) + Double.parseDouble(four) + Double.parseDouble(five) + Double.parseDouble(six)) / 6);
            etMin.setText(min);
            etMax.setText(max);
            etAverageValue.setText(String.valueOf(NumFormatUtil.getDoubleNumberString(Double.parseDouble(averageValue))));
            bean.setMin(min);
            bean.setMax(max);
            bean.setAverageValue(String.valueOf(NumFormatUtil.getDoubleNumberString(Double.parseDouble(averageValue))));
        } catch (Exception e) {
            Log.e("ChongTianPingHengAdap", "计算数据有误哦");
        }
        return true;
    }
}
