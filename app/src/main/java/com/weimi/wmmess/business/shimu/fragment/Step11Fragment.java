package com.weimi.wmmess.business.shimu.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.bean.step11.Step11Resbean;
import com.weimi.wmmess.business.shimu.bean.step11.Step11ResbeanDao;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.business.shimu.presenter.Step11Presenter;
import com.weimi.wmmess.utils.NumFormatUtil;
import com.weimi.wmmess.utils.TestUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 稳定性测试（测试设备&模具&工艺稳定性）
 */
public class Step11Fragment extends BaseFragment<Step11Presenter> implements View.OnClickListener {
    private static final int stepPosition = 10;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnNextStep;
    private EditText etHan1, etHan2, etHan3, etHan4, etHan5, etHan6, etHan7, etHan8, etHan9, etHan10, etHan11, etHan12, etHan13, etHan14, etHan15;
    private EditText etHan16, etHan17, etHan18, etHan19, etHan20, etHan21, etHan22, etHan23, etHan24, etHan25, etHan26, etHan27, etHan28, etHan29, etHan30;
    private EditText etBuHan1, etBuHan2, etBuHan3, etBuHan4, etBuHan5, etBuHan6, etBuHan7, etBuHan8, etBuHan9, etBuHan10, etBuHan11, etBuHan12, etBuHan13, etBuHan14, etBuHan15;
    private EditText etBuHan16, etBuHan17, etBuHan18, etBuHan19, etBuHan20, etBuHan21, etBuHan22, etBuHan23, etBuHan24, etBuHan25, etBuHan26, etBuHan27, etBuHan28, etBuHan29, etBuHan30;
    private Step11Resbean step11Resbean;
    private Step11Presenter presenter;
    private TextView tvMin, tvMax, tvPercent;
    private CheckBox cbOk, cbNG;

