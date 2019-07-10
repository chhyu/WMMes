package com.weimi.wmmess.business.shimu.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.base.permission.PermissionsManager;
import com.weimi.wmmess.base.permission.PermissionsResultAction;
import com.weimi.wmmess.business.shimu.bean.craftParams.ModelInfoResbean;
import com.weimi.wmmess.business.shimu.entity.SPHeaderInfoConstant;
import com.weimi.wmmess.business.shimu.viewInterface.IModelView;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.presenter.MainPresenter;
import com.weimi.wmmess.presenter.ShiMuPresenter;
import com.weimi.wmmess.business.shimu.adapter.StepListAdapter;
import com.weimi.wmmess.business.shimu.bean.HeadInfoResbean;
import com.weimi.wmmess.business.shimu.bean.HeadInfoResbeanDao;
import com.weimi.wmmess.business.shimu.bean.StepResbean;
import com.weimi.wmmess.business.shimu.fragment.Step10Fragment;
import com.weimi.wmmess.business.shimu.fragment.Step11Fragment;
import com.weimi.wmmess.business.shimu.fragment.Step12Fragment;
import com.weimi.wmmess.business.shimu.fragment.Step1Fragment;
import com.weimi.wmmess.business.shimu.fragment.Step2Fragment;
import com.weimi.wmmess.business.shimu.fragment.Step3Fragment;
import com.weimi.wmmess.business.shimu.fragment.Step4Fragment;
import com.weimi.wmmess.business.shimu.fragment.Step5Fragment;
import com.weimi.wmmess.business.shimu.fragment.Step6Fragment;
import com.weimi.wmmess.business.shimu.fragment.Step7Fragment;
import com.weimi.wmmess.business.shimu.fragment.Step8Fragment;
import com.weimi.wmmess.business.shimu.fragment.Step9Fragment;

import java.util.ArrayList;
import java.util.List;

public class ShiMuActivity extends WMActivity<MainPresenter> implements View.OnClickListener, IModelView {

    public static final String HISTORY_ITEM_ID = "history_item_id";
    protected List<StepResbean> stepList = new ArrayList<>();
    protected StepListAdapter stepListAdapter;
    private FrameLayout flContainer;
    private RecyclerView rcvStepList;
    private EditText etGanZaoWenDu, etGanZaoShiJian, etYuanLiaoHanShuiFen, etChengXingWenDu, etMuJuWenDu, etSheChuYaLi, etSheChuShiJian, etSheChuSuDu,
            etBaoYaYali, etBaoYaShiJian, etSuoMoLi, etKaiHeMoShiJian, etLengQueShijian, etLuoGanSuDu, etDingTuiChuShijian, etBeiYa;
    private EditText etDate, etMuRen, etMuJia, etXiangMuBianHao, etJiqiBianhao, etJiqiDunWei, etLuoGanZhiJing, etWaiSheZuiZhiJing, etNeiSheZuiZhiJing, etChanPinMingCheng, etChanPinBianHao;
    private EditText etKeHuLiaoHao, etChanPinBanBen, etYuanLiaoMingCheng, etYuanLiaoLiaoHao, etYuanLiaoGongYingShang, etRLDJRDS, etLiudaoLeiXing;
    private ImageView ivDisplayOrHide;
    private RelativeLayout rlTopExcelRow1;
    private RelativeLayout rlTopExcelRow2;
    private HeadInfoResbean headInfoResbean;
    private long historyId;
    private ShiMuPresenter presenter;
    private boolean isDisplay = true;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String workOrderId;
    private String procedureId;
    public static final String WORK_ORDER_ID = "work_order_id";
    public static final String PROCEDURE_ID = "procedure_Id";

    {
        stepList.add(new StepResbean(false, "试模前准备", 0));
        stepList.add(new StepResbean(false, "模具检查&上模", 1));
        stepList.add(new StepResbean(false, "模具水流量测试&模具水路连接", 2));
        stepList.add(new StepResbean(false, "温度设定", 3));
        stepList.add(new StepResbean(false, "开合模&顶出设定", 4));
//        stepList.add(new StepResbean(false, "空运行", 5));
        stepList.add(new StepResbean(false, "压力损失测试", 5));
        stepList.add(new StepResbean(false, "决定射出时间&压力", 6));
        stepList.add(new StepResbean(false, "模穴充填平衡检查", 7));
        stepList.add(new StepResbean(false, "决定保压压力&时间", 8));
        stepList.add(new StepResbean(false, "冷却时间分析", 9));
        stepList.add(new StepResbean(false, "测试设备&模具&工艺稳定性", 10));
        stepList.add(new StepResbean(false, "决定螺杆&模具温度公差", 11));
        stepList.add(new StepResbean(false, "工艺确定", 12));
    }

