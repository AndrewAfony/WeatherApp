package com.myapp.weather.feature_weather.data.remote.weatherDto

import com.myapp.weather.feature_weather.domain.model.Forecast

data class ForecastDto(
    val forecastday: List<ForecastDayDto>
) {
    fun toForecast(): Forecast {
        return Forecast(forecastday.map { it.toForecastDay() })
    }
}