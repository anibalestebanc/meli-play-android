package com.example.search.presentation

import com.example.network.ApiError
import com.example.search.InstantTaskExecution
import com.example.search.createDomainItem
import com.example.search.domain.usecase.SearchUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecution::class)
class SearchViewModelTest {
    @MockK
    lateinit var mockSearchUseCase: SearchUseCase

    private lateinit var viewModel: SearchViewModel

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = SearchViewModel(
            searchUseCase = mockSearchUseCase,
            dispatcher = UnconfinedTestDispatcher()
        )
    }

    @Test
    fun `given search list, when call to searchByText, then return search list`() {
        //GIVEN
        val results = listOf(createDomainItem())
        coEvery { mockSearchUseCase("value") } returns Result.success(results)

        val expectedUiState = SearchUiState(items = results)
        //WHEN
        viewModel.searchByText("value")
        //THEN
        assertEquals(expectedUiState, viewModel.searchState.value)
    }

    @Test
    fun `given search list, when call to searchByText, then return network error`() {
        //GIVEN
        coEvery { mockSearchUseCase("value") } returns Result.failure(IOException())

        val expectedUiState = SearchUiState(error = ApiError.Network)
        //WHEN
        viewModel.searchByText("value")
        //THEN
        assertEquals(expectedUiState, viewModel.searchState.value)
    }

    @Test
    fun `given search list, when call to searchByText, then return server error`() {
        //GIVEN
        coEvery { mockSearchUseCase("value") } returns Result.failure(
            HttpException(
                Response.success(
                    ""
                )
            )
        )

        val expectedUiState = SearchUiState(error = ApiError.Server)
        //WHEN
        viewModel.searchByText("value")
        //THEN
        assertEquals(expectedUiState, viewModel.searchState.value)
    }

    @Test
    fun `given search list, when call to searchByText, then return Unknown error`() {
        //GIVEN
        coEvery { mockSearchUseCase("value") } returns Result.failure(
            RuntimeException()
        )

        val expectedUiState = SearchUiState(error = ApiError.Unknown)
        //WHEN
        viewModel.searchByText("value")
        //THEN
        assertEquals(expectedUiState, viewModel.searchState.value)
    }
}