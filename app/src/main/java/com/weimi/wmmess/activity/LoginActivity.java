package com.weimi.wmmess.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.weimi.wmmess.MainActivity;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.WMActivity;
import com.weimi.wmmess.business.workOrder.WorkOrderListActivity;
import com.weimi.wmmess.presenter.LoginPresenter;
import com.weimi.wmmess.viewInterface.ILoginView;
import com.weimi.wmmess.widget.ClearEditText;
import com.weimi.wmmess.widget.LoginButton;

public class LoginActivity extends WMActivity<LoginPresenter> implements View.OnClickListener, ILoginView {

    private LoginButton btnLogin;
    private EditText etUserName;
    private EditText etPassword;
    private LoginPresenter presenter;
    private ImageButton ibAccountDelete;
    private CheckBox cbPasswordVisibility;

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        etUserName = findViewById(R.id.etUserName);
        ibAccountDelete = findViewById(R.id.ib_account_delete);
        etPassword = findViewById(R.id.etPassword);
        cbPasswordVisibility = findViewById(R.id.cb_password_visibility);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        ibAccountDelete.setOnClickListener(this);
        etPassword.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {   // 按下完成按钮，这里和上面imeOptions对应
                doLogin();
                return false;   //返回true，保留软键盘。false，隐藏软键盘
            }
            return true;
        });
        cbPasswordVisibility.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbPasswordVisibility.setButtonDrawable(R.drawable.main_app_icon_eye_open);
                etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                cbPasswordVisibility.setButtonDrawable(R.drawable.main_app_icon_eye_close);
                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
    }

    @Override
    public void initData() {
        btnLogin.setButtonText("登录");
        presenter = new LoginPresenter(this);

//        doLogin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                doLogin();
                break;
            case R.id.ib_account_delete:
                etUserName.setText(null);
                break;
        }
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
        toastShort("登录失败");
        btnLogin.stopAnim();
    }

    @Override
    public void onLoginError(String msg) {
        toastShort("登录失败");
        btnLogin.stopAnim();
    }

    private void onLoginDone() {
        btnLogin.stopAnim();
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Intent intent = new Intent(LoginActivity.this, WorkOrderListActivity.class);
        startActivity(intent);
        finish();
    }
}
