package com.eatchicken.go.core.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.eatchicken.go.model.MainListModel

/**
 * @author zhangnan
 * @date 2018/7/19
 */
class MainListAdapter(private val data: List<MainListModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return MainItemHolder(View(parent?.context))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    inner class MainItemHolder(view: View) : RecyclerView.ViewHolder(view)
}