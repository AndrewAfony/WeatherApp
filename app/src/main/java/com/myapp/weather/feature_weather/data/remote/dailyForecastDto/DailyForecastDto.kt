package com.myapp.weather.feature_weather.data.remote.dailyForecastDto

import com.myapp.weather.feature_weather.domain.model.daily_forecast_weather.DailyForecast

data class DailyForecastDto(
    val city_name: String,
    val country_code: String,
    val `data`: List<DataDto>,
    val lat: String,
    val lon: String,
    val state_code: String,
    val timezone: String
) {
    fun toDailyForecast(): DailyForecast {
        return DailyForecast(city_name, country_code, data)
    }
}