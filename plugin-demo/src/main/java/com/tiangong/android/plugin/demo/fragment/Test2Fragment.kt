package com.tiangong.android.plugin.demo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tiangong.android.plugin.demo.R

/**
 * Created by SillySnnall on 2018/5/8.
 */

class Test2Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_test2, null)
        initView(view)
        return view
    }

    private fun initView(view: View) {
    }
}
