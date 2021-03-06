package com.myapp.weather.feature_weather.presentation.weather_detail.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.weather.R
import com.myapp.weather.feature_weather.domain.model.Weather
import com.myapp.weather.feature_weather.presentation.ui.theme.WeatherTheme

@Composable
fun WeatherInfoCard(
    weather: Weather
) {

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.height(IntrinsicSize.Max)
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                drawLine(
                    color = Color.White,
                    strokeWidth = 1f,
                    start = Offset(size.width / 2.0f, 0f),
                    end = Offset(size.width / 2.0f, size.height)
                )
                drawLine(
                    color = Color.White,
                    strokeWidth = 1f,
                    start = Offset(0f, size.height / 2f),
                    end = Offset(size.width, size.height / 2f)
                )
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
               WeatherInfoCardItem(
                   icon = R.drawable.ic_wind,
                   title = "WIND",
                   value = "${weather.current.wind_kph} m/s"
               )
               WeatherInfoCardItem(
                   icon = R.drawable.ic_thermometer,
                   title = "FEELS LIKE",
                   value = "${weather.current.feelslike_c}??"
               )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherInfoCardItem(
                    icon = R.drawable.ic_uv_index,
                    title = "UV INDEX",
                    value = "${weather.current.uv}"
                )
                WeatherInfoCardItem(
                    icon = R.drawable.ic_gauge,
                    title = "PRESSURE",
                    value = "${weather.current.pressure_mb} mbar"
                )
            }
        }

    }
}

@Composable
fun WeatherInfoCardItem(
    @DrawableRes icon: Int,
    title: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon (
            painter = painterResource(icon),
            contentDescription = "$title icon",
            tint = Color.White,
            modifier = Modifier
                .size(20.dp)
        )
        Column(
            modifier = Modifier.padding(start = 12.dp)
        ) {
            Text(
                text = title,
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 13.sp
            )
        }
    }
}