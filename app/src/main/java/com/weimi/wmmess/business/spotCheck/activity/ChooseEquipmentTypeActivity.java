package com.weimi.wmmess.business.spotCheck.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Adapter;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.SizeUtils;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.base.adapter.OnItemClickListener;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.spotCheck.adapter.EquipmentTypeAdapter;
import com.weimi.wmmess.business.spotCheck.adapter.SpotCheckProjectListAdapter;
import com.weimi.wmmess.business.spotCheck.bean.EquipmentTypeResbean;
import com.weimi.wmmess.business.spotCheck.bean.SpotCheckProjectResbean;
import com.weimi.wmmess.business.spotCheck.presenter.SpotCheckPresenter;
import com.weimi.wmmess.business.workOrder.viewInterface.IChooseEquipmentTypeView;
import com.weimi.wmmess.utils.ConfigUtils;
import com.weimi.wmmess.widget.DividerItemDecoration;
import com.weimi.wmmess.widget.LoadMoreFootView;
import com.weimi.wmmess.widget.emptyView.MaskView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

public class ChooseEquipmentTypeActivity extends WMActivity<SpotCheckPresenter> implements IChooseEquipmentTypeView {


    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    private MaskView maskView;
    private EquipmentTypeAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_choose_equipment_type;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        swipeMenuRecyclerView = findViewById(R.id.swipeMenuRecyclerView);
        maskView = findViewById(R.id.maskview);
    }

    @Override
    public void initData() {
        setTitle("选择设备类型");
        initRcv();
        SpotCheckPresenter presenter = new SpotCheckPresenter(this);
        presenter.getEquipmentTypeList();
    }

    public static final String RESULT_EQUIPMENT_TYPE = "result_equipment_type";

    private void initRcv() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration.Builder().orientation(LinearLayoutManager.VERTICAL)
                .dividerHeight(SizeUtils.dp2px(0.5f))
                .dividerLeftMargin(SizeUtils.dp2px(60))
                .dividerColor(R.color.divider)
                .build();
        ConfigUtils.configRecycleView(swipeMenuRecyclerView, dividerItemDecoration);

        adapter = new EquipmentTypeAdapter(ChooseEquipmentTypeActivity.this, R.layout.item_equipment_type_list);
        swipeMenuRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, ViewHolder holder, int position) {
                EquipmentTypeResbean equipmentTypeResbean = adapter.getDatas().get(position);
                Intent intent = new Intent();
                intent.putExtra(RESULT_EQUIPMENT_TYPE, JSON.toJSONString(equipmentTypeResbean));
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
    public void onGetEquipmentTypeSuccess(List<EquipmentTypeResbean> list) {
        if (list == null || list.size() <= 0) {
            maskView.setVisibility(View.VISIBLE);
            swipeMenuRecyclerView.setVisibility(View.GONE);
            maskView.show();
        } else {
            maskView.setVisibility(View.GONE);
            swipeMenuRecyclerView.setVisibility(View.VISIBLE);
            adapter.clearItems();
            adapter.addAll(list);
        }
    }
}
