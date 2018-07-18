package com.eatchicken.go.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.eatchicken.go.FrameCore

object SharedPreferenceUtils {

    private var sPrefFileName: String? = null

    private val preference: SharedPreferences
        get() {
            checkInit()
            return FrameCore.context.getSharedPreferences(sPrefFileName, Context.MODE_PRIVATE)
        }

    fun init(prefFileName: String) {
        sPrefFileName = prefFileName
    }

    private fun checkInit() {
        if (TextUtils.isEmpty(sPrefFileName)) {
            throw RuntimeException("Call init before any other method!")
        }
    }


    fun clearAll() {
        val editor = preference.edit()
        editor.clear()
        editor.apply()
    }

    fun saveString(key: String, value: String) {
        val editor = preference.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveInt(key: String, value: Int) {
        val editor = preference.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun saveLong(key: String, value: Long) {
        val editor = preference.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun saveBoolean(key: String, value: Boolean) {
        val editor = preference.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }
}