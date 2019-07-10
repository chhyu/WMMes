package com.weimi.wmmess.widget;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;

import com.weimi.wmmess.R;


/**
 * @author chhyu
 * @date on 2018/9/19 0019$
 * @describe 类描述
 */
public class LoginButton extends View {

    private int progerssButtonDuration = 200;
    private int rotateAnimationDuration = 400;


    private Paint paintRectF;
    private Paint paintText;

    private Paint paintPro;

    private int mStrokeWidth = 0;
    private int mPadding = 0;

    private float mSpac = 0;
    private float mRadius = 0;

    private int mProRadius = 0;

    private float startAngle = 0f;

    private ProgerssButtonAnim mProgerssButtonAnim;

    private RotateAnimation mProgerssRotateAnim;


    private String text = "";

    private int bgColor = Color.BLUE;

    public void setBgColor(int color) {
        this.bgColor = color;
    }

    private int textColor = Color.WHITE;

    public void setTextColor(int color) {
        this.textColor = color;
    }

    private int proColor = Color.WHITE;

    public void setProColor(int color) {
        this.proColor = color;
    }

    public void setButtonText(String s) {
        this.text = s;
        invalidate();
    }

    private boolean mStop = false;

    public LoginButton(Context context) {
        this(context, null);
    }

    public LoginButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoginButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mStrokeWidth = dip2px(2);
        mPadding = dip2px(2);
        mProRadius = getMeasuredHeight() / 5;

        mProgerssButtonAnim = new ProgerssButtonAnim();
        mProgerssRotateAnim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mProgerssRotateAnim.setRepeatCount(-1);
        mProgerssRotateAnim.setInterpolator(new LinearInterpolator());//不停顿
        mProgerssRotateAnim.setFillAfter(true);//停在最后
        paintRectF = new Paint();
        paintRectF.setAntiAlias(true);
        paintRectF.setStyle(Paint.Style.FILL);
        paintRectF.setStrokeWidth(mStrokeWidth);


        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTextSize(dip2px(15));
        paintPro = new Paint();
        paintPro.setAntiAlias(true);
        paintPro.setStyle(Paint.Style.STROKE);
        paintPro.setStrokeWidth(mStrokeWidth / 2);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintText.setColor(textColor);
        paintRectF.setColor(bgColor);
        paintPro.setColor(proColor);

        RectF mRectF = new RectF();                           //RectF对象
        mRectF.left = mPadding + mSpac;
        mRectF.top = mPadding;
        mRectF.right = getMeasuredWidth() - mPadding - mSpac;
        mRectF.bottom = getMeasuredHeight() - mPadding;
        mRadius = (getMeasuredHeight() - 2 * mPadding) / 2;

        canvas.drawRoundRect(mRectF, mRadius, mRadius, paintRectF);

        if (mRectF.width() == mRectF.height() && !mStop) {
            RectF mRectFPro = new RectF();
            mRectFPro.left = getMeasuredWidth() / 2.0f - mRectF.width() / 4;
            mRectFPro.top = getMeasuredHeight() / 2.0f - mRectF.width() / 4;
            mRectFPro.right = getMeasuredWidth() / 2.0f + mRectF.width() / 4;
            mRectFPro.bottom = getMeasuredHeight() / 2.0f + mRectF.width() / 4;
            canvas.drawArc(mRectFPro, startAngle, 100, false, paintPro);
        }

        if (mSpac < (getMeasuredWidth() - getMeasuredHeight()) / 2.0f) {
            canvas.drawText(text,
                    getMeasuredWidth() / 2.0f - getFontlength(paintText, text) / 2.0f,
                    getMeasuredHeight() / 2.0f + getFontHeight(paintText, text) / 3.0f,
                    paintText);
        }

    }

    public void startAnim() {
        setEnabled(false);
        mStop = false;
        if (mProgerssButtonAnim != null)
            clearAnimation();
        mProgerssButtonAnim.setDuration(progerssButtonDuration);
        startAnimation(mProgerssButtonAnim);
    }

    public void startProAnim() {
        if (mProgerssRotateAnim != null)
            clearAnimation();
        mProgerssRotateAnim.setDuration(rotateAnimationDuration);
        startAnimation(mProgerssRotateAnim);
    }

    public void stopAnim() {
        setEnabled(true);
        clearAnimation();
        mStop = true;
        mSpac = 0;
        invalidate();
    }


    private class ProgerssButtonAnim extends Animation {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mSpac = (getMeasuredWidth() - getMeasuredHeight()) / 2.0f * interpolatedTime;
            invalidate();
            if (interpolatedTime == 1.0f)
                startProAnim();
        }
    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public float getFontlength(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    public float getFontHeight(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    public void onLoadFailed(Context context) {
        stopAnim();
        ObjectAnimator nopeAnimator = shakeAnimation(context);
        nopeAnimator.setRepeatCount(2);
        nopeAnimator.start();
    }

    public ObjectAnimator shakeAnimation(Context context) {
        int delta = context.getResources().getDimensionPixelOffset(R.dimen.dp_10);
        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.10f, -delta),
                Keyframe.ofFloat(.26f, delta),
                Keyframe.ofFloat(.42f, -delta),
                Keyframe.ofFloat(.58f, delta),
                Keyframe.ofFloat(.74f, -delta),
                Keyframe.ofFloat(.90f, delta),
                Keyframe.ofFloat(1f, 0f)
        );

        return ObjectAnimator.ofPropertyValuesHolder(this, pvhTranslateX).setDuration(200);
    }
}
