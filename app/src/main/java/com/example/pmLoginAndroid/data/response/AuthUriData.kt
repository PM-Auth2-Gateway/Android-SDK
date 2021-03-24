package com.example.pmLoginAndroid.data.response


import com.google.gson.annotations.SerializedName

internal data class AuthUriData(
    @SerializedName("auth_uri")
    val authUri: String,
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("response_type")
    val responseType: String,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("redirect_uri")
    val redirectUri: String
)
