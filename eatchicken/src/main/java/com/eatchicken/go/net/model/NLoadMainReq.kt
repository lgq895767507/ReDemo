package com.eatchicken.go.net.model

/**
 * @author zhangnan
 * @date 2018/7/19
 */
data class NLoadMainReq(
        var pageIndex: Int = 0,
        val pageSize: Int = 20,
        var type: Int = 0)