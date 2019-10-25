package com.example.exercise01.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exercise01.R;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        setContentView(getLayout());
        init();
    }

    @Override
    public void showLoading() {
        mProgressDialog.setMessage(getString(R.string.msg_loading));
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.hide();
    }

    @Override
    public void onGetError(String error) {
        Toast.makeText(this, "onGetError: " + error, Toast.LENGTH_SHORT).show();
    }

    public abstract int getLayout();

    public abstract void init();
}
