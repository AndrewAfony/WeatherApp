package com.myapp.weather.feature_weather.data.remote.weatherDto

import com.myapp.weather.feature_weather.domain.model.Location

data class LocationDto(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
) {
    fun toLocation(): Location {
        return Location(country, localtime, name, region, tz_id)
    }
}