package com.eatchicken.go.core.main

import android.app.Fragment
import android.app.FragmentTransaction
import android.os.Bundle
import com.eatchicken.go.R
import com.eatchicken.go.base.mvp.BaseViewActivity

class MainActivity : BaseViewActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
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