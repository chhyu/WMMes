package com.weimi.wmmess.business.shimu.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.bean.step2.Step2Resbean;
import com.weimi.wmmess.business.shimu.bean.step2.Step2ResbeanDao;
import com.weimi.wmmess.business.shimu.presenter.Step2Presenter;
import com.weimi.wmmess.utils.TestUtil;

import java.math.BigDecimal;

import static com.weimi.wmmess.MainApplication.thisTimeId;

/**
 * 第二步：模具检查&安装模具
 */
public class Step2Fragment extends BaseFragment<Step2Presenter> implements View.OnClickListener {
    public static final int stepPosition = 1;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnNextStep;
    private Step2Resbean step2Resbean;
    private static final int CHECK_OK = 1;
    private static final int CHECK_NG = 2;
    private static final int CHECK_NA = 3;
    private CheckBox cbCheckAOK, cbCheckBOK, cbCheckCOK, cbCheckDOK, cbCheckEOK, cbCheckFOK, cbCheckGOK, cbCheckHOK, cbCheckIOK, cbCheckJOK, cbCheckKOK, cbCheckLOK, cbCheckMOK, cbCheckNOK, cbCheckOOK;
    private CheckBox cbCheckANG, cbCheckBNG, cbCheckCNG, cbCheckDNG, cbCheckENG, cbCheckFNG, cbCheckGNG, cbCheckHNG, cbCheckING, cbCheckJNG, cbCheckKNG, cbCheckLNG, cbCheckMNG, cbCheckNNG, cbCheckONG;
    private CheckBox cbCheckANA, cbCheckBNA, cbCheckCNA, cbCheckDNA, cbCheckENA, cbCheckFNA, cbCheckGNA, cbCheckHNA, cbCheckINA, cbCheckJNA, cbCheckKNA, cbCheckLNA, cbCheckMNA, cbCheckNNA, cbCheckONA;
    private Step2Presenter presenter;

