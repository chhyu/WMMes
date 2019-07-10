package com.weimi.wmmess.business.workOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.base.adapter.OnItemClickListener;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.shimu.activity.CraftConfirmActivity;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.activity.ShiMuHistoryActivity;
import com.weimi.wmmess.business.workOrder.adapter.OrderListAdapter;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;
import com.weimi.wmmess.business.workOrder.enums.WorkOrderTypeEnum;
import com.weimi.wmmess.business.workOrder.params.WorkOrderVO;
import com.weimi.wmmess.business.workOrder.presenter.WorkOrderPresenter;
import com.weimi.wmmess.business.workOrder.viewInterface.IWorkOrderListView;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.params.GeneralParam;
import com.weimi.wmmess.business.workOrder.params.MobileWorkOrderParam;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.utils.TimeUtils;
import com.weimi.wmmess.widget.DividerItemDecoration;
import com.weimi.wmmess.widget.LoadMoreFootView;
import com.weimi.wmmess.widget.datepick.DatePickDialog;
import com.weimi.wmmess.widget.datepick.OnSureListener;
import com.weimi.wmmess.widget.datepick.bean.DateType;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.Date;

public class WorkOrderListActivity extends WMActivity<WorkOrderPresenter> implements IWorkOrderListView, View.OnClickListener {

    private WorkOrderPresenter mPresenter;
    private int size = 20;
    private int filterSize = 20;
    private int current = 1;
    private int filterCurrent = 1;
    private OrderListAdapter adapter;
    private SwipeRefreshLayout mRefreshLayout;
    private MaskView maskView;
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private DrawerLayout dlOrderFilter;
    private NavigationView nvOrderFilter;
    private ImageView ivFilter;
    private TextView tvCancelSearch, tvSearch, tvFilterStartTime, tvFilterEndTime;
    //    private FilterWorkOrderParams filterWorkOrderParams;
    private WorkOrderVO filterWorkOrderParams;
    private static final int START_TIME = 0;
    private static final int END_TIME = 1;
    private long startTime;
    private long endTime;
    private Button btnReset, btnConfirm;
    private RelativeLayout rlWorkOrderType, rlWorkOrderState;
    private TextView tvWorkOrderType;
    private TextView tvWorkOrderState;
    private EditText etSearch;
    //    private PageInfoParams pageInfoParams;
    private GeneralParam pageInfoParams;

    @Override
    public int initLayout() {
        return R.layout.activity_work_order_list;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeMenuRecyclerView = findViewById(R.id.swipeMenuRecyclerView);
        maskView = findViewById(R.id.maskview);
        dlOrderFilter = findViewById(R.id.dlOrderFilter);
        nvOrderFilter = findViewById(R.id.nvOrderFilter);
        ivFilter = findViewById(R.id.ivFilter);
        btnReset = findViewById(R.id.btnReset);
        btnConfirm = findViewById(R.id.btnConfirm);
        tvCancelSearch = findViewById(R.id.tvCancelSearch);
        etSearch = findViewById(R.id.etSearch);
        tvSearch = findViewById(R.id.tvSearch);

        View headerView = nvOrderFilter.getHeaderView(0);
        tvFilterStartTime = headerView.findViewById(R.id.tvFilterStartTime);
        tvFilterEndTime = headerView.findViewById(R.id.tvFilterEndTime);
        rlWorkOrderType = headerView.findViewById(R.id.rlWorkOrderType);
        rlWorkOrderState = headerView.findViewById(R.id.rlWorkOrderState);
        tvWorkOrderType = headerView.findViewById(R.id.tvWorkOrderType);
        tvWorkOrderState = headerView.findViewById(R.id.tvWorkOrderState);
        ivFilter.setOnClickListener(this);
        tvFilterStartTime.setOnClickListener(this);
        tvFilterEndTime.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        rlWorkOrderType.setOnClickListener(this);
        rlWorkOrderState.setOnClickListener(this);
        tvCancelSearch.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitle("工单列表");
        mPresenter = new WorkOrderPresenter(this);
        filterWorkOrderParams = new WorkOrderVO();
        pageInfoParams = new GeneralParam();
        initRcv();
        requestWorkOrderList(null, true);
        initDrawer();
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            isFilterRefresh = false;
            String keyword = etSearch.getText().toString();
            requestWorkOrderList(keyword, true);
            etSearch.setText(null);
            return false;
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, ViewHolder holder, int position) {
                WorkOrderListResbean workOrderListResbean = adapter.getDatas().get(position);
                mPresenter.checkWorkOrderHavingParameter(workOrderListResbean.getWorkOrderId(), workOrderListResbean.getProcedureId());
            }

