package com.myapp.weather

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.weather.model.Main
import com.myapp.weather.model.MainWeather
import com.myapp.weather.model.WeatherDescription
import com.myapp.weather.repository.Repository
import com.myapp.weather.utils.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import kotlin.coroutines.coroutineContext

class MainActivityViewModel(private val repository: Repository): ViewModel() {

    // loading progress bar
    val loading = mutableStateOf(false)
    val location = mutableStateOf("Moscow")

    init {
        getCurrentWeather(location = "Moscow")
    }

    var myResponse: MutableState<MainWeather> = mutableStateOf(MainWeather(main = Main(), weather = listOf(WeatherDescription())))

    fun getCurrentWeather(location: String){

        viewModelScope.launch() {

            loading.value = true

            delay(1000L)

            val response = try {
                repository.getCurrentWeather(location)
            } catch (e: IOException) {
//                Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                Log.d(Constants.TAG_MainActivity, "Error: ${e.localizedMessage}")
                return@launch
            } catch (e: HttpException) {
//                Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                Log.d(Constants.TAG_MainActivity, "Error: ${e.localizedMessage}")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                myResponse.value = response.body()!!
            } else {
//                Toast.makeText(context, "Response is not successful: ${response.body()}", Toast.LENGTH_LONG).show()
                Log.d(Constants.TAG_MainActivity, "Error: response is not successful")
            }

            loading.value = false
        }
    }

    // DATE
    var time = mutableStateOf(Clock.System.now())

    //    val date = DateFormat.getDateInstance(DateFormat.DEFAULT).format(Calendar.getInstance().time)
    private val localDate = time.value.toLocalDateTime(TimeZone.UTC)
    val currentTime = localDate.toInstant(TimeZone.of("UTC-3")).toLocalDateTime(TimeZone.UTC)

    val dayOfWeek = currentTime.dayOfWeek.toString().lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    val month = currentTime.month.toString().lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        .substring(0,3)

}