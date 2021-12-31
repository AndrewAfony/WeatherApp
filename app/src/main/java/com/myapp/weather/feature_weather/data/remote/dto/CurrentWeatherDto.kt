package com.myapp.weather.feature_weather.data.remote.dto

import com.myapp.weather.feature_weather.domain.model.current_weather.CurrentWeather

data class CurrentWeatherDto(
    val base: String,
    val cod: Int,
    val dt: Int,
    val id: Int,
    val main: MainParamsDto,
    val name: String,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDescriptionDto>,
    val wind: WindDto
) {
    fun toCurrentWeather(): CurrentWeather {
        return CurrentWeather(
            main = main.toMainParams(),
            name = name,
            timezone = timezone,
            weather = weather.map { it.toWeatherDescription() },
            wind = wind.toWind()
        )
    }
}