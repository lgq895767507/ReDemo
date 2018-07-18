package com.eatchicken.go.core.main

import com.eatchicken.go.base.mvp.BasePresenter
import com.eatchicken.go.base.mvp.BaseView

interface MainContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }

}