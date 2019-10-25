package com.example.exercise01.data.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.source.UserDataSource;
import com.example.exercise01.data.source.local.UserLocalDataSource;
import com.example.exercise01.data.source.remote.UserRemoteDataSource;
import com.example.exercise01.data.source.remote.api.response.ApiResponse;
import com.example.exercise01.data.source.remote.api.response.LoginResponse;

import java.util.List;

import io.reactivex.Observable;

public class UserRepository
        implements UserDataSource.RemoteDataSource, UserDataSource.LocalDataSource {

    @Nullable
    private static UserRepository INSTANCE;
    @NonNull
    private final UserRemoteDataSource mRemoteDataSource;
    @NonNull
    private final UserLocalDataSource mLocalDataSouce;

    private UserRepository(@NonNull UserLocalDataSource localDataSource,
                           @NonNull UserRemoteDataSource remoteDataSource) {
        mLocalDataSouce = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static synchronized UserRepository getInstance(
            @NonNull UserLocalDataSource localDataSource,
            @NonNull UserRemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<LoginResponse> doLogin(String email, String password) {
        //Fake account nhu tren web
        email = "eve.holt@reqres.in";
        password = "cityslicka";

        return mRemoteDataSource.doLogin(email, password);
    }

    @Override
    public Observable<ApiResponse<List<User>>> getUserList(int page) {
        return mRemoteDataSource.getUserList(page);
    }

    @Override
    public Observable<ApiResponse<User>> getUserDetail(int userID) {
        return mRemoteDataSource.getUserDetail(userID);
    }
}
