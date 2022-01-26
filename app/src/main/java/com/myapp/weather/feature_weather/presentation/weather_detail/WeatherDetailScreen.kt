package com.myapp.weather.feature_weather.presentation.weather_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myapp.weather.NavigationRoutes
import com.myapp.weather.feature_weather.presentation.weather_detail.components.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WeatherDetailScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    navController: NavController
) {

    val currentCity = viewModel.location

    val currentWeatherState: CurrentWeatherState = viewModel.currentWeatherState
    val weatherForecastState: HourlyForecastState = viewModel.weatherForecastState
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
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(NavigationRoutes.CitySearchScreen.route) }) {
                        Icon(Icons.Default.Search, contentDescription = "Searching button")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.MoreHoriz, contentDescription = "More button")
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = currentWeatherState.currentWeather?.location?.name ?: "City isn't found",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                currentWeatherState.currentWeather?.let { weather ->
                    WeatherCard(weather)
                    Spacer(modifier = Modifier.height(8.dp))
                    WeatherInfoCard(weather)
                }
                Spacer(Modifier.height(16.dp))
                ForecastTitle(navController = navController, currentCity)
                Spacer(Modifier.height(8.dp))
                weatherForecastState.weatherForecast?.let { weather ->
                    WeatherForecastRow(weather)
                }
            }
            if (currentWeatherState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                    .align(Alignment.Center))
            }
        }
    }
}