package com.weimi.wmmess.business.shimu.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.bean.step13.Step13Resbean;
import com.weimi.wmmess.business.shimu.bean.step13.Step13ResbeanDao;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.business.shimu.presenter.Step13Presenter;
import com.weimi.wmmess.utils.TestUtil;

/**
 * 成型工艺参数表
 */
public class Step13Fragment extends BaseFragment<Step13Presenter> implements View.OnClickListener {
    public static final int stepPosition = 12;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnNextStep;
    private EditText etJinLiaoDuanSD, etJinLiaoDuanSJ, etJinLiaoDuanMin, etJinLiaoDuanMax, etJinLiaoDuanTime, etHouDuanSD, etHouDuanSJ, etHouDuanMin, etHouDuanMax, etHouDuanTime;
    private EditText etZhongJianDuanSD, etZhongJianDuanSJ, etZhongJianDuanMin, etZhongJianDuanMax, etZhongJianDuanTime, etQianDuanSD, etQianDuanSJ, etQianDuanMin, etQianDuanMax, etQianDuanTime;
    private EditText etPenZuiSD, etPenZuiSJ, etPenZuiMin, etPenZuiMax, etPenZuiTime, etSheChu1SuSD, etSheChu1SuSJ, etSheChu1SuMin, etSheChu1SuMax, etSheChu1SuTime;
    private EditText etSheChu2SuSD, etSheChu2SuSJ, etSheChu2SuMin, etSheChu2SuMax, etSheChu2SuTime, etSheChu3SuSD, etSheChu3SuSJ, etSheChu3SuMin, etSheChu3SuMax, etSheChu3SuTime;
    private EditText etSheChu4SuSD, etSheChu4SuSJ, etSheChu4SuMin, etSheChu4SuMax, etSheChu4SuTime, et1DuanWeiZhiSD, et1DuanWeiZhiSJ, et1DuanWeiZhiMin, et1DuanWeiZhiMax, et1DuanWeiZhiTime;
    private EditText et2DuanWeiZhiSD, et2DuanWeiZhiSJ, et2DuanWeiZhiMin, et2DuanWeiZhiMax, et2DuanWeiZhiTime, et3DuanWeiZhiSD, et3DuanWeiZhiSJ, et3DuanWeiZhiMin, et3DuanWeiZhiMax, et3DuanWeiZhiTime;
    private EditText et4DuanWeiZhiSD, et4DuanWeiZhiSJ, et4DuanWeiZhiMin, et4DuanWeiZhiMax, et4DuanWeiZhiTime, etVPWeiZhiSD, etVPWeiZhiSJ, etVPWeiZhiMin, etVPWeiZhiMax, etVPWeiZhiTime;
    private EditText etSheChuYaLi1SD, etSheChuYaLi1SJ, etSheChuYaLi1Min, etSheChuYaLi1Max, etSheChuYaLi1Time, etSheChuYaLi2SD, etSheChuYaLi2SJ, etSheChuYaLi2Min, etSheChuYaLi2Max, etSheChuYaLi2Time;
    private EditText etSheChuYaLi3SD, etSheChuYaLi3SJ, etSheChuYaLi3Min, etSheChuYaLi3Max, etSheChuYaLi3Time, etSheChuYaLi4SD, etSheChuYaLi4SJ, etSheChuYaLi4Min, etSheChuYaLi4Max, etSheChuYaLi4Time;
    private EditText etLengQueShiJianSD, etLengQueShiJianSJ, etLengQueShiJianMin, etLengQueShiJianMax, etLengQueShiJianTime, etSheChuShiJianSD, etSheChuShiJianSJ, etSheChuShiJianMin, etSheChuShiJianMax, etSheChuShiJianTime;
    private EditText etZhouQiShiJianSD, etZhouQiShiJianSJ, etZhouQiShiJianMin, etZhouQiShiJianMax, etZhouQiShiJianTime, etBaoYa1DuanSD, etBaoYa1DuanSJ, etBaoYa1DuanMin, etBaoYa1DuanMax, etBaoYa1DuanTime;
    private EditText etBaoYa2DuanSD, etBaoYa2DuanSJ, etBaoYa2DuanMin, etBaoYa2DuanMax, etBaoYa2DuanTime, etBaoYa3DuanSD, etBaoYa3DuanSJ, etBaoYa3DuanMin, etBaoYa3DuanMax, etBaoYa3DuanTime, etBaoYa1SuSD, etBaoYa1SuSJ, etBaoYa1SuMin, etBaoYa1SuMax, etBaoYa1SuTime;
    private EditText etBaoYa2SuSD, etBaoYa2SuSJ, etBaoYa2SuMin, etBaoYa2SuMax, etBaoYa2SuTime, etBaoYa3SuSD, etBaoYa3SuSJ, etBaoYa3SuMin, etBaoYa3SuMax, etBaoYa3SuTime, etBaoYaShiJian1SD, etBaoYaShiJian1SJ, etBaoYaShiJian1Min, etBaoYaShiJian1Max, etBaoYaShiJian1Time;
    private EditText etBaoYaShiJian2SD, etBaoYaShiJian2SJ, etBaoYaShiJian2Min, etBaoYaShiJian2Max, etBaoYaShiJian2Time, etBaoYaShiJian3SD, etBaoYaShiJian3SJ, etBaoYaShiJian3Min, etBaoYaShiJian3Max, etBaoYaShiJian3Time;
    private EditText etLuoGanZhuanSuSD, etLuoGanZhuanSuSJ, etLuoGanZhuanSuMin, etLuoGanZhuanSuMax, etLuoGanZhuanSuTime, etBeiYaSD, etBeiYaSJ, etBeiYaMin, etBeiYaMax, etBeiYaTime, etSongTuiWeiZhiSD, etSongTuiWeiZhiSJ, etSongTuiWeiZhiMin, etSongTuiWeiZhiMax, etSongTuiWeiZhiTime;
    private EditText etYongLiaoLiangSD, etYongLiaoLiangSJ, etYongLiaoLiangMin, etYongLiaoLiangMax, etYongLiaoLiangTime, etHuanChongLiangSD, etHuanChongLiangSJ, etHuanChongLiangMin, etHuanChongLiangMax, etHuanChongLiangTime, etMuJuBaoHuYaLiSD, etMuJuBaoHuYaLiSJ, etMuJuBaoHuYaLiMin, etMuJuBaoHuYaLiMax, etMuJuBaoHuYaLiTime;
    private EditText etMuJuBaoHuWeiZhiSD, etMuJuBaoHuWeiZhiSJ, etMuJuBaoHuWeiZhiMin, etMuJuBaoHuWeiZhiMax, etMuJuBaoHuWeiZhiTime, etSuoMoLiSD, etSuoMoLiSJ, etSuoMoLiMin, etSuoMoLiMax, etSuoMoLiTime, etDingChuWeiZhiSD, etDingChuWeiZhiSJ, etDingChuWeiZhiMin, etDingChuWeiZhiMax, etDingChuWeiZhiTime;
    private EditText etDingMoWenDuSD, etDingMoWenDuSJ, etDingMoWenDuMin, etDingMoWenDuMax, etDingMoWenDuTime, etDongMoWenDuSD, etDongMoWenDuSJ, etDongMoWenDuMin, etDongMoWenDuMax, etDongMoWenDuTime;
    private EditText etHongLiaoWenDuSD, etHongLiaoWenDuSJ, etHongLiaoWenDuMin, etHongLiaoWenDuMax, etHongLiaoWenDuTime, etHuiLiaoBiLiSD, etHuiLiaoBiLiSJ, etHuiLiaoBiLiMin, etHuiLiaoBiLiMax, etHuiLiaoBiLiTime;
    private EditText etQianJianShuLiangSD, etQianJianShuLiangSJ, etQianJianShuLiangMin, etQianJianShuLiangMax, etQianJianShuLiangTime, etQianJianBianHaoSD, etQianJianBianHaoSJ, etQianJianBianHaoMin, etQianJianBianHaoMax, etQianJianBianHaoTime;
    private Step13Resbean step13Resbean;
    private Step13Presenter presenter;

