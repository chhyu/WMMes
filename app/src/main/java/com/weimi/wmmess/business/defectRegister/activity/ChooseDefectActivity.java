package com.weimi.wmmess.business.defectRegister.activity;

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
import com.weimi.wmmess.business.defectRegister.adapter.DefectTreeAdapter;
import com.weimi.wmmess.business.defectRegister.bean.DefectChildResbean;
import com.weimi.wmmess.business.defectRegister.bean.DefectParentResbean;
import com.weimi.wmmess.business.defectRegister.presenter.DefectRegisterPresenter;
import com.weimi.wmmess.business.defectRegister.viewInterface.IChooseDefectView;
import com.weimi.wmmess.widget.emptyView.MaskView;

import java.util.ArrayList;
import java.util.List;

public class ChooseDefectActivity extends ExpandableListActivity implements IChooseDefectView {
    public static final String DEFECT_REGISTER = "defect_register";
    private MaskView maskView;
    private PullToRefreshExpandableListView elvDefectList;
    private DefectRegisterPresenter presenter;
    private DefectTreeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_defect);
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
        elvDefectList = findViewById(R.id.elvDefectList);
        TextView tvToolTitle = findViewById(R.id.tvToolTitle);
        tvToolTitle.setText("选择设备");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void initData() {
        presenter = new DefectRegisterPresenter(this);
        adapter = new DefectTreeAdapter(this, new ArrayList<>());
        setListAdapter(adapter);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        elvDefectList.getRefreshableView().setIndicatorBounds(width - 160, width - 40);
        presenter.loadDefectList();

        elvDefectList.getRefreshableView().setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Intent intent = new Intent();
            DefectChildResbean childResbean = (DefectChildResbean) adapter.getChild(groupPosition, childPosition);
            intent.putExtra(DEFECT_REGISTER, JSON.toJSONString(childResbean));
            setResult(RESULT_OK, intent);
            finish();
            return false;
        });
    }

    @Override
    public void onLoadDefectTreeSuccess(List<DefectParentResbean> list) {
        if (list == null || list.size() == 0) {
            maskView.setVisibility(View.VISIBLE);
            elvDefectList.setVisibility(View.GONE);
            maskView.show();
        } else {
            maskView.setVisibility(View.GONE);
            elvDefectList.setVisibility(View.VISIBLE);
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
