package com.eatchicken.go.core.login

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.eatchicken.go.R
import com.eatchicken.go.base.mvp.BaseViewActivity

class LoginActivity : BaseViewActivity() {

    override fun setLayoutId() = R.layout.activity_login

    override fun initView(view: View, savedInstanceState: Bundle?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)
        if (fragment == null) {
            fragment = LoginFragment.newInstance()
            transaction.add(R.id.content, fragment)
        } else {
            transaction.show(fragment)
        }
        transaction.commit()
    }

}