package com.eatchicken.go.base.mvp;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void onStart();

    void onStop();
}
