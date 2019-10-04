package com.example.exercise01.data.source.remote;

import androidx.annotation.Nullable;
import com.example.exercise01.data.model.User;
import com.example.exercise01.data.source.UserDataSource;
import io.reactivex.Observable;

public class UserRemoteDataSource implements UserDataSource.RemoteDataSouce {

    @Nullable
    private static UserRemoteDataSource INSTANCE;

    public static synchronized UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Observable<User> doLogin(String email, String password) {
        //Fake Tra ve Data user khi login thanh cong
        User user = new User();
        user.setEmail(email);
        user.setName(email);
        return Observable.just(user);
    }
}
