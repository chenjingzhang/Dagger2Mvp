package com.dxt2.mvpdagger2.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dxt2.mvpdagger2.MainActivity;
import com.dxt2.mvpdagger2.R;
import com.dxt2.mvpdagger2.contract.LoginContract;
import com.dxt2.mvpdagger2.presenter.LoginPresenter;
import com.dxt2.mvpdagger2.utils.PermissionUtils;
import com.dxt2.mvpdagger2.utils.RegexUtils;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View{
    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.register)
    Button mRegister;
    @BindView(R.id.login)
    Button mLogin;

    @Inject
    LoginPresenter mLoginPresenter;
    @BindView(R.id.user_name_input_layout)
    TextInputLayout mUserNameInputLayout;
    @BindView(R.id.password_input_layout)
    TextInputLayout mPasswordInputLayout;
    @BindView(R.id.progress_layout)
    LinearLayout mProgressLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        super.init();
        mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                login();
                return true;
            }
        });
        PermissionUtils.checkPermissions(this);
    }
 @OnClick({R.id.register,R.id.login})
 public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.register:
                navigateForResultTo(RegisterActivity.class, 0);
                break;
            case R.id.login:
                login();
                break;
        }
 }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            mUserName.setText(data.getStringExtra("extra_user_name"));
            mPassword.setText(data.getStringExtra("extra_password"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLoginPresenter.takeView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.dropView();
    }

    private void login() {
        hideSoftKeyboard();
        String userName = mUserName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if (checkUserName(userName)) {
            if (checkPassword(password)) {
                mProgressLayout.setVisibility(View.VISIBLE);
                mLoginPresenter.login(userName, password);
            }
        }
    }
    protected void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
    private boolean checkPassword(String password) {
        if (password.length() == 0) {
            mPasswordInputLayout.setErrorEnabled(true);
            mPasswordInputLayout.setError(getString(R.string.error_password_empty));
            return false;
        } else if (!RegexUtils.isValidPassword(password)) {
            mPasswordInputLayout.setErrorEnabled(true);
            mPasswordInputLayout.setError(getString(R.string.error_password));
            return false;
        }
        return true;
    }

    private boolean checkUserName(String userName) {
        if (userName.length() == 0) {
            mUserNameInputLayout.setErrorEnabled(true);
            mUserNameInputLayout.setError(getString(R.string.error_user_name_empty));
            return false;
        } else if (!RegexUtils.isValidUserName(userName)) {
            mUserNameInputLayout.setErrorEnabled(true);
            mUserNameInputLayout.setError(getString(R.string.error_user_name));
            return false;
        }
        return true;
    }



    //实现重写
    //登录成功
    @Override
    public void onLoginSuccess() {
        mProgressLayout.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
        navigateTo(MainActivity.class);
    }
   //登录失败
    @Override
    public void onLoginFailed() {
        mProgressLayout.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserNamePasswordMismatch() {
        mProgressLayout.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.user_name_password_not_match), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserNameDoesNotExist() {
        mProgressLayout.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.user_name_does_not_exist), Toast.LENGTH_SHORT).show();
    }
}
