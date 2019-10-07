package com.example.exercise01.data.source.remote.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {

    @Expose
    @SerializedName("status")
    private Boolean mStatus;
    @Expose
    @SerializedName("data")
    private T mData;

    public Boolean getStatus() {
        return mStatus;
    }

    public T getData() {
        return mData;
    }
}
