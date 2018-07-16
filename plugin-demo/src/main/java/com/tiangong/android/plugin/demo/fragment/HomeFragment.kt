package com.tiangong.android.plugin.demo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tiangong.android.plugin.demo.R
import com.tiangong.android.plugin.demo.adapter.HomeAdapter

/**
 * Created by SillySnnall on 2018/5/8.
 */

class HomeFragment : Fragment() {

    val list = arrayListOf<String>()


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater!!.inflate(R.layout.fragment_home, null)
        initView(inflate)
        return inflate
    }

    private fun initView(inflate: View) {
        for (i in 0..100) {
            list.add("条目$i")
        }
        val recyclerview = inflate.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = HomeAdapter(context, list)
    }
}
