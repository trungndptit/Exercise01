package com.example.exercise01.data.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.exercise01.data.model.User;
import com.example.exercise01.data.source.UserDataSource;
import com.example.exercise01.data.source.local.UserLocalDataSource;
import com.example.exercise01.data.source.remote.UserRemoteDataSource;
import io.reactivex.Observable;

public class UserRepository
        implements UserDataSource.RemoteDataSouce, UserDataSource.LocalDataSource {

    @NonNull
    private final UserRemoteDataSource mRemoteDataSource;

    @NonNull
    private final UserLocalDataSource mLocalDataSouce;

    @Nullable
    private static UserRepository INSTANCE;

    private UserRepository(@NonNull UserLocalDataSource localDataSouce,
            @NonNull UserRemoteDataSource remoteDataSource) {
        mLocalDataSouce = localDataSouce;
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
    public Observable<User> doLogin(String email, String password) {
        return mRemoteDataSource.doLogin(email, password);
    }
}
