package com.example.exercise01.data;

import android.os.Handler;

import com.example.exercise01.login.LoginPresenter;

public class LoginRepositoryImpl implements LoginRepository {
    LoginPresenter loginPresenter;

    public LoginRepositoryImpl(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void doLogin(Login login) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginRepoCallback();
            }
        }, 1000);
    }

    @Override
    public boolean getResponse() {
        return true;
    }

    private void loginRepoCallback(){
        loginPresenter.loginPreCallback();
    }

}