    public static Step13Fragment newInstance(long historyId, boolean isHistory) {
        Step13Fragment fragment = new Step13Fragment();
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
        return R.layout.fragment_step13;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        btnNextStep = rootView.findViewById(R.id.btnNextStep);

        etJinLiaoDuanSD = rootView.findViewById(R.id.etJinLiaoDuanSD);
        etJinLiaoDuanSJ = rootView.findViewById(R.id.etJinLiaoDuanSJ);
        etJinLiaoDuanMin = rootView.findViewById(R.id.etJinLiaoDuanMin);
        etJinLiaoDuanMax = rootView.findViewById(R.id.etJinLiaoDuanMax);
        etJinLiaoDuanTime = rootView.findViewById(R.id.etJinLiaoDuanTime);

        etJinLiaoDuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etJinLiaoDuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etJinLiaoDuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etJinLiaoDuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etHouDuanSD = rootView.findViewById(R.id.etHouDuanSD);
        etHouDuanSJ = rootView.findViewById(R.id.etHouDuanSJ);
        etHouDuanMin = rootView.findViewById(R.id.etHouDuanMin);
        etHouDuanMax = rootView.findViewById(R.id.etHouDuanMax);
        etHouDuanTime = rootView.findViewById(R.id.etHouDuanTime);

        etHouDuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHouDuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHouDuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHouDuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etZhongJianDuanSD = rootView.findViewById(R.id.etZhongJianDuanSD);
        etZhongJianDuanSJ = rootView.findViewById(R.id.etZhongJianDuanSJ);
        etZhongJianDuanMin = rootView.findViewById(R.id.etZhongJianDuanMin);
        etZhongJianDuanMax = rootView.findViewById(R.id.etZhongJianDuanMax);
        etZhongJianDuanTime = rootView.findViewById(R.id.etZhongJianDuanTime);

        etZhongJianDuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhongJianDuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhongJianDuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhongJianDuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etQianDuanSD = rootView.findViewById(R.id.etQianDuanSD);
        etQianDuanSJ = rootView.findViewById(R.id.etQianDuanSJ);
        etQianDuanMin = rootView.findViewById(R.id.etQianDuanMin);
        etQianDuanMax = rootView.findViewById(R.id.etQianDuanMax);
        etQianDuanTime = rootView.findViewById(R.id.etQianDuanTime);

        etQianDuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianDuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianDuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianDuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etPenZuiSD = rootView.findViewById(R.id.etPenZuiSD);
        etPenZuiSJ = rootView.findViewById(R.id.etPenZuiSJ);
        etPenZuiMin = rootView.findViewById(R.id.etPenZuiMin);
        etPenZuiMax = rootView.findViewById(R.id.etPenZuiMax);
        etPenZuiTime = rootView.findViewById(R.id.etPenZuiTime);

        etPenZuiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etPenZuiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etPenZuiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etPenZuiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChu1SuSD = rootView.findViewById(R.id.etSheChu1SuSD);
        etSheChu1SuSJ = rootView.findViewById(R.id.etSheChu1SuSJ);
        etSheChu1SuMin = rootView.findViewById(R.id.etSheChu1SuMin);
        etSheChu1SuMax = rootView.findViewById(R.id.etSheChu1SuMax);
        etSheChu1SuTime = rootView.findViewById(R.id.etSheChu1SuTime);

        etSheChu1SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu1SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu1SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu1SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChu2SuSD = rootView.findViewById(R.id.etSheChu2SuSD);
        etSheChu2SuSJ = rootView.findViewById(R.id.etSheChu2SuSJ);
        etSheChu2SuMin = rootView.findViewById(R.id.etSheChu2SuMin);
        etSheChu2SuMax = rootView.findViewById(R.id.etSheChu2SuMax);
        etSheChu2SuTime = rootView.findViewById(R.id.etSheChu2SuTime);

        etSheChu2SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu2SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu2SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu2SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChu3SuSD = rootView.findViewById(R.id.etSheChu3SuSD);
        etSheChu3SuSJ = rootView.findViewById(R.id.etSheChu3SuSJ);
        etSheChu3SuMin = rootView.findViewById(R.id.etSheChu3SuMin);
        etSheChu3SuMax = rootView.findViewById(R.id.etSheChu3SuMax);
        etSheChu3SuTime = rootView.findViewById(R.id.etSheChu3SuTime);

        etSheChu3SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu3SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu3SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu3SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChu4SuSD = rootView.findViewById(R.id.etSheChu4SuSD);
        etSheChu4SuSJ = rootView.findViewById(R.id.etSheChu4SuSJ);
        etSheChu4SuMin = rootView.findViewById(R.id.etSheChu4SuMin);
        etSheChu4SuMax = rootView.findViewById(R.id.etSheChu4SuMax);
        etSheChu4SuTime = rootView.findViewById(R.id.etSheChu4SuTime);

        etSheChu4SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu4SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu4SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu4SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        et1DuanWeiZhiSD = rootView.findViewById(R.id.et1DuanWeiZhiSD);
        et1DuanWeiZhiSJ = rootView.findViewById(R.id.et1DuanWeiZhiSJ);
        et1DuanWeiZhiMin = rootView.findViewById(R.id.et1DuanWeiZhiMin);
        et1DuanWeiZhiMax = rootView.findViewById(R.id.et1DuanWeiZhiMax);
        et1DuanWeiZhiTime = rootView.findViewById(R.id.et1DuanWeiZhiTime);

        et1DuanWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et1DuanWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et1DuanWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et1DuanWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        et2DuanWeiZhiSD = rootView.findViewById(R.id.et2DuanWeiZhiSD);
        et2DuanWeiZhiSJ = rootView.findViewById(R.id.et2DuanWeiZhiSJ);
        et2DuanWeiZhiMin = rootView.findViewById(R.id.et2DuanWeiZhiMin);
        et2DuanWeiZhiMax = rootView.findViewById(R.id.et2DuanWeiZhiMax);
        et2DuanWeiZhiTime = rootView.findViewById(R.id.et2DuanWeiZhiTime);

        et2DuanWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et2DuanWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et2DuanWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et2DuanWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        et3DuanWeiZhiSD = rootView.findViewById(R.id.et3DuanWeiZhiSD);
        et3DuanWeiZhiSJ = rootView.findViewById(R.id.et3DuanWeiZhiSJ);
        et3DuanWeiZhiMin = rootView.findViewById(R.id.et3DuanWeiZhiMin);
        et3DuanWeiZhiMax = rootView.findViewById(R.id.et3DuanWeiZhiMax);
        et3DuanWeiZhiTime = rootView.findViewById(R.id.et3DuanWeiZhiTime);

        et3DuanWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et3DuanWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et3DuanWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et3DuanWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        et4DuanWeiZhiSD = rootView.findViewById(R.id.et4DuanWeiZhiSD);
        et4DuanWeiZhiSJ = rootView.findViewById(R.id.et4DuanWeiZhiSJ);
        et4DuanWeiZhiMin = rootView.findViewById(R.id.et4DuanWeiZhiMin);
        et4DuanWeiZhiMax = rootView.findViewById(R.id.et4DuanWeiZhiMax);
        et4DuanWeiZhiTime = rootView.findViewById(R.id.et4DuanWeiZhiTime);

        et4DuanWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et4DuanWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et4DuanWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et4DuanWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etVPWeiZhiSD = rootView.findViewById(R.id.etVPWeiZhiSD);
        etVPWeiZhiSJ = rootView.findViewById(R.id.etVPWeiZhiSJ);
        etVPWeiZhiMin = rootView.findViewById(R.id.etVPWeiZhiMin);
        etVPWeiZhiMax = rootView.findViewById(R.id.etVPWeiZhiMax);
        etVPWeiZhiTime = rootView.findViewById(R.id.etVPWeiZhiTime);

        etVPWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etVPWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etVPWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etVPWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChuYaLi1SD = rootView.findViewById(R.id.etSheChuYaLi1SD);
        etSheChuYaLi1SJ = rootView.findViewById(R.id.etSheChuYaLi1SJ);
        etSheChuYaLi1Min = rootView.findViewById(R.id.etSheChuYaLi1Min);
        etSheChuYaLi1Max = rootView.findViewById(R.id.etSheChuYaLi1Max);
        etSheChuYaLi1Time = rootView.findViewById(R.id.etSheChuYaLi1Time);

        etSheChuYaLi1SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi1SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi1Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi1Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChuYaLi2SD = rootView.findViewById(R.id.etSheChuYaLi2SD);
        etSheChuYaLi2SJ = rootView.findViewById(R.id.etSheChuYaLi2SJ);
        etSheChuYaLi2Min = rootView.findViewById(R.id.etSheChuYaLi2Min);
        etSheChuYaLi2Max = rootView.findViewById(R.id.etSheChuYaLi2Max);
        etSheChuYaLi2Time = rootView.findViewById(R.id.etSheChuYaLi2Time);

        etSheChuYaLi2SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi2SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi2Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi2Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChuYaLi3SD = rootView.findViewById(R.id.etSheChuYaLi3SD);
        etSheChuYaLi3SJ = rootView.findViewById(R.id.etSheChuYaLi3SJ);
        etSheChuYaLi3Min = rootView.findViewById(R.id.etSheChuYaLi3Min);
        etSheChuYaLi3Max = rootView.findViewById(R.id.etSheChuYaLi3Max);
        etSheChuYaLi3Time = rootView.findViewById(R.id.etSheChuYaLi3Time);

        etSheChuYaLi3SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi3SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi3Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi3Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChuYaLi4SD = rootView.findViewById(R.id.etSheChuYaLi4SD);
        etSheChuYaLi4SJ = rootView.findViewById(R.id.etSheChuYaLi4SJ);
        etSheChuYaLi4Min = rootView.findViewById(R.id.etSheChuYaLi4Min);
        etSheChuYaLi4Max = rootView.findViewById(R.id.etSheChuYaLi4Max);
        etSheChuYaLi4Time = rootView.findViewById(R.id.etSheChuYaLi4Time);

        etSheChuYaLi4SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi4SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi4Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi4Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etLengQueShiJianSD = rootView.findViewById(R.id.etLengQueShiJianSD);
        etLengQueShiJianSJ = rootView.findViewById(R.id.etLengQueShiJianSJ);
        etLengQueShiJianMin = rootView.findViewById(R.id.etLengQueShiJianMin);
        etLengQueShiJianMax = rootView.findViewById(R.id.etLengQueShiJianMax);
        etLengQueShiJianTime = rootView.findViewById(R.id.etLengQueShiJianTime);

        etLengQueShiJianSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLengQueShiJianSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLengQueShiJianMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLengQueShiJianMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChuShiJianSD = rootView.findViewById(R.id.etSheChuShiJianSD);
        etSheChuShiJianSJ = rootView.findViewById(R.id.etSheChuShiJianSJ);
        etSheChuShiJianMin = rootView.findViewById(R.id.etSheChuShiJianMin);
        etSheChuShiJianMax = rootView.findViewById(R.id.etSheChuShiJianMax);
        etSheChuShiJianTime = rootView.findViewById(R.id.etSheChuShiJianTime);

        etSheChuShiJianSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuShiJianSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuShiJianMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuShiJianMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etZhouQiShiJianSD = rootView.findViewById(R.id.etZhouQiShiJianSD);
        etZhouQiShiJianSJ = rootView.findViewById(R.id.etZhouQiShiJianSJ);
        etZhouQiShiJianMin = rootView.findViewById(R.id.etZhouQiShiJianMin);
        etZhouQiShiJianMax = rootView.findViewById(R.id.etZhouQiShiJianMax);
        etZhouQiShiJianTime = rootView.findViewById(R.id.etZhouQiShiJianTime);

        etZhouQiShiJianSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhouQiShiJianSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhouQiShiJianMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhouQiShiJianMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa1DuanSD = rootView.findViewById(R.id.etBaoYa1DuanSD);
        etBaoYa1DuanSJ = rootView.findViewById(R.id.etBaoYa1DuanSJ);
        etBaoYa1DuanMin = rootView.findViewById(R.id.etBaoYa1DuanMin);
        etBaoYa1DuanMax = rootView.findViewById(R.id.etBaoYa1DuanMax);
        etBaoYa1DuanTime = rootView.findViewById(R.id.etBaoYa1DuanTime);

        etBaoYa1DuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1DuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1DuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1DuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa2DuanSD = rootView.findViewById(R.id.etBaoYa2DuanSD);
        etBaoYa2DuanSJ = rootView.findViewById(R.id.etBaoYa2DuanSJ);
        etBaoYa2DuanMin = rootView.findViewById(R.id.etBaoYa2DuanMin);
        etBaoYa2DuanMax = rootView.findViewById(R.id.etBaoYa2DuanMax);
        etBaoYa2DuanTime = rootView.findViewById(R.id.etBaoYa2DuanTime);

        etBaoYa2DuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2DuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2DuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2DuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa3DuanSD = rootView.findViewById(R.id.etBaoYa3DuanSD);
        etBaoYa3DuanSJ = rootView.findViewById(R.id.etBaoYa3DuanSJ);
        etBaoYa3DuanMin = rootView.findViewById(R.id.etBaoYa3DuanMin);
        etBaoYa3DuanMax = rootView.findViewById(R.id.etBaoYa3DuanMax);
        etBaoYa3DuanTime = rootView.findViewById(R.id.etBaoYa3DuanTime);

        etBaoYa3DuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3DuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3DuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3DuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa1SuSD = rootView.findViewById(R.id.etBaoYa1SuSD);
        etBaoYa1SuSJ = rootView.findViewById(R.id.etBaoYa1SuSJ);
        etBaoYa1SuMin = rootView.findViewById(R.id.etBaoYa1SuMin);
        etBaoYa1SuMax = rootView.findViewById(R.id.etBaoYa1SuMax);
        etBaoYa1SuTime = rootView.findViewById(R.id.etBaoYa1SuTime);

        etBaoYa1SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa2SuSD = rootView.findViewById(R.id.etBaoYa2SuSD);
        etBaoYa2SuSJ = rootView.findViewById(R.id.etBaoYa2SuSJ);
        etBaoYa2SuMin = rootView.findViewById(R.id.etBaoYa2SuMin);
        etBaoYa2SuMax = rootView.findViewById(R.id.etBaoYa2SuMax);
        etBaoYa2SuTime = rootView.findViewById(R.id.etBaoYa2SuTime);

        etBaoYa2SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa3SuSD = rootView.findViewById(R.id.etBaoYa3SuSD);
        etBaoYa3SuSJ = rootView.findViewById(R.id.etBaoYa3SuSJ);
        etBaoYa3SuMin = rootView.findViewById(R.id.etBaoYa3SuMin);
        etBaoYa3SuMax = rootView.findViewById(R.id.etBaoYa3SuMax);
        etBaoYa3SuTime = rootView.findViewById(R.id.etBaoYa3SuTime);

        etBaoYa3SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYaShiJian1SD = rootView.findViewById(R.id.etBaoYaShiJian1SD);
        etBaoYaShiJian1SJ = rootView.findViewById(R.id.etBaoYaShiJian1SJ);
        etBaoYaShiJian1Min = rootView.findViewById(R.id.etBaoYaShiJian1Min);
        etBaoYaShiJian1Max = rootView.findViewById(R.id.etBaoYaShiJian1Max);
        etBaoYaShiJian1Time = rootView.findViewById(R.id.etBaoYaShiJian1Time);

        etBaoYaShiJian1SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian1SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian1Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian1Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYaShiJian2SD = rootView.findViewById(R.id.etBaoYaShiJian2SD);
        etBaoYaShiJian2SJ = rootView.findViewById(R.id.etBaoYaShiJian2SJ);
        etBaoYaShiJian2Min = rootView.findViewById(R.id.etBaoYaShiJian2Min);
        etBaoYaShiJian2Max = rootView.findViewById(R.id.etBaoYaShiJian2Max);
        etBaoYaShiJian2Time = rootView.findViewById(R.id.etBaoYaShiJian2Time);

        etBaoYaShiJian2SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian2SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian2Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian2Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYaShiJian3SD = rootView.findViewById(R.id.etBaoYaShiJian3SD);
        etBaoYaShiJian3SJ = rootView.findViewById(R.id.etBaoYaShiJian3SJ);
        etBaoYaShiJian3Min = rootView.findViewById(R.id.etBaoYaShiJian3Min);
        etBaoYaShiJian3Max = rootView.findViewById(R.id.etBaoYaShiJian3Max);
        etBaoYaShiJian3Time = rootView.findViewById(R.id.etBaoYaShiJian3Time);

        etBaoYaShiJian3SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian3SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian3Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian3Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etLuoGanZhuanSuSD = rootView.findViewById(R.id.etLuoGanZhuanSuSD);
        etLuoGanZhuanSuSJ = rootView.findViewById(R.id.etLuoGanZhuanSuSJ);
        etLuoGanZhuanSuMin = rootView.findViewById(R.id.etLuoGanZhuanSuMin);
        etLuoGanZhuanSuMax = rootView.findViewById(R.id.etLuoGanZhuanSuMax);
        etLuoGanZhuanSuTime = rootView.findViewById(R.id.etLuoGanZhuanSuTime);

        etLuoGanZhuanSuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLuoGanZhuanSuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLuoGanZhuanSuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLuoGanZhuanSuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBeiYaSD = rootView.findViewById(R.id.etBeiYaSD);
        etBeiYaSJ = rootView.findViewById(R.id.etBeiYaSJ);
        etBeiYaMin = rootView.findViewById(R.id.etBeiYaMin);
        etBeiYaMax = rootView.findViewById(R.id.etBeiYaMax);
        etBeiYaTime = rootView.findViewById(R.id.etBeiYaTime);

        etBeiYaSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBeiYaSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBeiYaMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBeiYaMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSongTuiWeiZhiSD = rootView.findViewById(R.id.etSongTuiWeiZhiSD);
        etSongTuiWeiZhiSJ = rootView.findViewById(R.id.etSongTuiWeiZhiSJ);
        etSongTuiWeiZhiMin = rootView.findViewById(R.id.etSongTuiWeiZhiMin);
        etSongTuiWeiZhiMax = rootView.findViewById(R.id.etSongTuiWeiZhiMax);
        etSongTuiWeiZhiTime = rootView.findViewById(R.id.etSongTuiWeiZhiTime);

        etSongTuiWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSongTuiWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSongTuiWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSongTuiWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etYongLiaoLiangSD = rootView.findViewById(R.id.etYongLiaoLiangSD);
        etYongLiaoLiangSJ = rootView.findViewById(R.id.etYongLiaoLiangSJ);
        etYongLiaoLiangMin = rootView.findViewById(R.id.etYongLiaoLiangMin);
        etYongLiaoLiangMax = rootView.findViewById(R.id.etYongLiaoLiangMax);
        etYongLiaoLiangTime = rootView.findViewById(R.id.etYongLiaoLiangTime);

        etYongLiaoLiangSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etYongLiaoLiangSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etYongLiaoLiangMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etYongLiaoLiangMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etHuanChongLiangSD = rootView.findViewById(R.id.etHuanChongLiangSD);
        etHuanChongLiangSJ = rootView.findViewById(R.id.etHuanChongLiangSJ);
        etHuanChongLiangMin = rootView.findViewById(R.id.etHuanChongLiangMin);
        etHuanChongLiangMax = rootView.findViewById(R.id.etHuanChongLiangMax);
        etHuanChongLiangTime = rootView.findViewById(R.id.etHuanChongLiangTime);

        etHuanChongLiangSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuanChongLiangSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuanChongLiangMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuanChongLiangMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etMuJuBaoHuYaLiSD = rootView.findViewById(R.id.etMuJuBaoHuYaLiSD);
        etMuJuBaoHuYaLiSJ = rootView.findViewById(R.id.etMuJuBaoHuYaLiSJ);
        etMuJuBaoHuYaLiMin = rootView.findViewById(R.id.etMuJuBaoHuYaLiMin);
        etMuJuBaoHuYaLiMax = rootView.findViewById(R.id.etMuJuBaoHuYaLiMax);
        etMuJuBaoHuYaLiTime = rootView.findViewById(R.id.etMuJuBaoHuYaLiTime);

        etMuJuBaoHuYaLiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuYaLiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuYaLiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuYaLiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etMuJuBaoHuWeiZhiSD = rootView.findViewById(R.id.etMuJuBaoHuWeiZhiSD);
        etMuJuBaoHuWeiZhiSJ = rootView.findViewById(R.id.etMuJuBaoHuWeiZhiSJ);
        etMuJuBaoHuWeiZhiMin = rootView.findViewById(R.id.etMuJuBaoHuWeiZhiMin);
        etMuJuBaoHuWeiZhiMax = rootView.findViewById(R.id.etMuJuBaoHuWeiZhiMax);
        etMuJuBaoHuWeiZhiTime = rootView.findViewById(R.id.etMuJuBaoHuWeiZhiTime);

        etMuJuBaoHuWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSuoMoLiSD = rootView.findViewById(R.id.etSuoMoLiSD);
        etSuoMoLiSJ = rootView.findViewById(R.id.etSuoMoLiSJ);
        etSuoMoLiMin = rootView.findViewById(R.id.etSuoMoLiMin);
        etSuoMoLiMax = rootView.findViewById(R.id.etSuoMoLiMax);
        etSuoMoLiTime = rootView.findViewById(R.id.etSuoMoLiTime);

        etSuoMoLiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSuoMoLiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSuoMoLiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSuoMoLiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etDingChuWeiZhiSD = rootView.findViewById(R.id.etDingChuWeiZhiSD);
        etDingChuWeiZhiSJ = rootView.findViewById(R.id.etDingChuWeiZhiSJ);
        etDingChuWeiZhiMin = rootView.findViewById(R.id.etDingChuWeiZhiMin);
        etDingChuWeiZhiMax = rootView.findViewById(R.id.etDingChuWeiZhiMax);
        etDingChuWeiZhiTime = rootView.findViewById(R.id.etDingChuWeiZhiTime);

        etDingChuWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingChuWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingChuWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingChuWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etDingMoWenDuSD = rootView.findViewById(R.id.etDingMoWenDuSD);
        etDingMoWenDuSJ = rootView.findViewById(R.id.etDingMoWenDuSJ);
        etDingMoWenDuMin = rootView.findViewById(R.id.etDingMoWenDuMin);
        etDingMoWenDuMax = rootView.findViewById(R.id.etDingMoWenDuMax);
        etDingMoWenDuTime = rootView.findViewById(R.id.etDingMoWenDuTime);

        etDingMoWenDuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingMoWenDuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingMoWenDuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingMoWenDuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etDongMoWenDuSD = rootView.findViewById(R.id.etDongMoWenDuSD);
        etDongMoWenDuSJ = rootView.findViewById(R.id.etDongMoWenDuSJ);
        etDongMoWenDuMin = rootView.findViewById(R.id.etDongMoWenDuMin);
        etDongMoWenDuMax = rootView.findViewById(R.id.etDongMoWenDuMax);
        etDongMoWenDuTime = rootView.findViewById(R.id.etDongMoWenDuTime);

        etDongMoWenDuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDongMoWenDuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDongMoWenDuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDongMoWenDuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etHongLiaoWenDuSD = rootView.findViewById(R.id.etHongLiaoWenDuSD);
        etHongLiaoWenDuSJ = rootView.findViewById(R.id.etHongLiaoWenDuSJ);
        etHongLiaoWenDuMin = rootView.findViewById(R.id.etHongLiaoWenDuMin);
        etHongLiaoWenDuMax = rootView.findViewById(R.id.etHongLiaoWenDuMax);
        etHongLiaoWenDuTime = rootView.findViewById(R.id.etHongLiaoWenDuTime);

        etHongLiaoWenDuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHongLiaoWenDuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHongLiaoWenDuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHongLiaoWenDuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etHuiLiaoBiLiSD = rootView.findViewById(R.id.etHuiLiaoBiLiSD);
        etHuiLiaoBiLiSJ = rootView.findViewById(R.id.etHuiLiaoBiLiSJ);
        etHuiLiaoBiLiMin = rootView.findViewById(R.id.etHuiLiaoBiLiMin);
        etHuiLiaoBiLiMax = rootView.findViewById(R.id.etHuiLiaoBiLiMax);
        etHuiLiaoBiLiTime = rootView.findViewById(R.id.etHuiLiaoBiLiTime);

        etHuiLiaoBiLiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuiLiaoBiLiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuiLiaoBiLiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuiLiaoBiLiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etQianJianShuLiangSD = rootView.findViewById(R.id.etQianJianShuLiangSD);
        etQianJianShuLiangSJ = rootView.findViewById(R.id.etQianJianShuLiangSJ);
        etQianJianShuLiangMin = rootView.findViewById(R.id.etQianJianShuLiangMin);
        etQianJianShuLiangMax = rootView.findViewById(R.id.etQianJianShuLiangMax);
        etQianJianShuLiangTime = rootView.findViewById(R.id.etQianJianShuLiangTime);

        etQianJianShuLiangSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianShuLiangSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianShuLiangMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianShuLiangMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etQianJianBianHaoSD = rootView.findViewById(R.id.etQianJianBianHaoSD);
        etQianJianBianHaoSJ = rootView.findViewById(R.id.etQianJianBianHaoSJ);
        etQianJianBianHaoMin = rootView.findViewById(R.id.etQianJianBianHaoMin);
        etQianJianBianHaoMax = rootView.findViewById(R.id.etQianJianBianHaoMax);
        etQianJianBianHaoTime = rootView.findViewById(R.id.etQianJianBianHaoTime);

        etQianJianBianHaoSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianBianHaoSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianBianHaoMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianBianHaoMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        btnNextStep.setOnClickListener(this);
    }

