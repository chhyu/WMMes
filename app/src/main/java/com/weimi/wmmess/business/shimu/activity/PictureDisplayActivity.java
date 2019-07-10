package com.weimi.wmmess.business.shimu.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.StringUtils;
import com.github.chrisbanes.photoview.PhotoView;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;

public class PictureDisplayActivity extends WMActivity {


    private PhotoView photoView;
    public static final String PHOTO_PATH = "photo_path";

    @Override
    public int initLayout() {
        return R.layout.activity_picture_display;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        photoView = findViewById(R.id.photoView);
    }

    @Override
    public void initData() {
        String photoPath = getIntent().getStringExtra(PHOTO_PATH);
        if (StringUtils.isEmpty(photoPath)) {
            photoView.setImageResource(R.drawable.load_picture_error);
        } else {
            photoView.setImageURI(Uri.parse(photoPath));
        }
    }
}
