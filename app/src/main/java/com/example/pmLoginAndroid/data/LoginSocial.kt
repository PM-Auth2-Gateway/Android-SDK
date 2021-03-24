package com.example.pmLoginAndroid.data

import androidx.annotation.IdRes
import com.example.pmLoginAndroid.R

internal enum class LoginSocial(val id: Int, val label: String, @IdRes val icon: Int) {
    GOOGLE(1, "Google", R.drawable.ic__35_google_plus),
    FACEBOOK(2, "Facebook", R.drawable.ic__45_facebook)
}
