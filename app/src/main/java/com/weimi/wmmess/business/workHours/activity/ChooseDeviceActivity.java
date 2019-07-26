package com.weimi.wmmess.business.workHours.activity;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.weimi.wmmess.R;
import com.weimi.wmmess.business.workHours.adapter.DeviceTreeAdapter;
import com.weimi.wmmess.business.workHours.bean.DeviceChildResbean;
import com.weimi.wmmess.business.workHours.bean.DeviceParentResbean;
import com.weimi.wmmess.business.workHours.presenter.WorkHourPresenter;
import com.weimi.wmmess.business.workHours.viewInterface.IChooseDeviceView;
import com.weimi.wmmess.widget.emptyView.MaskView;

import java.util.ArrayList;
import java.util.List;

public class ChooseDeviceActivity extends ExpandableListActivity implements IChooseDeviceView {
    public static final String WORK_DEVICE = "work_device";
    private MaskView maskView;
    private WorkHourPresenter presenter;
    private PullToRefreshExpandableListView elvDeviceList;
    private DeviceTreeAdapter adapter;

    @Override
    protected void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_device);
        initTheme();
        initView();
        initData();
    }

    public void initTheme() {
        try {
            int result = 0;
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = getResources().getDimensionPixelSize(resourceId);
            }
            LinearLayout toptop = (LinearLayout) this.findViewById(R.id.toptop);
            RelativeLayout.LayoutParams para = new RelativeLayout.LayoutParams(this.getWindowManager().getDefaultDisplay().getWidth(), result);
            //设置修改后的布局。
            toptop.setLayoutParams(para);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initView() {
        maskView = findViewById(R.id.maskview);
        elvDeviceList = findViewById(R.id.elvDeviceList);
        TextView tvToolTitle = findViewById(R.id.tvToolTitle);
        tvToolTitle.setText("选择设备");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    public void initData() {
        presenter = new WorkHourPresenter(this);
        adapter = new DeviceTreeAdapter(this, new ArrayList<>());
        setListAdapter(adapter);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        elvDeviceList.getRefreshableView().setIndicatorBounds(width - 160, width - 40);
//        elvDeviceList.setMode(PullToRefreshBase.Mode.BOTH);//两端刷新
//        elvDeviceList.setRefreshing(false);
        elvDeviceList.getRefreshableView().setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Intent intent = new Intent();
            DeviceChildResbean deviceChildResbean = (DeviceChildResbean) adapter.getChild(groupPosition, childPosition);
            intent.putExtra(WORK_DEVICE, JSON.toJSONString(deviceChildResbean));
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
            maskView.setVisibility(View.GONE);
            elvDeviceList.setVisibility(View.VISIBLE);
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
