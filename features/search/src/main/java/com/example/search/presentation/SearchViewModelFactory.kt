package com.example.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.search.domain.usecase.SearchUseCase

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(private val searchUseCase: SearchUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(searchUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}