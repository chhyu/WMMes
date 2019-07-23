package com.weimi.wmmess.business.workHours.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SizeUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.base.adapter.OnItemClickListener;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.workHours.adapter.ProcedureListAdapter;
import com.weimi.wmmess.business.workHours.adapter.WorkTimeTypeAdapter;
import com.weimi.wmmess.business.workHours.bean.ProcedureResbean;
import com.weimi.wmmess.business.workHours.bean.WorkTimeTypeResbean;
import com.weimi.wmmess.business.workHours.presenter.WorkHourPresenter;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseTimeTypeView;
import com.weimi.wmmess.business.workOrder.params.MobileWorkOrderParam;
import com.weimi.wmmess.business.workOrder.params.WorkOrderVO;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.params.GeneralParam;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.widget.DividerItemDecoration;
import com.weimi.wmmess.widget.LoadMoreFootView;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

public class ChooseWorkTimeTypeActivity extends WMActivity<WorkHourPresenter> implements IChooseTimeTypeView {
    private EditText etSearch;
    private TextView tvSearch;
    private SwipeRefreshLayout mRefreshLayout;
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private MaskView maskView;
    private int size = 50;
    private int current = 1;
    private WorkHourPresenter presenter;
    private WorkTimeTypeAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_general_list;
    }

    private boolean isRefresh() {
        return current == 1;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        etSearch = findViewById(R.id.etSearch);
        tvSearch = findViewById(R.id.tvSearch);
        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        swipeMenuRecyclerView = findViewById(R.id.swipeMenuRecyclerView);
        maskView = findViewById(R.id.maskview);

        tvSearch.setOnClickListener(v -> {
            String keyword = etSearch.getText().toString();
            requestWorkTimeTypeList(true, keyword);
        });
    }

    @Override
    public void initData() {
        setTitle("工时种类");
        presenter = new WorkHourPresenter(this);
        initRcv();
        requestWorkTimeTypeList(true, null);
    }

    /**
     * 下拉刷新
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = () -> {
        requestWorkTimeTypeList(true, null);
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = () -> {
        requestWorkTimeTypeList(false, null);
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
        adapter = new WorkTimeTypeAdapter(this, R.layout.item_work_time_type_list);
        swipeMenuRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, ViewHolder holder, int position) {
                WorkTimeTypeResbean workTimeTypeResbean = adapter.getDatas().get(position);
                Intent intent = new Intent();
                intent.putExtra(AddWorkHourActivity.WORK_TIME_TYPE, JSON.toJSONString(workTimeTypeResbean));
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public boolean onItemLongClick(View view, ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void requestWorkTimeTypeList(boolean isRefresh, String keyWord) {
        if (isRefresh) {
            current = 1;
        } else {
            current++;
        }
        GeneralParam generalParam = new GeneralParam();
        generalParam.setSize(size);
        generalParam.setCurrent(current);
        generalParam.setKeyword(keyWord);
        presenter.loadWorkTimeTypeList(generalParam);
    }

    @Override
    public void onLoadWorkTimeTypeSuccess(ListModel<WorkTimeTypeResbean> listModel) {
        if (listModel != null && listModel.getRecords().size() > 0) {
            maskView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            if (isRefresh()) {
                adapter.clearItems();
            }
            if (listModel.getRecords().size() > 0) {
                adapter.addAll(listModel.getRecords());
                adapter.notifyDataSetChanged();
            }
            swipeMenuRecyclerView.loadMoreFinish(false, listModel.isHasNextPage());
        } else {
            if (isRefresh()) {
                //显示空视图
                maskView.setVisibility(View.VISIBLE);
                mRefreshLayout.setVisibility(View.GONE);
                maskView.show();
            } else {
                swipeMenuRecyclerView.loadMoreFinish(false, false);
            }
        }
    }
}
