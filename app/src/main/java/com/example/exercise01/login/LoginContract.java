package com.example.exercise01.login;

import com.example.exercise01.base.BasePresenter;
import com.example.exercise01.base.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void showLoading();
        void hideLoading();
        void loginSuccess(String msg);
        void loginFailure(String errMsg);
    }

    interface Presenter extends BasePresenter {
        void callLogin(String username, String password);
    }
}
