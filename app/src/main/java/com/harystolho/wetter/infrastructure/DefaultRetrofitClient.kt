package com.harystolho.wetter.infrastructure

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DefaultRetrofitClient {

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    val weatherBitRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(WEATHER_BIT_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    companion object {
        private const val WEATHER_BIT_URL = "https://api.weatherbit.io/v2.0/"
    }

}