    public static Step11Fragment newInstance(long historyId, boolean isHistory) {
        Step11Fragment fragment = new Step11Fragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, historyId);
        args.putBoolean(ARG_PARAM2, isHistory);
        fragment.setArguments(args);
        return fragment;
    }

    private Long historyId;
    private boolean isHistory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            historyId = getArguments().getLong(ARG_PARAM1);
            isHistory = getArguments().getBoolean(ARG_PARAM2);
        }
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_step11;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        btnNextStep = rootView.findViewById(R.id.btnNextStep);

        etHan1 = rootView.findViewById(R.id.etHan1);
        etHan2 = rootView.findViewById(R.id.etHan2);
        etHan3 = rootView.findViewById(R.id.etHan3);
        etHan4 = rootView.findViewById(R.id.etHan4);
        etHan5 = rootView.findViewById(R.id.etHan5);
        etHan6 = rootView.findViewById(R.id.etHan6);
        etHan7 = rootView.findViewById(R.id.etHan7);
        etHan8 = rootView.findViewById(R.id.etHan8);
        etHan9 = rootView.findViewById(R.id.etHan9);
        etHan10 = rootView.findViewById(R.id.etHan10);
        etHan11 = rootView.findViewById(R.id.etHan11);
        etHan12 = rootView.findViewById(R.id.etHan12);
        etHan13 = rootView.findViewById(R.id.etHan13);
        etHan14 = rootView.findViewById(R.id.etHan14);
        etHan15 = rootView.findViewById(R.id.etHan15);
        etHan16 = rootView.findViewById(R.id.etHan16);
        etHan17 = rootView.findViewById(R.id.etHan17);
        etHan18 = rootView.findViewById(R.id.etHan18);
        etHan19 = rootView.findViewById(R.id.etHan19);
        etHan20 = rootView.findViewById(R.id.etHan20);
        etHan21 = rootView.findViewById(R.id.etHan21);
        etHan22 = rootView.findViewById(R.id.etHan22);
        etHan23 = rootView.findViewById(R.id.etHan23);
        etHan24 = rootView.findViewById(R.id.etHan24);
        etHan25 = rootView.findViewById(R.id.etHan25);
        etHan26 = rootView.findViewById(R.id.etHan26);
        etHan27 = rootView.findViewById(R.id.etHan27);
        etHan28 = rootView.findViewById(R.id.etHan28);
        etHan29 = rootView.findViewById(R.id.etHan29);
        etHan30 = rootView.findViewById(R.id.etHan30);

        etHan1.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan2.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan3.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan4.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan5.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan6.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan7.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan8.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan9.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan10.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan11.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan12.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan13.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan14.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan15.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan16.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan17.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan18.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan19.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan20.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan22.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan23.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan24.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan25.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan26.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan27.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan28.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan29.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHan30.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBuHan1 = rootView.findViewById(R.id.etBuHan1);
        etBuHan2 = rootView.findViewById(R.id.etBuHan2);
        etBuHan3 = rootView.findViewById(R.id.etBuHan3);
        etBuHan4 = rootView.findViewById(R.id.etBuHan4);
        etBuHan5 = rootView.findViewById(R.id.etBuHan5);
        etBuHan6 = rootView.findViewById(R.id.etBuHan6);
        etBuHan7 = rootView.findViewById(R.id.etBuHan7);
        etBuHan8 = rootView.findViewById(R.id.etBuHan8);
        etBuHan9 = rootView.findViewById(R.id.etBuHan9);
        etBuHan10 = rootView.findViewById(R.id.etBuHan10);
        etBuHan11 = rootView.findViewById(R.id.etBuHan11);
        etBuHan12 = rootView.findViewById(R.id.etBuHan12);
        etBuHan13 = rootView.findViewById(R.id.etBuHan13);
        etBuHan14 = rootView.findViewById(R.id.etBuHan14);
        etBuHan15 = rootView.findViewById(R.id.etBuHan15);
        etBuHan16 = rootView.findViewById(R.id.etBuHan16);
        etBuHan17 = rootView.findViewById(R.id.etBuHan17);
        etBuHan18 = rootView.findViewById(R.id.etBuHan18);
        etBuHan19 = rootView.findViewById(R.id.etBuHan19);
        etBuHan20 = rootView.findViewById(R.id.etBuHan20);
        etBuHan21 = rootView.findViewById(R.id.etBuHan21);
        etBuHan22 = rootView.findViewById(R.id.etBuHan22);
        etBuHan23 = rootView.findViewById(R.id.etBuHan23);
        etBuHan24 = rootView.findViewById(R.id.etBuHan24);
        etBuHan25 = rootView.findViewById(R.id.etBuHan25);
        etBuHan26 = rootView.findViewById(R.id.etBuHan26);
        etBuHan27 = rootView.findViewById(R.id.etBuHan27);
        etBuHan28 = rootView.findViewById(R.id.etBuHan28);
        etBuHan29 = rootView.findViewById(R.id.etBuHan29);
        etBuHan30 = rootView.findViewById(R.id.etBuHan30);

        etBuHan1.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBuHan3.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan4.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan5.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan6.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan7.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan8.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan9.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan10.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan11.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan12.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan13.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan14.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan15.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan16.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan17.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan18.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan19.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan20.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan21.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan22.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan23.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan24.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan25.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan26.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan27.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan28.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan29.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBuHan30.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        tvMin = rootView.findViewById(R.id.tvMin);
        tvMax = rootView.findViewById(R.id.tvMax);
        tvPercent = rootView.findViewById(R.id.tvPercent);
        cbOk = rootView.findViewById(R.id.cbOk);
        cbNG = rootView.findViewById(R.id.cbNG);

        btnNextStep.setOnClickListener(this);
        cbNG.setOnClickListener(this);
        cbOk.setOnClickListener(this);

        etHan30.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                calculateValue();
            }
        });
    }

    private void calculateValue() {
        try { //为防止输入框未填写内容时抛NumberFormatException
            valueList.add((double) Integer.parseInt(etHan1.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan2.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan3.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan4.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan5.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan6.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan7.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan8.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan9.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan10.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan11.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan12.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan13.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan14.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan15.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan16.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan17.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan18.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan19.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan20.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan21.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan22.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan23.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan24.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan25.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan26.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan27.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan28.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan29.getText().toString()));
            valueList.add((double) Integer.parseInt(etHan30.getText().toString()));

            Double min = Collections.min(valueList);
            Double max = Collections.max(valueList);
            double percent = 1 - min / max;
            tvMin.setText(String.valueOf(NumFormatUtil.getDoubleNumberString(min)));
            tvMax.setText(String.valueOf(NumFormatUtil.getDoubleNumberString(max)));
            tvPercent.setText(String.valueOf(NumFormatUtil.getDoubleNumberString(percent)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        presenter = new Step11Presenter(this);
        step11Resbean = new Step11Resbean();
        if (historyId != 0 && isHistory == true) {
            showHistoryData();
        }
    }

    private void showHistoryData() {
        try {
            Step11Resbean step11Resbean = MainApplication.daoSession.getStep11ResbeanDao().load(historyId);
            etHan1.setText(step11Resbean.getHan1());
            etHan2.setText(step11Resbean.getHan2());
            etHan3.setText(step11Resbean.getHan3());
            etHan4.setText(step11Resbean.getHan4());
            etHan5.setText(step11Resbean.getHan5());
            etHan6.setText(step11Resbean.getHan6());
            etHan7.setText(step11Resbean.getHan7());
            etHan8.setText(step11Resbean.getHan8());
            etHan9.setText(step11Resbean.getHan9());
            etHan10.setText(step11Resbean.getHan10());
            etHan11.setText(step11Resbean.getHan11());
            etHan12.setText(step11Resbean.getHan12());
            etHan13.setText(step11Resbean.getHan13());
            etHan14.setText(step11Resbean.getHan14());
            etHan15.setText(step11Resbean.getHan15());
            etHan16.setText(step11Resbean.getHan16());
            etHan17.setText(step11Resbean.getHan17());
            etHan18.setText(step11Resbean.getHan18());
            etHan19.setText(step11Resbean.getHan19());
            etHan20.setText(step11Resbean.getHan20());
            etHan21.setText(step11Resbean.getHan21());
            etHan22.setText(step11Resbean.getHan22());
            etHan23.setText(step11Resbean.getHan23());
            etHan24.setText(step11Resbean.getHan24());
            etHan25.setText(step11Resbean.getHan25());
            etHan26.setText(step11Resbean.getHan26());
            etHan27.setText(step11Resbean.getHan27());
            etHan28.setText(step11Resbean.getHan28());
            etHan29.setText(step11Resbean.getHan29());
            etHan30.setText(step11Resbean.getHan30());

            etBuHan1.setText(step11Resbean.getBuHan1());
            etBuHan2.setText(step11Resbean.getBuHan1());
            etBuHan3.setText(step11Resbean.getBuHan1());
            etBuHan4.setText(step11Resbean.getBuHan1());
            etBuHan5.setText(step11Resbean.getBuHan1());
            etBuHan6.setText(step11Resbean.getBuHan1());
            etBuHan7.setText(step11Resbean.getBuHan1());
            etBuHan8.setText(step11Resbean.getBuHan1());
            etBuHan9.setText(step11Resbean.getBuHan1());
            etBuHan10.setText(step11Resbean.getBuHan1());
            etBuHan11.setText(step11Resbean.getBuHan1());
            etBuHan12.setText(step11Resbean.getBuHan1());
            etBuHan13.setText(step11Resbean.getBuHan1());
            etBuHan14.setText(step11Resbean.getBuHan1());
            etBuHan15.setText(step11Resbean.getBuHan1());
            etBuHan16.setText(step11Resbean.getBuHan1());
            etBuHan17.setText(step11Resbean.getBuHan1());
            etBuHan18.setText(step11Resbean.getBuHan1());
            etBuHan19.setText(step11Resbean.getBuHan1());
            etBuHan20.setText(step11Resbean.getBuHan20());
            etBuHan21.setText(step11Resbean.getBuHan21());
            etBuHan22.setText(step11Resbean.getBuHan22());
            etBuHan23.setText(step11Resbean.getBuHan23());
            etBuHan24.setText(step11Resbean.getBuHan24());
            etBuHan25.setText(step11Resbean.getBuHan25());
            etBuHan26.setText(step11Resbean.getBuHan26());
            etBuHan27.setText(step11Resbean.getBuHan27());
            etBuHan28.setText(step11Resbean.getBuHan28());
            etBuHan29.setText(step11Resbean.getBuHan29());
            etBuHan30.setText(step11Resbean.getBuHan30());

            tvMin.setText(step11Resbean.getMin());
            tvMax.setText(step11Resbean.getMax());
            tvPercent.setText(step11Resbean.getPercent());

            etHan1.setEnabled(false);
            etHan2.setEnabled(false);
            etHan3.setEnabled(false);
            etHan4.setEnabled(false);
            etHan5.setEnabled(false);
            etHan6.setEnabled(false);
            etHan7.setEnabled(false);
            etHan8.setEnabled(false);
            etHan9.setEnabled(false);
            etHan10.setEnabled(false);
            etHan11.setEnabled(false);
            etHan12.setEnabled(false);
            etHan13.setEnabled(false);
            etHan14.setEnabled(false);
            etHan15.setEnabled(false);
            etHan16.setEnabled(false);
            etHan17.setEnabled(false);
            etHan18.setEnabled(false);
            etHan19.setEnabled(false);
            etHan20.setEnabled(false);
            etHan21.setEnabled(false);
            etHan22.setEnabled(false);
            etHan23.setEnabled(false);
            etHan24.setEnabled(false);
            etHan25.setEnabled(false);
            etHan26.setEnabled(false);
            etHan27.setEnabled(false);
            etHan28.setEnabled(false);
            etHan29.setEnabled(false);
            etHan30.setEnabled(false);

            etBuHan1.setEnabled(false);
            etBuHan2.setEnabled(false);
            etBuHan3.setEnabled(false);
            etBuHan4.setEnabled(false);
            etBuHan5.setEnabled(false);
            etBuHan6.setEnabled(false);
            etBuHan7.setEnabled(false);
            etBuHan8.setEnabled(false);
            etBuHan9.setEnabled(false);
            etBuHan10.setEnabled(false);
            etBuHan11.setEnabled(false);
            etBuHan12.setEnabled(false);
            etBuHan13.setEnabled(false);
            etBuHan14.setEnabled(false);
            etBuHan15.setEnabled(false);
            etBuHan16.setEnabled(false);
            etBuHan17.setEnabled(false);
            etBuHan18.setEnabled(false);
            etBuHan19.setEnabled(false);
            etBuHan20.setEnabled(false);
            etBuHan21.setEnabled(false);
            etBuHan22.setEnabled(false);
            etBuHan23.setEnabled(false);
            etBuHan24.setEnabled(false);
            etBuHan25.setEnabled(false);
            etBuHan26.setEnabled(false);
            etBuHan27.setEnabled(false);
            etBuHan28.setEnabled(false);
            etBuHan29.setEnabled(false);
            etBuHan30.setEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Double> valueList = new ArrayList<>();
    private static final int RESULT_OK = 1;
    private static final int RESULT_NG = 2;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                saveDataAndJump2Next();
                break;
            case R.id.cbOk:
                cbOk.setBackgroundResource(R.drawable.cb_checked);
                cbNG.setBackgroundResource(R.drawable.cb_unchecked);
                step11Resbean.setOkOrNG(RESULT_OK);
                break;
            case R.id.cbNG:
                cbOk.setBackgroundResource(R.drawable.cb_unchecked);
                cbNG.setBackgroundResource(R.drawable.cb_checked);
                step11Resbean.setOkOrNG(RESULT_NG);
                break;
        }
    }

    private void saveDataAndJump2Next() {
        if (Constants.isNeedTest && !isHistory) {
            try {
                setDate2Resbean();
                boolean isLegal = presenter.checkDataIsLegal(step11Resbean);
                calculateValue();

                if (!isLegal) {
                    return;
                }
                step11Resbean.setStep11Id(MainApplication.thisTimeId);
                step11Resbean.setCurrentStepIsChecked(true);
                Step11ResbeanDao step11ResbeanDao = MainApplication.daoSession.getStep11ResbeanDao();
                step11ResbeanDao.insert(step11Resbean);
                TestUtil.getAllDbDatas();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //最后将含料头的值的集合清空
                valueList.clear();
            }
        }
        ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
    }

    private void setDate2Resbean() {
        step11Resbean.setHan1(etHan1.getText().toString());
        step11Resbean.setHan2(etHan2.getText().toString());
        step11Resbean.setHan3(etHan3.getText().toString());
        step11Resbean.setHan4(etHan4.getText().toString());
        step11Resbean.setHan5(etHan5.getText().toString());
        step11Resbean.setHan6(etHan6.getText().toString());
        step11Resbean.setHan7(etHan7.getText().toString());
        step11Resbean.setHan8(etHan8.getText().toString());
        step11Resbean.setHan9(etHan9.getText().toString());
        step11Resbean.setHan10(etHan10.getText().toString());
        step11Resbean.setHan11(etHan11.getText().toString());
        step11Resbean.setHan12(etHan12.getText().toString());
        step11Resbean.setHan13(etHan13.getText().toString());
        step11Resbean.setHan14(etHan14.getText().toString());
        step11Resbean.setHan15(etHan15.getText().toString());
        step11Resbean.setHan16(etHan16.getText().toString());
        step11Resbean.setHan17(etHan17.getText().toString());
        step11Resbean.setHan18(etHan18.getText().toString());
        step11Resbean.setHan19(etHan19.getText().toString());
        step11Resbean.setHan20(etHan20.getText().toString());
        step11Resbean.setHan21(etHan21.getText().toString());
        step11Resbean.setHan22(etHan22.getText().toString());
        step11Resbean.setHan23(etHan23.getText().toString());
        step11Resbean.setHan24(etHan24.getText().toString());
        step11Resbean.setHan25(etHan25.getText().toString());
        step11Resbean.setHan26(etHan26.getText().toString());
        step11Resbean.setHan27(etHan27.getText().toString());
        step11Resbean.setHan28(etHan28.getText().toString());
        step11Resbean.setHan29(etHan29.getText().toString());
        step11Resbean.setHan30(etHan30.getText().toString());

        step11Resbean.setBuHan1(etBuHan1.getText().toString());
        step11Resbean.setBuHan2(etBuHan2.getText().toString());
        step11Resbean.setBuHan3(etBuHan3.getText().toString());
        step11Resbean.setBuHan4(etBuHan4.getText().toString());
        step11Resbean.setBuHan5(etBuHan5.getText().toString());
        step11Resbean.setBuHan6(etBuHan6.getText().toString());
        step11Resbean.setBuHan7(etBuHan7.getText().toString());
        step11Resbean.setBuHan8(etBuHan8.getText().toString());
        step11Resbean.setBuHan9(etBuHan9.getText().toString());
        step11Resbean.setBuHan10(etBuHan10.getText().toString());
        step11Resbean.setBuHan11(etBuHan11.getText().toString());
        step11Resbean.setBuHan12(etBuHan12.getText().toString());
        step11Resbean.setBuHan13(etBuHan13.getText().toString());
        step11Resbean.setBuHan14(etBuHan14.getText().toString());
        step11Resbean.setBuHan15(etBuHan15.getText().toString());
        step11Resbean.setBuHan16(etBuHan16.getText().toString());
        step11Resbean.setBuHan17(etBuHan17.getText().toString());
        step11Resbean.setBuHan18(etBuHan18.getText().toString());
        step11Resbean.setBuHan19(etBuHan19.getText().toString());
        step11Resbean.setBuHan20(etBuHan20.getText().toString());
        step11Resbean.setBuHan21(etBuHan21.getText().toString());
        step11Resbean.setBuHan22(etBuHan22.getText().toString());
        step11Resbean.setBuHan23(etBuHan23.getText().toString());
        step11Resbean.setBuHan24(etBuHan24.getText().toString());
        step11Resbean.setBuHan25(etBuHan25.getText().toString());
        step11Resbean.setBuHan26(etBuHan26.getText().toString());
        step11Resbean.setBuHan27(etBuHan27.getText().toString());
        step11Resbean.setBuHan28(etBuHan28.getText().toString());
        step11Resbean.setBuHan29(etBuHan29.getText().toString());
        step11Resbean.setBuHan30(etBuHan30.getText().toString());
    }
}
