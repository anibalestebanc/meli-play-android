package com.example.detail.presentation

import com.example.detail.domain.model.ItemDetail
import com.example.network.ApiError

data class DetailUiState(
    val isLoading : Boolean = false,
    val itemDetail : ItemDetail? = null,
    val error : ApiError? = null
)
