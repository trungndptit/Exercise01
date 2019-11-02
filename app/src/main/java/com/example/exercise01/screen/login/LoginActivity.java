package com.example.exercise01.screen.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exercise01.R;
import com.example.exercise01.base.BaseActivity;
import com.example.exercise01.data.repository.UserRepository;
import com.example.exercise01.data.source.local.UserLocalDataSource;
import com.example.exercise01.data.source.remote.UserRemoteDataSource;
import com.example.exercise01.screen.listUsers.ListUsersActivity;
import com.example.exercise01.util.ActivityUtils;
import com.example.exercise01.util.StringUtils;


public class LoginActivity extends BaseActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    private EditText mEdtEmail;
    private EditText mEtdPassword;
    private Button mBtnLogin;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        UserRepository userRepository =
                UserRepository.getInstance(UserLocalDataSource.getInstance(this),
                        UserRemoteDataSource.getInstance());
        mPresenter = new LoginPresenter(userRepository);
        mPresenter.setView(this);

        mEdtEmail = findViewById(R.id.edt_email);
        mEtdPassword = findViewById(R.id.edt_password);
        mBtnLogin = findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEdtEmail.getText().toString();
                String password = mEtdPassword.getText().toString();
                if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                    return;
                }
                mPresenter.doLogin(username, password);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.subscribe();
    }

    @Override
    protected void onStop() {
        mPresenter.unsubscribe();
        super.onStop();
    }

    @Override
    public void onLoginSuccess(String token) {
        if (StringUtils.isBlank(token)) {
            return;
        }
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
        Intent intent = ListUsersActivity.getIntent(LoginActivity.this);
        ActivityUtils.startActivityAtRoot(this, intent);
    }

    @Override
    public void onLoginError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
