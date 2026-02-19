package com.example.weatherapp.data.remote.dto

import kotlinx.serialization.Serializable

//A data class makes new copies for each weather response.
//A data object is one shared single instance.
//
//You need many separate responses for `app/src/main/java/com/example/weatherapp/data/remote/dto/Weatherdto.kt`, so use a data class.
@Serializable
data class Weatherdto(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)