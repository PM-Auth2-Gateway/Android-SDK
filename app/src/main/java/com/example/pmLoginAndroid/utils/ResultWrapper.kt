package com.example.pmLoginAndroid.utils

internal sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(
        val isNetworkError: Boolean,
        val code: Int? = null,
        val error: String? = null
    ) :
        ResultWrapper<Nothing>()
}
