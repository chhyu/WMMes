package com.weimi.wmmess.business.shimu.fragment;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.adapter.step9.BaoYaTimeAdapter;
import com.weimi.wmmess.business.shimu.adapter.step9.BaoYaYaLiAdapter;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaTimeResbean;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaTimeResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaYaLiResbean;
import com.weimi.wmmess.business.shimu.bean.step9.BaoYaYaLiResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step9.Step9Resbean;
import com.weimi.wmmess.business.shimu.bean.step9.Step9ResbeanDao;
import com.weimi.wmmess.business.shimu.filter.DecimalDigitsInputFilter;
import com.weimi.wmmess.business.shimu.presenter.Step9Presenter;
import com.weimi.wmmess.utils.NumFormatUtil;
import com.weimi.wmmess.utils.TestUtil;
import com.weimi.wmmess.widget.MyMarkerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 保压压力&时间分析
 */
public class Step9Fragment extends BaseFragment<Step9Presenter> implements View.OnClickListener {
    private static final int stepPosition = 8;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnNextStep, btnAddRecord, btnAddBaoYaShiJian;
    private RecyclerView rcvBaoYaYaLi;
    private RecyclerView rcvBaoYaTime;
    private Step9Presenter presenter;
    private BaoYaYaLiAdapter baoYaYaLiAdapter;

    private BaoYaTimeAdapter baoYaTimeAdapter;

    private LineChart lineChartYaLi, lineChartTime;

    private Step9Resbean step9Resbean;
    private EditText etYaLiMin, etYaLiMax, etYaLiConfirm, etTimeMin, etTimeMax, etTimeConfirm;

