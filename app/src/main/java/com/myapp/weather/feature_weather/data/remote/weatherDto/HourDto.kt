package com.myapp.weather.feature_weather.data.remote.weatherDto

import com.myapp.weather.feature_weather.domain.model.Hour

data class HourDto(
    val chance_of_rain: Int,
    val chance_of_snow: Int,
    val cloud: Int,
    val condition: ConditionDto,
    val dewpoint_c: Double,
    val dewpoint_f: Double,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val heatindex_c: Double,
    val heatindex_f: Double,
    val humidity: Int,
    val is_day: Int,
    val precip_in: Double,
    val precip_mm: Double,
    val pressure_in: Double,
    val pressure_mb: Int,
    val temp_c: Double,
    val temp_f: Double,
    val time: String,
    val time_epoch: Int,
    val uv: Int,
    val vis_km: Int,
    val vis_miles: Int,
    val will_it_rain: Int,
    val will_it_snow: Int,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double,
    val windchill_c: Double,
    val windchill_f: Double
) {
    fun toHour(): Hour {
        return Hour(
            feelslike_c,
            temp_c,
            time,
            condition.toCondition()
        )
    }
}