package com.weimi.wmmess.business.sip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import com.weimi.wmmess.R;


public class VideoActivity extends AppCompatActivity {


    private TextureView mRenderingView,mPreviewView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        init();

        LinphoneMiniManager.getInstance().getLC(). setNativeVideoWindowId(mRenderingView);
        //远方的
        LinphoneMiniManager.getInstance().getLC().setNativePreviewWindowId(mPreviewView);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPreviewView = null;
        mRenderingView = null;



    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void init() {
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinphoneMiniManager instance = LinphoneMiniManager.getInstance();
                switch (v.getId()) {
                    case R.id.id_video_gua:
                        instance.hangUp();
                        finish();
                        break;
                    case R.id.id_video_mute:

                        break;
                    case R.id.id_video_speaker:

                        break;
                    case R.id.id_video_qiev:

                        break;
                    default:
                        break;
                }
            }
        };
        ((Button) findViewById(R.id.id_video_gua)).setOnClickListener(listener);
        ((Button) findViewById(R.id.id_video_mute)).setOnClickListener(listener);
        ((Button) findViewById(R.id.id_video_speaker)).setOnClickListener(listener);
        ((Button) findViewById(R.id.id_video_qiev)).setOnClickListener(listener);
        mRenderingView = findViewById(R.id.id_video_rendering);
        mPreviewView =  findViewById(R.id.id_video_preview);
    }

}
