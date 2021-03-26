package com.example.pmLoginAndroid.data.response

import androidx.annotation.IdRes
import com.example.pmLoginAndroid.R

internal enum class LoginSocial(val id: Int, val label: String, @IdRes val icon: Int) {
    GOOGLE(1, "Google", R.drawable.ic_google_plus_circle_shadow),
    FACEBOOK(2, "Facebook", R.drawable.ic_facebook_circle_shadow)
}
