package com.eatchicken.go.core.main

import android.support.v4.content.ContextCompat
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
    var selectedIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return TabHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_tab_item, parent, false))
    }

    override fun getItemCount(): Int = tabs.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (selectedIndex == position) {
            holder.itemView.tv_tab.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.colorPrimary))
        } else {
            holder.itemView.tv_tab.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.black3))
        }
        holder.itemView.tv_tab.text = tabs[position].tabName
        holder.itemView.setOnClickListener {
            val oldSelectedIndex = selectedIndex
            selectedIndex = position
            notifyItemChanged(oldSelectedIndex)
            notifyItemChanged(selectedIndex)
            onTabSelectedListener?.onTabSelected(position)
        }
    }

    inner class TabHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnTabSelectedListener {

        fun onTabSelected(position: Int)

    }
}