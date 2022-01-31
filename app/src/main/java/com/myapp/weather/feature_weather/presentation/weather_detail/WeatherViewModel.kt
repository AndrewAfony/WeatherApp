package com.myapp.weather.feature_weather.presentation.weather_detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.use_cases.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeather: GetWeatherUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var location by mutableStateOf("Нижний Новгород")

    var currentWeatherState by mutableStateOf(CurrentWeatherState())
    private set

    var weatherForecastState by mutableStateOf(HourlyForecastState())
    private set

    var eventFlow = MutableSharedFlow<UIEvent>()
    private set

    private var searchJob: Job? = null

    init {
        if(savedStateHandle.get<String>("city") == null)
            onUpdate("Нижний Новгород")

        savedStateHandle.get<String>("city")?.let { city ->
            location = city
            onUpdate(location)
        }
    }

    private fun onUpdate(city: String) {
        searchJob = viewModelScope.launch {
            getCurrentWeather(city)
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            currentWeatherState = currentWeatherState.copy(
                                isLoading = false,
                                currentWeather = result.data
                            )
                            weatherForecastState = weatherForecastState.copy(
                                isLoading = false,
                                weatherForecast = result.data?.forecast?.forecastday?.get(0)?.hour
                            )
                            Log.d("Result", "First screen")
                        }
                        is Resource.Error -> {
                            currentWeatherState = currentWeatherState.copy(
                                isLoading = false,
                                currentWeather = result.data
                            )
                            weatherForecastState = weatherForecastState.copy(
                                isLoading = false,
                                weatherForecast = result.data?.forecast?.forecastday?.get(0)?.hour
                            )
                            eventFlow.emit(UIEvent.ShowSnackbar(
                                message = result.message ?: "Unknown error"
                            ))
                        }
                        is Resource.Loading -> {
                            currentWeatherState = currentWeatherState.copy(
                                isLoading = true,
                                currentWeather = result.data
                            )
                            weatherForecastState = weatherForecastState.copy(
                                isLoading = false,
                                weatherForecast = result.data?.forecast?.forecastday?.get(0)?.hour
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
    }
}