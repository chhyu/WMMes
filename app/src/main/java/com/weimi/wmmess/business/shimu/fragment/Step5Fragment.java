package com.weimi.wmmess.business.shimu.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.bean.step5.Step5Resbean;
import com.weimi.wmmess.business.shimu.bean.step5.Step5ResbeanDao;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.business.shimu.presenter.Step5Presenter;
import com.weimi.wmmess.utils.TestUtil;

/**
 * 开合模&顶出设定
 */
public class Step5Fragment extends BaseFragment<Step5Presenter> implements View.OnClickListener {
    private static final int stepPosition = 4;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int CHECK_OK = 1;
    private static final int CHECK_NG = 2;
    private Button btnNextStep;
    private EditText etKMsheDingShijian, etKMShiJiShiJian, etDCSheDingShijian, etDCshijishijian, etDTSheDingShijian, etDTShiJiShiJian, etHMSheDingSJ, etHMShiJiSJ;
    private CheckBox cbCheck1OK, cbCheck2OK, cbCheck3OK, cbCheck4OK, cbCheck5OK, cbCheck6OK, cbCheck7OK, cbCheck8OK, cbCheck9OK, cbCheck10OK, cbCheck11OK;
    private CheckBox cbCheck1NG, cbCheck2NG, cbCheck3NG, cbCheck4NG, cbCheck5NG, cbCheck6NG, cbCheck7NG, cbCheck8NG, cbCheck9NG, cbCheck10NG, cbCheck11NG;
    private EditText etLengQueSj, etXunHuanZhouQi;
    private Step5Resbean step5Resbean;
    private Step5Presenter presenter;

