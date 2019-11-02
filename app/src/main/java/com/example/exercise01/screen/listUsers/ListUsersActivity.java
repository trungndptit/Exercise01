package com.example.exercise01.screen.listUsers;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise01.R;
import com.example.exercise01.base.BaseActivity;
import com.example.exercise01.base.recyclerView.BaseRecyclerViewAdapter;
import com.example.exercise01.data.model.User;
import com.example.exercise01.data.repository.UserRepository;
import com.example.exercise01.data.source.local.UserLocalDataSource;
import com.example.exercise01.data.source.remote.UserRemoteDataSource;
import com.example.exercise01.screen.favoriteUser.FavoriteUserActivity;
import com.example.exercise01.screen.userDetail.UserDetailActivity;
import com.example.exercise01.util.LogUtils;

import java.util.List;

public class ListUsersActivity extends BaseActivity implements ListUserContract.view, BaseRecyclerViewAdapter.OnItemClickListener<User>, ListUserAdapter.OnFavoriteClickListener<User> {

    public static final String TAG = ListUsersActivity.class.getSimpleName();

    private ListUserContract.presenter mPresenter;

    private RecyclerView mRvUsers;

    private ListUserAdapter mAdapter;

    public static Intent getIntent(Context context) {
        return new Intent(context, ListUsersActivity.class);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_list_users;
    }

    @Override
    public void init() {

        UserRepository userRepository =
                UserRepository.getInstance(UserLocalDataSource.getInstance(this),
                        UserRemoteDataSource.getInstance());
        mPresenter = new ListUserPresenter(userRepository);
        mPresenter.setView(this);

        mRvUsers = findViewById(R.id.rv_users);
        mRvUsers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRvUsers.setHasFixedSize(true);

        mAdapter = new ListUserAdapter();
        mAdapter.setItemClickListener(this);
        mAdapter.setFavoriteClickListener(this);
        mRvUsers.setAdapter(mAdapter);

        mPresenter.getUserListApi();
    }

    @Override
    public void onGetUserListSuccess(List<User> users) {
        mAdapter.updateData(users);
    }

    @Override
    public void onSaveFavoriteUserSuccess() {
        Toast.makeText(this, "Saved into the Favorite list", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        mPresenter.unsubscribe();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exercise_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_fav_user:
                Intent intent = FavoriteUserActivity.getIntent(ListUsersActivity.this);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(User mUser) {
        Intent intent = UserDetailActivity.getIntent(ListUsersActivity.this, mUser.getId());
        startActivity(intent);
        LogUtils.d(TAG, mUser.getId() + "");
    }

    @Override
    public void onFavoriteClicked(User mUser) {
        mPresenter.saveFavoriteUser(mUser);

    }
}
