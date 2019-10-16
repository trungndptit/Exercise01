package com.example.exercise01.screen.login;

import com.example.exercise01.data.repository.UserRepository;
import com.example.exercise01.data.source.remote.api.response.LoginResponse;
import com.example.exercise01.util.LogUtils;
import com.example.exercise01.util.StringUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
                .map(new Function<LoginResponse, String>() {
                    @Override
                    public String apply(LoginResponse loginResponse) throws Exception {
                        return loginResponse.getToken();
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
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String token) throws Exception {
                        if (StringUtils.isBlank(token)) {
                            return;
                        }
                        mView.onLoginSuccess(token);
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
