package com.example.detail.data

import com.example.detail.createDomainItem
import com.example.detail.createRemoteItem
import com.example.detail.data.remote.DetailRemoteDataSource
import com.example.detail.data.remote.model.RemoteItem
import com.example.detail.domain.model.ItemDetail
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefaultDetailRepositoryTest{
    @MockK
    lateinit var mockRemoteDataSource: DetailRemoteDataSource

    private lateinit var repository: DefaultDetailRepository

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        repository = DefaultDetailRepository(mockRemoteDataSource)
    }

    @Test
    fun `given item detail, when call to getItemById, then return detail`() = runBlocking {
        //GIVEN
        val remoteItem: Result<RemoteItem> = Result.success(
            createRemoteItem()
        )
        val expectedResult: Result<ItemDetail> = Result.success(createDomainItem())

        coEvery { mockRemoteDataSource.getItemById("value") } returns remoteItem
        //WHEN
        val result = repository.getItemById("value")
        //THEN
        assertEquals(expectedResult, result)
    }


    @Test
    fun `given item detail, when call to getItemById, then return error`() = runBlocking {
        //GIVEN
        val detailError: Result<RemoteItem> =
            Result.failure(RuntimeException("Example exception"))

        coEvery { mockRemoteDataSource.getItemById("value") } returns detailError
        //WHEN
        val result = repository.getItemById("value")
        //THEN
        assertEquals(detailError, result)
    }
}