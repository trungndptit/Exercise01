package com.example.exercise01.data.source.local;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.exercise01.data.model.User;

@Entity(tableName = "USER_DB")
public class UserEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "userid")
    private int mId;

    @ColumnInfo(name = "email")
    private String mEmail;

    @ColumnInfo(name = "first_name")
    private String mName;

    @ColumnInfo(name = "last_name")
    private String mLastName;

    @ColumnInfo(name = "avatar")
    private String mAvatar;

    @ColumnInfo(name = "isFavoriteUser")
    private boolean mIsFavoriteUser;

    public UserEntity() {
    }

    public UserEntity(int id, String email, String name, String lastName, String avatar, boolean  isFavoriteUser) {
        mId = id;
        mEmail = email;
        mName = name;
        mLastName = lastName;
        mAvatar = avatar;
        mIsFavoriteUser = isFavoriteUser;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
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

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public UserEntity userToEntity(User user){
        this.mId = user.getId();
        this.mName = user.getName();
        this.mEmail = user.getEmail();
        this.mLastName = user.getLastName();
        this.mAvatar = user.getAvatar();
        this.mIsFavoriteUser = user.getFavoriteUser();
        return this;
    }

    public User userFromEntity(){
        User user = new User();
        user.setId(this.mId);
        user.setEmail(this.mEmail);
        user.setName(this.mName);
        user.setLastName(this.mLastName);
        user.setAvatar(this.mAvatar);
        user.setFavoriteUser(this.mIsFavoriteUser);
        return user;
    }
}
