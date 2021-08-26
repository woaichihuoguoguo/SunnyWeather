package com.sunnyweather.android.logic.model

/**
 * @author 郭梦龙
 * @data 8/22/21-11:15 PM
 * @Description
 */
data class Weather(val realtime:RealtimeResponse.Realtime,val daily:DailyResponse.Daily) {
}