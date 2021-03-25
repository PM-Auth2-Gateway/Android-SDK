package com.example.pmLoginAndroid.client.model

import com.example.pmLoginAndroid.data.LoginError

sealed class LoginResult {
    data class Success(val profile: HashMap<String, Any>) : LoginResult()
    data class Error(val type: LoginError) : LoginResult()
}
