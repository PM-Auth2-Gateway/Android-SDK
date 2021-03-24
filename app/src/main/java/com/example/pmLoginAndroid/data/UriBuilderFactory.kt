package com.example.pmLoginAndroid.data

import com.example.pmLoginAndroid.data.uris.AuthUriBuilder
import com.example.pmLoginAndroid.data.uris.FacebookAuthUriBuilder
import com.example.pmLoginAndroid.data.uris.GoogleAuthUriBuilder
import javax.inject.Inject

internal class UriBuilderFactory @Inject constructor() {

    fun create(social: LoginSocial): AuthUriBuilder {
        return when (social) {
            LoginSocial.GOOGLE -> GoogleAuthUriBuilder()
            LoginSocial.FACEBOOK -> FacebookAuthUriBuilder()
        }
    }
}
