package com.example.pmLoginAndroid.client.model

import androidx.annotation.IdRes
import com.example.pmLoginAndroid.R

enum class LoginError(@IdRes val msgId: Int) {
    NetworkError(R.string.network_error),
    GenericError(R.string.generic_error),
    NoRequiredFieldsError(R.string.no_required_fields_error),
    UserAbortedLogin(R.string.aborted_authorization_error),
    SessionIdExpired(R.string.session_id_expired_error),
    ErrorDuringAuthorization(R.string.authorization_error)
}
