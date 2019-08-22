package com.weimi.wmmess.business.spotCheck.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.base.adapter.OnItemClickListener;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.spotCheck.adapter.SpecificTypeEquipmentAdapter;
import com.weimi.wmmess.business.spotCheck.presenter.SpotCheckPresenter;
import com.weimi.wmmess.business.workHours.adapter.EmployeeAdapter;
import com.weimi.wmmess.business.workHours.bean.EmployeeResbean;
import com.weimi.wmmess.business.workHours.presenter.WorkHourPresenter;
import com.weimi.wmmess.params.GeneralParam;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.widget.DividerItemDecoration;
import com.weimi.wmmess.widget.LoadMoreFootView;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

/**
 * 根据
 */
public class ChooseSpecificEquipmentActivity extends WMActivity<SpotCheckPresenter> {
    public static final String EQUIPMENT_TYPE_CODE = "equipment_type_code";
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private MaskView maskView;

    private int size = 20;
    private int current = 1;
    private EditText etSearch;
    private TextView tvSearch;
    private SpotCheckPresenter presenter;

    @Override
    public int initLayout() {
        return R.layout.activity_general_list;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        swipeMenuRecyclerView = findViewById(R.id.swipeMenuRecyclerView);
        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        maskView = findViewById(R.id.maskview);

        etSearch = findViewById(R.id.etSearch);
        tvSearch = findViewById(R.id.tvSearch);

        tvSearch.setOnClickListener(v -> {
            String keyword = etSearch.getText().toString();
            if (StringUtils.isEmpty(keyword)) {
                toastShort("请输入搜索关键字");
                return;
            }
            requestEquipmentList(true, keyword);
        });
    }

    @Override
    public void initData() {
        setTitle("选择设备");
        presenter = new SpotCheckPresenter(this);
        initRcv();
        requestEquipmentList(true, null);
    }

    /**
     * 下拉刷新
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = () -> {
        requestEquipmentList(true, null);
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = () -> {
        requestEquipmentList(false, null);
    };

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
        SpecificTypeEquipmentAdapter adapter = new SpecificTypeEquipmentAdapter(this, R.layout.item_employee_list);
        swipeMenuRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }


    private void requestEquipmentList(boolean isRefresh, String keyword) {
        if (isRefresh) {
            current = 1;
        } else {
            current++;
        }
        GeneralParam param = new GeneralParam();
        param.setKeyword(keyword);
        param.setSize(size);
        param.setCurrent(current);

    }

}
