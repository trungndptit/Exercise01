package com.example.exercise01.listUsers;

import com.example.exercise01.base.BasePresenter;
import com.example.exercise01.base.BaseView;
import com.example.exercise01.data.model.User;

import java.util.List;

public interface ListUserContract {
    interface view extends BaseView{
        void showLoading();

        void hideLoading();

        void onGetUserListSuccess(List<User> users);

        void onGetUserListError(String error);
    }

    interface presenter extends BasePresenter<ListUserContract.view>{
        void getUserList();
    }
}
