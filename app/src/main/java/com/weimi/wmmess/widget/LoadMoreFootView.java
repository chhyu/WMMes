package com.weimi.wmmess.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.weimi.wmmess.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

/**
 * Create by chhyu
 * On 2018/12/28
 * Class describe:
 */
public class LoadMoreFootView extends LinearLayout implements SwipeMenuRecyclerView.LoadMoreView, View.OnClickListener {


    private ProgressBar progressBar;
    private TextView tvMessage;

    public LoadMoreFootView(Context context) {
        this(context, 0);
    }

    public LoadMoreFootView(Context context, int paddingBottom) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        setGravity(Gravity.CENTER);
        setVisibility(GONE);
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        int minHeight = (int) (displayMetrics.density * 60 + 0.5);
//        setMinimumHeight(minHeight);
        setPadding(0, 0, 0, paddingBottom);

        inflate(context, R.layout.default_load_more, this);
        progressBar = findViewById(R.id.progressbar);
        tvMessage = findViewById(R.id.tvMessage);


        setOnClickListener(this);
    }

    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;

    @Override
    public void onClick(View v) {
        if (mLoadMoreListener != null) mLoadMoreListener.onLoadMore();
    }

    /**
     * 马上开始回调加载更多了，这里应该显示进度条。
     */
    @Override
    public void onLoading() {
        setVisibility(VISIBLE);
        progressBar.setVisibility(VISIBLE);
        tvMessage.setVisibility(VISIBLE);
        tvMessage.setText("正在加载，请稍后");
    }

    /**
     * 加载更多完成了。
     *
     * @param dataEmpty 是否请求到空数据。
     * @param hasMore   是否还有更多数据等待请求。
     */
    @Override
    public void onLoadFinish(boolean dataEmpty, boolean hasMore) {
        if (!hasMore) {
            setVisibility(VISIBLE);
            if (dataEmpty) {
                progressBar.setVisibility(GONE);
                tvMessage.setVisibility(VISIBLE);
                tvMessage.setText("暂无数据");
            } else {
//                progressBar.setVisibility(GONE);
//                //没有数据时隐藏消息提示
//                tvMessage.setVisibility(GONE);
//                tvMessage.setText("no data");
                progressBar.setVisibility(GONE);
                tvMessage.setVisibility(GONE);
                this.setVisibility(GONE);
            }
        } else {
            progressBar.setVisibility(VISIBLE);
            tvMessage.setVisibility(VISIBLE);
            setVisibility(INVISIBLE);
        }
    }

    @Override
    public void onWaitToLoadMore(SwipeMenuRecyclerView.LoadMoreListener loadMoreListener) {
        this.mLoadMoreListener = loadMoreListener;

        setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        tvMessage.setVisibility(VISIBLE);
        tvMessage.setText("点我加载更多");
    }

    @Override
    public void onLoadError(int errorCode, String errorMessage) {
        setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        tvMessage.setVisibility(VISIBLE);
        tvMessage.setText(errorMessage);
    }
}
