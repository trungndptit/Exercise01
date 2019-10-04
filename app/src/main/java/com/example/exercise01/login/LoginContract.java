package com.example.exercise01.login;

import com.example.exercise01.base.BasePresenter;
import com.example.exercise01.base.BaseView;
import com.example.exercise01.data.Login;

public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void showLoading();
        void hideLoading();
        void loginSuccess(String msg);
        void loginFailure(String errMsg);
    }

    interface Presenter extends BasePresenter {
        void callLogin(Login login);
    }
}
