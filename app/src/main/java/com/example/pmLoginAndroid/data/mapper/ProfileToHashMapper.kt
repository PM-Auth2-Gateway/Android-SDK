package com.example.pmLoginAndroid.data.mapper

import com.example.pmLoginAndroid.client.PmLogin
import com.example.pmLoginAndroid.data.ProfileFields
import com.example.pmLoginAndroid.data.response.ProfileData
import javax.inject.Inject

internal class ProfileToHashMapper @Inject constructor(
    private val options: PmLogin.PmOptions
) {

    fun map(profile: ProfileData): HashMap<String, Any> {
        val map = hashMapOf<String, Any>()

        options.scope.forEach { field ->
            if (field == ProfileFields.PROFILE_ID) {
                map[ProfileFields.PROFILE_ID] = profile.id
            }
            if (field == ProfileFields.PROFILE_EMAIL) {
                map[ProfileFields.PROFILE_EMAIL] = profile.email
            }
            if (field == ProfileFields.PROFILE_FIRST_NAME) {
                map[ProfileFields.PROFILE_FIRST_NAME] = profile.firstName
            }
            if (field == ProfileFields.PROFILE_LAST_NAME) {
                map[ProfileFields.PROFILE_LAST_NAME] = profile.lastName
            }
            if (field == ProfileFields.PROFILE_PHOTO) {
                map[ProfileFields.PROFILE_PHOTO] = profile.photo
            }
            if (field == ProfileFields.PROFILE_LOCALE) {
                map[ProfileFields.PROFILE_LOCALE] = profile.locale
            }
            if(field == ProfileFields.PROFILE_IS_EMAIL_VERIFIED) {
                map[ProfileFields.PROFILE_IS_EMAIL_VERIFIED] = profile.isVerifiedEmail
            }
        }
        return map
    }
}
