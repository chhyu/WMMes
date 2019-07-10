package com.weimi.wmmess.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.weimi.wmmess.R;
import com.weimi.wmmess.constants.ConstantValues;
import com.weimi.wmmess.widget.DividerItemDecoration;
import com.weimi.wmmess.widget.emptyView.MaskView;


/**
 * Created by Jason on 2018/8/9.
 */
public class ConfigUtils {
    /**
     * 获取上次提示更新时间
     *
     * @return
     */
    public static long getLastVersionCheckedTime() {
        return SPUtils.getInstance().getLong(ConstantValues.SF_VERSION);
    }

    /**
     * 设置上次提示更新时间
     *
     * @param time
     */
    public static void setLastVersionCheckedTime(long time) {
        SPUtils.getInstance().put(ConstantValues.SF_VERSION, time);
    }

    /**
     * 四小时之内不再提示更新
     *
     * @return
     */
    public static boolean canUpdate() {
        boolean flag = getLastVersionCheckedTime() <= 0 ||
                System.currentTimeMillis() > getLastVersionCheckedTime() + ConstantValues.UPDATE_NO_DISTURB_DURATION;
        return flag;
    }


    public static void initWebView(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setSupportMultipleWindows(false);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

//        settings.setJavaScriptEnabled(true);//启用javascript支持
//        settings.setLoadsImagesAutomatically(true); // 支持自动加载图片
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 支持内容重新布局
//        settings.setAllowFileAccessFromFileURLs(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setSupportZoom(true);
//        settings.setDomStorageEnabled(true);
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        settings.setAllowFileAccess(true);// 设置允许访问文件数据
//        settings.setUseWideViewPort(true);
//        settings.setSupportMultipleWindows(true);
//        settings.setBlockNetworkImage(false);//同步请求图片
//        mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
//        mWebView.setVerticalScrollBarEnabled(true); //垂直不显示
//        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条风格
//        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null); // 关闭硬件加速，防止内容加载失败
//        mWebView.loadData("", "text/html", "UTF-8");
    }

    /**
     * 配置recycleview
     *
     * @param recyclerView
     */
    public static void configRecycleView(final RecyclerView recyclerView) {
        configRecycleView(recyclerView, true, true);
    }

    public static void configRecycleView(final RecyclerView recyclerView, boolean fixedHeight, boolean animator) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Utils.getApp());
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (fixedHeight) {
            //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            recyclerView.setHasFixedSize(true);
        }
        if (animator) {
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        } else {
            recyclerView.setItemAnimator(null);
        }

        //添加Android自带的分割线
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration.Builder().orientation(LinearLayoutManager.VERTICAL)
                        .dividerHeight(SizeUtils.dp2px(0.5f))
                        .dividerLeftMargin(SizeUtils.dp2px(16))
                        .dividerColor(R.color.divider)
                        .build();
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    public static void configRecycleView(final RecyclerView recyclerView, DividerItemDecoration dividerItemDecoration) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Utils.getApp());
//        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //添加Android自带的分割线
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    /**
     * 添加无数据的提示信息
     *
     * @param context
     * @param viewGroup
     */
    public static void emptyData(Context context, ViewGroup viewGroup) {
//        TextView textView = new TextView(context);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        textView.setLayoutParams(lp);
//        textView.setGravity(Gravity.CENTER_HORIZONTAL);
//        textView.setPadding(0, SizeUtils.dp2px(15), 0, 0);
//        textView.setText(context.getString(R.string.comm_empty_data));
//        viewGroup.addView(textView, 0);
        MaskView maskView = new MaskView(context);
        maskView.showEmptyDateView();
        viewGroup.addView(maskView);
    }

    /**
     * 添加无数据的提示信息
     *
     * @param context
     * @param viewGroup
     * @param strId
     */
    public static void emptyData(Context context, ViewGroup viewGroup, @StringRes int strId) {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setPadding(0, SizeUtils.dp2px(15), 0, 0);
        textView.setText(context.getString(strId));
        viewGroup.addView(textView, 0);
    }

    private static String ticket;

    public static String getTicket() {
        return ticket;
    }

    public static void setTicket(String ticket) {
        ConfigUtils.ticket = ticket;
    }
}
