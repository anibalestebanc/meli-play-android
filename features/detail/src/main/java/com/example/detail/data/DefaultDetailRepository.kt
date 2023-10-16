package com.example.detail.data

import com.example.detail.data.remote.DetailRemoteDataSource
import com.example.detail.data.remote.model.asDomain
import com.example.detail.domain.DetailRepository
import com.example.detail.domain.model.ItemDetail

class DefaultDetailRepository(
    private val remoteDataSource: DetailRemoteDataSource
) : DetailRepository {
    override suspend fun getItemById(id: String): Result<ItemDetail> =
        remoteDataSource.getItemById(id).map { it.asDomain() }
}