package com.myapp.weather.feature_weather.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

fun getCurrentDate(): String {

    val calendar = Calendar.getInstance()
    val date = calendar.time

    val result = SimpleDateFormat("EEEE, dd MMM", Locale.ENGLISH).format(date.time)

    return result
}

fun toDailyDate(date: String): String {

    val firstApiFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ofPattern("yyyy-MM-dd")
    } else {
        return date
    }
    val resultAvg = LocalDate.parse(date, firstApiFormat)

    val dayOfWeek = resultAvg.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    val dayOfMonth = resultAvg.dayOfMonth
    val month = resultAvg.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)

    return "$dayOfWeek, $dayOfMonth $month"

}