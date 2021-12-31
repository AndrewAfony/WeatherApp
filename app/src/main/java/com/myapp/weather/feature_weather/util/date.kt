package com.myapp.weather.feature_weather.util

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {

    val calendar = Calendar.getInstance()
    val date = calendar.time

    val result = SimpleDateFormat("EEEE, dd MMM", Locale.ENGLISH).format(date.time)

    return result
}