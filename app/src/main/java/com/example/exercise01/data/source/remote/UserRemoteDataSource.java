package com.example.exercise01.data.source.remote;

import androidx.annotation.Nullable;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.source.UserDataSource;
import com.example.exercise01.data.source.remote.api.response.ApiResponse;
import com.example.exercise01.data.source.remote.api.response.LoginResponse;
import com.example.exercise01.data.source.remote.network.RetrofitProvider;
import com.example.exercise01.data.source.remote.service.AppApi;

import java.util.List;

import io.reactivex.Observable;

public class UserRemoteDataSource implements UserDataSource.RemoteDataSource {

    @Nullable
    private static UserRemoteDataSource INSTANCE;

    private AppApi mAppApi;

    public UserRemoteDataSource() {
        mAppApi = RetrofitProvider.getInstance().getAppApi();
    }

    public static synchronized UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<LoginResponse> doLogin(String email, String password) {
        return mAppApi.login(email, password);
    }

    @Override
    public Observable<ApiResponse<List<User>>> getUserList(int page) {
        return mAppApi.getUserList(page);
    }

    @Override
    public Observable<ApiResponse<User>> getUserDetail(int userID) {
        return mAppApi.getDetailUser(userID);
    }
}
