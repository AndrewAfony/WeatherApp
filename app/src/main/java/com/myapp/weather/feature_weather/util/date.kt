package com.myapp.weather.feature_weather.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

fun getCurrentWeather(): String {

    return DateFormat.getDateInstance(DateFormat.MEDIUM).format(Calendar.getInstance().time)

}

fun test(): String {

    val calendar = Calendar.getInstance()
    val date = calendar.time

    val result = SimpleDateFormat("EEEE, dd MMM", Locale.ENGLISH).format(date.time)

    return result
}