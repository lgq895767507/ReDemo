package com.eatchicken.go.base.mvp

import io.reactivex.disposables.CompositeDisposable

/**
 * @author zhangnan
 * @date 2018/4/11
 */
abstract class DefaultDisposablePresenterImpl<T : BaseView> : DefaultPresenterImpl<T>() {

    val disposables = CompositeDisposable()

    override fun onStop() {
        disposables.clear()
    }
}