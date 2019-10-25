package com.example.exercise01.screen.login;

import com.example.exercise01.base.BasePresenter;
import com.example.exercise01.base.BaseView;

public interface LoginContract {

    interface View extends BaseView {

        void onLoginSuccess(String token);

        void onLoginError(Throwable throwable);
    }

    interface Presenter extends BasePresenter<LoginContract.View> {
        void doLogin(String email, String password);
    }
}
