package com.myapp.weather.feature_weather.presentation.weather_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapp.weather.feature_weather.presentation.weather_detail.components.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WeatherDetailScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {

    val currentWeatherState = viewModel.currentWeatherState.value
    val weatherForecastState = viewModel.weatherForecastState.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is WeatherViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    // TODO("Через BackdropScaffold добавить сохраненные места")
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = currentWeatherState.currentWeather?.name ?: "",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                currentWeatherState.currentWeather?.let { weather ->
                    WeatherCard(weather)
                    Spacer(modifier = Modifier.height(8.dp))
                    WeatherInfoCard(weather)
                }
                Spacer(Modifier.height(24.dp))
                ForecastTitle()
                Spacer(Modifier.height(8.dp))
                weatherForecastState.weatherForecast?.let { weather ->
                    WeatherForecastRow(weather)
                }
            }
            if (currentWeatherState.isLoading) {
                CircularProgressIndicator(modifier = Modifier
                    .align(Alignment.Center))
            }
        }
    }
}