package com.myapp.weather.feature_weather.data.remote.weatherDto

import com.myapp.weather.feature_weather.domain.model.ForecastDay

data class ForecastDayDto(
    val astro: AstroDto,
    val date: String,
    val date_epoch: Int,
    val day: DayDto,
    val hour: List<HourDto>
) {
    fun toForecastDay(): ForecastDay {
        return ForecastDay(
            astro.toAstro(),
            date,
            date_epoch,
            day.toDay(),
            hour.map { it.toHour() }
        )
    }
}