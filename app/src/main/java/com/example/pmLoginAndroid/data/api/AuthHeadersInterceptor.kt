package com.example.pmLoginAndroid.data.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthHeadersInterceptor(private val appId: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("App_id", appId)
                .addHeader("X-APP_ID", appId)
                .build()
        )
    }

}