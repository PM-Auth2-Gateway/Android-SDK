package com.example.pmLoginAndroid.client.model

import androidx.annotation.IdRes
import com.example.pmLoginAndroid.R

enum class LoginError(@IdRes val msgId: Int) {
    NetworkError(R.string.network_error_msg),
    GenericError(R.string.generic_error_msg),
    NoRequiredFieldsError(R.string.try_another_social_msg),
    UserAbortedLogin(R.string.aborted_authorization_error_msg),
    SessionIdExpired(R.string.try_again_later_msg),
    ErrorDuringAuthorization(R.string.try_again_later_msg),
    InvalidId(R.string.generic_error_msg),
    TokenError(R.string.generic_error_msg),
    UnauthorizedAccess(R.string.generic_error_msg)
}
