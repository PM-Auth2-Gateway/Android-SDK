package com.example.pmLoginAndroid.data.mapper

import com.example.pmLoginAndroid.client.PmLogin
import javax.inject.Inject

internal class ProfileToHashMapper @Inject constructor(
    private val options: PmLogin.PmOptions
) {

    fun map(hashMap: HashMap<String, Any>): HashMap<String, Any> {
        val map = hashMapOf<String, Any>()

        options.requiredFields.forEach { field ->
            hashMap[field]?.let { value ->
                map[field] = value
            }
        }
        return map
    }
}
