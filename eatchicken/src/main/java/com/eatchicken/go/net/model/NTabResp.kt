package com.eatchicken.go.net.model

/**
 * @author zhangnan
 * @date 2018/7/21
 */

data class NTabResp(
        val code: Int = 0,
        val data: List<Data> = listOf()
) {
    data class Data(
            val id: Int = 0,
            val value: String = "",
            val name: String = ""
    )
}