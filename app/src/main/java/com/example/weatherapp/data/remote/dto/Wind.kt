package com.example.weatherapp.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class Wind(
    val deg: Int,
    val speed: Double
)