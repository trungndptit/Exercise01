package com.example.exercise01.data.source;

import com.example.exercise01.data.source.remote.api.response.LoginResponse;
import io.reactivex.Observable;

public interface UserDataSource {

    /**
     * RemoteData For User
     */
    interface RemoteDataSouce {

        Observable<LoginResponse> doLogin(String email, String password);
    }

    /**
     * LocalData For User
     */
    interface LocalDataSource {

    }
}
