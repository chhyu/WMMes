package com.weimi.wmmess.business.workOrder;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.weimi.wmmess.R;
import com.weimi.wmmess.business.workOrder.adapter.CraftParamsListAdapter;
import com.weimi.wmmess.business.workOrder.bean.CraftRebean;
import com.weimi.wmmess.business.workOrder.presenter.WorkOrderPresenter;
import com.weimi.wmmess.business.workOrder.viewInterface.ICraftParamsView;

import java.util.ArrayList;
import java.util.List;

/**
 * 工艺参数列表
 */
@Deprecated
public class CraftParamsListActivity extends ExpandableListActivity implements ICraftParamsView {

    public static final String WORK_ORDER_ID = "work_order_id";
    private PullToRefreshExpandableListView elvCraftParamsList;
    private WorkOrderPresenter presenter;
    private CraftParamsListAdapter adapter;
    private int size = 20;
    private int current = 1;

    @Override
    protected void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craft_params);
        initView();
        initData();
    }


    public void initView() {
        elvCraftParamsList = findViewById(R.id.elvCraftParamsList);
    }

    public void initData() {
        setTitle("工艺参数列表");
        presenter = new WorkOrderPresenter(this);
        String workOrderId = getIntent().getStringExtra(WORK_ORDER_ID);
        requestCraftParamsList(workOrderId);

        adapter = new CraftParamsListAdapter(this, new ArrayList<>());
//        elvCraftParamsList.setAdapter(mAdapter);
        setListAdapter(adapter);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        elvCraftParamsList.getRefreshableView().setIndicatorBounds(width - 160, width - 40);
        elvCraftParamsList.setMode(PullToRefreshBase.Mode.BOTH);//两端刷新
        elvCraftParamsList.setOnRefreshListener(refreshView -> {
            if (PullToRefreshBase.Mode.PULL_FROM_START == refreshView.getCurrentMode()) {
                current = 1;
            } else if (PullToRefreshBase.Mode.PULL_FROM_END == refreshView.getCurrentMode()) {
                current++;
            }
            requestCraftParamsList(workOrderId);
        });
        elvCraftParamsList.getRefreshableView().setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            ToastUtils.showShort("groupPosition==" + groupPosition + ",childPosition==" + childPosition);
            // TODO: 2019/7/4 跳转到工艺参数界面
            Intent intent = new Intent(CraftParamsListActivity.this, CraftParamsListActivity.class);
            startActivity(intent);
            return false;
        });

    }

    private void requestCraftParamsList(String workOrderId) {
        presenter.requestCraftParamsList(workOrderId, size, current);
    }

    @Override
    public void onLoadCraftParamsListSuccessed(List<CraftRebean> craftRebeans) {
        adapter.addDatas(craftRebeans);
        elvCraftParamsList.onRefreshComplete();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void hideProgress() {

    }
}
