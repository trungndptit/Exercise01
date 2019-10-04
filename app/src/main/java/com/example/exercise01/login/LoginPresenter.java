package com.example.exercise01.login;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.repository.UserRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;

    private UserRepository mUserRepository;

    private CompositeDisposable mCompositeDisposable;

    LoginPresenter(UserRepository userRepository) {
        mUserRepository = userRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void setView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        mView = null;
        mCompositeDisposable.clear();
    }

    @Override
    public void doLogin(String email, String password) {
        mView.showLoading();
        Disposable disposable =
                mUserRepository.doLogin(email, password)
                        .delay(2000, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doAfterTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                mView.hideLoading();
                            }
                        })
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(User user) throws Exception {
                                if (user == null) {
                                    return;
                                }
                                mView.onLoginSuccess(user);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mView.onLoginError(throwable);
                            }
                        });
        mCompositeDisposable.add(disposable);
    }
}
