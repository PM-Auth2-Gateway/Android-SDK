package com.example.pmLoginAndroid.data.usecases

import com.example.pmLoginAndroid.client.PmLogin
import com.example.pmLoginAndroid.client.model.LoginError
import com.example.pmLoginAndroid.client.model.LoginResult
import javax.inject.Inject

class RequiredFieldUseCase @Inject constructor(private val options: PmLogin.PmOptions) {
    operator fun invoke(profile: HashMap<String, Any>): LoginResult {
        options.requiredFields.forEach {
            if (profile[it] == null) return LoginResult.Error(LoginError.NoRequiredFieldsError)
        }
        return LoginResult.Success(profile)
    }
}
