package com.myapp.weather.utils

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toLowerCase
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import java.text.DateFormat
import java.util.*

object date {

    val time = Clock.System.now()

//    val date = DateFormat.getDateInstance(DateFormat.DEFAULT).format(Calendar.getInstance().time)
    val localDate = time.toLocalDateTime(TimeZone.UTC)
    val currentTime = localDate.toInstant(TimeZone.of("UTC-3")).toLocalDateTime(TimeZone.UTC)

    val dayOfWeek = currentTime.dayOfWeek.toString().lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    val month = currentTime.month.toString().lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        .substring(0,3)

}