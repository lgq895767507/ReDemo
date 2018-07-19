package com.eatchicken.go.core.main

import com.eatchicken.go.base.mvp.DefaultDisposablePresenterImpl
import com.eatchicken.go.net.model.NLoadMainReq


class MainListPresenter : DefaultDisposablePresenterImpl<MainListContract.View>(), MainListContract.Presenter {

    override fun onStart() {
    }

    override fun loadMainList(isLoadMore: Boolean, loadMainListReq: NLoadMainReq) {

    }
}
