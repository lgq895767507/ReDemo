package com.eatchicken.go.core.login

import com.eatchicken.go.base.mvp.BasePresenter
import com.eatchicken.go.base.mvp.BaseView

interface RegisterContract {

    interface View : BaseView {

        fun registerSuccess()

        fun registerFailed(e: Throwable)
    }

    interface Presenter : BasePresenter<View> {

        fun register(account: String, password: String)
    }

}