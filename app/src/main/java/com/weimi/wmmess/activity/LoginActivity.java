package com.weimi.wmmess.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.weimi.wmmess.MainActivity;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.presenter.LoginPresenter;
import com.weimi.wmmess.viewInterface.ILoginView;
import com.weimi.wmmess.widget.ClearEditText;
import com.weimi.wmmess.widget.LoginButton;

public class LoginActivity extends WMActivity<LoginPresenter> implements View.OnClickListener, ILoginView {

    private LoginButton btnLogin;
    private ClearEditText etUserName;
    private ClearEditText etPassword;
    private LoginPresenter presenter;
    private ImageView ivEye;
    private boolean isPasswordVisible = false;  //密码是否可见

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        ivEye = findViewById(R.id.ivEye);
        btnLogin.setOnClickListener(this);
        ivEye.setOnClickListener(this);
        etPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {   // 按下完成按钮，这里和上面imeOptions对应
                    doLogin();
                    return false;   //返回true，保留软键盘。false，隐藏软键盘
                }
                return true;
            }
        });
    }

    @Override
    public void initData() {
        btnLogin.setButtonText("登录");
        presenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                doLogin();
                break;
            case R.id.ivEye:
                passwordVisibleToggle();
                break;
        }
    }

    /**
     * 密码是否可见开关
     */
    private void passwordVisibleToggle() {
        isPasswordVisible = !isPasswordVisible;
        int selectionStart = etPassword.getSelectionStart();
        if (isPasswordVisible) {
            ivEye.setImageResource(R.drawable.main_app_icon_eye_open);
            etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            ivEye.setImageResource(R.drawable.main_app_icon_eye_close);
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        etPassword.setSelection(selectionStart);
    }

    private void doLogin() {
        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            toastShort("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            toastShort("请输入密码");
            return;
        }
        btnLogin.startAnim();
        presenter.doLogin(username, password);
    }

    @Override
    public void onLoginSuccess() {
        onLoginDone();
    }

    @Override
    public void onLoginFailed(String msg) {
        onLoginDone();
    }

    @Override
    public void onLoginError(String msg) {
        onLoginDone();
    }

    private void onLoginDone() {
        btnLogin.stopAnim();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
