package com.myapp.weather.feature_weather.data.remote

import com.myapp.weather.BuildConfig
import com.myapp.weather.feature_weather.data.remote.dailyForecastDto.DailyForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DailyForecastWeatherApi {

    @GET("/v2.0/forecast/daily")
    suspend fun getDailyWeatherForecast(
        @Query("city") city: String,
        @Query("key") key: String = BuildConfig.FORECAST_TOKEN
    ): DailyForecastDto
}