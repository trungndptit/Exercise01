package com.example.exercise01.login;

import android.app.Activity;

import com.example.exercise01.data.Login;
import com.example.exercise01.data.LoginRepositoryImpl;

public class LoginPresenter implements LoginContract.Presenter {
    LoginActivity mLoginActivity;

    public LoginPresenter(LoginActivity loginActivity) {
        mLoginActivity = loginActivity;
    }

    @Override
    public void callLogin(String username, String password) {
        LoginRepositoryImpl loginRepository = new LoginRepositoryImpl(this);
        Login login = new Login(username, password);
        loginRepository.doLogin(login);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    public void loginPreCallback(){
        mLoginActivity.hideLoading();
    }
}
