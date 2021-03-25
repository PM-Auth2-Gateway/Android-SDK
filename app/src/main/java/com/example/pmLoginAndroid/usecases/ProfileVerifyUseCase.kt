package com.example.pmLoginAndroid.usecases

import com.example.pmLoginAndroid.data.mapper.ProfileToHashMapper
import com.example.pmLoginAndroid.data.mapper.ProfileToScopeVerifier
import com.example.pmLoginAndroid.data.response.ProfileData
import javax.inject.Inject

internal class ProfileVerifyUseCase @Inject constructor(
    private val profileToScopeVerifier: ProfileToScopeVerifier,
    private val profileToHashMapper: ProfileToHashMapper,
) {
    operator fun invoke(profile: ProfileData): ProfileVerifyState {
        return if(profileToScopeVerifier.verify(profile)) {
            ProfileVerifyState.Success(profileToHashMapper.map(profile))
        } else {
            ProfileVerifyState.Error("Some of profile fields are null")
        }
    }
}

internal sealed class ProfileVerifyState {
    data class Success(val hashMap: HashMap<String, Any>): ProfileVerifyState()
    data class Error(val error: String): ProfileVerifyState()
}
