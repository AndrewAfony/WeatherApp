package com.myapp.weather.feature_weather.presentation.daily_weather_forecast

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.weather.core.util.Resource
import com.myapp.weather.feature_weather.domain.use_cases.GetDailyWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel @Inject constructor(
    private val getWeather: GetDailyWeatherForecastUseCase
): ViewModel() {

    var state = mutableStateOf(DailyForecastState())
    private set

    var eventFlow = MutableSharedFlow<UIEvent>()
        private set

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
                        }
                        is Resource.Loading -> {
                            state.value = state.value.copy(
                                isLoading = true,
                                dailyForecast = result.data
                            )
                        }
                    }
                }
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
    }

}