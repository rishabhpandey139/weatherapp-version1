package com.example.weatherapp.data.repoImpl

import com.example.weatherapp.data.remote.dto.Weatherdto
import com.example.weatherapp.data.service.WeatherApiService
import com.example.weatherapp.domain.repository.weather.WeatherRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherRepoImplem(
    private val apiService: WeatherApiService
): WeatherRepository{
    private  val apikey="d1d20cead6c90a89823a1fb9c7edbdb6"
    override suspend fun getWeather(city: String): Weatherdto {
        return try {
            apiService.client.get("/data/2.5/weather") {
                parameter("q", city)
                parameter("appid", apikey)
                parameter("units", "metric")

            }.body()
        }catch (e:Exception){
            //Log the exception for debugging
            e.printStackTrace()
            throw Exception("Failed to fetch weather data: ${e.localizedMessage}")
        }



    }
}