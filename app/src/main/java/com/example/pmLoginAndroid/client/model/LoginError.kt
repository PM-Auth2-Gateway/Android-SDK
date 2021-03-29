package com.example.pmLoginAndroid.client.model

import androidx.annotation.IdRes
import com.example.pmLoginAndroid.R

enum class LoginError(@IdRes val msgId: Int, val errorCode: Int?) {
    NetworkError(R.string.network_error_msg, null),
    GenericError(R.string.generic_error_msg, null),
    NoRequiredFieldsError(R.string.try_another_social_msg, null),
    UserAbortedLogin(R.string.aborted_authorization_error_msg, 12),
    SessionIdExpired(R.string.try_again_later_msg, 10),
    ErrorDuringAuthorization(R.string.try_again_later_msg, 14),
    InvalidId(R.string.generic_error_msg, 16),
    TokenError(R.string.generic_error_msg, 18),
    UnauthorizedAccess(R.string.generic_error_msg, 20)
}
