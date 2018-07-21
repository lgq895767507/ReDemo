package com.eatchicken.go.core.main

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.eatchicken.go.FrameCore
import com.eatchicken.go.R
import com.eatchicken.go.base.mvp.BaseViewActivity

class MainActivity : BaseViewActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        FrameCore.init(application, "EatChicken")
        setTitle(R.string.lucky_air_ship)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(MainFragment::class.java.simpleName)
        if (fragment == null) {
            fragment = MainFragment.newInstance()
            transaction.add(R.id.content, fragment)
        } else {
            transaction.show(fragment)
        }
        transaction.commit()
    }
}