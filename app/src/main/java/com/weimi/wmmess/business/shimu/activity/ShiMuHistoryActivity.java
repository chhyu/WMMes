package com.weimi.wmmess.business.shimu.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.business.shimu.adapter.ShiMuHistoryAdapter;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbean;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbeanDao;
import com.weimi.wmmess.business.shimu.bean.history.HistoryResbean;
import com.weimi.wmmess.business.shimu.viewInterface.IShiMuHistoryView;
import com.weimi.wmmess.business.workOrder.adapter.OrderListAdapter;
import com.weimi.wmmess.params.GeneralParam;
import com.weimi.wmmess.presenter.ShiMuPresenter;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.widget.DividerItemDecoration;
import com.weimi.wmmess.widget.LoadMoreFootView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShiMuHistoryActivity extends WMActivity<ShiMuPresenter> implements IShiMuHistoryView {

    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private TextView tvRightOp;
    public static final String WORK_ORDER_ID = "work_order_id";
    public static final String PROCEDURE_ID = "procedure_Id";
    private SwipeRefreshLayout mRefreshLayout;
    private int size = 20;
    private int current = 1;
    private String workOrderId;
    private String procedureId;
    private ShiMuPresenter presenter;
    private ShiMuHistoryAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_shi_mu_history;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        setTitle("试模列表");
        tvRightOp = findViewById(R.id.tvOption);
        tvRightOp.setVisibility(View.VISIBLE);
        tvRightOp.setText("新建");
        swipeMenuRecyclerView = findViewById(R.id.rcvShiMuList);
        mRefreshLayout = findViewById(R.id.mRefreshLayout);
    }

    @Override
    public void initData() {
        presenter = new ShiMuPresenter(this);
        handleRightOption();
        initRcv();
        parseIntent();
    }

    private void parseIntent() {
        workOrderId = getIntent().getStringExtra(WORK_ORDER_ID);
        procedureId = getIntent().getStringExtra(PROCEDURE_ID);
        if (!StringUtils.isEmpty(workOrderId) && !StringUtils.isEmpty(procedureId)) {
            requestShiMuHistory();
        }
    }

    private void requestShiMuHistory() {
        if (isRefresh()) {
            current = 1;
        } else {
            current++;
        }
        presenter.requestShiMuHistory(size, current, workOrderId, procedureId);
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
        adapter = new ShiMuHistoryAdapter(this, new ArrayList<>());
        swipeMenuRecyclerView.setAdapter(adapter);
        adapter.setOnHistoryItemClickListener(new ShiMuHistoryAdapter.OnHistoryItemClickListener() {
            @Override
            public void onItemClick(HistoryResbean historyResbean) {
                Intent intent = new Intent(ShiMuHistoryActivity.this, CraftConfirmActivity.class);
                intent.putExtra(CraftConfirmActivity.CRAFT_PARAMETER_ID, historyResbean.getCraftParameterId());
                intent.putExtra(CraftConfirmActivity.WORK_ORDER_ID, workOrderId);
                intent.putExtra(CraftConfirmActivity.PROCEDURE_ID, procedureId);
                startActivity(intent);
            }
        });
    }

    /**
     * 下拉刷新
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            requestShiMuHistory();
        }
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            requestShiMuHistory();
        }
    };

    private boolean isRefresh() {
        return current == 1;
    }

    private void handleRightOption() {
        tvRightOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShiMuHistoryActivity.this, ShiMuActivity.class);
                MainApplication.thisTimeId = System.currentTimeMillis();
                intent.putExtra(ShiMuActivity.WORK_ORDER_ID, workOrderId);
                intent.putExtra(ShiMuActivity.PROCEDURE_ID, procedureId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoadShiMuRecodeSuccess(List<HistoryResbean> historyResbeans) {
        adapter.addDatas(historyResbeans);
        mRefreshLayout.setRefreshing(false);
    }
}
