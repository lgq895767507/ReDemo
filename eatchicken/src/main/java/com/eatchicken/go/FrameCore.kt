package com.eatchicken.go

import android.app.Application
import android.content.Context
import com.eatchicken.go.net.retrofit.MyRetrofit
import com.eatchicken.go.utils.SharedPreferenceUtils

/**
 * 由于此模块没有Application，所有初始化操作在此执行
 */
object FrameCore {
    private var application: Application? = null
    private var isInitialized: Boolean = false

    val context: Context
        get() = application!!.applicationContext

    fun init(application: Application, prefFileName: String) {
        this.application = application
        SharedPreferenceUtils.init(prefFileName)
        MyRetrofit.initClient(application)
        isInitialized = true
    }

    fun getString(stringRes: Int): String {
        return context.getString(stringRes)
    }

    fun isInitialized() = isInitialized

}