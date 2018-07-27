package com.eatchicken.go.core.login

import com.eatchicken.go.base.mvp.BasePresenter
import com.eatchicken.go.base.mvp.BaseView

interface LoginContract {

    interface View : BaseView {

        fun loginSuccess()

        fun loginFailed(e: Throwable)
    }

    interface Presenter : BasePresenter<View> {

        fun login(account: String, password: String)
    }

}