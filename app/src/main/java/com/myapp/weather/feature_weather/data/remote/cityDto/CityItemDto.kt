package com.myapp.weather.feature_weather.data.remote.cityDto

import com.myapp.weather.feature_weather.domain.model.city.CityItem

data class CityItemDto(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String
) {
    fun toCityItem(): CityItem {
        return CityItem(country, lat, lon, name, region)
    }
}
