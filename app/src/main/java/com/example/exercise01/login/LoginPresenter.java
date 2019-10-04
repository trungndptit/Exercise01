package com.example.exercise01.login;

import android.os.Handler;

import com.example.exercise01.data.Login;
import com.example.exercise01.data.LoginRepository;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View mLoginView;
    LoginRepository mLoginRepository;

    public LoginPresenter(LoginContract.View loginView) {
        mLoginView = loginView;
        mLoginRepository = new LoginRepository();
    }

    @Override
    public void callLogin(final Login login) {
        mLoginView.showLoading();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoginRepository.doLogin(login);
                mLoginView.hideLoading();
            }
        }, 1000);

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