    public static Step2Fragment newInstance(long historyId, boolean isHistory) {
        Step2Fragment fragment = new Step2Fragment();
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
        return R.layout.fragment_step2;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View view) {
        btnNextStep = view.findViewById(R.id.btnNextStep);
        LinearLayout llCheckAOK = view.findViewById(R.id.llCheckAOK);
        LinearLayout llCheckBOK = view.findViewById(R.id.llCheckBOK);
        LinearLayout llCheckCOK = view.findViewById(R.id.llCheckCOK);
        LinearLayout llCheckDOK = view.findViewById(R.id.llCheckDOK);
        LinearLayout llCheckEOK = view.findViewById(R.id.llCheckEOK);
        LinearLayout llCheckFOK = view.findViewById(R.id.llCheckFOK);
        LinearLayout llCheckGOK = view.findViewById(R.id.llCheckGOK);
        LinearLayout llCheckHOK = view.findViewById(R.id.llCheckHOK);
        LinearLayout llCheckIOK = view.findViewById(R.id.llCheckIOK);
        LinearLayout llCheckJOK = view.findViewById(R.id.llCheckJOK);
        LinearLayout llCheckKOK = view.findViewById(R.id.llCheckKOK);
        LinearLayout llCheckLOK = view.findViewById(R.id.llCheckLOK);
        LinearLayout llCheckMOK = view.findViewById(R.id.llCheckMOK);
        LinearLayout llCheckNOK = view.findViewById(R.id.llCheckNOK);
        LinearLayout llCheckOOK = view.findViewById(R.id.llCheckOOK);

        llCheckAOK.setOnClickListener(this);
        llCheckBOK.setOnClickListener(this);
        llCheckCOK.setOnClickListener(this);
        llCheckDOK.setOnClickListener(this);
        llCheckEOK.setOnClickListener(this);
        llCheckFOK.setOnClickListener(this);
        llCheckGOK.setOnClickListener(this);
        llCheckHOK.setOnClickListener(this);
        llCheckIOK.setOnClickListener(this);
        llCheckJOK.setOnClickListener(this);
        llCheckKOK.setOnClickListener(this);
        llCheckLOK.setOnClickListener(this);
        llCheckMOK.setOnClickListener(this);
        llCheckNOK.setOnClickListener(this);
        llCheckOOK.setOnClickListener(this);

        LinearLayout llCheckANG = view.findViewById(R.id.llCheckANG);
        LinearLayout llCheckBNG = view.findViewById(R.id.llCheckBNG);
        LinearLayout llCheckCNG = view.findViewById(R.id.llCheckCNG);
        LinearLayout llCheckDNG = view.findViewById(R.id.llCheckDNG);
        LinearLayout llCheckENG = view.findViewById(R.id.llCheckENG);
        LinearLayout llCheckFNG = view.findViewById(R.id.llCheckFNG);
        LinearLayout llCheckGNG = view.findViewById(R.id.llCheckGNG);
        LinearLayout llCheckHNG = view.findViewById(R.id.llCheckHNG);
        LinearLayout llCheckING = view.findViewById(R.id.llCheckING);
        LinearLayout llCheckJNG = view.findViewById(R.id.llCheckJNG);
        LinearLayout llCheckKNG = view.findViewById(R.id.llCheckKNG);
        LinearLayout llCheckLNG = view.findViewById(R.id.llCheckLNG);
        LinearLayout llCheckMNG = view.findViewById(R.id.llCheckMNG);
        LinearLayout llCheckNNG = view.findViewById(R.id.llCheckNNG);
        LinearLayout llCheckONG = view.findViewById(R.id.llCheckONG);

        llCheckANG.setOnClickListener(this);
        llCheckBNG.setOnClickListener(this);
        llCheckCNG.setOnClickListener(this);
        llCheckDNG.setOnClickListener(this);
        llCheckENG.setOnClickListener(this);
        llCheckFNG.setOnClickListener(this);
        llCheckGNG.setOnClickListener(this);
        llCheckHNG.setOnClickListener(this);
        llCheckING.setOnClickListener(this);
        llCheckJNG.setOnClickListener(this);
        llCheckKNG.setOnClickListener(this);
        llCheckLNG.setOnClickListener(this);
        llCheckMNG.setOnClickListener(this);
        llCheckNNG.setOnClickListener(this);
        llCheckONG.setOnClickListener(this);

        LinearLayout llCheckANA = view.findViewById(R.id.llCheckANA);
        LinearLayout llCheckBNA = view.findViewById(R.id.llCheckBNA);
        LinearLayout llCheckCNA = view.findViewById(R.id.llCheckCNA);
        LinearLayout llCheckDNA = view.findViewById(R.id.llCheckDNA);
        LinearLayout llCheckENA = view.findViewById(R.id.llCheckENA);
        LinearLayout llCheckFNA = view.findViewById(R.id.llCheckFNA);
        LinearLayout llCheckGNA = view.findViewById(R.id.llCheckGNA);
        LinearLayout llCheckHNA = view.findViewById(R.id.llCheckHNA);
        LinearLayout llCheckINA = view.findViewById(R.id.llCheckINA);
        LinearLayout llCheckJNA = view.findViewById(R.id.llCheckJNA);
        LinearLayout llCheckKNA = view.findViewById(R.id.llCheckKNA);
        LinearLayout llCheckLNA = view.findViewById(R.id.llCheckLNA);
        LinearLayout llCheckMNA = view.findViewById(R.id.llCheckMNA);
        LinearLayout llCheckNNA = view.findViewById(R.id.llCheckNNA);
        LinearLayout llCheckONA = view.findViewById(R.id.llCheckONA);

        llCheckANA.setOnClickListener(this);
        llCheckBNA.setOnClickListener(this);
        llCheckCNA.setOnClickListener(this);
        llCheckDNA.setOnClickListener(this);
        llCheckENA.setOnClickListener(this);
        llCheckFNA.setOnClickListener(this);
        llCheckGNA.setOnClickListener(this);
        llCheckHNA.setOnClickListener(this);
        llCheckINA.setOnClickListener(this);
        llCheckJNA.setOnClickListener(this);
        llCheckKNA.setOnClickListener(this);
        llCheckLNA.setOnClickListener(this);
        llCheckMNA.setOnClickListener(this);
        llCheckNNA.setOnClickListener(this);
        llCheckONA.setOnClickListener(this);

        cbCheckAOK = view.findViewById(R.id.cbCheckAOK);
        cbCheckBOK = view.findViewById(R.id.cbCheckBOK);
        cbCheckCOK = view.findViewById(R.id.cbCheckCOK);
        cbCheckDOK = view.findViewById(R.id.cbCheckDOK);
        cbCheckEOK = view.findViewById(R.id.cbCheckEOK);
        cbCheckFOK = view.findViewById(R.id.cbCheckFOK);
        cbCheckGOK = view.findViewById(R.id.cbCheckGOK);
        cbCheckHOK = view.findViewById(R.id.cbCheckHOK);
        cbCheckIOK = view.findViewById(R.id.cbCheckIOK);
        cbCheckJOK = view.findViewById(R.id.cbCheckJOK);
        cbCheckKOK = view.findViewById(R.id.cbCheckKOK);
        cbCheckLOK = view.findViewById(R.id.cbCheckLOK);
        cbCheckMOK = view.findViewById(R.id.cbCheckMOK);
        cbCheckNOK = view.findViewById(R.id.cbCheckNOK);
        cbCheckOOK = view.findViewById(R.id.cbCheckOOK);

        cbCheckANG = view.findViewById(R.id.cbCheckANG);
        cbCheckBNG = view.findViewById(R.id.cbCheckBNG);
        cbCheckCNG = view.findViewById(R.id.cbCheckCNG);
        cbCheckDNG = view.findViewById(R.id.cbCheckDNG);
        cbCheckENG = view.findViewById(R.id.cbCheckENG);
        cbCheckFNG = view.findViewById(R.id.cbCheckFNG);
        cbCheckGNG = view.findViewById(R.id.cbCheckGNG);
        cbCheckHNG = view.findViewById(R.id.cbCheckHNG);
        cbCheckING = view.findViewById(R.id.cbCheckING);
        cbCheckJNG = view.findViewById(R.id.cbCheckJNG);
        cbCheckKNG = view.findViewById(R.id.cbCheckKNG);
        cbCheckLNG = view.findViewById(R.id.cbCheckLNG);
        cbCheckMNG = view.findViewById(R.id.cbCheckMNG);
        cbCheckNNG = view.findViewById(R.id.cbCheckNNG);
        cbCheckONG = view.findViewById(R.id.cbCheckONG);

        cbCheckANA = view.findViewById(R.id.cbCheckANA);
        cbCheckBNA = view.findViewById(R.id.cbCheckBNA);
        cbCheckCNA = view.findViewById(R.id.cbCheckCNA);
        cbCheckDNA = view.findViewById(R.id.cbCheckDNA);
        cbCheckENA = view.findViewById(R.id.cbCheckENA);
        cbCheckFNA = view.findViewById(R.id.cbCheckFNA);
        cbCheckGNA = view.findViewById(R.id.cbCheckGNA);
        cbCheckHNA = view.findViewById(R.id.cbCheckHNA);
        cbCheckINA = view.findViewById(R.id.cbCheckINA);
        cbCheckJNA = view.findViewById(R.id.cbCheckJNA);
        cbCheckKNA = view.findViewById(R.id.cbCheckKNA);
        cbCheckLNA = view.findViewById(R.id.cbCheckLNA);
        cbCheckMNA = view.findViewById(R.id.cbCheckMNA);
        cbCheckNNA = view.findViewById(R.id.cbCheckNNA);
        cbCheckONA = view.findViewById(R.id.cbCheckONA);
        btnNextStep.setOnClickListener(this);
    }

