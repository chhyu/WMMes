package com.weimi.wmmess.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView通用Adapter，且加入了左滑功能
 * 参考文章 http://blog.csdn.net/lmj623565791/article/details/51118836/
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<ViewHolder> implements OnItemClickListener, OnLoadMoreListener {

    //region validate
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected OnItemClickListener mOnItemClickListener;
    protected OnLoadMoreListener mOnLoadMoreListener;
    public SubClickListener subClickListener;
    private long lastClick = 0;

    public void setsubClickListener(SubClickListener topicClickListener) {
        this.subClickListener = topicClickListener;
    }

    public interface SubClickListener {
        void OntopicClickListener(View v, int position);
    }
    //endregion

    public BaseAdapter(Context context, int layoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = new ArrayList<T>();
        setOnItemClickListener(this);
        setOnLoadMoreListener(this);
    }

    public BaseAdapter(Context context, int layoutId, List<T> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
        setOnItemClickListener(this);
        setOnLoadMoreListener(this);
    }

    //region extends RecyclerView.Adapter
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.createViewHolder(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDatas.get(position), position);
        setListener(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    protected void setListener(final ViewHolder viewHolder, final int position) {
        View itemView;
        itemView = viewHolder.getConvertView();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    long now = System.currentTimeMillis();
                    if (now - lastClick >= 200) {
                        lastClick = now;
                        mOnItemClickListener.onItemClick(v, viewHolder, position);
                    }
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return mOnItemClickListener != null && mOnItemClickListener.onItemLongClick(v, viewHolder, position);
            }
        });

    }
    //endregion

    //region Public method
    public abstract void convert(ViewHolder holder, T t, int positon);

    public void setLastClick(long lastClick) {
        this.lastClick = lastClick;
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }

    public void addAll(List<T> list) {
        if (list != null) {
            mDatas.addAll(list);
        }
    }

    public void clearItems() {
        int itemCount = getItemCount();
        mDatas.clear();
//        notifyItemRangeRemoved(0, itemCount);
//        notifyItemRangeChanged(0, itemCount);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
        if (position != mDatas.size()) {
            notifyItemRangeChanged(position, mDatas.size() - position);
        }
    }

    public void insertItem(int position, T t) {
        mDatas.add(position, t);
        notifyItemInserted(position);
    }

    public void updateItem(int position, T t) {
        if (mDatas != null) {
            mDatas.set(position, t);
        }
        notifyItemChanged(position, "updateItem");
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setDatas(List<T> t) {
        this.mDatas = t;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener != null) {
            this.mOnItemClickListener = onItemClickListener;
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
    }
    //endregion
}
