package com.example.exercise01.screen.listUsers;

import com.example.exercise01.base.BasePresenter;
import com.example.exercise01.base.BaseView;
import com.example.exercise01.data.model.User;
import com.example.exercise01.data.source.local.UserEntity;

import java.util.List;

public interface ListUserContract {
    interface view extends BaseView {
        void onGetUserListSuccess(List<User> users);

        void onGetFavUserList(List<User> users);

        void onInsertFavoriteUserSuccess();
    }

    interface presenter extends BasePresenter<ListUserContract.view> {
        void getUserList();
        void getFavUserList();
        void saveFavoriteUser(UserEntity userEntity);
        void saveFavoriteUserSharedPrefs(int id);
    }
}
