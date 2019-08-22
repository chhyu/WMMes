package com.weimi.wmmess.business.spotCheck.activity;

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
import com.blankj.utilcode.util.StringUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.base.adapter.OnItemClickListener;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.spotCheck.adapter.ChooseModelFrameAdapter;
import com.weimi.wmmess.business.spotCheck.adapter.ChooseModelKernelAdapter;
import com.weimi.wmmess.business.spotCheck.bean.ModelFrameResbean;
import com.weimi.wmmess.business.spotCheck.bean.ModelKernelResbean;
import com.weimi.wmmess.business.spotCheck.presenter.SpotCheckPresenter;
import com.weimi.wmmess.business.spotCheck.viewInterface.IChooseModelKernelView;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.params.GeneralParam;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.widget.DividerItemDecoration;
import com.weimi.wmmess.widget.LoadMoreFootView;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

public class ChooseModelKernelActivity extends WMActivity<SpotCheckPresenter> implements IChooseModelKernelView {
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private MaskView maskView;
    private int size = 20;
    private int current = 1;
    private EditText etSearch;
    private TextView tvSearch;
    private SpotCheckPresenter presenter;
    public static final String MODEL_KERNEL = "MODEL_KERNEL";
    private ChooseModelKernelAdapter adapter;

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
            requestModelKernelList(true, keyword);
        });
    }

    private void requestModelKernelList(boolean isRefresh, String keyword) {
        if (isRefresh) {
            current = 1;
        } else {
            current++;
        }
        GeneralParam param = new GeneralParam();
        param.setCurrent(current);
        param.setSize(size);
        param.setKeyword(keyword);
        presenter.getModelKernelList(param);
    }

    @Override
    public void initData() {
        setTitle("选择模仁");
        presenter = new SpotCheckPresenter(this);
        initRcv();
        requestModelKernelList(true, null);
    }


    /**
     * 下拉刷新
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = () -> requestModelKernelList(true, null);

    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = () -> requestModelKernelList(false, null);

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
        adapter = new ChooseModelKernelAdapter(this, R.layout.item_choose_model_info_list);
        swipeMenuRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, ViewHolder holder, int position) {
                ModelKernelResbean modelKernelResbean = adapter.getDatas().get(position);
                Intent intent = new Intent();
                intent.putExtra(MODEL_KERNEL, JSON.toJSONString(modelKernelResbean));
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
    public void hideProgress() {
        super.hideProgress();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onGetModelKernelListSuccess(ListModel<ModelKernelResbean> listModel) {
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
