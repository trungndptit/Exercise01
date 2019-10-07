package com.example.exercise01.data.source.remote;

import androidx.annotation.Nullable;
import com.example.exercise01.data.source.UserDataSource;
import com.example.exercise01.data.source.remote.api.response.LoginResponse;
import com.example.exercise01.data.source.remote.network.RetrofitProvider;
import com.example.exercise01.data.source.remote.service.AppApi;
import io.reactivex.Observable;

public class UserRemoteDataSource implements UserDataSource.RemoteDataSouce {

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
}
