package com.example.weatherapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.weatherapp.data.repoImpl.WeatherRepoImplem
import com.example.weatherapp.data.service.WeatherApiService
import com.example.weatherapp.domain.repository.weather.WeatherRepository
import com.example.weatherapp.presentation.WeatherViewModel
import com.example.weatherapp.ui.theme.WeatherappTheme
import com.neatroots.weatherapps.presentation.WeatherScreen

class MainActivity : ComponentActivity() {

    private val weatherApiService by lazy {
        WeatherApiService()
    }

    private val weatherRepository: WeatherRepository by lazy {
        WeatherRepoImplem(weatherApiService)
    }

    private val viewModel: WeatherViewModel by lazy {
        WeatherViewModel(weatherRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            WeatherappTheme {
                WeatherScreen(viewModel = viewModel)
            }
        }
    }
}