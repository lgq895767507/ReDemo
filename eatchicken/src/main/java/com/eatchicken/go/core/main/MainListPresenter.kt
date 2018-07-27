package com.eatchicken.go.core.main

import com.eatchicken.go.base.mvp.DefaultDisposablePresenterImpl
import com.eatchicken.go.exception.ApiException
import com.eatchicken.go.model.MainListModel
import com.eatchicken.go.net.api.LuckAirShipMainApi
import com.eatchicken.go.net.model.NLoadMainReq
import com.eatchicken.go.net.retrofit.MyRetrofit
import com.eatchicken.go.utils.ErrorCodeCheck
import com.eatchicken.go.utils.RxUtil


class MainListPresenter : DefaultDisposablePresenterImpl<MainListContract.View>(), MainListContract.Presenter {

    override fun onStart() {
    }

    override fun loadMainList(isLoadMore: Boolean, loadMainListReq: NLoadMainReq) {
        MyRetrofit.getClient().create(LuckAirShipMainApi::class.java)
                .loadList(loadMainListReq.type, loadMainListReq.pageIndex)
                .compose(RxUtil.ioMain())
                .doOnSubscribe { disposables.add(it) }
                .subscribe({
                    if (ErrorCodeCheck.succeed(it.code)) {
                        val list = it.data.mapTo(arrayListOf()) { MainListModel(it.title, it.desc, it.time, it.img, it.link) }
                        mViewReference.get()?.loadMainListSuccess(isLoadMore, list)
                    } else {
                        throw ApiException(it.code, "Service Error!")
                    }
                }, { mViewReference.get()?.loadMainListFailed(isLoadMore, it) })
    }
}
