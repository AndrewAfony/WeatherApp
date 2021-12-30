package com.myapp.weather.feature_weather.data.remote.dto

import com.myapp.weather.feature_weather.domain.model.MainParams

data class MainParamsDto(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
) {
    fun toMainParams(): MainParams {
        return MainParams(
            feels_like = feels_like,
            humidity = humidity,
            pressure = pressure,
            temp = temp,
            temp_max = temp_max,
            temp_min = temp_min
        )
    }
}