package com.example.pmLoginAndroid.data.mapper

import com.example.pmLoginAndroid.data.LoginSocial
import com.example.pmLoginAndroid.data.response.AvailableSocials
import javax.inject.Inject

internal class AvailableSocialsMapper @Inject constructor() {

    private val availableSocials = listOf(
        LoginSocial.GOOGLE
    )

    fun map(response: AvailableSocials): List<LoginSocial> {
        return response.socials.filter { social ->
            availableSocials.any { it.id == social.id }
        }
            .map { social ->
                availableSocials.find { it.id == social.id }!!
            }
    }
}