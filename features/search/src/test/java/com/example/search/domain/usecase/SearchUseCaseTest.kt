package com.example.search.domain.usecase

import com.example.search.createDomainItem
import com.example.search.domain.model.Item
import com.example.search.domain.repository.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchUseCaseTest{

    @MockK
    lateinit var mockSearchRepository: SearchRepository

    private lateinit var searchUseCase: SearchUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        searchUseCase = SearchUseCase(mockSearchRepository)
    }

    @Test
    fun `given search items, when call to SearchUseCase, then return search list`() = runBlocking {
        //GIVEN
        val results : Result<List<Item>> =  Result.success(listOf(createDomainItem()))

        coEvery { mockSearchRepository.getItemsByText("value") } returns results
        //WHEN
        val result = searchUseCase("value")
        //THEN
        assertEquals(results, result)
    }

    @Test
    fun `given search items, when call to SearchUseCase, then return error`() = runBlocking {
        //GIVEN
        val error : Result<List<Item>> = Result.failure(RuntimeException())

        coEvery { mockSearchRepository.getItemsByText("value") } returns error
        //WHEN
        val result = searchUseCase("value")
        //THEN
        assertEquals(error, result)
    }
}