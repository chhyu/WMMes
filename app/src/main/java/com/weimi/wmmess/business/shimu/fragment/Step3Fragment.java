package com.weimi.wmmess.business.shimu.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.adapter.FixSideAdapter;
import com.weimi.wmmess.business.shimu.adapter.MoveSideAdapter;
import com.weimi.wmmess.business.shimu.adapter.SlideAdapter;
import com.weimi.wmmess.business.shimu.adapter.Step3ShuiLuLianjieAdapter;
import com.weimi.wmmess.business.shimu.bean.step3.FixSideResbean;
import com.weimi.wmmess.business.shimu.bean.step3.FixSideResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.MoveSideResbean;
import com.weimi.wmmess.business.shimu.bean.step3.MoveSideResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.SlideResbean;
import com.weimi.wmmess.business.shimu.bean.step3.SlideResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.Step3MuResBean;
import com.weimi.wmmess.business.shimu.bean.step3.Step3MuResBeanDao;
import com.weimi.wmmess.business.shimu.bean.step3.Step3ShuiLuDate;
import com.weimi.wmmess.business.shimu.bean.step3.Step3ShuiLuDateDao;
import com.weimi.wmmess.business.shimu.presenter.Step2Presenter;
import com.weimi.wmmess.business.shimu.presenter.Step3Presenter;
import com.weimi.wmmess.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

import static com.weimi.wmmess.MainApplication.thisTimeId;

/**
 * 第三步：模具水路流量测试&水路连接
 */
public class Step3Fragment extends BaseFragment<Step3Presenter> implements View.OnClickListener {
    private static final int stepPosition = 2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnNextStep;
    private Button btnAddLiuLiangRecord;
    private Button btnAddLianJieRecord;
    private RecyclerView rcvDongMo, rcvDingMo, rcvHuaKuai, rcvShuiLuLianJie;
    private Step3ShuiLuLianjieAdapter shuiluLianJieAdapter;
    private MoveSideAdapter moveSideAdapter;
    private FixSideAdapter fixSideAdapter;
    private SlideAdapter slideAdapter;
    private Step2Presenter presenter;
    private Step3MuResBean step3MuResBean;

    public static Step3Fragment newInstance(long historyId, boolean isHistory) {
        Step3Fragment fragment = new Step3Fragment();
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
        return R.layout.fragment_step3;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        btnNextStep = rootView.findViewById(R.id.btnNextStep);

        rcvDongMo = rootView.findViewById(R.id.rcvDongMo);
        rcvDingMo = rootView.findViewById(R.id.rcvDingMo);
        rcvHuaKuai = rootView.findViewById(R.id.rcvHuaKuai);
        rcvShuiLuLianJie = rootView.findViewById(R.id.rcvShuiLuLianJie);

        btnAddLiuLiangRecord = rootView.findViewById(R.id.btnAddRecord);
        btnAddLianJieRecord = rootView.findViewById(R.id.btnAddRecordLiuliang);

        btnAddLiuLiangRecord.setOnClickListener(this);
        btnAddLianJieRecord.setOnClickListener(this);
        btnNextStep.setOnClickListener(this);

    }

    @Override
    public void initData() {
        presenter = new Step2Presenter(this);
        step3MuResBean = new Step3MuResBean();
        initRecordList();
        if (historyId != 0 && isHistory == true) {
            showHistoryData();
        }
    }

    private void showHistoryData() {
        try {
            Step3MuResBean step3MuResBean = MainApplication.daoSession.getStep3MuResBeanDao().load(historyId);
            List<FixSideResbean> fixSideResbeans = MainApplication.daoSession.getFixSideResbeanDao().loadAll();
            if (dingMoRecordList != null && dingMoRecordList.size() > 0) {
                dingMoRecordList.clear();
            }
            for (int i = 0; i < fixSideResbeans.size(); i++) {
                FixSideResbean fixSideResbean = fixSideResbeans.get(i);
                if (fixSideResbean.getFixSideResbeanId().equals(historyId)) {
                    dingMoRecordList.add(fixSideResbean);
                }
            }

            List<MoveSideResbean> moveSideResbeans = MainApplication.daoSession.getMoveSideResbeanDao().loadAll();
            if (dongMoRecordList != null && dongMoRecordList.size() > 0) {
                dongMoRecordList.clear();
            }
            for (MoveSideResbean moveSideResbean : moveSideResbeans) {
                if (moveSideResbean.getMoveSideResbeanId().equals(historyId)) {
                    dongMoRecordList.add(moveSideResbean);
                }
            }

            List<SlideResbean> slideResbeans = MainApplication.daoSession.getSlideResbeanDao().loadAll();
            if (huaKuaiRecordList != null && huaKuaiRecordList.size() > 0) {
                huaKuaiRecordList.clear();
            }
            for (SlideResbean slideResbean : slideResbeans) {
                if (slideResbean.getSlideResbeanId().equals(historyId)) {
                    huaKuaiRecordList.add(slideResbean);
                }
            }
            List<Step3ShuiLuDate> step3ShuiLuDates = MainApplication.daoSession.getStep3ShuiLuDateDao().loadAll();
            if (lianjieRecordList != null && lianjieRecordList.size() > 0) {
                lianjieRecordList.clear();
            }
            for (int i = 0; i < step3ShuiLuDates.size(); i++) {
                Step3ShuiLuDate step3ShuiLuDate = step3ShuiLuDates.get(i);
                if (step3ShuiLuDate.getShuiLuDateId().equals(historyId)) {
                    lianjieRecordList.add(step3ShuiLuDate);
                }
            }
            moveSideAdapter.notifyDataSetChanged();
            fixSideAdapter.notifyDataSetChanged();
            slideAdapter.notifyDataSetChanged();
            shuiluLianJieAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rcvDingMo.setEnabled(false);
            rcvDongMo.setEnabled(false);
            rcvHuaKuai.setEnabled(false);
            rcvShuiLuLianJie.setEnabled(false);
        }
    }

