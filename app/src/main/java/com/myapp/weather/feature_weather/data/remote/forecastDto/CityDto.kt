package com.myapp.weather.feature_weather.data.remote.forecastDto

import com.myapp.weather.feature_weather.domain.model.forecast_weather.City

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
            country, name
        )
    }
}