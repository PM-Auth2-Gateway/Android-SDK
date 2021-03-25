package com.example.pmLoginAndroid.data.mapper

import com.example.pmLoginAndroid.data.response.AvailableSocials
import com.example.pmLoginAndroid.data.response.LoginSocial
import javax.inject.Inject

internal class AvailableSocialsMapper @Inject constructor() {

    fun map(response: AvailableSocials): List<LoginSocial> {

        return response.socials.filter { social ->
            LoginSocial.values().any { it.id == social.id }
        }
            .map { social ->
                LoginSocial.values().find { it.id == social.id }!!
            }
    }
}
