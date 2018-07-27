package com.eatchicken.go.core.login

import com.eatchicken.go.base.mvp.DefaultDisposablePresenterImpl

class RegisterPresenter : DefaultDisposablePresenterImpl<RegisterContract.View>(), RegisterContract.Presenter {

    override fun onStart() {
    }

    override fun register(account: String, password: String) {
        // todo
//        MyRetrofit.getClient().create(LuckAirShipLoginApi::class.java)
//                .register(account, password)
//                .compose(RxUtil.ioMain())
//                .doOnSubscribe { disposables.add(it) }
//                .subscribe({
//                    if (it.data) {
//                        mViewReference.get()?.registerSuccess()
//                    } else {
//                        throw ApiException(it.code, it.resultMsg)
//                    }
//                }, { mViewReference.get()?.registerFailed(it) })
    }
}
