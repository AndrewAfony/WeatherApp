package com.myapp.weather.feature_weather.data.remote.weatherDto

import com.myapp.weather.feature_weather.domain.model.Current

data class CurrentDto(
    val cloud: Int,
    val condition: ConditionDto,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val humidity: Int,
    val is_day: Int,
    val last_updated: String,
    val last_updated_epoch: Int,
    val precip_in: Int,
    val precip_mm: Int,
    val pressure_in: Double,
    val pressure_mb: Int,
    val temp_c: Int,
    val temp_f: Double,
    val uv: Int,
    val vis_km: Double,
    val vis_miles: Int,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double
) {
    fun toCurrent(): Current {
        return Current(
            condition.toCondition(),
            feelslike_c,
            humidity,
            last_updated,
            precip_mm,
            pressure_mb,
            temp_c,
            uv,
            wind_kph
        )
    }
}