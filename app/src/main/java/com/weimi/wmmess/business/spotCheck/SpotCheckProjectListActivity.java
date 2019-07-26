package com.weimi.wmmess.business.spotCheck;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;

/**
 * 点检项目列表
 */
public class SpotCheckProjectListActivity extends WMActivity<SpotCheckPresenter> {

    private SpotCheckPresenter presenter;
    private RecyclerView rcvSpotCheckList;

    @Override
    public int initLayout() {
        return R.layout.activity_spot_check;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        rcvSpotCheckList = findViewById(R.id.rcvSpotCheckList);

    }

    @Override
    public void initData() {
        presenter = new SpotCheckPresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvSpotCheckList.setLayoutManager(layoutManager);

        presenter.getSpotCheckProjectList();
    }
}
