package com.example.pmLoginAndroid.utils

import okhttp3.ResponseBody

internal sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(
        val isNetworkError: Boolean,
        val code: Int? = null,
        val errorBody: ResponseBody? = null
    ) : ResultWrapper<Nothing>()
}
