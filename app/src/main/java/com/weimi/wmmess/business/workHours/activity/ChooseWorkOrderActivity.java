package com.weimi.wmmess.business.workHours.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.weimi.wmmess.business.workHours.WorkHourActivity;
import com.weimi.wmmess.business.workHours.presenter.WorkHourPresenter;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseWorkOrderView;
import com.weimi.wmmess.business.workOrder.adapter.OrderListAdapter;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;
import com.weimi.wmmess.business.workOrder.params.MobileWorkOrderParam;
import com.weimi.wmmess.business.workOrder.params.WorkOrderVO;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.params.GeneralParam;
import com.weimi.wmmess.utils.CommUtils;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.widget.DividerItemDecoration;
import com.weimi.wmmess.widget.LoadMoreFootView;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

/**
 * 新增工时--选择工单
 */
public class ChooseWorkOrderActivity extends WMActivity<WorkHourPresenter> implements IChooseWorkOrderView {

    public static final String WORK_ORDER = "work_order";
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private MaskView maskView;
    private WorkHourPresenter presenter;
    private int size = 20;
    private int current = 1;
    private EditText etSearch;
    private TextView tvSearch;
    private OrderListAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_choose_work_order;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        swipeMenuRecyclerView = findViewById(R.id.smrcvWorkOrder);
        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        maskView = findViewById(R.id.maskview);

        etSearch = findViewById(R.id.etSearch);
        tvSearch = findViewById(R.id.tvSearch);

        tvSearch.setOnClickListener(v -> {
            String keyword = etSearch.getText().toString();
            if (StringUtils.isEmpty(keyword)) {
                toastShort("请输入关键字");
                return;
            }
            requestMesCommonProductWorkOrder(true, keyword);
        });
    }

    @Override
    public void initData() {
        setTitle("选择工单");
        presenter = new WorkHourPresenter(this);
        initRcv();
        requestMesCommonProductWorkOrder(true, null);
    }

    private boolean isRefresh() {
        return current == 1;
    }

    private void requestMesCommonProductWorkOrder(boolean isrefresh, String keyWord) {
        if (isrefresh) {
            current = 1;
        } else {
            current++;
        }
        MobileWorkOrderParam mobileWorkOrderParam = new MobileWorkOrderParam();

        GeneralParam generalParam = new GeneralParam();

        generalParam.setSize(size);
        generalParam.setCurrent(current);
        WorkOrderVO workOrderVO = new WorkOrderVO();
        workOrderVO.setKeyword(keyWord);
        mobileWorkOrderParam.setParam(generalParam);
        mobileWorkOrderParam.setWorkOrderVO(workOrderVO);
        presenter.loadMesCommonProductWorkOrder(mobileWorkOrderParam);
    }

    /**
     * 下拉刷新
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = () -> {
        current = 1;
        requestMesCommonProductWorkOrder(true, null);
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = () -> {
        current++;
        requestMesCommonProductWorkOrder(false, null);
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
        adapter = new OrderListAdapter(this, R.layout.item_work_order_list);
        swipeMenuRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, ViewHolder holder, int position) {
                WorkOrderListResbean workOrderListResbean = adapter.getDatas().get(position);
                Intent intent = new Intent();
                intent.putExtra(WORK_ORDER, JSON.toJSONString(workOrderListResbean));
                setResult(RESULT_OK, intent);
                finish();
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

    @Override
    public void onLoadWorkOrderListSuccess(ListModel<WorkOrderListResbean> listModel) {
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


}
