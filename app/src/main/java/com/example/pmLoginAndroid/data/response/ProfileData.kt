package com.example.pmLoginAndroid.data.response


import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("additionalInformation")
    val additionalInformation: AdditionalInformation,
    @SerializedName("email")
    val email: String,
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
    val photo: String
)

data class AdditionalInformation(
    @SerializedName("additionalProp1")
    val additionalProp1: String,
    @SerializedName("additionalProp2")
    val additionalProp2: String,
    @SerializedName("additionalProp3")
    val additionalProp3: String
)
