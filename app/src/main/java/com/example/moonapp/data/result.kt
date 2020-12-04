package com.example.moonapp.data

import java.lang.Exception

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val exception: Exception): Result<Nothing>()
    object Loading : Result<Nothing>()
}