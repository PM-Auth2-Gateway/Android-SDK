package com.example.pmLoginAndroid.data.request

import com.google.gson.annotations.SerializedName

data class ProfileRequestData(
    @SerializedName("sessionId")
    val sessionId: String
)
