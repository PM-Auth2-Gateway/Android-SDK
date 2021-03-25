package com.example.pmLoginAndroid.data.mapper

import com.example.pmLoginAndroid.client.model.LoginError
import com.example.pmLoginAndroid.utils.ResultWrapper
import javax.inject.Inject

internal class ErrorMapper @Inject constructor() {

    fun map(error: ResultWrapper.Error): LoginError {

        if (error.isNetworkError) return LoginError.NetworkError

        // TODO return error based on code/message after final contracts are available
        return when (error.code) {
            null -> LoginError.GenericError
            else -> LoginError.GenericError
        }

    }
}
