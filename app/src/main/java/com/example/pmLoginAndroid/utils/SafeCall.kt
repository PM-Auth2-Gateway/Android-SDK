package com.example.pmLoginAndroid.utils

import retrofit2.HttpException
import java.io.IOException

@Suppress("TooGenericExceptionCaught")
internal suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultWrapper.Error(isNetworkError = true)
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = throwable.response()?.errorBody()
                ResultWrapper.Error(
                    isNetworkError = false,
                    code = code,
                    errorBody = errorResponse
                )
            }
            else -> {
                ResultWrapper.Error(isNetworkError = false)
            }
        }
    }
}
