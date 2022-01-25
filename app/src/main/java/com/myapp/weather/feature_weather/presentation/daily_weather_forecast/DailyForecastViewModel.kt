package com.myapp.weather.feature_weather.presentation.daily_weather_forecast

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.use_cases.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel @Inject constructor(
    private val getWeather: GetWeatherUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state = mutableStateOf(DailyForecastState())  // TODO: 25.01.2022 Переделать все state через by 
    private set

    var eventFlow = MutableSharedFlow<UIEvent>()
        private set

    init {
        savedStateHandle.get<String>("city")?.let { city ->
            onLoad(city)
        }
    }

    fun onLoad(city: String) {
        viewModelScope.launch {
            getWeather(city)
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            state.value = state.value.copy(
                                isLoading = false,
                                dailyForecast = result.data
                            )
                            Log.d("Result", "Success")
                        }
                        is Resource.Error -> {
                            state.value = state.value.copy(
                                isLoading = false,
                                dailyForecast = result.data
                            )
                            eventFlow.emit(
                                UIEvent.ShowSnackbar(
                                message = result.message ?: "Unknown error"
                            ))
                            Log.d("Result", "Error")
                        }
                        is Resource.Loading -> {
                            state.value = state.value.copy(
                                isLoading = true,
                                dailyForecast = result.data
                            )
                            Log.d("Result", "Loading")
                        }
                    }
                }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
    }

}