package com.example.pmLoginAndroid.data.response

import com.google.gson.annotations.SerializedName

data class AvailableSocials(
    @SerializedName("socials")
    val socials: List<AvailableSocialData>
)
