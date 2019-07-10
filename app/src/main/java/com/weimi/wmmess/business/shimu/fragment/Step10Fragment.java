package com.weimi.wmmess.business.shimu.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.adapter.step10.YaLiTestAdapter;
import com.weimi.wmmess.business.shimu.bean.step10.Step10Resbean;
import com.weimi.wmmess.business.shimu.bean.step10.Step10ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step10.YaLiTestResbean;
import com.weimi.wmmess.business.shimu.bean.step10.YaLiTestResbeanDao;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.business.shimu.presenter.Step10Presenter;
import com.weimi.wmmess.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 冷却时间分析
 */
public class Step10Fragment extends BaseFragment<Step10Presenter> implements View.OnClickListener {
    private static final int stepPosition = 9;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnNextStep, btnAddRecord;
    private Step10Presenter presenter;
    private Step10Resbean step10Resbean;
    private EditText etConfirmTime;
    private RecyclerView rcvList;
    private YaLiTestAdapter adapter;

    public static Step10Fragment newInstance(long historyId, boolean isHistory) {
        Step10Fragment fragment = new Step10Fragment();
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
        return R.layout.fragment_step10;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        btnNextStep = rootView.findViewById(R.id.btnNextStep);
        btnAddRecord = rootView.findViewById(R.id.btnAddRecord);
        etConfirmTime = rootView.findViewById(R.id.etConfirmTime);

        rcvList = rootView.findViewById(R.id.rcvList);
        etConfirmTime.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        btnNextStep.setOnClickListener(this);
        btnAddRecord.setOnClickListener(this);
    }

    @Override
    public void initData() {
        presenter = new Step10Presenter(this);
        step10Resbean = new Step10Resbean();
        initRcv();

        if (historyId != 0 && isHistory == true) {
            showHistoryData();
        }
    }

    private void showHistoryData() {
        try {
            Step10Resbean step10Resbean = MainApplication.daoSession.getStep10ResbeanDao().load(historyId);
            etConfirmTime.setText(step10Resbean.getConfirmTime());
            etConfirmTime.setEnabled(false);
            List<YaLiTestResbean> yaLiTestResbeanList = MainApplication.daoSession.getYaLiTestResbeanDao().loadAll();
            if (yaLiTestResbeans != null && yaLiTestResbeans.size() > 0) {
                yaLiTestResbeans.clear();
            }
            for (YaLiTestResbean yaLiTestResbean : yaLiTestResbeanList) {
                if (yaLiTestResbean.getYaLiTestResbeanId().equals(historyId)) {
                    yaLiTestResbeans.add(yaLiTestResbean);
                }
            }
            rcvList.setEnabled(false);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<YaLiTestResbean> yaLiTestResbeans = new ArrayList<>();

    private void initRcv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvList.setLayoutManager(layoutManager);

        adapter = new YaLiTestAdapter(getActivity(), yaLiTestResbeans);
        rcvList.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                if (Constants.isNeedTest && !isHistory) {
                    if (StringUtils.isEmpty(etConfirmTime.getText().toString())) {
                        ToastUtils.showShort("请输入确定冷却时间");
                        return;
                    }
                    try {
                        step10Resbean.setStep10Id(MainApplication.thisTimeId);
                        step10Resbean.setCurrentStepIsChecked(true);
                        Step10ResbeanDao step10ResbeanDao = MainApplication.daoSession.getStep10ResbeanDao();
                        YaLiTestResbeanDao yaLiTestResbeanDao = MainApplication.daoSession.getYaLiTestResbeanDao();
                        yaLiTestResbeanDao.insertInTx(yaLiTestResbeans);

                        step10ResbeanDao.insert(step10Resbean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        TestUtil.getAllDbDatas();
                    }
                }
                ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
                break;
            case R.id.btnAddRecord:
                addYaLiTestRecord();
                break;
        }
    }

    private void addYaLiTestRecord() {
        if (yaLiTestResbeans.size() == 0) {
            yaLiTestResbeans.add(new YaLiTestResbean(null, MainApplication.thisTimeId, "", "", "", ""));
            adapter.notifyDataSetChanged();
        } else {
            if (!presenter.checkDataIsHaveNull(yaLiTestResbeans)) {
                yaLiTestResbeans.add(new YaLiTestResbean(null, MainApplication.thisTimeId, "", "", "", ""));
                adapter.notifyDataSetChanged();
            }
        }
    }
}
