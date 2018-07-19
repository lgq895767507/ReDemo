package com.eatchicken.go.core.main

import com.eatchicken.go.base.mvp.BasePresenter
import com.eatchicken.go.base.mvp.BaseView
import com.eatchicken.go.model.MainListModel
import com.eatchicken.go.net.model.NLoadMainReq


interface MainListContract {

    interface View : BaseView {

        fun loadMainListSuccess(isLoadMore: Boolean, dataList: List<MainListModel>)

        fun loadMainListFailed(isLoadMore: Boolean, e: Throwable)

    }

    interface Presenter : BasePresenter<View> {

        fun loadMainList(isLoadMore: Boolean, loadMainListReq: NLoadMainReq)

    }

}