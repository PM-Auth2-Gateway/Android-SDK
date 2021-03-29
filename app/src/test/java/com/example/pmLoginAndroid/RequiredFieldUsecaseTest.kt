package com.example.pmLoginAndroid

import com.example.pmLoginAndroid.client.PmLogin
import com.example.pmLoginAndroid.client.model.LoginResult
import com.example.pmLoginAndroid.client.model.ProfileContract
import com.example.pmLoginAndroid.data.usecases.RequiredFieldUseCase
import org.junit.Test

internal class RequiredFieldUsecaseTest {
    internal class RequiredFieldUsecaseTest {

        @Test
        fun `returns success if all required fields are not null`() {
            val requiredFields = listOf(
                ProfileContract.FIRST_NAME,
                ProfileContract.LAST_NAME,
            )

            val options = PmLogin.PmOptions("1", "222", requiredFields)
            val requiredFieldUsecase = RequiredFieldUseCase(options)

            val response = hashMapOf<String, String?>(
                ProfileContract.FIRST_NAME to "name",
                ProfileContract.LAST_NAME to "last",
                ProfileContract.EMAIL to null
            )
            assert(requiredFieldUsecase.invoke(response) is LoginResult.Success)
        }

        @Test
        fun `returns error if required any field is null`() {
            val requiredFields = listOf(
                ProfileContract.FIRST_NAME,
                ProfileContract.LAST_NAME,
                ProfileContract.EMAIL,
            )

            val options = PmLogin.PmOptions("1", "222", requiredFields)
            val requiredFieldUsecase = RequiredFieldUseCase(options)

            val response = hashMapOf(
                ProfileContract.FIRST_NAME to "name",
                ProfileContract.LAST_NAME to "last",
                ProfileContract.EMAIL to null
            )
            assert(requiredFieldUsecase.invoke(response) is LoginResult.Error)
        }
    }
}