    private List<MoveSideResbean> dongMoRecordList = new ArrayList<>();
    private List<FixSideResbean> dingMoRecordList = new ArrayList<>();
    private List<SlideResbean> huaKuaiRecordList = new ArrayList<>();
    private List<Step3ShuiLuDate> lianjieRecordList = new ArrayList<>();

    private void initRecordList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getActivity());
        rcvDongMo.setLayoutManager(layoutManager);
        rcvDingMo.setLayoutManager(layoutManager2);
        rcvHuaKuai.setLayoutManager(layoutManager3);
        rcvShuiLuLianJie.setLayoutManager(layoutManager4);

        moveSideAdapter = new MoveSideAdapter(dongMoRecordList, getActivity());
        fixSideAdapter = new FixSideAdapter(dingMoRecordList, getActivity());
        slideAdapter = new SlideAdapter(huaKuaiRecordList, getActivity());

        shuiluLianJieAdapter = new Step3ShuiLuLianjieAdapter(lianjieRecordList, getActivity());

        rcvDongMo.setAdapter(moveSideAdapter);
        rcvDingMo.setAdapter(fixSideAdapter);
        rcvHuaKuai.setAdapter(slideAdapter);
        rcvShuiLuLianJie.setAdapter(shuiluLianJieAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                checkDataAndJump2Next();
                break;
            case R.id.btnAddRecord:
                addNewRecord();
                break;
            case R.id.btnAddRecordLiuliang:
                addShuiLuLianJieRecord();
                break;
        }
    }

    private void checkDataAndJump2Next() {
        if (Constants.isNeedTest && !isHistory) {
            if (dingMoRecordList.size() == 0 || dingMoRecordList.size() == 0 || huaKuaiRecordList.size() == 0 || lianjieRecordList.size() == 0) {
                ToastUtils.showShort("请填写水路流量测试记录和水路连接记录");
                return;
            }

            try {
                step3MuResBean.setStep3Id(thisTimeId);
                step3MuResBean.setCurrentStepIsChecked(true);
                Step3MuResBeanDao step3MuResBeanDao = MainApplication.daoSession.getStep3MuResBeanDao();

                MoveSideResbeanDao moveSideResbeanDao = MainApplication.daoSession.getMoveSideResbeanDao();
                for (int i = 0; i < dongMoRecordList.size(); i++) {
                    moveSideResbeanDao.insert(dongMoRecordList.get(i));
                }

                FixSideResbeanDao fixSideResbeanDao = MainApplication.daoSession.getFixSideResbeanDao();
                for (int i = 0; i < dingMoRecordList.size(); i++) {
                    fixSideResbeanDao.insert(dingMoRecordList.get(i));
                }

                SlideResbeanDao slideResbeanDao = MainApplication.daoSession.getSlideResbeanDao();
                for (int i = 0; i < huaKuaiRecordList.size(); i++) {
                    slideResbeanDao.insert(huaKuaiRecordList.get(i));
                }

                Step3ShuiLuDateDao step3ShuiLuDateDao = MainApplication.daoSession.getStep3ShuiLuDateDao();
                for (int i = 0; i < lianjieRecordList.size(); i++) {
                    step3ShuiLuDateDao.insert(lianjieRecordList.get(i));
                }
                step3MuResBeanDao.insert(step3MuResBean);
                TestUtil.getAllDbDatas();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
    }

    private void addShuiLuLianJieRecord() {
        lianjieRecordList.add(new Step3ShuiLuDate(null, thisTimeId, lianjieRecordList.size() == 0 ? 1 : lianjieRecordList.size() + 1, ""));
        shuiluLianJieAdapter.notifyDataSetChanged();
    }

    private void addNewRecord() {
        dongMoRecordList.add(new MoveSideResbean(null, thisTimeId, dongMoRecordList.size() == 0 ? 1 : dongMoRecordList.size() + 1, ""));
        dingMoRecordList.add(new FixSideResbean(null, thisTimeId, dingMoRecordList.size() == 0 ? 1 : dingMoRecordList.size() + 1, ""));
        huaKuaiRecordList.add(new SlideResbean(null, thisTimeId, huaKuaiRecordList.size() == 0 ? 1 : huaKuaiRecordList.size() + 1, ""));

        moveSideAdapter.notifyDataSetChanged();
        fixSideAdapter.notifyDataSetChanged();
        slideAdapter.notifyDataSetChanged();
    }
}
