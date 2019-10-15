package com.example.exercise01.listUsers;

import com.example.exercise01.data.model.User;
import com.example.exercise01.data.repository.UserRepository;
import com.example.exercise01.data.source.remote.api.response.ApiResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListUserPresenter implements ListUserContract.presenter{
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
        Disposable disposable = mUserRepository.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Consumer<ApiResponse<List<User>>>() {
                    @Override
                    public void accept(ApiResponse<List<User>> listApiResponse) throws Exception {
                        if (listApiResponse == null && listApiResponse.getData() == null) {
                            return;
                        }
                        mView.onGetUserListSuccess(listApiResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (throwable == null){
                            return;
                        }
                        mView.onGetUserListError(throwable.getMessage());
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
