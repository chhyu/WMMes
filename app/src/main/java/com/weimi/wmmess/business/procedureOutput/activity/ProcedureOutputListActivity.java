package com.weimi.wmmess.business.procedureOutput.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.business.procedureInput.activity.AddProcedureInputActivity;
import com.weimi.wmmess.business.procedureInput.bean.ProcedureInputResbean;
import com.weimi.wmmess.business.procedureOutput.adapter.ProcedureOutputListAdapter;
import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputResbean;
import com.weimi.wmmess.business.procedureOutput.presenter.ProcedureOutputPresenter;
import com.weimi.wmmess.business.procedureOutput.viewInterface.IProcedureOutputView;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.params.GeneralParam;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.widget.DividerItemDecoration;
import com.weimi.wmmess.widget.LoadMoreFootView;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

public class ProcedureOutputListActivity extends WMActivity<ProcedureOutputPresenter> implements IProcedureOutputView {
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private MaskView maskView;
    private int size = 20;
    private int current = 1;
    private EditText etSearch;
    private TextView tvSearch;
    private ProcedureOutputPresenter presenter;
    private ProcedureOutputListAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_procedure_output_list;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        swipeMenuRecyclerView = findViewById(R.id.swipeMenuRecyclerView);
        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        maskView = findViewById(R.id.maskview);

        etSearch = findViewById(R.id.etSearch);
        tvSearch = findViewById(R.id.tvSearch);
        ImageView ivAdd = findViewById(R.id.ivAdd);
        ivAdd.setVisibility(View.VISIBLE);
        ivAdd.setOnClickListener(v -> {
            Intent intent = new Intent(ProcedureOutputListActivity.this, AddProcedureOutputActivity.class);
            startActivity(intent);
        });

        tvSearch.setOnClickListener(v -> {
            String keyword = etSearch.getText().toString();
            if (StringUtils.isEmpty(keyword)) {
                toastShort("请输入搜索关键字");
                return;
            }
            requestProcedureOutputList(true, keyword);
        });
    }

    private void requestProcedureOutputList(boolean isRefresh, String keyword) {
        if (isRefresh) {
            current = 1;
        } else {
            current++;
        }
        GeneralParam param = new GeneralParam();
        param.setCurrent(current);
        param.setSize(size);
        param.setKeyword(keyword);
        presenter.loadProcedureOutputList(param);
    }

    @Override
    public void initData() {
        setTitle("工序产出");
        presenter = new ProcedureOutputPresenter(this);
        initRcv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestProcedureOutputList(true, null);
    }

    /**
     * 下拉刷新
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = () -> {
        requestProcedureOutputList(true, null);
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = () -> {
        requestProcedureOutputList(false, null);
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
        adapter = new ProcedureOutputListAdapter(this, R.layout.item_procedure_out_list);
        swipeMenuRecyclerView.setAdapter(adapter);

        adapter.setOnProcedureOutputItemClickListener(new ProcedureOutputListAdapter.OnProcedureOutputItemClickListener() {

            @Override
            public void onProcedureOutputItemClick(ProcedureOutputResbean procedureOutputResbean) {
                Intent intent = new Intent(ProcedureOutputListActivity.this, AddProcedureOutputActivity.class);
                intent.putExtra(AddProcedureOutputActivity.RECORD_ID, procedureOutputResbean.getRecordId());
                startActivity(intent);
            }

            @Override
            public void onSwipProcedureOutputItemClick(ProcedureOutputResbean procedureOutputResbean) {
                presenter.deleteProcedureOutputItem(procedureOutputResbean);
            }
        });
    }

    private boolean isRefresh() {
        return current == 1;
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoadProcedureOutputSuccess(ListModel<ProcedureOutputResbean> listModel) {
        if (listModel != null && listModel.getRecords().size() > 0) {
            maskView.setVisibility(View.GONE);
            swipeMenuRecyclerView.setVisibility(View.VISIBLE);
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
                swipeMenuRecyclerView.setVisibility(View.GONE);
                maskView.show();
            } else {
                swipeMenuRecyclerView.loadMoreFinish(false, false);
            }
        }
    }

    @Override
    public void onDeteleProcedureOutputItemSuccess() {
        toastShort("删除成功");
        finish();
        requestProcedureOutputList(true, null);
    }
}
