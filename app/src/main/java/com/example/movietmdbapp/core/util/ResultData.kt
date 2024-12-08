package com.example.movietmdbapp.core.util

sealed class ResultData<out T> {
    data class Success<out T>(val data: T?) : ResultData<T>()

    data class Failure(val e: Exception?) : ResultData<Nothing>()

    data object Loading : ResultData<Nothing>()
}