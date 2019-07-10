package com.weimi.wmmess.business.shimu.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.bean.step6.Step6Resbean;
import com.weimi.wmmess.business.shimu.bean.step6.Step6ResbeanDao;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.business.shimu.presenter.Step6Presenter;
import com.weimi.wmmess.utils.TestUtil;

/**
 * 注塑压力损失测试
 */
public class Step6Fragment extends BaseFragment<Step6Presenter> implements View.OnClickListener {
    private static final int stepPosition = 5;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnNextStep;
    private EditText etShijiOne, etShijiTwo, etShijiThree, etShijiFour, etShijiFive, etSheDingZhuSuYaLi;
    private TextView tvOKorNGOne, tvOKorNGTwo, tvOKorNGThree, tvOKorNGFour, tvOKorNGFive;
    private Step6Presenter presenter;
    private Step6Resbean step6Resbean;
    private final int MAX_PRESSURE = 2400;//注塑机最大压力
    private final int CHECK_RESULT_OK = 1;
    private final int CHECK_RESULT_NG = 2;
    private final String CHECK_STATE_OK = "OK";
    private final String CHECK_STATE_NG = "NG";

    public static Step6Fragment newInstance(long historyId, boolean isHistory) {
        Step6Fragment fragment = new Step6Fragment();
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
        return R.layout.fragment_step6;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        btnNextStep = rootView.findViewById(R.id.btnNextStep);
        etSheDingZhuSuYaLi = rootView.findViewById(R.id.etSheDingZhuSuYaLi);
        etShijiOne = rootView.findViewById(R.id.etShijiOne);
        etShijiTwo = rootView.findViewById(R.id.etShijiTwo);
        etShijiThree = rootView.findViewById(R.id.etShijiThree);
        etShijiFour = rootView.findViewById(R.id.etShijiFour);
        etShijiFive = rootView.findViewById(R.id.etShijiFive);

        tvOKorNGOne = rootView.findViewById(R.id.tvOKorNGOne);
        tvOKorNGTwo = rootView.findViewById(R.id.tvOKorNGTwo);
        tvOKorNGThree = rootView.findViewById(R.id.tvOKorNGThree);
        tvOKorNGFour = rootView.findViewById(R.id.tvOKorNGFour);
        tvOKorNGFive = rootView.findViewById(R.id.tvOKorNGFive);

        btnNextStep.setOnClickListener(this);

        etSheDingZhuSuYaLi.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etShijiOne.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etShijiTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etShijiThree.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etShijiFour.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etShijiFive.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
    }

    @Override
    public void initData() {
        presenter = new Step6Presenter(this);
        step6Resbean = new Step6Resbean();

        initInputListener();

        if (historyId != 0 && isHistory == true) {
            showHistoryData();
        }
    }

    private void showHistoryData() {
        try {
            Step6Resbean step6Resbean = MainApplication.daoSession.getStep6ResbeanDao().load(historyId);
            if (step6Resbean == null) {
                return;
            }
            etSheDingZhuSuYaLi.setText(String.valueOf(step6Resbean.getSheDingZhuSuYaLi()));
            etShijiOne.setText(String.valueOf(step6Resbean.getShijiOne()));
            etShijiTwo.setText(String.valueOf(step6Resbean.getShijiTwo()));
            etShijiThree.setText(String.valueOf(step6Resbean.getShijiThree()));
            etShijiFour.setText(String.valueOf(step6Resbean.getShijiFour()));
            etShijiFive.setText(String.valueOf(step6Resbean.getShijiFive()));

            tvOKorNGOne.setText(step6Resbean.getResultOne());
            tvOKorNGTwo.setText(step6Resbean.getResultTwo());
            tvOKorNGThree.setText(step6Resbean.getResultThree());
            tvOKorNGFour.setText(step6Resbean.getResultFour());
            tvOKorNGFive.setText(step6Resbean.getResultFive());

            etSheDingZhuSuYaLi.setEnabled(false);
            etShijiOne.setEnabled(false);
            etShijiTwo.setEnabled(false);
            etShijiThree.setEnabled(false);
            etShijiFour.setEnabled(false);
            etShijiFive.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initInputListener() {
        etShijiOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String shijiOne = etShijiOne.getText().toString();
                if (StringUtils.isEmpty(shijiOne)) {
                    ToastUtils.showShort("请输入不接触模具射出实际压力");
                    return;
                } else {
                    Integer one = Integer.valueOf(shijiOne);
                    step6Resbean.setShijiOne(one);
                    if (one < 20 || one < (MAX_PRESSURE * 0.15)) {
                        step6Resbean.setResultOne(CHECK_RESULT_OK);
                        tvOKorNGOne.setText(CHECK_STATE_OK);
                    } else {
                        step6Resbean.setResultOne(CHECK_RESULT_NG);
                        tvOKorNGOne.setText(CHECK_STATE_NG);
                    }
                }
            }
        });

        etShijiTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String shijiTwo = etShijiTwo.getText().toString();
                if (StringUtils.isEmpty(shijiTwo)) {
                    ToastUtils.showShort("请输入接触模具流道空射实际压力");
                    return;
                } else {
                    Integer two = Integer.valueOf(shijiTwo);
                    step6Resbean.setShijiTwo(two);
                    if (two < 30 || two < (MAX_PRESSURE * 0.2)) {
                        step6Resbean.setResultTwo(CHECK_RESULT_OK);
                        tvOKorNGTwo.setText(CHECK_STATE_OK);
                    } else {
                        step6Resbean.setResultTwo(CHECK_RESULT_NG);
                        tvOKorNGTwo.setText(CHECK_STATE_NG);
                    }
                }
            }
        });

        etShijiThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String shijiThree = etShijiThree.getText().toString();
                if (StringUtils.isEmpty(shijiThree)) {
                    ToastUtils.showShort("请输入产品注塑量到10%实际压力");
                    return;
                } else {
                    Integer three = Integer.valueOf(shijiThree);
                    step6Resbean.setShijiThree(three);
                    if (three < 100 || three < (MAX_PRESSURE * 0.7)) {
                        step6Resbean.setResultThree(CHECK_RESULT_OK);
                        tvOKorNGThree.setText(CHECK_STATE_OK);
                    } else {
                        step6Resbean.setResultThree(CHECK_RESULT_NG);
                        tvOKorNGThree.setText(CHECK_STATE_NG);
                    }
                }
            }
        });

        etShijiFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String shijiFour = etShijiFour.getText().toString();
                if (StringUtils.isEmpty(shijiFour)) {
                    ToastUtils.showShort("请输入保压切换实际压力");
                    return;
                } else {
                    Integer four = Integer.valueOf(shijiFour);
                    step6Resbean.setShijiFour(four);
                    if (four < 100 || four < (MAX_PRESSURE * 0.7)) {
                        step6Resbean.setResultFour(CHECK_RESULT_OK);
                        tvOKorNGFour.setText(CHECK_STATE_OK);
                    } else {
                        step6Resbean.setResultFour(CHECK_RESULT_NG);
                        tvOKorNGFour.setText(CHECK_STATE_NG);
                    }
                }
            }
        });

        etShijiFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String shijiFive = etShijiFive.getText().toString();
                if (StringUtils.isEmpty(shijiFive)) {
                    ToastUtils.showShort("请输入实际最高压力");
                    return;
                } else {
                    Integer five = Integer.valueOf(shijiFive);
                    step6Resbean.setShijiFive(five);
                    if (five < 100 || five < (MAX_PRESSURE * 0.7)) {
                        step6Resbean.setResultFive(CHECK_RESULT_OK);
                        tvOKorNGFive.setText(CHECK_STATE_OK);
                    } else {
                        step6Resbean.setResultFive(CHECK_RESULT_NG);
                        tvOKorNGFive.setText(CHECK_STATE_NG);
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                checkDataAndJump2Next();
                break;
        }
    }

    private void checkDataAndJump2Next() {
        if (Constants.isNeedTest && !isHistory) {
            boolean isPass = checkDataAndSetDatas();
            if (!isPass) {
                return;
            }
            boolean isHave = presenter.checkIsHaveNG(step6Resbean);
            if (isHave) {
                return;
            }
            try {
                step6Resbean.setStep6Id(MainApplication.thisTimeId);
                step6Resbean.setCurrentStepIsChecked(true);
                Step6ResbeanDao step6ResbeanDao = MainApplication.daoSession.getStep6ResbeanDao();
                step6ResbeanDao.insert(step6Resbean);
                TestUtil.getAllDbDatas();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
    }

    private boolean checkDataAndSetDatas() {
        String sheDingZhuSuYaLi = etSheDingZhuSuYaLi.getText().toString();
        if (StringUtils.isEmpty(sheDingZhuSuYaLi)) {
            ToastUtils.showShort("请输入设定注塑压力");
            return false;
        } else {
            step6Resbean.setSheDingZhuSuYaLi(Integer.valueOf(sheDingZhuSuYaLi));
        }
        return true;
    }
}
