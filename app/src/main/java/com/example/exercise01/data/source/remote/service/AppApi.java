package com.example.exercise01.data.source.remote.service;

import com.example.exercise01.data.source.remote.api.response.LoginResponse;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AppApi {

    @FormUrlEncoded
    @POST("/api/login")
    Observable<LoginResponse> login(@Field("email") String email,
            @Field("password") String password);
}
