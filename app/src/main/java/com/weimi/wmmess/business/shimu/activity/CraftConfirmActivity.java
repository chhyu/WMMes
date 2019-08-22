package com.weimi.wmmess.business.shimu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.business.shimu.bean.craftParams.CraftConfirmResbean;
import com.weimi.wmmess.business.shimu.bean.craftParams.ModelInfoResbean;
import com.weimi.wmmess.business.shimu.entity.CraftParamsName;
import com.weimi.wmmess.business.shimu.entity.SPHeaderInfoConstant;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.business.shimu.params.MobileTrialModeParam;
import com.weimi.wmmess.business.shimu.presenter.Step13Presenter;
import com.weimi.wmmess.business.shimu.viewInterface.ICraftConfirmView;
import com.weimi.wmmess.business.shimu.viewInterface.IModelView;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.presenter.ShiMuPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 工艺确定（工艺参数）
 */
public class CraftConfirmActivity extends WMActivity<Step13Presenter> implements View.OnClickListener, ICraftConfirmView, IModelView {
    public static final int stepPosition = 12;
    public static final String CRAFT_PARAMETER_ID = "craftParameterId";
    public static final String WORK_ORDER_ID = "work_order_id";
    public static final String PROCEDURE_ID = "procedure_Id";
    public static final String IS_NEW_CREATE = "is_new_create";
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
    private EditText etGanZaoWenDu, etGanZaoShiJian, etYuanLiaoHanShuiFen, etChengXingWenDu, etMuJuWenDu, etSheChuYaLi, etSheChuShiJian, etSheChuSuDu,
            etBaoYaYali, etBaoYaShiJian, etSuoMoLi, etKaiHeMoShiJian, etLengQueShijian, etLuoGanSuDu, etDingTuiChuShijian, etBeiYa;
    private EditText etDate, etMuRen, etMuJia, etXiangMuBianHao, etJiqiBianhao, etJiqiDunWei, etLuoGanZhiJing, etWaiSheZuiZhiJing, etNeiSheZuiZhiJing, etChanPinMingCheng, etChanPinBianHao;
    private EditText etKeHuLiaoHao, etChanPinBanBen, etYuanLiaoMingCheng, etYuanLiaoLiaoHao, etYuanLiaoGongYingShang, etRLDJRDS, etLiudaoLeiXing;
    private String workOrderId;
    private String procedureId;
    private ShiMuPresenter presenter;


