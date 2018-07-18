package com.eatchicken.go.utils

import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*

object GetSystemUtils {

    const val SYS_MIUI = "miui"
    const val SYS_EMUI = "emui"

    private const val KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code"
    private const val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
    private const val KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage"
    private const val KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level"
    private const val KEY_EMUI_VERSION = "ro.build.version.emui"
    private const val KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion"

    fun getSystemRom(): String {
        var sys = "un_know"
        try {
            val prop = Properties()
            prop.load(FileInputStream(File(Environment.getRootDirectory(), "build.prop")))
            if (prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null ||
                    prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null ||
                    prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null) {
                sys = SYS_MIUI
            } else if (prop.getProperty(KEY_EMUI_API_LEVEL, null) != null ||
                    prop.getProperty(KEY_EMUI_VERSION, null) != null ||
                    prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null) {
                sys = SYS_EMUI
            }

        } catch (e: IOException) {
            e.printStackTrace()
            return sys
        }
        return sys
    }
}