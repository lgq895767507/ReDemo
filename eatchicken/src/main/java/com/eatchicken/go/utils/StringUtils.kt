package com.eatchicken.go.utils

import android.text.TextUtils

/**
 * @author zhangnan
 * @date 2018/7/27
 */
object StringUtils {
    /**
     * 去除所有的空格
     *
     * @param content
     * @return
     */
    fun removeAllBlank(content: String): String? {
        return if (TextUtils.isEmpty(content)) {
            content
        } else content.replace("\\s*".toRegex(), "")
    }

    fun sepetareByBlankEveryFourChar(content: String): String? {
        return if (TextUtils.isEmpty(content)) {
            content
        } else {
            val regex = "(.{4})"
            content.replace(regex.toRegex(), "$1\t\t")
        }
    }
}
