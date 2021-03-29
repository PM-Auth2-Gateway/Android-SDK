package com.example.pmLoginAndroid

import com.example.pmLoginAndroid.client.model.LoginError
import com.example.pmLoginAndroid.data.mapper.ErrorMapper
import com.example.pmLoginAndroid.utils.ResultWrapper
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.junit.Test

internal class ErrorMapperTest {

    @Test
    fun `maps unknown error correctly`() {
        val gson = Gson()

        val error = ResultWrapper.Error(false, 777, null)
        val errorMapper = ErrorMapper(gson)

        assert(errorMapper.map(error) == LoginError.GenericError)
    }

    @Test
    fun `maps network error correctly`() {
        val gson = Gson()

        val error = ResultWrapper.Error(true)
        val errorMapper = ErrorMapper(gson)

        assert(errorMapper.map(error) == LoginError.NetworkError)
    }

    @Test
    fun `maps defined error correctly`() {
        val gson = Gson()

        val errorJson = "{\n" +
                "  \"errorCode\": 10,\n" +
                "  \"error\": \"Session id expired or doesn't exists\",\n" +
                "  \"error_description\": \"There is no profile related to provided session id\"\n" +
                "}"

        val responseBody = ResponseBody.create(null, errorJson)

        val error = ResultWrapper.Error(false, 400, responseBody)
        val errorMapper = ErrorMapper(gson)

        assert(errorMapper.map(error) == LoginError.SessionIdExpired)
    }

}