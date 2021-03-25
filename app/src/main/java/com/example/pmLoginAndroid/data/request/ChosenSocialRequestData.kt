package com.example.pmLoginAndroid.data.request

import com.google.gson.annotations.SerializedName

internal data class ChosenSocialRequestData(
    @SerializedName("social_id")
    val socialId: Int,
    @SerializedName("device")
    val redirectUri: String
)
