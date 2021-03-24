package com.example.pmLoginAndroid.data.api

import com.example.pmLoginAndroid.data.request.ChosenSocialRequestData
import com.example.pmLoginAndroid.data.request.ProfileRequestData
import com.example.pmLoginAndroid.data.response.AuthUriData
import com.example.pmLoginAndroid.data.response.AvailableSocials
import com.example.pmLoginAndroid.data.response.ProfileData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface PmService {

    //    @Headers("App_id: 1", "accept: text/plain")
    @GET("Socials")
    suspend fun getAvailableSocials(): AvailableSocials

    //    @Headers("App_id: 1", "accept: text/plain")
    @POST("Socials/auth-link")
    suspend fun getAuthUriData(@Body chosenSocialRequestData: ChosenSocialRequestData): AuthUriData

    @POST("Profile/info")
    suspend fun getProfileInfo(@Body profileRequestData: ProfileRequestData): ProfileData
}
