package com.example.weatherapp.domain.repository.weather

import com.example.weatherapp.data.remote.dto.Weatherdto

interface WeatherRepository {
    suspend fun getWeather(city:String): Weatherdto
}