package com.example.pmLoginAndroid.di

import com.example.pmLoginAndroid.client.PmLogin
import com.example.pmLoginAndroid.data.api.AuthHeadersInterceptor
import com.example.pmLoginAndroid.data.api.PmService
import com.example.pmLoginAndroid.data.mapper.ProfileToHashMapper
import com.example.pmLoginAndroid.data.mapper.ProfileToScopeVerifier
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class LibraryModule(private val options: PmLogin.PmOptions) {

    @Provides
    @Singleton
    fun provideOptions(): PmLogin.PmOptions = options

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://net-api-hbyuu.ondigitalocean.app")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(headersInterceptor: AuthHeadersInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(headersInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthHeadersInterceptor() =
        AuthHeadersInterceptor(options.appId)

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        )
    }

    @Provides
    @Singleton
    fun providePmService(retrofit: Retrofit): PmService {
        return retrofit.create(PmService::class.java)
    }

    @Provides
    fun provideProfileToScopeVerifier(options: PmLogin.PmOptions): ProfileToScopeVerifier {
        return ProfileToScopeVerifier(options)
    }

    @Provides
    fun provideProfileToHashMapper(options: PmLogin.PmOptions): ProfileToHashMapper {
        return ProfileToHashMapper(options)
    }
}