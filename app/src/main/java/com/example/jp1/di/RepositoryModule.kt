package com.example.jp1.di

import com.example.jp1.provider.GamesProvider
import com.example.jp1.repository.GamesRepo
import com.example.jp1.repository.GamesRepositoryImp
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
class RepositoryModule {

    @Provides
    @Singleton
    fun providerGamesRepository(gamesProvider: GamesProvider): GamesRepo =
        GamesRepositoryImp(gamesProvider)
}