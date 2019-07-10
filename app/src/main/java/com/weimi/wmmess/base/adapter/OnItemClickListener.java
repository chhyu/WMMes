package com.weimi.wmmess.base.adapter;

import android.view.View;

/**
 * Created by Jason on 2018/9/28.
 */
public interface OnItemClickListener {
    void onItemClick(View view, ViewHolder holder, int position);

    boolean onItemLongClick(View view, ViewHolder holder, int position);
}