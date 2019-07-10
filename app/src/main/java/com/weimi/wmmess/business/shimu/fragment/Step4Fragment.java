package com.weimi.wmmess.business.shimu.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.adapter.KongZhiDianAdapter;
import com.weimi.wmmess.business.shimu.adapter.ZhuLiuDaoAdapter;
import com.weimi.wmmess.business.shimu.bean.step4.KongZhiDianResbean;
import com.weimi.wmmess.business.shimu.bean.step4.KongZhiDianResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step4.Step4Resbean;
import com.weimi.wmmess.business.shimu.bean.step4.Step4ResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step4.ZhuLiuDaoResbean;
import com.weimi.wmmess.business.shimu.bean.step4.ZhuLiuDaoResbeanDao;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.business.shimu.presenter.Step4Presenter;
import com.weimi.wmmess.utils.NumFormatUtil;
import com.weimi.wmmess.utils.TestUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 第四步：温度设定
 */
public class Step4Fragment extends BaseFragment<Step4Presenter> implements View.OnClickListener {
    private static final int stepPosition = 3;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnNextStep, btnAddRecord;
    private RecyclerView rcvZhuLiuDao, rcvKongZhiDian;
    private ZhuLiuDaoAdapter zhuLiuDaoAdapter;
    private KongZhiDianAdapter kongZhiDianAdapter;
    private Step4Presenter presenter;
    private Step4Resbean step4Resbean;
    private EditText etPenZui, etOne, etTwo, etThree, etFour, etFive, etXiaLiaoKou, etCheckTemp, etDongMoSheDing, etDongMoShiJi, etDingMoSheDing, etDingMoShiJi,
            etHuaKuaiSheDing, etHuaKuaiShiJi, etLuoGanZhiJing, etYuanLiao, etLuoGanZhuanSu, etZhuanFenZhong;

