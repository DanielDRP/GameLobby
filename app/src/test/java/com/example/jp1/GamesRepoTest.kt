package com.example.jp1

import com.example.jp1.provider.GamesProvider
import com.example.jp1.repository.GamesRepositoryImp
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GamesRepoTest {
    private val mockWebServer = MockWebServer()

    private val gamesProvider = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GamesProvider::class.java)
    private val gamesRepository = GamesRepositoryImp(gamesProvider)

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun `Games Response is Correct`(){
        mockWebServer.enqueueResponse("games_giveaways.json")

        runBlocking {
            val games = gamesRepository.getAllGiveaways()
            assertEquals(2,games.size)
            assertEquals("Pathfinder: Kingmaker - Enhanced Plus Edition", games[0].title)
            assertEquals("I Have No Mouth And I Must Scream", games[1].title)
        }
    }
}

fun MockWebServer.enqueueResponse(filePath: String){
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(it.readString(StandardCharsets.UTF_8))
        )
    }
}