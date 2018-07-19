package com.eatchicken.go.core.main

import android.app.Fragment
import android.app.FragmentTransaction
import android.os.Bundle
import android.view.View
import com.eatchicken.go.R
import com.eatchicken.go.base.mvp.BaseViewActivity
import com.eatchicken.go.net.retrofit.MyRetrofit

class MainActivity : BaseViewActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        MyRetrofit.initClient(application)
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        var fragment: Fragment
        try {
            fragment = fragmentManager.findFragmentByTag(MainFragment::class.java.simpleName)
            transaction.show(fragment)
        } catch (e: Exception) {
            fragment = MainFragment.newInstance()
            transaction.add(R.id.content, fragment)
        }
        transaction.commit()
    }

}