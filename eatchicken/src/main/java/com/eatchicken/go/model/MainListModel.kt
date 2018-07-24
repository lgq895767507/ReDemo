package com.eatchicken.go.model

import com.eatchicken.go.base.BaseRecyclerViewModel
import com.eatchicken.go.enums.MainListItemEnum

/**
 * @author zhangnan
 * @date 2018/7/19
 */
data class MainListModel(
        val title: String,
        val description: String,
        val date: String,
        val picture: String,
        val routeUrl: String) : BaseRecyclerViewModel() {
    override val type: Int = MainListItemEnum.LIST_ITEM.type
}
