package com.example.jp1.di

import com.example.jp1.provider.GamesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProviderModule {
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = "https://www.gamerpower.com/api/".toHttpUrl()

    @Singleton
    @Provides
    fun providesRetrofit(@Named("BaseUrl") baseUrl: HttpUrl): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun providerGamesProvider(retrofit: Retrofit): GamesProvider =
        retrofit.create(GamesProvider::class.java)
}