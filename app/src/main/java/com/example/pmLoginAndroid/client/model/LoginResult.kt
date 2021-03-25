package com.example.pmLoginAndroid.client.model

sealed class LoginResult {
    data class Success(val profile: HashMap<String, Any>) : LoginResult()
    data class Error(val type: LoginError) : LoginResult()
}