            @Override
            public boolean onItemLongClick(View view, ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void requestWorkOrderList(String keyword, boolean isRefresh) {
        if (isFilterRefresh) {
            pageInfoParams.setCurrent(filterCurrent);
            pageInfoParams.setSize(filterSize);
        } else {
            if (isRefresh) {
                current = 1;
            } else {
                current++;
            }
            pageInfoParams.setCurrent(current);
            pageInfoParams.setSize(size);
            filterWorkOrderParams.setKeyword(keyword);
            filterWorkOrderParams.setWorkOrderType(null);
            filterWorkOrderParams.setWorkOrderState(null);
            filterWorkOrderParams.setPlanStartTime(null);
            filterWorkOrderParams.setPlanEndTime(null);
        }
        MobileWorkOrderParam mobileWorkOrderParam = new MobileWorkOrderParam();
        mobileWorkOrderParam.setParam(pageInfoParams);
        mobileWorkOrderParam.setWorkOrderVO(filterWorkOrderParams);
        filterWorkOrderParams.setWorkOrderType(WorkOrderTypeEnum.TRIAL_MODEL);
        mPresenter.loadWorkOrderList(mobileWorkOrderParam);
    }

    @Override
    public void onLoadWorkOrderListSuccess(ListModel<WorkOrderListResbean> list) {
        if (list != null && list.getRecords().size() > 0) {
            maskView.setVisibility(View.GONE);
            swipeMenuRecyclerView.setVisibility(View.VISIBLE);
            if (isRefresh() || filterCurrent == 1) {
                adapter.clearItems();
            }
            if (list.getRecords().size() > 0) {
                adapter.addAll(list.getRecords());
                adapter.notifyDataSetChanged();
            }
            swipeMenuRecyclerView.loadMoreFinish(false, list.isHasNextPage());
        } else {
            if (isRefresh() || filterCurrent == 1) {
                //显示空视图
                maskView.setVisibility(View.VISIBLE);
                swipeMenuRecyclerView.setVisibility(View.GONE);
                maskView.show();
            } else {
                swipeMenuRecyclerView.loadMoreFinish(false, false);
            }
        }
    }

    @Override
    public void onWorkOrderTypeChoosed(String typeEnum, String text) {
        tvWorkOrderType.setText(text);
        filterWorkOrderParams.setWorkOrderType(typeEnum);
    }

    @Override
    public void onWorkOrderStateChoosed(String stateEnum, String text) {
        tvWorkOrderState.setText(text);
        filterWorkOrderParams.setWorkOrderType(stateEnum);
    }

    @Override
    public void onCheckWorkOrderHavingParameterSuccess(boolean isHave, String orderId, String procedureId) {
        Intent intent = null;
        if (isHave) {
            intent = new Intent(WorkOrderListActivity.this, ShiMuHistoryActivity.class);
            intent.putExtra(ShiMuHistoryActivity.WORK_ORDER_ID, orderId);
            intent.putExtra(ShiMuHistoryActivity.PROCEDURE_ID, procedureId);
        } else {
            intent = new Intent(WorkOrderListActivity.this, ShiMuActivity.class);
            intent.putExtra(ShiMuHistoryActivity.WORK_ORDER_ID, orderId);
            intent.putExtra(ShiMuHistoryActivity.PROCEDURE_ID, procedureId);
        }
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        View parent = findViewById(android.R.id.content);
        switch (v.getId()) {
            case R.id.ivFilter:
                boolean drawerOpen = dlOrderFilter.isDrawerOpen(nvOrderFilter);
                if (drawerOpen) {
                    dlOrderFilter.closeDrawer(nvOrderFilter);
                } else {
                    dlOrderFilter.openDrawer(nvOrderFilter);
                }
                break;
            case R.id.tvFilterStartTime:
                showDatePickDialog(DateType.TYPE_ALL, START_TIME);
                break;
            case R.id.tvFilterEndTime:
                showDatePickDialog(DateType.TYPE_ALL, END_TIME);
                break;
            case R.id.btnReset:
                toastShort("重置");
                resetAllFilterCondition();
                break;
            case R.id.btnConfirm:
                toastShort("确定");
                isFilterRefresh = true;
                requestWorkOrderList(null, false);
                dlOrderFilter.closeDrawer(nvOrderFilter);
                break;
            case R.id.rlWorkOrderType:
                mPresenter.showChooseWorkOrderTypePopupwindow(WorkOrderListActivity.this, parent);
                break;
            case R.id.rlWorkOrderState:
                mPresenter.showChooseWorkOrderStatePopupwindow(WorkOrderListActivity.this, parent);
                break;
            case R.id.tvSearch:
                etSearch.setVisibility(View.VISIBLE);
                swipeMenuRecyclerView.setVisibility(View.GONE);
                tvCancelSearch.setVisibility(View.VISIBLE);
                ivFilter.setVisibility(View.GONE);
                break;
            case R.id.tvCancelSearch:
                etSearch.setVisibility(View.GONE);
                swipeMenuRecyclerView.setVisibility(View.VISIBLE);
                tvCancelSearch.setVisibility(View.GONE);
                ivFilter.setVisibility(View.VISIBLE);
                KeyboardUtils.hideSoftInput(etSearch);
                etSearch.setText(null);
                break;
        }
    }

    private boolean isFilterRefresh = false;


    /**
     * 重置所有筛选条件
     */
    private void resetAllFilterCondition() {
        isFilterRefresh = false;
        tvWorkOrderType.setText(null);
        tvWorkOrderState.setText(null);
        tvFilterStartTime.setText(null);
        tvFilterEndTime.setText(null);
        filterWorkOrderParams.setPlanStartTime(null);
        filterWorkOrderParams.setPlanEndTime(null);
        //关掉抽屉
        dlOrderFilter.closeDrawer(nvOrderFilter);
        requestWorkOrderList(null, true);
    }

    /**
     * 弹出选择时间dialog
     *
     * @param type
     * @param startOrEnd
     */
    private void showDatePickDialog(DateType type, int startOrEnd) {
        DatePickDialog dialog = new DatePickDialog(WorkOrderListActivity.this);
        //点击外部不消失
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        //设置上下年分限制
        dialog.setYearLimt(15);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
        //设置选择回调
        dialog.setOnChangeListener(null);
        //设置点击确定按钮回调
        dialog.setOnSureListener(new OnSureListener() {
            @Override
            public void onSure(Date date) {
                if (startOrEnd == START_TIME) {
                    startTime = date.getTime();
                    if (endTime > 0 && startTime > endTime) {
                        toastShort("结束时间早于开始时间");
                        return;
                    }
                    String startTime = TimeUtils.getFormatTime(date);
                    tvFilterStartTime.setText(startTime);
                    filterWorkOrderParams.setPlanStartTime(startTime);
                } else if (startOrEnd == END_TIME) {
                    endTime = date.getTime();
                    if (endTime < startTime) {
                        toastShort("结束时间早于开始时间");
                        return;
                    }
                    String endTime = TimeUtils.getFormatTime(date);
                    tvFilterEndTime.setText(endTime);
                    filterWorkOrderParams.setPlanEndTime(endTime);
                } else {
                    toastShort("时间类型参数错误");
                    return;
                }
            }
        });
        dialog.setOnDialogCancelListener(new DatePickDialog.OnDialogCancelListener() {
            @Override
            public void onCancel() {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    /**
     * 下拉刷新
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (isFilterRefresh) {
                filterCurrent = 1;
                pageInfoParams.setCurrent(filterCurrent);
                requestWorkOrderList(null, false);
            } else {
                requestWorkOrderList(null, true);
            }
        }
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            if (isFilterRefresh) {
                filterCurrent++;
                pageInfoParams.setCurrent(filterCurrent);
                requestWorkOrderList(null, false);
            } else {
                requestWorkOrderList(null, false);
            }
        }
    };

    private boolean isRefresh() {
        return current == 1;
    }

    private void initDrawer() {
        nvOrderFilter.setItemIconTintList(null);
        nvOrderFilter.setNavigationItemSelectedListener(item -> {
            dlOrderFilter.closeDrawer(nvOrderFilter);
            return true;
        });
//        dlOrderFilter.addDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(@NonNull View view, float v) {
//
//            }
//
//            @Override
//            public void onDrawerOpened(@NonNull View view) {
//                String currentTime = mPresenter.getCurrentTime();
//                tvFilterStartTime.setText(currentTime);
//                tvFilterEndTime.setText(currentTime);
//                filterWorkOrderParams.setPlanStartTime(currentTime);
//                filterWorkOrderParams.setPlanEndTime(currentTime);
//            }
//
//            @Override
//            public void onDrawerClosed(@NonNull View view) {
//
//            }
//
//            @Override
//            public void onDrawerStateChanged(int i) {
//
//            }
//        });
    }

    private void initRcv() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration.Builder().orientation(LinearLayoutManager.VERTICAL)
                .dividerHeight(SizeUtils.dp2px(0.5f))
                .dividerLeftMargin(SizeUtils.dp2px(60))
                .dividerColor(R.color.divider)
                .build();
        ConfigUtils.configRecycleView(swipeMenuRecyclerView, dividerItemDecoration);
        mRefreshLayout.setOnRefreshListener(mRefreshListener);
        LoadMoreFootView loadMoreView = new LoadMoreFootView(this);
        swipeMenuRecyclerView.addFooterView(loadMoreView);
        swipeMenuRecyclerView.setLoadMoreView(loadMoreView);
        swipeMenuRecyclerView.setLoadMoreListener(mLoadMoreListener);
        adapter = new OrderListAdapter(this, R.layout.item_work_order_list);
        swipeMenuRecyclerView.setAdapter(adapter);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }
}