    @Override
    public int initLayout() {
        return R.layout.activity_craft_confirm;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        btnNextStep = findViewById(R.id.btnNextStep);

        etDate = findViewById(R.id.etDate);
        etMuRen = findViewById(R.id.etMuRen);
        etMuJia = findViewById(R.id.etMuJia);
        etXiangMuBianHao = findViewById(R.id.etXiangMuBianHao);

        etJiqiBianhao = findViewById(R.id.etJiqiBianhao);
        etJiqiDunWei = findViewById(R.id.etJiqiDunWei);
        etLuoGanZhiJing = findViewById(R.id.etLuoGanZhiJing);
        etWaiSheZuiZhiJing = findViewById(R.id.etWaiSheZuiZhiJing);
        etNeiSheZuiZhiJing = findViewById(R.id.etNeiSheZuiZhiJing);

        etChanPinMingCheng = findViewById(R.id.etChanPinMingCheng);
        etChanPinBianHao = findViewById(R.id.etChanPinBianHao);
        etKeHuLiaoHao = findViewById(R.id.etKeHuLiaoHao);
        etChanPinBanBen = findViewById(R.id.etChanPinBanBen);

        etYuanLiaoMingCheng = findViewById(R.id.etYuanLiaoMingCheng);
        etYuanLiaoLiaoHao = findViewById(R.id.etYuanLiaoLiaoHao);
        etYuanLiaoGongYingShang = findViewById(R.id.etYuanLiaoGongYingShang);
        etRLDJRDS = findViewById(R.id.etRLDJRDS);
        etLiudaoLeiXing = findViewById(R.id.etLiudaoLeiXing);


        etGanZaoWenDu = findViewById(R.id.etGanZaoWenDu);
        etGanZaoShiJian = findViewById(R.id.etGanZaoShiJian);
        etYuanLiaoHanShuiFen = findViewById(R.id.etYuanLiaoHanShuiFen);
        etChengXingWenDu = findViewById(R.id.etChengXingWenDu);

        etMuJuWenDu = findViewById(R.id.etMuJuWenDu);
        etSheChuYaLi = findViewById(R.id.etSheChuYaLi);
        etSheChuShiJian = findViewById(R.id.etSheChuShiJian);
        etSheChuSuDu = findViewById(R.id.etSheChuSuDu);

        etBaoYaYali = findViewById(R.id.etBaoYaYali);
        etBaoYaShiJian = findViewById(R.id.etBaoYaShiJian);
        etSuoMoLi = findViewById(R.id.etSuoMoLi);
        etKaiHeMoShiJian = findViewById(R.id.etKaiHeMoShiJian);

        etLengQueShijian = findViewById(R.id.etLengQueShijian);
        etLuoGanSuDu = findViewById(R.id.etLuoGanSuDu);
        etDingTuiChuShijian = findViewById(R.id.etDingTuiChuShijian);
        etBeiYa = findViewById(R.id.etBeiYa);

        etJinLiaoDuanSD = findViewById(R.id.etJinLiaoDuanSD);
        etJinLiaoDuanSJ = findViewById(R.id.etJinLiaoDuanSJ);
        etJinLiaoDuanMin = findViewById(R.id.etJinLiaoDuanMin);
        etJinLiaoDuanMax = findViewById(R.id.etJinLiaoDuanMax);
        etJinLiaoDuanTime = findViewById(R.id.etJinLiaoDuanTime);

        etJinLiaoDuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etJinLiaoDuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etJinLiaoDuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etJinLiaoDuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etHouDuanSD = findViewById(R.id.etHouDuanSD);
        etHouDuanSJ = findViewById(R.id.etHouDuanSJ);
        etHouDuanMin = findViewById(R.id.etHouDuanMin);
        etHouDuanMax = findViewById(R.id.etHouDuanMax);
        etHouDuanTime = findViewById(R.id.etHouDuanTime);

        etHouDuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHouDuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHouDuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHouDuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etZhongJianDuanSD = findViewById(R.id.etZhongJianDuanSD);
        etZhongJianDuanSJ = findViewById(R.id.etZhongJianDuanSJ);
        etZhongJianDuanMin = findViewById(R.id.etZhongJianDuanMin);
        etZhongJianDuanMax = findViewById(R.id.etZhongJianDuanMax);
        etZhongJianDuanTime = findViewById(R.id.etZhongJianDuanTime);

        etZhongJianDuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhongJianDuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhongJianDuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhongJianDuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etQianDuanSD = findViewById(R.id.etQianDuanSD);
        etQianDuanSJ = findViewById(R.id.etQianDuanSJ);
        etQianDuanMin = findViewById(R.id.etQianDuanMin);
        etQianDuanMax = findViewById(R.id.etQianDuanMax);
        etQianDuanTime = findViewById(R.id.etQianDuanTime);

        etQianDuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianDuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianDuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianDuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etPenZuiSD = findViewById(R.id.etPenZuiSD);
        etPenZuiSJ = findViewById(R.id.etPenZuiSJ);
        etPenZuiMin = findViewById(R.id.etPenZuiMin);
        etPenZuiMax = findViewById(R.id.etPenZuiMax);
        etPenZuiTime = findViewById(R.id.etPenZuiTime);

        etPenZuiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etPenZuiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etPenZuiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etPenZuiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChu1SuSD = findViewById(R.id.etSheChu1SuSD);
        etSheChu1SuSJ = findViewById(R.id.etSheChu1SuSJ);
        etSheChu1SuMin = findViewById(R.id.etSheChu1SuMin);
        etSheChu1SuMax = findViewById(R.id.etSheChu1SuMax);
        etSheChu1SuTime = findViewById(R.id.etSheChu1SuTime);

        etSheChu1SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu1SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu1SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu1SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChu2SuSD = findViewById(R.id.etSheChu2SuSD);
        etSheChu2SuSJ = findViewById(R.id.etSheChu2SuSJ);
        etSheChu2SuMin = findViewById(R.id.etSheChu2SuMin);
        etSheChu2SuMax = findViewById(R.id.etSheChu2SuMax);
        etSheChu2SuTime = findViewById(R.id.etSheChu2SuTime);

        etSheChu2SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu2SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu2SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu2SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChu3SuSD = findViewById(R.id.etSheChu3SuSD);
        etSheChu3SuSJ = findViewById(R.id.etSheChu3SuSJ);
        etSheChu3SuMin = findViewById(R.id.etSheChu3SuMin);
        etSheChu3SuMax = findViewById(R.id.etSheChu3SuMax);
        etSheChu3SuTime = findViewById(R.id.etSheChu3SuTime);

        etSheChu3SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu3SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu3SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu3SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChu4SuSD = findViewById(R.id.etSheChu4SuSD);
        etSheChu4SuSJ = findViewById(R.id.etSheChu4SuSJ);
        etSheChu4SuMin = findViewById(R.id.etSheChu4SuMin);
        etSheChu4SuMax = findViewById(R.id.etSheChu4SuMax);
        etSheChu4SuTime = findViewById(R.id.etSheChu4SuTime);

        etSheChu4SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu4SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu4SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChu4SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        et1DuanWeiZhiSD = findViewById(R.id.et1DuanWeiZhiSD);
        et1DuanWeiZhiSJ = findViewById(R.id.et1DuanWeiZhiSJ);
        et1DuanWeiZhiMin = findViewById(R.id.et1DuanWeiZhiMin);
        et1DuanWeiZhiMax = findViewById(R.id.et1DuanWeiZhiMax);
        et1DuanWeiZhiTime = findViewById(R.id.et1DuanWeiZhiTime);

        et1DuanWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et1DuanWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et1DuanWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et1DuanWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        et2DuanWeiZhiSD = findViewById(R.id.et2DuanWeiZhiSD);
        et2DuanWeiZhiSJ = findViewById(R.id.et2DuanWeiZhiSJ);
        et2DuanWeiZhiMin = findViewById(R.id.et2DuanWeiZhiMin);
        et2DuanWeiZhiMax = findViewById(R.id.et2DuanWeiZhiMax);
        et2DuanWeiZhiTime = findViewById(R.id.et2DuanWeiZhiTime);

        et2DuanWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et2DuanWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et2DuanWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et2DuanWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        et3DuanWeiZhiSD = findViewById(R.id.et3DuanWeiZhiSD);
        et3DuanWeiZhiSJ = findViewById(R.id.et3DuanWeiZhiSJ);
        et3DuanWeiZhiMin = findViewById(R.id.et3DuanWeiZhiMin);
        et3DuanWeiZhiMax = findViewById(R.id.et3DuanWeiZhiMax);
        et3DuanWeiZhiTime = findViewById(R.id.et3DuanWeiZhiTime);

        et3DuanWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et3DuanWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et3DuanWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et3DuanWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        et4DuanWeiZhiSD = findViewById(R.id.et4DuanWeiZhiSD);
        et4DuanWeiZhiSJ = findViewById(R.id.et4DuanWeiZhiSJ);
        et4DuanWeiZhiMin = findViewById(R.id.et4DuanWeiZhiMin);
        et4DuanWeiZhiMax = findViewById(R.id.et4DuanWeiZhiMax);
        et4DuanWeiZhiTime = findViewById(R.id.et4DuanWeiZhiTime);

        et4DuanWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et4DuanWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et4DuanWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        et4DuanWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etVPWeiZhiSD = findViewById(R.id.etVPWeiZhiSD);
        etVPWeiZhiSJ = findViewById(R.id.etVPWeiZhiSJ);
        etVPWeiZhiMin = findViewById(R.id.etVPWeiZhiMin);
        etVPWeiZhiMax = findViewById(R.id.etVPWeiZhiMax);
        etVPWeiZhiTime = findViewById(R.id.etVPWeiZhiTime);

        etVPWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etVPWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etVPWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etVPWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChuYaLi1SD = findViewById(R.id.etSheChuYaLi1SD);
        etSheChuYaLi1SJ = findViewById(R.id.etSheChuYaLi1SJ);
        etSheChuYaLi1Min = findViewById(R.id.etSheChuYaLi1Min);
        etSheChuYaLi1Max = findViewById(R.id.etSheChuYaLi1Max);
        etSheChuYaLi1Time = findViewById(R.id.etSheChuYaLi1Time);

        etSheChuYaLi1SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi1SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi1Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi1Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChuYaLi2SD = findViewById(R.id.etSheChuYaLi2SD);
        etSheChuYaLi2SJ = findViewById(R.id.etSheChuYaLi2SJ);
        etSheChuYaLi2Min = findViewById(R.id.etSheChuYaLi2Min);
        etSheChuYaLi2Max = findViewById(R.id.etSheChuYaLi2Max);
        etSheChuYaLi2Time = findViewById(R.id.etSheChuYaLi2Time);

        etSheChuYaLi2SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi2SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi2Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi2Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChuYaLi3SD = findViewById(R.id.etSheChuYaLi3SD);
        etSheChuYaLi3SJ = findViewById(R.id.etSheChuYaLi3SJ);
        etSheChuYaLi3Min = findViewById(R.id.etSheChuYaLi3Min);
        etSheChuYaLi3Max = findViewById(R.id.etSheChuYaLi3Max);
        etSheChuYaLi3Time = findViewById(R.id.etSheChuYaLi3Time);

        etSheChuYaLi3SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi3SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi3Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi3Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChuYaLi4SD = findViewById(R.id.etSheChuYaLi4SD);
        etSheChuYaLi4SJ = findViewById(R.id.etSheChuYaLi4SJ);
        etSheChuYaLi4Min = findViewById(R.id.etSheChuYaLi4Min);
        etSheChuYaLi4Max = findViewById(R.id.etSheChuYaLi4Max);
        etSheChuYaLi4Time = findViewById(R.id.etSheChuYaLi4Time);

        etSheChuYaLi4SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi4SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi4Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuYaLi4Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etLengQueShiJianSD = findViewById(R.id.etLengQueShiJianSD);
        etLengQueShiJianSJ = findViewById(R.id.etLengQueShiJianSJ);
        etLengQueShiJianMin = findViewById(R.id.etLengQueShiJianMin);
        etLengQueShiJianMax = findViewById(R.id.etLengQueShiJianMax);
        etLengQueShiJianTime = findViewById(R.id.etLengQueShiJianTime);

        etLengQueShiJianSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLengQueShiJianSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLengQueShiJianMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLengQueShiJianMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSheChuShiJianSD = findViewById(R.id.etSheChuShiJianSD);
        etSheChuShiJianSJ = findViewById(R.id.etSheChuShiJianSJ);
        etSheChuShiJianMin = findViewById(R.id.etSheChuShiJianMin);
        etSheChuShiJianMax = findViewById(R.id.etSheChuShiJianMax);
        etSheChuShiJianTime = findViewById(R.id.etSheChuShiJianTime);

        etSheChuShiJianSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuShiJianSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuShiJianMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSheChuShiJianMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etZhouQiShiJianSD = findViewById(R.id.etZhouQiShiJianSD);
        etZhouQiShiJianSJ = findViewById(R.id.etZhouQiShiJianSJ);
        etZhouQiShiJianMin = findViewById(R.id.etZhouQiShiJianMin);
        etZhouQiShiJianMax = findViewById(R.id.etZhouQiShiJianMax);
        etZhouQiShiJianTime = findViewById(R.id.etZhouQiShiJianTime);

        etZhouQiShiJianSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhouQiShiJianSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhouQiShiJianMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etZhouQiShiJianMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa1DuanSD = findViewById(R.id.etBaoYa1DuanSD);
        etBaoYa1DuanSJ = findViewById(R.id.etBaoYa1DuanSJ);
        etBaoYa1DuanMin = findViewById(R.id.etBaoYa1DuanMin);
        etBaoYa1DuanMax = findViewById(R.id.etBaoYa1DuanMax);
        etBaoYa1DuanTime = findViewById(R.id.etBaoYa1DuanTime);

        etBaoYa1DuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1DuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1DuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1DuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa2DuanSD = findViewById(R.id.etBaoYa2DuanSD);
        etBaoYa2DuanSJ = findViewById(R.id.etBaoYa2DuanSJ);
        etBaoYa2DuanMin = findViewById(R.id.etBaoYa2DuanMin);
        etBaoYa2DuanMax = findViewById(R.id.etBaoYa2DuanMax);
        etBaoYa2DuanTime = findViewById(R.id.etBaoYa2DuanTime);

        etBaoYa2DuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2DuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2DuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2DuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa3DuanSD = findViewById(R.id.etBaoYa3DuanSD);
        etBaoYa3DuanSJ = findViewById(R.id.etBaoYa3DuanSJ);
        etBaoYa3DuanMin = findViewById(R.id.etBaoYa3DuanMin);
        etBaoYa3DuanMax = findViewById(R.id.etBaoYa3DuanMax);
        etBaoYa3DuanTime = findViewById(R.id.etBaoYa3DuanTime);

        etBaoYa3DuanSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3DuanSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3DuanMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3DuanMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa1SuSD = findViewById(R.id.etBaoYa1SuSD);
        etBaoYa1SuSJ = findViewById(R.id.etBaoYa1SuSJ);
        etBaoYa1SuMin = findViewById(R.id.etBaoYa1SuMin);
        etBaoYa1SuMax = findViewById(R.id.etBaoYa1SuMax);
        etBaoYa1SuTime = findViewById(R.id.etBaoYa1SuTime);

        etBaoYa1SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa1SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa2SuSD = findViewById(R.id.etBaoYa2SuSD);
        etBaoYa2SuSJ = findViewById(R.id.etBaoYa2SuSJ);
        etBaoYa2SuMin = findViewById(R.id.etBaoYa2SuMin);
        etBaoYa2SuMax = findViewById(R.id.etBaoYa2SuMax);
        etBaoYa2SuTime = findViewById(R.id.etBaoYa2SuTime);

        etBaoYa2SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa2SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYa3SuSD = findViewById(R.id.etBaoYa3SuSD);
        etBaoYa3SuSJ = findViewById(R.id.etBaoYa3SuSJ);
        etBaoYa3SuMin = findViewById(R.id.etBaoYa3SuMin);
        etBaoYa3SuMax = findViewById(R.id.etBaoYa3SuMax);
        etBaoYa3SuTime = findViewById(R.id.etBaoYa3SuTime);

        etBaoYa3SuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3SuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3SuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYa3SuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYaShiJian1SD = findViewById(R.id.etBaoYaShiJian1SD);
        etBaoYaShiJian1SJ = findViewById(R.id.etBaoYaShiJian1SJ);
        etBaoYaShiJian1Min = findViewById(R.id.etBaoYaShiJian1Min);
        etBaoYaShiJian1Max = findViewById(R.id.etBaoYaShiJian1Max);
        etBaoYaShiJian1Time = findViewById(R.id.etBaoYaShiJian1Time);

        etBaoYaShiJian1SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian1SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian1Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian1Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYaShiJian2SD = findViewById(R.id.etBaoYaShiJian2SD);
        etBaoYaShiJian2SJ = findViewById(R.id.etBaoYaShiJian2SJ);
        etBaoYaShiJian2Min = findViewById(R.id.etBaoYaShiJian2Min);
        etBaoYaShiJian2Max = findViewById(R.id.etBaoYaShiJian2Max);
        etBaoYaShiJian2Time = findViewById(R.id.etBaoYaShiJian2Time);

        etBaoYaShiJian2SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian2SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian2Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian2Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBaoYaShiJian3SD = findViewById(R.id.etBaoYaShiJian3SD);
        etBaoYaShiJian3SJ = findViewById(R.id.etBaoYaShiJian3SJ);
        etBaoYaShiJian3Min = findViewById(R.id.etBaoYaShiJian3Min);
        etBaoYaShiJian3Max = findViewById(R.id.etBaoYaShiJian3Max);
        etBaoYaShiJian3Time = findViewById(R.id.etBaoYaShiJian3Time);

        etBaoYaShiJian3SD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian3SJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian3Min.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBaoYaShiJian3Max.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etLuoGanZhuanSuSD = findViewById(R.id.etLuoGanZhuanSuSD);
        etLuoGanZhuanSuSJ = findViewById(R.id.etLuoGanZhuanSuSJ);
        etLuoGanZhuanSuMin = findViewById(R.id.etLuoGanZhuanSuMin);
        etLuoGanZhuanSuMax = findViewById(R.id.etLuoGanZhuanSuMax);
        etLuoGanZhuanSuTime = findViewById(R.id.etLuoGanZhuanSuTime);

        etLuoGanZhuanSuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLuoGanZhuanSuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLuoGanZhuanSuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etLuoGanZhuanSuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etBeiYaSD = findViewById(R.id.etBeiYaSD);
        etBeiYaSJ = findViewById(R.id.etBeiYaSJ);
        etBeiYaMin = findViewById(R.id.etBeiYaMin);
        etBeiYaMax = findViewById(R.id.etBeiYaMax);
        etBeiYaTime = findViewById(R.id.etBeiYaTime);

        etBeiYaSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBeiYaSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBeiYaMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etBeiYaMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSongTuiWeiZhiSD = findViewById(R.id.etSongTuiWeiZhiSD);
        etSongTuiWeiZhiSJ = findViewById(R.id.etSongTuiWeiZhiSJ);
        etSongTuiWeiZhiMin = findViewById(R.id.etSongTuiWeiZhiMin);
        etSongTuiWeiZhiMax = findViewById(R.id.etSongTuiWeiZhiMax);
        etSongTuiWeiZhiTime = findViewById(R.id.etSongTuiWeiZhiTime);

        etSongTuiWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSongTuiWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSongTuiWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSongTuiWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etYongLiaoLiangSD = findViewById(R.id.etYongLiaoLiangSD);
        etYongLiaoLiangSJ = findViewById(R.id.etYongLiaoLiangSJ);
        etYongLiaoLiangMin = findViewById(R.id.etYongLiaoLiangMin);
        etYongLiaoLiangMax = findViewById(R.id.etYongLiaoLiangMax);
        etYongLiaoLiangTime = findViewById(R.id.etYongLiaoLiangTime);

        etYongLiaoLiangSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etYongLiaoLiangSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etYongLiaoLiangMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etYongLiaoLiangMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etHuanChongLiangSD = findViewById(R.id.etHuanChongLiangSD);
        etHuanChongLiangSJ = findViewById(R.id.etHuanChongLiangSJ);
        etHuanChongLiangMin = findViewById(R.id.etHuanChongLiangMin);
        etHuanChongLiangMax = findViewById(R.id.etHuanChongLiangMax);
        etHuanChongLiangTime = findViewById(R.id.etHuanChongLiangTime);

        etHuanChongLiangSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuanChongLiangSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuanChongLiangMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuanChongLiangMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etMuJuBaoHuYaLiSD = findViewById(R.id.etMuJuBaoHuYaLiSD);
        etMuJuBaoHuYaLiSJ = findViewById(R.id.etMuJuBaoHuYaLiSJ);
        etMuJuBaoHuYaLiMin = findViewById(R.id.etMuJuBaoHuYaLiMin);
        etMuJuBaoHuYaLiMax = findViewById(R.id.etMuJuBaoHuYaLiMax);
        etMuJuBaoHuYaLiTime = findViewById(R.id.etMuJuBaoHuYaLiTime);

        etMuJuBaoHuYaLiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuYaLiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuYaLiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuYaLiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etMuJuBaoHuWeiZhiSD = findViewById(R.id.etMuJuBaoHuWeiZhiSD);
        etMuJuBaoHuWeiZhiSJ = findViewById(R.id.etMuJuBaoHuWeiZhiSJ);
        etMuJuBaoHuWeiZhiMin = findViewById(R.id.etMuJuBaoHuWeiZhiMin);
        etMuJuBaoHuWeiZhiMax = findViewById(R.id.etMuJuBaoHuWeiZhiMax);
        etMuJuBaoHuWeiZhiTime = findViewById(R.id.etMuJuBaoHuWeiZhiTime);

        etMuJuBaoHuWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etMuJuBaoHuWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etSuoMoLiSD = findViewById(R.id.etSuoMoLiSD);
        etSuoMoLiSJ = findViewById(R.id.etSuoMoLiSJ);
        etSuoMoLiMin = findViewById(R.id.etSuoMoLiMin);
        etSuoMoLiMax = findViewById(R.id.etSuoMoLiMax);
        etSuoMoLiTime = findViewById(R.id.etSuoMoLiTime);

        etSuoMoLiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSuoMoLiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSuoMoLiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etSuoMoLiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etDingChuWeiZhiSD = findViewById(R.id.etDingChuWeiZhiSD);
        etDingChuWeiZhiSJ = findViewById(R.id.etDingChuWeiZhiSJ);
        etDingChuWeiZhiMin = findViewById(R.id.etDingChuWeiZhiMin);
        etDingChuWeiZhiMax = findViewById(R.id.etDingChuWeiZhiMax);
        etDingChuWeiZhiTime = findViewById(R.id.etDingChuWeiZhiTime);

        etDingChuWeiZhiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingChuWeiZhiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingChuWeiZhiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingChuWeiZhiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etDingMoWenDuSD = findViewById(R.id.etDingMoWenDuSD);
        etDingMoWenDuSJ = findViewById(R.id.etDingMoWenDuSJ);
        etDingMoWenDuMin = findViewById(R.id.etDingMoWenDuMin);
        etDingMoWenDuMax = findViewById(R.id.etDingMoWenDuMax);
        etDingMoWenDuTime = findViewById(R.id.etDingMoWenDuTime);

        etDingMoWenDuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingMoWenDuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingMoWenDuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDingMoWenDuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etDongMoWenDuSD = findViewById(R.id.etDongMoWenDuSD);
        etDongMoWenDuSJ = findViewById(R.id.etDongMoWenDuSJ);
        etDongMoWenDuMin = findViewById(R.id.etDongMoWenDuMin);
        etDongMoWenDuMax = findViewById(R.id.etDongMoWenDuMax);
        etDongMoWenDuTime = findViewById(R.id.etDongMoWenDuTime);

        etDongMoWenDuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDongMoWenDuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDongMoWenDuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etDongMoWenDuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etHongLiaoWenDuSD = findViewById(R.id.etHongLiaoWenDuSD);
        etHongLiaoWenDuSJ = findViewById(R.id.etHongLiaoWenDuSJ);
        etHongLiaoWenDuMin = findViewById(R.id.etHongLiaoWenDuMin);
        etHongLiaoWenDuMax = findViewById(R.id.etHongLiaoWenDuMax);
        etHongLiaoWenDuTime = findViewById(R.id.etHongLiaoWenDuTime);

        etHongLiaoWenDuSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHongLiaoWenDuSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHongLiaoWenDuMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHongLiaoWenDuMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etHuiLiaoBiLiSD = findViewById(R.id.etHuiLiaoBiLiSD);
        etHuiLiaoBiLiSJ = findViewById(R.id.etHuiLiaoBiLiSJ);
        etHuiLiaoBiLiMin = findViewById(R.id.etHuiLiaoBiLiMin);
        etHuiLiaoBiLiMax = findViewById(R.id.etHuiLiaoBiLiMax);
        etHuiLiaoBiLiTime = findViewById(R.id.etHuiLiaoBiLiTime);

        etHuiLiaoBiLiSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuiLiaoBiLiSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuiLiaoBiLiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etHuiLiaoBiLiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etQianJianShuLiangSD = findViewById(R.id.etQianJianShuLiangSD);
        etQianJianShuLiangSJ = findViewById(R.id.etQianJianShuLiangSJ);
        etQianJianShuLiangMin = findViewById(R.id.etQianJianShuLiangMin);
        etQianJianShuLiangMax = findViewById(R.id.etQianJianShuLiangMax);
        etQianJianShuLiangTime = findViewById(R.id.etQianJianShuLiangTime);

        etQianJianShuLiangSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianShuLiangSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianShuLiangMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianShuLiangMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        etQianJianBianHaoSD = findViewById(R.id.etQianJianBianHaoSD);
        etQianJianBianHaoSJ = findViewById(R.id.etQianJianBianHaoSJ);
        etQianJianBianHaoMin = findViewById(R.id.etQianJianBianHaoMin);
        etQianJianBianHaoMax = findViewById(R.id.etQianJianBianHaoMax);
        etQianJianBianHaoTime = findViewById(R.id.etQianJianBianHaoTime);

        etQianJianBianHaoSD.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianBianHaoSJ.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianBianHaoMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etQianJianBianHaoMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        btnNextStep.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("工艺确定");
        presenter = new ShiMuPresenter(this);
        parseIntent();
    }

