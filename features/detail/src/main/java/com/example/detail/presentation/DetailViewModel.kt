package com.example.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detail.domain.DetailRepository
import com.example.detail.domain.model.ItemDetail
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
                try {
                    updateState(false, repository.getItemById(itemID))
                } catch (e: Exception) {
                    updateState(isLoading = false)
                }
            }
        }
    }

    private fun updateState(isLoading: Boolean = false, itemDetail: ItemDetail? = null) {
        _detailState.value = _detailState.value.copy(
            isLoading = isLoading,
            itemDetail = itemDetail
        )
    }
}