    public static Step5Fragment newInstance(long historyId, boolean isHistory) {
        Step5Fragment fragment = new Step5Fragment();
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
        return R.layout.fragment_step5;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        btnNextStep = rootView.findViewById(R.id.btnNextStep);

        etKMsheDingShijian = rootView.findViewById(R.id.etKMsheDingShijian);
        etKMShiJiShiJian = rootView.findViewById(R.id.etKMShiJiShiJian);
        etDCSheDingShijian = rootView.findViewById(R.id.etDCSheDingShijian);
        etDCshijishijian = rootView.findViewById(R.id.etDCshijishijian);
        etDTSheDingShijian = rootView.findViewById(R.id.etDTSheDingShijian);
        etDTShiJiShiJian = rootView.findViewById(R.id.etDTShiJiShiJian);
        etHMSheDingSJ = rootView.findViewById(R.id.etHMSheDingSJ);
        etHMShiJiSJ = rootView.findViewById(R.id.etHMShiJiSJ);
        etLengQueSj = rootView.findViewById(R.id.etLengQueSj);
        etXunHuanZhouQi = rootView.findViewById(R.id.etXunHuanZhouQi);

        etKMsheDingShijian.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etKMShiJiShiJian.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDCSheDingShijian.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDCshijishijian.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDTSheDingShijian.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDTShiJiShiJian.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHMSheDingSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHMShiJiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLengQueSj.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etXunHuanZhouQi.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        LinearLayout llCheck1OK = rootView.findViewById(R.id.llCheck1OK);
        LinearLayout llCheck2OK = rootView.findViewById(R.id.llCheck2OK);
        LinearLayout llCheck3OK = rootView.findViewById(R.id.llCheck3OK);
        LinearLayout llCheck4OK = rootView.findViewById(R.id.llCheck4OK);
        LinearLayout llCheck5OK = rootView.findViewById(R.id.llCheck5OK);
        LinearLayout llCheck6OK = rootView.findViewById(R.id.llCheck6OK);
        LinearLayout llCheck7OK = rootView.findViewById(R.id.llCheck7OK);
        LinearLayout llCheck8OK = rootView.findViewById(R.id.llCheck8OK);
        LinearLayout llCheck9OK = rootView.findViewById(R.id.llCheck9OK);
        LinearLayout llCheck10OK = rootView.findViewById(R.id.llCheck10OK);
        LinearLayout llCheck11OK = rootView.findViewById(R.id.llCheck11OK);

        llCheck1OK.setOnClickListener(this);
        llCheck2OK.setOnClickListener(this);
        llCheck3OK.setOnClickListener(this);
        llCheck4OK.setOnClickListener(this);
        llCheck5OK.setOnClickListener(this);
        llCheck6OK.setOnClickListener(this);
        llCheck7OK.setOnClickListener(this);
        llCheck8OK.setOnClickListener(this);
        llCheck9OK.setOnClickListener(this);
        llCheck10OK.setOnClickListener(this);
        llCheck11OK.setOnClickListener(this);

        LinearLayout llCheck1NG = rootView.findViewById(R.id.llCheck1NG);
        LinearLayout llCheck2NG = rootView.findViewById(R.id.llCheck2NG);
        LinearLayout llCheck3NG = rootView.findViewById(R.id.llCheck3NG);
        LinearLayout llCheck4NG = rootView.findViewById(R.id.llCheck4NG);
        LinearLayout llCheck5NG = rootView.findViewById(R.id.llCheck5NG);
        LinearLayout llCheck6NG = rootView.findViewById(R.id.llCheck6NG);
        LinearLayout llCheck7NG = rootView.findViewById(R.id.llCheck7NG);
        LinearLayout llCheck8NG = rootView.findViewById(R.id.llCheck8NG);
        LinearLayout llCheck9NG = rootView.findViewById(R.id.llCheck9NG);
        LinearLayout llCheck10NG = rootView.findViewById(R.id.llCheck10NG);
        LinearLayout llCheck11NG = rootView.findViewById(R.id.llCheck11NG);

        llCheck1NG.setOnClickListener(this);
        llCheck2NG.setOnClickListener(this);
        llCheck3NG.setOnClickListener(this);
        llCheck4NG.setOnClickListener(this);
        llCheck5NG.setOnClickListener(this);
        llCheck6NG.setOnClickListener(this);
        llCheck7NG.setOnClickListener(this);
        llCheck8NG.setOnClickListener(this);
        llCheck9NG.setOnClickListener(this);
        llCheck10NG.setOnClickListener(this);
        llCheck11NG.setOnClickListener(this);

        cbCheck1OK = rootView.findViewById(R.id.cbCheck1OK);
        cbCheck2OK = rootView.findViewById(R.id.cbCheck2OK);
        cbCheck3OK = rootView.findViewById(R.id.cbCheck3OK);
        cbCheck4OK = rootView.findViewById(R.id.cbCheck4OK);
        cbCheck5OK = rootView.findViewById(R.id.cbCheck5OK);
        cbCheck6OK = rootView.findViewById(R.id.cbCheck6OK);
        cbCheck7OK = rootView.findViewById(R.id.cbCheck7OK);
        cbCheck8OK = rootView.findViewById(R.id.cbCheck8OK);
        cbCheck9OK = rootView.findViewById(R.id.cbCheck9OK);
        cbCheck10OK = rootView.findViewById(R.id.cbCheck10OK);
        cbCheck11OK = rootView.findViewById(R.id.cbCheck11OK);

        cbCheck1NG = rootView.findViewById(R.id.cbCheck1NG);
        cbCheck2NG = rootView.findViewById(R.id.cbCheck2NG);
        cbCheck3NG = rootView.findViewById(R.id.cbCheck3NG);
        cbCheck4NG = rootView.findViewById(R.id.cbCheck4NG);
        cbCheck5NG = rootView.findViewById(R.id.cbCheck5NG);
        cbCheck6NG = rootView.findViewById(R.id.cbCheck6NG);
        cbCheck7NG = rootView.findViewById(R.id.cbCheck7NG);
        cbCheck8NG = rootView.findViewById(R.id.cbCheck8NG);
        cbCheck9NG = rootView.findViewById(R.id.cbCheck9NG);
        cbCheck10NG = rootView.findViewById(R.id.cbCheck10NG);
        cbCheck11NG = rootView.findViewById(R.id.cbCheck11NG);

        btnNextStep.setOnClickListener(this);
    }

    @Override
    public void initData() {
        presenter = new Step5Presenter(this);
        step5Resbean = new Step5Resbean();
        if (historyId != 0 && isHistory == true) {
            showHistoryData();
        }
    }

