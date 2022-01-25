package com.myapp.weather.feature_weather.data.remote.weatherDto

import com.myapp.weather.feature_weather.domain.model.Weather

data class WeatherDto(
    val current: CurrentDto,
    val forecast: ForecastDto,
    val location: LocationDto
) {
    fun toWeather(): Weather {
        return Weather(
            current.toCurrent(),
            forecast.toForecast(),
            location.toLocation()
        )
    }
}