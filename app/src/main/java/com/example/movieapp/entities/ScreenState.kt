package com.example.movieapp.entities

sealed class ScreenState<out T> {
    data class Success<T>(val data: T) : ScreenState<T>()
    object Loading : ScreenState<Nothing>()
    data class Error(val error: Throwable) : ScreenState<Nothing>()
}