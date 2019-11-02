package com.example.exercise01.screen.listUsers;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.repository.UserRepository;
import com.example.exercise01.data.source.remote.api.response.ApiResponse;
import com.example.exercise01.util.Constant;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListUserPresenter implements ListUserContract.presenter {
    private ListUserContract.view mView;

    private UserRepository mUserRepository;

    private CompositeDisposable mCompositeDisposable;

    public ListUserPresenter(UserRepository userRepository) {
        mUserRepository = userRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    private List<User> getUserList(List<User> users, List<User> userEntities) {
        for (User user : users) {
            for (User userEntity : userEntities) {
                if (user.getId() == userEntity.getId()) {
                    user.setFavoriteUser(true);
                }
            }
        }

        return users;
    }

    @Override
    public void getUserListApi() {
        mView.showLoading();
        Disposable disposable = Observable.zip(mUserRepository.getUserList(Constant.PAGE_REQUEST),
                mUserRepository.getFavoriteUserList().toObservable(),
                new BiFunction<ApiResponse<List<User>>, List<User>, List<User>>() {
                    @Override
                    public List<User> apply(ApiResponse<List<User>> listApiResponse, List<User> userEntities) throws Exception {
                        getUserList(listApiResponse.getData(), userEntities);
                        return listApiResponse.getData();
                    }
                }).subscribeOn(Schedulers.io())
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
                        if (throwable == null){
                            return;
                        }
                        mView.onGetError(throwable.getMessage());
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void saveFavoriteUser(User user) {
        Disposable disposable =
                mUserRepository.insertOrUpdateFavoriteUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.onSaveFavoriteUserSuccess();
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
