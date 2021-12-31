package com.myapp.weather.feature_weather.di

import com.myapp.weather.BuildConfig
import com.myapp.weather.feature_weather.data.remote.WeatherApi
import com.myapp.weather.feature_weather.data.repository.GetCurrentWeatherRepositoryImpl
import com.myapp.weather.feature_weather.data.repository.GetWeatherForecastRepositoryImpl
import com.myapp.weather.feature_weather.domain.repository.GetCurrentWeatherRepository
import com.myapp.weather.feature_weather.domain.repository.GetWeatherForecastRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides
    @Singleton
    fun provideGetCurrentWeatherRepository(api: WeatherApi): GetCurrentWeatherRepository {
        return GetCurrentWeatherRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetWeatherForecastRepository(api: WeatherApi): GetWeatherForecastRepository {
        return GetWeatherForecastRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(WeatherApi::class.java)
    }
}