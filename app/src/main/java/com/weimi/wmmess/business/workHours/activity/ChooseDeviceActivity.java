package com.weimi.wmmess.business.workHours.activity;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ToastUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.business.workHours.adapter.DeviceTreeAdapter;
import com.weimi.wmmess.business.workHours.bean.DeviceChildResbean;
import com.weimi.wmmess.business.workHours.bean.DeviceParentResbean;
import com.weimi.wmmess.business.workHours.presenter.WorkHourPresenter;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseDeviceView;
import com.weimi.wmmess.business.workOrder.CraftParamsListActivity;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChooseDeviceActivity extends ExpandableListActivity implements IChooseDeviceView {
    private EditText etSearch;
    private TextView tvSearch;
    private MaskView maskView;

    private WorkHourPresenter presenter;
    private PullToRefreshExpandableListView elvDeviceList;
    private DeviceTreeAdapter adapter;

    @Override
    protected void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_device);
        initView();
        initData();
    }

    public void initView() {
        etSearch = findViewById(R.id.etSearch);
        tvSearch = findViewById(R.id.tvSearch);
        maskView = findViewById(R.id.maskview);
        elvDeviceList = findViewById(R.id.elvDeviceList);
    }

    public void initData() {
        setTitle("选择设备");
        presenter = new WorkHourPresenter(this);
        adapter = new DeviceTreeAdapter(this, new ArrayList<>());
        setListAdapter(adapter);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        elvDeviceList.getRefreshableView().setIndicatorBounds(width - 160, width - 40);
        elvDeviceList.setMode(PullToRefreshBase.Mode.BOTH);//两端刷新

        elvDeviceList.getRefreshableView().setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Intent intent = new Intent();
            DeviceChildResbean deviceChildResbean = (DeviceChildResbean) adapter.getChild(groupPosition, childPosition);
            intent.putExtra(AddWorkHourActivity.WORK_DEVICE, JSON.toJSONString(deviceChildResbean));
            setResult(RESULT_OK, intent);
            finish();
            return false;
        });
        presenter.loadDeviceList();
    }

    @Override
    public void onLoadDeviceListSuccess(List<DeviceParentResbean> list) {
        if (list == null || list.size() == 0) {
            maskView.setVisibility(View.VISIBLE);
            elvDeviceList.setVisibility(View.GONE);
            maskView.show();
        } else {
            adapter.addDatas(list);
        }
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
