package com.example.exercise01.screen.userDetail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercise01.R;
import com.example.exercise01.base.BaseActivity;
import com.example.exercise01.data.model.User;
import com.example.exercise01.data.repository.UserRepository;
import com.example.exercise01.data.source.local.UserLocalDataSource;
import com.example.exercise01.data.source.remote.UserRemoteDataSource;
import com.example.exercise01.util.Constant;
import com.example.exercise01.util.ViewUtils;

public class UserDetailActivity extends BaseActivity implements UserDetailContract.view {

    private UserDetailContract.presenter mPresenter;

    private ImageView mIvAvatar;
    private TextView mTvId;
    private TextView mTvEmail;
    private TextView mTvFirstName;
    private TextView mTvLastName;

    public static Intent getIntent(Context context, int userID) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.USER_ID_EXTRA, userID);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_user_detail;
    }

    @Override
    public void init() {
        UserRepository userRepository = UserRepository.getInstance(UserLocalDataSource.getInstance(),
                UserRemoteDataSource.getInstance());
        mPresenter = new UserDetailPresenter(userRepository);
        mPresenter.setView(this);

        mIvAvatar = findViewById(R.id.iv_avatar);
        mTvId = findViewById(R.id.tv_id);
        mTvEmail = findViewById(R.id.tv_email);
        mTvFirstName = findViewById(R.id.tv_firstname);
        mTvLastName = findViewById(R.id.tv_last_name);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            Toast.makeText(this, "No user received", Toast.LENGTH_SHORT).show();
            return;
        }
        int userID = bundle.getInt(Constant.USER_ID_EXTRA);
        mPresenter.getUserDetail(userID);
    }

    @Override
    protected void onStop() {
        mPresenter.unsubscribe();
        super.onStop();
    }

    @Override
    public void onGetUserDetailSuccess(User user) {
        ViewUtils.loadImage(user.getAvatar(), mIvAvatar);
        mTvId.setText(user.getId() + "");
        mTvEmail.setText(user.getEmail());
        mTvFirstName.setText(user.getName());
        mTvLastName.setText(user.getLastName());
    }

}
