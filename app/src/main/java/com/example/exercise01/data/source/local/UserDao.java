package com.example.exercise01.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Query("Select * from USER_DB")
    Flowable<List<UserEntity>> getUserList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(UserEntity userEntity);
}
