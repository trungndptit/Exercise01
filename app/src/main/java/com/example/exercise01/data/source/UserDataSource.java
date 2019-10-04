package com.example.exercise01.data.source;

import com.example.exercise01.data.model.User;
import io.reactivex.Observable;

public interface UserDataSource {

    /**
     * RemoteData For User
     */
    interface RemoteDataSouce {

        Observable<User> doLogin(String email, String password);
    }

    /**
     * LocalData For User
     */
    interface LocalDataSource {

    }
}
