package com.weimi.wmmess.base.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.weimi.wmmess.R;

import java.util.List;

/**
 * Created by Jason on 2017/8/23.
 */

public abstract class CommAdapter<T> extends BaseAdapter<T> {

    private static final int ITEM_TYPE_FOOTER = Integer.MAX_VALUE - 2;

    private RelativeLayout mFooterLayout;   //底部父级布局

    private View mLoadMoreView;     //加载更多视图
    private View mLoadFailedView;   //加载失败视图
    private View mLoadEndView;      //加载结束视图
    private boolean isOpenLoadMore = false; //是否开启加载更多

    public CommAdapter(Context context, int layoutId) {
        super(context, layoutId);
        init();
    }

    public CommAdapter(Context context, int layoutId, List<T> data) {
        super(context, layoutId, data);
        init();
    }

    private void init() {
        setOnItemClickListener(this);
        setOnLoadMoreListener(this);
        mLoadMoreView = LayoutInflater.from(mContext).inflate(R.layout.default_load_more, null);
        mLoadFailedView = LayoutInflater.from(mContext).inflate(R.layout.default_load_failed, null);
        mLoadEndView = LayoutInflater.from(mContext).inflate(R.layout.default_load_end, null);
    }

    @Override
    public void convert(ViewHolder holder, T t, int position) {

    }

    @Override
    public void onItemClick(View view, ViewHolder holder, int position) {

    }

    @Override
    public boolean onItemLongClick(View view, ViewHolder holder, int position) {
        return true;
    }

    @Override
    public void onLoadMore() {

    }

    //region Load More

    @Override
    public int getItemCount() {
        return mDatas.size() + (isOpenLoadMore ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooterView(position)) {
            return ITEM_TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_FOOTER) {
            if (mFooterLayout == null) {
                mFooterLayout = new RelativeLayout(mContext);
            }
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFooterLayout);
            return holder;
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mOnLoadMoreListener != null && isFooterView(position)) {
            mOnLoadMoreListener.onLoadMore();
            return;
        }
        super.onBindViewHolder(holder, position);
    }

    // TODO: 2019/1/14 有问题，当全部数据一屛就可以显示下也会走加载更多
    private boolean isFooterView(int position) {
//        return mDatas != null && mDatas.size() > 0 && position >= mDatas.size() - 1;
        return false;
    }

    private void addFooterView(View footerView) {
        if (footerView == null) {
            return;
        }

        if (mFooterLayout == null) {
            mFooterLayout = new RelativeLayout(mContext);
        }
        removeFooterView();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mFooterLayout.addView(footerView, params);
    }

    private void removeFooterView() {
        mFooterLayout.removeAllViews();
    }

    public boolean isOpenLoadMore() {
        return isOpenLoadMore;
    }

    public CommAdapter setOpenLoadMore(boolean openLoadMore) {
        isOpenLoadMore = openLoadMore;
        return this;
    }

    public CommAdapter setLoadMoreView(View loadMoreView) {
        mLoadMoreView = loadMoreView;
        return this;
    }

    public CommAdapter setLoadMoreView(@LayoutRes int layoutId) {
        mLoadMoreView = LayoutInflater.from(mContext).inflate(layoutId, null);
        return this;
    }

    public CommAdapter setLoadFailedView(View loadFailedView) {
        mLoadFailedView = loadFailedView;
        return this;
    }

    public CommAdapter setLoadFailedView(int loadFailedId) {
        mLoadFailedView = LayoutInflater.from(mContext).inflate(loadFailedId, null);
        return this;
    }

    /**
     * 加载更多监听，设置了监听才会添加加载更多视图
     *
     * @param loadMoreListener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            super.setOnLoadMoreListener(loadMoreListener);
            addFooterView(mLoadMoreView);
        }
    }

    /**
     * 加载失败，重新加入加载更多视图
     */
    public void loadFailed() {
        addFooterView(mLoadFailedView);
        mLoadFailedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFooterView(mLoadMoreView);
                if (mOnLoadMoreListener != null) {
                    mOnLoadMoreListener.onLoadMore();
                }
            }
        });
    }

    /**
     * 加载完成
     */
    public void loadEnd() {
        if (mLoadEndView != null) {
            addFooterView(mLoadEndView);
        } else {
            clearLoadView();
        }
    }

    /**
     * 清除底部视图
     */
    public void clearLoadView() {
        addFooterView(new View(mContext));
    }

    /**
     * 加载更多的数据
     *
     * @param list
     */
    public void setLoadMoreData(List<T> list) {
        int oldPosition = mDatas.size();
        mDatas.addAll(oldPosition, list);
        notifyItemRangeInserted(oldPosition, list.size());
        notifyItemRangeChanged(oldPosition, list.size());
    }
    //endregion
}