    public static Step4Fragment newInstance(long historyId, boolean isHistory) {
        Step4Fragment fragment = new Step4Fragment();
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
        return R.layout.fragment_step4;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        btnNextStep = rootView.findViewById(R.id.btnNextStep);
        btnAddRecord = rootView.findViewById(R.id.btnAddRecord);
        rcvZhuLiuDao = rootView.findViewById(R.id.rcvZhuLiuDao);
        rcvKongZhiDian = rootView.findViewById(R.id.rcvKongZhiDian);

        etPenZui = rootView.findViewById(R.id.etPenZui);
        etOne = rootView.findViewById(R.id.etOne);
        etTwo = rootView.findViewById(R.id.etTwo);
        etThree = rootView.findViewById(R.id.etThree);
        etFour = rootView.findViewById(R.id.etFour);
        etFive = rootView.findViewById(R.id.etFive);
        etXiaLiaoKou = rootView.findViewById(R.id.etXiaLiaoKou);
        etCheckTemp = rootView.findViewById(R.id.etCheckTemp);

        etDongMoSheDing = rootView.findViewById(R.id.etDongMoSheDing);
        etDongMoShiJi = rootView.findViewById(R.id.etDongMoShiJi);
        etDingMoSheDing = rootView.findViewById(R.id.etDingMoSheDing);
        etDingMoShiJi = rootView.findViewById(R.id.etDingMoShiJi);
        etHuaKuaiSheDing = rootView.findViewById(R.id.etHuaKuaiSheDing);
        etHuaKuaiShiJi = rootView.findViewById(R.id.etHuaKuaiShiJi);

        etLuoGanZhiJing = rootView.findViewById(R.id.etLGZJ);
        etYuanLiao = rootView.findViewById(R.id.etYuanLiao);
        etLuoGanZhuanSu = rootView.findViewById(R.id.etLuoGanZhuanSu);
        etZhuanFenZhong = rootView.findViewById(R.id.etZhuanFenZhong);

        etPenZui.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etOne.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etTwo.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etThree.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etFour.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etFive.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etXiaLiaoKou.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etCheckTemp.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etDongMoSheDing.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etDongMoShiJi.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etDingMoSheDing.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etDingMoShiJi.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etHuaKuaiSheDing.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etHuaKuaiShiJi.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etLuoGanZhiJing.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});
        etLuoGanZhuanSu.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(4)});

        btnNextStep.setOnClickListener(this);
        btnAddRecord.setOnClickListener(this);
    }

    @Override
    public void initData() {
        presenter = new Step4Presenter(this);
        step4Resbean = new Step4Resbean();
        initAdapter();
        if (historyId != 0 && isHistory == true) {
            showHistoryData();
        }

        etLuoGanZhuanSu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String luoGanZhuanSu = etLuoGanZhuanSu.getText().toString();
                String luoGanZhiJing = etLuoGanZhiJing.getText().toString();
                if (StringUtils.isEmpty(luoGanZhiJing)) {
                    ToastUtils.showShort("请输入螺杆直径");
                    return;
                }
                if (StringUtils.isEmpty(luoGanZhuanSu)) {
                    ToastUtils.showShort("请输入螺杆转速");
                    return;
                }
                double zhuanSu = Double.parseDouble(luoGanZhuanSu);
                double zhiJing = Double.parseDouble(luoGanZhiJing);
                calculateZhuanFenZhong(zhuanSu, zhiJing);
            }
        });
    }

    private void showHistoryData() {
        try {
            Step4Resbean step4Resbean = MainApplication.daoSession.getStep4ResbeanDao().load(historyId);
            etPenZui.setText(step4Resbean.getPenzui());
            etOne.setText(step4Resbean.getOne());
            etTwo.setText(step4Resbean.getTwo());
            etThree.setText(step4Resbean.getThree());
            etFour.setText(step4Resbean.getFour());
            etFive.setText(step4Resbean.getFive());
            etXiaLiaoKou.setText(step4Resbean.getXiaLiaoKou());
            etCheckTemp.setText(step4Resbean.getCheckTemp());

            etDingMoSheDing.setText(step4Resbean.getDingMoSheDing());
            etDingMoShiJi.setText(step4Resbean.getDingMoShiJi());
            etDongMoSheDing.setText(step4Resbean.getDongMoSheDing());
            etDongMoShiJi.setText(step4Resbean.getDongMoShiJi());
            etHuaKuaiSheDing.setText(step4Resbean.getHuaKuaiSheDing());
            etHuaKuaiShiJi.setText(step4Resbean.getHuaKuaiShiJi());

            etLuoGanZhiJing.setText(String.valueOf(step4Resbean.getLuoGanZhiJing()));
            etYuanLiao.setText(String.valueOf(step4Resbean.getYuanLiao()));
            etLuoGanZhuanSu.setText(String.valueOf(step4Resbean.getLuoGanZhuanSu()));
            etZhuanFenZhong.setText(String.valueOf(step4Resbean.getZhuanFenZhong()));

            List<ZhuLiuDaoResbean> zhuLiuDaoResbeanList = MainApplication.daoSession.getZhuLiuDaoResbeanDao().loadAll();
            if (zhuLiuDaoResbeans != null && zhuLiuDaoResbeans.size() > 0) {
                zhuLiuDaoResbeans.clear();
            }
            for (ZhuLiuDaoResbean zhuLiuDaoResbean : zhuLiuDaoResbeanList) {
                if (zhuLiuDaoResbean.getZhuliuDaoId().equals(historyId)) {
                    zhuLiuDaoResbeans.add(zhuLiuDaoResbean);
                }
            }

            List<KongZhiDianResbean> kongZhiDianResbeanList = MainApplication.daoSession.getKongZhiDianResbeanDao().loadAll();
            if (kongZhiDianResbeans != null && kongZhiDianResbeans.size() > 0) {
                kongZhiDianResbeans.clear();
            }
            for (KongZhiDianResbean kongZhiDianResbean : kongZhiDianResbeanList) {
                kongZhiDianResbeans.add(kongZhiDianResbean);
            }
            zhuLiuDaoAdapter.notifyDataSetChanged();
            kongZhiDianAdapter.notifyDataSetChanged();

            setWidgetEnable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setWidgetEnable() {
        etPenZui.setEnabled(false);
        etOne.setEnabled(false);
        etTwo.setEnabled(false);
        etThree.setEnabled(false);
        etFour.setEnabled(false);
        etFive.setEnabled(false);
        etXiaLiaoKou.setEnabled(false);
        etCheckTemp.setEnabled(false);

        etDingMoSheDing.setEnabled(false);
        etDingMoShiJi.setEnabled(false);
        etDongMoSheDing.setEnabled(false);
        etDongMoShiJi.setEnabled(false);
        etHuaKuaiSheDing.setEnabled(false);
        etHuaKuaiShiJi.setEnabled(false);

        etLuoGanZhiJing.setEnabled(false);
        etYuanLiao.setEnabled(false);
        etLuoGanZhuanSu.setEnabled(false);
        etZhuanFenZhong.setEnabled(false);

        rcvKongZhiDian.setEnabled(false);
        rcvZhuLiuDao.setEnabled(false);
    }

    private void initAdapter() {
        zhuLiuDaoAdapter = new ZhuLiuDaoAdapter(getActivity(), zhuLiuDaoResbeans);
        kongZhiDianAdapter = new KongZhiDianAdapter(getActivity(), kongZhiDianResbeans);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());

        rcvZhuLiuDao.setLayoutManager(layoutManager);
        rcvKongZhiDian.setLayoutManager(layoutManager2);

        rcvZhuLiuDao.setAdapter(zhuLiuDaoAdapter);
        rcvKongZhiDian.setAdapter(kongZhiDianAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                checkDataAndJump2Next();
                break;
            case R.id.btnAddRecord:
                addRecord();
                break;
            default:
                break;
        }
    }

    /**
     * 给step4Resbean 赋值
     */
    private void setDate2Bean() {
        step4Resbean.setPenzui(etPenZui.getText().toString());
        step4Resbean.setOne(etOne.getText().toString());
        step4Resbean.setTwo(etTwo.getText().toString());
        step4Resbean.setThree(etThree.getText().toString());
        step4Resbean.setFour(etFour.getText().toString());
        step4Resbean.setFive(etFive.getText().toString());
        step4Resbean.setXiaLiaoKou(etXiaLiaoKou.getText().toString());
        step4Resbean.setCheckTemp(etCheckTemp.getText().toString());
        step4Resbean.setDongMoSheDing(etDongMoSheDing.getText().toString());
        step4Resbean.setDongMoShiJi(etDongMoShiJi.getText().toString());
        step4Resbean.setDingMoSheDing(etDingMoSheDing.getText().toString());
        step4Resbean.setDingMoShiJi(etDingMoShiJi.getText().toString());
        step4Resbean.setHuaKuaiSheDing(etHuaKuaiSheDing.getText().toString());
        step4Resbean.setHuaKuaiShiJi(etHuaKuaiShiJi.getText().toString());


        String luoganzhijing = etLuoGanZhiJing.getText().toString();
        if (!StringUtils.isEmpty(luoganzhijing)) {
            step4Resbean.setLuoGanZhiJing(Double.parseDouble(luoganzhijing));
        }

        String yuanliao = etYuanLiao.getText().toString();
        if (!StringUtils.isEmpty(yuanliao)) {
            step4Resbean.setYuanLiao(Double.parseDouble(yuanliao));
        }
        String luoganzhaunsu = etLuoGanZhuanSu.getText().toString();
        if (!StringUtils.isEmpty(luoganzhaunsu)) {
            step4Resbean.setLuoGanZhuanSu(Double.parseDouble(luoganzhaunsu));
        }
    }

    private void checkDataAndJump2Next() {
        if (Constants.isNeedTest && !isHistory) {
            setDate2Bean();
            boolean isHave = presenter.checkDataIsHaveNull(step4Resbean);
            if (isHave) {
                return;
            }
            try {
                step4Resbean.setStep4Id(MainApplication.thisTimeId);
                step4Resbean.setCurrentStepIsChecked(true);
                calculateZhuanFenZhong(step4Resbean.getLuoGanZhuanSu(), step4Resbean.getLuoGanZhiJing());
                Step4ResbeanDao step4ResbeanDao = MainApplication.daoSession.getStep4ResbeanDao();
                ZhuLiuDaoResbeanDao zhuLiuDaoResbeanDao = MainApplication.daoSession.getZhuLiuDaoResbeanDao();
                for (int i = 0; i < zhuLiuDaoResbeans.size(); i++) {
                    zhuLiuDaoResbeanDao.insert(zhuLiuDaoResbeans.get(i));
                }

                KongZhiDianResbeanDao kongZhiDianResbeanDao = MainApplication.daoSession.getKongZhiDianResbeanDao();
                for (int i = 0; i < kongZhiDianResbeans.size(); i++) {
                    kongZhiDianResbeanDao.insert(kongZhiDianResbeans.get(i));
                }
                step4ResbeanDao.insert(step4Resbean);
                TestUtil.getAllDbDatas();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
    }

    private void calculateZhuanFenZhong(double zhuanSu, double zhiJing) {
        //换算成 转/分钟==(螺杆转速*60/(螺杆直径*3.142))
        double zhuanFenZhong = zhuanSu * 60 / (zhiJing * 3.142);
//        BigDecimal bg_rectangle_corner = new BigDecimal(zhuanFenZhong);
//        double f1 = bg_rectangle_corner.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        etZhuanFenZhong.setText(String.valueOf(  NumFormatUtil.getDoubleNumberString(zhuanFenZhong)));
        step4Resbean.setZhuanFenZhong(zhuanFenZhong);
    }

    private List<ZhuLiuDaoResbean> zhuLiuDaoResbeans = new ArrayList<>();
    private List<KongZhiDianResbean> kongZhiDianResbeans = new ArrayList<>();

    private void addRecord() {
        zhuLiuDaoResbeans.add(new ZhuLiuDaoResbean(null, MainApplication.thisTimeId, zhuLiuDaoResbeans.size() + 1, ""));
        kongZhiDianResbeans.add(new KongZhiDianResbean(null, MainApplication.thisTimeId, kongZhiDianResbeans.size() + 1, ""));

        zhuLiuDaoAdapter.notifyDataSetChanged();
        kongZhiDianAdapter.notifyDataSetChanged();
    }

}
