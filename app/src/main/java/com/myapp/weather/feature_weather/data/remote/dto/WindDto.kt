package com.myapp.weather.feature_weather.data.remote.dto

import com.myapp.weather.feature_weather.domain.model.Wind

data class WindDto(
    val speed: Double
) {
    fun toWind(): Wind {
        return Wind(
            speed = speed
        )
    }
}