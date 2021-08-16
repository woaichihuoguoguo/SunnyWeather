package com.sunnyweather.android.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author 郭梦龙
 * @data 8/15/21-9:39 PM
 */
object ServiceCreator {
    private const val BASE_URL="https://api.caiyunapp.com/"
    private val retrofit=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass: Class<T>):T= retrofit.create(serviceClass)
    inline fun <reified T> create():T= create(T::class.java)

}