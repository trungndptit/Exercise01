package com.example.exercise01.screen.favoriteUser;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.repository.UserRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FavoriteUserPresenter implements FavoriteUserContract.presenter{

    private FavoriteUserContract.view mView;

    private UserRepository mUserRepository;

    private CompositeDisposable mCompositeDisposable;

    public FavoriteUserPresenter(UserRepository userRepository){
        mUserRepository = userRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getFavoriteUserList() {
        Disposable disposable = mUserRepository.getFavoriteUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        if (users == null) {
                            return;
                        }
                        mView.onGetFavoriteListSuccess(users);
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
    public void setView(FavoriteUserContract.view view) {
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