    @Override
    public int initLayout() {
        return R.layout.activity_shi_mo;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {


        rcvStepList = findViewById(R.id.rcvStepList);
        flContainer = findViewById(R.id.flContainer);

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

        ivDisplayOrHide = findViewById(R.id.ivDisplayOrHide);
        rlTopExcelRow1 = findViewById(R.id.rlTopExcelRow1);
        rlTopExcelRow2 = findViewById(R.id.rlTopExcelRow2);
        ivDisplayOrHide.setOnClickListener(this);
    }

    private void checkPermissionIfNecessary() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                Log.e("lmsg", "pass");
            }

            @Override
            public void onDenied(String permission) {
                Log.e("lmsg", "refuse");
            }
        });
    }

    @Override
    public void initData() {
        checkPermissionIfNecessary();
        setTitle(stepList.get(0).getStepName());

        presenter = new ShiMuPresenter(this);
        headInfoResbean = new HeadInfoResbean();

        parseIntent();
        initRcv();
        addFragmentAndSetDefault();
    }

    private void parseIntent() {
//        try {
//            historyId = getIntent().getLongExtra(HISTORY_ITEM_ID, 0);
//            if (historyId != 0) {
//                HeadInfoResbean headInfoResbean = MainApplication.daoSession.getHeadInfoResbeanDao().load(historyId);
//                setHistoryInfo2Widget(headInfoResbean);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        workOrderId = getIntent().getStringExtra(WORK_ORDER_ID);
        procedureId = getIntent().getStringExtra(PROCEDURE_ID);

        if (!StringUtils.isEmpty(workOrderId) && !StringUtils.isEmpty(procedureId)) {
            presenter.getModelInfoById(workOrderId, procedureId);
        }
    }

    private void addFragmentAndSetDefault() {
        fragmentList.add(Step1Fragment.newInstance(historyId, historyId == 0 ? false : true));
        fragmentList.add(Step2Fragment.newInstance(historyId, historyId == 0 ? false : true));
        fragmentList.add(Step3Fragment.newInstance(historyId, historyId == 0 ? false : true));
        fragmentList.add(Step4Fragment.newInstance(historyId, historyId == 0 ? false : true));
        fragmentList.add(Step5Fragment.newInstance(historyId, historyId == 0 ? false : true));
        fragmentList.add(Step6Fragment.newInstance(historyId, historyId == 0 ? false : true));
        fragmentList.add(Step7Fragment.newInstance(historyId, historyId == 0 ? false : true));
        fragmentList.add(Step8Fragment.newInstance(historyId, historyId == 0 ? false : true));
        fragmentList.add(Step9Fragment.newInstance(historyId, historyId == 0 ? false : true));
        fragmentList.add(Step10Fragment.newInstance(historyId, historyId == 0 ? false : true));
        fragmentList.add(Step11Fragment.newInstance(historyId, historyId == 0 ? false : true));
        fragmentList.add(Step12Fragment.newInstance(historyId, historyId == 0 ? false : true));
//        fragmentList.add(Step13Fragment.newInstance(historyId, historyId == 0 ? false : true));

        switchFragment(R.id.flContainer, fragmentList.get(0));
    }

    private void initRcv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvStepList.setLayoutManager(layoutManager);

        stepListAdapter = new StepListAdapter(this, stepList, historyId == 0 ? false : true);
        rcvStepList.setAdapter(stepListAdapter);

        stepListAdapter.setOnRcvItemClickListener(new StepListAdapter.OnRcvItemClickListener() {
            @Override
            public void onRcvItemClick(StepResbean stepResbean) {
                if (stepResbean.isChecked()) {
                    switchFragment(R.id.flContainer, fragmentList.get(stepResbean.getCurrentStepPosition()));
                    setTitle(stepResbean.getStepName());
                } else {
                    ToastUtils.showShort("此步骤未完成，无法跳转");
                }
            }
        });
    }

    /**
     * 选择下一步fragment
     *
     * @param stepPosition
     */
    public void chooseNextFragment(int stepPosition) {
        try {
            if (stepPosition == Step12Fragment.stepPosition) {
                setHeadInfo2Sp();
//                if (Constants.isNeedTest) {
//                    if (historyId == 0) {//历史数据
//                        boolean isHave = presenter.checkHeadInfoIsHaveNull(headInfoResbean);
//                        if (isHave) {
//                            return;
//                        }
//                        HeadInfoResbeanDao headInfoResbeanDao = MainApplication.daoSession.getHeadInfoResbeanDao();
//                        headInfoResbeanDao.insert(headInfoResbean);
//                    }
//                }
                Intent intent = new Intent(this, CraftConfirmActivity.class);
                intent.putExtra(CraftConfirmActivity.WORK_ORDER_ID, workOrderId);
                intent.putExtra(CraftConfirmActivity.PROCEDURE_ID, procedureId);
                intent.putExtra(CraftConfirmActivity.IS_NEW_CREATE, true);
                startActivity(intent);
                finish();
            } else /*if (stepPosition )*/ {
                stepList.get(stepPosition).setChecked(true);
                stepListAdapter.notifyDataSetChanged();
                switchFragment(R.id.flContainer, fragmentList.get(stepPosition + 1));
                setTitle(stepList.get(stepPosition + 1).getStepName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setHeadInfo2Sp() {
        SPUtils spUtils = SPUtils.getInstance();
        spUtils.put(SPHeaderInfoConstant.SP_GANZAOWENDU, etGanZaoWenDu.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_GANZAOSHIJIAN, etGanZaoShiJian.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_YUANLIAOHANSHUIFEN, etYuanLiaoHanShuiFen.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_CHENGXINGWENDU, etChengXingWenDu.getText().toString());

        spUtils.put(SPHeaderInfoConstant.SP_MUJUWENDU, etMuJuWenDu.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_SHECHUYALI, etSheChuYaLi.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_SHECHUSHIJIAN, etSheChuShiJian.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_SHECHUSUDU, etSheChuSuDu.getText().toString());

        spUtils.put(SPHeaderInfoConstant.SP_BAOYAYALI, etBaoYaYali.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_BAOYASHIJIAN, etBaoYaShiJian.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_SUOMOLI, etSuoMoLi.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_KAIHEMOSHIJIAN, etKaiHeMoShiJian.getText().toString());

        spUtils.put(SPHeaderInfoConstant.SP_LENGQUESHIJIAN, etLengQueShijian.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_LUOGANSUDU, etLuoGanSuDu.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_DINGTUICHUSHIJIAN, etDingTuiChuShijian.getText().toString());
        spUtils.put(SPHeaderInfoConstant.SP_BEIYA, etBeiYa.getText().toString());
    }

//    private void setHistoryInfo2Widget(HeadInfoResbean headInfoResbean) {
//        etGanZaoWenDu.setText(headInfoResbean.getGanZaoWenDu());
//        etGanZaoShiJian.setText(headInfoResbean.getGanZaoShiJian());
//        etYuanLiaoHanShuiFen.setText(headInfoResbean.getYuanLiaoHanShuiFen());
//        etChengXingWenDu.setText(headInfoResbean.getChengXingWenDu());
//
//        etMuJuWenDu.setText(headInfoResbean.getMuJuWenDu());
//        etSheChuYaLi.setText(headInfoResbean.getSheChuYaLi());
//        etSheChuShiJian.setText(headInfoResbean.getSheChuShiJian());
//        etSheChuSuDu.setText(headInfoResbean.getSheChuSuDu());
//
//        etBaoYaYali.setText(headInfoResbean.getBaoYaYaLi());
//        etBaoYaShiJian.setText(headInfoResbean.getBaoYaShiJian());
//        etSuoMoLi.setText(headInfoResbean.getSuoMuLi());
//        etKaiHeMoShiJian.setText(headInfoResbean.getKaiHeMuShiJian());
//
//        etLengQueShijian.setText(headInfoResbean.getLengQueShiJian());
//        etLuoGanSuDu.setText(headInfoResbean.getLuoGanSuDu());
//        etDingTuiChuShijian.setText(headInfoResbean.getDingTuiChuShiJian());
//        etBeiYa.setText(headInfoResbean.getBeiYa());
//
//        etGanZaoWenDu.setEnabled(false);
//        etGanZaoShiJian.setEnabled(false);
//        etYuanLiaoHanShuiFen.setEnabled(false);
//        etChengXingWenDu.setEnabled(false);
//
//        etMuJuWenDu.setEnabled(false);
//        etSheChuYaLi.setEnabled(false);
//        etSheChuShiJian.setEnabled(false);
//        etSheChuSuDu.setEnabled(false);
//
//        etBaoYaYali.setEnabled(false);
//        etBaoYaShiJian.setEnabled(false);
//        etSuoMoLi.setEnabled(false);
//        etKaiHeMoShiJian.setEnabled(false);
//
//        etLengQueShijian.setEnabled(false);
//        etLuoGanSuDu.setEnabled(false);
//        etDingTuiChuShijian.setEnabled(false);
//        etBeiYa.setEnabled(false);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivDisplayOrHide:
                if (isDisplay) {
                    rlTopExcelRow1.setVisibility(View.GONE);
                    rlTopExcelRow2.setVisibility(View.GONE);
                    ivDisplayOrHide.setImageResource(R.drawable.double_arrow_up);
                } else {
                    rlTopExcelRow1.setVisibility(View.VISIBLE);
                    rlTopExcelRow2.setVisibility(View.VISIBLE);
                    ivDisplayOrHide.setImageResource(R.drawable.double_arrow_down);
                }
                isDisplay = !isDisplay;
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("android:support:fragments", null);
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
