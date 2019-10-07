package com.example.exercise01.login;

import com.example.exercise01.data.repository.UserRepository;
import com.example.exercise01.data.source.remote.api.response.LoginResponse;
import com.example.exercise01.util.LogUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();

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

        Disposable disposable = mUserRepository.doLogin(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        mView.onLoginSuccess(loginResponse.getToken());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.e(TAG, throwable.getMessage(), throwable);
                        mView.onLoginError(throwable);
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
