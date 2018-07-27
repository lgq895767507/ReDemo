package com.eatchicken.go.net.model

/**
 * @author zhangnan
 * @date 2018/7/27
 */
data class NBooleanResp(
        val code: Int = 0,
        val data: Boolean = false,
        val resultMsg: String = ""
)