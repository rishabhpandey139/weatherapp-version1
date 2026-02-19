package com.example.weatherapp.data.service



import io.ktor.client.HttpClient

import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest

import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class WeatherApiService() {

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {6
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 15000 // 15 seconds
            connectTimeoutMillis = 15000 //
            socketTimeoutMillis = 15000
        }



        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.openweathermap.org"

            }
        }
    }


}
