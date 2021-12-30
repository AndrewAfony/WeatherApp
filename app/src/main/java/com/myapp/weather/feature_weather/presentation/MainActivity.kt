package com.myapp.weather.feature_weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.unit.sp
import com.myapp.weather.feature_weather.presentation.ui.theme.WeatherTheme
import com.myapp.weather.feature_weather.presentation.weather_detail.WeatherDetailScreen
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // val focusManager = LocalFocusManager.current

    /*
    * keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onAny = {
                        viewModel.getCurrentWeather(location = location)
                        focusManager.clearFocus()
                    }
                )
    */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    WeatherDetailScreen()
                }
            }
        }
    }
}
