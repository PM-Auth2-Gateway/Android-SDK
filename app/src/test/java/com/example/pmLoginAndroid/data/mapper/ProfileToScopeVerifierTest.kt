package com.example.pmLoginAndroid.data.mapper

import android.util.Log
import com.example.pmLoginAndroid.client.PmLogin
import com.example.pmLoginAndroid.data.ProfileFields
import com.example.pmLoginAndroid.data.response.ProfileData
import org.junit.Ignore
import org.junit.Test

internal class ProfileToScopeVerifierTest {

    @Test
    fun `ProfileToScopeVerifier works correctly`() {
        val options = PmLogin.PmOptions(
            appId = "1",
            redirectUrl = "pmacademy://authorize",
            onErrorCallback = {
                Log.d("TAG", " error")
            },
            onSuccessCallback = {
                Log.d("TAG", " Success")
            },
            scope = listOf(
                ProfileFields.PROFILE_ID,
                ProfileFields.PROFILE_LOCALE,
                ProfileFields.PROFILE_PHOTO,
                ProfileFields.PROFILE_FIRST_NAME,
                ProfileFields.PROFILE_LAST_NAME,
                ProfileFields.PROFILE_IS_EMAIL_VERIFIED,
            )
        )

        val profile = ProfileData(
            accessToken = "",
            email = "",
            expiresIn = 213,
            firstName = "asd",
            lastName = "dsa",
            id = "1",
            isVerifiedEmail = true,
            locale = "ru",
            photo = "",
            refreshToken = "434"
        )

        assert(ProfileToScopeVerifier(options).verify(profile))
    }

    @Ignore("ProfileToScopeVerifier catching null works correctly: Ignored while profile is not nullable")
    @Test
    fun `ProfileToScopeVerifier catching null works correctly`() {
        val options = PmLogin.PmOptions(
            appId = "1",
            redirectUrl = "pmacademy://authorize",
            onErrorCallback = {
                Log.d("TAG", " error")
            },
            onSuccessCallback = {
                Log.d("TAG", " Success")
            },
            scope = listOf(
                ProfileFields.PROFILE_ID,
                ProfileFields.PROFILE_LOCALE,
                ProfileFields.PROFILE_PHOTO,
                ProfileFields.PROFILE_FIRST_NAME,
                ProfileFields.PROFILE_LAST_NAME,
            )
        )

        val profile = ProfileData(
            accessToken = "null",
            email = "",
            expiresIn = 213,
            firstName = "asd",
            lastName = "dsa",
            id = "null",
            isVerifiedEmail = true,
            locale = "ru",
            photo = "",
            refreshToken = "434"
        )

        assert(!ProfileToScopeVerifier(options).verify(profile))
    }
}