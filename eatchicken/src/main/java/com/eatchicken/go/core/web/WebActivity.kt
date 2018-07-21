package com.eatchicken.go.core.web

import android.os.Bundle
import android.view.View
import com.eatchicken.go.R
import com.eatchicken.go.base.mvp.BaseViewActivity

class WebActivity : BaseViewActivity() {
    var activityListener: IActivity? = null

    override fun setLayoutId(): Int = R.layout.activity_web

    override fun initView(view: View, savedInstanceState: Bundle?) {
        goneTitle()
        val url = intent.getStringExtra(WEB_URL_KEY)
        val title = intent.getStringExtra(WEB_TITLE_KEY)
        fragmentManager.beginTransaction().add(R.id.activity_tbs, WebFragment.newInstance(url, title)).commitAllowingStateLoss()
    }


    override fun onBackPressed() {
        activityListener?.onBackPressed()
    }

    companion object {
        private const val WEB_TITLE_KEY: String = "WEB_TITLE_KEY"
        private const val WEB_URL_KEY: String = "WEB_URL_KEY"
        @JvmStatic
        fun setArguments(title: String? = "", webUrl: String): Bundle {
            val bundle = Bundle()
            bundle.putString(WEB_TITLE_KEY, title)
            bundle.putString(WEB_URL_KEY, webUrl)
            return bundle
        }
    }

    interface IActivity {
        fun onBackPressed()
    }

}
