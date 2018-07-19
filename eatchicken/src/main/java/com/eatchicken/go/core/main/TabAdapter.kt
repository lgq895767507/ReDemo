package com.eatchicken.go.core.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eatchicken.go.R
import com.eatchicken.go.model.TabModel
import kotlinx.android.synthetic.main.item_tab_item.view.*

/**
 * @author zhangnan
 * @date 2018/7/19
 */
class TabAdapter(private val tabs: List<TabModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onTabSelectedListener: OnTabSelectedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return TabHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_tab_item, null, false))
    }

    override fun getItemCount(): Int = tabs.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.tv_tab.text = tabs[position].tabName
        holder.itemView.setOnClickListener { onTabSelectedListener?.onTabSelected(position) }
    }

    inner class TabHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnTabSelectedListener {

        fun onTabSelected(position: Int)

    }
}