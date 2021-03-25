package com.example.pmLoginAndroid.data.response

import com.google.gson.annotations.SerializedName

internal data class AvailableSocials(
    @SerializedName("socials")
    val socials: List<AvailableSocialData>
)
