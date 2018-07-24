package com.eatchicken.go.core.main

import com.eatchicken.go.base.mvp.DefaultDisposablePresenterImpl
import com.eatchicken.go.exception.ApiException
import com.eatchicken.go.model.BannerModel
import com.eatchicken.go.net.api.LuckAirShipApi
import com.eatchicken.go.net.retrofit.MyRetrofit
import com.eatchicken.go.utils.ErrorCodeCheck
import com.eatchicken.go.utils.RxUtil

class MainBannerPresenter : DefaultDisposablePresenterImpl<MainBannerContract.View>(), MainBannerContract.Presenter {

    override fun onStart() {
    }

    override fun loadMainBanner() {
        MyRetrofit.getClient().create(LuckAirShipApi::class.java)
                .loadBanner()
                .compose(RxUtil.ioMain())
                .doOnSubscribe { disposables.add(it) }
                .subscribe({
                    if (ErrorCodeCheck.succeed(it.code)) {
                        val banner = BannerModel(it.data.mapTo(arrayListOf()) { BannerModel.Data(it.img, it.title, it.link) })
                        mViewReference.get()?.loadMainBannersSuccess(banner)
                    } else {
                        throw ApiException(it.code, "Service Error!")
                    }
                }, { mViewReference.get()?.loadMainBannerFailed(it) })
    }
}