    public static Step9Fragment newInstance(long historyId, boolean isHistory) {
        Step9Fragment fragment = new Step9Fragment();
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
        return R.layout.fragment_step14;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        btnNextStep = rootView.findViewById(R.id.btnNextStep);
        btnAddRecord = rootView.findViewById(R.id.btnAddRecord);
        btnAddBaoYaShiJian = rootView.findViewById(R.id.btnAddBaoYaShiJian);

        rcvBaoYaYaLi = rootView.findViewById(R.id.rcvBaoYaYaLi);

        rcvBaoYaTime = rootView.findViewById(R.id.rcvBaoYaTime);

        lineChartYaLi = rootView.findViewById(R.id.lineChartYaLi);
        lineChartTime = rootView.findViewById(R.id.lineChartTime);

        etYaLiMin = rootView.findViewById(R.id.etYaLiMin);
        etYaLiMax = rootView.findViewById(R.id.etYaLiMax);
        etYaLiConfirm = rootView.findViewById(R.id.etYaLiConfirm);
        etTimeMin = rootView.findViewById(R.id.etTimeMin);
        etTimeMax = rootView.findViewById(R.id.etTimeMax);
        etTimeConfirm = rootView.findViewById(R.id.etTimeConfirm);

        etYaLiMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etYaLiMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etYaLiConfirm.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etTimeMin.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etTimeMax.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});
        etTimeConfirm.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(Constants.MAX_DECIMAL_LENGTH)});

        btnNextStep.setOnClickListener(this);
        btnAddRecord.setOnClickListener(this);
        btnAddBaoYaShiJian.setOnClickListener(this);
    }

    @Override
    public void initData() {
        presenter = new Step9Presenter(this);
        step9Resbean = new Step9Resbean();
        initBaoYaYaLiRcv();
        initBaoYaTimeRcv();
        initLineChartYaLi();
        initLineChartTime();
        addYaLiEditListener();
        addTimeEditListener();

        if (historyId != 0 && isHistory == true) {
            showHistoryData();
        }
    }

    private void showHistoryData() {
        try {
            Step9Resbean step9Resbean = MainApplication.daoSession.getStep9ResbeanDao().load(historyId);
            etYaLiMin.setText(step9Resbean.getYaLiMin());
            etYaLiMax.setText(step9Resbean.getYaLiMax());
            etYaLiConfirm.setText(step9Resbean.getYaLiConfirm());

            etYaLiMin.setEnabled(false);
            etYaLiMax.setEnabled(false);
            etYaLiConfirm.setEnabled(false);

            etTimeMin.setText(step9Resbean.getTimeMin());
            etTimeMax.setText(step9Resbean.getTimeMax());
            etTimeConfirm.setText(step9Resbean.getTimeConfirm());

            etTimeMin.setEnabled(false);
            etTimeMax.setEnabled(false);
            etTimeConfirm.setEnabled(false);

            List<BaoYaYaLiResbean> baoYaYaLiResbeans = MainApplication.daoSession.getBaoYaYaLiResbeanDao().loadAll();
            if (baoYaYaLiResbeanList != null && baoYaYaLiResbeanList.size() > 0) {
                baoYaYaLiResbeanList.clear();
            }
            for (BaoYaYaLiResbean baoYaYaLiResbean : baoYaYaLiResbeans) {
                if (baoYaYaLiResbean.getBaoYaYaLiResbeanId().equals(historyId)) {
                    baoYaYaLiResbeanList.add(baoYaYaLiResbean);
                }
            }

            List<BaoYaTimeResbean> baoYaTimeResbeans = MainApplication.daoSession.getBaoYaTimeResbeanDao().loadAll();
            if (baoYaTimeResbeanList != null && baoYaTimeResbeanList.size() > 0) {
                baoYaTimeResbeanList.clear();
            }
            for (BaoYaTimeResbean baoYaTimeResbean : baoYaTimeResbeans) {
                if (baoYaTimeResbean.getBaoYaTimeResbeanId().equals(historyId)) {
                    baoYaTimeResbeanList.add(baoYaTimeResbean);
                }
            }
            rcvBaoYaTime.setEnabled(false);
            rcvBaoYaYaLi.setEnabled(false);

            baoYaYaLiAdapter.notifyDataSetChanged();
            baoYaTimeAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindYaLiData() {
        try {    /**
         * Entry 坐标点对象  构造函数 第一个参数为x点坐标 第二个为y点
         */
            ArrayList<Entry> yaLiValues = new ArrayList<>();
            for (int i = 0; i < baoYaYaLiResbeanList.size(); i++) {
                yaLiValues.add(new Entry((float) Double.parseDouble(baoYaYaLiResbeanList.get(i).getYali()), (float) Double.parseDouble(baoYaYaLiResbeanList.get(i).getYaLiAverageValue())));
            }

            //LineDataSet每一个对象就是一条连接线
            LineDataSet set1;
            //判断图表中原来是否有数据
            if (lineChartYaLi.getData() != null && lineChartYaLi.getData().getDataSetCount() > 0) {
                //获取数据1
                set1 = (LineDataSet) lineChartYaLi.getData().getDataSetByIndex(0);
                set1.setValues(yaLiValues);
                //刷新数据
                lineChartYaLi.getData().notifyDataChanged();
                lineChartYaLi.notifyDataSetChanged();
            } else {
                //设置数据1  参数1：数据源 参数2：图例名称
                set1 = new LineDataSet(yaLiValues, "重量平均值");
                set1.setColor(Color.BLACK);
                set1.setCircleColor(Color.BLACK);
                set1.setLineWidth(1f);//设置线宽
                set1.setCircleRadius(3f);//设置焦点圆心的大小
                set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
                set1.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
                set1.setHighlightEnabled(true);//是否禁用点击高亮线
                set1.setHighLightColor(Color.RED);//设置点击交点后显示交高亮线的颜色
                set1.setValueTextSize(9f);//设置显示值的文字大小
                set1.setDrawFilled(false);//设置禁用范围背景填充

                //格式化显示数据
                final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
                set1.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                        return mFormat.format(value);
                    }
                });
                if (Utils.getSDKInt() >= 18) {
                    // fill drawable only supported on api level 18 and above
                    Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.fade_red);
                    set1.setFillDrawable(drawable);//设置范围背景填充
                } else {
                    set1.setFillColor(Color.BLACK);
                }
                //保存LineDataSet集合
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1); // add the datasets
                //创建LineData对象 属于LineChart折线图的数据集合
                LineData data = new LineData(dataSets);
                // 添加到图表中
                lineChartYaLi.setData(data);
                //绘制图表
                lineChartYaLi.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindTimeData() {

        try {
            ArrayList<Entry> yaLiValues = new ArrayList<>();

            for (int i = 0; i < baoYaTimeResbeanList.size(); i++) {
                yaLiValues.add(new Entry((float) Double.parseDouble(baoYaTimeResbeanList.get(i).getTime()), (float) Double.parseDouble(baoYaTimeResbeanList.get(i).getTimeAverageValue())));
            }
            //LineDataSet每一个对象就是一条连接线
            LineDataSet set1;
            //判断图表中原来是否有数据
            if (lineChartTime.getData() != null && lineChartTime.getData().getDataSetCount() > 0) {
                //获取数据1
                set1 = (LineDataSet) lineChartTime.getData().getDataSetByIndex(0);
                set1.setValues(yaLiValues);
                //刷新数据
                lineChartTime.getData().notifyDataChanged();
                lineChartTime.notifyDataSetChanged();
            } else {
                //设置数据1  参数1：数据源 参数2：图例名称
                set1 = new LineDataSet(yaLiValues, "重量平均值");
                set1.setColor(Color.BLACK);
                set1.setCircleColor(Color.BLACK);
                set1.setLineWidth(1f);//设置线宽
                set1.setCircleRadius(3f);//设置焦点圆心的大小
                set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
                set1.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
                set1.setHighlightEnabled(true);//是否禁用点击高亮线
                set1.setHighLightColor(Color.RED);//设置点击交点后显示交高亮线的颜色
                set1.setValueTextSize(9f);//设置显示值的文字大小
                set1.setDrawFilled(false);//设置禁用范围背景填充

                //格式化显示数据
                final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
                set1.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                        return mFormat.format(value);
                    }
                });
                if (Utils.getSDKInt() >= 18) {
                    // fill drawable only supported on api level 18 and above
                    Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.fade_red);
                    set1.setFillDrawable(drawable);//设置范围背景填充
                } else {
                    set1.setFillColor(Color.BLACK);
                }
                //保存LineDataSet集合
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1); // add the datasets
                //创建LineData对象 属于LineChart折线图的数据集合
                LineData data = new LineData(dataSets);
                // 添加到图表中
                lineChartTime.setData(data);
                //绘制图表
                lineChartTime.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加压力编辑监听
     */
    private void addYaLiEditListener() {
        baoYaYaLiAdapter.setOnYaLiEditCompleteListener(new BaoYaYaLiAdapter.OnYaLiEditCompleteListener() {
            @Override
            public void onComplete(String text, int position, int number) {
                BaoYaYaLiResbean baoYaYaLiResbean = setEtDate2Bean(text, position, number);
                if (StringUtils.isEmpty(baoYaYaLiResbean.getYali()) || StringUtils.isEmpty(baoYaYaLiResbean.getYaLiOne()) || StringUtils.isEmpty(baoYaYaLiResbean.getYaLiTwo()) || StringUtils.isEmpty(baoYaYaLiResbean.getYaLiThree()) ||
                        baoYaYaLiResbean.getYali().equals("0") || baoYaYaLiResbean.getYaLiOne().equals("0") || baoYaYaLiResbean.getYaLiTwo().equals("0") || baoYaYaLiResbean.getYaLiThree().equals("0")) {
                    return;
                }
                if (StringUtils.isEmpty(baoYaYaLiResbean.getYali()) || StringUtils.isEmpty(baoYaYaLiResbean.getYaLiOne()) || StringUtils.isEmpty(baoYaYaLiResbean.getYaLiTwo()) || StringUtils.isEmpty(baoYaYaLiResbean.getYaLiThree()) ||
                        baoYaYaLiResbean.getYali().equals("0") || baoYaYaLiResbean.getYaLiOne().equals("0") || baoYaYaLiResbean.getYaLiTwo().equals("0") || baoYaYaLiResbean.getYaLiThree().equals("0")) {
                    return;
                }
                calculateYaLiAverageVulue(baoYaYaLiResbean);
                bindYaLiData();
            }
        });
    }

    private void calculateYaLiAverageVulue(BaoYaYaLiResbean bean) {
        double one = Double.parseDouble(bean.getYaLiOne());
        double two = Double.parseDouble(bean.getYaLiTwo());
        double three = Double.parseDouble(bean.getYaLiThree());
        double averageVulue = (one + two + three) / 3;
        bean.setYaLiAverageValue(String.valueOf(NumFormatUtil.getDoubleNumberString(averageVulue)));
    }

    private BaoYaYaLiResbean setEtDate2Bean(String text, int position, int number) {
        BaoYaYaLiResbean baoYaYaLiResbean = baoYaYaLiResbeanList.get(position);
        if (number == BaoYaYaLiAdapter.ET_BAOYA_YALI) {
            baoYaYaLiResbean.setYali(text);
        } else if (number == BaoYaYaLiAdapter.ET_YALI_ONE) {
            baoYaYaLiResbean.setYaLiOne(text);
        } else if (number == BaoYaYaLiAdapter.ET_YALI_TWO) {
            baoYaYaLiResbean.setYaLiTwo(text);
        } else if (number == BaoYaYaLiAdapter.ET_YALI_THREE) {
            baoYaYaLiResbean.setYaLiThree(text);
        } else if (number == BaoYaYaLiAdapter.ET_YALI_BeiZhu) {
            baoYaYaLiResbean.setYaLiBeizhu(text);
        }
        return baoYaYaLiResbean;
    }

    /**
     * 添加保压时间输入监听
     */
    private void addTimeEditListener() {
        baoYaTimeAdapter.setOnTimeEditCompleteListener(new BaoYaTimeAdapter.OnTimeEditCompleteListener() {
            @Override
            public void onComplete(String text, int position, int number) {
                BaoYaTimeResbean baoYaTimeResbean = setEtTimeDate2Bean(text, position, number);
                if (StringUtils.isEmpty(baoYaTimeResbean.getTime()) || StringUtils.isEmpty(baoYaTimeResbean.getTimeOne()) || StringUtils.isEmpty(baoYaTimeResbean.getTimeTwo()) || StringUtils.isEmpty(baoYaTimeResbean.getTimeThree()) ||
                        baoYaTimeResbean.getTime().equals("0") || baoYaTimeResbean.getTimeOne().equals("0") || baoYaTimeResbean.getTimeTwo().equals("0") || baoYaTimeResbean.getTimeThree().equals("0")) {
                    return;
                }
                if (StringUtils.isEmpty(baoYaTimeResbean.getTime()) || StringUtils.isEmpty(baoYaTimeResbean.getTimeOne()) || StringUtils.isEmpty(baoYaTimeResbean.getTimeTwo()) || StringUtils.isEmpty(baoYaTimeResbean.getTimeThree()) ||
                        baoYaTimeResbean.getTime().equals("0") || baoYaTimeResbean.getTimeOne().equals("0") || baoYaTimeResbean.getTimeTwo().equals("0") || baoYaTimeResbean.getTimeThree().equals("0")) {
                    return;
                }
                calculateTimeAverageVulue(baoYaTimeResbean);
                bindTimeData();
            }
        });
    }

    private void calculateTimeAverageVulue(BaoYaTimeResbean bean) {
        double one = Double.parseDouble(bean.getTimeOne());
        double two = Double.parseDouble(bean.getTimeTwo());
        double three = Double.parseDouble(bean.getTimeThree());
        double averageVulue = (one + two + three) / 3;
        bean.setTimeAverageValue(String.valueOf(NumFormatUtil.getDoubleNumberString(averageVulue)));
    }

    private BaoYaTimeResbean setEtTimeDate2Bean(String text, int position, int number) {
        BaoYaTimeResbean baoYaTimeResbean = baoYaTimeResbeanList.get(position);
        if (number == BaoYaTimeAdapter.ET_BAOYA_TIME) {
            baoYaTimeResbean.setTime(text);
        } else if (number == BaoYaTimeAdapter.ET_TIME_ONE) {
            baoYaTimeResbean.setTimeOne(text);
        } else if (number == BaoYaTimeAdapter.ET_TIME_TWO) {
            baoYaTimeResbean.setTimeTwo(text);
        } else if (number == BaoYaTimeAdapter.ET_TIME_THREE) {
            baoYaTimeResbean.setTimeThree(text);
        } else if (number == BaoYaTimeAdapter.ET_TIME_BEIZHU) {
            baoYaTimeResbean.setTimeBeizhu(text);
        }
        return baoYaTimeResbean;
    }

    /**
     * 初始化保压时间recyclerView
     */
    private void initBaoYaTimeRcv() {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rcvBaoYaTime.setFocusable(false);
        rcvBaoYaTime.setHasFixedSize(true);
        rcvBaoYaTime.setNestedScrollingEnabled(false);

        baoYaTimeAdapter = new BaoYaTimeAdapter(getActivity(), baoYaTimeResbeanList);

        rcvBaoYaTime.setLayoutManager(layoutManager1);

        rcvBaoYaTime.setAdapter(baoYaTimeAdapter);

    }

    /**
     * 初始化保压压力recyclerView
     */
    private void initBaoYaYaLiRcv() {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rcvBaoYaYaLi.setFocusable(false);
        rcvBaoYaYaLi.setHasFixedSize(true);
        rcvBaoYaYaLi.setNestedScrollingEnabled(false);
        baoYaYaLiAdapter = new BaoYaYaLiAdapter(getActivity(), baoYaYaLiResbeanList);

        rcvBaoYaYaLi.setLayoutManager(layoutManager1);

        rcvBaoYaYaLi.setAdapter(baoYaYaLiAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                saveDataAndJump2Next();
                break;
            case R.id.btnAddRecord:
                addBaoYaYaLiRecord();
                break;
            case R.id.btnAddBaoYaShiJian:
                addBaoYaTimeRecord();
                break;
            default:
                break;
        }
    }

    private void saveDataAndJump2Next() {
        step9Resbean.setYaLiMin(etYaLiMin.getText().toString());
        step9Resbean.setYaLiMax(etYaLiMax.getText().toString());
        step9Resbean.setYaLiConfirm(etYaLiConfirm.getText().toString());
        step9Resbean.setTimeMin(etTimeMin.getText().toString());
        step9Resbean.setTimeMax(etTimeMax.getText().toString());
        step9Resbean.setTimeConfirm(etTimeConfirm.getText().toString());
        if (Constants.isNeedTest && !isHistory) {
            boolean isHave = presenter.checkDataIsHaveNull(step9Resbean);
            if (isHave) {
                return;
            }
            try {
                step9Resbean.setStep9Id(MainApplication.thisTimeId);
                step9Resbean.setCurrentStepIsChecked(true);
                Step9ResbeanDao step9ResbeanDao = MainApplication.daoSession.getStep9ResbeanDao();

                BaoYaYaLiResbeanDao baoYaYaLiResbeanDao = MainApplication.daoSession.getBaoYaYaLiResbeanDao();
                for (int i = 0; i < baoYaYaLiResbeanList.size(); i++) {
                    baoYaYaLiResbeanDao.insert(baoYaYaLiResbeanList.get(i));
                }

                BaoYaTimeResbeanDao baoYaTimeResbeanDao = MainApplication.daoSession.getBaoYaTimeResbeanDao();
                for (int i = 0; i < baoYaTimeResbeanList.size(); i++) {
                    baoYaTimeResbeanDao.insert(baoYaTimeResbeanList.get(i));
                }
                step9ResbeanDao.insert(step9Resbean);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                TestUtil.getAllDbDatas();
            }
        }
        ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
    }

    private List<BaoYaTimeResbean> baoYaTimeResbeanList = new ArrayList<>();
    private List<BaoYaYaLiResbean> baoYaYaLiResbeanList = new ArrayList<>();

    /**
     * 添加保压压力记录
     */
    private void addBaoYaYaLiRecord() {
        if (baoYaYaLiResbeanList.size() > 0) {
            if (presenter.checkYaLiDataIsLegal(baoYaYaLiResbeanList)) {
                baoYaYaLiResbeanList.add(new BaoYaYaLiResbean(null, MainApplication.thisTimeId, "", "", "", "", "", ""));
                baoYaYaLiAdapter.notifyDataSetChanged();
            }
        } else {
            baoYaYaLiResbeanList.add(new BaoYaYaLiResbean(null, MainApplication.thisTimeId, "", "", "", "", "", ""));
            baoYaYaLiAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 添加保压时间记录
     */
    private void addBaoYaTimeRecord() {
        //增加时都是同步增加的，所以集合长度都一样，判断一个就行了
        if (baoYaTimeResbeanList.size() != 0) {
            if (presenter.checkTimeDataIsLegal(baoYaTimeResbeanList)) {
                baoYaTimeResbeanList.add(new BaoYaTimeResbean(null, MainApplication.thisTimeId, "", "", "", "", "", ""));
                baoYaTimeAdapter.notifyDataSetChanged();
            }
        } else {
            baoYaTimeResbeanList.add(new BaoYaTimeResbean(null, MainApplication.thisTimeId, "", "", "", "", "", ""));
            baoYaTimeAdapter.notifyDataSetChanged();
        }
    }

    private void initLineChartTime() {
        //创建描述信息
        Description description = new Description();
        description.setText("保压时间");
        description.setTextColor(Color.BLACK);
        description.setTextSize(20);
        lineChartTime.setDescription(description);//设置图表描述信息
        lineChartTime.setNoDataText("没有数据熬");//没有数据时显示的文字
        lineChartTime.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        lineChartTime.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChartTime.setDrawBorders(false);//禁止绘制图表边框的线

        //setX style
        XAxis xAxis = lineChartTime.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度

        //setYStyle
        //获取右边的轴线
        YAxis rightAxis = lineChartTime.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        //获取左边的轴线
        YAxis leftAxis = lineChartTime.getAxisLeft();
        //设置网格线为虚线效果
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //是否绘制0所在的网格线
        leftAxis.setDrawZeroLine(false);

        lineChartTime.setTouchEnabled(true); // 设置是否可以触摸
        lineChartTime.setDragEnabled(true);// 是否可以拖拽
        lineChartTime.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChartTime.setScaleXEnabled(true); //是否可以缩放 仅x轴
        lineChartTime.setScaleYEnabled(true); //是否可以缩放 仅y轴
        lineChartTime.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
        lineChartTime.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
        lineChartTime.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        lineChartTime.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        lineChartTime.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。

        Legend l = lineChartTime.getLegend();//图例
        l.setTextSize(10f);//设置文字大小
        l.setForm(Legend.LegendForm.CIRCLE);//正方形，圆形或线
        l.setFormSize(10f); // 设置Form的大小
        l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
        l.setFormLineWidth(10f);//设置Form的宽度

        //自定义的MarkerView对象
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
        mv.setChartView(lineChartTime);
        lineChartTime.setMarker(mv);
    }

    private void initLineChartYaLi() {
        //创建描述信息
        Description description = new Description();
        description.setText("保压压力");
        description.setTextColor(Color.BLACK);
        description.setTextSize(20);
        lineChartYaLi.setDescription(description);//设置图表描述信息
        lineChartYaLi.setNoDataText("没有数据熬");//没有数据时显示的文字
        lineChartYaLi.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        lineChartYaLi.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChartYaLi.setDrawBorders(false);//禁止绘制图表边框的线
//        lineChartYaLi.setBorderColor(); //设置 chart 边框线的颜色。
        //lineChartYaLi.setBorderWidth(); //设置 chart 边界线的宽度，单位 dp。
        //lineChartYaLi.setLogEnabled(true);//打印日志
        //lineChartYaLi.notifyDataSetChanged();//刷新数据
        //lineChlineChartYaLiart.invalidate();//重绘

        setXStyle();
        setYStyle();
        setGes();
    }

    private void setGes() {
        lineChartYaLi.setTouchEnabled(true); // 设置是否可以触摸
        lineChartYaLi.setDragEnabled(true);// 是否可以拖拽
        lineChartYaLi.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChartYaLi.setScaleXEnabled(true); //是否可以缩放 仅x轴
        lineChartYaLi.setScaleYEnabled(true); //是否可以缩放 仅y轴
        lineChartYaLi.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
        lineChartYaLi.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
        lineChartYaLi.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        lineChartYaLi.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        lineChartYaLi.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。

        Legend l = lineChartYaLi.getLegend();//图例
        l.setTextSize(10f);//设置文字大小
        l.setForm(Legend.LegendForm.CIRCLE);//正方形，圆形或线
        l.setFormSize(10f); // 设置Form的大小
        l.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
        l.setFormLineWidth(10f);//设置Form的宽度

        //自定义的MarkerView对象
        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
        mv.setChartView(lineChartYaLi);
        lineChartYaLi.setMarker(mv);
    }

    private void setYStyle() {
        /**
         * Y轴默认显示左右两个轴线
         */
        //获取右边的轴线
        YAxis rightAxis = lineChartYaLi.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        //获取左边的轴线
        YAxis leftAxis = lineChartYaLi.getAxisLeft();
        //设置网格线为虚线效果
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //是否绘制0所在的网格线
        leftAxis.setDrawZeroLine(false);
    }

    private void setXStyle() {
        //获取此图表的x轴
        XAxis xAxis = lineChartYaLi.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        //xAxis.setTextSize(20f);//设置字体
        //xAxis.setTextColor(Color.BLACK);//设置字体颜色
        //设置竖线的显示样式为虚线
        //lineLength控制虚线段的长度
        //spaceLength控制线之间的空间
        xAxis.enableGridDashedLine(10f, 10f, 0f);
//        xAxis.setAxisMinimum(0f);//设置x轴的最小值
//        xAxis.setAxisMaximum(10f);//设置最大值
        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
//        设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
//        xAxis.setLabelCount(10);
//        xAxis.setTextColor(Color.BLUE);//设置轴标签的颜色
//        xAxis.setTextSize(24f);//设置轴标签的大小
//        xAxis.setGridLineWidth(10f);//设置竖线大小
//        xAxis.setGridColor(Color.RED);//设置竖线颜色
//        xAxis.setAxisLineColor(Color.GREEN);//设置x轴线颜色
//        xAxis.setAxisLineWidth(5f);//设置x轴线宽度
//        xAxis.setValueFormatter();//格式化x轴标签显示字符
    }
}