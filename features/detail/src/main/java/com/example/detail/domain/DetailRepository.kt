package com.example.detail.domain

import com.example.detail.domain.model.ItemDetail

interface DetailRepository {
    suspend fun getItemById(id: String): Result<ItemDetail>
}