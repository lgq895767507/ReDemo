package com.eatchicken.go.core.main

import com.eatchicken.go.base.mvp.DefaultDisposablePresenterImpl
import com.eatchicken.go.exception.ApiException
import com.eatchicken.go.model.TabModel
import com.eatchicken.go.net.api.LuckAirShipMainApi
import com.eatchicken.go.net.retrofit.MyRetrofit
import com.eatchicken.go.utils.ErrorCodeCheck
import com.eatchicken.go.utils.RxUtil

class MainTabPresenter : DefaultDisposablePresenterImpl<MainTabContract.View>(), MainTabContract.Presenter {

    override fun onStart() {
    }

    override fun loadMainTabs() {
        MyRetrofit.getClient().create(LuckAirShipMainApi::class.java)
                .loadTab()
                .compose(RxUtil.ioMain())
                .doOnSubscribe { disposables.add(it) }
                .subscribe({
                    if (ErrorCodeCheck.succeed(it.code)) {
                        val tabs = it.data.mapTo(arrayListOf()) { TabModel(it.name, it.id) }
                        mViewReference.get()?.loadMainTabsSuccess(tabs)
                    } else {
                        throw ApiException(it.code, "Service Error!")
                    }
                }, { mViewReference.get()?.loadMainTabsFailed(it) })
    }
}
