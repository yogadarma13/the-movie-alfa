package com.yogadarma.core.data.source.remote

sealed class ApiResponse<T>(val value: T? = null, val error: Exception? = null) {
    class Success<T>(value: T) : ApiResponse<T>(value)
    class Error<T>(error: Exception) : ApiResponse<T>(error = error)
}
