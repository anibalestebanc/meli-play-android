package com.example.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.ApiError
import com.example.network.ErrorHandler.handleError
import com.example.search.domain.model.Item
import com.example.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchUiState())
    val searchState: StateFlow<SearchUiState> = _searchState

    private var fetchJob: Job? = null
    fun searchByText(value: String) {
        if (searchState.value.items.isNullOrEmpty()) {
            fetchJob?.cancel()
            fetchJob = viewModelScope.launch(dispatcher) {
                updateState(isLoading = true)
                searchUseCase(value).fold(
                    onSuccess = { updateState(items = it) },
                    onFailure = { updateState(error = handleError(it)) }
                )
            }
        }
    }

    private fun updateState(
        isLoading: Boolean = false,
        items: List<Item>? = null,
        error: ApiError? = null
    ) {
        _searchState.value = _searchState.value.copy(
            isLoading = isLoading,
            items = items,
            error = error
        )
    }
}