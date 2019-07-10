package com.weimi.wmmess.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import com.blankj.utilcode.util.SizeUtils;
import com.weimi.wmmess.R;

public class ClearEditText extends android.support.v7.widget.AppCompatEditText implements
        OnFocusChangeListener, TextWatcher {
    private Context mContext;
    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFoucs;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        //这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }


    private void init() {
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
//        	throw new NullPointerException("You can add drawableRight attribute in XML");
            mClearDrawable = ContextCompat.getDrawable(this.getContext(), R.drawable.selector_edittext_clear);
        }

        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        //默认设置隐藏图标
        setClearIconVisible(false);
        //设置焦点改变的监听
        setOnFocusChangeListener(this);
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this);

        setBorderColor(normalColorId);
    }


    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {

                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
            if (pressedColorId != 0) {
                changeColor(pressedColorId);    // 改变图标颜色
                setBorderColor(pressedColorId); // 改变边框颜色
            }
        } else {
            setClearIconVisible(false);
            if (normalColorId != 0) {
                changeColor(normalColorId);
                setBorderColor(normalColorId);
            }
        }
    }

    //region 自定义
    private int normalColorId = R.color.iconGrey;
    private int pressedColorId = R.color.theme;

    //初始化获取焦点和失去焦点的颜色
    public void setColor(int normalColorId, int pressedColorId) {
        this.normalColorId = normalColorId;
        this.pressedColorId = pressedColorId;
        setBorderColor(normalColorId);
    }

    /**
     * 改变Textview的DrawableLeft图标颜色
     *
     * @param colorId
     */
    private void changeColor(int colorId) {
        Drawable drawable = this.getCompoundDrawables()[0]; // 获取TextView左侧图标
        if (drawable != null) {
            drawable.setColorFilter(ContextCompat.getColor(mContext, colorId), PorterDuff.Mode.MULTIPLY);
        }
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = null;
        if (visible) {
            right = visible ? mClearDrawable : null;
            right.setBounds(0, 0, SizeUtils.dp2px(15), SizeUtils.dp2px(15));
        }
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

//    private int sroke_width = 1;

    @Override
    protected void onDraw(Canvas canvas) {
        if (borderCol != 0) {
            Paint paint = new Paint();
            //  将边框设为黑色
            paint.setColor(ContextCompat.getColor(mContext, borderCol)); //android.graphics.Color.BLACK;
//            int sroke_width = SizeUtils.dp2px(2);
            //  画TextView的4个边
//        canvas.drawLine(0, 0, this.getWidth() - sroke_width, 0, paint);
//        canvas.drawLine(0, 0, 0, this.getHeight() - sroke_width, paint);
//        canvas.drawLine(this.getWidth() - sroke_width, 0, this.getWidth() - sroke_width, this.getHeight() - sroke_width, paint);
//            canvas.drawLine(0, this.getHeight() - sroke_width, this.getWidth() - sroke_width, this.getHeight() - sroke_width, paint);
        }
        super.onDraw(canvas);
    }

    private int borderCol = 0; //边框颜色

    /**
     * 设置边框颜色
     *
     * @param newColor
     */
    public void setBorderColor(int newColor) {
        borderCol = newColor;
        invalidate();
        requestLayout();
    }
    //endregion

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count,
                              int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {


    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    /**
     * 设置晃动动画
     */
    public void setShakeAnimation() {
        this.setAnimation(shakeAnimation(5));
    }

    /**
     * 晃动动画
     *
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }


}
