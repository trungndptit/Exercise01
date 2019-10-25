package com.example.exercise01.screen.userDetail;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.repository.UserRepository;
import com.example.exercise01.data.source.remote.api.response.ApiResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class UserDetailPresenter implements UserDetailContract.presenter {
    private UserDetailContract.view mView;

    private UserRepository mUserRepository;

    private CompositeDisposable mCompositeDisposable;

    public UserDetailPresenter(UserRepository userRepository) {
        mUserRepository = userRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getUserDetail(int userID) {
        mView.showLoading();
        Disposable disposable = mUserRepository.getUserDetail(userID)
                .map(new Function<ApiResponse<User>, User>() {
                    @Override
                    public User apply(ApiResponse<User> userApiResponse) throws Exception {
                        return userApiResponse.getData();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        mView.onGetUserDetailSuccess(user);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (throwable == null) {
                            return;
                        }
                        mView.onGetError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void setView(UserDetailContract.view view) {
        mView = view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
