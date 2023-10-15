package com.example.detail.presentation

import com.example.detail.domain.model.ItemDetail

data class DetailUiState(
    val isLoading : Boolean = false,
    val itemDetail : ItemDetail? = null
)
