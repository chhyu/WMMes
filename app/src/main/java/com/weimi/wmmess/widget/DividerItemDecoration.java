package com.weimi.wmmess.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;


/**
 * Created by Jason on 2017/8/22.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;

    private int mOrientation;
    private int mDividerHeight;
    private int mDividerLeftMargin;
    @ColorRes
    private int mColor;
    private Paint dividerPaint;

    private DividerItemDecoration(Builder builder) {
        mOrientation = builder.mOrientation;
        mDividerHeight = builder.mDividerHeight;
        mDividerLeftMargin = builder.mDividerLeftMargin;
        mColor = builder.mColor;
        init();
    }

    private void init() {
        if (mOrientation != HORIZONTAL && mOrientation != VERTICAL) {
            throw new IllegalArgumentException("invalid orientation");
        }

        dividerPaint = new Paint();
        dividerPaint.setColor(ContextCompat.getColor(MainApplication.getInstance(), mColor));
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft() + mDividerLeftMargin;
        int right = parent.getWidth() - parent.getPaddingRight();
        //最后一个item不画分割线
        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + mDividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (mOrientation == VERTICAL) {
            outRect.bottom = mDividerHeight;
        } else {
            outRect.right = mDividerHeight;
        }
    }

    public static final class Builder {
        private int mOrientation = LinearLayoutManager.VERTICAL;
        private int mDividerHeight = 1;
        private int mDividerLeftMargin = SizeUtils.dp2px(16);
        private int mColor = R.color.divider;

        public Builder() {
        }

        public Builder orientation(int val) {
            mOrientation = val;
            return this;
        }

        public Builder dividerHeight(int val) {
            mDividerHeight = val;
            return this;
        }

        public Builder dividerLeftMargin(int val) {
            mDividerLeftMargin = val;
            return this;
        }

        public Builder dividerColor(@ColorRes int val) {
            mColor = val;
            return this;
        }

        public DividerItemDecoration build() {
            return new DividerItemDecoration(this);
        }
    }
}