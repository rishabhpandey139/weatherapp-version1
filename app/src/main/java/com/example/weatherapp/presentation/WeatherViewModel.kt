package com.example.weatherapp.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.remote.dto.Weatherdto
import com.example.weatherapp.domain.repository.weather.WeatherRepository
import com.example.weatherapp.util.Result
import kotlinx.coroutines.launch

class WeatherViewModel (
    private val weatherRepository: WeatherRepository
    ) : ViewModel() {
        var city by mutableStateOf("")
        private set

         var weatherState by mutableStateOf<Result<Weatherdto>>(Result.Initial)
         private set

    var snackbarMessage by mutableStateOf<String?>(null)
        private set

    fun updateCity(newCity: String) {
        city = newCity
    }
    fun searchWeather() {
        if (city.isBlank()) {
            snackbarMessage = "Please enter a city name"
            return
        }
        getWeatherForCity(city)

    }
    private fun getWeatherForCity(city:String,){
        Log.d("SearchWeather","GetWeatherForCity funtion started")
        viewModelScope.launch {
            weatherState = Result.Loading
            Log.d("SearchWeather", "GetWeatherForCity function showing Loaging state")
            try {
                val weatherData = weatherRepository.getWeather(city)
                Log.d("SearchWeather", "GetWeatherForCity function showing $weatherData")
                weatherState = Result.Success(weatherData)

            }
            catch (e:Exception){
                Log.e("SearchWeather", "GetWeatherForCity function showing error ${e.localizedMessage}")
                val errorMessage = e.message ?: "An error occurred"

                weatherState = Result.Error(errorMessage)
                snackbarMessage = errorMessage

            }
        }

    }

    fun clearSnackbarMessage() {
        snackbarMessage = null
    }


    }