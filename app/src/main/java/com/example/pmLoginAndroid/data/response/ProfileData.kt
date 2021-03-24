package com.example.pmLoginAndroid.data.response


import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("expiresIn")
    val expiresIn: Int,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("isVerifiedEmail")
    val isVerifiedEmail: Boolean,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)
