package com.example.pmLoginAndroid.data.uris

import android.net.Uri
import com.example.pmLoginAndroid.data.response.AuthUriData

internal interface AuthUriBuilder {

    fun build(data: AuthUriData): Uri
}
