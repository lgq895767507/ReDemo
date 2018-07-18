package com.eatchicken.go

import android.app.Application
import android.content.Context
import com.eatchicken.go.utils.SharedPreferenceUtils

object FrameCore {
    var sApplication: Application? = null

    val context: Context
        get() = sApplication!!.applicationContext

    fun init(application: Application, prefFileName: String) {
        sApplication = application
        SharedPreferenceUtils.init(prefFileName)
    }

    fun getString(stringRes: Int): String {
        return context.getString(stringRes)
    }

}