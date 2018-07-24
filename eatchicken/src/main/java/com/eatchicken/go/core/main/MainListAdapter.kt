package com.eatchicken.go.core.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.eatchicken.go.R
import com.eatchicken.go.base.BaseRecyclerViewModel
import com.eatchicken.go.core.web.WebActivity
import com.eatchicken.go.enums.MainListItemEnum
import com.eatchicken.go.model.BannerModel
import com.eatchicken.go.model.MainListModel
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoaderInterface
import kotlinx.android.synthetic.main.item_main_list_banner.view.*
import kotlinx.android.synthetic.main.item_main_list_item.view.*

/**
 * @author zhangnan
 * @date 2018/7/19
 */
class MainListAdapter(private val data: List<BaseRecyclerViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MainListItemEnum.BANNER.type ->
                BannerHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_main_list_banner, parent, false))
            MainListItemEnum.LIST_ITEM.type ->
                MainItemHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_main_list_item, parent, false))
            else -> EmptyHolder(View(parent?.context))
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MainListItemEnum.BANNER.type ->
                bindBannerHolder(data[position] as BannerModel, holder.itemView)
            MainListItemEnum.LIST_ITEM.type ->
                bindListItemHolder(data[position] as MainListModel, holder.itemView)
        }
    }

    private fun bindBannerHolder(bannerModel: BannerModel, itemView: View) {
        itemView.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        itemView.banner.setImages(bannerModel.items.mapTo(arrayListOf()) { it.img })
        itemView.banner.setBannerTitles(bannerModel.items.mapTo(arrayListOf()) { it.title })
        itemView.banner.setOnBannerListener {
            itemView.context.startActivity(Intent(itemView.context, WebActivity::class.java).apply {
                putExtras(WebActivity.setArguments(itemView.context.getString(R.string.lucky_air_ship), bannerModel.items[it].link))
            })
        }
        itemView.banner.setImageLoader(object : ImageLoaderInterface<View?> {
            override fun displayImage(context: Context?, path: Any?, imageView: View?) {
                Glide.with(context!!).load(path!!).into(imageView!! as ImageView)
            }

            override fun createImageView(context: Context?): View? = ImageView(context)
        })
        itemView.banner.setIndicatorGravity(BannerConfig.RIGHT)
        itemView.banner.start()
    }

    private fun bindListItemHolder(mainListModel: MainListModel, itemView: View) {
        itemView.tv_title.text = mainListModel.title
        itemView.tv_description.text = mainListModel.description
        itemView.tv_date.text = mainListModel.date
        Glide.with(itemView.context).load(mainListModel.picture).into(itemView.iv_img)
        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context, WebActivity::class.java).apply {
                putExtras(WebActivity.setArguments(itemView.context.getString(R.string.lucky_air_ship), mainListModel.routeUrl))
            })
        }
    }

    override fun getItemViewType(position: Int) = data[position].type

    inner class BannerHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class MainItemHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class EmptyHolder(view: View) : RecyclerView.ViewHolder(view)
}