package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @author 郭梦龙
 * @data 8/15/21-8:18 PM
 * 获取全局Context的方法
 */
class SunnyWeatherApplication:Application() {
    companion object{
        const val TOKEN="Scdokbf1wYqH0bog"//令牌值
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}