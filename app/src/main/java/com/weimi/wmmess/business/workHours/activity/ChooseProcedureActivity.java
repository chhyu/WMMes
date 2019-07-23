package com.weimi.wmmess.business.workHours.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.base.adapter.OnItemClickListener;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.workHours.adapter.ProcedureListAdapter;
import com.weimi.wmmess.business.workHours.bean.ProcedureResbean;
import com.weimi.wmmess.business.workHours.presenter.WorkHourPresenter;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseProcedureView;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.widget.DividerItemDecoration;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

/**
 * 新增工时--选择工序
 */
public class ChooseProcedureActivity extends WMActivity<WorkHourPresenter> implements IChooseProcedureView {

    public static final String WORK_ORDER_BOM_ID = "work_order_bom_id";
    public static final String WORK_ORDER_CRAFT_ID = "work_order_craft_id";
    private EditText etSearch;
    private TextView tvSearch;
    private SwipeRefreshLayout mRefreshLayout;
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private MaskView maskView;
    private int size = 50;
    private int current = 1;
    private String bomId;
    private String craftId;
    private WorkHourPresenter presenter;
    private ProcedureListAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_general_list;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        etSearch = findViewById(R.id.etSearch);
        tvSearch = findViewById(R.id.tvSearch);
        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        swipeMenuRecyclerView = findViewById(R.id.swipeMenuRecyclerView);
        maskView = findViewById(R.id.maskview);
        //张鑫说工序不会太多，故在此不做搜索功能
        RelativeLayout rlSearch = findViewById(R.id.rlSearch);
        rlSearch.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        setTitle("选择工序");
        presenter = new WorkHourPresenter(this);
        bomId = getIntent().getStringExtra(WORK_ORDER_BOM_ID);
        craftId = getIntent().getStringExtra(WORK_ORDER_CRAFT_ID);
        initRcv();
        requestProcedureByWorkOrder(true);
    }

    private boolean isRefresh() {
        return current == 1;
    }

    private void requestProcedureByWorkOrder(boolean isRefresh) {
        if (StringUtils.isEmpty(bomId) || StringUtils.isEmpty(craftId)) {
            toastShort("参数缺失");
            return;
        }
        if (isRefresh) {
            current = 1;
        } else {
            current++;
        }
        presenter.getBomProcedureList(bomId, craftId);
    }

    private void initRcv() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration.Builder().orientation(LinearLayoutManager.VERTICAL)
                .dividerHeight(SizeUtils.dp2px(0.5f))
                .dividerLeftMargin(SizeUtils.dp2px(60))
                .dividerColor(R.color.divider)
                .build();
        ConfigUtils.configRecycleView(swipeMenuRecyclerView, dividerItemDecoration);
        /*------------------------------暂时不要下拉刷新、上拉加载更多--------------------------------*/
//        mRefreshLayout.setRefreshing(false);
//        mRefreshLayout.setEnabled(false);
//        mRefreshLayout.setOnRefreshListener(mRefreshListener);
//        LoadMoreFootView loadMoreView = new LoadMoreFootView(this);
//        swipeMenuRecyclerView.addFooterView(loadMoreView);
//        swipeMenuRecyclerView.setLoadMoreView(loadMoreView);
//        swipeMenuRecyclerView.setLoadMoreListener(mLoadMoreListener);
        adapter = new ProcedureListAdapter(this, R.layout.item_procedure_list);
        swipeMenuRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, ViewHolder holder, int position) {
                ProcedureResbean procedureResbean = adapter.getDatas().get(position);
                Intent intent = new Intent();
                intent.putExtra(AddWorkHourActivity.WORK_PROCEDURE, JSON.toJSONString(procedureResbean));
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public boolean onItemLongClick(View view, ViewHolder holder, int position) {
                return false;
            }
        });
    }

    /**
     * 下拉刷新
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = () -> {
        current = 1;
        requestProcedureByWorkOrder(true);
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = () -> {
        current++;
        requestProcedureByWorkOrder(false);
    };

    @Override
    public void onLoadProcedureListSuccess(List<ProcedureResbean> list) {
        if (list != null && list.size() > 0) {
            maskView.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            if (isRefresh()) {
                adapter.clearItems();
            }
            if (list.size() > 0) {
                adapter.addAll(list);
                adapter.notifyDataSetChanged();
            }
//            swipeMenuRecyclerView.loadMoreFinish(false, listModel.isHasNextPage());
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
