package com.example.exercise01.data.source.local;

import androidx.annotation.Nullable;

import com.example.exercise01.data.source.UserDataSource;

public class UserLocalDataSource implements UserDataSource.LocalDataSource {

    @Nullable
    private static UserLocalDataSource INSTANCE;

    public static synchronized UserLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource();
        }
        return INSTANCE;
    }

}
