package com.eatchicken.go.net.api

import com.eatchicken.go.net.model.NBooleanResp
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author zhangnan
 * @date 2018/7/27
 */
interface LuckAirShipLoginApi {

    @GET
    fun login(@Query("account") account: String,
              @Query("password") password: String): Observable<NBooleanResp>

    @GET
    fun register(@Query("account") account: String,
              @Query("password") password: String): Observable<NBooleanResp>
}