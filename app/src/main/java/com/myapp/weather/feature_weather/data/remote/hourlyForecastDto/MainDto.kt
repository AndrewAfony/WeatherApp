package com.myapp.weather.feature_weather.data.remote.hourlyForecastDto

import com.myapp.weather.feature_weather.domain.model.hourly_forecast_weather.Main

data class MainDto(
    val feels_like: Double?,
    val grnd_level: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val sea_level: Int?,
    val temp: Double?,
    val temp_kf: Double?,
    val temp_max: Double?,
    val temp_min: Double?
) {
    fun toMain(): Main {
        return Main(
            feels_like, temp
        )
    }
}