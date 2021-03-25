package com.example.pmLoginAndroid.data.request

import com.google.gson.annotations.SerializedName

internal data class ProfileRequestData(
    @SerializedName("session_id")
    val sessionId: String
)
