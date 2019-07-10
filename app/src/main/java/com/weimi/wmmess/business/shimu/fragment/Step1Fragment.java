package com.weimi.wmmess.business.shimu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.bean.step1.Step1Resbean;
import com.weimi.wmmess.business.shimu.bean.step1.Step1ResbeanDao;
import com.weimi.wmmess.business.shimu.presenter.Step1Presenter;
import com.weimi.wmmess.utils.TestUtil;

import static com.weimi.wmmess.MainApplication.thisTimeId;

/**
 * 第一步：试模前准备
 */
public class Step1Fragment extends BaseFragment<Step1Presenter> implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int stepPosition = 0;
    private Button btnNextStep;
    private CheckBox cbStep1One, cbStep1Two, cbStep1Three, cbStep1Four, cbStep1Five, cbStep1Six;
    private Step1Resbean step1Resbean;
    private Step1Presenter step1Presenter;

    public static Step1Fragment newInstance(long historyId, boolean isHistory) {
        Step1Fragment fragment = new Step1Fragment();
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
        return R.layout.fragment_step1;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View view) {
        btnNextStep = view.findViewById(R.id.btnNextStep);
        btnNextStep.setOnClickListener(this);
        cbStep1One = view.findViewById(R.id.cbStep1One);
        cbStep1Two = view.findViewById(R.id.cbStep1Two);
        cbStep1Three = view.findViewById(R.id.cbStep1Three);
        cbStep1Four = view.findViewById(R.id.cbStep1Four);
        cbStep1Five = view.findViewById(R.id.cbStep1Five);
        cbStep1Six = view.findViewById(R.id.cbStep1Six);

        cbStep1One.setOnCheckedChangeListener(this);
        cbStep1Two.setOnCheckedChangeListener(this);
        cbStep1Three.setOnCheckedChangeListener(this);
        cbStep1Four.setOnCheckedChangeListener(this);
        cbStep1Five.setOnCheckedChangeListener(this);
        cbStep1Six.setOnCheckedChangeListener(this);
    }

    @Override
    public void initData() {
        step1Presenter = new Step1Presenter(this);
        step1Resbean = new Step1Resbean();
        if (historyId != 0 && isHistory == true) {
            setDate2Widget();
        }
    }

    private void setDate2Widget() {
        try {
            Step1Resbean step1Resbean = MainApplication.daoSession.getStep1ResbeanDao().load(historyId);
            this.step1Resbean = step1Resbean;
            setCheckState(cbStep1One, step1Resbean.getOneIsChecked());
            setCheckState(cbStep1Two, step1Resbean.getTwoIsChecked());
            setCheckState(cbStep1Three, step1Resbean.getThreeIsChecked());
            setCheckState(cbStep1Four, step1Resbean.getFourIsChecked());
            setCheckState(cbStep1Five, step1Resbean.getFiveIsChecked());
            setCheckState(cbStep1Six, step1Resbean.getSixIsChecked());

            cbStep1One.setEnabled(false);
            cbStep1Two.setEnabled(false);
            cbStep1Three.setEnabled(false);
            cbStep1Four.setEnabled(false);
            cbStep1Five.setEnabled(false);
            cbStep1Six.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cbStep1One:
                step1Resbean.setOneIsChecked(isChecked);
                setCheckState(cbStep1One, isChecked);
                break;
            case R.id.cbStep1Two:
                step1Resbean.setTwoIsChecked(isChecked);
                setCheckState(cbStep1Two, isChecked);
                break;
            case R.id.cbStep1Three:
                step1Resbean.setThreeIsChecked(isChecked);
                setCheckState(cbStep1Three, isChecked);
                break;
            case R.id.cbStep1Four:
                step1Resbean.setFourIsChecked(isChecked);
                setCheckState(cbStep1Four, isChecked);
                break;
            case R.id.cbStep1Five:
                step1Resbean.setFiveIsChecked(isChecked);
                setCheckState(cbStep1Five, isChecked);
                break;
            case R.id.cbStep1Six:
                step1Resbean.setSixIsChecked(isChecked);
                setCheckState(cbStep1Six, isChecked);
                break;
            default:
                break;
        }
    }

    private void setCheckState(CheckBox checkBox, boolean isChecked) {
        if (isChecked) {
            checkBox.setBackgroundResource(R.drawable.cb_checked);
        } else {
            checkBox.setBackgroundResource(R.drawable.cb_unchecked);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                if (Constants.isNeedTest) {
                    boolean isHave = step1Presenter.checkIsHaveFalse(step1Resbean);
                    if (isHave) {
                        Toast.makeText(getActivity(), "请确保所有准备工作已完毕", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        try {
                            step1Resbean.setStep1ResbeanId(thisTimeId);
                            step1Resbean.setCurrentStepIsChecked(true);
                            Step1ResbeanDao step1ResbeanDao = MainApplication.daoSession.getStep1ResbeanDao();
                            step1ResbeanDao.insert(step1Resbean);
                            TestUtil.getAllDbDatas();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
                break;
        }
    }
}
