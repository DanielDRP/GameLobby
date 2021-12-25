package com.example.jp1.provider

import com.example.jp1.model.Games
import com.example.jp1.model.GamesApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesProvider {

    @GET("giveaways")
    suspend fun gameGiveaways(): Response<List<Games>>
    @GET("giveaways?type=game")
    suspend fun freeGames(): Response<List<Games>>
    @GET("giveaways?type=loot")
    suspend fun getFreeLoot(): Response<List<Games>>
    @GET("giveaway?platform=")
    suspend fun gamesByPlatform(@Query("platform") platform:String): Response<List<Games>>
    @GET("giveaway?id=")
    suspend fun gamesById(@Query("id") id:Int): Response<Games>
}