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

import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.adapter.step7.Step7ZhuSuAdapter;
import com.weimi.wmmess.business.shimu.bean.step7.Step7Resbean;
import com.weimi.wmmess.business.shimu.bean.step7.Step7ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step7.ZhuSuResbean;
import com.weimi.wmmess.business.shimu.bean.step7.ZhuSuResbeanDao;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.business.shimu.presenter.Step7Presenter;
import com.weimi.wmmess.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 射出时间&压力
 */
public class Step7Fragment extends BaseFragment<Step7Presenter> implements View.OnClickListener {
    private static final int stepPosition = 6;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnNextStep, btnAddRecord;
    private Step7Presenter presenter;
    private RecyclerView rcvZhuSuList;
    private EditText etZuiJiaShijian, etZuiJiaSuDu;
    private Step7Resbean step7Resbean;
    private Step7ZhuSuAdapter adapter;

    public static Step7Fragment newInstance(long historyId, boolean isHistory) {
        Step7Fragment fragment = new Step7Fragment();
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
        return R.layout.fragment_step7;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        btnNextStep = rootView.findViewById(R.id.btnNextStep);
        btnAddRecord = rootView.findViewById(R.id.btnAddRecord);
        rcvZhuSuList = rootView.findViewById(R.id.rcvZhuSuList);

        etZuiJiaShijian = rootView.findViewById(R.id.etZuiJiaShijian);
        etZuiJiaSuDu = rootView.findViewById(R.id.etZuiJiaSuDu);
        btnNextStep.setOnClickListener(this);
        btnAddRecord.setOnClickListener(this);

        etZuiJiaSuDu.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZuiJiaShijian.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
    }

    @Override
    public void initData() {
        presenter = new Step7Presenter(this);
        step7Resbean = new Step7Resbean();
        initRcv();
        if (historyId != 0 && isHistory == true) {
            showHistoryData();
        }
    }

    private void showHistoryData() {
        try {
            Step7Resbean step7Resbean = MainApplication.daoSession.getStep7ResbeanDao().load(historyId);
            etZuiJiaSuDu.setText(step7Resbean.getZuijiaSuDu());
            etZuiJiaShijian.setText(step7Resbean.getZuijiaShijian());

            etZuiJiaSuDu.setEnabled(false);
            etZuiJiaShijian.setEnabled(false);

            List<ZhuSuResbean> zhuSuResbeans = MainApplication.daoSession.getZhuSuResbeanDao().loadAll();
            if (zhuSuResbeanList != null && zhuSuResbeanList.size() > 0) {
                zhuSuResbeanList.clear();
            }
            for (ZhuSuResbean zhuSuResbean : zhuSuResbeans) {
                if (zhuSuResbean.getZhuSuResbeanId().equals(historyId)) {
                    zhuSuResbeanList.add(zhuSuResbean);
                }
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<ZhuSuResbean> zhuSuResbeanList = new ArrayList<>();

    private void initRcv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new Step7ZhuSuAdapter(getActivity(), zhuSuResbeanList);

        rcvZhuSuList.setLayoutManager(layoutManager);
        rcvZhuSuList.setAdapter(adapter);

        adapter.setOnEditCompleteListener(new Step7ZhuSuAdapter.OnEditCompleteListener() {
            @Override
            public void onComplete(String text, int position, int etIndex) {
                setText2Data(text, position, etIndex);
            }
        });
    }

    private void setText2Data(String text, int position, int etIndex) {
        ZhuSuResbean zhuSuResbean = zhuSuResbeanList.get(position);
        if (etIndex == Step7ZhuSuAdapter.ET_ZHUSU_SUDU) {
            zhuSuResbean.setZhuSuSuDu(text);
        } else if (etIndex == Step7ZhuSuAdapter.ET_ZHUSU_SHIJIAN) {
            zhuSuResbean.setZhuSuTime(text);
        } else if (etIndex == Step7ZhuSuAdapter.ET_ZHUSU_YALI) {
            zhuSuResbean.setYaLiMax(text);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                saveData2DBAndJump2Next();
                break;
            case R.id.btnAddRecord:
                if (zhuSuResbeanList.size() == 0) {
                    zhuSuResbeanList.add(new ZhuSuResbean(null, MainApplication.thisTimeId, "", "", "", ""));
                    adapter.notifyDataSetChanged();
                } else {
                    if (presenter.checkDataIsLegal(zhuSuResbeanList)) {
                        zhuSuResbeanList.add(new ZhuSuResbean(null, MainApplication.thisTimeId, "", "", "", ""));
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void saveData2DBAndJump2Next() {
        if (Constants.isNeedTest && !isHistory) {
            setDataAndCheck();
            boolean isHave = presenter.checkDataIsHaveNull(step7Resbean);
            if (isHave) {
                return;
            }
            try {
                step7Resbean.setStep7Id(MainApplication.thisTimeId);
                step7Resbean.setCurrentStepIsChecked(true);

                Step7ResbeanDao step7ResbeanDao = MainApplication.daoSession.getStep7ResbeanDao();
                ZhuSuResbeanDao zhuSuResbeanDao = MainApplication.daoSession.getZhuSuResbeanDao();
                for (int i = 0; i < zhuSuResbeanList.size(); i++) {
                    zhuSuResbeanDao.insert(zhuSuResbeanList.get(i));
                }
                step7ResbeanDao.insert(step7Resbean);
                TestUtil.getAllDbDatas();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
    }

    private void setDataAndCheck() {
        step7Resbean.setZuijiaShijian(etZuiJiaShijian.getText().toString());
        step7Resbean.setZuijiaSuDu(etZuiJiaSuDu.getText().toString());
    }

}
