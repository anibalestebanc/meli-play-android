package com.example.search.data

import com.example.search.data.remote.SearchRemoteDataSource
import com.example.search.data.remote.model.asDomain
import com.example.search.domain.repository.SearchRepository
import com.example.search.domain.model.Item

class DefaultSearchRepository(
    private val remoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    override suspend fun getItemsByText(value: String): Result<List<Item>> =
        remoteDataSource.getItemsByText(value).map { items ->
            items.map { it.asDomain() }
        }
}