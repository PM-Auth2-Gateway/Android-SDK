package com.example.pmLoginAndroid.data.mapper

import com.example.pmLoginAndroid.client.model.LoginError
import com.example.pmLoginAndroid.data.response.ErrorResponse
import com.example.pmLoginAndroid.utils.ResultWrapper
import com.google.gson.Gson
import javax.inject.Inject

@Suppress("ReturnCount")
internal class ErrorMapper @Inject constructor(private val gson: Gson) {

    fun map(error: ResultWrapper.Error): LoginError {

        if (error.isNetworkError) return LoginError.NetworkError

        error.errorBody?.let {
            val errorModel = gson.fromJson(error.errorBody.string(), ErrorResponse::class.java)
            return LoginError.values().find { it.errorCode == errorModel.errorCode }
                ?: LoginError.GenericError
        }
        return LoginError.GenericError

    }
}

