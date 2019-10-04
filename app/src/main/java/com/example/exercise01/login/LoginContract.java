package com.example.exercise01.login;

import com.example.exercise01.base.BasePresenter;
import com.example.exercise01.base.BaseView;
import com.example.exercise01.data.model.User;

public interface LoginContract {

    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void onLoginSuccess(User user);

        void onLoginError(Throwable throwable);
    }

    interface Presenter extends BasePresenter<LoginContract.View> {
        void doLogin(String email, String password);
    }
}
