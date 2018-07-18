package com.eatchicken.go.base.mvp

import com.eatchicken.go.base.BaseFragment

open class BaseViewFragment : BaseFragment(), BaseView {

    override fun showLoading() {
        showProgressLoading()
    }

    override fun hideLoading() {
        hideProgressLoading()
    }

}