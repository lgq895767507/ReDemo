package com.eatchicken.go

import android.app.Application
import android.content.Context
import com.eatchicken.go.net.retrofit.MyRetrofit
import com.eatchicken.go.utils.SharedPreferenceUtils

object FrameCore {
    private var application: Application? = null

    val context: Context
        get() = application!!.applicationContext

    fun init(application: Application, prefFileName: String) {
        this.application = application
        SharedPreferenceUtils.init(prefFileName)
        MyRetrofit.initClient(application)
    }

    fun getString(stringRes: Int): String {
        return context.getString(stringRes)
    }

}