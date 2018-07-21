package com.eatchicken.go.base

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View

abstract class BaseActivity : AppCompatActivity() {

    protected var progressDialog: ProgressDialog? = null

    protected fun hideProgressLoading() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
    }

    protected fun showProgressLoading() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }
        if (!progressDialog!!.isShowing) {
            progressDialog!!.show()
        }
    }

    override fun onDestroy() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
        }
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar.elevation = 0f
        }
        val layoutId = setLayoutId()
        if (layoutId > 0) {
            val contentView = layoutInflater.inflate(layoutId, null, false)
            setContentView(contentView)
            initView(contentView, savedInstanceState)
        }
//        if (GetSystemUtils.getSystemRom() == GetSystemUtils.SYS_MIUI) {
//            setStatusBarDarkMode(true, this)
//        }
    }

    /**
     * 小米手机是否为darkmode。 https://dev.mi.com/doc/p=4769/index.html
     *
     * @param darkmode darkMode
     * @param activity activity
     */
    fun setStatusBarDarkMode(darkmode: Boolean, activity: Activity) {
        val clazz = activity.window::class.java
        try {
            val darkModeFlag: Int
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz.getMethod("setExtraFlags", Int::class.java, Int::class.java)
            extraFlagField.invoke(activity.window, if (darkmode) darkModeFlag else 0, darkModeFlag)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun setFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val options = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.decorView.systemUiVisibility = options
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    abstract fun setLayoutId(): Int

    abstract fun initView(view: View, savedInstanceState: Bundle?)
}
