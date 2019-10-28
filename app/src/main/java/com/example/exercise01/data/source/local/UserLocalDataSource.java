package com.example.exercise01.data.source.local;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.source.UserDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UserLocalDataSource implements UserDataSource.LocalDataSource {

    @Nullable
    private static UserLocalDataSource INSTANCE;

    private UserDao mUserDao;

    public UserLocalDataSource(Context context){
        mUserDao = UserDatabase.getInstance(context).userDao();
    }

    public static synchronized UserLocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource(context.getApplicationContext());
        }
        return INSTANCE;
    }

    @Override
    public Flowable<List<UserEntity>> getUserList() {
        return null;
    }

    @Override
    public Completable insertOrUpdateUser(User user) {
        return null;
    }
}
