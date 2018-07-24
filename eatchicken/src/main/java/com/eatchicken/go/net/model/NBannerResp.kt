package com.eatchicken.go.net.model

/**
 * @author zhangnan
 * @date 2018/7/24
 */

data class NBannerResp(
    val code: Int = 0,
    val data: List<Data> = listOf()
) {

    data class Data(
        val img: String = "",
        val title: String = "",
        val link: String = ""
    )
}