package com.eatchicken.go.net.retrofit

import android.app.Application
import com.eatchicken.go.net.ApiConstant
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class MyRetrofit {
    companion object {

        private var retrofitClient: Retrofit? = null
        private var httpClient: OkHttpClient? = null

        @JvmStatic
        fun initClient(contextApp: Application) {
            retrofitClient = createRetrofit(contextApp)
        }

        @JvmStatic
        fun getClient(): Retrofit {
            if (retrofitClient == null) {
                throw NullPointerException("请先在Application中initClient()")
            } else {
                return retrofitClient!!
            }
        }

        @JvmStatic
        fun getOkHttpClient(): OkHttpClient {
            if (httpClient == null) {
                throw NullPointerException("请先在Application中initClient()")
            } else {
                return httpClient!!
            }
        }

        @JvmStatic
        private fun createRetrofit(context: Application): Retrofit {
            httpClient = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build()

            return Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .baseUrl(ApiConstant.getApiUrl())
                    .client(httpClient!!).build()
        }
    }
}