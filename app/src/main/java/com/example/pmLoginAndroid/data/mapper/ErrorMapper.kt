package com.example.pmLoginAndroid.data.mapper

import com.example.pmLoginAndroid.client.model.LoginError
import com.example.pmLoginAndroid.data.response.ErrorResponse
import com.example.pmLoginAndroid.utils.ResultWrapper
import com.google.gson.Gson
import javax.inject.Inject

@Suppress("ReturnCount")
internal class ErrorMapper @Inject constructor(private val gson: Gson) {

    private companion object {
        const val CODE_SESSION_EXPIRED = 10
        const val CODE_ABORTED = 12
        const val CODE_SERVER_ERROR = 14
        const val CODE_INVALID_ID = 16
        const val CODE_TOKEN_ERROR = 18
        const val CODE_UNAUTHORIZED_ACCESS = 20
    }

    fun map(error: ResultWrapper.Error): LoginError {

        if (error.isNetworkError) return LoginError.NetworkError

        error.errorBody?.let {
            val errorModel = gson.fromJson(error.errorBody.string(), ErrorResponse::class.java)
            return when (errorModel.errorCode) {
                CODE_SESSION_EXPIRED -> LoginError.SessionIdExpired
                CODE_ABORTED -> LoginError.UserAbortedLogin
                CODE_SERVER_ERROR -> LoginError.ErrorDuringAuthorization
                CODE_INVALID_ID -> LoginError.InvalidId
                CODE_TOKEN_ERROR -> LoginError.TokenError
                CODE_UNAUTHORIZED_ACCESS -> LoginError.UnauthorizedAccess
                else -> LoginError.GenericError
            }
        }
        return LoginError.GenericError

    }
}

