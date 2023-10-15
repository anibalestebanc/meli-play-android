package com.example.detail.data

import com.example.detail.data.remote.model.asDomain
import com.example.detail.data.source.DetailDataSource
import com.example.detail.domain.DetailRepository
import com.example.detail.domain.model.ItemDetail

class DefaultDetailRepository(
    private val datasource: DetailDataSource
) : DetailRepository {
    override suspend fun getItemById(id: String): ItemDetail =
        datasource.getItemById(id).asDomain()
}