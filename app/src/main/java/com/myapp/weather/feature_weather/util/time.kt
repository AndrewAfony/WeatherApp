package com.myapp.weather.feature_weather.util

import java.text.SimpleDateFormat
import java.util.*

fun getTime(time: String): String {

    return time.substringAfter(" ")
}

fun getCurrentTime(): String {
    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat.getDateTimeInstance()
    val formatedDate = formatter.format(date).substringAfterLast(" ").substringBefore(":")

    return formatedDate

}