package com.example.pmLoginAndroid.data.api

import com.example.pmLoginAndroid.data.request.ChosenSocialRequestData
import com.example.pmLoginAndroid.data.request.ProfileRequestData
import com.example.pmLoginAndroid.data.response.AuthUriData
import com.example.pmLoginAndroid.data.response.AvailableSocials
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface PmService {

    @GET("Socials")
    suspend fun getAvailableSocials(): AvailableSocials

    @POST("Socials/auth-link")
    suspend fun getAuthUriData(@Body chosenSocialRequestData: ChosenSocialRequestData): AuthUriData

    @POST("Profile/info")
    suspend fun getProfileInfo(@Body profileRequestData: ProfileRequestData): HashMap<String, String?>
}
