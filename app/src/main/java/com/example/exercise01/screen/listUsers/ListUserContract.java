package com.example.exercise01.screen.listUsers;

import com.example.exercise01.base.BasePresenter;
import com.example.exercise01.base.BaseView;
import com.example.exercise01.data.model.User;

import java.util.List;

public interface ListUserContract {
    interface view extends BaseView {
        void onGetUserListSuccess(List<User> users);
    }

    interface presenter extends BasePresenter<ListUserContract.view> {
        void getUserList();
    }
}
