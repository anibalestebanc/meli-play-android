package com.example.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detail.domain.DetailRepository
import com.example.detail.domain.model.ItemDetail
import com.example.network.ApiError
import com.example.network.ErrorHandler.handleError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: DetailRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailUiState())
    val detailState: StateFlow<DetailUiState> = _detailState

    private var fetchJob: Job? = null
    fun getItemById(itemID: String) {
        if (detailState.value.itemDetail == null) {
            fetchJob?.cancel()
            fetchJob = viewModelScope.launch(dispatcher) {
                updateState(isLoading = true)
                repository.getItemById(itemID).fold(
                    onSuccess = {
                                updateState(itemDetail = it)
                    },
                    onFailure = {
                        updateState(error = handleError(it))
                    }
                )
            }
        }
    }

    private fun updateState(
        isLoading: Boolean = false,
        itemDetail: ItemDetail? = null,
        error: ApiError? = null
    ) {
        _detailState.value = _detailState.value.copy(
            isLoading = isLoading,
            itemDetail = itemDetail,
            error = error
        )
    }
}