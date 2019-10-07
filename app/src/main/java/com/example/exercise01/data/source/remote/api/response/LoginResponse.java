package com.example.exercise01.data.source.remote.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @Expose
    @SerializedName("token")
    private String mToken;

    public String getToken() {
        return mToken;
    }
}
