package com.eatchicken.go.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import com.eatchicken.go.FrameCore

class ToastUtils {

    private var toast: Toast? = null
    private val context: Context = FrameCore.context

    fun showToast(text: String) {
        if (TextUtils.isEmpty(text)) return
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(text)
            toast!!.setDuration(Toast.LENGTH_SHORT)
        }
        toast!!.show()
    }

    fun showToast(stringRes: Int) {
        val text = context.getString(stringRes)
        showToast(text)
    }

    fun showLongToast(stringRes: Int) {
        val text = context.getString(stringRes)
        showLongToast(text)
    }

    fun showLongToast(text: String) {
        if (TextUtils.isEmpty(text)) return
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG)
        } else {
            toast!!.setText(text)
            toast!!.setDuration(Toast.LENGTH_LONG)
        }
        toast!!.show()
    }

}