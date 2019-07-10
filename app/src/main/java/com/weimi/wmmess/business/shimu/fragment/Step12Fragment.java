package com.weimi.wmmess.business.shimu.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.bean.step12.Step12ResBean;
import com.weimi.wmmess.business.shimu.bean.step12.Step12ResBeanDao;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.business.shimu.presenter.Step12Presenter;
import com.weimi.wmmess.utils.TestUtil;

/**
 * 决定螺杆&模具温度公差
 */
public class Step12Fragment extends BaseFragment<Step12Presenter> implements View.OnClickListener {
    public static final int stepPosition = 11;
    private EditText etAddZero, etAddOne, etAddTwo, etAddThree, etAddFour, etAddFive, etAddHopperOne, etAddHopperTwo, etAddOutside, etAddSize, etAddZhiLiangOne, etAddZhiLiangTwo;
    private EditText etSubtractZero, etSubtractOne, etSubtractTwo, etSubtractThree, etSubtractFour, etSubtractFive, etSubtractHopperOne, etSubtractHopperTwo, etSubtractOutSide, etSubtractSize, etSubtractZhiLiangOne, etSubtractZhiLiangTwo;
    private EditText etAddWendu, etMuJuWaiGuan, etMuJuChiCun, etAddZhiLiang, etSubtractWenDu, etSubtractWaiGuan, etSubtractChiCun, etSubtractZhiLiang;
    private RecyclerView rcvStepList;
    private Button btnNextStep;
    private Step12Presenter presenter;
    private Step12ResBean step12ResBean;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static Step12Fragment newInstance(long historyId, boolean isHistory) {
        Step12Fragment fragment = new Step12Fragment();
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
        return R.layout.fragment_step12;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        btnNextStep = rootView.findViewById(R.id.btnNextStep);

        etAddZero = rootView.findViewById(R.id.etAddZero);
        etAddOne = rootView.findViewById(R.id.etAddOne);
        etAddTwo = rootView.findViewById(R.id.etAddTwo);
        etAddThree = rootView.findViewById(R.id.etAddThree);
        etAddFour = rootView.findViewById(R.id.etAddFour);
        etAddFive = rootView.findViewById(R.id.etAddFive);
        etAddHopperOne = rootView.findViewById(R.id.etAddHopperOne);
        etAddHopperTwo = rootView.findViewById(R.id.etAddHopperTwo);
        etAddOutside = rootView.findViewById(R.id.etAddOutside);
        etAddSize = rootView.findViewById(R.id.etAddSize);
        etAddZhiLiangOne = rootView.findViewById(R.id.etAddZhiLiangOne);
        etAddZhiLiangTwo = rootView.findViewById(R.id.etAddZhiLiangTwo);

        etAddZero.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etAddOne.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etAddTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etAddThree.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etAddFour.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etAddFive.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etAddHopperOne.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etAddHopperTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etAddZhiLiangOne.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etAddZhiLiangTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSubtractZero = rootView.findViewById(R.id.etSubtractZero);
        etSubtractOne = rootView.findViewById(R.id.etSubtractOne);
        etSubtractTwo = rootView.findViewById(R.id.etSubtractTwo);
        etSubtractThree = rootView.findViewById(R.id.etSubtractThree);
        etSubtractFour = rootView.findViewById(R.id.etSubtractFour);
        etSubtractFive = rootView.findViewById(R.id.etSubtractFive);
        etSubtractHopperOne = rootView.findViewById(R.id.etSubtractHopperOne);
        etSubtractHopperTwo = rootView.findViewById(R.id.etSubtractHopperTwo);
        etSubtractOutSide = rootView.findViewById(R.id.etSubtractOutSide);
        etSubtractSize = rootView.findViewById(R.id.etSubtractSize);
        etSubtractZhiLiangOne = rootView.findViewById(R.id.etSubtractZhiLiangOne);
        etSubtractZhiLiangTwo = rootView.findViewById(R.id.etSubtractZhiLiangTwo);

        etSubtractZero.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSubtractOne.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSubtractTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSubtractThree.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSubtractFour.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSubtractFive.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSubtractHopperOne.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSubtractHopperTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSubtractZhiLiangOne.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSubtractZhiLiangTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etAddWendu = rootView.findViewById(R.id.etAddWendu);
        etMuJuWaiGuan = rootView.findViewById(R.id.etMuJuWaiGuan);
        etMuJuChiCun = rootView.findViewById(R.id.etMuJuChiCun);
        etAddZhiLiang = rootView.findViewById(R.id.etAddZhiLiang);
        etSubtractWenDu = rootView.findViewById(R.id.etSubtractWenDu);
        etSubtractWaiGuan = rootView.findViewById(R.id.etSubtractWaiGuan);
        etSubtractChiCun = rootView.findViewById(R.id.etSubtractChiCun);
        etSubtractZhiLiang = rootView.findViewById(R.id.etSubtractZhiLiang);

        etAddWendu.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etAddZhiLiang.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSubtractWenDu.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSubtractZhiLiang.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        btnNextStep.setOnClickListener(this);
    }

