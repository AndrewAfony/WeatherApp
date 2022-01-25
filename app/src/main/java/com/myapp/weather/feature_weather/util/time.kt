package com.myapp.weather.feature_weather.util

fun getTime(time: String): String {

    return time.substringAfter(" ")
}