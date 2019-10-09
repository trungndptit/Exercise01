package com.example.exercise01.data.source;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.source.remote.api.response.ApiResponse;
import com.example.exercise01.data.source.remote.api.response.LoginResponse;

import java.util.List;

import io.reactivex.Observable;

public interface UserDataSource {

    /**
     * RemoteData For User
     */
    interface RemoteDataSource {

        Observable<LoginResponse> doLogin(String email, String password);

        Observable<ApiResponse<List<User>>> getUserList();
    }

    /**
     * LocalData For User
     */
    interface LocalDataSource {

    }
}
