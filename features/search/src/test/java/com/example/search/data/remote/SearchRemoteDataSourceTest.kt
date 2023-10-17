package com.example.search.data.remote

import com.example.network.MockWebServerFactory
import com.example.search.createSearchResponse
import com.example.search.data.remote.model.RemoteItem
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.HttpException

class SearchRemoteDataSourceTest {

    private val mockedWebServer = MockWebServer()

    private lateinit var searchApi: SearchApi

    private lateinit var remoteDataSource: SearchRemoteDataSource

    private val siteID = "MLC"

    @BeforeEach
    fun setup() {
        searchApi = MockWebServerFactory<SearchApi>().create(
            mockWebServer = mockedWebServer, kClass = SearchApi::class.java
        )
        remoteDataSource = SearchRemoteDataSource(
            searchApi = searchApi,
            siteId = siteID,
            ioDispatcher = Dispatchers.Default
        )
    }

    @AfterEach
    fun tearShutDown() {
        mockedWebServer.shutdown()
    }

    @Test
    fun `given search results, when call to getItemsByText, then return success`() = runBlocking {
        //GIVEN
        val searchResponse = createSearchResponse()
        val searchResponseBody = Gson().toJson(searchResponse)

        mockedWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(searchResponseBody)
        )

        val expectedResult: Result<List<RemoteItem>> =
            Result.success(searchResponse.result)

        //WHEN
        val result = remoteDataSource.getItemsByText("value")
        //THEN
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun `given search results, when call to getItemsByText, then return httpException error`() = runBlocking {
        //GIVEN
        mockedWebServer.enqueue(MockResponse().setResponseCode(500))

        //WHEN
        val result = remoteDataSource.getItemsByText("value")
        //THEN
        Assertions.assertNotNull(result)
        Assertions.assertTrue(result.exceptionOrNull() is HttpException)
    }
}