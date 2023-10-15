package com.example.search.data

import com.example.search.data.mappers.asDomain
import com.example.search.data.source.SearchDatasource
import com.example.search.domain.repository.SearchRepository
import com.example.search.domain.model.Item

class DefaultSearchRepository(
    private val datasource: SearchDatasource
) : SearchRepository {
    override suspend fun getItemsByText(value: String): List<Item> =
        datasource.getItemsByText(value).map {
            it.asDomain()
        }
}