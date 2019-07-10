package com.weimi.wmmess.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.weimi.wmmess.R;
import com.weimi.wmmess.adapter.WebBannerAdapter;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.bean.AppItemBean;
import com.weimi.wmmess.business.shimu.activity.CraftConfirmActivity;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.activity.ShiMuHistoryActivity;
import com.weimi.wmmess.business.sip.SipCallActivity;
import com.weimi.wmmess.business.workOrder.WorkOrderListActivity;
import com.weimi.wmmess.presenter.MainPresenter;
import com.weimi.wmmess.widget.NoticeView;
import com.weimi.wmmess.widget.banner.BannerLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用中心
 */
public class ApplicationCenterFragment extends BaseFragment<MainPresenter> {
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;

    private BannerLayout recyclerBanner;
    private NoticeView vNotice;
    private RecyclerView rvAPP;
    //    private CommAdapter appAdapter;
    private LinearLayout llContent;
    private MainPresenter presenter;
    private CommAdapter appAdapter;

    public static ApplicationCenterFragment newInstance() {
        ApplicationCenterFragment fragment = new ApplicationCenterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_application_center;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        isPrepared = true;
        recyclerBanner = rootView.findViewById(R.id.bl_store_recycler_banner);
        rvAPP = rootView.findViewById(R.id.rvAPP);
        vNotice = rootView.findViewById(R.id.nv_store_tips);
        llContent = rootView.findViewById(R.id.llContent);
    }

    private List<AppItemBean> list = new ArrayList<>();

    @Override
    public void initData() {
        presenter = new MainPresenter(this);
        addButtons();
        initBanner();
        appAdapter = new CommAdapter<AppItemBean>(getContext(), R.layout.main_grid_item_app, list) {
            @Override
            public void convert(ViewHolder holder, AppItemBean appItemBean, int position) {
                super.convert(holder, appItemBean, position);
                holder.setImageResource(R.id.ivIcon, appItemBean.getImageId())
                        .setText(R.id.tvText, appItemBean.getText());
//                CommUtils.setBackgroundTintList(holder.getView(R.id.flBg), appItemBean.getBgColor(), SizeUtils.dp2px(8));
//                ImageView imageView = holder.getView(R.id.ivIcon);
//                Badge badgeView = new QBadgeView(getContext()).bindTarget(imageView);
//                if (appItemBean.getTarget().equals(RoutePath.MAIL_MAIN)) {
//                    badgeView.setBadgeNumber(mailUnreadCount);
//                } else if (appItemBean.getTarget().equals(RoutePath.WORK_MAIN)) {
//                    badgeView.setBadgeNumber(oaUnreadCount);
//                } else {
//                    badgeView.setBadgeNumber(0);
//                }
            }

            @Override
            public void onItemClick(View view, ViewHolder holder, int position) {
                super.onItemClick(view, holder, position);
                AppItemBean item = (AppItemBean) appAdapter.getItem(position);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(
                        view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);

                Intent intent = new Intent(getActivity(), item.getaClass());
                startActivity(intent);
            }
        };
        rvAPP.setAdapter(appAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        gridLayoutManager.setOrientation(GridLayout.VERTICAL);
        rvAPP.setLayoutManager(gridLayoutManager);

        rvAPP.setAdapter(appAdapter);
        rvAPP.setLayoutAnimation(getAnimationController());
    }

    private void addButtons() {
        list.add(new AppItemBean(R.drawable.main_app_icon_shimu, R.color.md_blue_800, "科学试模", ShiMuActivity.class));
        list.add(new AppItemBean(R.drawable.main_app_icon_sip, R.color.md_deep_orange_400, "Sip通话", SipCallActivity.class));
        list.add(new AppItemBean(R.drawable.main_app_icon_gongdan, R.color.md_light_green_800, "工单管理", WorkOrderListActivity.class));
    }

    /**
     * 初始化Banner
     */
    private void initBanner() {
        //指示器
        recyclerBanner.setShowIndicator(false);
        List<String> bannerList = new ArrayList<>();
        bannerList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3192558913,962143618&fm=26&gp=0.jpg");
        bannerList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3002381519,2832976848&fm=26&gp=0.jpg");
        bannerList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2609201346,1547814986&fm=26&gp=0.jpg");
        bannerList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3372344973,452033861&fm=26&gp=0.jpg");
        bannerList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3339521810,5385666&fm=26&gp=0.jpg");
        bannerList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1226382817,2877711195&fm=26&gp=0.jpg");
        WebBannerAdapter webBannerAdapter = new WebBannerAdapter(getContext(), bannerList);
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "点击了第  " + position + "  项", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerBanner.setAdapter(webBannerAdapter);
        recyclerBanner.setAutoPlaying(true);
    }

    /**
     * 按钮动画
     *
     * @return
     */
    private LayoutAnimationController getAnimationController() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.comm_anim_scale_overshoot);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.3f);
        return controller;
    }
}
