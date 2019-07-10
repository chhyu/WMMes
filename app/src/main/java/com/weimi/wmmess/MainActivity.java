package com.weimi.wmmess;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.fragment.ApplicationCenterFragment;
import com.weimi.wmmess.fragment.ContactsFragment;
import com.weimi.wmmess.fragment.MessageFragment;
import com.weimi.wmmess.fragment.MineFragment;
import com.weimi.wmmess.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends WMActivity<MainPresenter> {

    private FrameLayout flContainer;
    private BottomNavigationView bnvBottomNavigation;

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        getToolbar().setNavigationIcon(null);
        flContainer = findViewById(R.id.flContainer);
        bnvBottomNavigation = findViewById(R.id.bnv_bottom_navigation);
    }

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void initData() {
        setTitle("message");
        fragmentList.add(MessageFragment.newInstance());
        fragmentList.add(ApplicationCenterFragment.newInstance());
        fragmentList.add(ContactsFragment.newInstance());
        fragmentList.add(MineFragment.newInstance());
        bnvBottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        switchFragment(R.id.flContainer, fragmentList.get(0));
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.item_message) {
                setTitle("message");
                switchFragment(R.id.flContainer, fragmentList.get(0));
                return true;
            } else if (item.getItemId() == R.id.item_work) {
                setTitle("应用中心");
                switchFragment(R.id.flContainer, fragmentList.get(1));
                return true;
            } else if (item.getItemId() == R.id.item_contact) {
                setTitle("联系人");
                switchFragment(R.id.flContainer, fragmentList.get(2));
                return true;
            } else if (item.getItemId() == R.id.item_mine) {
                setTitle("个人中心");
                switchFragment(R.id.flContainer, fragmentList.get(3));
                return true;
            } else {
                return false;
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("android:support:fragments", null);
    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
