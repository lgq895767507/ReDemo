package com.eatchicken.go.core.main

import com.eatchicken.go.base.mvp.BasePresenter
import com.eatchicken.go.base.mvp.BaseView
import com.eatchicken.go.model.TabModel


interface MainTabContract {

    interface View : BaseView {

        fun loadMainTabsSuccess(mainTabs: List<TabModel>)

        fun loadMainTabsFailed(e: Throwable)

    }

    interface Presenter : BasePresenter<View> {

        fun loadMainTabs()

    }

}