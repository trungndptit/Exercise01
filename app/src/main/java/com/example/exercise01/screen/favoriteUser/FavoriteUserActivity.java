package com.example.exercise01.screen.favoriteUser;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise01.R;
import com.example.exercise01.base.BaseActivity;
import com.example.exercise01.data.model.User;
import com.example.exercise01.data.repository.UserRepository;
import com.example.exercise01.data.source.local.UserLocalDataSource;
import com.example.exercise01.data.source.remote.UserRemoteDataSource;
import com.example.exercise01.screen.listUsers.ListUserAdapter;

import java.util.List;

public class FavoriteUserActivity extends BaseActivity implements FavoriteUserContract.view {

    public static final String TAG = FavoriteUserActivity.class.getSimpleName();

    private FavoriteUserContract.presenter mPresenter;

    private RecyclerView mRvUsers;

    private ListUserAdapter mAdapter;

    public static Intent getIntent(Context context) {
        return new Intent(context, FavoriteUserActivity.class);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_favorite;
    }

    @Override
    public void init() {
        UserRepository userRepository =
                UserRepository.getInstance(UserLocalDataSource.getInstance(this),
                        UserRemoteDataSource.getInstance());
        mPresenter = new FavoriteUserPresenter(userRepository);
        mPresenter.setView(this);

        mRvUsers = findViewById(R.id.rv_users);
        mRvUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRvUsers.setHasFixedSize(true);

        mAdapter = new ListUserAdapter();
        mRvUsers.setAdapter(mAdapter);

        mPresenter.getFavoriteUserList();
    }

    @Override
    protected void onStop() {
        mPresenter.unsubscribe();
        super.onStop();
    }

    @Override
    public void onGetFavoriteListSuccess(List<User> users) {
        mAdapter.updateData(users);
    }
}
