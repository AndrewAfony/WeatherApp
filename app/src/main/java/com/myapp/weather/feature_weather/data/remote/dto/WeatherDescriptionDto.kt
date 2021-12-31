package com.myapp.weather.feature_weather.data.remote.dto

import com.myapp.weather.feature_weather.domain.model.current_weather.WeatherDescription

data class WeatherDescriptionDto(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) {
    fun toWeatherDescription(): WeatherDescription {
        val descr = description.replaceFirstChar {
            it.uppercaseChar()
        }
        return WeatherDescription(
            description = descr,
            icon = icon
        )
    }
}