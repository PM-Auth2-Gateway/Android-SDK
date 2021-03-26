package com.example.pmLoginAndroid.client.model

sealed class LoginResult {
    data class Success(val profile: HashMap<String, String?>) : LoginResult()
    data class Error(val type: LoginError) : LoginResult()
}
