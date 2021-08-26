package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author 郭梦龙
 * @data 8/22/21-10:42 PM
 * @Description 未来几天天气的数据模型
 */
data class DailyResponse(val status:String,val result: Result) {
    data class Result(val daily:Daily)
    data class Daily(val temperature:List<Temperature>,val skycon:List<Skycon>,
    @SerializedName("life_index") val lifeIndex:LifeIndex)
    data class Temperature(val max:Float,val min:Float)
    data class Skycon(val value:String,val date: Date)
    data class LifeIndex(val coldRisk:List<LifeDescription>,val carWashing:List<LifeDescription>,val ultraviolet:List<LifeDescription>,val dressing:List<LifeDescription>)
    data class LifeDescription(val desc:String)
}