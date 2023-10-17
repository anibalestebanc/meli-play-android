package com.example.search.data

import com.example.search.createDomainItem
import com.example.search.createRemoteSearchItem
import com.example.search.data.remote.SearchRemoteDataSource
import com.example.search.data.remote.model.RemoteItem
import com.example.search.domain.model.Item
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefaultSearchRepositoryTest {
    @MockK
    lateinit var mockRemoteDataSource: SearchRemoteDataSource

    private lateinit var repository: DefaultSearchRepository

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        repository = DefaultSearchRepository(mockRemoteDataSource)
    }

    @Test
    fun `given search items, when call to getItemsByText, then return result list`() = runBlocking {
        //GIVEN
        val remoteItems: Result<List<RemoteItem>> = Result.success(
            listOf(createRemoteSearchItem())
        )
        val expectedResult: Result<List<Item>> = Result.success(listOf(createDomainItem()))

        coEvery { mockRemoteDataSource.getItemsByText("value") } returns remoteItems
        //WHEN
        val result = repository.getItemsByText("value")
        //THEN
        Assertions.assertEquals(expectedResult, result)
    }

    @Test
    fun `given search items, when call to getItemsByText, then return a empty list`() =
        runBlocking {
            //GIVEN
            val emptyList: Result<List<RemoteItem>> = Result.success(emptyList())

            val expectedResult: Result<List<Item>> = Result.success(emptyList())

            coEvery { mockRemoteDataSource.getItemsByText("value") } returns emptyList
            //WHEN
            val result = repository.getItemsByText("value")
            //THEN
            Assertions.assertEquals(expectedResult, result)
        }

    @Test
    fun `given search items, when call to getItemsByText, then return error`() = runBlocking {
        //GIVEN
        val searchError: Result<List<RemoteItem>> =
            Result.failure(RuntimeException("Example exception"))

        coEvery { mockRemoteDataSource.getItemsByText("value") } returns searchError
        //WHEN
        val result = repository.getItemsByText("value")
        //THEN
        Assertions.assertEquals(searchError, result)
    }
}