    @Override
    public void initData() {
        presenter = new Step2Presenter(this);
        step2Resbean = new Step2Resbean();

        if (historyId != 0 && isHistory == true) {
            showHistoryDate();
        }
    }

    private void showHistoryDate() {
        try {
            Step2Resbean step2Resbean = MainApplication.daoSession.getStep2ResbeanDao().load(historyId);
            showHistoryCheckedState(step2Resbean.getA(), cbCheckAOK, cbCheckANG, cbCheckANA);
            showHistoryCheckedState(step2Resbean.getB(), cbCheckBOK, cbCheckBNG, cbCheckBNA);
            showHistoryCheckedState(step2Resbean.getC(), cbCheckCOK, cbCheckCNG, cbCheckCNA);
            showHistoryCheckedState(step2Resbean.getD(), cbCheckDOK, cbCheckDNG, cbCheckDNA);
            showHistoryCheckedState(step2Resbean.getE(), cbCheckEOK, cbCheckENG, cbCheckENA);
            showHistoryCheckedState(step2Resbean.getF(), cbCheckFOK, cbCheckFNG, cbCheckFNA);
            showHistoryCheckedState(step2Resbean.getG(), cbCheckGOK, cbCheckGNG, cbCheckGNA);
            showHistoryCheckedState(step2Resbean.getH(), cbCheckHOK, cbCheckHNG, cbCheckHNA);
            showHistoryCheckedState(step2Resbean.getI(), cbCheckIOK, cbCheckING, cbCheckINA);
            showHistoryCheckedState(step2Resbean.getJ(), cbCheckJOK, cbCheckJNG, cbCheckJNA);
            showHistoryCheckedState(step2Resbean.getK(), cbCheckKOK, cbCheckKNG, cbCheckKNA);
            showHistoryCheckedState(step2Resbean.getL(), cbCheckLOK, cbCheckLNG, cbCheckLNA);
            showHistoryCheckedState(step2Resbean.getM(), cbCheckMOK, cbCheckMNG, cbCheckMNA);
            showHistoryCheckedState(step2Resbean.getN(), cbCheckNOK, cbCheckNNG, cbCheckNNA);
            showHistoryCheckedState(step2Resbean.getO(), cbCheckOOK, cbCheckONG, cbCheckONA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showHistoryCheckedState(int a, CheckBox cbOK, CheckBox cbNG, CheckBox cbNA) {
        if (a == CHECK_OK) {
            cbOK.setBackgroundResource(R.drawable.cb_checked);
            cbNG.setBackgroundResource(R.drawable.cb_unchecked);
            cbNA.setBackgroundResource(R.drawable.cb_unchecked);
        } else if (a == CHECK_NG) {
            cbOK.setBackgroundResource(R.drawable.cb_unchecked);
            cbNG.setBackgroundResource(R.drawable.cb_checked);
            cbNA.setBackgroundResource(R.drawable.cb_unchecked);
        } else if (a == CHECK_NA) {
            cbOK.setBackgroundResource(R.drawable.cb_unchecked);
            cbNG.setBackgroundResource(R.drawable.cb_unchecked);
            cbNA.setBackgroundResource(R.drawable.cb_checked);
        }
        cbOK.setEnabled(false);
        cbNG.setEnabled(false);
        cbNA.setEnabled(false);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                checkDataAndJump2Next();
                break;
            case R.id.llCheckAOK:
                step2Resbean.setA(CHECK_OK);
                setCheckedOk(cbCheckAOK, cbCheckANG, cbCheckANA);
                break;
            case R.id.llCheckBOK:
                step2Resbean.setB(CHECK_OK);
                setCheckedOk(cbCheckBOK, cbCheckBNG, cbCheckBNA);
                break;
            case R.id.llCheckCOK:
                step2Resbean.setC(CHECK_OK);
                setCheckedOk(cbCheckCOK, cbCheckCNG, cbCheckCNA);
                break;
            case R.id.llCheckDOK:
                step2Resbean.setD(CHECK_OK);
                setCheckedOk(cbCheckDOK, cbCheckDNG, cbCheckDNA);
                break;
            case R.id.llCheckEOK:
                step2Resbean.setE(CHECK_OK);
                setCheckedOk(cbCheckEOK, cbCheckENG, cbCheckENA);
                break;
            case R.id.llCheckFOK:
                step2Resbean.setF(CHECK_OK);
                setCheckedOk(cbCheckFOK, cbCheckFNG, cbCheckFNA);
                break;
            case R.id.llCheckGOK:
                step2Resbean.setG(CHECK_OK);
                setCheckedOk(cbCheckGOK, cbCheckGNG, cbCheckGNA);
                break;
            case R.id.llCheckHOK:
                step2Resbean.setH(CHECK_OK);
                setCheckedOk(cbCheckHOK, cbCheckHNG, cbCheckHNA);
                break;
            case R.id.llCheckIOK:
                step2Resbean.setI(CHECK_OK);
                setCheckedOk(cbCheckIOK, cbCheckING, cbCheckINA);
                break;
            case R.id.llCheckJOK:
                step2Resbean.setJ(CHECK_OK);
                setCheckedOk(cbCheckJOK, cbCheckJNG, cbCheckJNA);
                break;
            case R.id.llCheckKOK:
                step2Resbean.setK(CHECK_OK);
                setCheckedOk(cbCheckKOK, cbCheckKNG, cbCheckKNA);
                break;
            case R.id.llCheckLOK:
                step2Resbean.setL(CHECK_OK);
                setCheckedOk(cbCheckLOK, cbCheckLNG, cbCheckLNA);
                break;
            case R.id.llCheckMOK:
                step2Resbean.setM(CHECK_OK);
                setCheckedOk(cbCheckMOK, cbCheckMNG, cbCheckMNA);
                break;
            case R.id.llCheckNOK:
                step2Resbean.setN(CHECK_OK);
                setCheckedOk(cbCheckNOK, cbCheckNNG, cbCheckNNA);
                break;
            case R.id.llCheckOOK:
                step2Resbean.setO(CHECK_OK);
                setCheckedOk(cbCheckOOK, cbCheckONG, cbCheckONA);
                break;
            case R.id.llCheckANG:
                step2Resbean.setA(CHECK_NG);
                setCheckedNG(cbCheckAOK, cbCheckANG, cbCheckANA);
                break;
            case R.id.llCheckBNG:
                step2Resbean.setB(CHECK_NG);
                setCheckedNG(cbCheckBOK, cbCheckBNG, cbCheckBNA);
                break;
            case R.id.llCheckCNG:
                step2Resbean.setC(CHECK_NG);
                setCheckedNG(cbCheckCOK, cbCheckCNG, cbCheckCNA);
                break;
            case R.id.llCheckDNG:
                step2Resbean.setD(CHECK_NG);
                setCheckedNG(cbCheckDOK, cbCheckDNG, cbCheckDNA);
                break;
            case R.id.llCheckENG:
                step2Resbean.setE(CHECK_NG);
                setCheckedNG(cbCheckEOK, cbCheckENG, cbCheckENA);
                break;
            case R.id.llCheckFNG:
                step2Resbean.setF(CHECK_NG);
                setCheckedNG(cbCheckFOK, cbCheckFNG, cbCheckFNA);
                break;
            case R.id.llCheckGNG:
                step2Resbean.setG(CHECK_NG);
                setCheckedNG(cbCheckGOK, cbCheckGNG, cbCheckGNA);
                break;
            case R.id.llCheckHNG:
                step2Resbean.setH(CHECK_NG);
                setCheckedNG(cbCheckHOK, cbCheckHNG, cbCheckHNA);
                break;
            case R.id.llCheckING:
                step2Resbean.setI(CHECK_NG);
                setCheckedNG(cbCheckIOK, cbCheckING, cbCheckINA);
                break;
            case R.id.llCheckJNG:
                step2Resbean.setJ(CHECK_NG);
                setCheckedNG(cbCheckJOK, cbCheckJNG, cbCheckJNA);
                break;
            case R.id.llCheckKNG:
                step2Resbean.setK(CHECK_NG);
                setCheckedNG(cbCheckKOK, cbCheckKNG, cbCheckKNA);
                break;
            case R.id.llCheckLNG:
                step2Resbean.setL(CHECK_NG);
                setCheckedNG(cbCheckLOK, cbCheckLNG, cbCheckLNA);
                break;
            case R.id.llCheckMNG:
                step2Resbean.setM(CHECK_NG);
                setCheckedNG(cbCheckMOK, cbCheckMNG, cbCheckMNA);
                break;
            case R.id.llCheckNNG:
                step2Resbean.setN(CHECK_NG);
                setCheckedNG(cbCheckNOK, cbCheckNNG, cbCheckNNA);
                break;
            case R.id.llCheckONG:
                step2Resbean.setO(CHECK_NG);
                setCheckedNG(cbCheckOOK, cbCheckONG, cbCheckONA);
                break;
            case R.id.llCheckANA:
                step2Resbean.setA(CHECK_NA);
                setCheckedNA(cbCheckAOK, cbCheckANG, cbCheckANA);
                break;
            case R.id.llCheckBNA:
                step2Resbean.setB(CHECK_NA);
                setCheckedNA(cbCheckBOK, cbCheckBNG, cbCheckBNA);
                break;
            case R.id.llCheckCNA:
                step2Resbean.setC(CHECK_NA);
                setCheckedNA(cbCheckCOK, cbCheckCNG, cbCheckCNA);
                break;
            case R.id.llCheckDNA:
                step2Resbean.setD(CHECK_NA);
                setCheckedNA(cbCheckDOK, cbCheckDNG, cbCheckDNA);
                break;
            case R.id.llCheckENA:
                step2Resbean.setE(CHECK_NA);
                setCheckedNA(cbCheckEOK, cbCheckENG, cbCheckENA);
                break;
            case R.id.llCheckFNA:
                step2Resbean.setF(CHECK_NA);
                setCheckedNA(cbCheckFOK, cbCheckFNG, cbCheckFNA);
                break;
            case R.id.llCheckGNA:
                step2Resbean.setG(CHECK_NA);
                setCheckedNA(cbCheckGOK, cbCheckGNG, cbCheckGNA);
                break;
            case R.id.llCheckHNA:
                step2Resbean.setH(CHECK_NA);
                setCheckedNA(cbCheckHOK, cbCheckHNG, cbCheckHNA);
                break;
            case R.id.llCheckINA:
                step2Resbean.setI(CHECK_NA);
                setCheckedNA(cbCheckIOK, cbCheckING, cbCheckINA);
                break;
            case R.id.llCheckJNA:
                step2Resbean.setJ(CHECK_NA);
                setCheckedNA(cbCheckJOK, cbCheckJNG, cbCheckJNA);
                break;
            case R.id.llCheckKNA:
                step2Resbean.setK(CHECK_NA);
                setCheckedNA(cbCheckKOK, cbCheckKNG, cbCheckKNA);
                break;
            case R.id.llCheckLNA:
                step2Resbean.setL(CHECK_NA);
                setCheckedNA(cbCheckLOK, cbCheckLNG, cbCheckLNA);
                break;
            case R.id.llCheckMNA:
                step2Resbean.setM(CHECK_NA);
                setCheckedNA(cbCheckMOK, cbCheckMNG, cbCheckMNA);
                break;
            case R.id.llCheckNNA:
                step2Resbean.setN(CHECK_NA);
                setCheckedNA(cbCheckNOK, cbCheckNNG, cbCheckNNA);
                break;
            case R.id.llCheckONA:
                step2Resbean.setO(CHECK_NA);
                setCheckedNA(cbCheckOOK, cbCheckONG, cbCheckONA);
                break;
            default:
                break;
        }
    }

    private void checkDataAndJump2Next() {
        if (Constants.isNeedTest && !isHistory) {
            //step1:检查是否有未设置选项
            boolean isHaveNotSet = presenter.checkIsHaveNotSet(step2Resbean);
            if (isHaveNotSet) {
                ToastUtils.showShort("请确保所有检查均已完成");
                return;
            }
            //step2:检查是否有NG
            boolean isHaveNg = presenter.checkIsHaveNg(step2Resbean);
            if (isHaveNg) {
                ToastUtils.showShort("存在NG选项，请立即通知项目和钳工");
                return;
            }
            //step3:存库
            try {
                step2Resbean.setStep2Id(thisTimeId);
                step2Resbean.setCurrentStepIsChecked(true);
                Step2ResbeanDao step2ResbeanDao = MainApplication.daoSession.getStep2ResbeanDao();
                step2ResbeanDao.insert(step2Resbean);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                TestUtil.getAllDbDatas();
            }
        }
        ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
    }

    /**
     * 设置选择状态为NA
     *
     * @param cbOK
     * @param cbNG
     * @param cbNA
     */
    private void setCheckedNA(CheckBox cbOK, CheckBox cbNG, CheckBox cbNA) {
        cbOK.setBackgroundResource(R.drawable.cb_unchecked);
        cbNG.setBackgroundResource(R.drawable.cb_unchecked);
        cbNA.setBackgroundResource(R.drawable.cb_checked);
    }

    /**
     * 设置选择状态为NG
     *
     * @param cbOK
     * @param cbNG
     * @param cbNA
     */
    private void setCheckedNG(CheckBox cbOK, CheckBox cbNG, CheckBox cbNA) {
        cbOK.setBackgroundResource(R.drawable.cb_unchecked);
        cbNG.setBackgroundResource(R.drawable.cb_checked);
        cbNA.setBackgroundResource(R.drawable.cb_unchecked);
    }

    /**
     * 设置状态为OK
     *
     * @param cbOK
     * @param cbNG
     * @param cbNA
     */
    private void setCheckedOk(CheckBox cbOK, CheckBox cbNG, CheckBox cbNA) {
        cbOK.setBackgroundResource(R.drawable.cb_checked);
        cbNG.setBackgroundResource(R.drawable.cb_unchecked);
        cbNA.setBackgroundResource(R.drawable.cb_unchecked);
    }
}