    private void showHistoryData() {
        try {
            Step5Resbean step5Resbean = MainApplication.daoSession.getStep5ResbeanDao().load(historyId);
            if (step5Resbean == null) {
                return;
            }
            etKMsheDingShijian.setText(step5Resbean.getKaiMoSheDingSj());
            etKMShiJiShiJian.setText(step5Resbean.getKaiMoShiJiSj());

            etDCSheDingShijian.setText(step5Resbean.getDingChuSheDingSj());
            etDCshijishijian.setText(step5Resbean.getDingChuShiJiSj());

            etDTSheDingShijian.setText(step5Resbean.getDingTuiSheDingSj());
            etDTShiJiShiJian.setText(step5Resbean.getDingTuiShiJiSj());

            etHMSheDingSJ.setText(step5Resbean.getHeMoSheDingSj());
            etHMShiJiSJ.setText(step5Resbean.getHeMoShiJiSj());

            etLengQueSj.setText(step5Resbean.getLengQueSj());
            etXunHuanZhouQi.setText(step5Resbean.getXunHuanZhouQi());

            setHistoryCheckState(step5Resbean.getOne(), cbCheck1OK, cbCheck1NG);
            setHistoryCheckState(step5Resbean.getTwo(), cbCheck2OK, cbCheck2NG);
            setHistoryCheckState(step5Resbean.getThree(), cbCheck3OK, cbCheck3NG);
            setHistoryCheckState(step5Resbean.getFour(), cbCheck4OK, cbCheck4NG);
            setHistoryCheckState(step5Resbean.getFive(), cbCheck5OK, cbCheck5NG);
            setHistoryCheckState(step5Resbean.getSix(), cbCheck6OK, cbCheck6NG);
            setHistoryCheckState(step5Resbean.getSeven(), cbCheck7OK, cbCheck7NG);
            setHistoryCheckState(step5Resbean.getEight(), cbCheck8OK, cbCheck8NG);
            setHistoryCheckState(step5Resbean.getNine(), cbCheck9OK, cbCheck9NG);
            setHistoryCheckState(step5Resbean.getTen(), cbCheck10OK, cbCheck10NG);
            setHistoryCheckState(step5Resbean.getEleven(), cbCheck11OK, cbCheck11NG);

            etKMsheDingShijian.setEnabled(false);
            etKMShiJiShiJian.setEnabled(false);
            etDCSheDingShijian.setEnabled(false);
            etDCshijishijian.setEnabled(false);
            etDTSheDingShijian.setEnabled(false);
            etDTShiJiShiJian.setEnabled(false);
            etHMSheDingSJ.setEnabled(false);
            etHMShiJiSJ.setEnabled(false);
            etLengQueSj.setEnabled(false);
            etXunHuanZhouQi.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setHistoryCheckState(int one, CheckBox cbOK, CheckBox cbNG) {
        if (one == CHECK_OK) {
            cbOK.setBackgroundResource(R.drawable.cb_checked);
            cbNG.setBackgroundResource(R.drawable.cb_unchecked);
        } else if (one == CHECK_NG) {
            cbOK.setBackgroundResource(R.drawable.cb_unchecked);
            cbNG.setBackgroundResource(R.drawable.cb_checked);
        }
        cbOK.setEnabled(false);
        cbNG.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                setDataAndJump2Next();
                break;
            case R.id.llCheck1OK:
                step5Resbean.setOne(CHECK_OK);
                setCheckedOk(cbCheck1OK, cbCheck1NG);
                break;
            case R.id.llCheck2OK:
                step5Resbean.setTwo(CHECK_OK);
                setCheckedOk(cbCheck2OK, cbCheck2NG);
                break;
            case R.id.llCheck3OK:
                step5Resbean.setThree(CHECK_OK);
                setCheckedOk(cbCheck3OK, cbCheck3NG);
                break;
            case R.id.llCheck4OK:
                step5Resbean.setFour(CHECK_OK);
                setCheckedOk(cbCheck4OK, cbCheck4NG);
                break;
            case R.id.llCheck5OK:
                step5Resbean.setFive(CHECK_OK);
                setCheckedOk(cbCheck5OK, cbCheck5NG);
                break;
            case R.id.llCheck6OK:
                step5Resbean.setSix(CHECK_OK);
                setCheckedOk(cbCheck6OK, cbCheck6NG);
                break;
            case R.id.llCheck7OK:
                step5Resbean.setSeven(CHECK_OK);
                setCheckedOk(cbCheck7OK, cbCheck7NG);
                break;
            case R.id.llCheck8OK:
                step5Resbean.setEight(CHECK_OK);
                setCheckedOk(cbCheck8OK, cbCheck8NG);
                break;
            case R.id.llCheck9OK:
                step5Resbean.setNine(CHECK_OK);
                setCheckedOk(cbCheck9OK, cbCheck9NG);
                break;
            case R.id.llCheck10OK:
                step5Resbean.setTen(CHECK_OK);
                setCheckedOk(cbCheck10OK, cbCheck10NG);
                break;
            case R.id.llCheck11OK:
                step5Resbean.setEleven(CHECK_OK);
                setCheckedOk(cbCheck11OK, cbCheck11NG);
                break;

            case R.id.llCheck1NG:
                step5Resbean.setOne(CHECK_NG);
                setCheckedNG(cbCheck1OK, cbCheck1NG);
                break;
            case R.id.llCheck2NG:
                step5Resbean.setTwo(CHECK_NG);
                setCheckedNG(cbCheck2OK, cbCheck2NG);
                break;
            case R.id.llCheck3NG:
                step5Resbean.setThree(CHECK_NG);
                setCheckedNG(cbCheck3OK, cbCheck3NG);
                break;
            case R.id.llCheck4NG:
                step5Resbean.setFour(CHECK_NG);
                setCheckedNG(cbCheck4OK, cbCheck4NG);
                break;
            case R.id.llCheck5NG:
                step5Resbean.setFive(CHECK_NG);
                setCheckedNG(cbCheck5OK, cbCheck5NG);
                break;
            case R.id.llCheck6NG:
                step5Resbean.setSix(CHECK_NG);
                setCheckedNG(cbCheck6OK, cbCheck6NG);
                break;
            case R.id.llCheck7NG:
                step5Resbean.setSeven(CHECK_NG);
                setCheckedNG(cbCheck7OK, cbCheck7NG);
                break;
            case R.id.llCheck8NG:
                step5Resbean.setEight(CHECK_NG);
                setCheckedNG(cbCheck8OK, cbCheck8NG);
                break;
            case R.id.llCheck9NG:
                step5Resbean.setNine(CHECK_NG);
                setCheckedNG(cbCheck9OK, cbCheck9NG);
                break;
            case R.id.llCheck10NG:
                step5Resbean.setTen(CHECK_NG);
                setCheckedNG(cbCheck10OK, cbCheck10NG);
                break;
            case R.id.llCheck11NG:
                step5Resbean.setEleven(CHECK_NG);
                setCheckedNG(cbCheck11OK, cbCheck11NG);
                break;
            default:
                break;
        }
    }

    private void setDataAndJump2Next() {
        if (Constants.isNeedTest && !isHistory) {
            setDate2Resbean();
            boolean isHave = presenter.checkDateIsHaveNull(step5Resbean);
            if (isHave) {
                return;
            }
            boolean isExist = presenter.checkIsHaveNG(step5Resbean);
            if (isExist) {
                ToastUtils.showShort("存在NG选项，请解决后再进行下一次试模");
                return;
            }
            try {
                step5Resbean.setStep5Id(MainApplication.thisTimeId);
                step5Resbean.setCurrentStepIsChecked(true);

                Step5ResbeanDao step5ResbeanDao = MainApplication.daoSession.getStep5ResbeanDao();
                step5ResbeanDao.insert(step5Resbean);
                TestUtil.getAllDbDatas();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
    }

    private void setDate2Resbean() {
        step5Resbean.setKaiMoSheDingSj(etKMsheDingShijian.getText().toString());
        step5Resbean.setKaiMoShiJiSj(etKMShiJiShiJian.getText().toString());
        step5Resbean.setDingChuSheDingSj(etDCSheDingShijian.getText().toString());
        step5Resbean.setDingChuShiJiSj(etDCshijishijian.getText().toString());
        step5Resbean.setDingTuiSheDingSj(etDTSheDingShijian.getText().toString());
        step5Resbean.setDingTuiShiJiSj(etDTShiJiShiJian.getText().toString());
        step5Resbean.setHeMoSheDingSj(etHMSheDingSJ.getText().toString());
        step5Resbean.setHeMoShiJiSj(etHMShiJiSJ.getText().toString());
        step5Resbean.setLengQueSj(etLengQueSj.getText().toString());
        step5Resbean.setXunHuanZhouQi(etXunHuanZhouQi.getText().toString());
    }

    /**
     * 设置选择状态为OK
     *
     * @param cbCheckedOk
     * @param cbCheckedNG
     */
    private void setCheckedOk(CheckBox cbCheckedOk, CheckBox cbCheckedNG) {
        cbCheckedOk.setBackgroundResource(R.drawable.cb_checked);
        cbCheckedNG.setBackgroundResource(R.drawable.cb_unchecked);
    }

    /**
     * 设置选择状态为NG
     *
     * @param cbCheckedOk
     * @param cbCheckedNG
     */
    private void setCheckedNG(CheckBox cbCheckedOk, CheckBox cbCheckedNG) {
        cbCheckedOk.setBackgroundResource(R.drawable.cb_unchecked);
        cbCheckedNG.setBackgroundResource(R.drawable.cb_checked);
    }
}
