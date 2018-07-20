package com.eatchicken.go.core.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.eatchicken.go.R
import com.eatchicken.go.model.MainListModel
import kotlinx.android.synthetic.main.item_main_list_item.view.*

/**
 * @author zhangnan
 * @date 2018/7/19
 */
class MainListAdapter(private val data: List<MainListModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return MainItemHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_main_list_item, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = holder.itemView
        val itemModel = data[position]
        itemView.tv_title.text = itemModel.title
        itemView.tv_description.text = itemModel.description
        itemView.tv_date.text = itemModel.date
        Glide.with(itemView.context).load(itemModel.picture).into(itemView.iv_img)
    }

    inner class MainItemHolder(view: View) : RecyclerView.ViewHolder(view)
}