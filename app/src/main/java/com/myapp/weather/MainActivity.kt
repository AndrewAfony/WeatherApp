package com.myapp.weather

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.myapp.weather.repository.Repository
import com.myapp.weather.ui.theme.WeatherTheme
import com.myapp.weather.utils.Constants
import java.util.*

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    private val repository = Repository()

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this, MainAcitivityViewModelFactory(repository)).get(MainActivityViewModel::class.java)
    }

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val location = viewModel.location.value


            val scaffoldState = rememberScaffoldState()
            Scaffold (
                scaffoldState = scaffoldState,
                floatingActionButton = {
                   FloatingActionButton(
                       onClick = { viewModel.getCurrentWeather(location = location) },
                       backgroundColor = Color.White
                   ) {
                       Icon(
                           Icons.Filled.Update,
                           contentDescription = null,
                           tint = Color.Gray
                       )
                   }
                },
                isFloatingActionButtonDocked = true,
                floatingActionButtonPosition = FabPosition.End,
                bottomBar = {
                    BottomBar()
                }
            ) {
                Column(modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    App(
                        modifier = Modifier.padding(it),
                        viewModel = viewModel
                    )
                }

            }
        }


        // TODO ("BottomBar с FAB (Update) и SearchButton")
        // TODO ("При нажатии на SearchButton снизу выезжает поиск")

    }
}

@ExperimentalAnimationApi
@Composable
fun App(
    modifier: Modifier = Modifier,
    viewModel: MainActivityViewModel
) {

    val weather = viewModel.myResponse.value
    val location = viewModel.location

    var textFiledSearchIsShown by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.MyLocation,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(16.dp)
            )
            Text(
                text = location.value,
                modifier = Modifier
                    .clickable {
                        textFiledSearchIsShown = !textFiledSearchIsShown
                    }
            )
        }


        Card(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 4.dp)
            .fillMaxWidth(),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(modifier = Modifier
                .background(
                brush = Brush.verticalGradient(listOf(Color(0xFF446CCF), Color(0xFF2D4AD8)))
            )) {

                Row(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column() {

                        Surface(
                            color = Color.Transparent,
                            shape = CircleShape
                        ) {

                            WeatherImage(viewModel = viewModel)

                        }

                        Text(
                            modifier = Modifier
                                .padding(top = 4.dp),
                            text = weather.weather?.get(0)?.description.toString()
                                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                            fontSize = 20.sp,
                            color = Color.White
                        )
                        Text(
                            text = "${viewModel.dayOfWeek}, ${viewModel.month} ${viewModel.currentTime.dayOfMonth}",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Start,
                            color = Color.White
                        )
                    }

                    Column( modifier = Modifier
                        .padding(end = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        if (viewModel.loading.value) {
                            LoadingProgressBar(isDisplayed = viewModel.loading.value)
                        }
                        else {
                            Row() {
                                Text(
                                    text = weather.main?.temp.toString(),
                                    fontSize = 50.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_circle),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(top = 16.dp, start = 4.dp)
                                        .size(10.dp)
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .padding(bottom = 8.dp),
                                text = "Feels like ${weather.main?.feels_like}",
                                fontSize = 12.sp,
                                color = Color.White
                            )

                        }
                    }
                }
            }


        }
        Row (
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
                ) {
            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(end = 4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Max",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = weather.main?.temp_max.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Min",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = weather.main?.temp_min.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Humidity",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "${weather.main?.humidity.toString()} %",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Pressure",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "${weather.main?.pressure.toString()} hPa",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        val den = LocalDensity.current

        AnimatedVisibility(
            visible = textFiledSearchIsShown,
            enter = slideInVertically(
                initialOffsetY = { with(den) { -10.dp.roundToPx() } }
            ),
            exit = slideOutVertically()
        ) {
            SearchField(
                viewModel = viewModel,
                isShown = textFiledSearchIsShown,
                modifier = Modifier
                    .animateEnterExit(
                        enter = slideInVertically(),
                        exit = slideOutVertically()
                    ),
                location = location.value
            )
        }

    }

}


@Composable
fun WeatherImage(viewModel: MainActivityViewModel) {

    val imgId = viewModel.myResponse.value.weather?.get(0)?.icon

    Image(
        painter = rememberImagePainter(
            data = "https://openweathermap.org/img/wn/$imgId@2x.png"
        ),
        contentDescription = "Weather Icon",
        modifier = Modifier.size(60.dp)
    )
}

@Composable
fun SearchField(
    viewModel: MainActivityViewModel,
    isShown: Boolean,
    modifier: Modifier = Modifier,
    location: String
) {
    val focusManager = LocalFocusManager.current

    if (isShown) {
        Card(modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            TextField(
                value = viewModel.location.value,
                onValueChange = { viewModel.location.value = it },
                singleLine = true,
                leadingIcon = { Icon(Icons.Filled.EditLocation, contentDescription = null)},
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                keyboardActions = KeyboardActions(
                    onAny = {
                        viewModel.getCurrentWeather(location = location)
                        focusManager.clearFocus()
                    }
                ),
                modifier = modifier
                    .fillMaxWidth()
            )
        }

    }
}

@Composable
fun LoadingProgressBar(
    isDisplayed: Boolean
) {

    if(isDisplayed) {
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun BottomBar(){

    var selectedIndex by remember { mutableStateOf(0)}

    BottomAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {

        BottomNavigation(backgroundColor = Color.White) {

            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                selected = (selectedIndex == 0),
                onClick = {selectedIndex = 0 }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Search, contentDescription = null) },
                selected = (selectedIndex == 1),
                onClick = {selectedIndex = 1 }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Doorbell, contentDescription = null) },
                selected = (selectedIndex == 2),
                onClick = {selectedIndex = 2 }
            )
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                selected = (selectedIndex == 3),
                onClick = {selectedIndex = 3 }
            )

            Spacer(modifier = Modifier.weight(1f, true))
        }


    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    WeatherTheme {
        App(
            viewModel = MainActivityViewModel(repository = Repository())
        )
    }
}