package com.example.exercise01.data.source.local;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.source.UserDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

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
    public Flowable<List<User>> getFavoriteUserList() {
        return mUserDao.getUserList().map(new Function<List<UserEntity>, List<User>>() {
            @Override
            public List<User> apply(List<UserEntity> userEntities) throws Exception {
                List<User> users = new ArrayList<>();
                for (UserEntity userEntity : userEntities) {
                    User user = userEntity.userFromEntity();
                    users.add(user);
                }
                return users;
            }
        });
    }

    @Override
    public Completable insertOrUpdateFavoriteUser(User user) {
        UserEntity userEntity = new UserEntity().userToEntity(user);
        return mUserDao.insertUser(userEntity);
    }
}
