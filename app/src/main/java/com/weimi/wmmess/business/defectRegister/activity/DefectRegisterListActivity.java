package com.weimi.wmmess.business.defectRegister.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.business.defectRegister.adapter.DefectRegisterListAdapter;
import com.weimi.wmmess.business.defectRegister.bean.DefectRegisterResbean;
import com.weimi.wmmess.business.defectRegister.presenter.DefectRegisterPresenter;
import com.weimi.wmmess.business.defectRegister.viewInterface.IDefectRegisterView;
import com.weimi.wmmess.business.procedureOutput.activity.AddProcedureOutputActivity;
import com.weimi.wmmess.business.procedureOutput.activity.ProcedureOutputListActivity;
import com.weimi.wmmess.business.procedureOutput.adapter.ProcedureOutputListAdapter;
import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputResbean;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.params.GeneralParam;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.widget.DividerItemDecoration;
import com.weimi.wmmess.widget.LoadMoreFootView;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

public class DefectRegisterListActivity extends WMActivity<DefectRegisterPresenter> implements IDefectRegisterView {

    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private MaskView maskView;
    private int size = 20;
    private int current = 1;
    private EditText etSearch;
    private TextView tvSearch;
    private DefectRegisterPresenter presenter;
    private DefectRegisterListAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_defect_register_list;
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
            Intent intent = new Intent(DefectRegisterListActivity.this, AddDefectRegisterActivity.class);
            startActivity(intent);
        });

        tvSearch.setOnClickListener(v -> {
            String keyword = etSearch.getText().toString();
            if (StringUtils.isEmpty(keyword)) {
                toastShort("请输入搜索关键字");
                return;
            }
            requestDefectRegisterList(true, keyword);
        });
    }

    private void requestDefectRegisterList(boolean isRefresh, String keyword) {
        if (isRefresh) {
            current = 1;
        } else {
            current++;
        }
        GeneralParam param = new GeneralParam();
        param.setCurrent(current);
        param.setSize(size);
        param.setKeyword(keyword);
        presenter.loadDefectRegisterList(param);
    }

    @Override
    public void initData() {
        setTitle("缺陷登记");
        presenter = new DefectRegisterPresenter(this);

        initRcv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestDefectRegisterList(true, null);
    }

    /**
     * 下拉刷新
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = () -> requestDefectRegisterList(true, null);

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = () -> requestDefectRegisterList(false, null);

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
        adapter = new DefectRegisterListAdapter(this, R.layout.item_defect_register_list);
        swipeMenuRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new DefectRegisterListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(Object o) {
                DefectRegisterResbean defectRegisterResbean = (DefectRegisterResbean) o;
                Intent intent = new Intent(DefectRegisterListActivity.this, AddDefectRegisterActivity.class);
                intent.putExtra(AddDefectRegisterActivity.RECORD_ID, defectRegisterResbean.getRecordId());
                startActivity(intent);
            }

            @Override
            public void onSwipItemClick(Object o) {
                DefectRegisterResbean defectRegisterResbean = (DefectRegisterResbean) o;
                if (!StringUtils.isEmpty(defectRegisterResbean.getRecordId())) {
                    presenter.deleteItem(defectRegisterResbean.getRecordId());
                }
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
    public void onLoadDefectRegisterListSuccess(ListModel<DefectRegisterResbean> listModel) {
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
    public void onDeleteSuccess() {
        toastShort("删除成功");
        requestDefectRegisterList(true, null);
    }
}
