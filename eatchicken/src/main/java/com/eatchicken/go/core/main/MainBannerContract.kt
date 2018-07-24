package com.eatchicken.go.core.main

import com.eatchicken.go.base.mvp.BasePresenter
import com.eatchicken.go.base.mvp.BaseView
import com.eatchicken.go.model.BannerModel


interface MainBannerContract {

    interface View : BaseView {

        fun loadMainBannersSuccess(banner: BannerModel)

        fun loadMainBannerFailed(e: Throwable)

    }

    interface Presenter : BasePresenter<View> {

        fun loadMainBanner()

    }

}