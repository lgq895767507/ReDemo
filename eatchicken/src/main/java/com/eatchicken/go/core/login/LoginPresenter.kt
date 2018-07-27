package com.eatchicken.go.core.login

import com.eatchicken.go.base.mvp.DefaultDisposablePresenterImpl

class LoginPresenter : DefaultDisposablePresenterImpl<LoginContract.View>(), LoginContract.Presenter {

    override fun onStart() {
    }

    override fun login(account: String, password: String) {
        // todo
//        MyRetrofit.getClient().create(LuckAirShipLoginApi::class.java)
//                .login(account, password)
//                .compose(RxUtil.ioMain())
//                .doOnSubscribe { disposables.add(it) }
//                .subscribe({
//                    if (it.data) {
//                        mViewReference.get()?.loginSuccess()
//                    } else {
//                        throw ApiException(it.code, it.resultMsg)
//                    }
//                }, { mViewReference.get()?.loginFailed(it) })
    }
}
