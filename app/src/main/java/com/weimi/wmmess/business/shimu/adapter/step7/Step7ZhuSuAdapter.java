package com.weimi.wmmess.business.shimu.adapter.step7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.bean.step7.ZhuSuResbean;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.utils.NumFormatUtil;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/5/15
 * Describle:
 */
public class Step7ZhuSuAdapter extends RecyclerView.Adapter<Step7ZhuSuAdapter.ViewHolder> {

    private Context context;
    private List<ZhuSuResbean> datas;
    public static final int ET_ZHUSU_SUDU = 1;
    public static final int ET_ZHUSU_SHIJIAN = 2;
    public static final int ET_ZHUSU_YALI = 3;

    public Step7ZhuSuAdapter(Context context, List<ZhuSuResbean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.step7_rcv_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        ZhuSuResbean zhuSuResbean = datas.get(i);

        holder.etSuDu.setTag(i);
        holder.etTime.setTag(i);
        holder.etYaLiMax.setTag(i);

        holder.etSuDu.setText(zhuSuResbean.getZhuSuSuDu());
        holder.etTime.setText(zhuSuResbean.getZhuSuTime());
        holder.etYaLiMax.setText(zhuSuResbean.getYaLiMax());
        holder.tvYaLiAndTime.setText(zhuSuResbean.getYaliAndTime());
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public interface OnEditCompleteListener {
        void onComplete(String text, int position, int etIndex);
    }

    private OnEditCompleteListener listener;

    public void setOnEditCompleteListener(OnEditCompleteListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final EditText etSuDu, etTime, etYaLiMax;
        private final TextView tvYaLiAndTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etSuDu = itemView.findViewById(R.id.etSuDu);
            etTime = itemView.findViewById(R.id.etTime);
            etYaLiMax = itemView.findViewById(R.id.etYaLiMax);
            tvYaLiAndTime = itemView.findViewById(R.id.tvYaLiAndTime);

            etSuDu.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etTime.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
            etYaLiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

            etSuDu.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etSuDu.getTag();
                    String text = etSuDu.getText().toString();
                    datas.get(position).setZhuSuSuDu(text);
                    calculateYaLiAndShiJian(tvYaLiAndTime, datas.get(position));
                    if (listener != null) {
                        listener.onComplete(text, position, ET_ZHUSU_SUDU);
                    }
                }
            });

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
                    datas.get(position).setZhuSuTime(text);
                    calculateYaLiAndShiJian(tvYaLiAndTime, datas.get(position));
                    if (listener != null) {
                        listener.onComplete(text, position, ET_ZHUSU_SHIJIAN);
                    }
                }
            });

            etYaLiMax.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etYaLiMax.getTag();
                    String text = etYaLiMax.getText().toString();
                    datas.get(position).setYaLiMax(text);
                    calculateYaLiAndShiJian(tvYaLiAndTime, datas.get(position));
                    if (listener != null) {
                        listener.onComplete(text, position, ET_ZHUSU_YALI);
                    }
                }
            });
        }
    }

    private void calculateYaLiAndShiJian(TextView tvYaLiAndTime, ZhuSuResbean zhuSuResbean) {
        try {
            String zhuSuTime = zhuSuResbean.getZhuSuTime();
            String yaLiMax = zhuSuResbean.getYaLiMax();
            if (StringUtils.isEmpty(zhuSuTime)) {
                ToastUtils.showShort("请输入注塑时间");
                return;
            }
            if (StringUtils.isEmpty(yaLiMax)) {
                ToastUtils.showShort("请输入注塑压力");
                return;
            }
            double shijian = Double.parseDouble(zhuSuTime);
            double yali = Double.parseDouble(yaLiMax);

            zhuSuResbean.setYaliAndTime(NumFormatUtil.getDoubleNumberString(shijian * yali));
            tvYaLiAndTime.setText(NumFormatUtil.getDoubleNumberString(shijian * yali));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
