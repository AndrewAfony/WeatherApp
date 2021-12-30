package com.myapp.weather.feature_weather.data.remote

import com.myapp.weather.BuildConfig
import com.myapp.weather.feature_weather.data.remote.dto.CurrentWeatherDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") token: String = BuildConfig.TOKEN,
        @Query("units") units: String = "metric"
    ): CurrentWeatherDto
}