package com.example.pmLoginAndroid.usecases

import com.example.pmLoginAndroid.client.PmLogin
import com.example.pmLoginAndroid.client.model.LoginResult
import com.example.pmLoginAndroid.data.LoginError
import com.example.pmLoginAndroid.data.mapper.ProfileToHashMapper

class RequiredFieldUseCase(private val options: PmLogin.PmOptions) {
    operator fun invoke(profile: HashMap<String, Any>): LoginResult {
        options.requiredFields.forEach {
            if(profile[it] == null) return LoginResult.Error(LoginError.NoRequiredFieldsError)
        }
        return LoginResult.Success(ProfileToHashMapper(options).map(profile))
    }
}
