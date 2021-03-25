package com.example.pmLoginAndroid.data.mapper

import com.example.pmLoginAndroid.client.PmLogin
import com.example.pmLoginAndroid.data.ProfileFields
import com.example.pmLoginAndroid.data.response.ProfileData
import javax.inject.Inject

@Suppress("ComplexMethod", "ReturnCount")
internal class ProfileToScopeVerifier @Inject constructor(
    private val options: PmLogin.PmOptions
) {

    fun verify(profile: ProfileData): Boolean {
        if (options.requiredFields.contains(ProfileFields.PROFILE_ID)) {
            profile.id ?: return false
        }
        if (options.requiredFields.contains(ProfileFields.PROFILE_EMAIL)) {
            profile.email ?: return false
        }
        if (options.requiredFields.contains(ProfileFields.PROFILE_FIRST_NAME)) {
            profile.firstName ?: return false
        }
        if (options.requiredFields.contains(ProfileFields.PROFILE_LAST_NAME)) {
            profile.lastName ?: return false
        }
        if (options.requiredFields.contains(ProfileFields.PROFILE_PHOTO)) {
            profile.photo ?: return false
        }
        if (options.requiredFields.contains(ProfileFields.PROFILE_LOCALE)) {
            profile.locale ?: return false
        }
        if (options.requiredFields.contains(ProfileFields.PROFILE_IS_EMAIL_VERIFIED)) {
            profile.isVerifiedEmail ?: return false
        }
        if (options.requiredFields.contains(ProfileFields.PROFILE_ADDITIONAL_INFO)) {
            profile.additionalInformation ?: return false
        }
        return true
    }
}
