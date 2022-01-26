package com.myapp.weather.feature_weather.presentation.city_search

import com.myapp.weather.feature_weather.domain.model.city.CityItem

data class CitiesState(
    val isLoading: Boolean = false,
    val cities: List<CityItem>? = null
)