    private void parseIntent() {
        workOrderId = getIntent().getStringExtra(WORK_ORDER_ID);
        procedureId = getIntent().getStringExtra(PROCEDURE_ID);
        String craftPaeameterId = getIntent().getStringExtra(CRAFT_PARAMETER_ID);
        boolean isNewCreate = getIntent().getBooleanExtra(IS_NEW_CREATE, false);

        if (!StringUtils.isEmpty(craftPaeameterId)) {
            presenter.getTrialParameterInfoById(craftPaeameterId);
            btnNextStep.setVisibility(View.GONE);
        } else {
            btnNextStep.setVisibility(View.VISIBLE);
        }

        if (!StringUtils.isEmpty(workOrderId) && !StringUtils.isEmpty(procedureId)) {
            presenter.getModelInfoById(workOrderId, procedureId);
        }

        if (isNewCreate) {
            displayAlwaysExistData();
        }
    }

    /**
     * 显示已经存在的数据
     */
    private void displayAlwaysExistData() {
        SPUtils spUtils = SPUtils.getInstance();
        etGanZaoWenDu.setText(spUtils.getString(SPHeaderInfoConstant.SP_GANZAOWENDU));
        etGanZaoShiJian.setText(spUtils.getString(SPHeaderInfoConstant.SP_GANZAOSHIJIAN));
        etYuanLiaoHanShuiFen.setText(spUtils.getString(SPHeaderInfoConstant.SP_YUANLIAOHANSHUIFEN));
        etChengXingWenDu.setText(spUtils.getString(SPHeaderInfoConstant.SP_CHENGXINGWENDU));

        etMuJuWenDu.setText(spUtils.getString(SPHeaderInfoConstant.SP_MUJUWENDU));
        etSheChuYaLi.setText(spUtils.getString(SPHeaderInfoConstant.SP_SHECHUYALI));
        etSheChuShiJian.setText(spUtils.getString(SPHeaderInfoConstant.SP_SHECHUSHIJIAN));
        etSheChuSuDu.setText(spUtils.getString(SPHeaderInfoConstant.SP_SHECHUSUDU));

        etBaoYaYali.setText(spUtils.getString(SPHeaderInfoConstant.SP_BAOYAYALI));
        etBaoYaShiJian.setText(spUtils.getString(SPHeaderInfoConstant.SP_BAOYASHIJIAN));
        etSuoMoLi.setText(spUtils.getString(SPHeaderInfoConstant.SP_SUOMOLI));
        etKaiHeMoShiJian.setText(spUtils.getString(SPHeaderInfoConstant.SP_KAIHEMOSHIJIAN));

        etLengQueShijian.setText(spUtils.getString(SPHeaderInfoConstant.SP_LENGQUESHIJIAN));
        etLuoGanSuDu.setText(spUtils.getString(SPHeaderInfoConstant.SP_LUOGANSUDU));
        etDingTuiChuShijian.setText(spUtils.getString(SPHeaderInfoConstant.SP_DINGTUICHUSHIJIAN));
        etBeiYa.setText(spUtils.getString(SPHeaderInfoConstant.SP_BEIYA));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                List<MobileTrialModeParam> list = setData2Resbean();
                boolean isHave = presenter.checkDataIsHaveNull(list);
                if (isHave) {
                    return;
                }
                presenter.saveCraftParams2Service(list, workOrderId, procedureId);
                break;
        }
    }

    private List<MobileTrialModeParam> carftParamsList = new ArrayList<>();


    private List<MobileTrialModeParam> setData2Resbean() {
        carftParamsList.clear();
        MobileTrialModeParam jinLiaoDuan = new MobileTrialModeParam();
        jinLiaoDuan.setParameterName(CraftParamsName.JINLIAODUAN);
        jinLiaoDuan.setDefaultValue(etJinLiaoDuanSD.getText().toString());
        jinLiaoDuan.setActualValue(etJinLiaoDuanSJ.getText().toString());
        jinLiaoDuan.setLowerLimit(etJinLiaoDuanMin.getText().toString());
        jinLiaoDuan.setUpperLimit(etJinLiaoDuanMax.getText().toString());
        jinLiaoDuan.setCheckDate(etJinLiaoDuanTime.getText().toString());
        jinLiaoDuan.setParameterType(CraftParamsName.TEMPERATURE);
        carftParamsList.add(jinLiaoDuan);

        MobileTrialModeParam houduan = new MobileTrialModeParam();
        houduan.setParameterName(CraftParamsName.HOUDUAN);
        houduan.setDefaultValue(etHouDuanSD.getText().toString());
        houduan.setActualValue(etHouDuanSJ.getText().toString());
        houduan.setLowerLimit(etHouDuanMin.getText().toString());
        houduan.setUpperLimit(etHouDuanMax.getText().toString());
        houduan.setCheckDate(etHouDuanTime.getText().toString());
        houduan.setParameterType(CraftParamsName.TEMPERATURE);
        carftParamsList.add(houduan);

        MobileTrialModeParam zhongJianDuan = new MobileTrialModeParam();
        zhongJianDuan.setParameterName(CraftParamsName.ZHONGJIANDUAN);
        zhongJianDuan.setDefaultValue(etZhongJianDuanSD.getText().toString());
        zhongJianDuan.setActualValue(etZhongJianDuanSJ.getText().toString());
        zhongJianDuan.setLowerLimit(etZhongJianDuanMin.getText().toString());
        zhongJianDuan.setUpperLimit(etZhongJianDuanMax.getText().toString());
        zhongJianDuan.setCheckDate(etZhongJianDuanTime.getText().toString());
        zhongJianDuan.setParameterType(CraftParamsName.TEMPERATURE);
        carftParamsList.add(zhongJianDuan);

        MobileTrialModeParam qianDuan = new MobileTrialModeParam();
        qianDuan.setParameterName(CraftParamsName.QIANDUAN);
        qianDuan.setDefaultValue(etQianDuanSD.getText().toString());
        qianDuan.setActualValue(etQianDuanSJ.getText().toString());
        qianDuan.setLowerLimit(etQianDuanMin.getText().toString());
        qianDuan.setUpperLimit(etQianDuanMax.getText().toString());
        qianDuan.setCheckDate(etQianDuanTime.getText().toString());
        qianDuan.setParameterType(CraftParamsName.TEMPERATURE);
        carftParamsList.add(qianDuan);

        MobileTrialModeParam penZui = new MobileTrialModeParam();
        penZui.setParameterName(CraftParamsName.PENZUI);
        penZui.setDefaultValue(etPenZuiSD.getText().toString());
        penZui.setActualValue(etPenZuiSJ.getText().toString());
        penZui.setLowerLimit(etPenZuiMin.getText().toString());
        penZui.setUpperLimit(etPenZuiMax.getText().toString());
        penZui.setCheckDate(etPenZuiTime.getText().toString());
        penZui.setParameterType(CraftParamsName.TEMPERATURE);
        carftParamsList.add(penZui);

        MobileTrialModeParam sheChu1Su = new MobileTrialModeParam();
        sheChu1Su.setParameterName(CraftParamsName.SHECHU1SU);
        sheChu1Su.setDefaultValue(etSheChu1SuSD.getText().toString());
        sheChu1Su.setActualValue(etSheChu1SuSJ.getText().toString());
        sheChu1Su.setLowerLimit(etSheChu1SuMin.getText().toString());
        sheChu1Su.setUpperLimit(etSheChu1SuMax.getText().toString());
        sheChu1Su.setCheckDate(etSheChu1SuTime.getText().toString());
        sheChu1Su.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(sheChu1Su);


        MobileTrialModeParam sheChu2Su = new MobileTrialModeParam();
        sheChu2Su.setParameterName(CraftParamsName.SHECHU2SU);
        sheChu2Su.setDefaultValue(etSheChu2SuSD.getText().toString());
        sheChu2Su.setActualValue(etSheChu2SuSJ.getText().toString());
        sheChu2Su.setLowerLimit(etSheChu2SuMin.getText().toString());
        sheChu2Su.setUpperLimit(etSheChu2SuMax.getText().toString());
        sheChu2Su.setCheckDate(etSheChu2SuTime.getText().toString());
        sheChu2Su.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(sheChu2Su);

        MobileTrialModeParam sheChu3Su = new MobileTrialModeParam();
        sheChu3Su.setParameterName(CraftParamsName.SHECHU3SU);
        sheChu3Su.setDefaultValue(etSheChu3SuSD.getText().toString());
        sheChu3Su.setActualValue(etSheChu3SuSJ.getText().toString());
        sheChu3Su.setLowerLimit(etSheChu3SuMin.getText().toString());
        sheChu3Su.setUpperLimit(etSheChu3SuMax.getText().toString());
        sheChu3Su.setCheckDate(etSheChu3SuTime.getText().toString());
        sheChu3Su.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(sheChu3Su);

        MobileTrialModeParam sheChu4Su = new MobileTrialModeParam();
        sheChu4Su.setParameterName(CraftParamsName.SHECHU4SU);
        sheChu4Su.setDefaultValue(etSheChu4SuSD.getText().toString());
        sheChu4Su.setActualValue(etSheChu4SuSJ.getText().toString());
        sheChu4Su.setLowerLimit(etSheChu4SuMin.getText().toString());
        sheChu4Su.setUpperLimit(etSheChu4SuMax.getText().toString());
        sheChu4Su.setCheckDate(etSheChu4SuTime.getText().toString());
        sheChu4Su.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(sheChu4Su);

        MobileTrialModeParam oneDuanWeiZhi = new MobileTrialModeParam();
        oneDuanWeiZhi.setParameterName(CraftParamsName.ONEDUANWEIZHI);
        oneDuanWeiZhi.setDefaultValue(et1DuanWeiZhiSD.getText().toString());
        oneDuanWeiZhi.setActualValue(et1DuanWeiZhiSJ.getText().toString());
        oneDuanWeiZhi.setLowerLimit(et1DuanWeiZhiMin.getText().toString());
        oneDuanWeiZhi.setUpperLimit(et1DuanWeiZhiMax.getText().toString());
        oneDuanWeiZhi.setCheckDate(et1DuanWeiZhiTime.getText().toString());
        oneDuanWeiZhi.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(oneDuanWeiZhi);

        MobileTrialModeParam twoDuanWeiZhi = new MobileTrialModeParam();
        twoDuanWeiZhi.setParameterName(CraftParamsName.TWODUANWEIZHI);
        twoDuanWeiZhi.setDefaultValue(et2DuanWeiZhiSD.getText().toString());
        twoDuanWeiZhi.setActualValue(et2DuanWeiZhiSJ.getText().toString());
        twoDuanWeiZhi.setLowerLimit(et2DuanWeiZhiMin.getText().toString());
        twoDuanWeiZhi.setUpperLimit(et2DuanWeiZhiMax.getText().toString());
        twoDuanWeiZhi.setCheckDate(et2DuanWeiZhiTime.getText().toString());
        twoDuanWeiZhi.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(twoDuanWeiZhi);

        MobileTrialModeParam threeDuanWeiZhi = new MobileTrialModeParam();
        threeDuanWeiZhi.setParameterName(CraftParamsName.THREEDUANWEIZHI);
        threeDuanWeiZhi.setDefaultValue(et3DuanWeiZhiSD.getText().toString());
        threeDuanWeiZhi.setActualValue(et3DuanWeiZhiSJ.getText().toString());
        threeDuanWeiZhi.setLowerLimit(et3DuanWeiZhiMin.getText().toString());
        threeDuanWeiZhi.setUpperLimit(et3DuanWeiZhiMax.getText().toString());
        threeDuanWeiZhi.setCheckDate(et3DuanWeiZhiTime.getText().toString());
        threeDuanWeiZhi.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(threeDuanWeiZhi);

        MobileTrialModeParam fourDuanWeiZhi = new MobileTrialModeParam();
        fourDuanWeiZhi.setParameterName(CraftParamsName.FOURDUANWEIZHI);
        fourDuanWeiZhi.setDefaultValue(et4DuanWeiZhiSD.getText().toString());
        fourDuanWeiZhi.setActualValue(et4DuanWeiZhiSJ.getText().toString());
        fourDuanWeiZhi.setLowerLimit(et4DuanWeiZhiMin.getText().toString());
        fourDuanWeiZhi.setUpperLimit(et4DuanWeiZhiMax.getText().toString());
        fourDuanWeiZhi.setCheckDate(et4DuanWeiZhiTime.getText().toString());
        fourDuanWeiZhi.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(fourDuanWeiZhi);


        MobileTrialModeParam vpWeiZhi = new MobileTrialModeParam();
        vpWeiZhi.setParameterName(CraftParamsName.VPWEIZHI);
        vpWeiZhi.setDefaultValue(etVPWeiZhiSD.getText().toString());
        vpWeiZhi.setActualValue(etVPWeiZhiSJ.getText().toString());
        vpWeiZhi.setLowerLimit(etVPWeiZhiMin.getText().toString());
        vpWeiZhi.setUpperLimit(etVPWeiZhiMax.getText().toString());
        vpWeiZhi.setCheckDate(etVPWeiZhiTime.getText().toString());
        vpWeiZhi.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(vpWeiZhi);

        MobileTrialModeParam sheChuYaLi1 = new MobileTrialModeParam();
        sheChuYaLi1.setParameterName(CraftParamsName.SHECHUYALI1);
        sheChuYaLi1.setDefaultValue(etSheChuYaLi1SD.getText().toString());
        sheChuYaLi1.setActualValue(etSheChuYaLi1SJ.getText().toString());
        sheChuYaLi1.setLowerLimit(etSheChuYaLi1Min.getText().toString());
        sheChuYaLi1.setUpperLimit(etSheChuYaLi1Max.getText().toString());
        sheChuYaLi1.setCheckDate(etSheChuYaLi1Time.getText().toString());
        sheChuYaLi1.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(sheChuYaLi1);

        MobileTrialModeParam sheChuYaLi2 = new MobileTrialModeParam();
        sheChuYaLi2.setParameterName(CraftParamsName.SHECHUYALI2);
        sheChuYaLi2.setDefaultValue(etSheChuYaLi2SD.getText().toString());
        sheChuYaLi2.setActualValue(etSheChuYaLi2SJ.getText().toString());
        sheChuYaLi2.setLowerLimit(etSheChuYaLi2Min.getText().toString());
        sheChuYaLi2.setUpperLimit(etSheChuYaLi2Max.getText().toString());
        sheChuYaLi2.setCheckDate(etSheChuYaLi2Time.getText().toString());
        sheChuYaLi2.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(sheChuYaLi2);

        MobileTrialModeParam sheChuYaLi3 = new MobileTrialModeParam();
        sheChuYaLi3.setParameterName(CraftParamsName.SHECHUYALI3);
        sheChuYaLi3.setDefaultValue(etSheChuYaLi3SD.getText().toString());
        sheChuYaLi3.setActualValue(etSheChuYaLi3SJ.getText().toString());
        sheChuYaLi3.setLowerLimit(etSheChuYaLi3Min.getText().toString());
        sheChuYaLi3.setUpperLimit(etSheChuYaLi3Max.getText().toString());
        sheChuYaLi3.setCheckDate(etSheChuYaLi3Time.getText().toString());
        sheChuYaLi3.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(sheChuYaLi3);

        MobileTrialModeParam sheChuYaLi4 = new MobileTrialModeParam();
        sheChuYaLi4.setParameterName(CraftParamsName.SHECHUYALI4);
        sheChuYaLi4.setDefaultValue(etSheChuYaLi4SD.getText().toString());
        sheChuYaLi4.setActualValue(etSheChuYaLi4SJ.getText().toString());
        sheChuYaLi4.setLowerLimit(etSheChuYaLi4Min.getText().toString());
        sheChuYaLi4.setUpperLimit(etSheChuYaLi4Max.getText().toString());
        sheChuYaLi4.setCheckDate(etSheChuYaLi4Time.getText().toString());
        sheChuYaLi4.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(sheChuYaLi4);

        MobileTrialModeParam lengQueShiJian = new MobileTrialModeParam();
        lengQueShiJian.setParameterName(CraftParamsName.LENGQUESHIJIAN);
        lengQueShiJian.setDefaultValue(etLengQueShiJianSD.getText().toString());
        lengQueShiJian.setActualValue(etLengQueShiJianSJ.getText().toString());
        lengQueShiJian.setLowerLimit(etLengQueShiJianMin.getText().toString());
        lengQueShiJian.setUpperLimit(etLengQueShiJianMax.getText().toString());
        lengQueShiJian.setCheckDate(etLengQueShiJianTime.getText().toString());
        lengQueShiJian.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(lengQueShiJian);

        MobileTrialModeParam sheChuShiJian = new MobileTrialModeParam();
        sheChuShiJian.setParameterName(CraftParamsName.SHECHUSHIJIAN);
        sheChuShiJian.setDefaultValue(etSheChuShiJianSD.getText().toString());
        sheChuShiJian.setActualValue(etSheChuShiJianSJ.getText().toString());
        sheChuShiJian.setLowerLimit(etSheChuShiJianMin.getText().toString());
        sheChuShiJian.setUpperLimit(etSheChuShiJianMax.getText().toString());
        sheChuShiJian.setCheckDate(etSheChuShiJianTime.getText().toString());
        sheChuShiJian.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(sheChuShiJian);

        MobileTrialModeParam zhouQiShiJian = new MobileTrialModeParam();
        zhouQiShiJian.setParameterName(CraftParamsName.ZHOUQISHIJIAN);
        zhouQiShiJian.setDefaultValue(etZhouQiShiJianSD.getText().toString());
        zhouQiShiJian.setActualValue(etZhouQiShiJianSJ.getText().toString());
        zhouQiShiJian.setLowerLimit(etZhouQiShiJianMin.getText().toString());
        zhouQiShiJian.setUpperLimit(etZhouQiShiJianMax.getText().toString());
        zhouQiShiJian.setCheckDate(etZhouQiShiJianTime.getText().toString());
        zhouQiShiJian.setParameterType(CraftParamsName.INJECTION);
        carftParamsList.add(zhouQiShiJian);

        MobileTrialModeParam baoYa1Duan = new MobileTrialModeParam();
        baoYa1Duan.setParameterName(CraftParamsName.BAOYA1DUAN);
        baoYa1Duan.setDefaultValue(etBaoYa1DuanSD.getText().toString());
        baoYa1Duan.setActualValue(etBaoYa1DuanSJ.getText().toString());
        baoYa1Duan.setLowerLimit(etBaoYa1DuanMin.getText().toString());
        baoYa1Duan.setUpperLimit(etBaoYa1DuanMax.getText().toString());
        baoYa1Duan.setCheckDate(etBaoYa1DuanTime.getText().toString());
        baoYa1Duan.setParameterType(CraftParamsName.PRESSURE);
        carftParamsList.add(baoYa1Duan);

        MobileTrialModeParam baoYa2Duan = new MobileTrialModeParam();
        baoYa2Duan.setParameterName(CraftParamsName.BAOYA2DUAN);
        baoYa2Duan.setDefaultValue(etBaoYa2DuanSD.getText().toString());
        baoYa2Duan.setActualValue(etBaoYa2DuanSJ.getText().toString());
        baoYa2Duan.setLowerLimit(etBaoYa2DuanMin.getText().toString());
        baoYa2Duan.setUpperLimit(etBaoYa2DuanMax.getText().toString());
        baoYa2Duan.setCheckDate(etBaoYa2DuanTime.getText().toString());
        baoYa2Duan.setParameterType(CraftParamsName.PRESSURE);
        carftParamsList.add(baoYa2Duan);

        MobileTrialModeParam baoYa3Duan = new MobileTrialModeParam();
        baoYa3Duan.setParameterName(CraftParamsName.BAOYA3DUAN);
        baoYa3Duan.setDefaultValue(etBaoYa3DuanSD.getText().toString());
        baoYa3Duan.setActualValue(etBaoYa3DuanSJ.getText().toString());
        baoYa3Duan.setLowerLimit(etBaoYa3DuanMin.getText().toString());
        baoYa3Duan.setUpperLimit(etBaoYa3DuanMax.getText().toString());
        baoYa3Duan.setCheckDate(etBaoYa3DuanTime.getText().toString());
        baoYa3Duan.setParameterType(CraftParamsName.PRESSURE);
        carftParamsList.add(baoYa3Duan);

        MobileTrialModeParam baoYa1Su = new MobileTrialModeParam();
        baoYa1Su.setParameterName(CraftParamsName.BAOYA1SU);
        baoYa1Su.setDefaultValue(etBaoYa1SuSD.getText().toString());
        baoYa1Su.setActualValue(etBaoYa1SuSJ.getText().toString());
        baoYa1Su.setLowerLimit(etBaoYa1SuMin.getText().toString());
        baoYa1Su.setUpperLimit(etBaoYa1SuMax.getText().toString());
        baoYa1Su.setCheckDate(etBaoYa1SuTime.getText().toString());
        baoYa1Su.setParameterType(CraftParamsName.PRESSURE);
        carftParamsList.add(baoYa1Su);

        MobileTrialModeParam baoYa2Su = new MobileTrialModeParam();
        baoYa2Su.setParameterName(CraftParamsName.BAOYA2SU);
        baoYa2Su.setDefaultValue(etBaoYa2SuSD.getText().toString());
        baoYa2Su.setActualValue(etBaoYa2SuSJ.getText().toString());
        baoYa2Su.setLowerLimit(etBaoYa2SuMin.getText().toString());
        baoYa2Su.setUpperLimit(etBaoYa2SuMax.getText().toString());
        baoYa2Su.setCheckDate(etBaoYa2SuTime.getText().toString());
        baoYa2Su.setParameterType(CraftParamsName.PRESSURE);
        carftParamsList.add(baoYa2Su);

        MobileTrialModeParam baoYa3Su = new MobileTrialModeParam();
        baoYa3Su.setParameterName(CraftParamsName.BAOYA3SU);
        baoYa3Su.setDefaultValue(etBaoYa3SuSD.getText().toString());
        baoYa3Su.setActualValue(etBaoYa3SuSJ.getText().toString());
        baoYa3Su.setLowerLimit(etBaoYa3SuMin.getText().toString());
        baoYa3Su.setUpperLimit(etBaoYa3SuMax.getText().toString());
        baoYa3Su.setCheckDate(etBaoYa3SuTime.getText().toString());
        baoYa3Su.setParameterType(CraftParamsName.PRESSURE);
        carftParamsList.add(baoYa3Su);

        MobileTrialModeParam baoYaShiJian1 = new MobileTrialModeParam();
        baoYaShiJian1.setParameterName(CraftParamsName.BAOYASHIJIAN1);
        baoYaShiJian1.setDefaultValue(etBaoYaShiJian1SD.getText().toString());
        baoYaShiJian1.setActualValue(etBaoYaShiJian1SJ.getText().toString());
        baoYaShiJian1.setLowerLimit(etBaoYaShiJian1Min.getText().toString());
        baoYaShiJian1.setUpperLimit(etBaoYaShiJian1Max.getText().toString());
        baoYaShiJian1.setCheckDate(etBaoYaShiJian1Time.getText().toString());
        baoYaShiJian1.setParameterType(CraftParamsName.PRESSURE);
        carftParamsList.add(baoYaShiJian1);

        MobileTrialModeParam baoYaShiJian2 = new MobileTrialModeParam();
        baoYaShiJian2.setParameterName(CraftParamsName.BAOYASHIJIAN2);
        baoYaShiJian2.setDefaultValue(etBaoYaShiJian2SD.getText().toString());
        baoYaShiJian2.setActualValue(etBaoYaShiJian2SJ.getText().toString());
        baoYaShiJian2.setLowerLimit(etBaoYaShiJian2Min.getText().toString());
        baoYaShiJian2.setUpperLimit(etBaoYaShiJian2Max.getText().toString());
        baoYaShiJian2.setCheckDate(etBaoYaShiJian2Time.getText().toString());
        baoYaShiJian2.setParameterType(CraftParamsName.PRESSURE);
        carftParamsList.add(baoYaShiJian2);

        MobileTrialModeParam baoYaShiJian3 = new MobileTrialModeParam();
        baoYaShiJian3.setParameterName(CraftParamsName.BAOYASHIJIAN3);
        baoYaShiJian3.setDefaultValue(etBaoYaShiJian3SD.getText().toString());
        baoYaShiJian3.setActualValue(etBaoYaShiJian3SJ.getText().toString());
        baoYaShiJian3.setLowerLimit(etBaoYaShiJian3Min.getText().toString());
        baoYaShiJian3.setUpperLimit(etBaoYaShiJian3Max.getText().toString());
        baoYaShiJian3.setCheckDate(etBaoYaShiJian3Time.getText().toString());
        baoYaShiJian3.setParameterType(CraftParamsName.PRESSURE);
        carftParamsList.add(baoYaShiJian3);

        MobileTrialModeParam luoGanZhuanSu = new MobileTrialModeParam();
        luoGanZhuanSu.setParameterName(CraftParamsName.LUOGANZHUANSU);
        luoGanZhuanSu.setDefaultValue(etLuoGanZhuanSuSD.getText().toString());
        luoGanZhuanSu.setActualValue(etLuoGanZhuanSuSJ.getText().toString());
        luoGanZhuanSu.setLowerLimit(etLuoGanZhuanSuMin.getText().toString());
        luoGanZhuanSu.setUpperLimit(etLuoGanZhuanSuMax.getText().toString());
        luoGanZhuanSu.setCheckDate(etLuoGanZhuanSuTime.getText().toString());
        luoGanZhuanSu.setParameterType(CraftParamsName.PRESSURE);
        carftParamsList.add(luoGanZhuanSu);

        MobileTrialModeParam beiYa = new MobileTrialModeParam();
        beiYa.setParameterName(CraftParamsName.BEIYA);
        beiYa.setDefaultValue(etBeiYaSD.getText().toString());
        beiYa.setActualValue(etBeiYaSJ.getText().toString());
        beiYa.setLowerLimit(etBeiYaMin.getText().toString());
        beiYa.setUpperLimit(etBeiYaMax.getText().toString());
        beiYa.setCheckDate(etBeiYaTime.getText().toString());
        beiYa.setParameterType(CraftParamsName.LOCKEDMODE);
        carftParamsList.add(beiYa);

        MobileTrialModeParam songTuiWeiZhi = new MobileTrialModeParam();
        songTuiWeiZhi.setParameterName(CraftParamsName.SONGTUIWEIZHI);
        songTuiWeiZhi.setDefaultValue(etSongTuiWeiZhiSD.getText().toString());
        songTuiWeiZhi.setActualValue(etSongTuiWeiZhiSJ.getText().toString());
        songTuiWeiZhi.setLowerLimit(etSongTuiWeiZhiMin.getText().toString());
        songTuiWeiZhi.setUpperLimit(etSongTuiWeiZhiMax.getText().toString());
        songTuiWeiZhi.setCheckDate(etSongTuiWeiZhiTime.getText().toString());
        songTuiWeiZhi.setParameterType(CraftParamsName.LOCKEDMODE);
        carftParamsList.add(songTuiWeiZhi);

        MobileTrialModeParam yongLiaoLiang = new MobileTrialModeParam();
        yongLiaoLiang.setParameterName(CraftParamsName.YONGLIANGLIANG);
        yongLiaoLiang.setDefaultValue(etYongLiaoLiangSD.getText().toString());
        yongLiaoLiang.setActualValue(etYongLiaoLiangSJ.getText().toString());
        yongLiaoLiang.setLowerLimit(etYongLiaoLiangMin.getText().toString());
        yongLiaoLiang.setUpperLimit(etYongLiaoLiangMax.getText().toString());
        yongLiaoLiang.setCheckDate(etYongLiaoLiangTime.getText().toString());
        yongLiaoLiang.setParameterType(CraftParamsName.LOCKEDMODE);
        carftParamsList.add(yongLiaoLiang);

        MobileTrialModeParam huanChongLiang = new MobileTrialModeParam();
        huanChongLiang.setParameterName(CraftParamsName.HUANCHONGLIANG);
        huanChongLiang.setDefaultValue(etHuanChongLiangSD.getText().toString());
        huanChongLiang.setActualValue(etHuanChongLiangSJ.getText().toString());
        huanChongLiang.setLowerLimit(etHuanChongLiangMin.getText().toString());
        huanChongLiang.setUpperLimit(etHuanChongLiangMax.getText().toString());
        huanChongLiang.setCheckDate(etHuanChongLiangTime.getText().toString());
        huanChongLiang.setParameterType(CraftParamsName.LOCKEDMODE);
        carftParamsList.add(huanChongLiang);

        MobileTrialModeParam muJuBaoHuYaLi = new MobileTrialModeParam();
        muJuBaoHuYaLi.setParameterName(CraftParamsName.MUJUBAOHUYALI);
        muJuBaoHuYaLi.setDefaultValue(etMuJuBaoHuYaLiSD.getText().toString());
        muJuBaoHuYaLi.setActualValue(etMuJuBaoHuYaLiSJ.getText().toString());
        muJuBaoHuYaLi.setLowerLimit(etMuJuBaoHuYaLiMin.getText().toString());
        muJuBaoHuYaLi.setUpperLimit(etMuJuBaoHuYaLiMax.getText().toString());
        muJuBaoHuYaLi.setCheckDate(etMuJuBaoHuYaLiTime.getText().toString());
        muJuBaoHuYaLi.setParameterType(CraftParamsName.LOCKEDMODE);
        carftParamsList.add(muJuBaoHuYaLi);

        MobileTrialModeParam muJuBaoHuWeiZhi = new MobileTrialModeParam();
        muJuBaoHuWeiZhi.setParameterName(CraftParamsName.MUJUBAOHUWEIZHI);
        muJuBaoHuWeiZhi.setDefaultValue(etMuJuBaoHuWeiZhiSD.getText().toString());
        muJuBaoHuWeiZhi.setActualValue(etMuJuBaoHuWeiZhiSJ.getText().toString());
        muJuBaoHuWeiZhi.setLowerLimit(etMuJuBaoHuWeiZhiMin.getText().toString());
        muJuBaoHuWeiZhi.setUpperLimit(etMuJuBaoHuWeiZhiMax.getText().toString());
        muJuBaoHuWeiZhi.setCheckDate(etMuJuBaoHuWeiZhiTime.getText().toString());
        muJuBaoHuWeiZhi.setParameterType(CraftParamsName.LOCKEDMODE);
        carftParamsList.add(muJuBaoHuWeiZhi);

        MobileTrialModeParam suoMoLi = new MobileTrialModeParam();
        suoMoLi.setParameterName(CraftParamsName.SUOMOLI);
        suoMoLi.setDefaultValue(etSuoMoLiSD.getText().toString());
        suoMoLi.setActualValue(etSuoMoLiSJ.getText().toString());
        suoMoLi.setLowerLimit(etSuoMoLiMin.getText().toString());
        suoMoLi.setUpperLimit(etSuoMoLiMax.getText().toString());
        suoMoLi.setCheckDate(etSuoMoLiTime.getText().toString());
        suoMoLi.setParameterType(CraftParamsName.LOCKEDMODE);
        carftParamsList.add(suoMoLi);

        MobileTrialModeParam dingChuWeiZhi = new MobileTrialModeParam();
        dingChuWeiZhi.setParameterName(CraftParamsName.DINGCHUWEIZHI);
        dingChuWeiZhi.setDefaultValue(etDingChuWeiZhiSD.getText().toString());
        dingChuWeiZhi.setActualValue(etDingChuWeiZhiSJ.getText().toString());
        dingChuWeiZhi.setLowerLimit(etDingChuWeiZhiMin.getText().toString());
        dingChuWeiZhi.setUpperLimit(etDingChuWeiZhiMax.getText().toString());
        dingChuWeiZhi.setCheckDate(etDingChuWeiZhiTime.getText().toString());
        dingChuWeiZhi.setParameterType(CraftParamsName.LOCKEDMODE);
        carftParamsList.add(dingChuWeiZhi);

        MobileTrialModeParam dingMoWenDu = new MobileTrialModeParam();
        dingMoWenDu.setParameterName(CraftParamsName.DINGMOWENDU);
        dingMoWenDu.setDefaultValue(etDingMoWenDuSD.getText().toString());
        dingMoWenDu.setActualValue(etDingMoWenDuSJ.getText().toString());
        dingMoWenDu.setLowerLimit(etDingMoWenDuMin.getText().toString());
        dingMoWenDu.setUpperLimit(etDingMoWenDuMax.getText().toString());
        dingMoWenDu.setCheckDate(etDingMoWenDuTime.getText().toString());
        dingMoWenDu.setParameterType(CraftParamsName.LOCKEDMODE);
        carftParamsList.add(dingMoWenDu);

        MobileTrialModeParam dongMoWenDu = new MobileTrialModeParam();
        dongMoWenDu.setParameterName(CraftParamsName.DONGMOWENDU);
        dongMoWenDu.setDefaultValue(etDongMoWenDuSD.getText().toString());
        dongMoWenDu.setActualValue(etDongMoWenDuSJ.getText().toString());
        dongMoWenDu.setLowerLimit(etDongMoWenDuMin.getText().toString());
        dongMoWenDu.setUpperLimit(etDongMoWenDuMax.getText().toString());
        dongMoWenDu.setCheckDate(etDongMoWenDuTime.getText().toString());
        dongMoWenDu.setParameterType(CraftParamsName.LOCKEDMODE);
        carftParamsList.add(dongMoWenDu);

        MobileTrialModeParam hongLiaoWenDu = new MobileTrialModeParam();
        hongLiaoWenDu.setParameterName(CraftParamsName.HONGLIAOWENDU);
        hongLiaoWenDu.setDefaultValue(etHongLiaoWenDuSD.getText().toString());
        hongLiaoWenDu.setActualValue(etHongLiaoWenDuSJ.getText().toString());
        hongLiaoWenDu.setLowerLimit(etHongLiaoWenDuMin.getText().toString());
        hongLiaoWenDu.setUpperLimit(etHongLiaoWenDuMax.getText().toString());
        hongLiaoWenDu.setCheckDate(etHongLiaoWenDuTime.getText().toString());
        hongLiaoWenDu.setParameterType(CraftParamsName.OTHER);
        carftParamsList.add(hongLiaoWenDu);

        MobileTrialModeParam huiLiaoBiLi = new MobileTrialModeParam();
        huiLiaoBiLi.setParameterName(CraftParamsName.HUILIAOBILI);
        huiLiaoBiLi.setDefaultValue(etHuiLiaoBiLiSD.getText().toString());
        huiLiaoBiLi.setActualValue(etHuiLiaoBiLiSJ.getText().toString());
        huiLiaoBiLi.setLowerLimit(etHuiLiaoBiLiMin.getText().toString());
        huiLiaoBiLi.setUpperLimit(etHuiLiaoBiLiMax.getText().toString());
        huiLiaoBiLi.setCheckDate(etHuiLiaoBiLiTime.getText().toString());
        huiLiaoBiLi.setParameterType(CraftParamsName.OTHER);
        carftParamsList.add(huiLiaoBiLi);

        MobileTrialModeParam qianJianShuLiang = new MobileTrialModeParam();
        qianJianShuLiang.setParameterName(CraftParamsName.QIANJIANSHULIANG);
        qianJianShuLiang.setDefaultValue(etQianJianShuLiangSD.getText().toString());
        qianJianShuLiang.setActualValue(etQianJianShuLiangSJ.getText().toString());
        qianJianShuLiang.setLowerLimit(etQianJianShuLiangMin.getText().toString());
        qianJianShuLiang.setUpperLimit(etQianJianShuLiangMax.getText().toString());
        qianJianShuLiang.setCheckDate(etQianJianShuLiangTime.getText().toString());
        qianJianShuLiang.setParameterType(CraftParamsName.OTHER);
        carftParamsList.add(qianJianShuLiang);

        MobileTrialModeParam qianJianBianHao = new MobileTrialModeParam();
        qianJianBianHao.setParameterName(CraftParamsName.QIANJIANBIANHAO);
        qianJianBianHao.setDefaultValue(etQianJianBianHaoSD.getText().toString());
        qianJianBianHao.setActualValue(etQianJianBianHaoSJ.getText().toString());
        qianJianBianHao.setLowerLimit(etQianJianBianHaoMin.getText().toString());
        qianJianBianHao.setUpperLimit(etQianJianBianHaoMax.getText().toString());
        qianJianBianHao.setCheckDate(etQianJianBianHaoTime.getText().toString());
        qianJianBianHao.setParameterType(CraftParamsName.OTHER);
        carftParamsList.add(qianJianBianHao);

        /*原料使用范围*/
        MobileTrialModeParam ganZaoWenDu = new MobileTrialModeParam();
        ganZaoWenDu.setParameterName(CraftParamsName.GANZAOWENDU);
        ganZaoWenDu.setDefaultValue(etGanZaoWenDu.getText().toString());
        ganZaoWenDu.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(ganZaoWenDu);

        MobileTrialModeParam ganZaoShiJian = new MobileTrialModeParam();
        ganZaoShiJian.setParameterName(CraftParamsName.GANZAOSHIJIAN);
        ganZaoShiJian.setDefaultValue(etGanZaoShiJian.getText().toString());
        ganZaoShiJian.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(ganZaoShiJian);

        MobileTrialModeParam yuanLiaoHanShuiFen = new MobileTrialModeParam();
        yuanLiaoHanShuiFen.setParameterName(CraftParamsName.YUANLIAOHANSHUIFEN);
        yuanLiaoHanShuiFen.setDefaultValue(etYuanLiaoHanShuiFen.getText().toString());
        yuanLiaoHanShuiFen.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(yuanLiaoHanShuiFen);

        MobileTrialModeParam chengXingWenDu = new MobileTrialModeParam();
        chengXingWenDu.setParameterName(CraftParamsName.CHENGXINGWENDU);
        chengXingWenDu.setDefaultValue(etChengXingWenDu.getText().toString());
        chengXingWenDu.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(chengXingWenDu);

        MobileTrialModeParam muJuWenDu = new MobileTrialModeParam();
        muJuWenDu.setParameterName(CraftParamsName.MUJUWENDU);
        muJuWenDu.setDefaultValue(etMuJuWenDu.getText().toString());
        muJuWenDu.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(muJuWenDu);

        MobileTrialModeParam sheChuYaLi = new MobileTrialModeParam();
        sheChuYaLi.setParameterName(CraftParamsName.SHECHUYALI);
        sheChuYaLi.setDefaultValue(etSheChuYaLi.getText().toString());
        sheChuYaLi.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(sheChuYaLi);

        MobileTrialModeParam sheChuShiJian2 = new MobileTrialModeParam();
        sheChuShiJian2.setParameterName(CraftParamsName.SHECHUSHIJIAN2);
        sheChuShiJian2.setDefaultValue(etSheChuShiJian.getText().toString());
        sheChuShiJian2.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(sheChuShiJian2);

        MobileTrialModeParam sheChuSuDu = new MobileTrialModeParam();
        sheChuSuDu.setParameterName(CraftParamsName.SHECHUSUDU);
        sheChuSuDu.setDefaultValue(etSheChuSuDu.getText().toString());
        sheChuSuDu.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(sheChuSuDu);

        MobileTrialModeParam baoYaYaLi = new MobileTrialModeParam();
        baoYaYaLi.setParameterName(CraftParamsName.BAOYAYALI);
        baoYaYaLi.setDefaultValue(etBaoYaYali.getText().toString());
        baoYaYaLi.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(baoYaYaLi);

        MobileTrialModeParam baoYaShiJian = new MobileTrialModeParam();
        baoYaShiJian.setParameterName(CraftParamsName.BAOYASHIJIAN);
        baoYaShiJian.setDefaultValue(etBaoYaShiJian.getText().toString());
        baoYaShiJian.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(baoYaShiJian);

        MobileTrialModeParam suoMoLi2 = new MobileTrialModeParam();
        suoMoLi2.setParameterName(CraftParamsName.SUOMOLI2);
        suoMoLi2.setDefaultValue(etBaoYaShiJian.getText().toString());
        suoMoLi2.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(suoMoLi2);

        MobileTrialModeParam kaiHeMoShiJian = new MobileTrialModeParam();
        kaiHeMoShiJian.setParameterName(CraftParamsName.KAIHEMOSHIJIAN);
        kaiHeMoShiJian.setDefaultValue(etKaiHeMoShiJian.getText().toString());
        kaiHeMoShiJian.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(kaiHeMoShiJian);

        MobileTrialModeParam lengQueShiJian2 = new MobileTrialModeParam();
        lengQueShiJian2.setParameterName(CraftParamsName.LENGQUESHIJIAN2);
        lengQueShiJian2.setDefaultValue(etLengQueShijian.getText().toString());
        lengQueShiJian2.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(lengQueShiJian2);

        MobileTrialModeParam luoGanSuDu = new MobileTrialModeParam();
        luoGanSuDu.setParameterName(CraftParamsName.LUOGANZHUANSU2);
        luoGanSuDu.setDefaultValue(etLuoGanSuDu.getText().toString());
        luoGanSuDu.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(luoGanSuDu);

        MobileTrialModeParam dingTuiChuShiJian = new MobileTrialModeParam();
        dingTuiChuShiJian.setParameterName(CraftParamsName.DINGTUICHUSHIJIAN);
        dingTuiChuShiJian.setDefaultValue(etDingTuiChuShijian.getText().toString());
        dingTuiChuShiJian.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(dingTuiChuShiJian);

        MobileTrialModeParam beiYa2 = new MobileTrialModeParam();
        beiYa2.setParameterName(CraftParamsName.BEIYA2);
        beiYa2.setDefaultValue(etBeiYa.getText().toString());
        beiYa2.setParameterType(CraftParamsName.METERIALUSERANGE);
        carftParamsList.add(beiYa2);
        return carftParamsList;
    }

    private void showHistoryShiMuCraft(List<CraftConfirmResbean> craftConfirmResbeans) {
        try {
            if (craftConfirmResbeans == null || craftConfirmResbeans.size() == 0) {
                return;
            }
            for (CraftConfirmResbean param : craftConfirmResbeans) {
                String parameterName = param.getParameterName();
                if (parameterName.equals(CraftParamsName.JINLIAODUAN)) {
                    etJinLiaoDuanSD.setText(param.getDefaultValue());
                    etJinLiaoDuanSJ.setText(param.getActualValue());
                    etJinLiaoDuanMin.setText(param.getLowerLimit());
                    etJinLiaoDuanMax.setText(param.getUpperLimit());
                    etJinLiaoDuanTime.setText(param.getDescription());
                    etJinLiaoDuanSD.setEnabled(false);
                    etJinLiaoDuanSJ.setEnabled(false);
                    etJinLiaoDuanMin.setEnabled(false);
                    etJinLiaoDuanMax.setEnabled(false);
                    etJinLiaoDuanTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.HOUDUAN)) {
                    etHouDuanSD.setText(param.getDefaultValue());
                    etHouDuanSJ.setText(param.getActualValue());
                    etHouDuanMin.setText(param.getLowerLimit());
                    etHouDuanMax.setText(param.getUpperLimit());
                    etHouDuanTime.setText(param.getDescription());
                    etHouDuanSD.setEnabled(false);
                    etHouDuanSJ.setEnabled(false);
                    etHouDuanMin.setEnabled(false);
                    etHouDuanMax.setEnabled(false);
                    etHouDuanTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.ZHONGJIANDUAN)) {
                    etZhongJianDuanSD.setText(param.getDefaultValue());
                    etZhongJianDuanSJ.setText(param.getActualValue());
                    etZhongJianDuanMin.setText(param.getLowerLimit());
                    etZhongJianDuanMax.setText(param.getUpperLimit());
                    etZhongJianDuanTime.setText(param.getDescription());
                    etZhongJianDuanSD.setEnabled(false);
                    etZhongJianDuanSJ.setEnabled(false);
                    etZhongJianDuanMin.setEnabled(false);
                    etZhongJianDuanMax.setEnabled(false);
                    etZhongJianDuanTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.QIANDUAN)) {
                    etQianDuanSD.setText(param.getDefaultValue());
                    etQianDuanSJ.setText(param.getActualValue());
                    etQianDuanMin.setText(param.getLowerLimit());
                    etQianDuanMax.setText(param.getUpperLimit());
                    etQianDuanTime.setText(param.getDescription());
                    etQianDuanSD.setEnabled(false);
                    etQianDuanSJ.setEnabled(false);
                    etQianDuanMin.setEnabled(false);
                    etQianDuanMax.setEnabled(false);
                    etQianDuanTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.PENZUI)) {
                    etPenZuiSD.setText(param.getDefaultValue());
                    etPenZuiSJ.setText(param.getActualValue());
                    etPenZuiMin.setText(param.getLowerLimit());
                    etPenZuiMax.setText(param.getUpperLimit());
                    etPenZuiTime.setText(param.getDescription());
                    etPenZuiSD.setEnabled(false);
                    etPenZuiSJ.setEnabled(false);
                    etPenZuiMin.setEnabled(false);
                    etPenZuiMax.setEnabled(false);
                    etPenZuiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHU1SU)) {
                    etSheChu1SuSD.setText(param.getDefaultValue());
                    etSheChu1SuSJ.setText(param.getActualValue());
                    etSheChu1SuMin.setText(param.getLowerLimit());
                    etSheChu1SuMax.setText(param.getUpperLimit());
                    etSheChu1SuTime.setText(param.getDescription());
                    etSheChu1SuSD.setEnabled(false);
                    etSheChu1SuSJ.setEnabled(false);
                    etSheChu1SuMin.setEnabled(false);
                    etSheChu1SuMax.setEnabled(false);
                    etSheChu1SuTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHU2SU)) {
                    etSheChu2SuSD.setText(param.getDefaultValue());
                    etSheChu2SuSJ.setText(param.getActualValue());
                    etSheChu2SuMin.setText(param.getLowerLimit());
                    etSheChu2SuMax.setText(param.getUpperLimit());
                    etSheChu2SuTime.setText(param.getDescription());
                    etSheChu2SuSD.setEnabled(false);
                    etSheChu2SuSJ.setEnabled(false);
                    etSheChu2SuMin.setEnabled(false);
                    etSheChu2SuMax.setEnabled(false);
                    etSheChu2SuTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHU3SU)) {
                    etSheChu3SuSD.setText(param.getDefaultValue());
                    etSheChu3SuSJ.setText(param.getActualValue());
                    etSheChu3SuMin.setText(param.getLowerLimit());
                    etSheChu3SuMax.setText(param.getUpperLimit());
                    etSheChu3SuTime.setText(param.getDescription());
                    etSheChu3SuSD.setEnabled(false);
                    etSheChu3SuSJ.setEnabled(false);
                    etSheChu3SuMin.setEnabled(false);
                    etSheChu3SuMax.setEnabled(false);
                    etSheChu3SuTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHU4SU)) {
                    etSheChu4SuSD.setText(param.getDefaultValue());
                    etSheChu4SuSJ.setText(param.getActualValue());
                    etSheChu4SuMin.setText(param.getLowerLimit());
                    etSheChu4SuMax.setText(param.getUpperLimit());
                    etSheChu4SuTime.setText(param.getDescription());
                    etSheChu4SuSD.setEnabled(false);
                    etSheChu4SuSJ.setEnabled(false);
                    etSheChu4SuMin.setEnabled(false);
                    etSheChu4SuMax.setEnabled(false);
                    etSheChu4SuTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.ONEDUANWEIZHI)) {
                    et1DuanWeiZhiSD.setText(param.getDefaultValue());
                    et1DuanWeiZhiSJ.setText(param.getActualValue());
                    et1DuanWeiZhiMin.setText(param.getLowerLimit());
                    et1DuanWeiZhiMax.setText(param.getUpperLimit());
                    et1DuanWeiZhiTime.setText(param.getDescription());
                    et1DuanWeiZhiSD.setEnabled(false);
                    et1DuanWeiZhiSJ.setEnabled(false);
                    et1DuanWeiZhiMin.setEnabled(false);
                    et1DuanWeiZhiMax.setEnabled(false);
                    et1DuanWeiZhiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.TWODUANWEIZHI)) {
                    et2DuanWeiZhiSD.setText(param.getDefaultValue());
                    et2DuanWeiZhiSJ.setText(param.getActualValue());
                    et2DuanWeiZhiMin.setText(param.getLowerLimit());
                    et2DuanWeiZhiMax.setText(param.getUpperLimit());
                    et2DuanWeiZhiTime.setText(param.getDescription());
                    et2DuanWeiZhiSD.setEnabled(false);
                    et2DuanWeiZhiSJ.setEnabled(false);
                    et2DuanWeiZhiMin.setEnabled(false);
                    et2DuanWeiZhiMax.setEnabled(false);
                    et2DuanWeiZhiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.THREEDUANWEIZHI)) {
                    et3DuanWeiZhiSD.setText(param.getDefaultValue());
                    et3DuanWeiZhiSJ.setText(param.getActualValue());
                    et3DuanWeiZhiMin.setText(param.getLowerLimit());
                    et3DuanWeiZhiMax.setText(param.getUpperLimit());
                    et3DuanWeiZhiTime.setText(param.getDescription());
                    et3DuanWeiZhiSD.setEnabled(false);
                    et3DuanWeiZhiSJ.setEnabled(false);
                    et3DuanWeiZhiMin.setEnabled(false);
                    et3DuanWeiZhiMax.setEnabled(false);
                    et3DuanWeiZhiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.FOURDUANWEIZHI)) {
                    et4DuanWeiZhiSD.setText(param.getDefaultValue());
                    et4DuanWeiZhiSJ.setText(param.getActualValue());
                    et4DuanWeiZhiMin.setText(param.getLowerLimit());
                    et4DuanWeiZhiMax.setText(param.getUpperLimit());
                    et4DuanWeiZhiTime.setText(param.getDescription());
                    et4DuanWeiZhiSD.setEnabled(false);
                    et4DuanWeiZhiSJ.setEnabled(false);
                    et4DuanWeiZhiMin.setEnabled(false);
                    et4DuanWeiZhiMax.setEnabled(false);
                    et4DuanWeiZhiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.VPWEIZHI)) {
                    etVPWeiZhiSD.setText(param.getDefaultValue());
                    etVPWeiZhiSJ.setText(param.getActualValue());
                    etVPWeiZhiMin.setText(param.getLowerLimit());
                    etVPWeiZhiMax.setText(param.getUpperLimit());
                    etVPWeiZhiTime.setText(param.getDescription());
                    etVPWeiZhiSD.setEnabled(false);
                    etVPWeiZhiSJ.setEnabled(false);
                    etVPWeiZhiMin.setEnabled(false);
                    etVPWeiZhiMax.setEnabled(false);
                    etVPWeiZhiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHUYALI1)) {
                    etSheChuYaLi1SD.setText(param.getDefaultValue());
                    etSheChuYaLi1SJ.setText(param.getActualValue());
                    etSheChuYaLi1Min.setText(param.getLowerLimit());
                    etSheChuYaLi1Max.setText(param.getUpperLimit());
                    etSheChuYaLi1Time.setText(param.getDescription());
                    etSheChuYaLi1SD.setEnabled(false);
                    etSheChuYaLi1SJ.setEnabled(false);
                    etSheChuYaLi1Min.setEnabled(false);
                    etSheChuYaLi1Max.setEnabled(false);
                    etSheChuYaLi1Time.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHUYALI2)) {
                    etSheChuYaLi2SD.setText(param.getDefaultValue());
                    etSheChuYaLi2SJ.setText(param.getActualValue());
                    etSheChuYaLi2Min.setText(param.getLowerLimit());
                    etSheChuYaLi2Max.setText(param.getUpperLimit());
                    etSheChuYaLi2Time.setText(param.getDescription());
                    etSheChuYaLi2SD.setEnabled(false);
                    etSheChuYaLi2SJ.setEnabled(false);
                    etSheChuYaLi2Min.setEnabled(false);
                    etSheChuYaLi2Max.setEnabled(false);
                    etSheChuYaLi2Time.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHUYALI3)) {
                    etSheChuYaLi3SD.setText(param.getDefaultValue());
                    etSheChuYaLi3SJ.setText(param.getActualValue());
                    etSheChuYaLi3Min.setText(param.getLowerLimit());
                    etSheChuYaLi3Max.setText(param.getUpperLimit());
                    etSheChuYaLi3Time.setText(param.getDescription());
                    etSheChuYaLi3SD.setEnabled(false);
                    etSheChuYaLi3SJ.setEnabled(false);
                    etSheChuYaLi3Min.setEnabled(false);
                    etSheChuYaLi3Max.setEnabled(false);
                    etSheChuYaLi3Time.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHUYALI4)) {
                    etSheChuYaLi4SD.setText(param.getDefaultValue());
                    etSheChuYaLi4SJ.setText(param.getActualValue());
                    etSheChuYaLi4Min.setText(param.getLowerLimit());
                    etSheChuYaLi4Max.setText(param.getUpperLimit());
                    etSheChuYaLi4Time.setText(param.getDescription());
                    etSheChuYaLi4SD.setEnabled(false);
                    etSheChuYaLi4SJ.setEnabled(false);
                    etSheChuYaLi4Min.setEnabled(false);
                    etSheChuYaLi4Max.setEnabled(false);
                    etSheChuYaLi4Time.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.LENGQUESHIJIAN)) {
                    etLengQueShiJianSD.setText(param.getDefaultValue());
                    etLengQueShiJianSJ.setText(param.getActualValue());
                    etLengQueShiJianMin.setText(param.getLowerLimit());
                    etLengQueShiJianMax.setText(param.getUpperLimit());
                    etLengQueShiJianTime.setText(param.getDescription());
                    etLengQueShiJianSD.setEnabled(false);
                    etLengQueShiJianSJ.setEnabled(false);
                    etLengQueShiJianMin.setEnabled(false);
                    etLengQueShiJianMax.setEnabled(false);
                    etLengQueShiJianTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHUSHIJIAN)) {
                    etSheChuShiJianSD.setText(param.getDefaultValue());
                    etSheChuShiJianSJ.setText(param.getActualValue());
                    etSheChuShiJianMin.setText(param.getLowerLimit());
                    etSheChuShiJianMax.setText(param.getUpperLimit());
                    etSheChuShiJianTime.setText(param.getDescription());
                    etSheChuShiJianSD.setEnabled(false);
                    etSheChuShiJianSJ.setEnabled(false);
                    etSheChuShiJianMin.setEnabled(false);
                    etSheChuShiJianMax.setEnabled(false);
                    etSheChuShiJianTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.ZHOUQISHIJIAN)) {
                    etZhouQiShiJianSD.setText(param.getDefaultValue());
                    etZhouQiShiJianSJ.setText(param.getActualValue());
                    etZhouQiShiJianMin.setText(param.getLowerLimit());
                    etZhouQiShiJianMax.setText(param.getUpperLimit());
                    etZhouQiShiJianTime.setText(param.getDescription());
                    etZhouQiShiJianSD.setEnabled(false);
                    etZhouQiShiJianSJ.setEnabled(false);
                    etZhouQiShiJianMin.setEnabled(false);
                    etZhouQiShiJianMax.setEnabled(false);
                    etZhouQiShiJianTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BAOYA1DUAN)) {
                    etBaoYa1DuanSD.setText(param.getDefaultValue());
                    etBaoYa1DuanSJ.setText(param.getActualValue());
                    etBaoYa1DuanMin.setText(param.getLowerLimit());
                    etBaoYa1DuanMax.setText(param.getUpperLimit());
                    etBaoYa1DuanTime.setText(param.getDescription());
                    etBaoYa1DuanSD.setEnabled(false);
                    etBaoYa1DuanSJ.setEnabled(false);
                    etBaoYa1DuanMin.setEnabled(false);
                    etBaoYa1DuanMax.setEnabled(false);
                    etBaoYa1DuanTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BAOYA2DUAN)) {
                    etBaoYa2DuanSD.setText(param.getDefaultValue());
                    etBaoYa2DuanSJ.setText(param.getActualValue());
                    etBaoYa2DuanMin.setText(param.getLowerLimit());
                    etBaoYa2DuanMax.setText(param.getUpperLimit());
                    etBaoYa2DuanTime.setText(param.getDescription());
                    etBaoYa2DuanSD.setEnabled(false);
                    etBaoYa2DuanSJ.setEnabled(false);
                    etBaoYa2DuanMin.setEnabled(false);
                    etBaoYa2DuanMax.setEnabled(false);
                    etBaoYa2DuanTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BAOYA3DUAN)) {
                    etBaoYa3DuanSD.setText(param.getDefaultValue());
                    etBaoYa3DuanSJ.setText(param.getActualValue());
                    etBaoYa3DuanMin.setText(param.getLowerLimit());
                    etBaoYa3DuanMax.setText(param.getUpperLimit());
                    etBaoYa3DuanTime.setText(param.getDescription());
                    etBaoYa3DuanTime.setEnabled(false);
                    etBaoYa3DuanSJ.setEnabled(false);
                    etBaoYa3DuanMin.setEnabled(false);
                    etBaoYa3DuanMax.setEnabled(false);
                    etBaoYa3DuanTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BAOYA1SU)) {
                    etBaoYa1SuSD.setText(param.getDefaultValue());
                    etBaoYa1SuSJ.setText(param.getActualValue());
                    etBaoYa1SuMin.setText(param.getLowerLimit());
                    etBaoYa1SuMax.setText(param.getUpperLimit());
                    etBaoYa1SuTime.setText(param.getDescription());
                    etBaoYa1SuSD.setEnabled(false);
                    etBaoYa1SuSJ.setEnabled(false);
                    etBaoYa1SuMin.setEnabled(false);
                    etBaoYa1SuMax.setEnabled(false);
                    etBaoYa1SuTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BAOYA2SU)) {
                    etBaoYa2SuSD.setText(param.getDefaultValue());
                    etBaoYa2SuSJ.setText(param.getActualValue());
                    etBaoYa2SuMin.setText(param.getLowerLimit());
                    etBaoYa2SuMax.setText(param.getUpperLimit());
                    etBaoYa2SuTime.setText(param.getDescription());
                    etBaoYa2SuSD.setEnabled(false);
                    etBaoYa2SuSJ.setEnabled(false);
                    etBaoYa2SuMin.setEnabled(false);
                    etBaoYa2SuMax.setEnabled(false);
                    etBaoYa2SuTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BAOYA3SU)) {
                    etBaoYa3SuSD.setText(param.getDefaultValue());
                    etBaoYa3SuSJ.setText(param.getActualValue());
                    etBaoYa3SuMin.setText(param.getLowerLimit());
                    etBaoYa3SuMax.setText(param.getUpperLimit());
                    etBaoYa3SuTime.setText(param.getDescription());
                    etBaoYa3SuSD.setEnabled(false);
                    etBaoYa3SuSJ.setEnabled(false);
                    etBaoYa3SuMin.setEnabled(false);
                    etBaoYa3SuMax.setEnabled(false);
                    etBaoYa3SuTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BAOYASHIJIAN1)) {
                    etBaoYaShiJian1SD.setText(param.getDefaultValue());
                    etBaoYaShiJian1SJ.setText(param.getActualValue());
                    etBaoYaShiJian1Min.setText(param.getLowerLimit());
                    etBaoYaShiJian1Max.setText(param.getUpperLimit());
                    etBaoYaShiJian1Time.setText(param.getDescription());
                    etBaoYaShiJian1SD.setEnabled(false);
                    etBaoYaShiJian1SJ.setEnabled(false);
                    etBaoYaShiJian1Min.setEnabled(false);
                    etBaoYaShiJian1Max.setEnabled(false);
                    etBaoYaShiJian1Time.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BAOYASHIJIAN2)) {
                    etBaoYaShiJian2SD.setText(param.getDefaultValue());
                    etBaoYaShiJian2SJ.setText(param.getActualValue());
                    etBaoYaShiJian2Min.setText(param.getLowerLimit());
                    etBaoYaShiJian2Max.setText(param.getUpperLimit());
                    etBaoYaShiJian2Time.setText(param.getDescription());
                    etBaoYaShiJian2SD.setEnabled(false);
                    etBaoYaShiJian2SJ.setEnabled(false);
                    etBaoYaShiJian2Min.setEnabled(false);
                    etBaoYaShiJian2Max.setEnabled(false);
                    etBaoYaShiJian2Time.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BAOYASHIJIAN3)) {
                    etBaoYaShiJian3SD.setText(param.getDefaultValue());
                    etBaoYaShiJian3SJ.setText(param.getActualValue());
                    etBaoYaShiJian3Min.setText(param.getLowerLimit());
                    etBaoYaShiJian3Max.setText(param.getUpperLimit());
                    etBaoYaShiJian3Time.setText(param.getDescription());
                    etBaoYaShiJian3SD.setEnabled(false);
                    etBaoYaShiJian3SJ.setEnabled(false);
                    etBaoYaShiJian3Min.setEnabled(false);
                    etBaoYaShiJian3Max.setEnabled(false);
                    etBaoYaShiJian3Time.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.LUOGANZHUANSU)) {
                    etLuoGanZhuanSuSD.setText(param.getDefaultValue());
                    etLuoGanZhuanSuSJ.setText(param.getActualValue());
                    etLuoGanZhuanSuMin.setText(param.getLowerLimit());
                    etLuoGanZhuanSuMax.setText(param.getUpperLimit());
                    etLuoGanZhuanSuTime.setText(param.getDescription());
                    etLuoGanZhuanSuSD.setEnabled(false);
                    etLuoGanZhuanSuSJ.setEnabled(false);
                    etLuoGanZhuanSuMin.setEnabled(false);
                    etLuoGanZhuanSuMax.setEnabled(false);
                    etLuoGanZhuanSuTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BEIYA)) {
                    etBeiYaSD.setText(param.getDefaultValue());
                    etBeiYaSJ.setText(param.getActualValue());
                    etBeiYaMin.setText(param.getLowerLimit());
                    etBeiYaMax.setText(param.getUpperLimit());
                    etBeiYaTime.setText(param.getDescription());
                    etBeiYaSD.setEnabled(false);
                    etBeiYaSJ.setEnabled(false);
                    etBeiYaMin.setEnabled(false);
                    etBeiYaMax.setEnabled(false);
                    etBeiYaTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SONGTUIWEIZHI)) {
                    etSongTuiWeiZhiSD.setText(param.getDefaultValue());
                    etSongTuiWeiZhiSJ.setText(param.getActualValue());
                    etSongTuiWeiZhiMin.setText(param.getLowerLimit());
                    etSongTuiWeiZhiMax.setText(param.getUpperLimit());
                    etSongTuiWeiZhiTime.setText(param.getDescription());
                    etSongTuiWeiZhiSD.setEnabled(false);
                    etSongTuiWeiZhiSJ.setEnabled(false);
                    etSongTuiWeiZhiMin.setEnabled(false);
                    etSongTuiWeiZhiMax.setEnabled(false);
                    etSongTuiWeiZhiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.YONGLIANGLIANG)) {
                    etYongLiaoLiangSD.setText(param.getDefaultValue());
                    etYongLiaoLiangSJ.setText(param.getActualValue());
                    etYongLiaoLiangMin.setText(param.getLowerLimit());
                    etYongLiaoLiangMax.setText(param.getUpperLimit());
                    etYongLiaoLiangTime.setText(param.getDescription());
                    etYongLiaoLiangSD.setEnabled(false);
                    etYongLiaoLiangSJ.setEnabled(false);
                    etYongLiaoLiangMin.setEnabled(false);
                    etYongLiaoLiangMax.setEnabled(false);
                    etYongLiaoLiangTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.HUANCHONGLIANG)) {
                    etHuanChongLiangSD.setText(param.getDefaultValue());
                    etHuanChongLiangSJ.setText(param.getActualValue());
                    etHuanChongLiangMin.setText(param.getLowerLimit());
                    etHuanChongLiangMax.setText(param.getUpperLimit());
                    etHuanChongLiangTime.setText(param.getDescription());
                    etHuanChongLiangSD.setEnabled(false);
                    etHuanChongLiangSJ.setEnabled(false);
                    etHuanChongLiangMin.setEnabled(false);
                    etHuanChongLiangMax.setEnabled(false);
                    etHuanChongLiangTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.MUJUBAOHUYALI)) {
                    etMuJuBaoHuYaLiSD.setText(param.getDefaultValue());
                    etMuJuBaoHuYaLiSJ.setText(param.getActualValue());
                    etMuJuBaoHuYaLiMin.setText(param.getLowerLimit());
                    etMuJuBaoHuYaLiMax.setText(param.getUpperLimit());
                    etMuJuBaoHuYaLiTime.setText(param.getDescription());
                    etMuJuBaoHuYaLiSD.setEnabled(false);
                    etMuJuBaoHuYaLiSJ.setEnabled(false);
                    etMuJuBaoHuYaLiMin.setEnabled(false);
                    etMuJuBaoHuYaLiMax.setEnabled(false);
                    etMuJuBaoHuYaLiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.MUJUBAOHUWEIZHI)) {
                    etMuJuBaoHuWeiZhiSD.setText(param.getDefaultValue());
                    etMuJuBaoHuWeiZhiSJ.setText(param.getActualValue());
                    etMuJuBaoHuWeiZhiMin.setText(param.getLowerLimit());
                    etMuJuBaoHuWeiZhiMax.setText(param.getUpperLimit());
                    etMuJuBaoHuWeiZhiTime.setText(param.getDescription());
                    etMuJuBaoHuWeiZhiSD.setEnabled(false);
                    etMuJuBaoHuWeiZhiSJ.setEnabled(false);
                    etMuJuBaoHuWeiZhiMin.setEnabled(false);
                    etMuJuBaoHuWeiZhiMax.setEnabled(false);
                    etMuJuBaoHuWeiZhiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SUOMOLI)) {
                    etSuoMoLiSD.setText(param.getDefaultValue());
                    etSuoMoLiSJ.setText(param.getActualValue());
                    etSuoMoLiMin.setText(param.getLowerLimit());
                    etSuoMoLiMax.setText(param.getUpperLimit());
                    etSuoMoLiTime.setText(param.getDescription());
                    etSuoMoLiSD.setEnabled(false);
                    etSuoMoLiSJ.setEnabled(false);
                    etSuoMoLiMin.setEnabled(false);
                    etSuoMoLiMax.setEnabled(false);
                    etSuoMoLiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.DINGCHUWEIZHI)) {
                    etDingChuWeiZhiSD.setText(param.getDefaultValue());
                    etDingChuWeiZhiSJ.setText(param.getActualValue());
                    etDingChuWeiZhiMin.setText(param.getLowerLimit());
                    etDingChuWeiZhiMax.setText(param.getUpperLimit());
                    etDingChuWeiZhiTime.setText(param.getDescription());
                    etDingChuWeiZhiSD.setEnabled(false);
                    etDingChuWeiZhiSJ.setEnabled(false);
                    etDingChuWeiZhiMin.setEnabled(false);
                    etDingChuWeiZhiMax.setEnabled(false);
                    etDingChuWeiZhiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.DINGMOWENDU)) {
                    etDingMoWenDuSD.setText(param.getDefaultValue());
                    etDingMoWenDuSJ.setText(param.getActualValue());
                    etDingMoWenDuMin.setText(param.getLowerLimit());
                    etDingMoWenDuMax.setText(param.getUpperLimit());
                    etDingMoWenDuTime.setText(param.getDescription());
                    etDingMoWenDuSD.setEnabled(false);
                    etDingMoWenDuSJ.setEnabled(false);
                    etDingMoWenDuMin.setEnabled(false);
                    etDingMoWenDuMax.setEnabled(false);
                    etDingMoWenDuTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.DONGMOWENDU)) {
                    etDongMoWenDuSD.setText(param.getDefaultValue());
                    etDongMoWenDuSJ.setText(param.getActualValue());
                    etDongMoWenDuMin.setText(param.getLowerLimit());
                    etDongMoWenDuMax.setText(param.getUpperLimit());
                    etDongMoWenDuTime.setText(param.getDescription());
                    etDongMoWenDuSD.setEnabled(false);
                    etDongMoWenDuSJ.setEnabled(false);
                    etDongMoWenDuMin.setEnabled(false);
                    etDongMoWenDuMax.setEnabled(false);
                    etDongMoWenDuTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.HONGLIAOWENDU)) {
                    etHongLiaoWenDuSD.setText(param.getDefaultValue());
                    etHongLiaoWenDuSJ.setText(param.getActualValue());
                    etHongLiaoWenDuMin.setText(param.getLowerLimit());
                    etHongLiaoWenDuMax.setText(param.getUpperLimit());
                    etHongLiaoWenDuTime.setText(param.getDescription());
                    etHongLiaoWenDuSD.setEnabled(false);
                    etHongLiaoWenDuSJ.setEnabled(false);
                    etHongLiaoWenDuMin.setEnabled(false);
                    etHongLiaoWenDuMax.setEnabled(false);
                    etHongLiaoWenDuTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.HUILIAOBILI)) {
                    etHuiLiaoBiLiSD.setText(param.getDefaultValue());
                    etHuiLiaoBiLiSJ.setText(param.getActualValue());
                    etHuiLiaoBiLiMin.setText(param.getLowerLimit());
                    etHuiLiaoBiLiMax.setText(param.getUpperLimit());
                    etHuiLiaoBiLiTime.setText(param.getDescription());
                    etHuiLiaoBiLiSD.setEnabled(false);
                    etHuiLiaoBiLiSJ.setEnabled(false);
                    etHuiLiaoBiLiMin.setEnabled(false);
                    etHuiLiaoBiLiMax.setEnabled(false);
                    etHuiLiaoBiLiTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.QIANJIANSHULIANG)) {
                    etQianJianShuLiangSD.setText(param.getDefaultValue());
                    etQianJianShuLiangSJ.setText(param.getActualValue());
                    etQianJianShuLiangMin.setText(param.getLowerLimit());
                    etQianJianShuLiangMax.setText(param.getUpperLimit());
                    etQianJianShuLiangTime.setText(param.getDescription());
                    etQianJianShuLiangSD.setEnabled(false);
                    etQianJianShuLiangSJ.setEnabled(false);
                    etQianJianShuLiangMin.setEnabled(false);
                    etQianJianShuLiangMax.setEnabled(false);
                    etQianJianShuLiangTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.QIANJIANBIANHAO)) {
                    etQianJianBianHaoSD.setText(param.getDefaultValue());
                    etQianJianBianHaoSJ.setText(param.getActualValue());
                    etQianJianBianHaoMin.setText(param.getLowerLimit());
                    etQianJianBianHaoMax.setText(param.getUpperLimit());
                    etQianJianBianHaoTime.setText(param.getDescription());
                    etQianJianBianHaoSD.setEnabled(false);
                    etQianJianBianHaoSJ.setEnabled(false);
                    etQianJianBianHaoMin.setEnabled(false);
                    etQianJianBianHaoMax.setEnabled(false);
                    etQianJianBianHaoTime.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.GANZAOWENDU)) {
                    etGanZaoWenDu.setText(param.getDefaultValue());
                    etGanZaoWenDu.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.GANZAOSHIJIAN)) {
                    etGanZaoShiJian.setText(param.getDefaultValue());
                    etGanZaoShiJian.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.YUANLIAOHANSHUIFEN)) {
                    etYuanLiaoHanShuiFen.setText(param.getDefaultValue());
                    etYuanLiaoHanShuiFen.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.CHENGXINGWENDU)) {
                    etChengXingWenDu.setText(param.getDefaultValue());
                    etChengXingWenDu.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.MUJUWENDU)) {
                    etMuJuWenDu.setText(param.getDefaultValue());
                    etMuJuWenDu.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHUYALI)) {
                    etSheChuYaLi.setText(param.getDefaultValue());
                    etSheChuYaLi.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHUSHIJIAN2)) {
                    etSheChuShiJian.setText(param.getDefaultValue());
                    etSheChuShiJian.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SHECHUSUDU)) {
                    etSheChuSuDu.setText(param.getDefaultValue());
                    etSheChuSuDu.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BAOYAYALI)) {
                    etBaoYaYali.setText(param.getDefaultValue());
                    etBaoYaYali.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BAOYASHIJIAN)) {
                    etBaoYaShiJian.setText(param.getDefaultValue());
                    etBaoYaShiJian.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.SUOMOLI2)) {
                    etSuoMoLi.setText(param.getDefaultValue());
                    etSuoMoLi.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.KAIHEMOSHIJIAN)) {
                    etKaiHeMoShiJian.setText(param.getDefaultValue());
                    etKaiHeMoShiJian.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.LENGQUESHIJIAN2)) {
                    etLengQueShijian.setText(param.getDefaultValue());
                    etLengQueShijian.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.LUOGANZHUANSU2)) {
                    etLuoGanSuDu.setText(param.getDefaultValue());
                    etLuoGanSuDu.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.DINGTUICHUSHIJIAN)) {
                    etDingTuiChuShijian.setText(param.getDefaultValue());
                    etDingTuiChuShijian.setEnabled(false);
                } else if (parameterName.equals(CraftParamsName.BEIYA2)) {
                    etBeiYa.setText(param.getDefaultValue());
                    etBeiYa.setEnabled(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoadCraftParamsSuccess(List<CraftConfirmResbean> list) {
        showHistoryShiMuCraft(list);
    }

    @Override
    public void onSaveCraftParamsSuccess() {
        toastShort("保存成功");
        //清楚原来sp里面保存的用户填写的头部信息
        SPUtils.getInstance().clear();
        Intent intent = new Intent(CraftConfirmActivity.this, ProblemRecordActivity.class);
        startActivity(intent);
    }

    @Override
    public void getModelInfoSuccess(ModelInfoResbean modelInfoResbean) {
        etDate.setText(modelInfoResbean.getActualStartTime());
        etMuRen.setText(modelInfoResbean.getModelKernelCode());
        etMuJia.setText(modelInfoResbean.getModelFrameCode());
        etXiangMuBianHao.setText(modelInfoResbean.getProjectId());

        etJiqiBianhao.setText(modelInfoResbean.getEquipmentCode());
        etJiqiDunWei.setText(modelInfoResbean.getSpecs());
        etWaiSheZuiZhiJing.setText(modelInfoResbean.getNozzleOuterDiameter());
        etNeiSheZuiZhiJing.setText(modelInfoResbean.getNozzleAperture());

        etChanPinMingCheng.setText(modelInfoResbean.getProductName());
        etChanPinBianHao.setText(modelInfoResbean.getProductCode());
        etKeHuLiaoHao.setText(modelInfoResbean.getProductMaterialNo());
        etChanPinBanBen.setText(modelInfoResbean.getChangeNo());

        etYuanLiaoMingCheng.setText(modelInfoResbean.getMaterialName());
        etYuanLiaoLiaoHao.setText(modelInfoResbean.getMaterialNo());
        etYuanLiaoGongYingShang.setText(modelInfoResbean.getSupplierName());
        etRLDJRDS.setText(modelInfoResbean.getHotRunnerNo());

        etLiudaoLeiXing.setText(modelInfoResbean.getRunnerType());
        etLuoGanZhiJing.setText(modelInfoResbean.getScrewDiameter());
    }
}
