package com.example.exercise01.listUsers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise01.R;
import com.example.exercise01.base.BaseActivity;
import com.example.exercise01.data.model.User;
import com.example.exercise01.data.repository.UserRepository;
import com.example.exercise01.data.source.local.UserLocalDataSource;
import com.example.exercise01.data.source.remote.UserRemoteDataSource;

import java.util.List;

public class ListUsersActivity extends BaseActivity implements ListUserContract.view {

    private ListUserContract.presenter mPresenter;

    private RecyclerView mRvUsers;

    private ListUserAdapter mAdapter;

    private ProgressDialog mProgressDialog;

    public static void getIntent(Context context){
        Intent intent = new Intent(context, ListUsersActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_list_users;
    }

    @Override
    public void init() {

        UserRepository userRepository =
                UserRepository.getInstance(UserLocalDataSource.getInstance(),
                        UserRemoteDataSource.getInstance());
        mPresenter = new ListUserPresenter(userRepository);
        mPresenter.setView(this);

        mProgressDialog = new ProgressDialog(this);

        mAdapter = new ListUserAdapter();
        mRvUsers = findViewById(R.id.rv_users);
        mRvUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRvUsers.setHasFixedSize(true);
        mRvUsers.setAdapter(mAdapter);

        mPresenter.getUserList();
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
    public void onGetUserListSuccess(List<User> users) {
        mAdapter.updateData(users);
    }

    @Override
    public void onGetUserListError(String msgError) {
        Toast.makeText(this, msgError, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unsubscribe();
    }
}
