package com.example.detail.presentation

import com.example.detail.InstantTaskExecution
import com.example.detail.createDomainItem
import com.example.detail.domain.DetailRepository
import com.example.network.ApiError
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
class DetailViewModelTest {
    @MockK
    lateinit var mockRepository: DetailRepository

    private lateinit var viewModel: DetailViewModel

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = DetailViewModel(
            repository = mockRepository,
            dispatcher = UnconfinedTestDispatcher()
        )
    }

    @Test
    fun `given item detail, when call to getItemById, then return item`() {
        //GIVEN
        val domainItem = createDomainItem()
        coEvery { mockRepository.getItemById("value") } returns Result.success(domainItem)

        val expectedUiState = DetailUiState(itemDetail = domainItem)
        //WHEN
        viewModel.getItemById("value")
        //THEN
        assertEquals(expectedUiState, viewModel.detailState.value)
    }

    @Test
    fun `given item detail, when call to getItemById, then return network error`() {
        //GIVEN
        coEvery { mockRepository.getItemById("value") } returns Result.failure(IOException())

        val expectedUiState = DetailUiState(error = ApiError.Network)
        //WHEN
        viewModel.getItemById("value")
        //THEN
        assertEquals(expectedUiState, viewModel.detailState.value)
    }

    @Test
    fun `given item detail, when call to getItemById, then return server error`() {
        //GIVEN
        coEvery { mockRepository.getItemById("value") } returns Result.failure(
            HttpException(
                Response.success(
                    ""
                )
            )
        )

        val expectedUiState = DetailUiState(error = ApiError.Server)
        //WHEN
        viewModel.getItemById("value")
        //THEN
        assertEquals(expectedUiState, viewModel.detailState.value)
    }

    @Test
    fun `given item detail, when call to getItemById, then return Unknown error`() {
        //GIVEN
        coEvery { mockRepository.getItemById("value") } returns Result.failure(
            RuntimeException()
        )

        val expectedUiState = DetailUiState(error = ApiError.Unknown)
        //WHEN
        viewModel.getItemById("value")
        //THEN
        assertEquals(expectedUiState, viewModel.detailState.value)
    }
}