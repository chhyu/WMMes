package com.weimi.wmmess.business.sip;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.weimi.wmmess.R;

import org.linphone.core.Call;
import org.linphone.core.TransportType;


public class SipCallActivity extends AppCompatActivity {

    public static final String RECEIVE_MAIN_ACTIVITY = "receive_main_activity";
    private TextView id_text_status, id_text_code, id_call_stateinfo, id_text_version;
    private EditText id_etext_regtext, id_etext_dail, id_etext_regpwd, id_etext_ipport, id_etext_type;
    private MainActivityReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sip_call);
        id_etext_regtext = findViewById(R.id.id_etext_regtext);
        id_etext_dail = findViewById(R.id.id_etext_dail);
        id_etext_regpwd = findViewById(R.id.id_etext_regpwd);
        id_text_status = findViewById(R.id.id_text_status);
        id_call_stateinfo = findViewById(R.id.id_call_stateinfo);
        id_etext_ipport = findViewById(R.id.id_etext_ipport);
        id_text_version = findViewById(R.id.id_text_version);
        id_text_code = findViewById(R.id.id_text_code);
        id_etext_type = findViewById(R.id.id_etext_type);
        //权限
        if (PermissionsUtil.hasPermission(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA})) {
            //有
            Intent intentOne = new Intent(this, LinphoneMiniManager.class);
            startService(intentOne);
        } else {
            PermissionsUtil.requestPermission(this, new PermissionListener() {

                public void permissionGranted(@NonNull String[] permissions) {
                    //用户授予了
                    Intent intentOne = new Intent(SipCallActivity.this, LinphoneMiniManager.class);
                    startService(intentOne);
                }

                public void permissionDenied(@NonNull String[] permissions) {
                    //用户拒绝了访问摄像头的申请
                    Toast.makeText(SipCallActivity.this, "您没有授权将无法启用网络电话!", Toast.LENGTH_LONG).show();
                }
            }, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA});
        }

        //广播
        IntentFilter intentFilter = new IntentFilter(RECEIVE_MAIN_ACTIVITY);
        mReceiver = new MainActivityReceiver();
        registerReceiver(mReceiver, intentFilter);
    }

    public void register(View view) {
        if (!LinphoneMiniManager.isReady()) {
            Toast.makeText(SipCallActivity.this, "Service没准备好", Toast.LENGTH_SHORT).show();
            return;
        }
        LinphoneMiniManager instance = LinphoneMiniManager.getInstance();
        String[] split = id_etext_ipport.getText().toString().split(":");

        String host = split[0], port = split[1];

        try {
            String password = id_etext_regpwd.getText().toString();
            String type = id_etext_type.getText().toString();
            TransportType transport = TransportType.Udp;
            if (type.indexOf("tcp") != -1) {
                transport = TransportType.Tcp;
                Toast.makeText(SipCallActivity.this, "tcp注册", Toast.LENGTH_SHORT).show();
            } else if (type.indexOf("tls") != -1) {
                transport = TransportType.Tls;
                Toast.makeText(SipCallActivity.this, "tls注册", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SipCallActivity.this, "udp注册", Toast.LENGTH_SHORT).show();
            }
            instance.lilin_reg(host, id_etext_regtext.getText().toString(), password, port, transport);
            // instance.lilin_reg("192.168.10.146","1004","12345","5090");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void boda(View view) {
        String[] split = id_etext_ipport.getText().toString().split(":");
        String host = split[0], port = split[1];
        Log.e("ddd", "d");
        if (!LinphoneMiniManager.isReady()) {
            Toast.makeText(SipCallActivity.this, "Service没准备好", Toast.LENGTH_SHORT).show();
            return;
        }
        LinphoneMiniManager instance = LinphoneMiniManager.getInstance();
        instance.lilin_call(id_etext_dail.getText().toString(), id_etext_ipport.getText().toString(), false);

    }

    public void guaduan(View view) {
        if (!LinphoneMiniManager.isReady()) {
            Toast.makeText(SipCallActivity.this, "Service没准备好", Toast.LENGTH_SHORT).show();
            return;
        }
        LinphoneMiniManager instance = LinphoneMiniManager.getInstance();
        instance.hangUp();
    }

    public void jie(View view) {
        if (!LinphoneMiniManager.isReady()) {
            Toast.makeText(SipCallActivity.this, "Service没准备好", Toast.LENGTH_SHORT).show();
            return;
        }
        LinphoneMiniManager instance = LinphoneMiniManager.getInstance();
        instance.lilin_jie();
        Call ccall = instance.getLC().getCurrentCall();
        if (ccall != null && ccall.getRemoteParams().videoEnabled()) {
            startActivity(new Intent(SipCallActivity.this, VideoActivity.class));
        }
    }

    public void videoda(View view) {
        // Toast.makeText(SipCallActivity.this,"无视频",Toast.LENGTH_SHORT    ).show();

        if (!LinphoneMiniManager.isReady()) {
            Toast.makeText(SipCallActivity.this, "Service没准备好", Toast.LENGTH_SHORT).show();
            return;
        }
        LinphoneMiniManager instance = LinphoneMiniManager.getInstance();
        instance.lilin_call(id_etext_dail.getText().toString(), id_etext_ipport.getText().toString(), true);

        startActivity(new Intent(SipCallActivity.this, VideoActivity.class));

    }

    public void kuoyin(View view) {
        if (!LinphoneMiniManager.isReady()) {
            Toast.makeText(SipCallActivity.this, "Service没准备好", Toast.LENGTH_SHORT).show();
            return;
        }
        LinphoneMiniManager instance = LinphoneMiniManager.getInstance();
        instance.lilin_enkuo();

    }

    public void senddtmf(View view) {

        LinphoneMiniManager instance = LinphoneMiniManager.getInstance();
        instance.getLC().setUseInfoForDtmf(true);
        Call currentCall = instance.getLC().getCurrentCall();
        if (currentCall != null) {
            char a = 2;
            currentCall.sendDtmf(a);
            Toast.makeText(SipCallActivity.this, "senddtmf", Toast.LENGTH_SHORT).show();
        }
    }

    public class MainActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getStringExtra("action");
            switch (action) {
                case "reg_state":
                    id_text_status.setText(intent.getStringExtra("data"));
                    break;
                case "show_code":
                    id_text_code.setText(intent.getStringExtra("data"));
                    break;
                case "show_callinfo":
                    id_call_stateinfo.setText(intent.getStringExtra("data"));
                    break;
                case "show_version":
                    id_text_version.setText(intent.getStringExtra("data"));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }
}
