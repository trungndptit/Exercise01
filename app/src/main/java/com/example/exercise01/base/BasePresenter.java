package com.example.exercise01.base;

public interface BasePresenter<T> {

    void setView(T view);

    void subscribe();

    void unsubscribe();
}
