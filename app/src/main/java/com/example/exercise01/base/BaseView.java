package com.example.exercise01.base;

public interface BaseView {
    void showLoading();

    void hideLoading();

    void onGetError(String error);
}
