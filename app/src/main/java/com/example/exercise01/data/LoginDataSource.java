package com.example.exercise01.data;

import io.reactivex.Observable;

public interface LoginDataSource {
    Observable<Login> getLogin();

    void doLogin(Login login);
}
