package com.example.pmLoginAndroid.data.response


import com.google.gson.annotations.SerializedName

internal data class AvailableSocialModel(
    @SerializedName("authUri")
    val authUri: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("tokenUrl")
    val tokenUrl: String,
    @SerializedName("logoPath")
    val logoPath: String
)
