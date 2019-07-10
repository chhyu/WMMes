package com.weimi.wmmess.widget.emptyView;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weimi.wmmess.R;

/**
 * <p><b>Classname</b> MaskView
 * <p><b>Description</b>: 用于空数据视图、请求失败等
 * <p> Create by chhyu on 2018/8/10
 */
public class MaskView extends FrameLayout implements IDisplayMask {

    private static final String TAG = "MaskView";
    private View contentView;

    private ImageView mHintPic;

    private TextView mHintText;

    private TextView mOperateText;

    private final Animation fadeInAnim;

    private final Animation fadeOutAnim;

    static final int ANIMATION_DURATION = 150;

    public MaskView(Context context) {
        super(context);
        fadeInAnim = new AlphaAnimation(0.3f, 1f);
        fadeOutAnim = new AlphaAnimation(1f, 0f);
        init();
    }

    public MaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        fadeInAnim = new AlphaAnimation(0.3f, 1f);
        fadeOutAnim = new AlphaAnimation(1f, 0f);
        init();
    }

    public MaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        fadeInAnim = new AlphaAnimation(0.3f, 1f);
        fadeOutAnim = new AlphaAnimation(1f, 0f);
        init();
    }

    private void init() {
        contentView = inflate(getContext(), R.layout.view_mask, this);

        mHintPic = (ImageView) contentView.findViewById(R.id.mask_icon);
        mHintText = (TextView) contentView.findViewById(R.id.mask_hint);
        mOperateText = (TextView) contentView.findViewById(R.id.mask_op);

        this.fadeInAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.fadeInAnim.setDuration(ANIMATION_DURATION);
        this.fadeInAnim.setFillAfter(false);

        this.fadeOutAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.fadeOutAnim.setDuration(ANIMATION_DURATION);
        this.fadeOutAnim.setFillAfter(false);

        fadeInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(VISIBLE);
                bringToFront();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void setIcon(@DrawableRes int resId) {
        mHintPic.setImageResource(resId);
    }

    public void setText(@StringRes int resId) {
        mHintText.setText(resId);
    }

    public void setText(String text) {
        mHintText.setText(text);
    }

//    @Deprecated
//    private void showAsUnlogin(OnClickListener listener) {
//        setVisibility(VISIBLE);
//        bringToFront();
////        mImageView.setImageResource(R.drawable.v1011_drawable_mask_unlogin);
////        v1010_view_mask_text_unlogin 您还未登录，去登录
//        String s = getResources().getString(R.string.v1010_view_mask_text_unlogin);
//        SpannableStringBuilder builder = new SpannableStringBuilder(s);
//        builder.setSpan(newClickSpan(listener), 6, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        int color = Color.argb(255, 45, 126, 226);
//        builder.setSpan(new ForegroundColorSpan(color), 6, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        builder.setSpan(new UnderlineSpan(), 6, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        mTextView.setText(builder);
//        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
//    }
//
//    private ClickableSpan newClickSpan(final OnClickListener listener) {
//        return new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                if (listener != null)
//                    listener.onClick(widget);
//            }
//        };
//    }

    public void show() {
        if (getAnimation() == fadeInAnim && !getAnimation().hasEnded()) {
            //on shown return
            return;
        }

        if (getVisibility() == VISIBLE) {
            setAlpha(1f);
            bringToFront();
            return;
        }

        try {
            contentView.setOnClickListener(null);
            if (getAnimation() != null)
                getAnimation().cancel();
            startAnimation(fadeInAnim);
            bringToFront();
        } catch (Exception e) {
            setVisibility(VISIBLE);
            e.printStackTrace();
        }
    }

    @Override
    public void showLoadingMask() {
        try {
            show();
            mHintPic.setVisibility(VISIBLE);
            Glide.with(getContext()).asGif().load(R.drawable.comm_icon_load_error).into(mHintPic);
            mHintText.setText("加载中...");
            mOperateText.setText("");
        } catch (Exception e) {
            Log.e(TAG, " exception," + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void showPoolNetMask() {
        try {
            show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showEmptyDateView() {
        try {
            show();
//            Glide.with(getContext()).asGif().load(R.drawable.temp_loading_1).into(mHintPic);
            mHintPic.setVisibility(GONE);
            mHintText.setText("暂无数据");
            mOperateText.setText("");
        } catch (Exception e) {
            Log.e(TAG, " exception," + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void showEmptyDateView(String emptyText) {
        try {
            show();
            mHintPic.setVisibility(GONE);
            mHintText.setText(emptyText);
            mOperateText.setText("");
        } catch (Exception e) {
            Log.e(TAG, " exception," + e.getMessage());
            e.printStackTrace();
        }
    }

    public void showEmptyDateView(@DrawableRes int resId, String emptyText) {
        try {
            show();
            mHintPic.setVisibility(VISIBLE);
            mHintPic.setImageResource(resId);
            mHintText.setText(emptyText);
            mOperateText.setText("");
        } catch (Exception e) {
            Log.e(TAG, " exception," + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void showLoadFailMask(final ICallback onRetry) {
        try {
            show();
            contentView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRetry.onCallback();
                }
            });
            mHintPic.setVisibility(VISIBLE);
            Glide.with(getContext()).asBitmap().load(R.drawable.common_drawable_load_failure).into(mHintPic);
            mHintText.setText("加载失败");
            mOperateText.setText("点击重新加载");
        } catch (Exception e) {
            Log.e(TAG, " exception," + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void showUnLoginMask(CharSequence loginAccessHint) {
        try {
            show();
            mHintPic.setVisibility(VISIBLE);
            Glide.with(getContext().getApplicationContext()).asBitmap().load(R.drawable.comm_icon_load_error).into(mHintPic);
            mHintText.setText("未登录");
            mOperateText.setText("立即登录");
        } catch (Exception e) {
            Log.e(TAG, " exception," + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (getVisibility() == GONE) {
            Log.w(TAG, "not shown,ignore close request");
            return;
        }
        try {
            if (getAnimation() != null)
                getAnimation().cancel();
            startAnimation(fadeOutAnim);
        } catch (Exception e) {
            Log.e(TAG, " exception," + e.getMessage());
            setVisibility(GONE);
            e.printStackTrace();
        }
    }


}

/**
 *
 */
interface IDisplayMask {
    /**
     * loading中
     */
    void showLoadingMask();

    /**
     * 无网,先统一走加载失败
     */
    void showPoolNetMask();

    /**
     * 空视图
     */
    void showEmptyDateView();

    /**
     * 空视图
     */
    void showEmptyDateView(String emptyText);

    /**
     * 加载失败
     */
    void showLoadFailMask(ICallback onRetryCallback);

    /**
     * 未登录，原生内容部分，使用概率小
     *
     * @param loginAccessHint 引导登录入口
     */
    void showUnLoginMask(CharSequence loginAccessHint);

    /**
     * 关闭
     */
    void close();
}
