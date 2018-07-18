package com.eatchicken.go.base.mvp;
import java.lang.ref.WeakReference;

public abstract class DefaultPresenterImpl<T extends BaseView> implements BasePresenter<T> {

    public WeakReference<T> mViewReference;

    @Override
    public void attachView(T view) {
        mViewReference = new WeakReference<>(view);
    }
}
