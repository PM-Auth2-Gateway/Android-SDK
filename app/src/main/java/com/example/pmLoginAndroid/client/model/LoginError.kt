package com.example.pmLoginAndroid.client.model

import androidx.annotation.IdRes
import com.example.pmLoginAndroid.R

enum class LoginError(@IdRes val msgId: Int, val errorCode: Int?) {
    NetworkError(R.string.network_error_msg, null),
    GenericError(R.string.generic_error_msg, null),
    NoRequiredFieldsError(R.string.try_another_social_msg, null),
    UserAbortedLogin(R.string.aborted_authorization_error_msg, ErrorCode.ABORTED),
    SessionIdExpired(R.string.try_again_later_msg, ErrorCode.SESSION_EXPIRED),
    ErrorDuringAuthorization(R.string.try_again_later_msg, ErrorCode.SERVER_ERROR),
    InvalidId(R.string.generic_error_msg, ErrorCode.INVALID_ID),
    TokenError(R.string.generic_error_msg, ErrorCode.TOKEN_ERROR),
    UnauthorizedAccess(R.string.generic_error_msg, ErrorCode.UNAUTHORIZED_ACCESS)
}
