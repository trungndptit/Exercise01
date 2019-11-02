package com.example.exercise01.data.model;

import com.example.exercise01.data.source.local.UserEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id")
    @Expose
    private int mId;

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("first_name")
    @Expose
    private String mName;

    @SerializedName("last_name")
    @Expose
    private String mLastName;

    @SerializedName("avatar")
    @Expose
    private String mAvatar;

    public User() {
    }

    public User(UserEntity userEntity){
        setId(userEntity.getId());
        setEmail(userEntity.getEmail());
        setName(userEntity.getName());
        setLastName(userEntity.getLastName());
        setAvatar(userEntity.getAvatar());
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }
}
