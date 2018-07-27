package com.eatchicken.go.core.login

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eatchicken.go.R
import com.eatchicken.go.base.DefaultTextWatcher
import com.eatchicken.go.base.mvp.BaseViewFragment
import com.eatchicken.go.core.main.MainActivity
import com.eatchicken.go.extension.gone
import com.eatchicken.go.extension.visible
import com.eatchicken.go.utils.StringUtils
import com.eatchicken.go.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseViewFragment(), RegisterContract.View {

    private val presenter: RegisterContract.Presenter by lazy { RegisterPresenter() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attachView(this)
        et_account.addTextChangedListener(object : DefaultTextWatcher() {
            override fun afterTextChanged(s: Editable) {
                if (StringUtils.removeAllBlank(s.toString())!!.isNotEmpty()) {
                    iv_account_clear.visible()
                } else {
                    iv_account_clear.gone()
                }
            }
        })
        et_pwd.addTextChangedListener(object : DefaultTextWatcher() {
            override fun afterTextChanged(s: Editable) {
                if (StringUtils.removeAllBlank(s.toString())!!.isNotEmpty()) {
                    iv_pwd_clear.visible()
                } else {
                    iv_pwd_clear.gone()
                }
            }
        })
        et_pwd_again.addTextChangedListener(object : DefaultTextWatcher() {
            override fun afterTextChanged(s: Editable) {
                if (StringUtils.removeAllBlank(s.toString())!!.isNotEmpty()) {
                    iv_pwd_again_clear.visible()
                } else {
                    iv_pwd_again_clear.gone()
                }
            }
        })
        iv_account_clear.setOnClickListener { et_account.setText("") }
        iv_pwd_clear.setOnClickListener { et_pwd.setText("") }
        iv_pwd_again_clear.setOnClickListener { et_pwd.setText("") }
        iv_back.setOnClickListener{
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            var fragment = fragmentManager.findFragmentByTag(RegisterFragment::class.java.simpleName)
            if (fragment == null) {
                fragment = LoginFragment.newInstance()
                transaction.replace(R.id.content, fragment)
            } else {
                transaction.show(fragment)
            }
            transaction.commit()
        }
        tv_register.setOnClickListener {
            if (et_account.text.toString().isEmpty()) {
                ToastUtils.showToast(R.string.input_account)
                return@setOnClickListener
            }
            if (et_pwd.text.toString().isEmpty()) {
                ToastUtils.showToast(R.string.input_password)
                return@setOnClickListener
            }
            if (et_pwd_again.text.toString().isEmpty()) {
                ToastUtils.showToast(R.string.input_password_again)
                return@setOnClickListener
            }
            if (et_pwd.text.toString() != et_pwd_again.text.toString()) {
                ToastUtils.showToast(R.string.two_password_different)
                return@setOnClickListener
            }
            presenter.register(et_account.text.toString(), et_pwd.text.toString())
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun registerSuccess() {
        startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
    }

    override fun registerFailed(e: Throwable) {
        ToastUtils.showToast(e.message)
    }

    companion object {

        fun newInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }

}