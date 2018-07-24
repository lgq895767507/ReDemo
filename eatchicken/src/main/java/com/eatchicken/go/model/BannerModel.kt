package com.eatchicken.go.model

import com.eatchicken.go.base.BaseRecyclerViewModel
import com.eatchicken.go.enums.MainListItemEnum

data class BannerModel(
        val items: List<Data>
) : BaseRecyclerViewModel() {
    override val type: Int = MainListItemEnum.BANNER.type

    data class Data(
            val img: String,
            val title: String,
            val link: String
    )
}