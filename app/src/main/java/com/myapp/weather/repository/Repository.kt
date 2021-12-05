package com.myapp.weather.repository

import com.myapp.weather.api.RetrofitInstance
import com.myapp.weather.model.MainWeather
import retrofit2.Response

class Repository {

    suspend fun getCurrentWeather(location: String): Response<MainWeather> {
        return RetrofitInstance.api.getCurrentWeather(location = location)
    }

}