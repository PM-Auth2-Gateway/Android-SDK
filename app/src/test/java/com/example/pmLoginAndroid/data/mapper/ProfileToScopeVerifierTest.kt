package com.example.pmLoginAndroid.data.mapper

import android.util.Log
import com.example.pmLoginAndroid.client.PmLogin
import com.example.pmLoginAndroid.data.ProfileFields
import com.example.pmLoginAndroid.data.response.AdditionalInformation
import com.example.pmLoginAndroid.data.response.ProfileData
import org.junit.Ignore
import org.junit.Test

internal class ProfileToScopeVerifierTest {

    @Test
    fun `ProfileToScopeVerifier works correctly`() {
        val options = PmLogin.PmOptions(
            appId = "1",
            redirectUrl = "pmacademy://authorize",
            requiredFields = listOf(
                ProfileFields.PROFILE_ID,
                ProfileFields.PROFILE_LOCALE,
                ProfileFields.PROFILE_PHOTO,
                ProfileFields.PROFILE_FIRST_NAME,
                ProfileFields.PROFILE_LAST_NAME,
                ProfileFields.PROFILE_IS_EMAIL_VERIFIED,
            )
        )

        val profile = ProfileData(
            email = "",
            firstName = "asd",
            lastName = "dsa",
            id = "1",
            isVerifiedEmail = true,
            locale = "ru",
            photo = "",
            additionalInformation = AdditionalInformation("","","")
        )

        assert(ProfileToScopeVerifier(options).verify(profile))
    }

    @Ignore("ProfileToScopeVerifier catching null works correctly: Ignored while profile is not nullable")
    @Test
    fun `ProfileToScopeVerifier catching null works correctly`() {
        val options = PmLogin.PmOptions(
            appId = "1",
            redirectUrl = "pmacademy://authorize",
            requiredFields = listOf(
                ProfileFields.PROFILE_ID,
                ProfileFields.PROFILE_LOCALE,
                ProfileFields.PROFILE_PHOTO,
                ProfileFields.PROFILE_FIRST_NAME,
                ProfileFields.PROFILE_LAST_NAME,
                ProfileFields.PROFILE_IS_EMAIL_VERIFIED,
            )
        )

        val profile = ProfileData(
            email = "",
            firstName = "asd",
            lastName = "dsa",
            id = "null",
            isVerifiedEmail = true,
            locale = "ru",
            photo = "",
            additionalInformation = AdditionalInformation("","","")
        )

        assert(!ProfileToScopeVerifier(options).verify(profile))
    }
}