    @Override
    public void initData() {
        presenter = new Step12Presenter(this);
        step12ResBean = new Step12ResBean();
        if (historyId != 0 && isHistory == true) {
            showHistoryData();
        }
    }

    private void showHistoryData() {
        try {
            Step12ResBean step12ResBean = MainApplication.daoSession.getStep12ResBeanDao().load(historyId);
            etAddZero.setText(step12ResBean.getAddZero());
            etAddOne.setText(step12ResBean.getAddOne());
            etAddTwo.setText(step12ResBean.getAddTwo());
            etAddThree.setText(step12ResBean.getAddThree());
            etAddFour.setText(step12ResBean.getAddFour());
            etAddFive.setText(step12ResBean.getAddFive());
            etAddHopperOne.setText(step12ResBean.getAddHopperOne());
            etAddHopperTwo.setText(step12ResBean.getAddHopperTwo());
            etAddOutside.setText(step12ResBean.getAddOutSide());
            etAddSize.setText(step12ResBean.getAddSize());
            etAddZhiLiangOne.setText(step12ResBean.getAddZhiLiangOne());
            etAddZhiLiangTwo.setText(step12ResBean.getAddZhiLiangTwo());

            etAddZero.setEnabled(false);
            etAddOne.setEnabled(false);
            etAddTwo.setEnabled(false);
            etAddThree.setEnabled(false);
            etAddFour.setEnabled(false);
            etAddFive.setEnabled(false);
            etAddHopperOne.setEnabled(false);
            etAddHopperTwo.setEnabled(false);
            etAddOutside.setEnabled(false);
            etAddSize.setEnabled(false);
            etAddZhiLiangOne.setEnabled(false);
            etAddZhiLiangTwo.setEnabled(false);

            etSubtractZero.setText(step12ResBean.getSubtractZero());
            etSubtractOne.setText(step12ResBean.getSubtractOne());
            etSubtractTwo.setText(step12ResBean.getSubtractTwo());
            etSubtractThree.setText(step12ResBean.getSubtractThree());
            etSubtractFour.setText(step12ResBean.getSubtractFour());
            etSubtractFive.setText(step12ResBean.getSubtractFive());
            etSubtractHopperOne.setText(step12ResBean.getSubtractHopperOne());
            etSubtractHopperTwo.setText(step12ResBean.getSubtractHopperTwo());
            etSubtractOutSide.setText(step12ResBean.getSubtractOutside());
            etSubtractSize.setText(step12ResBean.getSubtractSize());
            etSubtractZhiLiangOne.setText(step12ResBean.getSubtractZhiLiangOne());
            etSubtractZhiLiangTwo.setText(step12ResBean.getSubtractZhiLiangTwo());

            etSubtractZero.setEnabled(false);
            etSubtractOne.setEnabled(false);
            etSubtractTwo.setEnabled(false);
            etSubtractThree.setEnabled(false);
            etSubtractFour.setEnabled(false);
            etSubtractFive.setEnabled(false);
            etSubtractHopperOne.setEnabled(false);
            etSubtractHopperTwo.setEnabled(false);
            etSubtractOutSide.setEnabled(false);
            etSubtractSize.setEnabled(false);
            etSubtractZhiLiangOne.setEnabled(false);
            etSubtractZhiLiangTwo.setEnabled(false);


            etAddWendu.setText(step12ResBean.getAddWendu());
            etMuJuWaiGuan.setText(step12ResBean.getMuJuAddWaiGuan());
            etMuJuChiCun.setText(step12ResBean.getMuJuAddChiCun());
            etAddZhiLiang.setText(step12ResBean.getMuJuAddZhiLiang());

            etSubtractWenDu.setText(step12ResBean.getSubtractWenDu());
            etSubtractWaiGuan.setText(step12ResBean.getMuJuSubtractWaiGuan());
            etSubtractChiCun.setText(step12ResBean.getMuJuSubtractChiCun());
            etSubtractZhiLiang.setText(step12ResBean.getMuJuSubtractZhiLiang());

            etAddWendu.setEnabled(false);
            etMuJuWaiGuan.setEnabled(false);
            etMuJuChiCun.setEnabled(false);
            etAddZhiLiang.setEnabled(false);
            etSubtractWenDu.setEnabled(false);
            etSubtractWaiGuan.setEnabled(false);
            etSubtractChiCun.setEnabled(false);
            etSubtractZhiLiang.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                if (Constants.isNeedTest && !isHistory) {
                    setData2Resbean();
                    boolean isLegal = presenter.checkDataIsLegal(step12ResBean);
                    if (!isLegal) {
                        return;
                    }
                    try {
                        step12ResBean.setStep12Id(MainApplication.thisTimeId);
                        step12ResBean.setCurrentStepIsChecked(true);
                        Step12ResBeanDao step12ResBeanDao = MainApplication.daoSession.getStep12ResBeanDao();
                        step12ResBeanDao.insert(step12ResBean);
                        TestUtil.getAllDbDatas();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
                break;
        }
    }

    private void setData2Resbean() {
        step12ResBean.setAddZero(etAddZero.getText().toString());
        step12ResBean.setAddOne(etAddOne.getText().toString());
        step12ResBean.setAddTwo(etAddTwo.getText().toString());
        step12ResBean.setAddThree(etAddThree.getText().toString());
        step12ResBean.setAddFour(etAddFour.getText().toString());
        step12ResBean.setAddFive(etAddFive.getText().toString());
        step12ResBean.setAddHopperOne(etAddHopperOne.getText().toString());
        step12ResBean.setAddHopperTwo(etAddHopperTwo.getText().toString());
        step12ResBean.setAddOutSide(etAddOutside.getText().toString());
        step12ResBean.setAddSize(etAddSize.getText().toString());
        step12ResBean.setAddZhiLiangOne(etAddZhiLiangOne.getText().toString());
        step12ResBean.setAddZhiLiangTwo(etAddZhiLiangTwo.getText().toString());


        step12ResBean.setSubtractZero(etSubtractZero.getText().toString());
        step12ResBean.setSubtractOne(etSubtractOne.getText().toString());
        step12ResBean.setSubtractTwo(etSubtractTwo.getText().toString());
        step12ResBean.setSubtractThree(etSubtractThree.getText().toString());
        step12ResBean.setSubtractFour(etSubtractFour.getText().toString());
        step12ResBean.setSubtractFive(etSubtractFive.getText().toString());
        step12ResBean.setSubtractHopperOne(etSubtractHopperOne.getText().toString());
        step12ResBean.setSubtractHopperTwo(etSubtractHopperTwo.getText().toString());
        step12ResBean.setSubtractOutside(etSubtractOutSide.getText().toString());
        step12ResBean.setSubtractSize(etSubtractSize.getText().toString());
        step12ResBean.setSubtractZhiLiangOne(etSubtractZhiLiangOne.getText().toString());
        step12ResBean.setSubtractZhiLiangTwo(etSubtractZhiLiangTwo.getText().toString());

        step12ResBean.setAddWendu(etAddWendu.getText().toString());
        step12ResBean.setMuJuAddWaiGuan(etMuJuWaiGuan.getText().toString());
        step12ResBean.setMuJuAddChiCun(etMuJuChiCun.getText().toString());
        step12ResBean.setMuJuAddZhiLiang(etAddZhiLiang.getText().toString());

        step12ResBean.setSubtractWenDu(etSubtractWenDu.getText().toString());
        step12ResBean.setMuJuSubtractWaiGuan(etSubtractWaiGuan.getText().toString());
        step12ResBean.setMuJuSubtractChiCun(etSubtractChiCun.getText().toString());
        step12ResBean.setMuJuSubtractZhiLiang(etSubtractZhiLiang.getText().toString());
    }
}
