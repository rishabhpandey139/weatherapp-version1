package com.example.weatherapp.util
/**
 * A generic class that holds a value or an error status.
 * Used for representing different states of data loading operations.
 */

//You are defining a Result state class so you can represent the lifecycle of a data operation and easily update the UI based on that state.
sealed class Result<out T>{
    object Initial: Result<Nothing>()
    object Loading: Result<Nothing>()
    data class Success<T>(val data : T): Result<T>()
    data class Error(val message:String): Result<Nothing>()

}
