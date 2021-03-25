package com.example.pmLoginAndroid.data

import androidx.annotation.IdRes
import com.example.pmLoginAndroid.R

enum class LoginError(@IdRes val msgId: Int) {
    NetworkError(R.string.network_error),
    GenericError(R.string.generic_error),
    NoRequiredFieldsError(R.string.no_required_fields_error),
}
