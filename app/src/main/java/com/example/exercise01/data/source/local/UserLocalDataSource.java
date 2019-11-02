package com.example.exercise01.data.source.local;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.exercise01.data.source.UserDataSource;
import com.example.exercise01.data.source.local.sharePrf.SharedPrefsImpl;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class UserLocalDataSource implements UserDataSource.LocalDataSource {

    @Nullable
    private static UserLocalDataSource INSTANCE;

    private UserDao mUserDao;
    private SharedPrefsImpl mSharedPrefs;

    public UserLocalDataSource(Context context){
        mUserDao = UserDatabase.getInstance(context).userDao();
        mSharedPrefs = SharedPrefsImpl.getInstance(context);
    }

    public static synchronized UserLocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource(context.getApplicationContext());
        }
        return INSTANCE;
    }

    @Override
    public Flowable<List<UserEntity>> getFavoriteUserList() {
        return mUserDao.getUserList();
    }

    @Override
    public Completable insertOrUpdateFavoriteUser(UserEntity userEntity) {
        return mUserDao.insertUser(userEntity);
    }

    @Override
    public void putFavUserID() {

    }
}
