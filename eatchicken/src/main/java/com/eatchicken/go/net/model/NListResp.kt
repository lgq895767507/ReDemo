package com.eatchicken.go.net.model

/**
 * @author zhangnan
 * @date 2018/7/21
 */

data class NListResp(
        val code: Int = 0,
        val data: List<Data> = listOf()
) {
    data class Data(
            val title: String = "",
            val desc: String = "",
            val img: String = "",
            val link: String = "",
            val time: String = "",
            val view_count: Int = 0,
            val source: String = ""
    )
}