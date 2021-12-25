package com.example.jp1.repository

import com.example.jp1.model.Games
import com.example.jp1.model.GamesApiResponse
import com.example.jp1.provider.GamesProvider
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

interface GamesRepo {
    suspend fun getAllGiveaways():List<Games>
    suspend fun freeGames():List<Games>
    suspend fun getFreeLoot():List<Games>
    fun gamesById(id: Int): Games
}

class GamesRepositoryImp @Inject constructor(
    private val gamesProvider: GamesProvider
): GamesRepo {

    private var games: List<Games> = emptyList()

    override suspend fun getAllGiveaways(): List<Games> {
        val apiResponse = gamesProvider.gameGiveaways().body()
        /*
        if (apiResponse?.status == "error"){
            when(apiResponse.code){
                "404" -> throw NotFoundException()
                "500" -> throw ServerErrorException()
                else -> throw Exception()
            }
        }
         */
        games = apiResponse ?: emptyList()
        return games
    }

    override suspend fun freeGames(): List<Games> {
        val apiResponse = gamesProvider.freeGames().body()
        games = apiResponse ?: emptyList()
        return games
    }

    override suspend fun getFreeLoot(): List<Games> {
        val apiResponse = gamesProvider.getFreeLoot().body()
        games = apiResponse ?: emptyList()
        return games
    }

    override fun gamesById(id: Int): Games {
        games.first{it.id == id}
        return games[0]
    }


}

class ServerErrorException : java.lang.Exception()
class NotFoundException : java.lang.Exception()