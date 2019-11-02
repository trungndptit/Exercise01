package com.example.exercise01.screen.favoriteUser;

import com.example.exercise01.base.BasePresenter;
import com.example.exercise01.base.BaseView;
import com.example.exercise01.data.model.User;

import java.util.List;

public interface FavoriteUserContract {
    interface view extends BaseView {

        void onGetFavoriteListSuccess(List<User> users);
    }

    interface presenter extends BasePresenter<FavoriteUserContract.view> {

        void getFavoriteUserList();
    }
}
