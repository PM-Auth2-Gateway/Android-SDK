package com.example.pmLoginAndroid.data.uris

import android.net.Uri
import com.example.pmLoginAndroid.data.response.AuthUriData

internal class GoogleAuthUriBuilder : AuthUriBuilder {

    override fun build(data: AuthUriData): Uri = with(data) {
        val scheme = authUri.substringBefore("://")
        val baseUrl = authUri.substringAfter("://")
        Uri.Builder()
            .scheme(scheme)
            .encodedPath(baseUrl)
            .appendQueryParameter("client_id", clientId)
            .appendQueryParameter("redirect_uri", redirectUri)
            .appendQueryParameter("response_type", responseType)
            .appendQueryParameter("scope", scope)
            .appendQueryParameter("state", state)
            .build()
    }
}
