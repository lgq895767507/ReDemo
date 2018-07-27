package com.eatchicken.go.base

import android.text.Editable
import android.text.TextWatcher

/**
 * @author zhangnan
 * @date 2018/7/27
 */
open class DefaultTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable) {
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }
}