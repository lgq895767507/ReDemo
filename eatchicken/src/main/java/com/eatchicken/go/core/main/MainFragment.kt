package com.eatchicken.go.core.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eatchicken.go.R
import com.eatchicken.go.base.mvp.BaseViewFragment

class MainFragment : BaseViewFragment(), MainContract.View {

    private val presenter: MainContract.Presenter by lazy { MainPresenter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

}