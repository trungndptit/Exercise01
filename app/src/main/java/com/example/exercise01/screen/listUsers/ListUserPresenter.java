package com.example.exercise01.screen.listUsers;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.repository.UserRepository;
import com.example.exercise01.data.source.local.UserEntity;
import com.example.exercise01.data.source.remote.api.response.ApiResponse;
import com.example.exercise01.util.Constant;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ListUserPresenter implements ListUserContract.presenter {
    private ListUserContract.view mView;

    private UserRepository mUserRepository;

    private CompositeDisposable mCompositeDisposable;

    public ListUserPresenter(UserRepository userRepository) {
        mUserRepository = userRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getUserList() {
        mView.showLoading();
        Disposable disposable = mUserRepository.getUserList(Constant.PAGE_REQUEST)
                .map(new Function<ApiResponse<List<User>>, List<User>>() {
                    @Override
                    public List<User> apply(ApiResponse<List<User>> listApiResponse) throws Exception {
                        return listApiResponse.getData();
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
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        if (users == null) {
                            return;
                        }
                        mView.onGetUserListSuccess(users);
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
    public void getFavUserList() {
    }

    @Override
    public void saveFavoriteUser(UserEntity userEntity) {
        Disposable disposable =
                mUserRepository.insertOrUpdateFavoriteUser(userEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.onInsertFavoriteUserSuccess();
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
    public void saveFavoriteUserSharedPrefs(int id) {

    }


    @Override
    public void setView(ListUserContract.view view) {
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
