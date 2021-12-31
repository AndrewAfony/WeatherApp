package com.myapp.weather.feature_weather.data.remote.forecastDto

import com.myapp.weather.feature_weather.domain.model.forecast_weather.Params

data class ParamsDto(
    val clouds: Clouds?,
    val dt: Int?,
    val dt_txt: String,
    val main: MainDto?,
    val pop: Float?,
    val snow: Snow?,
    val sys: Sys?,
    val visibility: Int?,
    val weather: List<Weather>?,
    val wind: Wind?
) {
    fun toParams(): Params {
        return Params(dt_txt, main?.toMain())
    }
}
