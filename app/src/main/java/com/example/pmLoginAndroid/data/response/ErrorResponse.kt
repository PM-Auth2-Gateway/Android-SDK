package com.example.pmLoginAndroid.data.response


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val error: String,
    @SerializedName("errorCode")
    val errorCode: Int,
    @SerializedName("error_description")
    val errorDescription: String
)
