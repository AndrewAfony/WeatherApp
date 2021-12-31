package com.myapp.weather.feature_weather.data.remote.hourlyForecastDto

import com.myapp.weather.feature_weather.domain.model.hourly_forecast_weather.City

data class CityDto(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
){
    fun toCity(): City {
        return City(
            country.lowercase(), name
        )
    }
}