package com.example.exercise01.data.source.remote.service;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.source.remote.api.response.ApiResponse;
import com.example.exercise01.data.source.remote.api.response.LoginResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AppApi {

    @FormUrlEncoded
    @POST("/api/login")
    Observable<LoginResponse> login(@Field("email") String email,
            @Field("password") String password);

    @GET("/api/users")
    Observable<ApiResponse<List<User>>> getUserList(@Query("page") String page);
}
