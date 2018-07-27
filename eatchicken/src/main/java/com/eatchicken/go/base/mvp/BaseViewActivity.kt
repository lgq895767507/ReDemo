package com.eatchicken.go.base.mvp

import android.os.Bundle
import com.eatchicken.go.FrameCore
import com.eatchicken.go.base.BaseActivity

abstract class BaseViewActivity : BaseActivity(), BaseView {

    private var isTop = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!FrameCore.isInitialized()) {
            FrameCore.init(application, "EatChicken")
        }
    }

    override fun onResume() {
        super.onResume()
        isTop = true
    }

    override fun showLoading() {
        showProgressLoading()
    }

    override fun hideLoading() {
        hideProgressLoading()
    }

    override fun onStop() {
        super.onStop()
        isTop = false
    }

    /**
     * 当前 Activity 是否在最上层
     */
    fun isTop() = isTop

}