package com.example.search.presentation

import com.example.search.domain.model.Item

data class SearchUiState(
    var isLoading : Boolean = false,
    var items : List<Item>? = null
)
