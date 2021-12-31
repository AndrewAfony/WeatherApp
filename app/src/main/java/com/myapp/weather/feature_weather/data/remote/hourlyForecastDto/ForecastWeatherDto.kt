package com.myapp.weather.feature_weather.data.remote.hourlyForecastDto

import com.myapp.weather.feature_weather.domain.model.hourly_forecast_weather.ForecastWeather

data class ForecastWeatherDto(
    val city: CityDto,
    val cnt: Int?,
    val cod: String?,
    val list: List<ParamsDto>,
    val message: Int?
) {
    fun toForecastWeather(): ForecastWeather {
        return ForecastWeather(
            city = city.toCity(),
            forecast = list.map { it.toParams() }
        )
    }
}