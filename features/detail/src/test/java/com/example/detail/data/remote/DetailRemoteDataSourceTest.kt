package com.example.detail.data.remote

import com.example.detail.createItemResponse
import com.example.detail.data.remote.model.RemoteItem
import com.example.detail.data.remote.model.ResponseDetail
import com.example.network.MockWebServerFactory
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

class DetailRemoteDataSourceTest {

    private val mockedWebServer = MockWebServer()

    private lateinit var itemsApi: ItemsApi

    private lateinit var remoteDataSource: DetailRemoteDataSource

    @BeforeEach
    fun setup() {
        itemsApi = MockWebServerFactory<ItemsApi>().create(
            mockWebServer = mockedWebServer, kClass = ItemsApi::class.java
        )
        remoteDataSource = DetailRemoteDataSource(
            itemsApi = itemsApi,
            dispatcher = Dispatchers.Default
        )
    }

    @AfterEach
    fun tearShutDown() {
        mockedWebServer.shutdown()
    }

    @Test
    fun `given item detail, when call to getItemById, then return success`() = runBlocking {
        //GIVEN
        val itemResponse = createItemResponse()
        val itemResponseBody = Gson().toJson(itemResponse)

        mockedWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(itemResponseBody)
        )

        val expectedResult: Result<RemoteItem> =
            Result.success(itemResponse[0].body)

        //WHEN
        val result = remoteDataSource.getItemById("value")
        //THEN
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun `given item detail, when call to getItemById, then return httpException error`() =
        runBlocking {
            //GIVEN
            mockedWebServer.enqueue(MockResponse().setResponseCode(500))

            //WHEN
            val result = remoteDataSource.getItemById("value")
            //THEN
            Assertions.assertNotNull(result)
            Assertions.assertTrue(result.exceptionOrNull() is HttpException)
        }

}