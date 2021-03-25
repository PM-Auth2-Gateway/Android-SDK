package com.example.pmLoginAndroid.data.uris

import com.example.pmLoginAndroid.data.response.LoginSocial
import javax.inject.Inject

internal class UriBuilderFactory @Inject constructor() {

    fun create(social: LoginSocial): AuthUriBuilder {
        return when (social) {
            LoginSocial.GOOGLE -> GoogleAuthUriBuilder()
            LoginSocial.FACEBOOK -> FacebookAuthUriBuilder()
        }
    }
}