    private void showHistoryData() {
        try {
            Step13Resbean step13Resbean = MainApplication.daoSession.getStep13ResbeanDao().load(historyId);

            etJinLiaoDuanSD.setText(step13Resbean.getJinLiaoDuanSD());
            etJinLiaoDuanSJ.setText(step13Resbean.getJinLiaoDuanSJ());
            etJinLiaoDuanMin.setText(step13Resbean.getJinLiaoDuanMin());
            etJinLiaoDuanMax.setText(step13Resbean.getJinLiaoDuanMax());
            etJinLiaoDuanTime.setText(step13Resbean.getJinLiaoDuanTime());
            etJinLiaoDuanSD.setEnabled(false);
            etJinLiaoDuanSJ.setEnabled(false);
            etJinLiaoDuanMin.setEnabled(false);
            etJinLiaoDuanMax.setEnabled(false);
            etJinLiaoDuanTime.setEnabled(false);

            etHouDuanSD.setText(step13Resbean.getHouDuanSD());
            etHouDuanSJ.setText(step13Resbean.getHouDuanSJ());
            etHouDuanMin.setText(step13Resbean.getHouDuanMin());
            etHouDuanMax.setText(step13Resbean.getHouDuanMax());
            etHouDuanTime.setText(step13Resbean.getHouDuanTime());
            etHouDuanSD.setEnabled(false);
            etHouDuanSJ.setEnabled(false);
            etHouDuanMin.setEnabled(false);
            etHouDuanMax.setEnabled(false);
            etHouDuanTime.setEnabled(false);

            etZhongJianDuanSD.setText(step13Resbean.getZhongJianDuanSD());
            etZhongJianDuanSJ.setText(step13Resbean.getZhongJianDuanSJ());
            etZhongJianDuanMin.setText(step13Resbean.getZhongJianDuanMin());
            etZhongJianDuanMax.setText(step13Resbean.getZhongJianDuanMax());
            etZhongJianDuanTime.setText(step13Resbean.getZhongJianDuanTime());
            etZhongJianDuanSD.setEnabled(false);
            etZhongJianDuanSJ.setEnabled(false);
            etZhongJianDuanMin.setEnabled(false);
            etZhongJianDuanMax.setEnabled(false);
            etZhongJianDuanTime.setEnabled(false);

            etQianDuanSD.setText(step13Resbean.getQianDuanSD());
            etQianDuanSJ.setText(step13Resbean.getQianDuanSJ());
            etQianDuanMin.setText(step13Resbean.getQianDuanMin());
            etQianDuanMax.setText(step13Resbean.getQianDuanMax());
            etQianDuanTime.setText(step13Resbean.getQianDuanTime());
            etQianDuanSD.setEnabled(false);
            etQianDuanSJ.setEnabled(false);
            etQianDuanMin.setEnabled(false);
            etQianDuanMax.setEnabled(false);
            etQianDuanTime.setEnabled(false);

            etPenZuiSD.setText(step13Resbean.getPenZuiSD());
            etPenZuiSJ.setText(step13Resbean.getPenZuiSJ());
            etPenZuiMin.setText(step13Resbean.getPenZuiMin());
            etPenZuiMax.setText(step13Resbean.getPenZuiMax());
            etPenZuiTime.setText(step13Resbean.getPenZuiTime());
            etPenZuiSD.setEnabled(false);
            etPenZuiSJ.setEnabled(false);
            etPenZuiMin.setEnabled(false);
            etPenZuiMax.setEnabled(false);
            etPenZuiTime.setEnabled(false);

            etSheChu1SuSD.setText(step13Resbean.getSheChu1SuSD());
            etSheChu1SuSJ.setText(step13Resbean.getSheChu1SuSJ());
            etSheChu1SuMin.setText(step13Resbean.getSheChu1SuMin());
            etSheChu1SuMax.setText(step13Resbean.getSheChu1SuMax());
            etSheChu1SuTime.setText(step13Resbean.getSheChu1SuTime());
            etSheChu1SuSD.setEnabled(false);
            etSheChu1SuSJ.setEnabled(false);
            etSheChu1SuMin.setEnabled(false);
            etSheChu1SuMax.setEnabled(false);
            etSheChu1SuTime.setEnabled(false);

            etSheChu2SuSD.setText(step13Resbean.getSheChu2SuSD());
            etSheChu2SuSJ.setText(step13Resbean.getSheChu2SuSJ());
            etSheChu2SuMin.setText(step13Resbean.getSheChu2SuMin());
            etSheChu2SuMax.setText(step13Resbean.getSheChu2SuMax());
            etSheChu2SuTime.setText(step13Resbean.getSheChu2SuTime());
            etSheChu2SuSD.setEnabled(false);
            etSheChu2SuSJ.setEnabled(false);
            etSheChu2SuMin.setEnabled(false);
            etSheChu2SuMax.setEnabled(false);
            etSheChu2SuTime.setEnabled(false);

            etSheChu3SuSD.setText(step13Resbean.getSheChu3SuSD());
            etSheChu3SuSJ.setText(step13Resbean.getSheChu3SuSJ());
            etSheChu3SuMin.setText(step13Resbean.getSheChu3SuMin());
            etSheChu3SuMax.setText(step13Resbean.getSheChu3SuMax());
            etSheChu3SuTime.setText(step13Resbean.getSheChu3SuTime());
            etSheChu3SuSD.setEnabled(false);
            etSheChu3SuSJ.setEnabled(false);
            etSheChu3SuMin.setEnabled(false);
            etSheChu3SuMax.setEnabled(false);
            etSheChu3SuTime.setEnabled(false);

            etSheChu4SuSD.setText(step13Resbean.getSheChu4SuSD());
            etSheChu4SuSJ.setText(step13Resbean.getSheChu4SuSJ());
            etSheChu4SuMin.setText(step13Resbean.getSheChu4SuMin());
            etSheChu4SuMax.setText(step13Resbean.getSheChu4SuMax());
            etSheChu4SuTime.setText(step13Resbean.getSheChu4SuTime());
            etSheChu4SuSD.setEnabled(false);
            etSheChu4SuSJ.setEnabled(false);
            etSheChu4SuMin.setEnabled(false);
            etSheChu4SuMax.setEnabled(false);
            etSheChu4SuTime.setEnabled(false);

            et1DuanWeiZhiSD.setText(step13Resbean.getEt1DuanWeiZhiSD());
            et1DuanWeiZhiSJ.setText(step13Resbean.getEt1DuanWeiZhiSJ());
            et1DuanWeiZhiMin.setText(step13Resbean.getEt1DuanWeiZhiMin());
            et1DuanWeiZhiMax.setText(step13Resbean.getEt1DuanWeiZhiMax());
            et1DuanWeiZhiTime.setText(step13Resbean.getEt1DuanWeiZhiTime());
            et1DuanWeiZhiSD.setEnabled(false);
            et1DuanWeiZhiSJ.setEnabled(false);
            et1DuanWeiZhiMin.setEnabled(false);
            et1DuanWeiZhiMax.setEnabled(false);
            et1DuanWeiZhiTime.setEnabled(false);

            et2DuanWeiZhiSD.setText(step13Resbean.getEt2DuanWeiZhiSD());
            et2DuanWeiZhiSJ.setText(step13Resbean.getEt2DuanWeiZhiSJ());
            et2DuanWeiZhiMin.setText(step13Resbean.getEt2DuanWeiZhiMin());
            et2DuanWeiZhiMax.setText(step13Resbean.getEt2DuanWeiZhiMax());
            et2DuanWeiZhiTime.setText(step13Resbean.getEt2DuanWeiZhiTime());
            et2DuanWeiZhiSD.setEnabled(false);
            et2DuanWeiZhiSJ.setEnabled(false);
            et2DuanWeiZhiMin.setEnabled(false);
            et2DuanWeiZhiMax.setEnabled(false);
            et2DuanWeiZhiTime.setEnabled(false);

            et3DuanWeiZhiSD.setText(step13Resbean.getEt3DuanWeiZhiSD());
            et3DuanWeiZhiSJ.setText(step13Resbean.getEt3DuanWeiZhiSJ());
            et3DuanWeiZhiMin.setText(step13Resbean.getEt3DuanWeiZhiMin());
            et3DuanWeiZhiMax.setText(step13Resbean.getEt3DuanWeiZhiMax());
            et3DuanWeiZhiTime.setText(step13Resbean.getEt3DuanWeiZhiTime());
            et3DuanWeiZhiSD.setEnabled(false);
            et3DuanWeiZhiSJ.setEnabled(false);
            et3DuanWeiZhiMin.setEnabled(false);
            et3DuanWeiZhiMax.setEnabled(false);
            et3DuanWeiZhiTime.setEnabled(false);

            et4DuanWeiZhiSD.setText(step13Resbean.getEt4DuanWeiZhiSD());
            et4DuanWeiZhiSJ.setText(step13Resbean.getEt4DuanWeiZhiSJ());
            et4DuanWeiZhiMin.setText(step13Resbean.getEt4DuanWeiZhiMin());
            et4DuanWeiZhiMax.setText(step13Resbean.getEt4DuanWeiZhiMax());
            et4DuanWeiZhiTime.setText(step13Resbean.getEt4DuanWeiZhiTime());
            et4DuanWeiZhiSD.setEnabled(false);
            et4DuanWeiZhiSJ.setEnabled(false);
            et4DuanWeiZhiMin.setEnabled(false);
            et4DuanWeiZhiMax.setEnabled(false);
            et4DuanWeiZhiTime.setEnabled(false);

            etVPWeiZhiSD.setText(step13Resbean.getVPWeiZhiSD());
            etVPWeiZhiSJ.setText(step13Resbean.getVPWeiZhiSJ());
            etVPWeiZhiMin.setText(step13Resbean.getVPWeiZhiMin());
            etVPWeiZhiMax.setText(step13Resbean.getVPWeiZhiMax());
            etVPWeiZhiTime.setText(step13Resbean.getVPWeiZhiTime());
            etVPWeiZhiSD.setEnabled(false);
            etVPWeiZhiSJ.setEnabled(false);
            etVPWeiZhiMin.setEnabled(false);
            etVPWeiZhiMax.setEnabled(false);
            etVPWeiZhiTime.setEnabled(false);

            etSheChuYaLi1SD.setText(step13Resbean.getSheChuYaLi1SD());
            etSheChuYaLi1SJ.setText(step13Resbean.getSheChuYaLi1SJ());
            etSheChuYaLi1Min.setText(step13Resbean.getSheChuYaLi1Min());
            etSheChuYaLi1Max.setText(step13Resbean.getSheChuYaLi1Max());
            etSheChuYaLi1Time.setText(step13Resbean.getSheChuYaLi1Time());
            etSheChuYaLi1SD.setEnabled(false);
            etSheChuYaLi1SJ.setEnabled(false);
            etSheChuYaLi1Min.setEnabled(false);
            etSheChuYaLi1Max.setEnabled(false);
            etSheChuYaLi1Time.setEnabled(false);

            etSheChuYaLi2SD.setText(step13Resbean.getSheChuYaLi2SD());
            etSheChuYaLi2SJ.setText(step13Resbean.getSheChuYaLi2SJ());
            etSheChuYaLi2Min.setText(step13Resbean.getSheChuYaLi2Min());
            etSheChuYaLi2Max.setText(step13Resbean.getSheChuYaLi2Max());
            etSheChuYaLi2Time.setText(step13Resbean.getSheChuYaLi2Time());
            etSheChuYaLi2SD.setEnabled(false);
            etSheChuYaLi2SJ.setEnabled(false);
            etSheChuYaLi2Min.setEnabled(false);
            etSheChuYaLi2Max.setEnabled(false);
            etSheChuYaLi2Time.setEnabled(false);

            etSheChuYaLi3SD.setText(step13Resbean.getSheChuYaLi3SD());
            etSheChuYaLi3SJ.setText(step13Resbean.getSheChuYaLi3SJ());
            etSheChuYaLi3Min.setText(step13Resbean.getSheChuYaLi3Min());
            etSheChuYaLi3Max.setText(step13Resbean.getSheChuYaLi3Max());
            etSheChuYaLi3Time.setText(step13Resbean.getSheChuYaLi3Time());
            etSheChuYaLi3SD.setEnabled(false);
            etSheChuYaLi3SJ.setEnabled(false);
            etSheChuYaLi3Min.setEnabled(false);
            etSheChuYaLi3Max.setEnabled(false);
            etSheChuYaLi3Time.setEnabled(false);

            etSheChuYaLi4SD.setText(step13Resbean.getSheChuYaLi4SD());
            etSheChuYaLi4SJ.setText(step13Resbean.getSheChuYaLi4SJ());
            etSheChuYaLi4Min.setText(step13Resbean.getSheChuYaLi4Min());
            etSheChuYaLi4Max.setText(step13Resbean.getSheChuYaLi4Max());
            etSheChuYaLi4Time.setText(step13Resbean.getSheChuYaLi4Time());
            etSheChuYaLi4SD.setEnabled(false);
            etSheChuYaLi4SJ.setEnabled(false);
            etSheChuYaLi4Min.setEnabled(false);
            etSheChuYaLi4Max.setEnabled(false);
            etSheChuYaLi4Time.setEnabled(false);

            etLengQueShiJianSD.setText(step13Resbean.getLengQueShiJianSD());
            etLengQueShiJianSJ.setText(step13Resbean.getLengQueShiJianSJ());
            etLengQueShiJianMin.setText(step13Resbean.getLengQueShiJianMin());
            etLengQueShiJianMax.setText(step13Resbean.getLengQueShiJianMax());
            etLengQueShiJianTime.setText(step13Resbean.getLengQueShiJianTime());
            etLengQueShiJianSD.setEnabled(false);
            etLengQueShiJianSJ.setEnabled(false);
            etLengQueShiJianMin.setEnabled(false);
            etLengQueShiJianMax.setEnabled(false);
            etLengQueShiJianTime.setEnabled(false);

            etSheChuShiJianSD.setText(step13Resbean.getSheChuShiJianSD());
            etSheChuShiJianSJ.setText(step13Resbean.getSheChuShiJianSJ());
            etSheChuShiJianMin.setText(step13Resbean.getSheChuShiJianMin());
            etSheChuShiJianMax.setText(step13Resbean.getSheChuShiJianMax());
            etSheChuShiJianTime.setText(step13Resbean.getSheChuShiJianTime());
            etSheChuShiJianSD.setEnabled(false);
            etSheChuShiJianSJ.setEnabled(false);
            etSheChuShiJianMin.setEnabled(false);
            etSheChuShiJianMax.setEnabled(false);
            etSheChuShiJianTime.setEnabled(false);

            etZhouQiShiJianSD.setText(step13Resbean.getZhouQiShiJianSD());
            etZhouQiShiJianSJ.setText(step13Resbean.getZhouQiShiJianSJ());
            etZhouQiShiJianMin.setText(step13Resbean.getZhouQiShiJianMin());
            etZhouQiShiJianMax.setText(step13Resbean.getZhouQiShiJianMax());
            etZhouQiShiJianTime.setText(step13Resbean.getZhouQiShiJianTime());
            etZhouQiShiJianSD.setEnabled(false);
            etZhouQiShiJianSJ.setEnabled(false);
            etZhouQiShiJianMin.setEnabled(false);
            etZhouQiShiJianMax.setEnabled(false);
            etZhouQiShiJianTime.setEnabled(false);

            etBaoYa1DuanSD.setText(step13Resbean.getBaoYa2DuanSD());
            etBaoYa1DuanSJ.setText(step13Resbean.getBaoYa2DuanSJ());
            etBaoYa1DuanMin.setText(step13Resbean.getBaoYa2DuanMin());
            etBaoYa1DuanMax.setText(step13Resbean.getBaoYa2DuanMax());
            etBaoYa1DuanTime.setText(step13Resbean.getBaoYa2DuanTime());
            etBaoYa1DuanSD.setEnabled(false);
            etBaoYa1DuanSJ.setEnabled(false);
            etBaoYa1DuanMin.setEnabled(false);
            etBaoYa1DuanMax.setEnabled(false);
            etBaoYa1DuanTime.setEnabled(false);

            etBaoYa2DuanSD.setText(step13Resbean.getBaoYa2DuanSD());
            etBaoYa2DuanSJ.setText(step13Resbean.getBaoYa2DuanSJ());
            etBaoYa2DuanMin.setText(step13Resbean.getBaoYa2DuanMin());
            etBaoYa2DuanMax.setText(step13Resbean.getBaoYa2DuanMax());
            etBaoYa2DuanTime.setText(step13Resbean.getBaoYa2DuanTime());
            etBaoYa2DuanSD.setEnabled(false);
            etBaoYa2DuanSJ.setEnabled(false);
            etBaoYa2DuanMin.setEnabled(false);
            etBaoYa2DuanMax.setEnabled(false);
            etBaoYa2DuanTime.setEnabled(false);

            etBaoYa3DuanSD.setText(step13Resbean.getBaoYa3DuanSD());
            etBaoYa3DuanSJ.setText(step13Resbean.getBaoYa3DuanSJ());
            etBaoYa3DuanMin.setText(step13Resbean.getBaoYa3DuanMin());
            etBaoYa3DuanMax.setText(step13Resbean.getBaoYa3DuanMax());
            etBaoYa3DuanTime.setText(step13Resbean.getBaoYa3DuanTime());
            etBaoYa3DuanTime.setEnabled(false);
            etBaoYa3DuanSJ.setEnabled(false);
            etBaoYa3DuanMin.setEnabled(false);
            etBaoYa3DuanMax.setEnabled(false);
            etBaoYa3DuanTime.setEnabled(false);

            etBaoYa1SuSD.setText(step13Resbean.getBaoYa2SuSD());
            etBaoYa1SuSJ.setText(step13Resbean.getBaoYa2SuSJ());
            etBaoYa1SuMin.setText(step13Resbean.getBaoYa2SuMin());
            etBaoYa1SuMax.setText(step13Resbean.getBaoYa2SuMax());
            etBaoYa1SuTime.setText(step13Resbean.getBaoYa2SuTime());
            etBaoYa1SuSD.setEnabled(false);
            etBaoYa1SuSJ.setEnabled(false);
            etBaoYa1SuMin.setEnabled(false);
            etBaoYa1SuMax.setEnabled(false);
            etBaoYa1SuTime.setEnabled(false);

            etBaoYa2SuSD.setText(step13Resbean.getBaoYa2SuSD());
            etBaoYa2SuSJ.setText(step13Resbean.getBaoYa2SuSJ());
            etBaoYa2SuMin.setText(step13Resbean.getBaoYa2SuMin());
            etBaoYa2SuMax.setText(step13Resbean.getBaoYa2SuMax());
            etBaoYa2SuTime.setText(step13Resbean.getBaoYa2SuTime());
            etBaoYa2SuSD.setEnabled(false);
            etBaoYa2SuSJ.setEnabled(false);
            etBaoYa2SuMin.setEnabled(false);
            etBaoYa2SuMax.setEnabled(false);
            etBaoYa2SuTime.setEnabled(false);

            etBaoYa3SuSD.setText(step13Resbean.getBaoYa3SuSD());
            etBaoYa3SuSJ.setText(step13Resbean.getBaoYa3SuSJ());
            etBaoYa3SuMin.setText(step13Resbean.getBaoYa3SuMin());
            etBaoYa3SuMax.setText(step13Resbean.getBaoYa3SuMax());
            etBaoYa3SuTime.setText(step13Resbean.getBaoYa3SuTime());
            etBaoYa3SuSD.setEnabled(false);
            etBaoYa3SuSJ.setEnabled(false);
            etBaoYa3SuMin.setEnabled(false);
            etBaoYa3SuMax.setEnabled(false);
            etBaoYa3SuTime.setEnabled(false);

            etBaoYaShiJian1SD.setText(step13Resbean.getBaoYaShiJian1SD());
            etBaoYaShiJian1SJ.setText(step13Resbean.getBaoYaShiJian1SJ());
            etBaoYaShiJian1Min.setText(step13Resbean.getBaoYaShiJian1Min());
            etBaoYaShiJian1Max.setText(step13Resbean.getBaoYaShiJian1Max());
            etBaoYaShiJian1Time.setText(step13Resbean.getBaoYaShiJian1Time());
            etBaoYaShiJian1SD.setEnabled(false);
            etBaoYaShiJian1SJ.setEnabled(false);
            etBaoYaShiJian1Min.setEnabled(false);
            etBaoYaShiJian1Max.setEnabled(false);
            etBaoYaShiJian1Time.setEnabled(false);

            etBaoYaShiJian2SD.setText(step13Resbean.getBaoYaShiJian2SD());
            etBaoYaShiJian2SJ.setText(step13Resbean.getBaoYaShiJian2SJ());
            etBaoYaShiJian2Min.setText(step13Resbean.getBaoYaShiJian2Min());
            etBaoYaShiJian2Max.setText(step13Resbean.getBaoYaShiJian2Max());
            etBaoYaShiJian2Time.setText(step13Resbean.getBaoYaShiJian2Time());
            etBaoYaShiJian2SD.setEnabled(false);
            etBaoYaShiJian2SJ.setEnabled(false);
            etBaoYaShiJian2Min.setEnabled(false);
            etBaoYaShiJian2Max.setEnabled(false);
            etBaoYaShiJian2Time.setEnabled(false);

            etBaoYaShiJian3SD.setText(step13Resbean.getBaoYaShiJian3SD());
            etBaoYaShiJian3SJ.setText(step13Resbean.getBaoYaShiJian3SJ());
            etBaoYaShiJian3Min.setText(step13Resbean.getBaoYaShiJian3Min());
            etBaoYaShiJian3Max.setText(step13Resbean.getBaoYaShiJian3Max());
            etBaoYaShiJian3Time.setText(step13Resbean.getBaoYaShiJian3Time());
            etBaoYaShiJian3SD.setEnabled(false);
            etBaoYaShiJian3SJ.setEnabled(false);
            etBaoYaShiJian3Min.setEnabled(false);
            etBaoYaShiJian3Max.setEnabled(false);
            etBaoYaShiJian3Time.setEnabled(false);

            etLuoGanZhuanSuSD.setText(step13Resbean.getLuoGanZhuanSuSD());
            etLuoGanZhuanSuSJ.setText(step13Resbean.getLuoGanZhuanSuSJ());
            etLuoGanZhuanSuMin.setText(step13Resbean.getLuoGanZhuanSuMin());
            etLuoGanZhuanSuMax.setText(step13Resbean.getLuoGanZhuanSuMax());
            etLuoGanZhuanSuTime.setText(step13Resbean.getLuoGanZhuanSuTime());
            etLuoGanZhuanSuSD.setEnabled(false);
            etLuoGanZhuanSuSJ.setEnabled(false);
            etLuoGanZhuanSuMin.setEnabled(false);
            etLuoGanZhuanSuMax.setEnabled(false);
            etLuoGanZhuanSuTime.setEnabled(false);

            etBeiYaSD.setText(step13Resbean.getBeiYaSD());
            etBeiYaSJ.setText(step13Resbean.getBeiYaSJ());
            etBeiYaMin.setText(step13Resbean.getBeiYaMin());
            etBeiYaMax.setText(step13Resbean.getBeiYaMax());
            etBeiYaTime.setText(step13Resbean.getBeiYaTime());
            etBeiYaSD.setEnabled(false);
            etBeiYaSJ.setEnabled(false);
            etBeiYaMin.setEnabled(false);
            etBeiYaMax.setEnabled(false);
            etBeiYaTime.setEnabled(false);

            etSongTuiWeiZhiSD.setText(step13Resbean.getSongTuiWeiZhiSD());
            etSongTuiWeiZhiSJ.setText(step13Resbean.getSongTuiWeiZhiSJ());
            etSongTuiWeiZhiMin.setText(step13Resbean.getSongTuiWeiZhiMin());
            etSongTuiWeiZhiMax.setText(step13Resbean.getSongTuiWeiZhiMax());
            etSongTuiWeiZhiTime.setText(step13Resbean.getSongTuiWeiZhiTime());
            etSongTuiWeiZhiSD.setEnabled(false);
            etSongTuiWeiZhiSJ.setEnabled(false);
            etSongTuiWeiZhiMin.setEnabled(false);
            etSongTuiWeiZhiMax.setEnabled(false);
            etSongTuiWeiZhiTime.setEnabled(false);

            etYongLiaoLiangSD.setText(step13Resbean.getYongLiaoLiangSD());
            etYongLiaoLiangSJ.setText(step13Resbean.getYongLiaoLiangSJ());
            etYongLiaoLiangMin.setText(step13Resbean.getYongLiaoLiangMin());
            etYongLiaoLiangMax.setText(step13Resbean.getYongLiaoLiangMax());
            etYongLiaoLiangTime.setText(step13Resbean.getYongLiaoLiangTime());
            etYongLiaoLiangSD.setEnabled(false);
            etYongLiaoLiangSJ.setEnabled(false);
            etYongLiaoLiangMin.setEnabled(false);
            etYongLiaoLiangMax.setEnabled(false);
            etYongLiaoLiangTime.setEnabled(false);

            etHuanChongLiangSD.setText(step13Resbean.getHuanChongLiangSD());
            etHuanChongLiangSJ.setText(step13Resbean.getHuanChongLiangSJ());
            etHuanChongLiangMin.setText(step13Resbean.getHuanChongLiangMin());
            etHuanChongLiangMax.setText(step13Resbean.getHuanChongLiangMax());
            etHuanChongLiangTime.setText(step13Resbean.getHuanChongLiangTime());
            etHuanChongLiangSD.setEnabled(false);
            etHuanChongLiangSJ.setEnabled(false);
            etHuanChongLiangMin.setEnabled(false);
            etHuanChongLiangMax.setEnabled(false);
            etHuanChongLiangTime.setEnabled(false);

            etMuJuBaoHuYaLiSD.setText(step13Resbean.getMuJuBaoHuYaLiSD());
            etMuJuBaoHuYaLiSJ.setText(step13Resbean.getMuJuBaoHuYaLiSJ());
            etMuJuBaoHuYaLiMin.setText(step13Resbean.getMuJuBaoHuYaLiMin());
            etMuJuBaoHuYaLiMax.setText(step13Resbean.getMuJuBaoHuYaLiMax());
            etMuJuBaoHuYaLiTime.setText(step13Resbean.getMuJuBaoHuYaLiTime());
            etMuJuBaoHuYaLiSD.setEnabled(false);
            etMuJuBaoHuYaLiSJ.setEnabled(false);
            etMuJuBaoHuYaLiMin.setEnabled(false);
            etMuJuBaoHuYaLiMax.setEnabled(false);
            etMuJuBaoHuYaLiTime.setEnabled(false);

            etMuJuBaoHuWeiZhiSD.setText(step13Resbean.getMuJuBaoHuWeiZhiSD());
            etMuJuBaoHuWeiZhiSJ.setText(step13Resbean.getMuJuBaoHuWeiZhiSJ());
            etMuJuBaoHuWeiZhiMin.setText(step13Resbean.getMuJuBaoHuWeiZhiMin());
            etMuJuBaoHuWeiZhiMax.setText(step13Resbean.getMuJuBaoHuWeiZhiMax());
            etMuJuBaoHuWeiZhiTime.setText(step13Resbean.getMuJuBaoHuWeiZhiTime());
            etMuJuBaoHuWeiZhiSD.setEnabled(false);
            etMuJuBaoHuWeiZhiSJ.setEnabled(false);
            etMuJuBaoHuWeiZhiMin.setEnabled(false);
            etMuJuBaoHuWeiZhiMax.setEnabled(false);
            etMuJuBaoHuWeiZhiTime.setEnabled(false);

            etSuoMoLiSD.setText(step13Resbean.getSuoMoLiSD());
            etSuoMoLiSJ.setText(step13Resbean.getSuoMoLiSJ());
            etSuoMoLiMin.setText(step13Resbean.getSuoMoLiMin());
            etSuoMoLiMax.setText(step13Resbean.getSuoMoLiMax());
            etSuoMoLiTime.setText(step13Resbean.getSuoMoLiTime());
            etSuoMoLiSD.setEnabled(false);
            etSuoMoLiSJ.setEnabled(false);
            etSuoMoLiMin.setEnabled(false);
            etSuoMoLiMax.setEnabled(false);
            etSuoMoLiTime.setEnabled(false);

            etDingChuWeiZhiSD.setText(step13Resbean.getDingChuWeiZhiSD());
            etDingChuWeiZhiSJ.setText(step13Resbean.getDingChuWeiZhiSJ());
            etDingChuWeiZhiMin.setText(step13Resbean.getDingChuWeiZhiMin());
            etDingChuWeiZhiMax.setText(step13Resbean.getDingChuWeiZhiMax());
            etDingChuWeiZhiTime.setText(step13Resbean.getDingChuWeiZhiTime());
            etDingChuWeiZhiSD.setEnabled(false);
            etDingChuWeiZhiSJ.setEnabled(false);
            etDingChuWeiZhiMin.setEnabled(false);
            etDingChuWeiZhiMax.setEnabled(false);
            etDingChuWeiZhiTime.setEnabled(false);

            etDingMoWenDuSD.setText(step13Resbean.getDingMoWenDuSD());
            etDingMoWenDuSJ.setText(step13Resbean.getDingMoWenDuSJ());
            etDingMoWenDuMin.setText(step13Resbean.getDingMoWenDuMin());
            etDingMoWenDuMax.setText(step13Resbean.getDingMoWenDuMax());
            etDingMoWenDuTime.setText(step13Resbean.getDingMoWenDuTime());
            etDingMoWenDuSD.setEnabled(false);
            etDingMoWenDuSJ.setEnabled(false);
            etDingMoWenDuMin.setEnabled(false);
            etDingMoWenDuMax.setEnabled(false);
            etDingMoWenDuTime.setEnabled(false);

            etDongMoWenDuSD.setText(step13Resbean.getDongMoWenDuSD());
            etDongMoWenDuSJ.setText(step13Resbean.getDongMoWenDuSJ());
            etDongMoWenDuMin.setText(step13Resbean.getDongMoWenDuMin());
            etDongMoWenDuMax.setText(step13Resbean.getDongMoWenDuMax());
            etDongMoWenDuTime.setText(step13Resbean.getDongMoWenDuTime());
            etDongMoWenDuSD.setEnabled(false);
            etDongMoWenDuSJ.setEnabled(false);
            etDongMoWenDuMin.setEnabled(false);
            etDongMoWenDuMax.setEnabled(false);
            etDongMoWenDuTime.setEnabled(false);

            etHongLiaoWenDuSD.setText(step13Resbean.getHongLiaoWenDuSD());
            etHongLiaoWenDuSJ.setText(step13Resbean.getHongLiaoWenDuSJ());
            etHongLiaoWenDuMin.setText(step13Resbean.getHongLiaoWenDuMin());
            etHongLiaoWenDuMax.setText(step13Resbean.getHongLiaoWenDuMax());
            etHongLiaoWenDuTime.setText(step13Resbean.getHongLiaoWenDuTime());
            etHongLiaoWenDuSD.setEnabled(false);
            etHongLiaoWenDuSJ.setEnabled(false);
            etHongLiaoWenDuMin.setEnabled(false);
            etHongLiaoWenDuMax.setEnabled(false);
            etHongLiaoWenDuTime.setEnabled(false);

            etHuiLiaoBiLiSD.setText(step13Resbean.getHuiLiaoBiLiSD());
            etHuiLiaoBiLiSJ.setText(step13Resbean.getHuiLiaoBiLiSJ());
            etHuiLiaoBiLiMin.setText(step13Resbean.getHuiLiaoBiLiMin());
            etHuiLiaoBiLiMax.setText(step13Resbean.getHuiLiaoBiLiMax());
            etHuiLiaoBiLiTime.setText(step13Resbean.getHuiLiaoBiLiTime());
            etHuiLiaoBiLiSD.setEnabled(false);
            etHuiLiaoBiLiSJ.setEnabled(false);
            etHuiLiaoBiLiMin.setEnabled(false);
            etHuiLiaoBiLiMax.setEnabled(false);
            etHuiLiaoBiLiTime.setEnabled(false);

            etQianJianShuLiangSD.setText(step13Resbean.getHuiLiaoBiLiSD());
            etQianJianShuLiangSJ.setText(step13Resbean.getHuiLiaoBiLiSJ());
            etQianJianShuLiangMin.setText(step13Resbean.getHuiLiaoBiLiMin());
            etQianJianShuLiangMax.setText(step13Resbean.getHuiLiaoBiLiMax());
            etQianJianShuLiangTime.setText(step13Resbean.getHuiLiaoBiLiTime());
            etQianJianShuLiangSD.setEnabled(false);
            etQianJianShuLiangSJ.setEnabled(false);
            etQianJianShuLiangMin.setEnabled(false);
            etQianJianShuLiangMax.setEnabled(false);
            etQianJianShuLiangTime.setEnabled(false);

            etQianJianBianHaoSD.setText(step13Resbean.getQianJianBianHaoSD());
            etQianJianBianHaoSJ.setText(step13Resbean.getQianJianBianHaoSJ());
            etQianJianBianHaoMin.setText(step13Resbean.getQianJianBianHaoMin());
            etQianJianBianHaoMax.setText(step13Resbean.getQianJianBianHaoMax());
            etQianJianBianHaoTime.setText(step13Resbean.getQianJianBianHaoTime());
            etQianJianBianHaoSD.setEnabled(false);
            etQianJianBianHaoSJ.setEnabled(false);
            etQianJianBianHaoMin.setEnabled(false);
            etQianJianBianHaoMax.setEnabled(false);
            etQianJianBianHaoTime.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        step13Resbean = new Step13Resbean();
        presenter = new Step13Presenter(this);
        if (historyId != 0 && isHistory == true) {
            showHistoryData();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                if (Constants.isNeedTest && !isHistory) {
                    setData2Resbean();
                    boolean isHave = presenter.checkDataIsHaveNull(step13Resbean);
                    if (isHave) {
                        return;
                    }
                    try {
                        step13Resbean.setStep13Id(MainApplication.thisTimeId);
                        step13Resbean.setCurrentStepIsChecked(true);
                        Step13ResbeanDao step13ResbeanDao = MainApplication.daoSession.getStep13ResbeanDao();
                        step13ResbeanDao.insert(step13Resbean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        TestUtil.getAllDbDatas();
                    }
                }
                ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
                break;
        }
    }

    private void setData2Resbean() {
        step13Resbean.setJinLiaoDuanSD(etJinLiaoDuanSD.getText().toString());
        step13Resbean.setJinLiaoDuanSJ(etJinLiaoDuanSJ.getText().toString());
        step13Resbean.setJinLiaoDuanMin(etJinLiaoDuanMin.getText().toString());
        step13Resbean.setJinLiaoDuanMax(etJinLiaoDuanMax.getText().toString());
        step13Resbean.setJinLiaoDuanTime(etJinLiaoDuanTime.getText().toString());

        step13Resbean.setHouDuanSD(etHouDuanSD.getText().toString());
        step13Resbean.setHouDuanSJ(etHouDuanSJ.getText().toString());
        step13Resbean.setHouDuanMin(etHouDuanMin.getText().toString());
        step13Resbean.setHouDuanMax(etHouDuanMax.getText().toString());
        step13Resbean.setHouDuanTime(etHouDuanTime.getText().toString());

        step13Resbean.setZhongJianDuanSD(etZhongJianDuanSD.getText().toString());
        step13Resbean.setZhongJianDuanSJ(etZhongJianDuanSJ.getText().toString());
        step13Resbean.setZhongJianDuanMin(etZhongJianDuanMin.getText().toString());
        step13Resbean.setZhongJianDuanMax(etZhongJianDuanMax.getText().toString());
        step13Resbean.setZhongJianDuanTime(etZhongJianDuanTime.getText().toString());

        step13Resbean.setQianDuanSD(etQianDuanSD.getText().toString());
        step13Resbean.setQianDuanSJ(etQianDuanSJ.getText().toString());
        step13Resbean.setQianDuanMin(etQianDuanMin.getText().toString());
        step13Resbean.setQianDuanMax(etQianDuanMax.getText().toString());
        step13Resbean.setQianDuanTime(etQianDuanTime.getText().toString());

        step13Resbean.setPenZuiSD(etPenZuiSD.getText().toString());
        step13Resbean.setPenZuiSJ(etPenZuiSJ.getText().toString());
        step13Resbean.setPenZuiMin(etPenZuiMin.getText().toString());
        step13Resbean.setPenZuiMax(etPenZuiMax.getText().toString());
        step13Resbean.setPenZuiTime(etPenZuiTime.getText().toString());

        step13Resbean.setSheChu1SuSD(etSheChu1SuSD.getText().toString());
        step13Resbean.setSheChu1SuSJ(etSheChu1SuSJ.getText().toString());
        step13Resbean.setSheChu1SuMin(etSheChu1SuMin.getText().toString());
        step13Resbean.setSheChu1SuMax(etSheChu1SuMax.getText().toString());
        step13Resbean.setSheChu1SuTime(etSheChu1SuTime.getText().toString());

        step13Resbean.setSheChu2SuSD(etSheChu2SuSD.getText().toString());
        step13Resbean.setSheChu2SuSJ(etSheChu2SuSJ.getText().toString());
        step13Resbean.setSheChu2SuMin(etSheChu2SuMin.getText().toString());
        step13Resbean.setSheChu2SuMax(etSheChu2SuMax.getText().toString());
        step13Resbean.setSheChu2SuTime(etSheChu2SuTime.getText().toString());

        step13Resbean.setSheChu3SuSD(etSheChu3SuSD.getText().toString());
        step13Resbean.setSheChu3SuSJ(etSheChu3SuSJ.getText().toString());
        step13Resbean.setSheChu3SuMin(etSheChu3SuMin.getText().toString());
        step13Resbean.setSheChu3SuMax(etSheChu3SuMax.getText().toString());
        step13Resbean.setSheChu3SuTime(etSheChu3SuTime.getText().toString());

        step13Resbean.setSheChu4SuSD(etSheChu4SuSD.getText().toString());
        step13Resbean.setSheChu4SuSJ(etSheChu4SuSJ.getText().toString());
        step13Resbean.setSheChu4SuMin(etSheChu4SuMin.getText().toString());
        step13Resbean.setSheChu4SuMax(etSheChu4SuMax.getText().toString());
        step13Resbean.setSheChu4SuTime(etSheChu4SuTime.getText().toString());

        step13Resbean.setEt1DuanWeiZhiSD(et1DuanWeiZhiSD.getText().toString());
        step13Resbean.setEt1DuanWeiZhiSJ(et1DuanWeiZhiSJ.getText().toString());
        step13Resbean.setEt1DuanWeiZhiMin(et1DuanWeiZhiMin.getText().toString());
        step13Resbean.setEt1DuanWeiZhiMax(et1DuanWeiZhiMax.getText().toString());
        step13Resbean.setEt1DuanWeiZhiTime(et1DuanWeiZhiTime.getText().toString());

        step13Resbean.setEt2DuanWeiZhiSD(et2DuanWeiZhiSD.getText().toString());
        step13Resbean.setEt2DuanWeiZhiSJ(et2DuanWeiZhiSJ.getText().toString());
        step13Resbean.setEt2DuanWeiZhiMin(et2DuanWeiZhiMin.getText().toString());
        step13Resbean.setEt2DuanWeiZhiMax(et2DuanWeiZhiMax.getText().toString());
        step13Resbean.setEt2DuanWeiZhiTime(et2DuanWeiZhiTime.getText().toString());

        step13Resbean.setEt3DuanWeiZhiSD(et3DuanWeiZhiSD.getText().toString());
        step13Resbean.setEt3DuanWeiZhiSJ(et3DuanWeiZhiSJ.getText().toString());
        step13Resbean.setEt3DuanWeiZhiMin(et3DuanWeiZhiMin.getText().toString());
        step13Resbean.setEt3DuanWeiZhiMax(et3DuanWeiZhiMax.getText().toString());
        step13Resbean.setEt3DuanWeiZhiTime(et3DuanWeiZhiTime.getText().toString());

        step13Resbean.setEt4DuanWeiZhiSD(et4DuanWeiZhiSD.getText().toString());
        step13Resbean.setEt4DuanWeiZhiSJ(et4DuanWeiZhiSJ.getText().toString());
        step13Resbean.setEt4DuanWeiZhiMin(et4DuanWeiZhiMin.getText().toString());
        step13Resbean.setEt4DuanWeiZhiMax(et4DuanWeiZhiMax.getText().toString());
        step13Resbean.setEt4DuanWeiZhiTime(et4DuanWeiZhiTime.getText().toString());

        step13Resbean.setVPWeiZhiSD(etVPWeiZhiSD.getText().toString());
        step13Resbean.setVPWeiZhiSJ(etVPWeiZhiSJ.getText().toString());
        step13Resbean.setVPWeiZhiMin(etVPWeiZhiMin.getText().toString());
        step13Resbean.setVPWeiZhiMax(etVPWeiZhiMax.getText().toString());
        step13Resbean.setVPWeiZhiTime(etVPWeiZhiTime.getText().toString());

        step13Resbean.setSheChuYaLi1SD(etSheChuYaLi1SD.getText().toString());
        step13Resbean.setSheChuYaLi1SJ(etSheChuYaLi1SJ.getText().toString());
        step13Resbean.setSheChuYaLi1Min(etSheChuYaLi1Min.getText().toString());
        step13Resbean.setSheChuYaLi1Max(etSheChuYaLi1Max.getText().toString());
        step13Resbean.setSheChuYaLi1Time(etSheChuYaLi1Time.getText().toString());

        step13Resbean.setSheChuYaLi2SD(etSheChuYaLi2SD.getText().toString());
        step13Resbean.setSheChuYaLi2SJ(etSheChuYaLi2SJ.getText().toString());
        step13Resbean.setSheChuYaLi2Min(etSheChuYaLi2Min.getText().toString());
        step13Resbean.setSheChuYaLi2Max(etSheChuYaLi2Max.getText().toString());
        step13Resbean.setSheChuYaLi2Time(etSheChuYaLi2Time.getText().toString());

        step13Resbean.setSheChuYaLi3SD(etSheChuYaLi3SD.getText().toString());
        step13Resbean.setSheChuYaLi3SJ(etSheChuYaLi3SJ.getText().toString());
        step13Resbean.setSheChuYaLi3Min(etSheChuYaLi3Min.getText().toString());
        step13Resbean.setSheChuYaLi3Max(etSheChuYaLi3Max.getText().toString());
        step13Resbean.setSheChuYaLi3Time(etSheChuYaLi3Time.getText().toString());

        step13Resbean.setSheChuYaLi4SD(etSheChuYaLi4SD.getText().toString());
        step13Resbean.setSheChuYaLi4SJ(etSheChuYaLi4SJ.getText().toString());
        step13Resbean.setSheChuYaLi4Min(etSheChuYaLi4Min.getText().toString());
        step13Resbean.setSheChuYaLi4Max(etSheChuYaLi4Max.getText().toString());
        step13Resbean.setSheChuYaLi4Time(etSheChuYaLi4Time.getText().toString());

        step13Resbean.setLengQueShiJianSD(etLengQueShiJianSD.getText().toString());
        step13Resbean.setLengQueShiJianSJ(etLengQueShiJianSJ.getText().toString());
        step13Resbean.setLengQueShiJianMin(etLengQueShiJianMin.getText().toString());
        step13Resbean.setLengQueShiJianMax(etLengQueShiJianMax.getText().toString());
        step13Resbean.setLengQueShiJianTime(etLengQueShiJianTime.getText().toString());

        step13Resbean.setSheChuShiJianSD(etSheChuShiJianSD.getText().toString());
        step13Resbean.setSheChuShiJianSJ(etSheChuShiJianSJ.getText().toString());
        step13Resbean.setSheChuShiJianMin(etSheChuShiJianMin.getText().toString());
        step13Resbean.setSheChuShiJianMax(etSheChuShiJianMax.getText().toString());
        step13Resbean.setSheChuShiJianTime(etSheChuShiJianTime.getText().toString());

        step13Resbean.setZhouQiShiJianSD(etZhouQiShiJianSD.getText().toString());
        step13Resbean.setZhouQiShiJianSJ(etZhouQiShiJianSJ.getText().toString());
        step13Resbean.setZhouQiShiJianMin(etZhouQiShiJianMin.getText().toString());
        step13Resbean.setZhouQiShiJianMax(etZhouQiShiJianMax.getText().toString());
        step13Resbean.setZhouQiShiJianTime(etZhouQiShiJianTime.getText().toString());

        step13Resbean.setBaoYa1DuanSD(etBaoYa1DuanSD.getText().toString());
        step13Resbean.setBaoYa1DuanSJ(etBaoYa1DuanSJ.getText().toString());
        step13Resbean.setBaoYa1DuanMin(etBaoYa1DuanMin.getText().toString());
        step13Resbean.setBaoYa1DuanMax(etBaoYa1DuanMax.getText().toString());
        step13Resbean.setBaoYa1DuanTime(etBaoYa1DuanTime.getText().toString());


        step13Resbean.setBaoYa2DuanSD(etBaoYa2DuanSD.getText().toString());
        step13Resbean.setBaoYa2DuanSJ(etBaoYa2DuanSJ.getText().toString());
        step13Resbean.setBaoYa2DuanMin(etBaoYa2DuanMin.getText().toString());
        step13Resbean.setBaoYa2DuanMax(etBaoYa2DuanMax.getText().toString());
        step13Resbean.setBaoYa2DuanTime(etBaoYa2DuanTime.getText().toString());

        step13Resbean.setBaoYa3DuanSD(etBaoYa3DuanSD.getText().toString());
        step13Resbean.setBaoYa3DuanSJ(etBaoYa3DuanSJ.getText().toString());
        step13Resbean.setBaoYa3DuanMin(etBaoYa3DuanMin.getText().toString());
        step13Resbean.setBaoYa3DuanMax(etBaoYa3DuanMax.getText().toString());
        step13Resbean.setBaoYa3DuanTime(etBaoYa3DuanTime.getText().toString());

        step13Resbean.setBaoYa1SuSD(etBaoYa1SuSD.getText().toString());
        step13Resbean.setBaoYa1SuSJ(etBaoYa1SuSJ.getText().toString());
        step13Resbean.setBaoYa1SuMin(etBaoYa1SuMin.getText().toString());
        step13Resbean.setBaoYa1SuMax(etBaoYa1SuMax.getText().toString());
        step13Resbean.setBaoYa1SuTime(etBaoYa1SuTime.getText().toString());

        step13Resbean.setBaoYa2SuSD(etBaoYa2SuSD.getText().toString());
        step13Resbean.setBaoYa2SuSJ(etBaoYa2SuSJ.getText().toString());
        step13Resbean.setBaoYa2SuMin(etBaoYa2SuMin.getText().toString());
        step13Resbean.setBaoYa2SuMax(etBaoYa2SuMax.getText().toString());
        step13Resbean.setBaoYa2SuTime(etBaoYa2SuTime.getText().toString());

        step13Resbean.setBaoYa3SuSD(etBaoYa3SuSD.getText().toString());
        step13Resbean.setBaoYa3SuSJ(etBaoYa3SuSJ.getText().toString());
        step13Resbean.setBaoYa3SuMin(etBaoYa3SuMin.getText().toString());
        step13Resbean.setBaoYa3SuMax(etBaoYa3SuMax.getText().toString());
        step13Resbean.setBaoYa3SuTime(etBaoYa3SuTime.getText().toString());

        step13Resbean.setBaoYaShiJian1SD(etBaoYaShiJian1SD.getText().toString());
        step13Resbean.setBaoYaShiJian1SJ(etBaoYaShiJian1SJ.getText().toString());
        step13Resbean.setBaoYaShiJian1Min(etBaoYaShiJian1Min.getText().toString());
        step13Resbean.setBaoYaShiJian1Max(etBaoYaShiJian1Max.getText().toString());
        step13Resbean.setBaoYaShiJian1Time(etBaoYaShiJian1Time.getText().toString());

        step13Resbean.setBaoYaShiJian2SD(etBaoYaShiJian2SD.getText().toString());
        step13Resbean.setBaoYaShiJian2SJ(etBaoYaShiJian2SJ.getText().toString());
        step13Resbean.setBaoYaShiJian2Min(etBaoYaShiJian2Min.getText().toString());
        step13Resbean.setBaoYaShiJian2Max(etBaoYaShiJian2Max.getText().toString());
        step13Resbean.setBaoYaShiJian2Time(etBaoYaShiJian2Time.getText().toString());

        step13Resbean.setBaoYaShiJian3SD(etBaoYaShiJian3SD.getText().toString());
        step13Resbean.setBaoYaShiJian3SJ(etBaoYaShiJian3SJ.getText().toString());
        step13Resbean.setBaoYaShiJian3Min(etBaoYaShiJian3Min.getText().toString());
        step13Resbean.setBaoYaShiJian3Max(etBaoYaShiJian3Max.getText().toString());
        step13Resbean.setBaoYaShiJian3Time(etBaoYaShiJian3Time.getText().toString());

        step13Resbean.setLuoGanZhuanSuSD(etLuoGanZhuanSuSD.getText().toString());
        step13Resbean.setLuoGanZhuanSuSJ(etLuoGanZhuanSuSJ.getText().toString());
        step13Resbean.setLuoGanZhuanSuMin(etLuoGanZhuanSuMin.getText().toString());
        step13Resbean.setLuoGanZhuanSuMax(etLuoGanZhuanSuMax.getText().toString());
        step13Resbean.setLuoGanZhuanSuTime(etLuoGanZhuanSuTime.getText().toString());

        step13Resbean.setBeiYaSD(etBeiYaSD.getText().toString());
        step13Resbean.setBeiYaSJ(etBeiYaSJ.getText().toString());
        step13Resbean.setBeiYaMin(etBeiYaMin.getText().toString());
        step13Resbean.setBeiYaMax(etBeiYaMax.getText().toString());
        step13Resbean.setBeiYaTime(etBeiYaTime.getText().toString());

        step13Resbean.setSongTuiWeiZhiSD(etSongTuiWeiZhiSD.getText().toString());
        step13Resbean.setSongTuiWeiZhiSJ(etSongTuiWeiZhiSJ.getText().toString());
        step13Resbean.setSongTuiWeiZhiMin(etSongTuiWeiZhiMin.getText().toString());
        step13Resbean.setSongTuiWeiZhiMax(etSongTuiWeiZhiMax.getText().toString());
        step13Resbean.setSongTuiWeiZhiTime(etSongTuiWeiZhiTime.getText().toString());

        step13Resbean.setYongLiaoLiangSD(etYongLiaoLiangSD.getText().toString());
        step13Resbean.setYongLiaoLiangSJ(etYongLiaoLiangSJ.getText().toString());
        step13Resbean.setYongLiaoLiangMin(etYongLiaoLiangMin.getText().toString());
        step13Resbean.setYongLiaoLiangMax(etYongLiaoLiangMax.getText().toString());
        step13Resbean.setYongLiaoLiangTime(etYongLiaoLiangTime.getText().toString());

        step13Resbean.setHuanChongLiangSD(etHuanChongLiangSD.getText().toString());
        step13Resbean.setHuanChongLiangSJ(etHuanChongLiangSJ.getText().toString());
        step13Resbean.setHuanChongLiangMin(etHuanChongLiangMin.getText().toString());
        step13Resbean.setHuanChongLiangMax(etHuanChongLiangMax.getText().toString());
        step13Resbean.setHuanChongLiangTime(etHuanChongLiangTime.getText().toString());

        step13Resbean.setMuJuBaoHuYaLiSD(etMuJuBaoHuYaLiSD.getText().toString());
        step13Resbean.setMuJuBaoHuYaLiSJ(etMuJuBaoHuYaLiSJ.getText().toString());
        step13Resbean.setMuJuBaoHuYaLiMin(etMuJuBaoHuYaLiMin.getText().toString());
        step13Resbean.setMuJuBaoHuYaLiMax(etMuJuBaoHuYaLiMax.getText().toString());
        step13Resbean.setMuJuBaoHuYaLiTime(etMuJuBaoHuYaLiTime.getText().toString());

        step13Resbean.setMuJuBaoHuWeiZhiSD(etMuJuBaoHuWeiZhiSD.getText().toString());
        step13Resbean.setMuJuBaoHuWeiZhiSJ(etMuJuBaoHuWeiZhiSJ.getText().toString());
        step13Resbean.setMuJuBaoHuWeiZhiMin(etMuJuBaoHuWeiZhiMin.getText().toString());
        step13Resbean.setMuJuBaoHuWeiZhiMax(etMuJuBaoHuWeiZhiMax.getText().toString());
        step13Resbean.setMuJuBaoHuWeiZhiTime(etMuJuBaoHuWeiZhiTime.getText().toString());

        step13Resbean.setSuoMoLiSD(etSuoMoLiSD.getText().toString());
        step13Resbean.setSuoMoLiSJ(etSuoMoLiSJ.getText().toString());
        step13Resbean.setSuoMoLiMin(etSuoMoLiMin.getText().toString());
        step13Resbean.setSuoMoLiMax(etSuoMoLiMax.getText().toString());
        step13Resbean.setSuoMoLiTime(etSuoMoLiTime.getText().toString());

        step13Resbean.setDingChuWeiZhiSD(etDingChuWeiZhiSD.getText().toString());
        step13Resbean.setDingChuWeiZhiSJ(etDingChuWeiZhiSJ.getText().toString());
        step13Resbean.setDingChuWeiZhiMin(etDingChuWeiZhiMin.getText().toString());
        step13Resbean.setDingChuWeiZhiMax(etDingChuWeiZhiMax.getText().toString());
        step13Resbean.setDingChuWeiZhiTime(etDingChuWeiZhiTime.getText().toString());

        step13Resbean.setDingMoWenDuSD(etDingMoWenDuSD.getText().toString());
        step13Resbean.setDingMoWenDuSJ(etDingMoWenDuSJ.getText().toString());
        step13Resbean.setDingMoWenDuMin(etDingMoWenDuMin.getText().toString());
        step13Resbean.setDingMoWenDuMax(etDingMoWenDuMax.getText().toString());
        step13Resbean.setDingMoWenDuTime(etDingMoWenDuTime.getText().toString());

        step13Resbean.setDongMoWenDuSD(etDongMoWenDuSD.getText().toString());
        step13Resbean.setDongMoWenDuSJ(etDongMoWenDuSJ.getText().toString());
        step13Resbean.setDongMoWenDuMin(etDongMoWenDuMin.getText().toString());
        step13Resbean.setDongMoWenDuMax(etDongMoWenDuMax.getText().toString());
        step13Resbean.setDongMoWenDuTime(etDongMoWenDuTime.getText().toString());

        step13Resbean.setHongLiaoWenDuSD(etHongLiaoWenDuSD.getText().toString());
        step13Resbean.setHongLiaoWenDuSJ(etHongLiaoWenDuSJ.getText().toString());
        step13Resbean.setHongLiaoWenDuMin(etHongLiaoWenDuMin.getText().toString());
        step13Resbean.setHongLiaoWenDuMax(etHongLiaoWenDuMax.getText().toString());
        step13Resbean.setHongLiaoWenDuTime(etHongLiaoWenDuTime.getText().toString());

        step13Resbean.setHuiLiaoBiLiSD(etHuiLiaoBiLiSD.getText().toString());
        step13Resbean.setHuiLiaoBiLiSJ(etHuiLiaoBiLiSJ.getText().toString());
        step13Resbean.setHuiLiaoBiLiMin(etHuiLiaoBiLiMin.getText().toString());
        step13Resbean.setHuiLiaoBiLiMax(etHuiLiaoBiLiMax.getText().toString());
        step13Resbean.setHuiLiaoBiLiTime(etHuiLiaoBiLiTime.getText().toString());

        step13Resbean.setQianJianShuLiangSD(etQianJianShuLiangSD.getText().toString());
        step13Resbean.setQianJianShuLiangSJ(etQianJianShuLiangSJ.getText().toString());
        step13Resbean.setQianJianShuLiangMin(etQianJianShuLiangMin.getText().toString());
        step13Resbean.setQianJianShuLiangMax(etQianJianShuLiangMax.getText().toString());
        step13Resbean.setQianJianShuLiangTime(etQianJianShuLiangTime.getText().toString());

        step13Resbean.setQianJianBianHaoSD(etQianJianBianHaoSD.getText().toString());
        step13Resbean.setQianJianBianHaoSJ(etQianJianBianHaoSJ.getText().toString());
        step13Resbean.setQianJianBianHaoMin(etQianJianBianHaoMin.getText().toString());
        step13Resbean.setQianJianBianHaoMax(etQianJianBianHaoMax.getText().toString());
        step13Resbean.setQianJianBianHaoTime(etQianJianBianHaoTime.getText().toString());

    }
}
