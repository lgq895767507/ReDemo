package com.tiangong.android.plugin.demo.adapter

import android.content.Context
import co.bxvip.adapter.recycler.BxRecyclerViewAdapter
import co.bxvip.adapter.recycler.base.RecyclerViewHolder
import com.tiangong.android.plugin.demo.R

/**
 * Created by SillySnnall on 2018/5/8.
 */
class HomeAdapter(private val context: Context, private val dataList: List<String>?) : BxRecyclerViewAdapter<String>(context, R.layout.item_home, dataList) {

    override fun convert(helper: RecyclerViewHolder?, item: String?, position: Int) {
        helper?.setText(R.id.item, item)
    }
}