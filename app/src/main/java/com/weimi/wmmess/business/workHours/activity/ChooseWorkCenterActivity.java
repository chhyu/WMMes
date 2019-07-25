package com.weimi.wmmess.business.workHours.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.base.adapter.OnItemClickListener;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.workHours.adapter.WorkCenterAdapter;
import com.weimi.wmmess.business.workHours.bean.WorkCenterResbean;
import com.weimi.wmmess.business.workHours.presenter.WorkHourPresenter;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseWorkCenterView;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

public class ChooseWorkCenterActivity extends WMActivity<WorkHourPresenter> implements IChooseWorkCenterView {

    public static final String WORK_CENTER = "work_center";
    private SwipeRefreshLayout mRefreshLayout;
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private MaskView maskView;
    private WorkHourPresenter presenter;
    private WorkCenterAdapter adapter;
    private int size = 50;
    private int current = 1;

    @Override
    public int initLayout() {
        return R.layout.activity_general_list;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        RelativeLayout rlSearch = findViewById(R.id.rlSearch);
        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        swipeMenuRecyclerView = findViewById(R.id.swipeMenuRecyclerView);
        maskView = findViewById(R.id.maskview);
        rlSearch.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        setTitle("工作中心");
        presenter = new WorkHourPresenter(this);
        ConfigUtils.configRecycleView(swipeMenuRecyclerView);
        adapter = new WorkCenterAdapter(this, R.layout.item_work_center_list);
        swipeMenuRecyclerView.setAdapter(adapter);
        presenter.getWorkcenterList();

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, ViewHolder holder, int position) {
                WorkCenterResbean workCenterResbean = adapter.getDatas().get(position);
                Intent intent = new Intent();
                intent.putExtra(WORK_CENTER, JSON.toJSONString(workCenterResbean));
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public boolean onItemLongClick(View view, ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private boolean isRefresh() {
        return current == 1;
    }

    @Override
    public void onLoadWorkCenterSuccess(List<WorkCenterResbean> list) {
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
