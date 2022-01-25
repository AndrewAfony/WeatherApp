package com.myapp.weather.feature_weather.data.remote.weatherDto

import com.myapp.weather.feature_weather.domain.model.Astro

data class AstroDto(
    val moon_illumination: String,
    val moon_phase: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String
) {
    fun toAstro(): Astro {
        return Astro(moon_illumination, moon_phase, moonrise, moonset, sunrise, sunset)
    }
}