package com.weimi.wmmess.business.workHours;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.base.adapter.OnItemClickListener;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.workHours.activity.AddWorkHourActivity;
import com.weimi.wmmess.business.workHours.adapter.WorkHourAdapter;
import com.weimi.wmmess.business.workHours.bean.WorkHourListResbean;
import com.weimi.wmmess.business.workHours.presenter.WorkHourPresenter;
import com.weimi.wmmess.business.workHours.viewInterface.IWorkHourView;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.params.GeneralParam;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.widget.LoadMoreFootView;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

public class WorkHourActivity extends WMActivity<WorkHourPresenter> implements IWorkHourView {

    private SwipeMenuRecyclerView rcvWorkHourList;
    private MaskView maskView;
    private WorkHourPresenter presenter;
    private int size = 20;
    private int current = 1;
    private SwipeRefreshLayout mRefreshLayout;
    private WorkHourAdapter adapter;
    private ImageView ivAdd;

    @Override
    public int initLayout() {
        return R.layout.activity_work_hour;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        rcvWorkHourList = findViewById(R.id.rcvWorkHourList);
        ivAdd = findViewById(R.id.ivAdd);
        maskView = findViewById(R.id.maskview);
        mRefreshLayout = findViewById(R.id.mRefreshLayout);
        ivAdd.setVisibility(View.VISIBLE);

        ivAdd.setOnClickListener(v -> {
            YoYo.with(Techniques.Pulse).duration(300).playOn(ivAdd);
            Intent intent = new Intent(WorkHourActivity.this, AddWorkHourActivity.class);
            startActivity(intent);
        });
    }

    private boolean isRefresh() {
        return current == 1;
    }

    @Override
    public void initData() {
        setTitle("工时列表");
        presenter = new WorkHourPresenter(this);
        ConfigUtils.configRecycleView(rcvWorkHourList);
        mRefreshLayout.setOnRefreshListener(mRefreshListener);
        LoadMoreFootView loadMoreView = new LoadMoreFootView(this);
        rcvWorkHourList.addFooterView(loadMoreView);
        rcvWorkHourList.setLoadMoreView(loadMoreView);
        rcvWorkHourList.setLoadMoreListener(mLoadMoreListener);
        adapter = new WorkHourAdapter(this, R.layout.item_work_hour_list);
        rcvWorkHourList.setAdapter(adapter);

        adapter.setOnSwipeDeleteClickListener(workHourListResbean -> {
            presenter.deleteCurrentWorkHourItem(workHourListResbean);
        });

        adapter.setOnWorkHourItemClickListener(workHourListResbean -> {
            if (StringUtils.isEmpty(workHourListResbean.getRecordId())) {
                toastShort("参数错误,请刷新后重试");
                return;
            }
            Intent intent = new Intent(WorkHourActivity.this, AddWorkHourActivity.class);
            intent.putExtra(AddWorkHourActivity.WORK_HOUR_ID, workHourListResbean.getRecordId());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestWorkHourList();
    }

    private void requestWorkHourList() {
        if (isRefresh()) {
            current = 1;
        } else {
            current++;
        }
        GeneralParam param = new GeneralParam();
        param.setCurrent(current);
        param.setSize(size);
        presenter.loadWorkHourList(param);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 下拉刷新
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = () -> {
        current = 1;
        requestWorkHourList();
    };

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = () -> {
        current++;
        requestWorkHourList();
    };

    @Override
    public void onLoadWorkHourListSuccess(ListModel<WorkHourListResbean> listModel) {
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
            rcvWorkHourList.loadMoreFinish(false, listModel.isHasNextPage());
        } else {
            if (isRefresh()) {
                //显示空视图
                maskView.setVisibility(View.VISIBLE);
                mRefreshLayout.setVisibility(View.GONE);
                maskView.show();
            } else {
                rcvWorkHourList.loadMoreFinish(false, false);
            }
        }
    }

    @Override
    public void onDeleteWorkHourSuccess() {
        toastShort("删除成功");
        requestWorkHourList();
    }
}
