package com.myapp.weather.feature_weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.myapp.weather.feature_weather.presentation.ui.theme.WeatherTheme

private const val TAG = "MainActivity"

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

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    WeatherTheme {

    }
}