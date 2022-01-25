package com.myapp.weather.feature_weather.data.remote.weatherDto

import com.myapp.weather.feature_weather.domain.model.Day

data class DayDto(
    val avghumidity: Int,
    val avgtemp_c: Double,
    val avgtemp_f: Double,
    val avgvis_km: Double,
    val avgvis_miles: Int,
    val condition: ConditionDto,
    val daily_chance_of_rain: Int,
    val daily_chance_of_snow: Int,
    val daily_will_it_rain: Int,
    val daily_will_it_snow: Int,
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val maxwind_kph: Double,
    val maxwind_mph: Double,
    val mintemp_c: Double,
    val mintemp_f: Double,
    val totalprecip_in: Double,
    val totalprecip_mm: Double,
    val uv: Int
) {
    fun toDay(): Day {
        return Day(
            avghumidity,
            avgtemp_c,
            avgvis_km,
            condition.toCondition(),
            daily_chance_of_rain,
            daily_chance_of_snow,
            daily_will_it_rain,
            daily_will_it_snow,
            maxtemp_c,
            maxwind_kph,
            mintemp_c,
            totalprecip_mm,
            uv
        )
    }
}