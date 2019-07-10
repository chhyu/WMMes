package com.weimi.wmmess.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.weimi.wmmess.MainActivity;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.business.shimu.activity.ProblemRecordActivity;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.activity.ShiMuHistoryActivity;

public class SplashActivity extends AppCompatActivity {


    private TextView tvSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
    }

    private void initView() {
        tvSplash = findViewById(R.id.tvSplash);
    }


    public void initData() {
        doAnimation();
    }

    private void doAnimation() {
        AnimationSet set = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 10f, 0, 10f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setRepeatCount(0);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setRepeatCount(0);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);

        tvSplash.startAnimation(set);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
