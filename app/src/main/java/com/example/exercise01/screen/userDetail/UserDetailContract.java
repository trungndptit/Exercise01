package com.example.exercise01.screen.userDetail;

import com.example.exercise01.base.BasePresenter;
import com.example.exercise01.base.BaseView;
import com.example.exercise01.data.model.User;

public interface UserDetailContract {
    interface view extends BaseView {

        void onGetUserDetailSuccess(User user);
    }

    interface presenter extends BasePresenter<UserDetailContract.view> {
        void getUserDetail(int userID);
    }
}
