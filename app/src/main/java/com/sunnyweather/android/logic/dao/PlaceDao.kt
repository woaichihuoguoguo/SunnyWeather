package com.sunnyweather.android.logic.dao

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork

/**
 * @author 郭梦龙
 * @data 8/26/21-10:46 AM
 * @Description
 */
object PlaceDao {
    fun savePlace(place:Place){
        sharedPreferences().edit{
            putString("place",Gson().toJson(place))
        }
    }
    fun getSavedPlace():Place{
        val placeJson= sharedPreferences().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }
    fun isPlacedSaved()= sharedPreferences().contains("place")
    private fun sharedPreferences()=SunnyWeatherApplication.context.
    getSharedPreferences("sunny_weather",Context.MODE_PRIVATE)
}