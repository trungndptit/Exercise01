package com.example.exercise01.data;

import android.os.Handler;

import androidx.annotation.Nullable;

import io.reactivex.Observable;

public class LoginRepository implements LoginDataSource {

    @Nullable
    private static LoginRepository INSTANCE = null;

    public LoginRepository() {
    }

    public static LoginRepository getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LoginRepository();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<Login> getLogin() {
        return null;
    }

    @Override
    public void doLogin(Login login) {

    }

}
