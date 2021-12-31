package com.myapp.weather.feature_weather.presentation.daily_weather_forecast

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DailyForecastScreen(
    viewModel: DailyForecastViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is DailyForecastViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .background(MaterialTheme.colors.primary)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                DailyForecastTitle(city = state.dailyForecast?.city_name ?: "Moscow")
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Next 7 Days",
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn{

                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun prev1() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            DailyForecastTitle(city = "Moscow")
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Next 7 Days",
                    fontSize = 18.sp
                )
            }
        }

    }
}