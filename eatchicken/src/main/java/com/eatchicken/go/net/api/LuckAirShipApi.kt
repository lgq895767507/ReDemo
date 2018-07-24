package com.eatchicken.go.net.api

import com.eatchicken.go.net.model.NBannerResp
import com.eatchicken.go.net.model.NListResp
import com.eatchicken.go.net.model.NTabResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author zhangnan
 * @date 2018/7/21
 */
interface LuckAirShipApi {

    @GET("lottery_new/get_news_types")
    fun loadTab(): Observable<NTabResp>

    @GET("lottery_new/get_news_banner")
    fun loadBanner(): Observable<NBannerResp>

    @GET("lottery_new/get_news_list")
    fun loadList(@Query("type") type: Int, @Query("page_index") page_index: Int): Observable<NListResp>

}